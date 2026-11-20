package day21;

import utils.FileReader;

import java.util.*;
import java.util.stream.Collectors;

public class Day21 {

    final List<String> lines;
    HashMap<String, List<Set<String>>> allergens = new HashMap<>();
    List<Food> foodList = new ArrayList<>();

    public Day21(String filename) {
        lines = FileReader.readFileToString(filename);
        List<String> allIngredients = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split("[()]");
            Food food = new Food(Arrays.stream(parts[0].trim().split(" ")).map(String::trim).collect(Collectors.toSet()),
                    Arrays.stream(parts[1].replace("contains", "").trim().split(",")).map(String::trim).collect(Collectors.toSet()));
            foodList.add(food);

            allIngredients.addAll(food.ingredients());

            for (String allergen : food.allergens()) {
                allergens.computeIfAbsent(allergen, k -> new ArrayList<>())
                        .add(food.ingredients());
            }
        }

        Set<String> possibleIngredients = new HashSet<>();
        HashMap<String, Set<String>> allergenIngredientMap = new HashMap<>();

        for (String allergen : allergens.keySet()) {
            List<Set<String>> ingredientLists = allergens.get(allergen);
            Set<String> intersection = ingredientLists.get(0);
            for (Set<String> ingredients : ingredientLists) {
                intersection = intersection(intersection, ingredients);
            }
            System.out.println(allergen + ": " + intersection);
            allergenIngredientMap.put(allergen, intersection);
            possibleIngredients.addAll(intersection);
            allIngredients.removeAll(intersection);
        }

        System.out.println(possibleIngredients);
        System.out.println(allIngredients);
        System.out.println(allIngredients.size());

        System.out.println(allergenIngredientMap);

        HashMap<String, String> allergens = new HashMap<>();

        while (allergenIngredientMap.size() > 0) {
            Set<String> solvedAllergens = new HashSet<>();
            Set<String> toRemove = new HashSet<>();
            for (String allergen : allergenIngredientMap.keySet()) {
                if (allergenIngredientMap.get(allergen).size() == 1) {
                    String r = allergenIngredientMap.get(allergen).stream().findFirst().orElseThrow();
                    allergens.put(allergen, r);
                    toRemove.add(r);
                    solvedAllergens.add(allergen);
                }
            }

            solvedAllergens.forEach(allergenIngredientMap::remove);

            for (Set<String> ingredients : allergenIngredientMap.values()) {
                ingredients.removeAll(toRemove);
            }

        }

        System.out.println(allergens);

        String answer = allergens.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.joining(","));

        System.out.println(answer);


    }

    static Set<String> intersection(Collection<String> a, Collection<String> b) {
        Set<String> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }
}

record Food(Set<String> ingredients, Set<String> allergens) {
}
