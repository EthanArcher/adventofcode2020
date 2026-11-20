package day21;

import utils.FileReader;

import java.util.*;
import java.util.stream.Collectors;

public class Day21 {

    final List<Food> foodList = new ArrayList<>();
    // allergen -> ingredients that could contain it (in every food listing that allergen)
    final Map<String, Set<String>> allergenCandidates = new HashMap<>();

    public Day21(String filename) {
        for (String line : FileReader.readFileToString(filename)) {
            String[] parts = line.split("[()]");
            Food food = new Food(
                    Arrays.stream(parts[0].trim().split(" ")).map(String::trim).collect(Collectors.toSet()),
                    Arrays.stream(parts[1].replace("contains", "").trim().split(",")).map(String::trim).collect(Collectors.toSet()));
            foodList.add(food);

            for (String allergen : food.allergens()) {
                allergenCandidates.computeIfAbsent(allergen, k -> new HashSet<>(food.ingredients()))
                        .retainAll(food.ingredients());
            }
        }
    }

    /**
     * Count how many times ingredients that can't contain any allergen appear across all foods.
     */
    public long part1() {
        Set<String> possibleAllergens = new HashSet<>();
        allergenCandidates.values().forEach(possibleAllergens::addAll);

        return foodList.stream()
                .flatMap(food -> food.ingredients().stream())
                .filter(ingredient -> !possibleAllergens.contains(ingredient))
                .count();
    }

    /**
     * Work out which ingredient holds each allergen, then list the ingredients sorted by allergen.
     */
    public String part2() {
        // copy the candidate sets so part2 doesn't change the shared state
        Map<String, Set<String>> remaining = new HashMap<>();
        allergenCandidates.forEach((allergen, candidates) -> remaining.put(allergen, new HashSet<>(candidates)));

        Map<String, String> allergenToIngredient = new HashMap<>();

        while (!remaining.isEmpty()) {
            Set<String> solvedAllergens = new HashSet<>();
            Set<String> foundIngredients = new HashSet<>();
            for (Map.Entry<String, Set<String>> entry : remaining.entrySet()) {
                if (entry.getValue().size() == 1) {
                    String ingredient = entry.getValue().iterator().next();
                    allergenToIngredient.put(entry.getKey(), ingredient);
                    solvedAllergens.add(entry.getKey());
                    foundIngredients.add(ingredient);
                }
            }

            if (solvedAllergens.isEmpty()) {
                throw new IllegalStateException("Can't narrow down allergens any further: " + remaining);
            }

            solvedAllergens.forEach(remaining::remove);
            for (Set<String> candidates : remaining.values()) {
                candidates.removeAll(foundIngredients);
            }
        }

        return allergenToIngredient.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.joining(","));
    }

    static Set<String> intersection(Collection<String> a, Collection<String> b) {
        Set<String> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }
}

record Food(Set<String> ingredients, Set<String> allergens) {
}
