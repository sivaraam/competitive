package org.example;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import org.example.data.CustomerData;
import org.jline.builtins.Completers;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReplDemo {
    private static List<CustomerData> customers = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Terminal terminal = TerminalBuilder.builder().build();
            // Create a FileNameCompleter for file name auto-completion
            Completer fileNameCompleter = new Completers.FileNameCompleter();

            LineReader reader = LineReaderBuilder.builder()
                                                 .terminal(terminal)
                                                 .completer(fileNameCompleter)
                                                 .build();

            while (true) {
                printMenu(terminal);
                String option = reader.readLine("Select an option: ");

                switch (option) {
                    case "1":
                        String filePath = reader.readLine("Enter CSV file path: ");
                        parseCsv(filePath.trim(), terminal);
                        break;
                    case "2":
                        printCsv(terminal);
                        break;
                    case "q":
                        terminal.writer().println("Exiting...");
                        terminal.flush();
                        return;
                    default:
                        terminal.writer().println("Invalid option. Please try again.");
                        terminal.flush();
                }

                assignUniqueIds();
            }
        } catch (IOException e) {
            System.err.println("Failed to get a terminal. Exiting.");
            System.exit(1);
        }
    }

    private static void assignUniqueIds() {
        int newUniqueId = 0;

        for (int i = 0; i < customers.size(); i++) {
            List<Integer> uniqueCustomers = new ArrayList<>();
            CustomerData cust1 = customers.get(i);
            uniqueCustomers.add(i);
            int uniqueId;

            if (cust1.getCustomerId() == null) {
                uniqueId = ++newUniqueId;
            }
            else {
                uniqueId = cust1.getCustomerId();
            }

            for (int j = i + 1; j < customers.size(); j++) {
                CustomerData cust2 = customers.get(j);
                if (cust2.getCustomerId() == null && CustomerData.doesMatch(cust1, cust2)) {
                    uniqueCustomers.add(j);
                }
            }

            for (int uniqueCustomerIndex : uniqueCustomers) {
                CustomerData uniqueCustomer = customers.get(uniqueCustomerIndex);
                uniqueCustomer.setCustomerId(uniqueId);
            }
        }
    }

    private static void printMenu(Terminal terminal) {
        terminal.writer().println("Menu:");
        terminal.writer().println("1. Parse a CSV file");
        terminal.writer().println("2. Print the CSV");
        terminal.writer().println("q. Quit");
        terminal.flush();
    }

    private static void parseCsv(String filePath, Terminal terminal) {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            terminal.writer().println("File does not exist.");
            terminal.flush();
            return;
        }

        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(path)) {
            customers = csv.stream()
                            .map(CustomerData::constructFromCsvRecords)
                            .collect(Collectors.toList());

            // Throw away the header
            customers.remove(0);

            terminal.writer().println("CSV file parsed successfully.");
            terminal.flush();
        } catch (IOException e) {
            terminal.writer().println("Error reading the CSV file: " + e.getMessage());
            terminal.flush();
        }
    }

    private static void printCsv(Terminal terminal) {
        if (customers.isEmpty()) {
            terminal.writer().println("No CSV data available. Please parse a CSV file first.");
            terminal.flush();
            return;
        }

        terminal.writer().println("CSV Data:");
        customers.forEach(row -> terminal.writer().println(row.toCsvLine()));
        terminal.flush();
    }
}