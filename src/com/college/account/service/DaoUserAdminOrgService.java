package com.college.account.service;

import java.util.Date;

import com.college.account.bean.UserAdminOrg;
import com.college.util.Cause;

public class DaoUserAdminOrgService extends  DaoService<UserAdminOrg>{
	
	public static String tablename = "UserAdminOrg";
	
	public static int USERADMINORGRELATIONEXIST = 14001;
	
	private UserAdminOrg getObj(Integer userId, Integer relationId){
		
		UserAdminOrg  useradminorg = searchByMutiFeild(tablename, "loginIdorgId", new Integer[]{userId, relationId});
		
		return useradminorg;
	}

	public String save(Integer userId, Integer relationId, Integer operId){
		
		if(null != getObj(userId, relationId)){
			
			return Cause.getFailcode(USERADMINORGRELATIONEXIST, "id", "relation is exist");
		}
		
		UserAdminOrg useradminorg = new UserAdminOrg(userId, relationId, new Date(), operId);
		
		Integer id = create(useradminorg);
		
		return Cause.getSuccess(id);
	}
	
	public String del(Integer userId, Integer relationId, Integer operId){
		
		UserAdminOrg  useradminorg = getObj(userId, relationId);
		
		if(null == useradminorg){
			
			return Cause.getFailcode(USERADMINORGRELATIONEXIST, "id", "relation is exist");
		}
		
		delete(UserAdminOrg.class, useradminorg.getId());
		
		return  Cause.getSuccess(useradminorg.getId());
	}
	
	public String sel(Integer userId, Integer relationId, Integer operId){
		
		//UserAdminOrg  useradminorg = getObj(userId, relationId);
		return  null;
	}
}
