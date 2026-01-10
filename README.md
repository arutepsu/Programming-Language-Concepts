# Programming Language Concepts (Sprachkonzepte)

This repository contains solutions and implementations for the university course  
**Programming Language Concepts (Sprachkonzepte)**  
at **Konstanz University of Applied Sciences (HTWG Konstanz)**.

Each task is organized in its own directory and documented with a dedicated
`README.md` file. The structure is designed to be clean, reproducible, and
directly usable as a written report template.

---
## Task Overview

### Task 1 – Lexer
📂[1_Lexer](/1_Lexer/)
- Definition of vocabularies using ANTLR4 lexer grammars
- Tokenization of real-world text (DB departure board example)
- Java-based tokenizer printing token sequences

---

### Task 2 – Language and Abstract Syntax
📂[2_Language](/2_Language/)
- Definition of a small custom language (*MiniJSON*)
- Concrete syntax using ANTLR4 lexer and parser grammars
- Abstract syntax defined using Java classes
- Transformation from parse trees to an abstract syntax tree (AST)

---

### Task 3 – Semantics
📂[3_Semantics](/3_Semantics/)
- Static semantics (e.g. detection of duplicate object keys)
- Dynamic semantics (interpretation of MiniJSON programs)
- Listener-based semantic checks and visitor-based evaluation

---

### Task 4 – Imperative vs Functional Programming
📂[4_Imperative_vs_Functional](/4_Imperative_vs_Functional/)
- Comparison of imperative and functional programming paradigms
- Analysis of control flow, state, and side effects
- Implementation of equivalent algorithms in different paradigms
- Discussion of strengths, weaknesses, and typical use cases

---

### Task 5 – Logic Programming with Prolog
📂[5_Prolog](/5_Prolog/)
- Introduction to logic programming concepts
- Definition of facts, rules, and recursive predicates
- Query evaluation and backtracking
- Modeling of real-world problems (e.g. route planning with constraints)

---

### Task 6 – Reflection
📂[6_Reflection](/6_Reflection/)
- Use of Java Reflection to inspect classes and interfaces dynamically at runtime
- Analysis of program structure without compile-time knowledge using Class.forName
- Extraction of implemented interfaces and public method signatures
- Filtering of inherited methods to focus on relevant API information
- Transformation of reflective information into structured HTML using StringTemplate
- Clear separation between analysis logic, internal data model, and presentation

---

### Task 7 – Scripting Languages (Python)
📂[7_CurrencyConverter](/7_CurrencyConverter/)
- Implementation of a small scripting-language application
- Python-based command-line currency converter
- Integration of an external REST API for exchange rates
- Analysis of typical scripting-language characteristics:
  - interpreted execution
  - dynamic typing
  - high-level libraries
  - rapid development and automation
- Orchestration via Gradle to ensure reproducible execution

---

## Build System

All tasks use **Gradle** as their build and execution system.

- ANTLR-based tasks use the Gradle ANTLR plugin
- Python-based tasks are executed via Gradle using virtual environments
- Generated sources and build artifacts are excluded from version control

Typical build command (inside a task directory):

```bash
gradle clean build
```

---

## Notes

- Each task directory is self-contained
- READMEs are written in English and can be used directly as report sections
- The project follows best practices for ANTLR, Java, Gradle-based workflows, scripting and automation with Python

---

Student work created as part of the course *Programming Language Concepts
(Sprachkonzepte)* at **HTWG Konstanz – Konstanz University of Applied Sciences**