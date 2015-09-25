package com.demo.testScripts.smoke;


import org.testng.annotations.Test;

import com.demo.objectRepository.HomePageRs;
import com.demo.support.ExcelReader;
import com.demo.workFlows.CommonHelper;


public class DemoScript3 extends CommonHelper{

	ExcelReader reader = new ExcelReader("D://Projects_GitHub//FlipKartProject//Resources//TestData//Data.xlsx", "Sheet1");

	@Test
	public void verifyHeaderLink() throws Throwable{
		launchUrl("http://www.rightstart.com/");
		click(HomePageRs.headerLinks("Sign In"), "Sign In link");
		driver.navigate().back();
		click(HomePageRs.headerLinks("Sign In"), "Sign In link");
		/*isElementPresent(HomePageRs.headerLinks("Sign In"), "Sign In link");
		isElementPresent(HomePageRs.headerLinks("Email Signup"), "Email Signup");*/
		

	}

}
