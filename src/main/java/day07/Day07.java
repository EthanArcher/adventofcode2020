package day07;

import utils.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day07 {

    final List<String> lines;
    private HashMap<String, HashMap<String, Integer>> bagRules = new HashMap<>();

    public Day07(String filename) {
        lines = FileReader.readFileToString(filename);

        for (String line: lines) {
            HashMap<String, Integer> contentMap = new HashMap<>();
            String[] bags = line.split("bags contain");
            String bag = bags[0].trim();
            String[] contents = bags[1].split(",");

            if (contents[0].contains("no other bags")) {
                bagRules.put(bag, new HashMap<>());
                continue;
            }

            for (String content: contents) {
                String contentTrimmed = content.trim().split("bag")[0].trim();
                contentMap.put(contentTrimmed.substring(2).trim(), Integer.parseInt(contentTrimmed.substring(0, 1)));
            }
            bagRules.put(bag, contentMap);

        }
    }

    public HashMap<String, Integer> whatsInTheBag(String bagName) {
        // create a contents map that holds what in the bag
        HashMap<String, Integer> contents = new HashMap<>();
        // add this bag to the list of contents
        contents.put(bagName, 1);

        // get what's in this bag
        HashMap<String, Integer> inThisBag = bagRules.get(bagName);

        // if there is nothing in this bag, just return this bag
        if (inThisBag.isEmpty()) {
            return contents;
        } else {
            // for each bag in the bag, find out what's in it
            for (String bag : inThisBag.keySet()) {

                HashMap<String, Integer> contentsOfBag = whatsInTheBag(bag);

                // add the contents of that bag to the contents of the parent bag
                for (String innerBag : contentsOfBag.keySet()) {

                    // contents add the innerbag the number of times
                    contents.put(innerBag, contents.getOrDefault(innerBag, 0) + (contentsOfBag.get(innerBag) * inThisBag.get(bag)));
                }

            }
            return contents;
        }
    }

    public int totalBagsIn(String bag) {
        int count = 0;

        HashMap<String, Integer> inTheBag = whatsInTheBag(bag);

        for (String eachBag : inTheBag.keySet()) {
            count += inTheBag.get(eachBag);
        }
        // minus 1 don't count the outer bag
        return count - 1;
    }

    public int bagsThatContain(String bag) {
        int count = 0;
        for (String eachBag: bagRules.keySet()) {
            if (eachBag.equals(bag)) {
                continue;
            }

            HashMap<String, Integer> bagContents = whatsInTheBag(eachBag);
            if (bagContents.containsKey(bag)) {
                count++;
            }
        }
        return count;
    }

}