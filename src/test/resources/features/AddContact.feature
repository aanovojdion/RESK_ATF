Feature: Add Contact Functionality

  Background:
    Given already registered user is on the Contact List page
    And user clicks on Add a New Contact button
#    And user on the Add Contact page

  @AddContact @PositiveFlow

  Scenario Outline: User add contact filling in only required fields

    When user populate the add contact form with valid data
      | firstName   | lastName   |
      | <firstName> | <lastName> |
    And user clicks on Submit button
    Then user is redirected to the Contact List page
    And newly added user is displayed in the table

    Examples:
      | firstName | lastName |
      | John      | Li       |
      | Ann       | Stone    |
      | Rob       | Ken      |


  @AddContact @NegativeFlow

  Scenario Outline: User cannot add contact

    When user populate the add contact form with invalid data
      | firstName   | lastName   |
      | <firstName> | <lastName> |
    And user click on Submit button
    Then the contact is not displayed in the table
    And the <warning message> is displayed

    Examples:
      | firstName | lastName | warning message                                                                                             |
      |           |          | Contact validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required. |
      | Rob       |          | Contact validation failed: lastName: Path `lastName` is required.                                           |
      |           | Ken      | Contact validation failed: firstName: Path `firstName` is required.                                         |