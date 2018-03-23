package com.solar.tech.util;

 
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
 





import com.ibm.workplace.wcm.api.AuthoringTemplate;
import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.DocumentLibrary;
import com.ibm.workplace.wcm.api.DocumentTypes;
import com.ibm.workplace.wcm.api.SiteArea;
import com.ibm.workplace.wcm.api.WCMApiObject;
import com.ibm.workplace.wcm.api.WCM_API;
import com.ibm.workplace.wcm.api.Workspace;
import com.ibm.workplace.wcm.api.query.Query;
import com.ibm.workplace.wcm.api.query.QueryService;
import com.ibm.workplace.wcm.api.query.ResultIterator;
import com.ibm.workplace.wcm.api.query.Selector;
import com.ibm.workplace.wcm.api.query.Selectors;
import com.solar.tech.bean.AuthArea;
import com.solar.tech.bean.LibraryAuth;
import com.solar.tech.bean.SimpleArea;
import com.solar.tech.bean.WebLibrary;

/***
 * WCM的工具类
 * @author csm
 * @date 2016年11月7日
 *
 */
public class WCMUtils {

	/***
	 * 根据内容库查询出所有的模板
	 * @author csm
	 * @date 2016年6月27日
	 * @param library
	 * @return 返回内容库与模板的关系
	 * @throws Exception
	 */
	public static List<LibraryAuth> getAuthTemplateByLibrary(DocumentLibrary library,Principal currentUser)
			throws Exception{
		List<LibraryAuth> listauth=new ArrayList<LibraryAuth>();
		Workspace wcmspace=getWCMWorkspace(currentUser);
		wcmspace.setCurrentDocumentLibrary(library);
		QueryService queryservice=wcmspace.getQueryService();
		Query query=queryservice.createQuery();
		query.addSelectors(Selectors.libraryEquals(library));
		query.addSelectors(Selectors.typeIn(DocumentTypes.AuthoringTemplate.getApiType()));
		ResultIterator iterator=queryservice.execute(query);
		while (iterator.hasNext()) {
			LibraryAuth libauth=new LibraryAuth();
			WCMApiObject apiobj=(WCMApiObject)iterator.next();
			if(apiobj instanceof AuthoringTemplate){
				libauth.setWebLibrary(library);
				libauth.setAuthTemplate((AuthoringTemplate)apiobj);
				listauth.add(libauth);
			}
			
		}
		return listauth;
	}
	
