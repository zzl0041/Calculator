# Calculator Project

## Overview

This project provides a Java-based calculator with support for basic and advanced mathematical operations. It emphasizes extensibility and good software design principles, particularly the Open-Closed Principle.

## Features

- **Basic Operations**: Addition, Subtraction, Multiplication, Division
- **Advanced Operations**: Modulo, Power, Root
- **Chaining**: Perform a sequence of operations on an initial value
- **Extensibility**: Easy to add new operations without altering existing code
- **Error Handling**: Manages unsupported operations and invalid inputs gracefully

## Design Decisions

- **Operation Enum**: Defines supported operations, including basic and advanced ones.
- **Calculator Class**:
    - **Basic Implementation**: Handles predefined operations directly.
    - **OperationStrategy Map**: Uses a strategy map for flexibility and easier extensibility.
    - **Final Implementation**: Utilizes an enum and associated strategies for clean design and adherence to the Open-Closed Principle.

# Assumptions

- **Precision**: Floating-point results are compared with a tolerance for precision issues.
- **Input Validity**: Assumes inputs are correctly formatted and validated.
- **Operation Accuracy**: All operations are implemented correctly.

## Testing

Unit tests ensure the chain method works correctly for all operations and handles errors. Tests verify:

- Correct results for various operations
- Proper handling of mismatched input sizes and unsupported operations