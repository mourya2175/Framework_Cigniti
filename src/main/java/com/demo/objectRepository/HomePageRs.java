package com.demo.objectRepository;

import org.openqa.selenium.By;

public class HomePageRs {

	//FlipKart


	public static By logInLink = By.linkText("Login");
	
	/*public By headerLinks(String linkNumber)
	{
		return By.xpath("//div[@class='signin']/a[2]")
	}*/
	
	public static By headerLinks(String linkText)
	{
		return By.xpath("//a[contains(text(),'"+linkText+"')]");
				
		
	}
	
	
	
	
}
