package com.demo.objectRepository;

import org.openqa.selenium.By;

public interface CommonObjects {
	// FlipKart
	public By searchBar();
	public By searchIcon();
	public By mainCategory(String MainCategory);
	public By subCategory(String MainCategory,String subCategory);
	
	//
	public By interaction_lnkName(String interactionName);

	public By interaction_lnkSelectLanguage(String language);

	public By interaction_lblPageTitle();

	public By interaction_lblPageTitle(String title);

	public By interaction_lblPageHeader();

	public By interaction_Poc1_lblQuestion();

	public By interaction_Poc1_txtTextArea();

	public By interaction_lblPageFooter();

	public By interaction_txtBreadcrumbName(String breadcrumbName);

	public By interaction_lblcontactUs(String contactUsLabel);

	public By interaction_lblHelp(String helpLabel);

	public By interaction_lblHelpTooltipMsg(String helpMsg);

	//POC2	

	public By interaction_txtInputValue();	

	//POC3

	public By interaction_Poc3_lblErrorHeader(String ErrorHeader);

	public By interaction_Poc3_txtErrorMsg(String ErrorMsg);

	public By interaction_btnOk(String ErrorMessage);

	//POC4

	public By interaction_lblAnswerHeader();

	public By interaction_txtAnswer();

	//S1T1
	public By interaction_btnNextStatus(String status);

	public By interaction_btnBackStatus(String status);

	public By interaction_btnDoneStatus(String status);

	public By interaction_btnBack();	

	public By interaction_btnNext();

	public By interaction_btnDone();

	//S1T3
	public By interaction_clickChoice(int choice);

	public By interaction_clickCategeory(int choice);

	public By interaction_clickCategeoryChoice(String choice);

	//S1T5
	public By interaction_lblPageHeader(String title);
	public By interaction_lblPageFooter(String title);

	//S1T7
	public By interaction_lblInnerPageTitle(String Title);
	public By interaction_lblInnerPageHeader(String title);

	public By interaction_btnInnerBack();

	//S1T9
	public By interaction_ContactUsButtons(String Button);

	public By interaction_PopUpPageTitle(String Title);

	public By interaction_btnChatclose();

	//S1T11
	//S1T13
	public By interaction_userName();

	public By interaction_password();

	public By interaction_submit();


	//S1T15
	public By interaction_verifyOrderCollectionText(String attribute,String value);
	//S1T17
	public By interaction_btnClose(String ErrorMessage);
	
	//S1T18

	//S2T1
	public By interaction_btn(String button);

	public By interaction_btnNext_old();

	public By interaction_btnBack_old();

	//S2T6
	public By interaction_continuePageHeader();

	public By interaction_continuePage(String value);	 

	public By interaction_ThankYouPage();
	
    //S2T21
	public  By interaction_breadCrumb(String page);
	
	//S4T1
	public By interaction_btnRadio(String button);
	public By interaction_logoJacada();
	public By interaction_questionLabel(String questionLabel);
}
