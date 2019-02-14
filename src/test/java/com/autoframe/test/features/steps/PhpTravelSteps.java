package com.autoframe.test.features.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.autoframe.test.features.pages.PhpTravelPages;
import net.thucydides.core.annotations.Step;

public class PhpTravelSteps {

	private WebDriver driver;
	PhpTravelPages phpTravelPages;

	@Step
	public void NavigatePhpTravelHomePage() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
		driver = new ChromeDriver();
		phpTravelPages = new PhpTravelPages(driver);
		phpTravelPages.OpenPhpTravelHomePage();
	}

	@Step
	public void NavigateFlighBookingPage() {
		phpTravelPages.OpenFlighBookingPage();
	}

	@Step
	public void SearchFlights() {
		phpTravelPages.EnterFlightDetailsAndSearch();
	}

	@Step
	public void SelectFlightAndEnterPassengerDetails() {
		phpTravelPages.SelectFlight();
		phpTravelPages.EnterPassengerDetails();
	}

	@Step
	public void ConfirmFlightBooking() {
		phpTravelPages.ConfirmBooking();
		phpTravelPages.CloseWebPage();
	}

}
