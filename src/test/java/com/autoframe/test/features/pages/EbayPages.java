package com.autoframe.test.features.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EbayPages {

	WebDriver driver;
	private String baseUrl;
	WebElement element;

	By sportsPage = By.xpath("//*[@id='mainContent']//li/a[text()='Sports']");
	By fitnessTechPage = By.xpath("//*[@id='mainContent']//div[text()='Fitness Technology']");
	By firstItem = By.xpath("//*[@id='w8-items[0]']/div/div[1]/div/a/div/img");
	By secondItem = By.xpath("//*[@id='w8-items[1]']/div/div[1]/div/a/div/img");
	By addToCartButton = By.xpath("//*[@id='atcRedesignId_btn']");
	By popupDialogButton = By.xpath("//*[@id='atcRedesignId_overlay-atc-container']/div/div[4]/div[1]/div/div");
	By backToFitnessTechPage = By.xpath("//*[@id='vi-VR-brumb-lnkLst']/table/tbody/tr[1]/td/ul/li[5]/a");
	By shoppingCartButton = By.xpath("//*[@id='gh-cart-1']");
	By checkoutButton = By.xpath("//*[@id='mainContent']//button[text()='Go to checkout']");
	By checkoutAsGuest = By.xpath("//*[@id='gtChk']");
	By checkoutItemCount = By.xpath("//*[@id='cart-details']/table/tbody/tr[2]/td[1]");

	public WebElement waitForElement(By locator, int timeout) {
		WebElement element = null;
		try {
			System.out.println("Waiting for max:: " + timeout + " seconds for element to be available");

			WebDriverWait wait = new WebDriverWait(driver, timeout);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			System.out.println("Element appeared on the web page");
		} catch (Exception e) {
			System.out.println("Element not appeared on the web page");
		}
		return element;
	}

	public void ClickOnElement(By locator) {
		WebElement element = waitForElement(locator, 40);
		if (element != null) {
			element.click();
		}
	}

	public EbayPages(WebDriver mdriver) {
		driver = mdriver;
		PageFactory.initElements(mdriver, this);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void OpenEbayHomePage() {
		baseUrl = "https://www.ebay.com.au/";
		driver.get(baseUrl);
	}

	public void OpenEbaySportsPage() {
		ClickOnElement(sportsPage);
		ClickOnElement(fitnessTechPage);
	}

	public void AddItemToCart(By item) {
		ClickOnElement(item);
		ClickOnElement(addToCartButton);
		ClickOnElement(popupDialogButton);
	}

	public void SelectAndAddTwoSportsItems() {
		AddItemToCart(firstItem);
		ClickOnElement(backToFitnessTechPage);
		AddItemToCart(secondItem);
	}

	public void NavigateToCheckoutPage() {
		ClickOnElement(shoppingCartButton);
		ClickOnElement(checkoutButton);
		ClickOnElement(checkoutAsGuest);
	}

	public int GetCheckoutItemCount() {
		WebElement element = waitForElement(checkoutItemCount, 10);
		String countDetails;
		int itemCount = 0;
		if (element != null) {
			countDetails = element.getText();
			System.out.println("Item Count String = " + countDetails);
			String sub = countDetails.substring(7, countDetails.length() - 1);
			itemCount = Integer.parseInt(sub);
			System.out.println("Item count = " + itemCount);
		}
		return itemCount;
	}

	public void CloseWebPage() {
		driver.close();
	}

}
