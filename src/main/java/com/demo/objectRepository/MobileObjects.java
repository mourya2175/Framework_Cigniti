package com.demo.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.demo.accelerators.TestEngine;

public class MobileObjects implements CommonObjects{

	
	public By searchBar(){
		return By.id("fk-top-search-box");
	}
	
	public By searchIcon(){
		return By.className("search-bar-icon");
	}
	
	public By mainCategory(String category){
		return By.xpath("//a[@class='menu-text fk-inline-block']/span[normalize-space()='"+category+"']");
	}
	public By subCategory(String MainCategory,String subCategory){
		return By.xpath("//div[contains(@id,'"+MainCategory+"')]//a[normalize-space()='"+subCategory+"']");
	}
	
	
	
	
	
	public By interaction_lnkName(String interactionName){
		return By.linkText(interactionName);
	}

	public By interaction_lnkSelectLanguage(String language){
		return By.linkText(language);
	}

	public By interaction_lblPageTitle(){
		return By.xpath("//h1[@class='ui-title']");
	}

	public By interaction_lblPageTitle(String title){
		
		return By.xpath("//*[contains(@class,'title')][contains(text(),'"+title+"')]");
		//return By.xpath("//h1[@class='ui-title'][contains(text(),'"+title+"')]");
	}

	public By interaction_lblPageHeader(){
		return By.xpath("//p[@class='jma-header-footer header']/p");
	}

	public By interaction_Poc1_lblQuestion(){
		return By.tagName("label");
	}

	public By interaction_Poc1_txtTextArea(){
		return By.tagName("textarea");
	}

	public By interaction_lblPageFooter(){
		return By.xpath("//p[@class='jma-header-footer footer']/p");
	}

	public By interaction_txtBreadcrumbName(String breadcrumbName){
		return By.xpath("//div[@class='ui-content']/div/div[text()='"+breadcrumbName+"']");
	}

	public By interaction_lblcontactUs(String contactUsLabel){
		return By.xpath("//span[contains(text(),'"+contactUsLabel+"')]");
	}

	public By interaction_lblHelp(String helpLabel){
		return By.xpath("//span[contains(text(),'"+helpLabel+"')]/..");
	}

	public By interaction_lblHelpTooltipMsg(String helpMsg){
		return By.xpath("//p[@class='jma-header-footer header'][text()='"+helpMsg+"']");
	}

	//POC2	
	public By interaction_txtInputValue(){
		return By.xpath("//input[@type='text']");
	}

	//POC3

	public By interaction_Poc3_lblErrorHeader(String ErrorHeader){
		return By.xpath("//h3[text()='"+ErrorHeader+"']");
	}

	public By interaction_Poc3_txtErrorMsg(String ErrorMsg){
		return By.xpath("//div[@class='ui-content content'][text()='"+ErrorMsg+"']");
	}

	public By interaction_btnOk(String ErrorMessage){
		return By.xpath("//div[text()='"+ErrorMessage+"']/following-sibling::div/a[text()='OK']");
	}

	//POC4

	public By interaction_lblAnswerHeader(){
		return By.xpath("//h1[@class='ui-title']");
	}

	public By interaction_txtAnswer(){
		return By.xpath("//p[@class='jma-header-footer header']/p");
	}

	//S1T1
	public By interaction_btnNextStatus(String status){
		if (status.equalsIgnoreCase("Enabled")){
			return By.xpath("//div[contains(@class,'ui-page-active')]//div[(@class='next-btn') or contains(@class,'btn-right')]");
		}
		return By.xpath("//div[contains(@class,'btn-disabled ui-disabled next-btn')]");
	}

	public By interaction_btnBackStatus(String status){
		if (status.equalsIgnoreCase("Enabled")){
			return By.xpath("//div[contains(@class,'ui-page-active')]//div[(@class='back-btn') or contains(@class,'btn-left')]");
		}
		return By.xpath("//div[(@class='btn-disabled ui-disabled back-btn)]");
	}

	public By interaction_btnDoneStatus(String status){
		if (status.equalsIgnoreCase("Enabled")){
			return By.xpath("//div[contains(@class,'ui-page-active')]//div[(@class='next-btn') or contains(@class,'btn-right')]");
		}
		return By.xpath("//div[(@class='btn-disabled ui-disabled next-btn')]");
	}

	public By interaction_btnBack(){
		
		return By.xpath("//div[contains(@class,'ui-page-active')]//div[contains(@class,'back-btn') or contains(@class,'btn-left')]");
		
		//	return By.xpath("//div[@class='back-btn']");
		
	}

	public By interaction_btnNext(){
	
		return By.xpath("//div[contains(@class,'ui-page-active')]//div[contains(@class,'next-btn') or contains(@class,'btn-right')]");
		//return By.xpath("//div[@class='next-btn']");
		
	}

	public By interaction_btnDone(){
		return By.xpath("//div[contains(@class,'ui-page-active')]//div[(@class='next-btn') or contains(@class,'btn-right')]");
	}

	//S1T3
	public By interaction_clickChoice(int choice){
		return By.xpath("//ul[@class='ui-listview ui-listview-inset ui-corner-all ui-shadow']/li["+choice+"]//p");
	}

	public By interaction_clickCategeory(int choice){
		return By.xpath("//ul[@class='jma-click-to-continue-category ui-listview ui-listview-inset ui-corner-all ui-shadow']/li["+choice+"]//span");
	}

	public By interaction_clickCategeoryChoice(String choice){
    return By.xpath("//div[contains(@class,'ui-page-active')]//ul[contains(@class,'jma-click-to-continue')]//a[contains(text(),'"+choice+"')]");
		//return By.xpath("//ul[@class='jma-click-to-continue-category ui-listview ui-listview-inset ui-corner-all ui-shadow']//a[contains(text(),"+choice+")]");
	}

