# Kotlin Exercises Project

A collection of Kotlin programming exercises and examples covering various topics including coroutines, algorithms, data structures, and design patterns.

## 🧪 Running Tests

### Basic Test Commands

```bash
# Run all tests
./gradlew test

# Clean, build and run tests
./gradlew clean build test
```

## 📊 Code Coverage

### Generate Coverage Reports

```bash
# Run tests and generate coverage report
./gradlew test jacocoTestReport

# View coverage report locations:
# - HTML: build/reports/jacoco/test/html/index.html
# - XML: build/reports/jacoco/test/jacocoTestReport.xml
```

### Coverage Verification

```bash
# Run coverage verification (checks coverage thresholds)
./gradlew jacocoTestCoverageVerification

# Run tests with coverage verification
./gradlew test jacocoTestCoverageVerification
```

## 📋 All-in-One Commands

```bash
# Complete build with tests and coverage
./gradlew clean build test jacocoTestReport

# Quick test run with coverage
./gradlew test jacocoTestReport && open build/reports/jacoco/test/html/index.html
```
