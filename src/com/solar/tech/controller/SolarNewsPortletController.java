package com.solar.tech.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.solar.tech.bean.SiteAreaContent;
import com.solar.tech.bean.WorkFlowConfig;
import com.solar.tech.dao.SolarNewsPortletDao;
/**
 * 
 * @author chenshoumao
 * 内容发布
 *
 */
@Controller
@RequestMapping("/SolarNewsPortlet")
public class SolarNewsPortletController {
	@Autowired
	private SolarNewsPortletDao solarNewsPortletDao;
	
	@RequestMapping("/getWorkFlow")
	@ResponseBody
	public List<WorkFlowConfig> getWorkFlow(HttpServletRequest request,HttpSession session){
		String SelectName = session.getAttribute("SelectName").toString();//内网外网标记字段
		List<WorkFlowConfig> listFlowConfig=null;//工作流程集合
		try {
			listFlowConfig = this.solarNewsPortletDao.getWorkFlow(request,SelectName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listFlowConfig;
	}
	
	@RequestMapping("/createContentPage")
	@ResponseBody
	public void createContentPage(HttpSession session,HttpServletRequest reqeust,String upload,String create){
		this.solarNewsPortletDao.createContentPage(session,reqeust,upload,create);
	}
	
	
	
	/**
	 * 个人测试获取站点的问题
	 * @return
	 */
	@RequestMapping("/getSiteArea")
	@ResponseBody
	public Map<String, Object> getSiteArea(HttpServletRequest request,String selectLib,String area){
		Map<String, Object> map = new HashMap();
		map = this.solarNewsPortletDao.getSiteArea(request,selectLib,area);
		return map;
	}
	
	/**
	 * 
	 * @param request
	 * @param file
	 * @param selectLib
	 * @param area
	 * @param title
	 * @param summary
	 * @param source
	 * @param workflow
	 * @param content
	 * @return
	 * note:暂时从前台传递站点当作编写模板吧
	 */
	@RequestMapping("/createContent")
	@ResponseBody
	public Map<String, Object> CreateContentPage(HttpServletRequest request,MultipartFile file,String area, 
			SiteAreaContent siteAreaContent){
		Map<String, Object> map = new HashMap();
		map = this.solarNewsPortletDao.createContent(request,file,area,siteAreaContent);
		return map;
	}
	
	
	 
	 
	
	/*
	 * 内容发布页，审核页面时，需要获取到的相关数据，通过map集合封装
	 * @Author csm
	 * @time 2016/11/9
	 * 参数 request，session，不需获取其他参数
	 */
	@RequestMapping("/getApprovar")
	@ResponseBody
	public Map<String, Object> getApprovar(HttpServletRequest request,HttpSession session,int firstPage,String start,String end){
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.solarNewsPortletDao.getApprovar(request,session,firstPage,start,end);
		
		return map;
	}
	
	/*
	 * get photo draft to commit
	 * @Author csm
	 * @time 2016/11/9
	 * 参数 request，session，不需获取其他参数
	 */
	@RequestMapping("/getPhotoDraftForApprover")
	@ResponseBody
	public Map<String, Object> getPhotoDraftForApprover(HttpServletRequest request,HttpSession session,int firstPage){
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.solarNewsPortletDao.getPhotoDraftForApprover(request,session,firstPage);
		
		return map;
	}
	
	/**
	 * 时间 2018/2/21 nextApprover 指定下一个审核人，这个做法是通过改变作者的署名来实现
	 */
	
	@RequestMapping("/nextApprover")
	@ResponseBody
	public Map<String, Object> nextApprover(HttpServletRequest request,SiteAreaContent siteAreaContent,String contentName){
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.solarNewsPortletDao.nextApprover(request,siteAreaContent,contentName);
		
		return map;
	}
	
	
	/**
	 * 删除内容
	 * chenshoumao
	 */
	@RequestMapping("/deleteContent")
	@ResponseBody
	public Map<String, Object> deleteContent(HttpServletRequest request,String contentName){
		Map<String, Object> map = null;
		map = this.solarNewsPortletDao.deleteContent(request,contentName);
		return map;
	}
	
	/**
	 * 获取个人 已通过审核的项目 包括图片 相册
	 * @Author chenshoumao
	 * @time 2017-03-01
	 */
	@RequestMapping("/getMyPubulished")
	@ResponseBody
	public Map<String, Object> getMyPubulished(HttpServletRequest request,String selectLib,String siteArea,int firstPage){
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.solarNewsPortletDao.getMyPubulishedContent(request,firstPage);
		
		return map;
	}
	
	/**
	 * 获取个人草稿
	 * @author chenshoumao
	 * @time 2017-02-27
	 */
	@RequestMapping("/getMyDraft")
	@ResponseBody
	public Map<String, Object> getMyDraft(HttpServletRequest request,int firstPage,String siteArea,String start,String end){
		Map<String, Object> map = null;
	 
			map = this.solarNewsPortletDao.getMyDraftIncludeContentImage(request,firstPage,siteArea,start,end);
		 
		
		return map;
	}
	
//	@RequestMapping("/getMyDraftAlbum")
//	@ResponseBody
//	public Map<String, Object> getMyDraftAlbum(HttpServletRequest request,int firstPage){
//		Map<String, Object> map = new HashMap<String, Object>();
//		map = this.solarNewsPortletDao.getMyAlbum(request,firstPage);
//		return map;
//	}
	
	/**
	 * 根据内容的name 以及编辑者 获取内容属性,
	 * @author chenshoumao
	 * @time 2017-05-7
	 */
	@RequestMapping("/searchContentByName")
	@ResponseBody
	public Map<String, Object> searchContentByName(HttpServletRequest request,String contentName,String siteArea){
		Map<String, Object> map = null;
		map = this.solarNewsPortletDao.searchContentByName(request,contentName,"Draft Stage","Draft");
		return map;
	}
	
	@RequestMapping("/getMyPublishContentByName")
	@ResponseBody
	public Map<String, Object> getMyPublishContentByName(HttpServletRequest request,String contentName,String siteArea){
		Map<String, Object> map = null;
		map = this.solarNewsPortletDao.searchContentByName(request,contentName,"Publish Stage","Published");
		return map;
	}
	
	/**
	 * 管理员审核内容时，根据
	 * 根据内容的name  获取内容属性
	 * @author chenshoumao
	 * @time 2017-05-18
	 */
	@RequestMapping("/getReviewContentByName")
	@ResponseBody
	public Map<String, Object> getReviewContentByName(HttpServletRequest request,String contentName,String siteArea){
		Map<String, Object> map = null;
		map = this.solarNewsPortletDao.searchContentByName(request,contentName,"Review Stage","Draft");
		return map;
	}
	
	@RequestMapping("/updateContent")
	@ResponseBody
	public Map<String, Object> updateContent(HttpServletRequest request,MultipartFile file,SiteAreaContent siteAreaContent){
		Map<String, Object> map = null;
		map = this.solarNewsPortletDao.updateContent(request,file,siteAreaContent);
		return map;
	}
	
	/**
	 * 根据Document 的id号 提交
	 * @author chenshoumao
	 * @time 2017-02-27
	 */
	@RequestMapping("/commitContent")
	@ResponseBody
	public Map<String, Object> toApprove(HttpServletRequest request,String contentName){
		Map<String, Object> map = null;
		map = this.solarNewsPortletDao.commitContent(request,contentName,"Draft Stage","");
		return map;
	}
	
	/**
	 *  
	 * @author chenshoumao
	 * @time 2018-02-21
	 * 发布内容
	 */
	@RequestMapping("/publishContent")
	@ResponseBody
	public Map<String, Object> publishContent(HttpServletRequest request,String contentName,String comment){
		Map<String, Object> map = null;
		map = this.solarNewsPortletDao.commitContent(request,contentName,"Review Stage",comment);
		return map;
	}
	
	@RequestMapping("/getTemplate")
	@ResponseBody
	public List<Map<String, Object>> getTemplate(String siteArea,HttpServletRequest request){
		List<Map<String, Object>> list = null;
		list = this.solarNewsPortletDao.getTemplate(siteArea,request);
		return list;
	}
	 
	
	/**
	 * @author 陈守貌
	 * @time 2017-01-17
	 * @param current 当前站点域的名字
	 * @param request
	 * 该方法用处，是根据 提供的当前站点域，查询该站点域的子域,只是一级子域
	 * @return
	 
	@RequestMapping("/getChileSite")
	@ResponseBody
	public List<Map<String, Object>> getChildSite(int id,String t,int level,HttpServletRequest request){
		//声明一个集合，用于存储数据并返回
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		list = this.solarNewsPortletDao.getChildSite(id,t,level,request);
		//返回结果
		return list;
	}*/
	
	/**
	 * @author 陈守貌
	 * @time 2017-01-17
	 * @param current 当前站点域的名字
	 * @param request
	 * 该方法用处，是根据 提供的当前站点域，查询该站点域的子域,子域的子域
	 * @return
	 */
	@RequestMapping("/getChileSite")
	@ResponseBody
	public List<Map<String, Object>> getChildSite(String param,HttpServletRequest request){
		//声明一个集合，用于存储数据并返回
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String[] sites = param.split(",");
		int id = 1;
		for(String site : sites){
			list.addAll(this.solarNewsPortletDao.getChildSite(id++,site,1,request));
		}
		
		//返回结果
		return list;
	}
	 

	/**
	 * 时间 2018/2/20 getJson 获取的是当前处理人 对于审批流程的下一步操作， 有无 发布权限 或者 下一步的可选择对象的集合
	 */
	
	@RequestMapping("/getApprovalJson")
	@ResponseBody
	public Map<String, Object> getApprovalJson(HttpServletRequest request){
		 return this.solarNewsPortletDao.getJson(request);
	}
	

	/**
	 * 2018/02/21
	 * 获取当前审核人
	 */
	@RequestMapping("/getCurrentApprover")
	@ResponseBody
	public Map<String, Object> getCurrentApprover(HttpServletRequest request,int firstPage){
		 return this.solarNewsPortletDao.getCurrentApprover(request,firstPage);
	}
	
	/**
	 * 2018/03/20
	 *dianzan
	 */
	@RequestMapping("/updateGreat")
	@ResponseBody
	public Map<String, Object> updateGreat(HttpServletRequest request,String contentName){
		Map<String, Object> map = null;
		map = this.solarNewsPortletDao.updateGreat(request,contentName);
		return map;
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		return "hello";
	}
	 
}
