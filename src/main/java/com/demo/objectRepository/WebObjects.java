package com.demo.objectRepository;

import org.openqa.selenium.By;

public class WebObjects implements CommonObjects{

	//FlipKart
	
	public By logInLink()
	{
		return By.linkText("Login");
	}
	
	public By emailOrMobileTextBox()
	{
		return By.xpath("//div[@class='login-input-wrap']/input[contains(@class, 'user-email')]");
	}
	
	public By passwordTextBox()
	{
		return By.xpath("//input[contains(@class, 'user-pwd')]");
	}
	
	public By loginButton()
	{
		return By.xpath("//input[contains(@class,'login-btn')]");
	}
	
	public By searchBar(){
		return By.id("fk-top-search-box");
	}
	
	public By searchButton()
	{
		return By.xpath("//input[contains(@class,'search-bar-submit')]");
	}
	
	public By firstProductLink()
	{
		return By.xpath("//div[@id='products']/div[1]/div[1]/div[1]/div/div[2]/div[1]/a");
	}
	
	public By addToCartButton()
	{
		return By.xpath("//div[@id='tab-0-content']/div/div/div[1]/div/div[2]/div/div[1]/form/input[8]");
	}
	
	public By cartToolTipTitle()
	{
		return By.xpath("//div[@class='fk-ui-tooltip-content']/span[@class='title']/strong");
	}
	
	public By greetingMenu()
	{
		return By.xpath("//li[@class='no-border greeting-link']/a");
	}
	
	public By logOut()
	{
		return By.xpath("//a[contains(text(),'Logout')]");
	}
	
	public By cartButton()
	{
		return By.xpath("//a[contains(@class,'btn-cart')]");
	}
	
	public By removeLink()
	{
		return By.xpath("//a[contains(@class,'cart-remove-item')]");
	}
	
	public By trackOrderLink()
	{
		return By.xpath("//span[@class='header-track-icon']");
	}
	
	public By recentOrdersOopsText()
	{
		return By.xpath("//div[@id='recent-orders-tab']/p");
	}
	
	
	
	
	
	
	public By searchIcon(){
		return By.className("search-bar-icon");
	}
	public By mainCategory(String MainCategory){
		return By.xpath("//a[@class='menu-text fk-inline-block']/span[normalize-space()='"+MainCategory+"']");
	}
	
	public By subCategory(String MainCategory,String subCategory){
		return By.xpath("//div[contains(@id,'"+MainCategory.toLowerCase()+"')]//a[normalize-space()='"+subCategory+"']");
	}
	
	public By interaction_lnkName(String interactionName){
		return By.xpath("//li[text()='"+interactionName+"']");
	}

	public By interaction_lnkSelectLanguage(String language){
		return By.xpath("//button/span[text()='"+language+"']");
	}

	public By interaction_lblPageTitle(){
		return By.xpath("//div[@class='jaa-content-panel']/span[@class='jaa-page-header-text']");
	}

	public By interaction_lblPageTitle(String title){
		return By.xpath("//div[@class='jaa-content-panel']/span[@class='jaa-page-header-text'][contains(text(),'"+title+"')]");
	}

	public By interaction_lblPageHeader(){
		return By.xpath("//p[@class='jaa-header-footer header']/p");
	}

	public By interaction_Poc1_lblQuestion(){
		return By.xpath("//p[@class='jaa-section-label']");
	}

	public By interaction_Poc1_txtTextArea(){
		return By.xpath("//textarea[@class='jaa-long-text text ui-widget-content ui-corner-all']");
	}

	public By interaction_lblPageFooter(){
		return By.xpath("//p[@class='jaa-header-footer footer']/p");
	}

	public By interaction_txtBreadcrumbName(String breadcrumbName){
		return By.xpath("//div[@class='jaa-breadcrumb']/div/div[text()='"+breadcrumbName+"']");
	}

	public By interaction_lblcontactUs(String contactUsLabel){
		if(contactUsLabel.equalsIgnoreCase("CONTACT US")||contactUsLabel.equalsIgnoreCase("NOUS CONTACTER"))
			contactUsLabel="contact-us";
		return By.xpath("//button[contains(@class,'"+contactUsLabel+"')]");
		
	}

