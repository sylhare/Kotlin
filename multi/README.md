# Multi-Module Kotlin Project

A demonstration of Gradle multi-module project architecture using Kotlin, showcasing best practices for project organization, dependency management, and build automation.

- **Core module**: Contains shared business logic and utilities
- **App module**: Application layer that depends on core functionality


## 🚀 Getting Started

### Prerequisites

- Java 11 or higher
- Gradle 8.11.1 (or use the included wrapper)

### Clone and Build

```bash
# Clone the repository
git clone <repository-url>
cd multi

# Build the project
./gradlew build

# Run tests
./gradlew test

# Generate code coverage report
./gradlew codeCoverageReport
```

## 🏃 Building and Testing

### Basic Commands

```bash
# Clean build
./gradlew clean build

# Run all tests
./gradlew test

# Run tests for specific module
./gradlew :core:test
./gradlew :app:test

# Generate and view coverage report
./gradlew codeCoverageReport
# Report available at: build/reports/jacoco/report.xml
```

## 📦 Modules

### Core Module
- **Purpose**: Contains shared business logic and utilities
- **Main Class**: `examples.Core`
- **Key Features**:
  - Provides `world()` function
  - Includes test utilities and mocks
  - Publishes JAR artifacts

**Example Usage:**
```kotlin
import examples.Core.Companion.world

val message = world() // Returns "world"
```

### App Module
- **Purpose**: Application layer that consumes core functionality
- **Main Class**: `examples.App`
- **Dependencies**: Depends on `:core` module
- **Key Features**:
  - Combines core functionality with application logic
  - Demonstrates inter-module dependency

**Example Usage:**
```kotlin
import examples.App.Companion.hello

val greeting = hello() // Returns "hello world"
```
