@API
Feature: Successfully user login

  Scenario: User successfully logged in
    Given user is using valid credentials
      | email    | any.key@mail.com |
      | password | any.key@mail.com |
    When a request to login is sent
    Then response that user successfully logged in is received