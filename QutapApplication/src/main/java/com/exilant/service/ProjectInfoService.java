package com.exilant.service;

import java.util.List;

import com.exilant.CommonUtils.Response;
import com.exilant.model.ProjectInfoModel;

public interface ProjectInfoService {

	Response saveProjectInfo(ProjectInfoModel projectInfoModel) ;
	
	public ProjectInfoModel getProjectInfo(String projectId);

	public List<ProjectInfoModel> getProjectListInfo();

}
