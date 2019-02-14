package com.autoframe.test.features.steps;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.junit.Assert;

public class WeatherApiSteps {

	public JSONObject GetWeatherForecast(String cityName) throws ParseException {
		String apiKey = "fcedf0de60f56a8ecb97a469cdaad1d4";
		String baseURL = "http://api.openweathermap.org/";
		// Using 5 day weather forecast as 16 day weather forecast is available for paid
		// accounts only
		String resource = "data/2.5/forecast/";
		String tempUnitFormat = "metric"; // Degree Celsius

		Client client = Client.create();

		String url = baseURL + resource + "?q=" + cityName + "&units=" + tempUnitFormat + "&appid=" + apiKey;
		System.out.println("\nURL is " + url);
		WebResource webResource = client.resource(url);

		ClientResponse response = webResource.get(ClientResponse.class);
		System.out.println("\nThe status code is " + response.getStatus());
		Assert.assertEquals(200, response.getStatus());

		String body = webResource.type(MediaType.APPLICATION_JSON).get(String.class);
		Assert.assertNotNull(body);
		System.out.println("\nJson body is" + body);

		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) jsonParse.parse(body);
		} catch (ParseException e) {
			System.out.println("\nInvalid Json content");
			e.printStackTrace();
		}
		return jsonObj;
	}

	public List<Double> GetWeatherForWeekOfDay(JSONObject jsonObj, String weekDay) {
		JSONArray jsonArr = (JSONArray) jsonObj.get("list");
		List<Double> tempList = new ArrayList<Double>();

		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jsonNewObj = (JSONObject) jsonArr.get(i);
			String date = (String) jsonNewObj.get("dt_txt");
			String weekDayInResponse = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
					.getDayOfWeek().name();
			Double temp = 0.0;
			if (weekDay.equalsIgnoreCase(weekDayInResponse)) {
				temp = (Double) ((JSONObject) jsonNewObj.get("main")).get("temp");
				tempList.add(temp);
			}
		}
		return tempList;
	}
}
