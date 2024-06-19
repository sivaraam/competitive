package org.example.data;

import de.siegmar.fastcsv.reader.CsvRecord;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CustomerData {
    Integer customerId;
    String firstName,
            lastName,
            phoneNumber1,
            email,
            zipCode;

    public static CustomerData constructFromCsvRecords(CsvRecord customerValue) {
        CustomerDataBuilder newCustomer = CustomerData.builder();

        newCustomer.firstName(getOptionalField(customerValue, 0));
        newCustomer.lastName(getOptionalField(customerValue,1));

        // TODO: Need to think of a better way to handle
        //  exceptions that occur while parsing a phone number
        String phone1 = getOptionalField(customerValue,2);
        newCustomer.phoneNumber1(phone1);

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

        // TODO: Need to think of a better way to handle
        //  exceptions that occur while parsing a website
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

    /*
     * Returns true when the phone number or the last name matches.
     */
    public static boolean doesMatch(CustomerData instance1, CustomerData instance2) {
        boolean phoneNumberMatches =
                instance1.phoneNumber1 != null && instance2.phoneNumber1 != null &&
                instance1.phoneNumber1.equals(instance2.phoneNumber1);

        boolean emailMatches =
                instance1.email != null && instance2.email != null &&
                instance1.email.equals(instance2.email);

        return  (phoneNumberMatches || emailMatches);
    }

    public List<String> toCsvLine() {
        List<String> csvLine = new ArrayList<>();
        csvLine.add(firstName);
        csvLine.add(lastName);
        csvLine.add(phoneNumber1);
        csvLine.add(email);
        csvLine.add(zipCode);
        if (customerId != null)
            csvLine.add(customerId.toString());

        return csvLine;
    }
}

