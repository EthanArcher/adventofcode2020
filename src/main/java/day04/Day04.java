package day04;

import utils.FileReader;

import java.util.ArrayList;
import java.util.List;

public class Day04 {

    private final List<String> passportLines = new ArrayList<>();

    public Day04(String filename) {
        List<String> lines = FileReader.readFileToString(filename);
        StringBuilder sb = new StringBuilder();

        for (String line : lines) {
            if (line.isEmpty()) {
                passportLines.add(sb.toString());
                sb.setLength(0); // Clear the StringBuilder
            } else {
                sb.append(line).append(" ");
            }
        }
        // Don't forget the last passport
        if (sb.length() > 0) {
            passportLines.add(sb.toString());
        }
    }

    public int countValidPassports() {
        int validCount = 0;
        for (String passportLine : passportLines) {
            Passport passport = new Passport(passportLine.trim());
            if (passport.isValid()) {
                validCount++;
            }
        }
        return validCount;
    }

    public int countStrictValidPassports() {
        int validCount = 0;
        for (String passportLine : passportLines) {
            Passport passport = new Passport(passportLine.trim());
            if (passport.isValidStrict()) {
                validCount++;
            }
        }
        return validCount;
    }

}

class Passport {

    String byr;
    String iyr;
    String eyr;
    String hgt;
    String hcl;
    String ecl;
    String pid;
    String cid;

    public Passport(String line) {
        String[] fields = line.split(" ");
        for (String field : fields) {
            String[] keyValue = field.split(":");
            switch (keyValue[0]) {
                case "byr" -> this.byr = keyValue[1];
                case "iyr" -> this.iyr = keyValue[1];
                case "eyr" -> this.eyr = keyValue[1];
                case "hgt" -> this.hgt = keyValue[1];
                case "hcl" -> this.hcl = keyValue[1];
                case "ecl" -> this.ecl = keyValue[1];
                case "pid" -> this.pid = keyValue[1];
                case "cid" -> this.cid = keyValue[1];
            }
        }
    }

    public boolean isValid() {
        return byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null && pid != null;
    }

    public boolean isValidStrict() {
        return isByrValid() && isIyrValid() && isEyrValid() && isHgtValid() && isHclValid() && isEclValid() && isPidValid();
    }

    private boolean isByrValid() {
        if (byr == null) return false;
        int year = Integer.parseInt(byr);
        return year >= 1920 && year <= 2002;
    }

    private boolean isIyrValid() {
        if (iyr == null) return false;
        int year = Integer.parseInt(iyr);
        return year >= 2010 && year <= 2020;
    }

    private boolean isEyrValid() {
        if (eyr == null) return false;
        int year = Integer.parseInt(eyr);
        return year >= 2020 && year <= 2030;
    }

    private boolean isHgtValid() {
        if (hgt == null) return false;
        if (hgt.endsWith("cm")) {
            int height = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
            return height >= 150 && height <= 193;
        } else if (hgt.endsWith("in")) {
            int height = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
            return height >= 59 && height <= 76;
        }
        return false;
    }

    private boolean isHclValid() {
        if (hcl == null) return false;
        if (!hcl.startsWith("#")) return false;
        if (hcl.length() != 7) return false;
        try {
            Integer.parseInt(hcl.substring(1), 16);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isEclValid() {
        if (ecl == null) return false;
        switch (ecl) {
            case "amb":
            case "blu":
            case "brn":
            case "gry":
            case "grn":
            case "hzl":
            case "oth":
                return true;
            default:
                return false;
        }
    }

    private boolean isPidValid() {
        if (pid == null) return false;
        if (pid.length() != 9) return false;
        try {
            Long.parseLong(pid);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
