package com.yakub;

import java.io.*;

public class Utility {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("adj.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            String line;
            // Read each line from the file until there are no more lines
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String normalizedText = removeDiacritics(line);
                    writer.write(normalizedText);
                    writer.newLine(); // Add a newline after each line if needed
                }
            }
            System.out.println("Lines copied to output.txt successfully.");
        } catch (IOException e) {
            // Handle any IO exceptions that occur during reading or writing
            e.printStackTrace();
        }
    }
    public static String removeDiacritics(String text) {
        // Replace accented characters with their base characters
        text = text.replace("́", ""); // Remove accent mark
        return text;
    }
}
