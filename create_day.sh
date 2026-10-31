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

# Don't overwrite a solution that already exists
if [ ! -s "$main_file" ]; then
    cat > "$main_file" <<EOF
package $day;

import utils.FileReader;

import java.util.List;

public class $day_cap {

    final List<String> lines;

    public $day_cap(String filename) {
        lines = FileReader.readFileToString(filename);
    }


}
EOF
fi

touch "$actual_file" "$example_file"

# Don't overwrite a test that already exists
if [ ! -s "$test_file" ]; then
    cat > "$test_file" <<EOF
package $day;

import org.junit.jupiter.api.Test;

public class ${day_cap}Test {

    @Test
    void testExample() {
        $day_cap $day = new $day_cap("$day/example.txt");

    }

    @Test
    void testActual() {
        $day_cap $day = new $day_cap("$day/actual.txt");

    }

}
EOF
fi

echo "Created files for $day"
