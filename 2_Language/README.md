# Task 2 – Language Definition and Abstract Syntax

In this task, a small custom language called **MiniJSON** is defined.
The task is divided into two parts:

- **2a)** Definition of the concrete syntax using ANTLR4
- **2b)** Definition of the abstract syntax and construction of an AST

---

## 2a) Concrete Syntax Definition

### Language Overview

MiniJSON is a simplified JSON-like language for representing structured data.
It supports:

- Objects with key–value pairs
- Arrays
- Strings
- Numbers
- Boolean values
- Null

The language is intentionally kept small to focus on grammar design and syntax
processing.

---

### Lexer Grammar

The vocabulary of the language is defined using an ANTLR4 **lexer grammar**.
It includes tokens for:

- Keywords: `true`, `false`, `null`
- Structural symbols: `{ } [ ] : ,`
- String literals
- Numeric literals
- Whitespace (ignored)

The lexer grammar is defined in:

```
MiniJSONLexer.g4
```

---

### Parser Grammar

The grammar of the language is defined using an ANTLR4 **parser grammar**.
The start symbol of the grammar is `json`.

The grammar defines rules for:

- Values
- Objects and object members
- Arrays
- Literals

The parser grammar is defined in:

```
MiniJSONParser.g4
```

---

### Parse Tree Generation

For several example inputs, parse trees were generated using the ANTLR tool
`org.antlr.v4.gui.TestRig`.

Example command:

```bash
java org.antlr.v4.gui.TestRig MiniJSON json -tree example1.mj
```

The resulting parse tree visualizes the full syntactic structure of the input
according to the grammar. 
An example parse tree:
[parse-tree](miniJSON/antlr4_parse_tree.png)

---

## 2b) Abstract Syntax Definition

### Abstract Syntax Tree (AST)

The abstract syntax of MiniJSON is defined using Java classes.
Each class represents a semantic construct of the language.

The AST classes include:

- `JsonObject`
- `JsonArray`
- `JsonString`
- `JsonNumber`
- `JsonBoolean`
- `JsonNull`
- `JsonValue` (common supertype)

These classes model only the meaningful structure of the language and omit
purely syntactic details.

---

### Parse Tree to AST Transformation

A Java program was implemented that traverses the ANTLR parse tree and
constructs the corresponding AST.

This transformation is performed using a visitor-based approach, implemented
in the classes:

- `AstBuilder`
- `BuildAst`

The program reads a MiniJSON input file, parses it using ANTLR, and converts
the resulting parse tree into an AST composed of the classes listed above.

---

### Omitted Elements in the AST

Compared to the parse tree, the AST omits the following elements:

- Structural tokens such as `{`, `}`, `[`, `]`, `:`, and `,`
- Intermediate nonterminals used only for grammar structuring
- Concrete syntax details such as parentheses and separators

Only semantically relevant constructs (values, objects, arrays, and literals)
are preserved in the AST.

---

## Project Structure

```
2_language/miniJson/
  src/
    main/
      antlr/     Lexer and parser grammars
      java/      AST classes and AST builder
  examples/
    example1.mj
    example2.mj
```

---

## How to Run

Build the project:

```bash
gradle clean build
```

Parse and process an example input:

```bash
gradle run --args="examples/example1.mj"
gradle run --args="examples/example2.mj"
```

---

## Result

- A complete concrete syntax is defined using ANTLR4
- Parse trees can be generated and visualized
- An abstract syntax is defined using Java classes
- The parse tree is successfully transformed into an AST
