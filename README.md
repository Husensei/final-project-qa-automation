# API and Web Automation Tests

This project automates tests for the functionalities of [Dummy API](dummyapi.io) and [Demo Blaze](demoblaze.com) using Java and Gradle.

## Getting Started
This project uses Gradle for build automation. The Gradle Wrapper included in the project automatically downloads and runs the appropriate Gradle version required by the project. While you don't need to install Gradle to run the tests, you will need Java installed on your machine to execute the Java code within the tests. Refer to the official Java download page for installation instructions.

### Prerequisites

- Java 21 or later

### Installation

1. Clone the repository `git clone https://github.com/<Your Username>/final-project-qa-automation`
2. Navigate to project directory `cd final-project-qa-automation`
3. Run the test using Gradle Wrapper `./gradlew <Task Name>`

Available tasks:
1. `cucumberTest` : Run all tests
2. `apiTest` : Run only API test
3. `webTest` : Run only Web test



