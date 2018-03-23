package com.solar.tech.bean;

public class SiteAreaContent {
	public String name;//内容在wcm中的标识
	
	public String title;// 标题
	public String title2;// 详细标题
	 
	public String number;
	public String source;
	public String content;
	public String summary;
	public String workflow;
	
	
	private String touzi_categoryName;   //投资的类别
	private String zhaopin_categoryName; //招聘的类别
	
	private String office;
	private String PhoneNumber;
	
	private int stick;//是否置顶 0 否，1 是

	public String getTitle() {
		return title;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	 

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
	
}
