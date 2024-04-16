@API @UserCreation
Feature: Successful creation of a new user

  Scenario: Successful creation of a new user
    Given user is using valid data
      | firstName | Kenny          |
      | lastName  | Li             |
      | email     | knlli@mail.com |
      | password  | kenny.lili     |
    When a request to create a new user is sent
    Then the user has successfully created