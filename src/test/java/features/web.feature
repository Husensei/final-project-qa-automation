@web
Feature: Demoblaze Web Automation Test

  @contact
  Scenario: Send message using Contact Menu
    Given user is on the "Home Page"
    And user clicks "Contact Menu" button
    When user input contact email
    And user input contact name
    And user input message
    And user clicks "Send Message" button
    Then website shows an alert with message "Thanks for the message!!"

  @about
  Scenario: Video popup in About Us Menu
    Given user is on the "Home Page"
    When user clicks "About Us Menu" button
    And user clicks video player
    Then video played successfully

  @login
  Scenario: Successful user log in
    Given user is on the "Home Page"
    And user clicks "Log In Menu" button
    When user input username "admin" and password "admin"
    And user clicks "Log In Submit" button
    Then verify log in successful with message "Welcome admin"

  @login
  Scenario: Unsuccessful user log in invalid username
    Given user is on the "Home Page"
    And user clicks "Log In Menu" button
    When user input invalid username and password
    And user clicks "Log In Submit" button
    Then website shows an alert with message "User does not exist."

  @login
  Scenario: Unsuccessful user log in wrong password
    Given user is on the "Home Page"
    And user clicks "Log In Menu" button
    When user input username "admin" and password "password"
    And user clicks "Log In Submit" button
    Then website shows an alert with message "Wrong password."

  @login
  Scenario: Unsuccessful user log in blank field
    Given user is on the "Home Page"
    And user clicks "Log In Menu" button
    When user input username "" and password ""
    And user clicks "Log In Submit" button
    Then website shows an alert with message "Please fill out Username and Password."

  @login
  Scenario: Successful user log out
    Given user is on the "Home Page"
    And user has been logged in
    When user clicks "Log Out Menu" button
    Then verify log out successful with message "Sign up"

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

  @cart
  Scenario: Add to cart functionality
    Given user is on the "Home Page"
    And user clicks product "Samsung galaxy s6"
    Then verify product detail page displayed properly
    When user clicks "Add to Cart" button
    Then website shows an alert with message "Product added"

  @cart
  Scenario: Order product functionality
    Given user is on the "Home Page"
    And user clicks product "Samsung galaxy s6"
    Then verify product detail page displayed properly
    When user clicks "Add to Cart" button
    Then website shows an alert with message "Product added"
    When user clicks "Home Menu" button
    And user clicks product "Sony vaio i5"
    Then verify product detail page displayed properly
    When user clicks "Add to Cart" button
    Then website shows an alert with message "Product added"
    When user clicks "Cart Menu" button
    Then verify ordered product details
    When user clicks "Place Order" button
    And user input form data name "Test Name" country "Test Country" city "Test City" card "12345" month "7" year "2024"
    When user clicks "Purchase" button
    Then verify product ordered successfully

