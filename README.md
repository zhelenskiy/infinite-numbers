# Infinite Numbers

A Kotlin multiplatform library providing arbitrary-precision numeric types with support for integers, rational numbers, and real numbers.

## Features

### Number Types

- **IntegerNumber**: Arbitrary-precision integers
- **RationalNumber**: Arbitrary-precision rational numbers represented as irreducible fractions (numerator/denominator)
- **RealNumber**: Real numbers with precise interval arithmetic and lazy evaluation

### Arithmetic Operations

- **Basic operations**: Addition, subtraction, multiplication, division
- **Powers and roots**: `pow()`, `root()`, `square()`
- **Logarithms**: `ln()`, `log()` with custom base
- **Comparison**: Full ordering support for rational numbers and approximate ordering support for other real numbers

### Mathematical Constants

- **e** (Euler's number): Computed via Taylor series
- **π** (Pi): Computed using Machin-like formula for high precision

### Advanced Features

- **Interval arithmetic**
- **Binary search**
- **Reverse function search**
- **Monotonic limits**
- **Series summation**
- **Evaluation caching**

### Formatting

- Multiple fraction formats:
  - `DOT`: Decimal notation (e.g., "3.(14159)")
  - `COMMA`: European notation (e.g., "3,(14159)")
  - `DIVISION`: Fraction notation (e.g., "22/7")
  - `MIXED`: Mixed number notation (e.g., "3 1/7")
- Custom radix support (base 2-36)
- Configurable precision for real numbers

## Supported Platforms

- JVM
- Android
- iOS (iosArm64, iosSimulatorArm64, iosX64)

## Usage

### Basic Examples

```kotlin
// Create an evaluation cache for efficient computation
context(SimpleEvaluationCache()) {
    // Integer arithmetic
    val a = IntegerNumber(5)
    val b = IntegerNumber(3)
    val sum = a + b  // 8

    // Rational numbers
    val half = RationalNumber(1, 2)
    val third = RationalNumber(1, 3)
    val result = half + third  // 5/6

    // Real numbers with arbitrary precision
    val sqrt2 = two.root(two)
    val piApprox = pi  // High-precision π

    // Mixed operations
    val mixed = a + half + sqrt2

    // Powers and roots
    val squared = IntegerNumber(3).pow(2)  // 9
    val cubeRoot = IntegerNumber(27).root(IntegerNumber(3))  // 3
    val power = two.pow(RationalNumber(3, 2))  // 2^(3/2) = 2√2
}
```

### Formatting Examples

```kotlin
val fraction = RationalNumber(22, 7)

// Different formats
fraction.toString(FractionFormat.DOT)      // "3.(142857)..."
fraction.toString(FractionFormat.COMMA)    // "3,(142857)..."
fraction.toString(FractionFormat.DIVISION) // "22/7"
fraction.toString(FractionFormat.MIXED)    // "3 1/7"

// Custom radix
val num = IntegerNumber(255)
num.toString(radix = 16)  // "ff"
num.toString(radix = 2)   // "11111111"
```

### Advanced Mathematical Operations

```kotlin
context(SimpleEvaluationCache(), RealNumberComparator) {
    // Logarithms
    val log10_100 = log(IntegerNumber(100), ten)  // 2
    val naturalLog = ln(e)  // 1

    // Real number powers
    val result = two.pow(pi)  // 2^π

    // Binary search to solve equations
    val sqrt5 = binarySearch(
        expected = IntegerNumber(5),
        searchBounds = Border.Inclusive(zero)..Border.PlusInfinity,
        function = { x: RealNumber -> x * x }
    )  // Finds x where x² = 5

    // Working with intervals
    val bounds = Border.Inclusive(one)..Border.Inclusive(ten)
}
```

### Computing Mathematical Constants with Precision

```kotlin
context(SimpleEvaluationCache(), RealNumberComparator) {
    // Round pi to 20 decimal digits
    val piRounded = pi.roundToRationalNumber(fractionDigitsCount = IntegerNumber(20))
    println(piRounded.toString(FractionFormat.DOT))
    // Output: 3.14159265358979323846

    // Round e to 20 decimal digits
    val eRounded = e.roundToRationalNumber(fractionDigitsCount = IntegerNumber(20))
    println(eRounded.toString(FractionFormat.DOT))
    // Output: 2.71828182845904523536

    // Combine constants: e^π rounded to 20 digits
    val ePowerPi = e.pow(pi)
    val ePowerPiRounded = ePowerPi.roundToRationalNumber(fractionDigitsCount = zero)
    println(ePowerPiRounded.toString(FractionFormat.DOT))
    // Output: 23

    // Compute π + e rounded to 20 digits
    val piPlusE = pi + e
    val piPlusERounded = piPlusE.roundToRationalNumber(fractionDigitsCount = IntegerNumber(20))
    println(piPlusERounded.toString(FractionFormat.DOT))
    // Output: 5.85987448204883847382
}
```

### Number Construction

```kotlin
// From integers
val int1 = IntegerNumber(42)
val int2 = IntegerNumber(Long.MAX_VALUE)

// From strings
val large = IntegerNumber("123456789012345678901234567890")

// Rationals from numerator and denominator
val rational = RationalNumber(numerator = IntegerNumber(22), denominator = IntegerNumber(7))

// Simplified constructor
val half = RationalNumber(1, 2)
val half1 = one / 2

// From primitive types
val fromInt = IntegerNumber(100)
val fromLong = IntegerNumber(1000L)
```

### Context Parameters

The library uses Kotlin's context parameters for managing computation state:

- **`EvaluationCache`**: Required for arithmetic operations with real numbers, provides memoization
- **`Comparator<RealNumber>`**: Required for comparison operations on real numbers, infinite sequences and limits

```kotlin
// Create contexts once and reuse
val cache = SimpleEvaluationCache()
val comparator = realNumberApproximateComparator(ten.pow(-20))

context(cache, comparator) {
    // All operations here benefit from caching and comparison
    val result = computeComplexExpression()
}
```

## Building

This project uses Amper for build configuration. Use the provided `amper` script to build the project:

```bash
./amper test
```
