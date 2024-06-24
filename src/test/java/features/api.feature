@api
Feature: REST API Automation Test for Dummy API

  @user
  Scenario: GET list all users
    Given endpoint for "GET_LIST_USERS"
    When send GET request
    Then validation response status code is equal to 200
    And validation response body list all users
    And validation response JSON with JSONSchema "get_list_all_users.json"

  @user
  Scenario: GET list all users with pagination
    Given endpoint for "GET_LIST_USERS"
    And add "Pagination" query params with value 2
    When send GET request
    Then validation response status code is equal to 200
    And validation response body list all users
    And validation response JSON with JSONSchema "get_list_all_users.json"

  @user
  Scenario: GET list all users with limit
    Given endpoint for "GET_LIST_USERS"
    And add "Limit" query params with value 20
    When send GET request
    Then validation response status code is equal to 200
    And validation response body list all users
    And validation response JSON with JSONSchema "get_list_all_users.json"

  @user
  Scenario: GET data specific valid user
    Given endpoint for "GET_SPECIFIC_USER"
    And retrieve "valid" user ID
    When send GET request
    Then validation response status code is equal to 200
    And validation response body data specific users
    And validation response JSON with JSONSchema "get_specific_valid_user.json"

  @user
  Scenario: GET data specific invalid user random ID
    Given endpoint for "GET_SPECIFIC_USER"
    And retrieve "random" user ID
    When send GET request
    Then validation response status code is equal to 404
    And validation error message "RESOURCE_NOT_FOUND"
    And validation response JSON with JSONSchema "error_message_invalid_user.json"

  @user
  Scenario: GET data specific invalid user invalid ID
    Given endpoint for "GET_SPECIFIC_USER"
    And retrieve "invalid" user ID
    When send GET request
    Then validation response status code is equal to 400
    And validation error message "PARAMS_NOT_VALID"
    And validation response JSON with JSONSchema "error_message_invalid_user.json"

  @user
  Scenario: GET list created users
    Given endpoint for "GET_LIST_CREATED_USERS"
    When send GET request
    Then validation response status code is equal to 200
    And validation response body list created users
    And validation response JSON with JSONSchema "get_list_created_users.json"