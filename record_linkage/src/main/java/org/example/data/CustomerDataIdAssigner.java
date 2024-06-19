package org.example.data;

import org.example.key.MatchingKey;
import org.example.key.MatchingKeyIdsHolder;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class responsible for assigning the same unique IDs to all records that have
 * the same matching key. For instance, if the matching key is email OR phone, then
 * the same ID should be assigned to all set of rows for which the email match, the
 * phone number match or both of them match.

 */
public class CustomerDataIdAssigner {

    /**
     * Iterates each customer to identify if it matches with any matching key for which an
     * ID already exists. If so, assigns that ID to the customer and adds the customer's
     * matching key to the set of matching keys corresponding to that ID.
     * <p>
     * If a matching key set doesn't exist, a new set is created with the customer's
     * matching key and a new unique ID is mapped to it.
     *
     * @param customers the list of customers to whom the unique ID needs to be assigned.
     */
    public static void assignUniqueIds(List<CustomerData> customers) {
        int newUniqueId = 1;

        for (CustomerData currCustomer : customers) {
            long currCustomerId;
            MatchingKey currCustomerKey = currCustomer.constructMatchingKey();

            Map.Entry<Set<MatchingKey>, Long> matchingEntry = MatchingKeyIdsHolder.getMatchingEntry(currCustomerKey);
            if (matchingEntry == null) {
                currCustomerId = newUniqueId;
                MatchingKeyIdsHolder.insertNewKey(currCustomerKey, newUniqueId++);
            } else {
                currCustomerId = matchingEntry.getValue();
                MatchingKeyIdsHolder.addKeyToSet(matchingEntry.getKey(), currCustomerKey);
            }

            currCustomer.setCustomerId(currCustomerId);
        }
    }

}
