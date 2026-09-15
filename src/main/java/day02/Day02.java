package day02;

import utils.FileReader;

import java.util.List;

public class Day02 {

    final List<String> lines;

    public Day02(String filename) {
        lines = FileReader.readFileToString(filename);
    }

    public void testPasswords() {
        int validCount = 0;
        for (String line : lines) {
            String[] parts = line.split(": ");
            PasswordPolicy policy = new PasswordPolicy(parts[0]);
            boolean valid = policy.isValid(parts[1]);
            if (valid) {
                validCount++;
            }
        }
        System.out.println("Valid passwords: " + validCount);
    }

    public void testUpdatedPasswords() {
        int validCount = 0;
        for (String line : lines) {
            String[] parts = line.split(": ");
            UpdatedPasswordPolicy policy = new UpdatedPasswordPolicy(parts[0]);
            boolean valid = policy.isValid(parts[1]);
            if (valid) {
                validCount++;
            }
        }
        System.out.println("Valid passwords: " + validCount);
    }

}

class UpdatedPasswordPolicy {
    private final int pos1;
    private final int pos2;
    private final char letter;

    public UpdatedPasswordPolicy(String line) {
        String[] parts = line.split(" ");
        String[] positions = parts[0].split("-");
        this.pos1 = Integer.parseInt(positions[0]) - 1; // Convert to 0-based index
        this.pos2 = Integer.parseInt(positions[1]) - 1; // Convert to 0-based index
        this.letter = parts[1].charAt(0);
    }

    public void print() {
        System.out.println("UpdatedPasswordPolicy{" +
                "pos1=" + pos1 +
                ", pos2=" + pos2 +
                ", letter='" + letter + '\'' +
                '}');
    }

    public boolean isValid(String password) {
        boolean firstPositionMatch = password.charAt(pos1) == letter;
        boolean secondPositionMatch = password.charAt(pos2) == letter;
        return firstPositionMatch ^ secondPositionMatch; // XOR: true if exactly one position matches
    }
}

class PasswordPolicy {
    private final int min;
    private final int max;
    private final char letter;

    public PasswordPolicy(String line) {
        String[] parts = line.split(" ");
        String[] range = parts[0].split("-");
        this.min = Integer.parseInt(range[0]);
        this.max = Integer.parseInt(range[1]);
        this.letter = parts[1].charAt(0);
    }

    public void print() {
        System.out.println("PasswordPolicy{" +
                "min=" + min +
                ", max=" + max +
                ", letter='" + letter + '\'' +
                '}');
    }

    public boolean isValid(String password) {
        long count = password.chars().filter(ch -> ch == letter).count();
        return count >= min && count <= max;
    }


}
