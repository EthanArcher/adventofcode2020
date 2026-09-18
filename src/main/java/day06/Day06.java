package day06;

import utils.FileReader;

import java.util.*;

public class Day06 {

    final List<String> lines;
    List<Group> groups = new ArrayList<>();

    public Day06(String filename) {
        lines = FileReader.readFileToString(filename);

        List<String> groupMembers = new ArrayList<>();

        for (String line : lines) {
            if (line.isEmpty()) {
                groups.add(new Group(groupMembers));
                groupMembers = new ArrayList<>();
            } else {
                groupMembers.add(line);
            }
        }
        groups.add(new Group(groupMembers));

    }

    public int sumUniqueAnswers() {
        int sum = 0;
        for (Group group : groups) {
            sum += group.countUniqueAnswers();
        }
        return sum;
    }

    public int sumCommonAnswers() {
        int sum = 0;
        for (Group group : groups) {
            sum += group.countCommonAnswers();
        }
        return sum;
    }

}

class Group {
    private Set<Character> uniqueAnswers = new HashSet<>();
    private List<String> groupMembers;
    private int groupSize;
    private HashMap<Character, Integer> answerCounts = new HashMap<>();

    public Group(List<String> groupMembers) {
        this.groupMembers = groupMembers;
        this.groupSize = groupMembers.size();
        for (String member : groupMembers) {
            for (char answer: member.toCharArray()) {
                uniqueAnswers.add(answer);
                answerCounts.put(answer, answerCounts.getOrDefault(answer, 0) + 1);
            }
        }
    }

    public int countUniqueAnswers() {
        return uniqueAnswers.size();
    }

    public int countCommonAnswers() {
        int count = 0;
        for (Map.Entry<Character, Integer> entry : answerCounts.entrySet()) {
            if (entry.getValue() == groupSize) {
                count++;
            }
        }
        return count;
    }

}