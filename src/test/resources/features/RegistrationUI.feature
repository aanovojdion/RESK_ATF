Feature: User Registration UI

  Background:
    Given user is on the Home page
    And user navigates to the Add User page
#
#  @UserRegistration @LogIn @PositiveFlow
#  Scenario Outline: Successful Registration of a New User
#    When user submits registration form with <valid data>
#    Then the My Contact List page is dislayed
#    Examples:
#      | valid data |
#      | firstName  | lastName | email            | password |
#      | John       | Li       | jli@gmail.com    | test123  |

  @UserRegistration @NegativeFlow
  Scenario Outline: Failed Registration of a New User using invalid data
    When user submits registration form with invalid data
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then user is not registered
    And an error message is displayed on the screen
    Examples:
      | firstName | lastName | email            | password | error message                                                                                                                                                             |
      | <empty>   | Stone    | astone@yahoo.com | test123  | User validation failed: firstName: Path `firstName` is required.                                                                                                          |
      | Rob       | <empty>  | rken@mail.com    | test123  | User validation failed: lastName: Path `lastName` is required.                                                                                                            |
      | Rob       | Ken      | rkenmail.com     | test123  | User validation failed: email: Email is invalid                                                                                                                           |
      | Ann       | Stone    | astone@gmail.com | 123      | User validation failed: password: Path `password` (`123`) is shorter than the minimum allowed length (7).                                                                 |
      | <empty>   | <empty>  | <empty>          | <empty>  | User validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required., email: Email is invalid, password: Path `password` is required. |


  @UserRegistration @NegativeFlow
  Scenario Outline: Failed Registration of a New User using already registered user data
    When user submits registration form with already registered user data
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then user is not registered
    And the 'Email address is already in use' error message is displayed on the screen
    Examples:
      | firstName | lastName | email            | password         |
      | Any       | Key      | any.key@mail.com | any.key@mail.com |