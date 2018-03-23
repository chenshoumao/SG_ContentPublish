package com.solar.tech.util;

import java.util.List;

import com.ibm.workplace.wcm.api.SiteArea;
import com.solar.tech.bean.SimpleArea;
import com.solar.tech.bean.WebLibrary;
import com.solar.tech.bean.WorkFlowConfig;
import com.ibm.workplace.wcm.api.Content;

public class ContentUtil {
	public static final String Content_TITLE = "title";//标题
	public static final String Content_SUMMARY="summary";//简介
	public static final String Content_SOURCE="source";//来源
	public static final String Content_WORKFLOW="workflow";//内容审核流程
	public static final String Content_CONTENT = "wcmcontent";//内容正文
	
	public String Content="content";//与内容演示模板进行对应的字段
	public static final String Content_FILE = "uploadfile";
	
	public static final String UploadfileDirct = "UploadFile";
	
	
	public String is_Create="false";//是否已经创建了内容
	
	public String ConfigFilePath="";//配置文件路径
	public List<WebLibrary> listlibrary=null;//配置内容库的集合
	public List<SimpleArea> listArea=null;//
	List<WorkFlowConfig> listFlowConfig=null;//工作流程集合
	public String treeStr="";//树形结构的集合
	public List<Content> listcontent=null;//我的待办的集合。
	
	public List<SiteArea> selectListArea=null;//需要选中的站点
    
    public String isApprovalPage="approvalPage";
	
	
	//配置文件的属性
	public String fileConfigName="SolarNewsConfig.xml";
}	
