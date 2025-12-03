package org.sivaraam.cassidoo.oct20_2025;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string str and an array of positive integers widths,
 * write a function that splits the string into lines, each with
 * the exact number of characters as specified by the corresponding
 * width. Return an array of the substrings. Use the last width for
 * any remaining characters if the array is shorter than needed.
 * <p>
 * <pre>
 *   const str = "Supercalifragilisticexpialidocious";
 *   const widths = [5, 9, 4];
 *
 *   > splitByWidths(str, widths);
 *   > ['Super', 'califragi', 'list', 'icex', 'pial', 'idoc', 'ious']
 * </pre>
 */
// TODO: Try to achieve this via streams.
public class SplitStrings {
    public static void main(String[] args) {
        String str = "Supercalifragilisticexpialidocious";
        var widths = List.of(5, 9, 4);

        System.out.println("Words split as per given widths: " + splitString(str, widths));
    }

    public static List<String> splitString(String str, List<Integer> widths) {
        var words = new ArrayList<String>();
        var currentStringIndex = 0;
        var currentWidthIndex = 0;
        var stringLength = str.length();

        if (widths == null || widths.isEmpty()) {
            return words;
        }

        while (true) {
            StringBuilder buf = new StringBuilder();
            int widthUsed = 0;

            while (currentStringIndex < stringLength && widthUsed < widths.get(currentWidthIndex)) {
                // TODO: Need to work on proper access for
                // dialects and other languages
                // buf.append(str.codePointAt(currentStringIndex));

                buf.append(str.charAt(currentStringIndex));
                currentStringIndex++;
                widthUsed++;
            }

            if (!buf.isEmpty()) {
                words.add(buf.toString());
            }

            if (currentStringIndex >= stringLength) {
                break;
            }

            if (currentWidthIndex < widths.size() - 1 && widthUsed == widths.get(currentWidthIndex)) {
                currentWidthIndex++;
            }
        }

        return words;
    }

}
