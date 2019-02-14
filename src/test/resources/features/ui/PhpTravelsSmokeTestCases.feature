Feature: Book flight in PHP travels

  Scenario: Booking of flight tickets in PHP travels
    Given User is on PHP travels Home Page
    When User navigates to Flight Booking Page
    And Searches for the flight
    Then User select the flight and enter personal details
    And Confirms the flight booking
