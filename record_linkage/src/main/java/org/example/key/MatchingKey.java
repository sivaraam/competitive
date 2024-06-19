package org.example.key;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The class that represents the matching key corresponding to the
 * {@link org.example.data.CustomerData}. In this case the
 * matching key is phoneNumber or email.
 */
@Data
@AllArgsConstructor
public class MatchingKey {
    private String phoneNumber, email;

    /**
     * Returns true if the searchKey matches with this instance.
     * A key matches with this when the phone number or the email matches.
     */
    public boolean matchesWith(MatchingKey searchKey) {
        boolean phoneNumberMatches =
                phoneNumber != null && searchKey.phoneNumber != null &&
                phoneNumber.equals(searchKey.phoneNumber);

        boolean emailMatches =
                email != null && searchKey.email != null &&
                email.equals(searchKey.email);

        return  (phoneNumberMatches || emailMatches);
    }

}
