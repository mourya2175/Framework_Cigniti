package com.demo.testScripts.smoke;


import org.testng.annotations.Test;

import com.demo.support.ExcelReader;
import com.demo.workFlows.CommonHelper;


public class DemoScript1 extends CommonHelper{

	ExcelReader reader = new ExcelReader("D://Projects_GitHub//FlipKartProject//Resources//TestData//Data.xlsx", "Sheet1");

	@Test
	public void addToCart() throws Throwable{
		launchUrl(configProps.getProperty("url"));
		searchProduct(reader.getCellData("Sheet1", "Product", 2));
		addtoCart();
		removefromCart();

	}

}
