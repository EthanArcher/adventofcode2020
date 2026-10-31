# Advent of Code 2020

My Java solutions to [Advent of Code 2020](https://adventofcode.com/2020).

## Requirements

- Java 26 or newer
- Maven

## Project layout

```text
src/main/java/dayXX/DayXX.java   Solution for each day
src/main/java/utils/             Shared helpers, such as reading input files
src/main/resources/dayXX/        example.txt and actual.txt for each day
src/test/java/dayXX/DayXXTest.java   Runs the solution against both inputs
create_day.sh                    Creates the files for a new day
```

## Puzzle input

Each day has two input files:

- `example.txt` is the example from the puzzle description.
- `actual.txt` is your personal puzzle input. These files are ignored by git, so they aren't in this repo.

To run the tests on a fresh clone, download your input from the Advent of Code site for each day and save it as
`src/main/resources/dayXX/actual.txt`. The answers checked in the tests are specific to my input, so some `testActual`
tests will fail with yours.

## Running the solutions

The solutions run through their JUnit tests. Some tests assert the answers, and others print them to the console.

```bash
# Run every day
mvn test

# Run one day
mvn test -Dtest=Day16Test

# Run one test for one day
mvn test -Dtest='Day16Test#testExample'
```

## Adding a new day

```bash
./create_day.sh day17
```

This creates the following, leaving any existing solution or test untouched:

- `src/main/java/day17/Day17.java`
- `src/test/java/day17/Day17Test.java`, with a test for each input file
- `src/main/resources/day17/example.txt` and `actual.txt`, which start empty
