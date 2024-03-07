#Feature:  User Registration API
#
#  Background:
#    Given user is on the Home page
#    And user navigates to the Add User page
#
#  @UserRegistration @LogIn @PositiveFlow @API
#  Scenario Outline: Successful Registration of a New User
#    When user submits registration form with valid data
#      | firstName   | lastName   | email   | password   |
#      | <firstName> | <lastName> | <email> | <password> |
#    Then user is successfully registered
#    And the My Contact List page is dislayed
#    Examples:
#      | firstName | lastName | email         | password |
#      | John      | Li       | jli@gmail.com | test123  |