	/***
	 * 根据内容库查询出所有的模板
	 * @author csm
	 * @date 2016年6月27日
	 * @param library
	 * @return 返回内容库与模板的关系
	 * @throws Exception
	 */
	public static SiteArea getSiteAreaByName(DocumentLibrary library,Principal currentUser,String areaName)
			throws Exception{
		SiteArea area=null;
		Workspace wcmspace=getWCMWorkspace(currentUser);
		wcmspace.setCurrentDocumentLibrary(library);
		QueryService queryservice=wcmspace.getQueryService();
		Query query=queryservice.createQuery();
		query.addSelectors(Selectors.libraryEquals(library));
		query.addSelectors(Selectors.typeIn(DocumentTypes.SiteArea.getApiType()));
		ResultIterator iterator=queryservice.execute(query);
		while (iterator.hasNext()) {
			LibraryAuth libauth=new LibraryAuth();
			WCMApiObject apiobj=(WCMApiObject)iterator.next();
			if(apiobj instanceof SiteArea){
				area=(SiteArea)apiobj;
				if(area.getName().equals(areaName)){
					break;
				}
			}
			
		}
		return area;
	}
	
	
	
	
	/***
	 * 根据内容库查询出所有的模板
	 * @author csm
	 * @date 2016年6月27日
	 * @param library
	 * @return 返回内容库与模板的关系
	 * @throws Exception
	 */
	public static SiteArea getSiteAreaBySelectedName(Principal currentUser,String selectedSite)
			throws Exception{
		SiteArea area=null;
		DocumentLibrary library=null;
		Workspace wcmspace=getWCMWorkspace(currentUser);
		if(null!=selectedSite&&selectedSite.length()>0){
			//1.分解已经选中的栏目
			//innerweb&solar&XuanChuanBu
			String [] strs=selectedSite.split("&solar&");
			String libName=strs[0];//拿到内容库名称
			String siteName=strs[1];//拿到栏目名称
			if(null!=libName&&libName.length()>0){
				library=getWCMLibrary(libName, currentUser);
				wcmspace.setCurrentDocumentLibrary(library);
				System.out.println("You Library Name:"+libName);
				System.out.println("You Site Name:"+siteName);
				QueryService queryservice=wcmspace.getQueryService();
				Query query=queryservice.createQuery();
				query.addSelectors(Selectors.libraryEquals(library));
				query.addSelectors(Selectors.typeIn(DocumentTypes.SiteArea.getApiType()));
				ResultIterator iterator=queryservice.execute(query);
				while (iterator.hasNext()) {
					LibraryAuth libauth=new LibraryAuth();
					WCMApiObject apiobj=(WCMApiObject)iterator.next();
					if(apiobj instanceof SiteArea){
						area=(SiteArea)apiobj;
						if(area.getName().equals(siteName)){
							break;
						}
					}
					
				}
			}
		}
		return area;
	}
	/***
	 * 根据内容库查询出所有的栏目
	 * @author csm
	 * @date 2016年6月27日
	 * @param library
	 * @return 返回内容库与模板的关系
	 * @throws Exception
	 */
	public static List<SiteArea> getAreaByLibrary(DocumentLibrary library,Principal currentUser)
			throws Exception{
		List<SiteArea> listauth=new ArrayList<SiteArea>();
		Workspace wcmspace=getWCMWorkspace(currentUser);
		wcmspace.setCurrentDocumentLibrary(library);
		QueryService queryservice=wcmspace.getQueryService();
		Query query=queryservice.createQuery();
		System.out.println("Selectors.libraryEquals(library) : " + Selectors.libraryEquals(library));
		query.addSelectors(Selectors.libraryEquals(library));
		query.addSelectors(Selectors.typeIn(DocumentTypes.SiteArea.getApiType()));
		System.out.println("Selectors.typeIn(DocumentTypes.SiteArea.getApiType() \n:" + DocumentTypes.SiteArea.getApiType()  
				+ "," + Selectors.typeIn(DocumentTypes.SiteArea.getApiType()));
		ResultIterator iterator=queryservice.execute(query);
		while (iterator.hasNext()) {
			WCMApiObject apiobj=(WCMApiObject)iterator.next();
			if(apiobj instanceof SiteArea){
				System.out.println("siteArea : " + apiobj);
				listauth.add((SiteArea)apiobj);
			}
		}
		return listauth;
	}
	/****
	 * 根据配置文件筛选相应的站点
	 * @author csm
	 * @date 2016年11月7日
	 * @return
	 */
	public static List<SimpleArea> getConfigAreaList(List<SiteArea> listauth,
			WebLibrary webLibrary){
		System.out.println("根据配置文件筛选相应的站点 start");
		List<SimpleArea> listarea=new ArrayList<SimpleArea>();
		List<AuthArea> autharealist=webLibrary.getAuthAreaList();
		
	    for (int j = 0; j < autharealist.size(); j++) {
	    	System.out.println("time j :" + j);
    		AuthArea authareaobj = autharealist.get(j);
    	    SimpleArea newobj=new SimpleArea();
    		for (int i = 0; i < listauth.size(); i++) {
    			System.out.println("time i :" + i);
    			SiteArea areaobj = listauth.get(i);
    	    	if(authareaobj.getAreaId().equals(areaobj.getName())){
    	    		System.out.println("now areaobj.getName() is :" + areaobj.getName());
	    	    	newobj.setTitle(areaobj.getTitle());
	    			newobj.setAreaName(areaobj.getName());
	    			newobj.setSiteName(webLibrary.getUnqiueName());
	    			System.out.println("title :" + areaobj.getTitle() + ", name :" + areaobj.getName() + ",UnqiueName: " + webLibrary.getUnqiueName());
	    			listarea.add(newobj);
	    			break;
    	    	}
    		}
    	}
	    System.out.println("根据配置文件筛选相应的站点 end!");
	    
		return listarea;
	}
	/****
	 * 查询出当前内容库和当前库的所有栏目集合
	 * @author csm
	 * @date 2016年11月7日
	 * @return
	 */
	public static List<SimpleArea> getAllAreaList(List<SiteArea> listauth,
			WebLibrary webLibrary){
		System.out.println("查询出当前内容库和当前库的所有栏目集合 start!");
		List<SimpleArea> listarea=new ArrayList<SimpleArea>();
	    for (int i = 0; i < listauth.size(); i++) {
	    	SimpleArea areaobj=new SimpleArea();
			SiteArea sitearea = listauth.get(i);
			areaobj.setAreaName(sitearea.getName());
			areaobj.setTitle(sitearea.getTitle());
			areaobj.setSiteName(webLibrary.getUnqiueName());
			System.out.println("title :" + sitearea.getTitle() + ", name :" + sitearea.getName() + ",UnqiueName: " + webLibrary.getUnqiueName());
			listarea.add(areaobj);
		}
	    System.out.println("查询出当前内容库和当前库的所有栏目集合 end!");
		return listarea;
	}
	/***
	 * 判断当前的内容名称是否存在
	 * @author csm
	 * @date 2016年11月7日
	 * @param library
	 * @param contentName
	 * @return
	 * @throws Exception
	 */
	public static boolean getCreateNewContentName(DocumentLibrary library,String contentName,Principal currentUser)
	throws Exception{
		System.out.println("判断当前的内容名称是否存在 start");
		List<LibraryAuth> listauth=new ArrayList<LibraryAuth>();
		Workspace wcmspace=getWCMWorkspace(currentUser);
		System.out.println("currentUser: " + currentUser);
		wcmspace.setCurrentDocumentLibrary(library);
		QueryService queryservice=wcmspace.getQueryService();
		System.out.println(11);
		Query query=queryservice.createQuery();
		System.out.println(22);
		query.addSelectors(Selectors.libraryEquals(library));
		System.out.println(library + "," + Selectors.libraryEquals(library));
		query.addSelectors(Selectors.typeIn(DocumentTypes.Content.getApiType()));
		System.out.println(DocumentTypes.Content.getApiType() + "," + DocumentTypes.Content.getApiType());
		ResultIterator iterator=queryservice.execute(query);
		boolean isExit=false;
		while (iterator.hasNext()) {
			LibraryAuth libauth=new LibraryAuth();
			WCMApiObject apiobj=(WCMApiObject)iterator.next();
			if(apiobj instanceof Content){
				Content te=(Content)apiobj;
				if(null!=contentName&&contentName.equals(te.getName())){
					isExit=true;
					break;
				}
			}
			
		}
		System.out.println("判断当前的内容名称是否存在 end");
		return isExit;
	}
	/***
	 * 根据内容库名称拿到对应的内容库
	 * @author csm
	 * @date 2016年6月27日
	 * @param libraryName 内容库名称
	 * @return 返回内容库对象
	 * @throws Exception
	 */
	public static DocumentLibrary getWCMLibrary(String libraryName,Principal currentUser) throws Exception{
    	Workspace wcmspace=getWCMWorkspace(currentUser);
    	DocumentLibrary library=wcmspace.getDocumentLibrary(libraryName);
    	return library;
    	
    }
	/***
	 * 根据当前用户和web内容库名称返回
	 * @author csm
	 * @date 2016年7月29日
	 * @param currentUser 当前登录用户
	 * @param libraryName web内容库名称
	 * @return 返回需要的web内容库
	 * @throws Exception
	 */
	public static DocumentLibrary getWCMLibraryByCurrentUser(Principal currentUser,
			String libraryName) throws Exception{
		 
		Workspace workspace=WCM_API.getRepository().getWorkspace(currentUser);
		DocumentLibrary library=workspace.getDocumentLibrary(libraryName);
		return library;
	}
	/***
	 * 根据当前的用户返回需要的工作空间
	 * @author csm
	 * @date 2016年7月29日
	 * @param currentUser 当前登录用户
	 * @return 返回WCM的工作空间
	 * @throws Exception
	 */
	public static Workspace getWorkSpaceByCurrentUser(Principal currentUser)throws Exception{
		Workspace workspace=WCM_API.getRepository().getWorkspace(currentUser);
		return workspace;
	}
    /***
	 * 根据内容库查询出所有的模板
	 * @author csm
	 * @date 2016年6月27日
	 * @param library
	 * @return 返回内容库与模板的关系
	 * @throws Exception
	 */
	public static AuthoringTemplate getAuthTemplate(DocumentLibrary library,
			String authTemplateName,Principal currentUser)
			throws Exception{
		System.out.println("根据内容库查询出所有的模板 start");
		AuthoringTemplate templateObj=null;
		Workspace wcmspace=getWCMWorkspace(currentUser);
		wcmspace.setCurrentDocumentLibrary(library);
		QueryService queryservice=wcmspace.getQueryService();
		Query query=queryservice.createQuery();
		query.addSelectors(Selectors.libraryEquals(library));
		query.addSelectors(Selectors.typeIn(DocumentTypes.AuthoringTemplate.getApiType())); 
	    
		ResultIterator iterator=queryservice.execute(query);
		while (iterator.hasNext()) {
			WCMApiObject apiobj=(WCMApiObject)iterator.next();
			if(apiobj instanceof AuthoringTemplate){
				if(((AuthoringTemplate)apiobj).getName().equals(authTemplateName)){
					templateObj=(AuthoringTemplate)apiobj;
					break;
				}
			}
			
		}
		System.out.println("根据内容库查询出所有的模板 end!");
		return templateObj;
	}
	/***
	 * 根据已选择了的内容库名称和模板名称拿到对应的模板对象
	 * @author csm
	 * @date 2016年6月28日
	 * @param authList
	 * @param libName
	 * @param authTemplate
	 * @return 返回编写模板
	 * @throws Exception
	 */
	public static AuthoringTemplate getTemplateCompant(List<LibraryAuth> authList,
			String libName,String authTemplate) throws Exception{
		System.out.println("返回编写模板 start");
		System.out.println("libName : " + libName);
		System.out.println("authTemplate : " + authTemplate);
		AuthoringTemplate selectTemplate=null;
		for (int i = 0; i < authList.size(); i++) {
			LibraryAuth libauth = authList.get(i);
			if(libauth.getAuthTemplate().getName().equals(authTemplate)&&libauth.getWebLibrary().getName().equals(libName)){
				selectTemplate=libauth.getAuthTemplate();
				break;
			}
		}
		return selectTemplate;
		
	}
    /**
     * 获得WCM的工作空间
     * @author csm
     * @date 2016年6月27日
     * @return
     * @throws Exception
     */
	public static Workspace getWCMWorkspace(Principal currentUser) throws Exception{
    	return WCM_API.getRepository().getWorkspace(currentUser);
    }
}
