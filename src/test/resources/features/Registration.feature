Feature: Registration User Functionality

  Background:
    Given user is on the Home page
    And user navigates to the Add User page

  @UserRegistration @LogIn @PositiveFlow
  Scenario Outline: Successful Registration of a New User
    When the User submit the registration form with <valid data>
    Then the My Contact List page is dislayed
    Examples:
      | valid data |
      | firstName  | lastName | email            | password |
      | John       | Li       | jli@gmail.com    | test123  |
      | Ann        | Stone    | astone@yahoo.com | test123  |
      | Rob        | Ken      | rken@mail.com    | test123  |


  @UserRegistration @NegativeFlow
  Scenario Outline: Failed Registration of a New User
    When the User populates the registration form with invalid data
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    And the User clicks on Submit button
    Then a warning message <warning message> is displayed on the screen
    Examples:
      | firstName | lastName | email            | password | warning message                                                                                                                                                           |
      |           | Stone    | astone@yahoo.com | test123  | User validation failed: firstName: Path `firstName` is required.                                                                                                          |
      | Rob       |          | astone@yahoo.com | test123  | User validation failed: lastName: Path `lastName` is required.                                                                                                            |
      | Rob       | Ken      | rkenmail.com     | test123  | User validation failed: email: Email is invalid                                                                                                                           |
      | Ann       | Stone    | astone@yahoo.com | 123      | User validation failed: password: Path `password` (`123`) is shorter than the minimum allowed length (7).                                                                 |
      |           |          |                  |          | User validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required., email: Email is invalid, password: Path `password` is required. |

