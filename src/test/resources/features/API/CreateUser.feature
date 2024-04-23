@API @UserCreationAPI
Feature: Successful creation of a new user

  Scenario: Successful creation of a new user via API
    Given user is using valid data
      | firstName | Kenny           |
      | lastName  | Lee             |
      | email     | knllee@mail.com |
      | password  | kenny.lili      |
    When a request to create a new user is sent to CREATE_USER endPoint
    Then the user has successfully created