# Task 4 – Imperative vs. Functional Programming in Java

This task compares an imperative (procedural) solution with a functional-style
solution using Java Streams and Lambdas.

The program processes a text file as follows:
1. Remove empty lines (empty or whitespace-only)
2. Remove short lines (length < MIN_LENGTH)
3. Compute the sum of the lengths of the remaining lines

---

## 4a) Procedural (Imperative) Version

The procedural implementation follows a classic imperative style:

- Mutable data structures (`LinkedList`)
- Procedures (`void` helper methods)
- Explicit loops (`while`, `for`)
- Conditional branching (`if`)
- In-place updates and side effects

File:
```
Procedural.java
```

This version clearly demonstrates an imperative / procedural programming style,
as control flow and state changes are explicitly managed by the programmer.

---

## 4b) Functional Version

The functional implementation uses Java Streams and Lambdas:

- `Files.lines(...)` to create a stream of lines
- `filter`, `mapToInt`, and `sum` operations
- No explicit loops
- No `if` statements
- No mutable collections
- No side effects in the data-processing pipeline

File:
```
Functional.java
```

The program expresses *what* should be computed instead of *how* it is computed,
which is characteristic of a functional programming style.

---

## 4c) Runtime Comparison

The runtime of both implementations is compared using different input sizes
(from kilobytes up to multiple megabytes).

To obtain meaningful results:
- Warm-up runs are executed to trigger JIT compilation
- Multiple measurement runs are performed
- Minimum and median execution times are reported

File:
```
Benchmark.java
```

---

## Benchmark Execution and Results

Run the benchmark on a 10 MB input file:

```bash
gradle run --args="inputs/10MB.txt" -PmainClass=Benchmark
```

---

### Benchmark Log (10 MB Input)

```text
> Task :run
result = 9463634 (134529 microsec)
result = 9463634 (81916 microsec)
result = 9463634 (62664 microsec)
result = 9463634 (35993 microsec)
result = 9463634 (43173 microsec)
result = 9463634 (39124 microsec)
result = 9463634 (55499 microsec)
result = 9463634 (41815 microsec)
result = 9463634 (37599 microsec)
result = 9463634 (29377 microsec)
result = 9463634 (46611 microsec)
result = 9463634 (39668 microsec)
result = 9463634 (46254 microsec)
result = 9463634 (44882 microsec)
result = 9463634 (60811 microsec)
result = 9463634 (34284 microsec)
result = 9463634 (46645 microsec)
result = 9463634 (37422 microsec)
result = 9463634 (60096 microsec)
result = 9463634 (58104 microsec)
result = 9463634 (49277 microsec)
result = 9463634 (32674 microsec)
result = 9463634 (36263 microsec)
result = 9463634 (30295 microsec)
result = 9463634 (36769 microsec)
result = 9463634 (31031 microsec)
result = 9463634 (36532 microsec)
result = 9463634 (35655 microsec)
result = 9463634 (78188 microsec)
result = 9463634 (32060 microsec)

Procedural (microsec): min=36665 median=47019
Functional (microsec): min=30566 median=36031
```

---

### Interpretation

Both implementations compute the same result, but the **functional implementation
is consistently faster** for this input size.

- The median runtime of the functional version is approximately 23% lower
- The functional pipeline avoids repeated list mutations and element removals
- Java Streams allow the JVM to apply internal optimizations
- Fewer intermediate data structures improve cache locality

This experiment demonstrates that a functional, declarative style using streams
can be **both clearer and more efficient** than a manual imperative approach,
contradicting the common assumption that functional code is inherently slower.

---

## Test Data Generation

Large input files are generated automatically to ensure reproducible benchmarks.

The generator creates files with a realistic mix of:
- Empty lines
- Short lines
- Long lines (length ≥ MIN_LENGTH)

File:
```
GenerateTestFiles.java
```

---

## Project Structure

```
4_Imperative_vs_Functional/
  src/
    main/
      java/
        Procedural.java
        Functional.java
        Benchmark.java
        GenerateTestFiles.java
  inputs/
  README.md
```

---

## How to Run

Build the project:

```bash
gradle clean build
```

Generate test files (written to `inputs/`):

```bash
gradle run -PmainClass=GenerateTestFiles
```

Run the procedural version:

```bash
gradle run --args="inputs/10MB.txt" -PmainClass=Procedural
```

Run the functional version:

```bash
gradle run --args="inputs/10MB.txt" -PmainClass=Functional
```

Run the benchmark:

```bash
gradle run --args="inputs/10MB.txt" -PmainClass=Benchmark
```

---

## Conclusion

The procedural version highlights explicit control flow, mutation, and
side effects, making it clearly imperative in style.

The functional version expresses the computation as a stream transformation
pipeline without explicit state changes. In practice, this leads not only to
clearer code but also to **better runtime performance**, as demonstrated by the
benchmark results.
