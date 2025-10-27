package org.sivaraam.cassidoo.oct20_2025;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SplitStringsTest {
    @Test
    public void splitStringWithExactWidths() {
        String str = "Supercalifragilisticexpialidocious";
        List<Integer> widths = List.of(5, 9, 4);
        List<String> result = SplitStrings.splitString(str, widths);
        assertEquals(List.of("Super", "califragi", "list", "icex", "pial", "idoc", "ious"), result);
    }

    @Test
    public void splitStringWithSingleWidth() {
        String str = "Supercalifragilisticexpialidocious";
        List<Integer> widths = List.of(10);
        List<String> result = SplitStrings.splitString(str, widths);
        assertEquals(List.of("Supercalif", "ragilistic", "expialidoc", "ious"), result);
    }

    @Test
    public void splitStringWithEmptyString() {
        String str = "";
        List<Integer> widths = List.of(5, 9, 4);
        List<String> result = SplitStrings.splitString(str, widths);
        assertEquals(List.of(), result);
    }

    @Test
    public void splitStringWithEmptyWidths() {
        String str = "Supercalifragilisticexpialidocious";
        List<Integer> widths = List.of();
        List<String> result = SplitStrings.splitString(str, widths);
        assertEquals(List.of(), result);
    }

    @Test
    public void splitStringWithWidthsExceedingStringLength() {
        String str = "Hello";
        List<Integer> widths = List.of(10, 15);
        List<String> result = SplitStrings.splitString(str, widths);
        assertEquals(List.of("Hello"), result);
    }

    @Test
    public void splitStringWithWidthsShorterThanStringLength() {
        String str = "HelloWorld";
        List<Integer> widths = List.of(3, 2);
        List<String> result = SplitStrings.splitString(str, widths);
        assertEquals(List.of("Hel", "lo", "Wo", "rl", "d"), result);
    }

    @Test
    public void splitStringWithNonUniformWidths() {
        String str = "abcdefghij";
        List<Integer> widths = List.of(3, 5, 2);
        List<String> result = SplitStrings.splitString(str, widths);
        assertEquals(List.of("abc", "defgh", "ij"), result);
    }
}
