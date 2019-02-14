package com.autoframe.test.features.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhpTravelPages {

	WebDriver driver;
	private String baseUrl;
	WebElement element;

	By flighBookingPage = By.xpath("//a[@title='Flights']");
	By sourceCityField = By.xpath("//*[@id='s2id_location_from']/a/span[1]");
	By destCityField = By.xpath("//*[@id='s2id_location_to']/a/span[1]");
	By cityListElements = By.xpath("//*[@id='select2-drop']/ul");
	By deptDateField = By.xpath("//input[@name='departure']");
	By deptDatePicker = By.xpath("/html/body/div[14]/div[1]");
	By activeDeptDatesElements = By.xpath("/html/body/div[14]/div[1]//td[@class='day ']");
	By returnDateField = By.xpath("//input[@name='arrival']");
	By returnDatePicker = By.xpath("/html/body/div[15]/div[1]");
	By activeReturnDatesElements = By.xpath("/html/body/div[15]/div[1]//td[@class='day ']");
	By numOfPassengersField = By.xpath("//input[@name='totalManualPassenger']");
	By searchButton = By.xpath("//*[@id='flights']/form/div[6]/button");
	By flightBookButton = By.xpath("//*[@id='load_data']//tr[1]//button");
	By confirmButton = By.xpath("//button[text()='CONFIRM THIS BOOKING']");
	By payNowButton = By.xpath("//button[text()='Pay Now' and @type='button']");

	public WebElement waitForElement(By locator, int timeout) {
		WebElement element = null;
		try {
			System.out.println("Waiting for max:: " + timeout + " seconds for element to be available");
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			System.out.println("Element appeared on the web page");
		} catch (Exception e) {
			System.out.println("Element not appeared on the web page" + locator.toString());
		}
		return element;
	}

	public void ClickOnElement(By locator) {
		WebElement element = waitForElement(locator, 40);
		if (element != null) {
			element.click();
		}
	}

	public void SendKeyToElement(By locator, String text) {
		WebElement element = waitForElement(locator, 10);
		if (element != null) {
			element.clear();
			element.sendKeys(text);
		}
	}

	public PhpTravelPages(WebDriver mdriver) {
		driver = mdriver;
		PageFactory.initElements(mdriver, this);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void OpenPhpTravelHomePage() {
		baseUrl = "https://www.phptravels.net/";
		driver.get(baseUrl);
	}

	public void OpenFlighBookingPage() {
		ClickOnElement(flighBookingPage);
	}

	public void SelectCity(By cityLocator, String searchCity, String cityDetails) {
		ClickOnElement(cityLocator);

		SendKeyToElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"), searchCity);

		// Wait to pop up the list of cities for searched city
		String listXpath = "//*[@id='select2-drop']/ul//li/div[contains(text(),'" + cityDetails + "')]";
		waitForElement(By.xpath(listXpath), 20);

		WebElement element = waitForElement(cityListElements, 40);
		if (element != null) {
			List<WebElement> results = element.findElements(By.tagName("li"));

			for (WebElement result : results) {
				if (result.getText().contains(cityDetails)) {
					result.click();
					break;
				}
			}
		}
	}

	public void SelectRoundTrip() {

		/*
		 * Not able to locate the Radio button of Round Trip Solution: Get all the radio
		 * buttons, traverse and find the radio button
		 */
		List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));

		for (WebElement button : radioButtons) {
			System.out.println("Radio button value : " + button.getAttribute("value"));
			if (button.getAttribute("value").equals("round")) {
				/*
				 * Not able to click the Radio button of Round Trip Solution: Use Action class
				 * to move to the element and then click
				 */
				Actions actions = new Actions(driver);
				actions.moveToElement(button).click().perform();
			}
		}
	}

	public void SelectDeptDate(String deptDate) {
		boolean dateSelected = false;

		ClickOnElement(deptDateField);

		WebElement calDays = waitForElement(deptDatePicker, 40);
		if (calDays != null) {
			List<WebElement> allValidDates = calDays.findElements(activeDeptDatesElements);

			for (WebElement date : allValidDates) {
				if (date.getText().equals(deptDate)) {
					date.click();
					dateSelected = true;
					break;
				}
			}
		}

		if (!dateSelected) {
			System.out.println("\n Selected Date is Inactive");
		}

	}

	public void SelectReturnDate(String returnDate) {
		boolean dateSelected = false;

		WebElement element = waitForElement(returnDateField, 10);
		if (element != null) {
			element.clear();
		}

		WebElement calDays = waitForElement(returnDatePicker, 40);
		if (calDays != null) {
			List<WebElement> allValidDates = calDays.findElements(activeReturnDatesElements);

			for (WebElement date : allValidDates) {
				if (date.getText().equals(returnDate)) {
					date.click();
					dateSelected = true;
					break;
				}
			}
		}

		if (!dateSelected) {
			System.out.println("\n Selected Date is Inactive");
		}

	}

	private void SelectNumberOfPassengers(String noOfPassengers) {
		SendKeyToElement(numOfPassengersField, noOfPassengers);
	}

	public void EnterFlightDetailsAndSearch() {
		SelectRoundTrip();
		SelectCity(sourceCityField, "Sydney", " Kingsford Smith Arpt (SYD)");
		SelectCity(destCityField, "London", " City Arpt (LCY)");
		SelectDeptDate("25");
		SelectReturnDate("28");
		SelectNumberOfPassengers("2");
		timeDelay(5000);
		ClickOnElement(searchButton);
		timeDelay(20000);
	}

	public void SelectFlight() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(flightBookButton);
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
	}

	public void EnterPassengerDetails() {

		SendKeyToElement(By.xpath("//input[@name='firstname']"), "John");
		SendKeyToElement(By.xpath("//input[@name='lastname']"), "Smith");
		SendKeyToElement(By.xpath("//input[@name='email']"), "JohnSmith99@gmail.com");
		SendKeyToElement(By.xpath("//input[@name='confirmemail']"), "JohnSmith99@gmail.com");
		SendKeyToElement(By.xpath("//input[@name='phone']"), "0444555666");
		SendKeyToElement(By.xpath("//input[@name='address']"), "99 JohnSmith Street Sydney");

		ClickOnElement(By.xpath("//*[@id='s2id_autogen1']/a/span[1]"));
		ClickOnElement(By.xpath("//li//div[text()='India']"));

		SendKeyToElement(By.xpath("//input[@name='passenger[name]']"), "JackSmith");
		SendKeyToElement(By.xpath("//input[@name='passenger[age]']"), "40");
		SendKeyToElement(By.xpath("//input[@name='passenger[passportnumber]']"), "JS111111P");
		timeDelay(10000);

	}

	public void ConfirmBooking() {
		ClickOnElement(confirmButton);
		timeDelay(5000);
		ClickOnElement(payNowButton);
		timeDelay(5000);
	}

	public void timeDelay(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
		}
	}

	public void CloseWebPage() {
		driver.close();
	}

}
