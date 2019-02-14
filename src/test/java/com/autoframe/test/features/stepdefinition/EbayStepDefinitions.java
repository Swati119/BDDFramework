package com.autoframe.test.features.stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.autoframe.test.features.steps.EbaySteps;

public class EbayStepDefinitions {

	public EbaySteps steps = new EbaySteps();

	@Given("^User is on Ebay Home Page$")
	public void user_is_on_Ebay_Home_Page() {
		steps.NavigateEbayHomePage();
	}

	@When("^User Navigates to Sport items Page$")
	public void user_Navigates_to_Sport_items_Page() {
		steps.NavigateEbaySportsPage();
	}

	@When("^Adds two items to cart/trolley$")
	public void adds_two_items_to_cart_trolley() {
		steps.AddTwoSportsItems();
	}

	@When("^Goes to checkout$")
	public void goes_to_checkout() {
		steps.CheckoutTwoItems();
	}

	@Then("^User can see two items on checkout page$")
	public void user_can_see_two_items_on_checkout_page() {
		steps.ValidateCheckoutPage();
	}
}
