package com.autoframe.test.features.stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.autoframe.test.features.steps.PhpTravelSteps;

public class PhpTravelStepDefinitions {

	public PhpTravelSteps steps = new PhpTravelSteps();

	@Given("^User is on PHP travels Home Page$")
	public void user_is_on_PHP_travels_Home_Page() {
		steps.NavigatePhpTravelHomePage();
	}

	@When("^User navigates to Flight Booking Page$")
	public void user_navigates_to_Flight_Booking_Page() {
		steps.NavigateFlighBookingPage();
	}

	@When("^Searches for the flight$")
	public void searches_for_the_flight() {
		steps.SearchFlights();
	}

	@Then("^User select the flight and enter personal details$")
	public void user_select_the_flight_and_enter_personal_details() {
		steps.SelectFlightAndEnterPassengerDetails();
	}

	@Then("^Confirms the flight booking$")
	public void confirms_the_flight_booking() {
		steps.ConfirmFlightBooking();
	}

}
