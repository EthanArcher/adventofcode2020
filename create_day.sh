#!/bin/bash

# Usage: ./create_day.sh day00
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 dayXX"
    exit 1
fi

day="$1"
if [[ ! "$day" =~ ^day[0-9]{2}$ ]]; then
    echo "Error: Argument must be in the format 'dayXX' (e.g., day04)"
    exit 1
fi

day_cap=$(echo "$day" | awk '{print toupper(substr($0,1,1)) substr($0,2)}')

main_dir="src/main/java/$day"
test_dir="src/test/java/$day"
main_file="$main_dir/$day_cap.java"
test_file="$test_dir/${day_cap}Test.java"
actual_file="src/main/resources/$day/actual.txt"
example_file="src/main/resources/$day/example.txt"

mkdir -p "$main_dir" "$test_dir" "src/main/resources/$day"

template="src/main/resources/day00/day00.txt"
if [ ! -f "$template" ]; then
    echo "Template $template not found"
    exit 1
fi

cp "$template" "$main_file"

# Replace package, class, and constant names in the copied file (use temp file for portability)
sed \
-e "s/package day00;/package $day;/" \
-e "s/Day00/$day_cap/g" \
-e "s/\"day00\"/\"$day\"/g" \
"$main_file" > "$main_file.tmp" && mv "$main_file.tmp" "$main_file"

touch "$actual_file" "$example_file" "$test_file"

echo "Created files for $day"
