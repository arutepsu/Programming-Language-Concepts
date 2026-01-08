# Task 1 – Lexer and Tokenization with ANTLR4

This task focuses on **lexical analysis** using ANTLR4.
The goal is to describe the vocabulary of a text using lexer rules and to
implement a program that reads a text file and outputs the corresponding
**token sequence**.

For this implementation, the **Deutsche Bahn departure board** text
(`abfahrt-kn.txt`) was selected.

---

## Task Description (Summary)

- Define the vocabulary of a text using ANTLR4 lexer rules
- Implement a tokenizer application similar to `ExprTokenizer.java`
  from the lecture
- Read an input text file and print the token stream
- Ensure the lexer is robust enough to handle similar texts of the same domain

---

## Chosen Text Domain

**Deutsche Bahn – Departure Board (Konstanz)**

The input text contains information such as:

- Train categories and line identifiers
- Destinations
- Departure times
- Platform numbers
- Status information

The lexer is designed to tokenize not only the given example file but also
other departure board texts of the same structure.

---

## Lexer Vocabulary Categories

Based on the vocabulary categories discussed in the lecture (slides 2–4),
the following categories occur in the departure board text:

- **Keywords** (e.g. train types, fixed domain terms)
- **Identifiers / names** (destinations, locations)
- **Numbers** (times, platform numbers)
- **Time expressions** (e.g. `12:34`)
- **Separators and punctuation** (e.g. `:`, `-`)
- **Whitespace** (ignored)
- **Fallback tokens** for unexpected input

---

## Implementation

### Lexer Grammar

The lexer grammar is defined in:

```
DBLexer.g4
```

It specifies the lexical structure of departure board texts and assigns
token types to the relevant vocabulary elements.

---

### Tokenizer Program

The tokenizer is implemented in Java:

```
DBTokenizer.java
```

The program:

1. reads an input file passed as a command-line argument
2. feeds the input to the ANTLR-generated lexer
3. prints the resulting token sequence

Hidden-channel tokens (such as whitespace) can be filtered or displayed
depending on the output mode.

---

## Project Structure

```
1_Lexer/
  src/
    main/
      antlr/
        DBLexer.g4
      java/
        DBTokenizer.java
  inputs/
    abfahrt-kn.txt
```

---

## How to Run

Build the project:

```bash
gradle clean build
```

Run the tokenizer on the departure board input:

```bash
gradle run --args="inputs/abfahrt-kn.txt"
```

---

## Result

- The departure board text is successfully tokenized
- The token sequence is printed to the console
- The lexer correctly identifies the relevant vocabulary categories
- The implementation fulfills the requirements of Task 1
