package com.hjkj.business.usermanage.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hjkj.common.db.SqlMapManager;
import com.hjkj.business.usermanage.dao.IUserManageDao;
import com.hjkj.business.usermanage.po.CaseMailMenuInfo;
import com.hjkj.business.usermanage.po.HospitalUserInfo;

@Repository("userManageDao")
public class UserManageDao implements IUserManageDao {

	@Override
	public HospitalUserInfo getHospitalUserByUserId(String user_id)
			throws SQLException {
		Object result = SqlMapManager.getSqlMap().queryForObject(
				"getHospitalUserByUserId", user_id);
		if (result == null) {
			return null;
		} else {
			return (HospitalUserInfo) result;
		}
	}
	
	@Override
	public List<CaseMailMenuInfo> getCaseMailMenuListForShow(Map paramMap) throws SQLException {
		// TODO Auto-generated method stub
		return SqlMapManager.getSqlMap().queryForList("getCaseMailMenuListForShow",
				paramMap);
	}

}
