@api
Feature: REST API Automation Test for Dummy API

  @users
  Scenario: GET list all users
    Given endpoint for "GET_LIST_USERS"
    When send GET request
    Then validation response status code is equal to 200
    And validation response body list all users
    And validation response JSON with JSONSchema "get_list_all_users.json"