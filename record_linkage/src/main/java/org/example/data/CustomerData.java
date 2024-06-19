package org.example.data;

import de.siegmar.fastcsv.reader.CsvRecord;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.validator.routines.EmailValidator;
import org.example.key.MatchingKey;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that uniquely represents the represents a particular
 * CustomerData entry.
 */
@Data
@Builder
public class CustomerData {
    Long customerId;
    String firstName,
            lastName,
            phoneNumber,
            email,
            zipCode;

    public static CustomerData constructFromCsvRecords(CsvRecord customerValue) {
        CustomerDataBuilder newCustomer = CustomerData.builder();

        newCustomer.firstName(getOptionalField(customerValue, 0));
        newCustomer.lastName(getOptionalField(customerValue,1));

        String phone1 = getOptionalField(customerValue,2);
        newCustomer.phoneNumber(phone1);

        String email = getOptionalField(customerValue, 3);
        if (email != null) {
            boolean validEmail = EmailValidator.getInstance().isValid(email);
            if (validEmail) {
                newCustomer.email(email);
            } else {
                // Falback to Invalid value.
                newCustomer.email("N/A (error)");
            }
        }

        String zipCode = getOptionalField(customerValue, 4);
        newCustomer.zipCode(zipCode);

        return newCustomer.build();
    }

    private static String getOptionalField(CsvRecord record, int index) {
        if (!record.getField(index).isEmpty()) {
            return record.getField(index);
        }
        else {
            return null;
        }
    }

    public List<String> toCsvLine() {
        List<String> csvLine = new ArrayList<>();
        csvLine.add(firstName);
        csvLine.add(lastName);
        csvLine.add(phoneNumber);
        csvLine.add(email);
        csvLine.add(zipCode);
        if (customerId != null)
            csvLine.add(customerId.toString());

        return csvLine;
    }

    /**
     * Constructs the matching key instance corresponding to this
     * CustomerData instance.
     */
    public MatchingKey constructMatchingKey() {
        return new MatchingKey(phoneNumber, email);
    }

}

