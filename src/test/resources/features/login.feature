Feature: Login Functionality

  Scenario: Test login with empty credentials
    Given I am on the login page
    When I enter empty credentials
    Then I should see an error message "Epic sadface: Username is required"

  Scenario: Test login passing only username
    Given I am on the login page
    When I enter a valid username and leave the password field empty
    Then I should see an error message "Epic sadface: Password is required"

  Scenario: Test login passing valid credentials
    Given I am on the login page
    When I enter valid credentials
    Then I should be redirected to the dashboard
    And I should see the dashboard title "Swag Labs"
    And I should see the page title "Swag Labs"
    And the shop content should be displayed
