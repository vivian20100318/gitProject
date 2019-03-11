package com.hjkj.business.usermanage.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.hjkj.business.usermanage.po.CaseMailMenuInfo;
import com.hjkj.business.usermanage.po.HospitalUserInfo;

public interface IUserManageDao {

	public HospitalUserInfo getHospitalUserByUserId(String user_id)throws SQLException;
	
	public List<CaseMailMenuInfo> getCaseMailMenuListForShow(Map paramMap) throws SQLException;
	
	
	
	
}
