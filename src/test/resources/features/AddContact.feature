Feature: Add New Contact

  Background:
    Given already logged-in user is on the Contact List page

  @AddContact @PositiveFlow

  Scenario Outline: User add a new contact filling in only required fields
    Given user clicked the Add a New Contact button
    When user submits add a new contact form with required valid data
      | firstName   | lastName   |
      | <firstName> | <lastName> |
    Then user is redirected to the Contact List page
    And newly added user is displayed in the table

    Examples:
      | firstName | lastName |
      | Ken       | Li       |
      | Ann       | Stone    |
      | Mark      | Rog      |

#
  @AddContact @NegativeFlow

  Scenario Outline: User cannot add a new contact

    Given user clicked the Add a New Contact button
    When user submits add a new contact form with required invalid data
      | firstName   | lastName   |
      | <firstName> | <lastName> |
    Then the warning message is displayed
    And the Add Contact page is displayed

    Examples:
      | firstName | lastName | warning message                                                                                             |
      | <empty>   | <empty>  | Contact validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required. |
      | Rob       | <empty>  | Contact validation failed: lastName: Path `lastName` is required.                                           |
      | <empty>   | Ken      | Contact validation failed: firstName: Path `firstName` is required.                                         |