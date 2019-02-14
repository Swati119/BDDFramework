package com.autoframe.test.features.stepdefinition;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import com.autoframe.test.features.steps.WeatherApiSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class WeatherApiStepDefinition {

	WeatherApiSteps weatherApiSteps = new WeatherApiSteps();
	JSONObject response;
	String cityName;
	String weekDay;
	List<Double> tempList;

	@Given("^I like to holiday in (.*)$")
	public void i_like_to_holiday_in_city(String city) {
		cityName = city;
	}

	@Given("^I only like to holiday on (.*)$")
	public void i_only_like_to_holiday_on_weekday(String day) {
		weekDay = day;
	}

	@When("^I look up the weather forecast$")
	public void i_look_up_the_weather_forecast() throws ParseException {
		// The API for 16 days forecast is only available for the paid account
		// so using the API of 5 days/3 hourly forecast
		// Get the weather forecast for next 5 days 3 hourly
		response = weatherApiSteps.GetWeatherForecast(cityName);

		// Verify the city name in response
		if (response != null) {
			Assert.assertEquals(cityName, (String) ((JSONObject) response.get("city")).get("name"));
		}
	}

	@Then("^I receive the weather forecast$")
	public void i_receive_the_weather_forecast() {
		if (response != null) {
			// List of temperature in Degree Celsius every 3 hourly for given weekDay
			tempList = weatherApiSteps.GetWeatherForWeekOfDay(response, weekDay);
		}
	}

	@Then("^the temperature is warmer than (.*) degrees$")
	public void the_temperature_is_warmer_than_degrees(String temperature) {
		boolean requiredTempFlag = true;

		// Check for required temperature in all the 3 hourly data points
		for (Double temp : tempList) {
			if (temp <= Double.parseDouble(temperature)) {
				requiredTempFlag = false;
				break;
			}
		}
		Assert.assertTrue(requiredTempFlag);
	}

}
