Feature: Ebay online shopping

  Scenario: Test adding two items to the cart/trolley and go to checkout
			Given User is on Ebay Home Page
			When User Navigates to Sport items Page
			And Adds two items to cart/trolley
			And Goes to checkout 
			Then User can see two items on checkout page

