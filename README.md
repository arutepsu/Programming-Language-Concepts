# Programming Language Concepts (Sprachkonzepte)

This repository contains solutions and implementations for the university course  
**Programming Language Concepts (Sprachkonzepte)**  
at **Konstanz University of Applied Sciences (HTWG Konstanz)**.

Each task is organized in its own directory and documented with a dedicated
`README.md` file. The structure is designed to be clean, reproducible, and
directly usable as a written report template.

---

## Repository Structure

programming-language-concepts/
├─ 1_Lexer/
│  └─ README.md        # Task 1: Lexer and tokenization
├─ 2_Language/
│  └─ README.md        # Task 2: Grammar and abstract syntax (AST)
├─ 3_Semantics/
│  └─ README.md        # Task 3: Static and dynamic semantics
├─ 4_Imperative_vs_Functional/
│ └─ README.md # Task 4: Paradigm comparison
├─ 5_Prolog/
│ └─ README.md # Task 5: Logic programming with Prolog
├─ 6_Reflection/
│ └─ README.md # Task 6: Reflection and meta-programming
├─ 7_Currencyconverter/
│ └─ README.md # Task 7: Scripting languages (Python)
├─ .gitignore
└─ README.md # This file

---

## Task Overview

### Task 1 – Lexer
- Definition of vocabularies using ANTLR4 lexer grammars
- Tokenization of real-world text (DB departure board example)
- Java-based tokenizer printing token sequences

---

### Task 2 – Language and Abstract Syntax
- Definition of a small custom language (*MiniJSON*)
- Concrete syntax using ANTLR4 lexer and parser grammars
- Abstract syntax defined using Java classes
- Transformation from parse trees to an abstract syntax tree (AST)

---

### Task 3 – Semantics
- Static semantics (e.g. detection of duplicate object keys)
- Dynamic semantics (interpretation of MiniJSON programs)
- Listener-based semantic checks and visitor-based evaluation

---

### Task 4 – Imperative vs Functional Programming
- Comparison of imperative and functional programming paradigms
- Analysis of control flow, state, and side effects
- Implementation of equivalent algorithms in different paradigms
- Discussion of strengths, weaknesses, and typical use cases

---

### Task 5 – Logic Programming with Prolog
- Introduction to logic programming concepts
- Definition of facts, rules, and recursive predicates
- Query evaluation and backtracking
- Modeling of real-world problems (e.g. route planning with constraints)

---

### Task 6 – Reflection
- Use of reflection to inspect classes, methods, and fields at runtime
- Analysis of reflective capabilities in Java
- Discussion of use cases, limitations, and risks
- Comparison between compile-time and runtime program structure

---

### Task 7 – Scripting Languages (Python)
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