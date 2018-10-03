package com.exilant.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exilant.CommonUtils.Response;
import com.exilant.controller.ProjectInfoController;
import com.exilant.dao.ProjectInfoDao;
import com.exilant.domain.ProjectInfoDomain;
import com.exilant.model.ProjectInfoModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;



@Service
public class ProjectInfoServiceImpl implements ProjectInfoService{
	
	org.slf4j.Logger log= LoggerFactory.getLogger(ProjectInfoServiceImpl.class);

	@Autowired
	ProjectInfoDao projectInfoDao;

	@Override
	public Response saveProjectInfo(ProjectInfoModel projectInfoModel) {
		
		try {
		ProjectInfoDomain projectInfoDomain=new ProjectInfoDomain();
		projectInfoModel.setProjectId(UUID.randomUUID().toString());
		
		BeanUtils.copyProperties(projectInfoModel, projectInfoDomain);
		Response response=projectInfoDao.saveProjectInfo(projectInfoDomain);
		return response;
		}catch (Exception e) {	
			log.info(e.getMessage());
		}return null;
	}
	
	@Override
	public ProjectInfoModel getProjectInfo(String projectId) {
		try {
		ProjectInfoModel projectInfoModel=new ProjectInfoModel();
		ProjectInfoDomain projectInfoDomain=projectInfoDao.getProjectInfo(projectId);
		BeanUtils.copyProperties(projectInfoDomain, projectInfoModel);
		return projectInfoModel;
		}
		catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public List<ProjectInfoModel> getProjectListInfo() {
		try {
		List<ProjectInfoDomain>  projectInfoDomain=projectInfoDao.getProjectListInfo();
		java.lang.reflect.Type typeToken=new TypeToken<List<ProjectInfoModel>>() {}.getType();
	//	ObjectMapper mapper=new ObjectMapper();
		ModelMapper mapper=new ModelMapper();
		List<ProjectInfoModel> projectInfoModel=mapper.map(projectInfoDomain, typeToken);
		return projectInfoModel;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public Response updateProjectInfo(ProjectInfoModel projectInfoModel) {
		try {
			ProjectInfoDomain projectInfoDomain=new ProjectInfoDomain();
			
			
			BeanUtils.copyProperties(projectInfoModel, projectInfoDomain);
			Response response=projectInfoDao.saveProjectInfo(projectInfoDomain);
			return response;
			}catch (Exception e) {	
				log.info(e.getMessage());
			}return null;
	}

	
}
