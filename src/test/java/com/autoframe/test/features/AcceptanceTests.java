package com.autoframe.test.features;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features= {"src/test/resources/features/"},tags= {"not @ignore","not @manual","not @wip"})
//@CucumberOptions(features= {"src/test/resources/features/services/Weather.feature"},tags= {"not @ignore","not @manual","not @wip"})
//@CucumberOptions(features= {"src/test/resources/features/ui/EbaySmokeTestCases.feature"},tags= {"not @ignore","not @manual","not @wip"})
//@CucumberOptions(features= {"src/test/resources/features/ui/PhpTravelsSmokeTestCases.feature"},tags= {"not @ignore","not @manual","not @wip"})

public class AcceptanceTests {

	@BeforeClass
	public static void Init() {
		System.out.println("Do Initialisation");
	}
	
	@AfterClass
	public static void Cleanup() {
		System.out.println("Do Cleanup");
	}

}
