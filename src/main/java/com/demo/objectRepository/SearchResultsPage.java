package com.demo.objectRepository;

import org.openqa.selenium.By;

public class SearchResultsPage {

	//FlipKart
	
	public static By firstProductLink=By.xpath("//div[@class='gd-row browse-grid-row']/div[1]//a");
	
	public static By addToCartButton = By.xpath("//input[@value='Add to Cart']");
	
	public static By cartToolTipTitle = By.xpath("//div[@class='fk-ui-tooltip-content']/span[@class='title']/strong");
	
	public static By greetingMenu =By.xpath("//li[@class='no-border greeting-link']/a"); 
	
	public static By logOut =By.xpath("//a[contains(text(),'Logout')]");
	
	public static By cartButton = By.xpath("//a[contains(@class,'btn-cart')]");
	public static By removeLink= By.xpath("//a[contains(@class,'cart-remove-item')]");
	public static By trackOrderLink = By.xpath("//span[@class='header-track-icon']");
	public static By recentOrdersOopsText =By.xpath("//div[@id='recent-orders-tab']/p");
	public static By btnContinueShop = By.xpath("//button[@class='btn btn-blue close continue-button continue-empty-btn tmargin10']");
	public static By lnkGiftCard = By.xpath("//span[contians(text(),'Gift Card')]");
	public static By email = By.name("recipient-email[]");
	public static By reEmail = By.id("confirm-recipient-email1");
	public static By buyGiftr = By.xpath("//button[@type='submit']");
	
	
	
	
	
}
