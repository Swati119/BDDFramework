Feature: Get Weather Forecast

  Scenario Outline: A happy holidaymaker
    Given I like to holiday in <city>
    And I only like to holiday on <weekday>
    When I look up the weather forecast
    Then I receive the weather forecast
    And the temperature is warmer than <temp> degrees

    Examples: 
      | city      | weekday  | temp |
      | Sydney    | Thursday |   10 |
      | Melbourne | Friday   |    8 |
