package com.autoframe.test.features.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.autoframe.test.features.pages.EbayPages;
import org.junit.Assert;
import net.thucydides.core.annotations.Step;

public class EbaySteps {

	private WebDriver driver;
	EbayPages ebayPages;

	@Step
	public void NavigateEbayHomePage() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
		driver = new ChromeDriver();
		ebayPages = new EbayPages(driver);
		ebayPages.OpenEbayHomePage();
	}

	@Step
	public void NavigateEbaySportsPage() {
		ebayPages.OpenEbaySportsPage();
	}

	@Step
	public void AddTwoSportsItems() {
		ebayPages.SelectAndAddTwoSportsItems();
	}

	@Step
	public void CheckoutTwoItems() {
		ebayPages.NavigateToCheckoutPage();
	}

	@Step
	public void ValidateCheckoutPage() {
		Assert.assertEquals("Checkout", driver.getTitle());
		Assert.assertEquals(2, ebayPages.GetCheckoutItemCount());
		ebayPages.CloseWebPage();
	}
}
