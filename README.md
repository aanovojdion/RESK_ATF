# Automated Test Framework (ATF Java)

## Introduction

This framework provides convenient tools for automated testing in Java using Selenium WebDriver, RestAssured, and other
libraries.

## Features

- Tests are written in BDD format using Gherkin syntax to be easy understandable for non-technical stakeholders.
- Integration with Selenium WebDriver for UI testing.
- Usage of RestAssured for API testing.
- Easy configuration and test execution.
- Ability to take screenshots during testing.
- Support for logging test results.
- Support for Cucumber reports.

## Setup

1. Clone the repository to your local machine:
    ```
    git clone [repository URL]
    ```
2. Navigate to the project directory and install the dependencies using Maven:
    ```
    cd [project name]
    mvn install
    ```

## Create Tests:

Create tests in the [features] directory. Write tests using the Gherkin language.

## Running Tests

Use an IDE or command line to run the tests.

## Logging and Reports

- After execution, you can find the log file for each scenario in the [logs] directory
- After execution, you can find the [cucumberreport.html] file in the [reports] directory

## Additional Settings

- Logging is configured via the log4j2.properties. You can customize the log format and location as needed.
- For UI tests, you can select the browser (Chrome or Firefox) and activate headless mode (true/false)
  in [application properties].