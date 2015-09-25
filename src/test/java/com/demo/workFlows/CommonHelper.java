package com.demo.workFlows;

import com.demo.accelerators.ActionEngine;
import com.demo.objectRepository.*;

public class CommonHelper extends ActionEngine {
	String firstProductLinkText ;

	public void login() throws Throwable
	{
		
		click(HomePage.logInLink, "Login Link");
		waitForVisibilityOfElement(HomePage.emailOrMobileTextBox, "UserName");
		Thread.sleep(500);
		type(HomePage.emailOrMobileTextBox, configProps.getProperty("UserId"), "user id text box");
		type(HomePage.passwordTextBox, configProps.getProperty("Password"), "password text box");
		click(HomePage.loginButton, "Login button");
		waitForVisibilityOfElement(SearchResultsPage.greetingMenu, "Greeting Menu");
	}

	public void searchProduct(String product) throws Throwable
	{
		type(HomePage.searchBar, product, "Search Text Box");
		click(HomePage.searchButton, "Search button");
		firstProductLinkText = getText(SearchResultsPage.firstProductLink, "First product link");
		click(SearchResultsPage.firstProductLink, "First product link");
	}

	public void addtoCart() throws Throwable
	{
		waitForVisibilityOfElement(SearchResultsPage.addToCartButton, "Add To Cart Button");
		mouseoverAndClick(SearchResultsPage.addToCartButton, "Add To Cart Button");
		waitForVisibilityOfElement(SearchResultsPage.cartToolTipTitle, "Cart tooltip title");
		//assertText(SearchResultsPage.cartToolTipTitle, firstProductLinkText);		
		click(SearchResultsPage.cartButton, "Cart Button");
	}

	public void removefromCart() throws Throwable
	{
		waitForVisibilityOfElement(SearchResultsPage.removeLink, "Remove Link");
		click(SearchResultsPage.removeLink, "Remove Link");
		waitForVisibilityOfElement(SearchResultsPage.btnContinueShop, "Continue Shopping Button");	

	}
	
	public void logout() throws Throwable
	{
		mouseover(SearchResultsPage.greetingMenu, "Greeting Menu");
		click(SearchResultsPage.logOut, "Logout Button");	
	}
	
	public void trackOrder() throws Throwable
	{
		
		waitForVisibilityOfElement(SearchResultsPage.trackOrderLink, "TrackOrder");
		click(SearchResultsPage.trackOrderLink, "track order link");
		waitForVisibilityOfElement(SearchResultsPage.recentOrdersOopsText, "TrackOrder Message");
		assertText(SearchResultsPage.recentOrdersOopsText, configProps.getProperty("recentOrdersOopsText"));	
	}
	
	public void gift() throws Throwable
	{
		
		driver.get("http://www.flipkart.com/buy-gift-voucher?otracker=hp_ch_vn_gift-voucher");
		waitForVisibilityOfElement(SearchResultsPage.email, "Email");
		type(SearchResultsPage.email, "dnr7dnr@gmail.com", "email");
		type(SearchResultsPage.reEmail, "dnr7dnr@gmail.com", "email");
		click(SearchResultsPage.buyGiftr, "Gift card buy");
		Thread.sleep(3000);
		
	}
	
}


