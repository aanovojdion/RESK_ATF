#@UI  @AddContact
#Feature: Add New Contact
#
#  Background:
#    Given already logged-in user is on the Contact List page
#
##  Scenario: User add a new contact filling in only required fields
##    Given the Contact List is empty
##    And user clicked the Add a New Contact button
##    When user submits add a new contact form with required valid data
##      | firstName | lastName |
##      | Ken       | Li       |
##    Then user is redirected to the Contact List page
##    And newly added user is displayed in the table
#
#
#  @Negative
#  Scenario Outline: User cannot add a new contact
#
#    Given user clicked the Add a New Contact button
#    When user submits add a new contact form with required invalid data
#      | firstName   | lastName   |
#      | <firstName> | <lastName> |
#    Then the warning message is displayed
#    And the Add Contact page is displayed
#
#    Examples:
#      | firstName | lastName |
#      | <empty>   | <empty>  |
#      | Rob       | <empty>  |
#      | <empty>   | Ken      |