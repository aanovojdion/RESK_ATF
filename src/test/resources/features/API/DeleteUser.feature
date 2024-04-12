@API
Feature: Delete user via API

  Scenario: Successful deletion of a user
    Given user with valid data is already created
      | firstName | Kenny          |
      | lastName  | Li             |
      | email     | knlli@mail.com |
      | password  | kenny.lili     |
    When a request to delete user is sent
    Then the user has successfully deleted