	public By interaction_lblHelp(String helpLabel){
		if(helpLabel.equalsIgnoreCase("HELP")||helpLabel.equalsIgnoreCase("AIDE"))
			helpLabel="help-button";
		return By.xpath("//div[contains(@class,'jaa-page-header')]//button[contains(@class,'"+helpLabel+"')]");
		//return By.xpath("//div[@class='jaa-page-header-right']//button[contains(@class,'"+helpLabel+"')]");
	}

	public By interaction_lblHelpTooltipMsg(String helpMsg){
		return By.xpath("//div[@id='powerTip'][text()='"+helpMsg+"']");
	}

	//POC2

	public By interaction_txtInputValue(){
		return By.xpath("//input[@type='text']");
	}

	//POC3

	public By interaction_Poc3_lblErrorHeader(String ErrorHeader){
		return By.xpath("//span[text()='"+ErrorHeader+"']");
	}

	public By interaction_Poc3_txtErrorMsg(String ErrorMsg){
		return By.xpath("//div[@class='jaa-confirm-dialog ui-dialog-content ui-widget-content']/p[text()='"+ErrorMsg+"']");
	}

	public By interaction_btnOk(String ErrorMessage){		
		return By.xpath("//div/p[text()='"+ErrorMessage+"']/../following-sibling::div/div/button/span[text()='OK']");
	}

	//POC4

	public By interaction_lblAnswerHeader(){
		return By.xpath("//div[@class='jaa-content-panel']/span[text()='Answer']");			
	}

	public By interaction_txtAnswer(){
		return By.xpath("//p[@class='jaa-header-footer header']/p");
	}

	//S1T1
	public By interaction_btnNextStatus(String status){
		Boolean flag=true;
		if (status.equalsIgnoreCase("Enabled")){
			flag=false; 
		}
		return By.xpath("//div[@class='jaa-content-panel']/following-sibling::div/button[1][@aria-disabled='"+flag+"']");
	}

	public By interaction_btnBackStatus(String status){
		Boolean flag=true;
		if (status.equalsIgnoreCase("Enabled")){
			flag=false; 
		}
		return By.xpath("//div[@class='jaa-content-panel']/following-sibling::div/button[2][@aria-disabled='"+flag+"']");          
	}

	public By interaction_btnDoneStatus(String status){
		Boolean flag=true;
		if (status.equalsIgnoreCase("Enabled")){
			flag=false; 
		}
		return By.xpath("//div[@class='jaa-content-panel']/following-sibling::div/button[1][@aria-disabled='"+flag+"']");
	}

	public By interaction_btnBack(){
		return By.xpath("//div[@class='jaa-content-panel']/following-sibling::div/button[2]");
	}	
	
	public By interaction_btnNext(){
		return By.xpath("//div[@class='jaa-content-panel']/following-sibling::div/button[1]");
	}

	public By interaction_btnDone(){
		return By.xpath("//div[@class='jaa-content-panel']/following-sibling::div/button[1]");
	}

	//S1T3
	public By interaction_clickChoice(int choice){			
		return By.xpath("//ul[@class='jaa-matrix']/li["+choice+"]//p");
	}

	public By interaction_clickCategeory(int choice){
		return By.xpath("//div[@class='jaa-click-to-continue-category ui-accordion ui-widget ui-helper-reset']//h3[contains(text(),"+choice+")]");
	}

	public By interaction_clickCategeoryChoice(String choice){
		return By.xpath("//span[@class='ui-button-text'][contains(text(),"+choice+")]");
	}

	//S1T5

	public By interaction_lblPageHeader(String title){
		return By.xpath("//p[@class='jaa-header-footer header']//p[contains(text(),'"+title+"')]");
	}
	public By interaction_lblPageFooter(String title){
		return By.xpath("//p[@class='jaa-header-footer footer']//p[contains(text(),'"+title+"')]");
	}

	//S1T7
	public By interaction_lblInnerPageTitle(String title){
		return By.xpath("//div[contains(@class,'ui-page-active')]//h1[text()='"+title+"']");
	}
	public By interaction_lblInnerPageHeader(String title){
		return By.xpath("//div[contains(@class,'ui-page-active')]//p[text()='"+title+"']");//----THis is to be modified
	}

