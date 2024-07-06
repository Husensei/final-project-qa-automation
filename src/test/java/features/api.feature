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
  Scenario: GET list created users
    Given endpoint for "GET_LIST_CREATED_USERS"
    When send GET request
    Then validation response status code is equal to 200
    And validation response body list created users
    And validation response JSON with JSONSchema "get_list_created_users.json"

  @user
  Scenario: GET data specific valid user
    Given endpoint for "CREATE_NEW_USER"
    When send POST request
    Then validation response status code is equal to 200
    And validation response body create new user
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
  Scenario: POST create new user
    Given endpoint for "CREATE_NEW_USER"
    When send POST request
    Then validation response status code is equal to 200
    And validation response body create new user
    And validation response JSON with JSONSchema "post_create_new_user.json"

  @user
  Scenario: POST create new user with existing email
    Given endpoint for "CREATE_NEW_USER"
    And retrieve existing email
    When send POST request with existing email
    Then validation response status code is equal to 400
    And validation response body create new user with existing email
    And validation response JSON with JSONSchema "post_create_new_user_existing_email.json"

  @user
  Scenario: POST create new user with blank required field
    Given endpoint for "CREATE_NEW_USER"
    When send POST request with blank field
    Then validation response status code is equal to 400
    And validation response body create new user with blank field
    And validation response JSON with JSONSchema "post_create_new_user_blank_field.json"

  @user
  Scenario: PUT update user data
    Given endpoint for "CREATE_NEW_USER"
    When send POST request
    Then validation response status code is equal to 200
    And validation response body create new user
    Given endpoint for "UPDATE_USER"
    And retrieve "valid" user ID
    When send PUT request
    Then validation response status code is equal to 200
    And validation response body update user
    And validation response JSON with JSONSchema "put_update_user.json"

  @user
  Scenario: DELETE user data valid ID
    Given endpoint for "CREATE_NEW_USER"
    When send POST request
    Then validation response status code is equal to 200
    And validation response body create new user
    Given endpoint for "DELETE_USER"
    And retrieve "valid" user ID
    When send DELETE request
    Then validation response status code is equal to 200
    And validation response body delete user data

  @user
  Scenario: DELETE user data random ID
    Given endpoint for "DELETE_USER"
    And retrieve "random" user ID
    When send DELETE request
    Then validation response status code is equal to 404
    And validation error message "RESOURCE_NOT_FOUND"
    And validation response JSON with JSONSchema "error_message_invalid_user.json"

  @user
  Scenario: DELETE user data invalid ID
    Given endpoint for "DELETE_USER"
    And retrieve "invalid" user ID
    When send DELETE request
    Then validation response status code is equal to 400
    And validation error message "PARAMS_NOT_VALID"
    And validation response JSON with JSONSchema "error_message_invalid_user.json"

  @tags
  Scenario: GET list all tags
    Given endpoint for "GET_LIST_TAGS"
    When send GET request
    Then validation response status code is equal to 200
    And validation response body get list tags