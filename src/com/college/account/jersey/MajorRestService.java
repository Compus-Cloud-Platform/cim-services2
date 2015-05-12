package com.college.account.jersey;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

import com.college.account.service.DaoDeptOrgService;
import com.college.account.service.DaoMajorDeptService;
import com.college.account.service.DaoMajorService;
import com.college.util.Cause;
import com.college.util.JacksonUtils;
import com.college.util.Logger4j;
import com.college.util.ServiceFactoryBean;


@Path("/organizations/{id}/departments/{idD}/majors")
public class MajorRestService {
	
	private static DaoMajorService p = ServiceFactoryBean.getMajorService();
	private static DaoDeptOrgService pDO = ServiceFactoryBean.getDeptOrgService();
	private static DaoMajorDeptService pMD = ServiceFactoryBean.getMajorDeptService();
	
    private static final Logger log = Logger4j.getLogger(MajorRestService.class);
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
	public String save(@PathParam("id") String id,
                       @PathParam("idD") String idD,
                       String jsonString){
    	
    	try {
    		
    		String result = pDO.isJudge(id, idD, jsonString);
    		
    		if(!Cause.isSuccess(result)){
    			return result;
    		}
    		
    		@SuppressWarnings("unchecked")
    		Map<String,Object> map = JacksonUtils.objectMapper.readValue(jsonString, Map.class);
    		
    		Integer majorId = Integer.parseInt(map.get("majorId").toString());
    		Integer deptOrgId = Integer.parseInt(map.get("deptOrgMapId").toString());
			
			return pMD.save(majorId, deptOrgId, null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return Cause.getFailcode(5000, "", "system error");
    }
    
    @DELETE
    @Path("/{idM}")
	public String deleteInfo(@PathParam("id") String id,
                             @PathParam("idD") String idD,
                             @PathParam("idM") String idM){
    	
    	try {
    		
			Integer idtemp = pDO.getIsRight(id, idD);
			
			return pMD.del(idM, idtemp.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
    	
    	return Cause.getFailcode(5000, "", "system error");
    }
    
    
    @GET
	public String searchInfo(@PathParam("id") String id,
                             @PathParam("idD") String idD){
    	return null;
    }
//    
//    @PUT
//	@Consumes({MediaType.APPLICATION_JSON})
//	@Path("/{id}")
//	public String updateInfo(@PathParam("id") String id,
//	                                          String jsonString){
//		try {
//			
//			String result=  p.upd(Integer.parseInt(id), jsonString);
//			return result;
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error(e);
//		}
//		
//		return Cause.getFailcode(5000, "", "system error");
//	}
//    
//    @DELETE
//	@Consumes({MediaType.APPLICATION_JSON})
//	@Path("/{id}")
//	public String deleteInfo(@PathParam("id") String id){
//		try {
//			
//			String result=  p.del(Integer.parseInt(id));
//			return result;
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error(e);
//		}
//		
//		return Cause.getFailcode(5000, "", "system error");
//	}
//    
//    @GET
//	@Consumes({MediaType.APPLICATION_JSON})
//	@Path("/{id}")
//	public String searchInfo(@PathParam("id") String id)
//	{
//		try {
//			
//			return p.sel(Integer.parseInt(id));
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error(e);
//		}
//		
//		return Cause.getFailcode(5000, "", "system error");
//		
//	}

}
