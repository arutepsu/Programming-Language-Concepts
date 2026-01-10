# Currency Converter – Scripting Language Mini Application

This repository contains a small command-line application implemented in **Python** and orchestrated via **Gradle**.
The goal of this task is to demonstrate the **typical characteristics of a scripting language** by implementing a lightweight but practical tool.

The application retrieves **live currency exchange rates** from the [Frankfurter](https://frankfurter.dev/) - a public web API which allows users to query exchange rates or convert monetary amounts.

---

## 1. Task Description

The assignment was to:

> Implement a small application using a scripting language and analyze which typical properties of a scripting language are exploited.

For this purpose, **Python** was chosen as the scripting language, and a **currency converter CLI** was implemented.

---

## 2. Overview of the Application

The application provides two commands:

- **rate** – retrieve the latest exchange rate between two currencies  
- **convert** – convert a monetary amount from one currency to another  

The program accesses a public REST API to fetch up-to-date exchange rates and prints the result in a **formatted table**.

---

## 3. Project Structure

```
7_Currencyconverter/
├─ build.gradle
├─ settings.gradle
├─ requirements.txt
├─ currency_converter.py
├─ app/
│  ├─ __init__.py
│  ├─ api.py
│  ├─ cache.py
│  ├─ format.py
│  └─ util.py
```

### Responsibilities

- **currency_converter.py**  
  Entry point and command-line interface

- **app/api.py**  
  Communication with the external exchange-rate API

- **app/cache.py**  
  Simple file-based caching to avoid repeated HTTP requests

- **app/format.py**  
  Pretty table output for terminal display

- **app/util.py**  
  Input validation utilities

---

## 4. Build & Execution (Gradle-based)

Although Python is an interpreted language and does not require a build step, **Gradle** is used here to automate execution and dependency management.

Gradle performs the following tasks:

1. Creates a Python virtual environment  
2. Installs dependencies from `requirements.txt`  
3. Executes the Python script with user-defined parameters  

### Requirements

- Python 3 installed  
- Gradle installed  

### Install Dependencies

```
gradle installDeps
```

### Retrieve Exchange Rate

```
gradle rate -Pbase=EUR -Pquote=USD
```

### Convert Currency Amount

```
gradle convert -Pbase=EUR -Pquote=JPY -Pamount=250
```

All required parameters must be passed explicitly via Gradle properties.

---

## 5. Example Output

```
+------------+-----------+----------+
| date       | pair      | rate     |
+============+===========+==========+
| 2026-01-09 | EUR->USD  | 1.083400 |
+------------+-----------+----------+
```

---

## 6. Analysis: Scripting Language Characteristics

This project intentionally exploits several typical characteristics of scripting languages.

### 6.1 Interpreted Execution

The application is executed directly by the Python interpreter.
There is no compilation step, which allows for rapid testing and short feedback cycles.

### 6.2 Dynamic Typing

The external API returns JSON data, which is parsed into Python dictionaries and lists without explicit type declarations.
This flexibility significantly reduces boilerplate code.

### 6.3 High-Level Abstractions and Productivity

Using high-level libraries such as `requests`, HTTP communication and JSON parsing can be implemented in only a few lines of code.
This is a classic advantage of scripting languages for small tools and automation tasks.

### 6.4 Integration with External Systems (Glue Code)

A common use case for scripting languages is connecting external components.
In this application, Python acts as *glue code* between:

- a web-based exchange-rate service  
- local command-line interaction  
- formatted terminal output  

### 6.5 Pragmatic Runtime Features

The application includes a simple caching mechanism and runtime error handling.
Such pragmatic, solution-oriented design is typical for scripting languages.

---

## 7. Use of Gradle with a Scripting Language

Even though Python does not require a build tool, Gradle is used to:

- ensure reproducible execution  
- manage dependencies consistently  
- automate environment setup  

This reflects real-world practice, where scripting languages are often embedded into larger build or automation pipelines.

---

## 8. Conclusion

This project demonstrates that scripting languages like Python are well-suited for:

- rapid development  
- automation tasks  
- integration with external services  

By combining Python with Gradle, the solution remains lightweight while still following structured and reproducible workflows.
