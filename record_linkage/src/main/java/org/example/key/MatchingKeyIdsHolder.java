package org.example.key;

import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class that holds the unique IDs corresponding to a set of
 * matching keys.
 */
public class MatchingKeyIdsHolder {

    /*
     * Holds the mapping of each set of matching keys to their unique IDs.
     */
    private static final HashMap<Set<MatchingKey>, Long> uniqueIds = new HashMap<>();

    public static Map.Entry<Set<MatchingKey>, Long> getMatchingEntry(@NonNull MatchingKey searchKey) {
        List<Map.Entry<Set<MatchingKey>, Long>> matchedKeys =
                    uniqueIds.entrySet()
                             .stream()
                             .filter(
                                (existingEntry) -> existingEntry.getKey().stream().anyMatch(existingKey -> existingKey.matchesWith(searchKey))
                             )
                            .collect(Collectors.toList());

        int matchedKeyCount = matchedKeys.size();
        if (matchedKeyCount > 1) {
            throw new RuntimeException("Strangely more than one set of matching keys matched.");
        }
        else if (matchedKeyCount == 1) {
            return matchedKeys.get(0);
        }
        else {
            return null;
        }
    }

    public static void insertNewKey(MatchingKey key, long newId) {
        Set<MatchingKey> newSet = new HashSet<>();
        newSet.add(key);

        // NOTE: We could validate here the non-existence of this new set
        // in the uniqueIds map but let us leave this for later.
        //
        // For the note, this would require us to iterate over each item
        // in the HashMap, verify if there exists and existing set of
        // which the new set is a subset.

        uniqueIds.put(newSet, newId);
    }

    public static void addKeyToSet(Set<MatchingKey> key, MatchingKey currCustomerKey) {
        Objects.requireNonNull(key);
        key.add(currCustomerKey);
    }
}
