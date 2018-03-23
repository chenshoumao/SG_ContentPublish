package com.solar.tech.bean;

public class SiteAreaContent {
	public String name;//内容在wcm中的标识
	
	public String title;// 标题 
	public String source;
	public String editor;//文字编辑者
	public String imageFrom;// 图片来源
	public int isImportant;//是否重要新闻
	
	public String content; 
	public String workflow; 
	private String touzi_categoryName;   //投资的类别
	private String zhaopin_categoryName; //招聘的类别 
	private String approver; //审核人
	
	private int stick;//是否置顶 0 否，1 是

	public String getTitle() {
		return title;
	}

	 

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	 

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}
 
	 
	public String getTouzi_categoryName() {
		return touzi_categoryName;
	}

	public void setTouzi_categoryName(String touzi_categoryName) {
		this.touzi_categoryName = touzi_categoryName;
	}

	public String getZhaopin_categoryName() {
		return zhaopin_categoryName;
	}

	public void setZhaopin_categoryName(String zhaopin_categoryName) {
		this.zhaopin_categoryName = zhaopin_categoryName;
	}

	public int getStick() {
		return stick;
	}

	public void setStick(int stick) {
		this.stick = stick;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}



	public String getEditor() {
		return editor;
	}



	public void setEditor(String editor) {
		this.editor = editor;
	}



	public String getImageFrom() {
		return imageFrom;
	}



	public void setImageFrom(String imageFrom) {
		this.imageFrom = imageFrom;
	}



	public int getIsImportant() {
		return isImportant;
	}



	public void setIsImportant(int isImportant) {
		this.isImportant = isImportant;
	}
	
}
