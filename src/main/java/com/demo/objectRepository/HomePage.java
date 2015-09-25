package com.demo.objectRepository;

import org.openqa.selenium.By;

public class HomePage {

	//FlipKart


	public static By logInLink = By.linkText("Login");
	public static By emailOrMobileTextBox = By.xpath("//div[@class='login-input-wrap']/input[contains(@class, 'user-email')]");
	public static By passwordTextBox=By.xpath("//input[contains(@class, 'user-pwd')]");
	public static By loginButton= By.xpath("//input[contains(@class,'login-btn')]");
	public static By searchBar=By.id("fk-top-search-box");
	public static By searchButton= By.xpath("//input[contains(@class,'search-bar-submit')]");
	public static By greetingMenu = By.xpath("//li[@class='no-border greeting-link']/a");
	public static By btnContinueShop = By.xpath("//button[@class='btn btn-blue close continue-button continue-empty-btn tmargin10']");
	
	
}
