@UI @LogIn
Feature: Login functionality

  Background:
    Given user is on the Home page

  Scenario: An already registered user has successfully logged in
    When user logins with valid 'any.key@mail.com' and '123NewPassword'
    Then user has successfully logged in

  @Negative
  Scenario Outline: An user cannot login using invalid credentials
    When user trying to login with invalid data
      | Email   | Password   |
      | <email> | <password> |
    Then user has not logged in
    And the 'Incorrect username or password' error message is displayed
    Examples:
      | email               | password |
      | jon.stone.mail.com  | 123      |
      | john.stone@mail.com | <empty>  |
      | <empty>             | test123  |
      | <empty>             | <empty>  |