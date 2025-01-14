# ex2
## Excel Project: Read Me

### Overview

This project is an Excel-like application designed to handle cells that can contain numbers, text, or formulas. It provides functionality to perform algebraic calculations and supports referencing other cells within formulas. The application also includes robust error handling for edge cases, such as circular references, ensuring consistent and accurate results.

### Features

-Cell Types

-Number

-Cells can store numeric values.

-These values can be used in algebraic operations.

-Text

-Cells can store text strings.

-Text content is static and cannot participate in arithmetic operations.

### Formula

Cells can store algebraic formulas that reference other cells or use constants.

Formulas are dynamically evaluated to compute a numeric result.

Algebraic Operations

Basic Arithmetic: Supports +, -, *, and / operators.

Complex Formulas: Handles formulas that involve multiple operators and parentheses for precedence.

Cell References: Allows referencing other cells (e.g., A1 + B2).

Actions on Numbers and Cells

Perform mathematical operations directly on numbers.

Use formulas to combine and compute values from multiple cells.

Edge Case Handling

Circular References

Detects when a cell references itself directly or indirectly.

Throws a clear error message to indicate the circular dependency.

Invalid References

Ensures referenced cells exist within the grid.

Throws an error for invalid or non-existent cell references.

Dynamic Updates

Automatically updates dependent cells when referenced cells change.

git repo-
https://github.com/yairvaisberg/ex2.git