package com.hjkj.business.usermanage.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hjkj.business.usermanage.dao.IUserManageDao;
import com.hjkj.business.usermanage.po.CaseMailMenuInfo;
import com.hjkj.business.usermanage.po.HospitalUserInfo;
import com.hjkj.business.usermanage.service.IUserManageService;
import com.hjkj.business.util.MD5;

@Service("userManageService")
public class UserManageService implements IUserManageService {

	@Resource(name = "userManageDao")
	private IUserManageDao userManageDao;
	
	@Override
	public HospitalUserInfo userLogin(String userId, String challenge,
			String sessionChallenge) throws Exception {
		
		HospitalUserInfo user = userManageDao.getHospitalUserByUserId(userId);
		// 用户不存在
		if (user == null)
			return null;
		
		String password = user.getUser_pwd();
		String shaPwd = MD5.getMD5Str(password + sessionChallenge);
		if (challenge.toLowerCase().equals(shaPwd.toLowerCase())) {
			return user;
		}
		return null;
		
	}
	
	@Override
	public List<CaseMailMenuInfo> getCaseMailMenuListForShow(Map paramMap) throws SQLException {
		// TODO Auto-generated method stub
		return userManageDao.getCaseMailMenuListForShow(paramMap);
	}

}
