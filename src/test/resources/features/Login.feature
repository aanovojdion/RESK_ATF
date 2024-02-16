Feature: LogIn Functionality

  Background:
    Given user is on the Home page

  @LogIn @PositiveFlow
  Scenario Outline: An already registered user has successfully logged in
    When user logins with valid '<email>' and '<password>'
    Then user is successfully redirected to the Contact List page
    Examples:
      | email            | password         |
#      | john.stone@mail.com | test123          |
      | any.key@mail.com | any.key@mail.com |

  @LogIn @NegativeFlow
  Scenario Outline: An user cannot login using invalid credentials
    When user populates only one required field form login form with valid data
      | Email   | Password   |
      | <email> | <password> |
    And user clicks on Submit button
    Then user is not logged in
    And the <warning message> is displayed
    Examples:
      | email               | password | warning message                |
      | john.stone@mail.com |          | Incorrect username or password |
      |                     | test123  | Incorrect username or password |
      |                     |          | Incorrect username or password |