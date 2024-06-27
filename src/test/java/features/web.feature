@web
Feature: Demoblaze Web Automation Test

  @signup
  Scenario: Successful user registration
    Given user is on the "Home Page"
    And user clicks "Sign Up Menu" button
    When user input valid username and password
    And user clicks "Sign Up Submit" button
    Then website shows an alert with message "Sign up successful."

  @signup
  Scenario: Unsuccessful user registration existing user
    Given user is on the "Home Page"
    And user clicks "Sign Up Menu" button
    When user input username "admin" and password "admin"
    And user clicks "Sign Up Submit" button
    Then website shows an alert with message "This user already exist."

  @signup
  Scenario: Unsuccessful user registration blank field
    Given user is on the "Home Page"
    And user clicks "Sign Up Menu" button
    When user input username "" and password ""
    And user clicks "Sign Up Submit" button
    Then website shows an alert with message "Please fill out Username and Password."

  @login
  Scenario: Successful user log in
    Given user is on the "Home Page"
    And user clicks "Log In Menu" button
    When user input username "admin" and password "admin"
    And user clicks "Log In Submit" button
    Then verify log in successful with message "Welcome admin"

  @login 
  Scenario: Successful user log out
    Given user is on the "Home Page"
    And user has been logged in
    When user clicks "Log Out Menu" button
    Then verify log out successful with message "Sign up"