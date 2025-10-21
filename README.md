# Java_software-lab5

## Description
The core purpose of this project is to model a hierarchy of Electric Appliances and demonstrate robust management operations within an apartment context using Object-Oriented Programming (OOP) principles in Java.

The solution utilizes a base class (`ElectricAppliance`) and concrete derived classes (`Refrigerator`, `Laptop`, `HairDryer`) to manage specific characteristics. A dedicated container class (`ApartmentApplianceManager`) is used to handle a collection of these objects, performing key operations such as power calculation, sorting, and specific property searches. A strong emphasis is placed on exception handling to manage invalid data and search failures
## Calculation of the task variant
Number in the group list: **15**;

C13 = 15 % 13 = **2**.

## Features
- **Appliance Hierarchy**: implements a clean inheritance structure starting from the abstract ElectricAppliance base class.
- **Power State Management**: provides methods (`plugIn()`, `unplug()`) to simulate connecting appliances to a socket.
- **Consumption Calculation**: accurately calculates the total power consumed only by the appliances currently plugged in.
- **Data Sorting**: implements sorting of all appliances based on their power consumption (in Watts).
- **Advanced Search**: features a search method (`findByRadiationRange`) to find appliances within a specified range of Electromagnetic Radiation (EMR) levels.
- **Robust Exception Handling**: thoroughly validates input parameters (e.g., non-positive power, invalid search ranges) and handles logical failures (e.g., search returning no results) using checked and unchecked exceptions.
- **Javadoc Documentation**: includes comprehensive Javadoc comments for all classes, fields, and methods, adhering to coding standards.

## How to run
First, clone the repository and navigate into the project directory:
```
git clone https://github.com/dk872/javasoftware-lab5
```
```
cd javasoftware-lab5
```

Compile the code:
```
javac src/main/java/org/example/*.java
```

Run the program:
```
java -cp src/main/java org.example.Main
```

## Unit tests
This project includes **19** unit tests using JUnit 5 to ensure the reliability and correctness of the core functionalities. The tests are split into logical files:

- **ElectricApplianceTest**: checks core state management (`plugIn`/`unplug`), initialization, and `toString` formatting.
- **ApplianceCreationTest**: validates constructors, ensuring `IllegalArgumentException` is thrown for invalid power or radiation inputs.
- **ApartmentApplianceManagerTest**: tests complex logic: correct power calculation, ascending sorting, and reliable EMR range search (including `RuntimeException` for no results).

### How to run tests
Make sure you have JUnit 5 configured, then run the tests with your preferred method:
  - From command line
  ```
  mvn test
  ```
  - In an IDE like IntelliJ IDEA or Eclipse using the test runner.

### Documentation
The project includes generated **Javadoc documentation**.  
You can browse it here: [Project Documentation](https://dk872.github.io/javasoftware-lab5/)

To regenerate the documentation, run:
```
javadoc -d docs -sourcepath src/main/java -subpackages org.example -private
```

## Author info
Dmytro Kulyk, a student of group IM-32.
