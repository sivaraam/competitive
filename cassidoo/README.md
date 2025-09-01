# Cassidoo Interview Questions

This folder contains solutions to [Cassidoo's weekly interview questions](https://cassidoo.co/newsletter/).
Each solution is implemented in Java and includes corresponding test cases to verify correctness.

## Structure

- **`src/main`**: Contains the source code for the solutions.
- **`src/test`**: Contains the test cases for the solutions, implemented using JUnit.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Gradle (for building and running the project)

## Instructions

### Running the Solutions

1. Navigate to the root of the project directory.
2. Set desired class as `mainClass` in `build.gradle.kts` (~L35)
3. Use Gradle to build and run the program:
   ```bash
   ./gradlew run
   ```

### Running the Tests

To execute the test cases, run:
```
./gradlew test
```

#### Test Cases

- Test cases for each solution are located in the test directory.
- Each test file is named after the corresponding solution file, with Test appended to the name.
