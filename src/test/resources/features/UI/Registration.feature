@UI @UserRegistration @NegativeFlow
Feature: User Registration UI

  Background:
    Given user is on the Home page
    And user navigates to the Add User page

  Scenario Outline: Failed Registration of a New User using invalid data
    When user submits registration form with invalid data
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then user is not registered
    And an error message is displayed on the screen
    Examples:
      | firstName | lastName | email            | password |
      | <empty>   | Stone    | astone@yahoo.com | test123  |
      | Rob       | <empty>  | rken@mail.com    | test123  |
      | Rob       | Ken      | rkenmail.com     | test123  |
      | Ann       | Stone    | astone@gmail.com | 123      |
      | <empty>   | <empty>  | <empty>          | <empty>  |


  Scenario Outline: Failed Registration of a New User using already registered user data
    When user submits registration form with already registered user data
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then user is not registered
    And the 'Email address is already in use' error message is displayed on the screen
    Examples:
      | firstName | lastName | email            | password         |
      | Any       | Key      | any.key@mail.com | any.key@mail.com |