	//S1T5
	public By interaction_lblPageHeader(String title){		
		return By.xpath("//p[@class='jma-header-footer header']//p[contains(text(),'"+title+"')]");		
	}
	public By interaction_lblPageFooter(String title){
		return By.xpath("//p[@class='jma-header-footer footer']//p[contains(text(),'"+title+"')]");	
	}

	//S1T7
	public By interaction_lblInnerPageTitle(String title){
		return By.xpath("//div[contains(@class,'ui-page-active')]//*[text()='"+title+"']");
	}
	public By interaction_lblInnerPageHeader(String title){
		return By.xpath("//div[contains(@class,'ui-page-active')]//*[contains(text(),'"+title+"')]");
	}

	public By interaction_btnInnerBack(){
		return By.xpath("//div[contains(@class,'ui-page-active')]//div[(@class='back-btn') or contains(@class,'btn-left')]");
	}

	//S1T9
	public By interaction_ContactUsButtons(String Button){
		return By.xpath("//div[contains(@class,'ui-page-active')]//p[contains(text(),'"+Button+"')]");
		/*if(Button.equalsIgnoreCase("Mail")) {  
			return By.xpath("//a[@class='jma-contactus-email-button ui-btn ui-btn-icon-right ui-icon-carat-r']/p[text()='Mail']");
		}
		else if(Button.equalsIgnoreCase("Chat")){
			return By.xpath("//a[@class='jma-contactus-chat-button ui-btn ui-btn-icon-right ui-icon-carat-r']/p[text()='Chat']");
		}
		else if(Button.equalsIgnoreCase("Call")){
			return By.xpath("//a[@class='jma-contactus-call-button ui-btn ui-btn-icon-right ui-icon-carat-r']/p[text()='Call']");
		}
		else
			return By.xpath("//a[@class='jma-contactus-callback-button ui-btn ui-btn-icon-right ui-icon-carat-r']/p[text()='Call Return']");*/

	}

	public By interaction_PopUpPageTitle(String Title){  
		return By.xpath("//h1[@class='ui-title'][text()='"+Title+"']");
	}

	public By interaction_btnChatclose(){
		return By.xpath("//div[@class='jma-chat-page jma-side-page ui-page ui-page-theme-a ui-page-header-fixed ui-page-footer-fixed ui-page-active']//a");
	}

	//S1T11

	//S1T13
	public By interaction_userName(){
		return By.xpath("//input[@id='boxLoginUserName']");
	}
	public By interaction_password(){
		return By.xpath("//input[@id='boxLoginPassword']");

	}

	public By interaction_submit(){
		return By.xpath("//input[@id='btnLogin']");
	}

	//S1T15
	public By interaction_verifyOrderCollectionText(String attribute,String value){
		return By.xpath("//tr[@class='ui-panel-page-container-a']//b[@class='ui-table-cell-label'][contains(text(),'"+attribute+"')]//ancestor::td[contains(text(),'"+value+"')]");
		
	}

	//S1T17
	public By interaction_btnClose(String ErrorMessage){
		return By.xpath("//div[@class='jaa-simple-error-container']//div[contains(text(),'"+ErrorMessage+"')]");
		
	}
	//S1T18

	//S2T1	 
	public By interaction_btn(String button){
		if(button.equalsIgnoreCase("Back")||button.equalsIgnoreCase("Left"))   
			return By.xpath("//div[contains(@class,'ui-page-active')]//div[contains(@class,'back-btn') or contains(@class,'btn-left')]");
			//return By.xpath("//div[(@class='back-btn')]"); 
		else
			return By.xpath("//div[contains(@class,'ui-page-active')]//div[contains(@class,'next-btn') or contains(@class,'btn-right')]");
			//return By.xpath("//div[(@class='next-btn')]"); 
	}

	public By interaction_btnBack_old(){

		return By.xpath("//div[@class='jaa-contact-us-buttons-container']/following-sibling::div/button[2]");
	}
	public By interaction_btnNext_old(){
		return By.xpath("//div[@class='jaa-contact-us-buttons-container']/following-sibling::div/button[1]");
	}

	//S2T6
	public By interaction_continuePageHeader(){
		return By.xpath("//p[@class='jma-header-footer header']/following-sibling::p/span[contains(text(),'Continue?')]");
	}
	public By interaction_continuePage(String value){
		return By.xpath("//ul[@class='jma-click-to-continue ui-listview ui-listview-inset ui-corner-all ui-shadow']//a[text()='"+value+"']");
	}

	public By interaction_ThankYouPage(){
		return By.xpath("//div[@class='jma-end-page']");

	}
	
	//S2T21	
	public By interaction_breadCrumb(String page){
	 if (page.equalsIgnoreCase("Home"))
	   return By.xpath("//div[@class='jma-breadcrumb-home']");
	  
	  else
	   return By.xpath("//div[@class='jma-breadcrumb-element'][contains(text(),'"+page+"')]");
	  
	 }
	//S4T1
	public By interaction_btnRadio(String button){
		
		return By.xpath("//div[contains(@class,'ui-page-active')]//div[@class='ui-radio']/label[contains(text(),'"+button+"')]");
	}
	public By interaction_logoJacada(){
		return By.xpath("//div[@class='logo']/img[@class='logo-image']");
	}
	
	public By interaction_questionLabel(String questionLabel){
		return By.xpath("//p[@class='jma-section-label']/span[contains(text(),'"+questionLabel+"')]");
	}
}