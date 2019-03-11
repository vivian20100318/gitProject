package com.hjkj.business.usermanage.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.hjkj.business.usermanage.po.CaseMailMenuInfo;
import com.hjkj.business.usermanage.po.HospitalUserInfo;

public interface IUserManageService {

	public HospitalUserInfo userLogin(String userId, String challenge,String sessionChallenge) throws Exception;
	
	public List<CaseMailMenuInfo> getCaseMailMenuListForShow(Map paramMap) throws SQLException;
	
	
}
