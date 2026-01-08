# Programming Language Concepts (Sprachkonzepte)

This repository contains solutions and implementations for the university course
**Programming Language Concepts (Sprachkonzepte)**  
at **Konstanz University of Applied Sciences (HTWG Konstanz)**.

Each task is organized in its own directory and documented with a dedicated
`README.md` file. The structure is designed to be clean, reproducible, and
suitable as a written report template.

---

## Repository Structure

```
programming-language-concepts/
├─ 1_Lexer/
│  └─ README.md        # Task 1: Lexer and tokenization
├─ 2_Language/
│  └─ README.md        # Task 2: Grammar and abstract syntax (AST)
├─ 3_Semantics/
│  └─ README.md        # Task 3: Static and dynamic semantics
├─ .gitignore
└─ README.md           # This file
```

---

## Task Overview

### Task 1 – Lexer
- Definition of vocabularies using ANTLR4 lexer grammars
- Tokenization of real-world text (DB departure board)
- Java tokenizer program printing token sequences

### Task 2 – Language and Abstract Syntax
- Definition of a small custom language (MiniJSON)
- Concrete syntax using ANTLR4 lexer and parser grammars
- Abstract syntax defined using Java classes
- Transformation from parse tree to AST

### Task 3 – Semantics
- Static semantics (e.g. detection of duplicate object keys)
- Dynamic semantics (interpretation of MiniJSON programs)
- Listener-based semantic checks and visitor-based evaluation

---

## Build System

All tasks use **Gradle** with the ANTLR plugin.
Generated sources and compiled artifacts are excluded from version control.

Typical build command (inside a task directory):

```bash
gradle clean build
```

---

## Notes

- Each task directory is self-contained
- READMEs are written in English and can be used directly as report sections
- The project follows best practices for ANTLR, Java, and Gradle-based workflows

---

Student work created as part of the course *Programming Language Concepts
(Sprachkonzepte)* at **HTWG Konstanz – Konstanz University of Applied Sciences**.
