package com.solar.tech.util;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ibm.workplace.wcm.api.DocumentId;
import com.solar.tech.bean.AuthArea;
import com.solar.tech.bean.WebLibrary;
import com.solar.tech.bean.WorkFlowConfig;
 
/***
 * 读取配置文件的实体
 * @author csm
 * @date 2016年11月7日
 *
 */
public class ReadConfigXML {
  /***
   * 读取配置的方法 
   * @author csm
   * @date 2016年11月7日
   * @param ConfigFilePath 配置文件的路径
   * @return 返回所有的WEB内容库的配置信息
   */
  public static List<WebLibrary> getAllConfig(String ConfigFilePath) throws Exception{
	  //内容库配置信息集合
	  System.out.println("内容库配置信息集合 start");
	  System.out.println("ConfigFilePath : " + ConfigFilePath);
	  List<WebLibrary> listLibrary=new ArrayList<WebLibrary>();
	  
	  DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	  DocumentBuilder bulid=factory.newDocumentBuilder();
	  
	  File configfile=new File(ConfigFilePath);
	  if(!configfile.exists()){//配置文件不存在
		  System.out.println("配置文件不存在");
		  throw new Exception(ConfigFilePath+"配置文件不存在"); 
	  }else{
	     Document document= bulid.parse(configfile);  
	     Element element=document.getDocumentElement();
	     NodeList libNodes=element.getElementsByTagName("library");
	     System.out.println("library : " + libNodes.getLength());
	     for (int i = 0; i < libNodes.getLength(); i++) {//length: 2
			Node libNode = libNodes.item(i);
			NodeList childNodes=libNode.getChildNodes();
			//0.创建一个Weblibrary对象（内容库配置信息）
			WebLibrary library=new WebLibrary();
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node childNode = childNodes.item(j);
				//1.拿到内容库的名称
				if(null!=childNode.getNodeName()&&"name".equals(childNode.getNodeName())){
					System.out.println("name : " + childNode.getTextContent());
					library.setName(childNode.getTextContent());
				}else if(null!=childNode.getNodeName()&&"unqiueName".equals(childNode.getNodeName())){
					//2.拿到内容库的唯一名称(唯一标识)
					System.out.println("unqiueName : " + childNode.getTextContent());
					library.setUnqiueName(childNode.getTextContent());
				}else if(null!=childNode.getNodeName()&&"desition".equals(childNode.getNodeName())){
					//3.拿到内容库的唯一名称(唯一标识)
					System.out.println("desition : " + childNode.getTextContent());
					library.setDescribe(childNode.getTextContent());
				}else if(null!=childNode.getNodeName()&&"Auth-Areas".equals(childNode.getNodeName())){
					library.setAuthAreaList(AllLibraryAuthAreaConfig(childNode));
				} 
			}
			listLibrary.add(library);
		 }
	  
	  }
	  System.out.println("内容库配置信息集合 end");
	  return listLibrary;
  }
  /***
   * 读取配置的方法 
   * @author csm
   * @date 2016年11月7日
   * @param ConfigFilePath 配置文件的路径
   * @return 返回所有的WEB内容库的配置信息
   */
  public static Map<String, String> getFilterSiteConfig(String ConfigFilePath) throws Exception{
	  //内容库配置信息集合
	  DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	  DocumentBuilder bulid=factory.newDocumentBuilder();
	  File configfile=new File(ConfigFilePath);
	  Map<String, String> mapstr=new HashMap<String, String>();
	  if(!configfile.exists()){//配置文件不存在
		  throw new Exception(ConfigFilePath+"配置文件不存在");
	  }else{
	     Document document=bulid.parse(configfile);
	     Element element=document.getDocumentElement();
	     NodeList libNodes=element.getElementsByTagName("library");
	     for (int i = 0; i < libNodes.getLength(); i++) {
			Node libNode = libNodes.item(i);
			NodeList childNodes=libNode.getChildNodes();
			//0.创建一个Weblibrary对象（内容库配置信息）
			WebLibrary library=new WebLibrary();
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node childNode = childNodes.item(j);
				if(null!=childNode.getNodeName()&&"filter-sites".equals(childNode.getNodeName())){
					mapstr=FilterSiteConfig(childNode);
					break;
				} 
			}
		 }
	  
	  }
	  return mapstr;
  }
  /***
   * 读取配置的方法 
   * @author csm
   * @date 2016年11月7日
   * @param ConfigFilePath 配置文件的路径
   * @return 返回所有的WEB内容库的配置信息
   */
  public static List<WorkFlowConfig> getAllWorkflowsConfig(String ConfigFilePath,String SelectName) throws Exception{
	  //内容库配置信息集合
	  DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	  DocumentBuilder bulid=factory.newDocumentBuilder();
	  File configfile=new File(ConfigFilePath);
	  List<WorkFlowConfig> listworkflows=new ArrayList<WorkFlowConfig>();
	  if(!configfile.exists()){//配置文件不存在
		  throw new Exception(ConfigFilePath+"配置文件不存在");
	  }else{
	     Document document=bulid.parse(configfile);
	     Element element=document.getDocumentElement();
	     NodeList libNodes=element.getElementsByTagName("library");
	     for (int i = 0; i < libNodes.getLength(); i++) {
			Node libNode = libNodes.item(i);
			NodeList childNodes=libNode.getChildNodes();
			//0.创建一个Weblibrary对象（内容库配置信息）
			WebLibrary library=new WebLibrary();
			String libName=null;
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node childNode = childNodes.item(j);
				if(null!=childNode.getNodeName()&&"name".equals(childNode.getNodeName())){
					libName=childNode.getTextContent();
				}
				if(null!=libName&&libName.equalsIgnoreCase(SelectName)){
					if(null!=childNode.getNodeName()&&"workflows".equals(childNode.getNodeName())){
						listworkflows=getAllWorkFlowIdConfig(childNode);
						break;
					} 
				}
			}
		 }
	  
	  }
	  return listworkflows;
  }
  /***
   * 返回所有的相关配置信息
   * @author csm
   * @date 2016年11月7日
   * @param authAreas AuthArea节点单独解析
   * @return 返回模板与站点(栏目)的配置
   */
  private static List<AuthArea> AllLibraryAuthAreaConfig(Node authAreas){
	  System.out.println("返回所有的相关配置信息 start");
	  List<AuthArea> listauth=new ArrayList<AuthArea>();
	  NodeList nodelist=((Element)authAreas).getElementsByTagName("Auth-Area");
	  System.out.println("get by Tag Name Auth-Area");
	  for (int i = 0; i < nodelist.getLength(); i++) {
			Node libNode = nodelist.item(i);
			NodeList childNodes=libNode.getChildNodes();
			AuthArea auth=new AuthArea();
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node childNode = childNodes.item(j);
				if(null!=childNode.getNodeName()&&"model".equals(childNode.getNodeName())){
					System.out.println("model : " + childNode.getTextContent());
					auth.setAuthTemplateId(childNode.getTextContent());
				}else if(null!=childNode.getNodeName()&&"area".equals(childNode.getNodeName())){
					System.out.println("area : " + childNode.getTextContent());
					auth.setAreaId(childNode.getTextContent());
				} 
			}
			listauth.add(auth);
		 }
	  System.out.println("返回所有的相关配置信息 end");
	  return listauth;
  }
  /***
   * 返回所有的相关配置信息
   * @author csm
   * @date 2016年11月7日
   * @param authAreas AuthArea节点单独解析
   * @return 返回模板与站点(栏目)的配置
   */
  private static Map<String, String> FilterSiteConfig(Node authAreas){
	  Map<String, String> mapstr=new HashMap<String, String>();
	  NodeList nodelist=((Element)authAreas).getElementsByTagName("filter-site");
	  for (int i = 0; i < nodelist.getLength(); i++) {
			Node libNode = nodelist.item(i);
			mapstr.put(libNode.getTextContent(), libNode.getTextContent());
		 }
	  return mapstr;
  }
  /***
   * 返回所有的相关配置信息
   * @author csm
   * @date 2016年11月7日
   * @param authAreas AuthArea节点单独解析
   * @return 返回模板与站点(栏目)的配置
   */
  private static List<WorkFlowConfig> getAllWorkFlowIdConfig(Node authAreas){
	  List<WorkFlowConfig> mapstr=new ArrayList<WorkFlowConfig>();
	  NodeList nodelist=((Element)authAreas).getElementsByTagName("workflow");
	  System.out.println("正在解析工作流程配置！！！！！！！");
	  for (int i = 0; i < nodelist.getLength(); i++) {
			Node libNode = nodelist.item(i);
			NodeList childNodes=libNode.getChildNodes();
			WorkFlowConfig auth=new WorkFlowConfig();
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node childNode = childNodes.item(j);
				if(null!=childNode.getNodeName()&&"workflowid".equals(childNode.getNodeName())){
					auth.setWorkflowid(childNode.getTextContent());
					 System.out.println("正在解析工作流程配置-workflowid："+childNode.getTextContent());
				}else if(null!=childNode.getNodeName()&&"workflowName".equals(childNode.getNodeName())){
					auth.setWorkflowName(childNode.getTextContent());
				} 
			}
			mapstr.add(auth);
		 }
	  System.out.println("mapstr配置："+mapstr);
	  System.out.println("完成解析工作流程配置！！！！！！！");
	  return mapstr;
  }
}

