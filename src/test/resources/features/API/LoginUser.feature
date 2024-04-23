@API
Feature: Successfully user login

  Scenario: User successfully logged in via API
    Given user is using valid credentials
      | email    | any.key@mail.com |
      | password | 123NewPassword   |
    When a request to login is sent to LOGIN_USER endPoint
    Then response that user successfully logged in is received