	public By interaction_btnInnerBack(){
		return By.xpath("//span[(@class='ui-button-text')][(text()='Préc.')or (text()='Back')]");
	}

	//S1T9
	public By interaction_ContactUsButtons(String Button){
		if(Button.equalsIgnoreCase("Call Return")){
			Button="callback";
		}
		return By.xpath("//p[@class='ui-corner-bottom'][contains(text(),'"+Button+"')]");
		//return By.xpath("//div[contains(@class,'"+Button.toLowerCase()+"')]/following-sibling::P");

		/*if(Button.equalsIgnoreCase("Mail")) {  
			return By.xpath("//div[@class='jaa-contact-us-icon jaa-icon-mailto']/following-sibling::P");
		}
		else if(Button.equalsIgnoreCase("Chat")){
			return By.xpath("//div[@class='jaa-contact-us-icon jaa-icon-chat']/following-sibling::P");
		}
		else if(Button.equalsIgnoreCase("Call")){
			return By.xpath("//div[@class='jaa-contact-us-icon jaa-icon-call']/following-sibling::P");
		}
		else
			return By.xpath("//div[@class='jaa-contact-us-icon jaa-icon-callback']/following-sibling::P"); */  
	}

	public By interaction_PopUpPageTitle(String Title){  

		return By.xpath("//div[@class='jaa-popover-title ui-state-active' and text()='"+Title+"']");
	}

	public By interaction_btnChatclose(){
		return By.xpath("//div[@class='jaa-full-mode-chat']/div/div[@class='jaa-popover-closebutton']");
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
		return By.xpath("//span[contains(text(),'"+attribute+"')]//ancestor::div[@class='jaa-table-wrapper']//div[contains(text(),'"+value+"')]");
		
	}
	//S1T17
	public By interaction_btnClose(String ErrorMessage){
		
		return By.xpath("//div[@class='jaa-simple-error-container']//div[contains(text(),'"+ErrorMessage+"')]");
		
		
	}
	//S1T18

	//S2T1
	public By interaction_btn(String button){

		if(button.equalsIgnoreCase("Next")||button.equalsIgnoreCase("Fini")||button.equalsIgnoreCase("Done")){
			button="right";
		}
		else{
			button="left";}

		return By.xpath("//span[@class='ui-button-text']/ancestor::button[contains(@class,'"+button+"')]");			  

	}

	public By interaction_btnBack_old(){

		return By.xpath("//div[@class='jaa-contact-us-buttons-container']/following-sibling::div/button[2]");
	}

	public By interaction_btnNext_old(){
		return By.xpath("//div[@class='jaa-contact-us-buttons-container']/following-sibling::div/button[1]");
	}
	
	//S2T6
	public By interaction_continuePageHeader(){
		return By.xpath("//p[@class='jaa-header-footer header']/following-sibling::p/span[contains(text(),'Continue')]");
	}
	public By interaction_continuePage(String value){
		return By.xpath("//table[@class='jaa-clickto-continue-container']//span[text()='"+value+"']");
	}

	public By interaction_ThankYouPage(){
		return By.xpath("//div[@class='jaa-end-page']");
	}

	//S2T21
	public By interaction_breadCrumb(String page){
		if (page.equalsIgnoreCase("Home"))
			   return By.xpath("//div[contains(@class,'jaa-breadcrumb-home')]");
			  
			  else
			   return By.xpath("//div[contains(@class,'jaa-breadcrumb-element')][contains(text(),'"+page+"')]");
	}
	
	//S4T1
	public By interaction_btnRadio(String button){

		return By.xpath("//div[contains(@class,'jaa-radio')]//label[contains(text(),'"+button+"')]");
	}
	public By interaction_logoJacada(){
		return By.xpath("//div[@class='jaa-logo']");
		//return By.xpath("//div[@class='jaa-page-header-left']/div[contains(@class,'jaa-logo')]");
	}
	public By interaction_questionLabel(String questionLabel){
		return By.xpath("//p[@class='jaa-section-label']/span[contains(text(),'"+questionLabel+"')]");
	}
}
