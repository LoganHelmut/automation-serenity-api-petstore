# Serenity BDD Test Project (ScreenPlay Design Pattern)

A UI test automation framework (Serenity BDD + RestAssured + Java + ScreenPlay) using **Java 20** and **Gradle 8.10 (via wrapper)**.

## 🚀 Video Results

1. Link to video with results API Testing:
   ```bash
   https://jam.dev/c/ccf2e090-958c-4749-b759-43995ebc457e
   
## ✅ Requirements

- Java 20 installed (`java -version`)
- Git installed (optional)
- Gradle is not required (uses wrapper)

## 🛠 Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/LoganHelmut/automation-serenity-api-petstore.git
   cd serenity-project

## 🚀 Running Tests

1. (Linux/macOS only) Grant execution permission to the Gradle wrapper:
   ```bash
   chmod +x gradlew
2. Clean the project:
   ```bash
   ./gradlew clean
3. Run tests and generate the Serenity report:
   ```bash
   ./gradlew test aggregate
4. After test execution, open:
   ```bash
   build/reports/serenity/index.html
## 🧠 Notes

- Make sure your IDE is configured to use Java 20 as the project SDK.
- Gradle wrapper version is configured in gradle/wrapper/gradle-wrapper.properties.

3. Project Structure (ScreenPlay Design Pattern):
   ```bash 
   📁 src
   ├── 📁 main
   │   └── 📁 java
   │       └── 📦 petstore.api.serenity.automation
   │           ├── 📁 models            # Data models for requests/responses
   │           ├── 📁 questions         # Custom questions for API validations
   │           ├── 📁 tasks             # Actions/Tasks the actor can perform
   │           └── 📁 utils             # Helper classes and utilities
   │           
   │
   ├── 📁 test
   │   ├── 📁 java
   │   │   └── 📦 petstore.api.serenity.automation
   │   │       ├── 📁 runners           # Cucumber runner class
   │   │       ├── 📁 stepdefinitions   # Step definitions for Gherkin steps
   │   │       │   └── 📁 hook          # Hooks (Before/After scenario logic)
   │   │       ├── 🧾 OrderStepDefinitions.java
   │   │       └── 🧾 PetStepDefinitions.java
   │   └── 📁 resources
   │       └── 📁 features              # Feature files
   │           ├── 🟩 order.feature
   │           └── 🟩 pet.feature
   
