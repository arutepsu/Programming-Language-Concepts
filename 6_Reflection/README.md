# Task 6 – Reflection-Based HTML Generation

This task demonstrates the use of **Java Reflection** together with
**StringTemplate** to generate structured HTML documentation for Java classes
and interfaces.

The goal is to show how program structure can be inspected dynamically at
runtime and transformed into a readable representation using templates, while
keeping analysis logic and presentation clearly separated.

---

## Problem Description

The program receives a list of **fully qualified Java class and interface
names** as command-line arguments and generates an HTML page that documents:

- Classes and interfaces
- Implemented interfaces of each class
- Public methods of each interface, including method signatures

The generated HTML follows the structure and formatting of a provided example
file (`aufgabe6.html`).

---

## Use of Reflection

### Runtime Class Loading

All input types are loaded dynamically using:

```java
Class.forName(String)
```

This allows the program to work with arbitrary Java classes and interfaces
without compile-time knowledge of their structure.

---

### Structural Analysis

Using Java Reflection, the following information is extracted:

- Whether a type is a **class** or an **interface**
- Fully qualified type names
- For classes:
  - All directly implemented interfaces
- For interfaces:
  - All public methods, including inherited interface methods

Methods inherited from `java.lang.Object` are filtered out to avoid irrelevant
output.

---

### Method Signatures

Each method is rendered with:

- Return type
- Method name
- Fully qualified parameter types

This results in clear and unambiguous method signatures suitable for technical
documentation.

---

## Separation of Model and Presentation

### Internal Data Model

Reflection results are mapped into simple data structures:

- `TypeInfo` represents a class or interface
- `InterfaceInfo` represents an implemented interface and its methods

This separation ensures that reflection logic is independent from the output
format.

---

### Template-Based HTML Generation

HTML output is generated using **StringTemplate 4**:

- A root template receives a collection of `TypeInfo` objects
- No reflection or business logic is embedded in the template
- The template is responsible solely for layout and formatting

To avoid conflicts with HTML syntax, `$...$` delimiters are used instead of
StringTemplate’s default `<...>` delimiters.

---

## Project Structure

```
6_reflection_html/
  src/
    main/
      java/
        a6/
          Main.java
          ReflectionModelBuilder.java
          SignatureFormatter.java
          TypeInfo.java
          InterfaceInfo.java
  aufgabe6.stg
  README.md
```

---

## How to Run

Generate the HTML documentation using Gradle:

```powershell
gradle -q run --args="java.lang.String java.util.Iterator java.time.Month" |
Out-File -Encoding utf8 aufgabe6.html
```

The `-q` flag suppresses Gradle task output so that only the generated HTML is
written to the output file.

---

## Result

- Java types are inspected dynamically using reflection
- Structural information is converted into a clean internal model
- HTML output is generated using templates instead of hard-coded strings
- The solution cleanly separates **analysis**, **modeling**, and
  **presentation**

This task demonstrates how reflection and templating can be combined to build
flexible and maintainable documentation tools.
