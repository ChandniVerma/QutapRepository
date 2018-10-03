package com.exilant.controller;



import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exilant.CommonUtils.ErrorObject;
import com.exilant.CommonUtils.Response;
import com.exilant.CommonUtils.StatusCode;
import com.exilant.CommonUtils.Utils;
import com.exilant.model.ProjectInfoModel;
import com.exilant.service.ProjectInfoService;

@RestController
@RequestMapping("/Qutap")
public class ProjectInfoController {
	
	org.slf4j.Logger log= LoggerFactory.getLogger(ProjectInfoController.class);
	
	@Autowired
	ProjectInfoService projectInfoService;
	
	
	@PostMapping("/saveProject")
	public Response saveProjectInfo(@RequestBody ProjectInfoModel projectInfoModel,HttpServletRequest req) { 
		log.info("url of the application"+req.getRequestURL().toString());
		Response response=projectInfoService.saveProjectInfo(projectInfoModel);
		response.setUrl(req.getRequestURL().toString());
		return response;
		
	}
	
	@GetMapping("/ProjectData/{projectId}")
	public   @ResponseBody String getProjectInfo(@PathVariable String projectId,HttpServletRequest req) throws IOException {
		Response response=Utils.getResponseObject("getting project details data");
		
		
		ProjectInfoModel projectInfoModel=projectInfoService.getProjectInfo(projectId);
		if(projectInfoModel==null) {
			response.setStatus(StatusCode.FAILURE.name());
			ErrorObject err=Utils.getErrorResponse("project info data", "no data found in detail");
		}else {
			response.setStatus(StatusCode.SUCCESS.name());
			response.setUrl(req.getRequestURL().toString());
			response.setData(projectInfoModel);	
		}
		return Utils.getJson(response);
		
		
	}
	@GetMapping("/ListData")
	public  @ResponseBody String getProjectListInfo(HttpServletRequest req) throws IOException {
		Response response=Utils.getResponseObject("getting project details data");
		List<ProjectInfoModel> projectInfoModel=projectInfoService.getProjectListInfo();
		if(projectInfoModel==null) {
			response.setStatus(StatusCode.FAILURE.name());
			ErrorObject err=Utils.getErrorResponse("project info data", "no list of data found in detail");
		}else {
			response.setStatus(StatusCode.SUCCESS.name());
			response.setUrl(req.getRequestURL().toString());
			response.setData(projectInfoModel);	
		}
		
		return Utils.getJson(response);
	}
}
