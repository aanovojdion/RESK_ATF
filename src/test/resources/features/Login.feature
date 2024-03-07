Feature: LogIn

  Background:
    Given user is on the Home page

  @LogIn @PositiveFlow
  Scenario Outline: An already registered user has successfully logged in
    When user logins with valid '<email>' and '<password>'
    Then user is successfully redirected to the Contact List page
    Examples:
      | email            | password         |
      | any.key@mail.com | any.key@mail.com |

  @LogIn @NegativeFlow
  Scenario Outline: An user cannot login using invalid credentials
    When user trying to login with invalid data
      | Email   | Password   |
      | <email> | <password> |
    Then user is not logged in
    And the 'Incorrect username or password' error message is displayed
    Examples:
      | email               | password |
      | jon.stone.mail.com  | 123      |
      | john.stone@mail.com | <empty>  |
      | <empty>             | test123  |
      | <empty>             | <empty>  |