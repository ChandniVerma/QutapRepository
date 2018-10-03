package com.exilant.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.exilant.CommonUtils.Response;
import com.exilant.CommonUtils.StatusCode;
import com.exilant.CommonUtils.Utils;
import com.exilant.controller.ProjectInfoController;
import com.exilant.domain.ProjectInfoDomain;
import com.exilant.model.ProjectInfoModel;

@Repository
@Transactional
public class ProjectInfoDaoImpl implements ProjectInfoDao{

	org.slf4j.Logger log= LoggerFactory.getLogger(ProjectInfoDaoImpl.class);
	@Autowired 
	MongoTemplate mongoTemplate;
	
	@Override
	public Response saveProjectInfo(ProjectInfoDomain projectInfoDomain) {
		Response response=Utils.getResponseObject("Adding project Details");
		try {
		mongoTemplate.insert(projectInfoDomain,"ProjectInfo");
		response.setStatus(StatusCode.SUCCESS.name());
		response.setData(projectInfoDomain);
		return response;
		}catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus(StatusCode.FAILURE.name());
			response.setErrors(e.getMessage());
			return response;
		}
		
	}
	
	public ProjectInfoDomain getProjectInfo(String projectId) {
		try {
		return mongoTemplate.findById(projectId, ProjectInfoDomain.class);
		}
		catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}
	
	public List<ProjectInfoDomain> getProjectListInfo() {
		try {
		return mongoTemplate.findAll(ProjectInfoDomain.class, "ProjectInfo");
		}
		catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}
	
	@Override
	public Response updateProjectInfo(ProjectInfoDomain projectInfoDomain) {
		Response response=Utils.getResponseObject("Adding project Details");
		try {
			getProjectInfo(projectInfoDomain.getProjectId());
		response.setStatus(StatusCode.SUCCESS.name());
		response.setData(projectInfoDomain);
		return response;
		}catch (Exception e) {
			log.info(e.getMessage());
			response.setStatus(StatusCode.FAILURE.name());
			response.setErrors(e.getMessage());
			return response;
		}
		
	}
}

