# Task 3 – Static and Dynamic Semantics

This task extends the MiniJSON language defined in **Task 2** by adding
static and dynamic semantics.

The goal is to demonstrate that semantic rules can be specified and enforced
beyond pure syntax, and that programs written in the language can be
meaningfully evaluated.

---

## 3a) Static Semantics

### Existence of Static Semantics

Although MiniJSON is a small data description language, it allows the definition
of meaningful static semantic rules. One important rule is:

**S1 – Unique Object Keys**  
Within a JSON object, all keys must be unique.

This rule ensures that the meaning of an object is unambiguous. Without it,
different implementations might interpret duplicate keys differently (e.g.
first-wins vs. last-wins).

---

### Syntax vs. Static Semantics

The concrete syntax defined in Task 2 allows syntactically valid inputs that
violate the static semantic rule. For example:

```json
{ "a": 1, "a": 2 }
```

This input is syntactically correct according to the grammar, but violates rule
S1 because the key `"a"` appears more than once.

---

### Static Semantics Check

A static semantics check was implemented using an ANTLR **listener**.  
During a traversal of the parse tree:

- Each object introduces a new scope
- All keys of the object are collected
- Duplicate keys are detected and reported as semantic errors

Programs that violate the static semantics are rejected and not evaluated.

---

## 3b) Dynamic Semantics

### Definition

A dynamic semantics was implemented by defining an **evaluation function**
that maps MiniJSON programs to runtime values.

Each syntactic construct is assigned a meaning:

- Strings evaluate to string values
- Numbers evaluate to numeric values
- Arrays evaluate to lists of values
- Objects evaluate to key–value maps
- Boolean and null literals evaluate to their corresponding runtime values

---

### Implementation

The dynamic semantics is implemented as an ANTLR **visitor** that traverses the
parse tree and constructs runtime value objects (`JValue`).

Evaluation is only performed if:
1. The input is syntactically correct
2. The static semantics check succeeds

This ensures that semantically invalid programs are never executed.

---

## Project Structure

```
3_Semantics/
  src/
    main/
      antlr/     MiniJSON grammar
      java/      Static semantics, dynamic semantics, and runner
  test-inputs/
    ok.json
    duplicate_keys.json
```

---

## How to Run

Build the project:

```bash
gradle clean build
```

Run a valid input:

```bash
gradle run --args="test-inputs/ok.json"
```

Run an input with a static semantics violation:

```bash
gradle run --args="test-inputs/duplicate_keys.json"
```

---

## Result

- Static semantics violations are detected and reported with source locations
- Valid programs are evaluated to runtime values
- The MiniJSON language now has both static and dynamic semantics
