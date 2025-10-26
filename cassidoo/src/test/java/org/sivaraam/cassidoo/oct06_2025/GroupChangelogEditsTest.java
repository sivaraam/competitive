
package org.sivaraam.cassidoo.oct06_2025;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupChangelogEditsTest {
    @Test
    public void groupChangelogEditsWithMultipleComponents() {
        var edits = List.of(
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:00:00Z", "Header"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:05:00Z", "Header"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:10:00Z", "Footer"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:15:00Z", "Footer")
        );

        var groupedEdits = GroupChangelogEdits.groupChangelogEdits(edits);

        assertEquals(2, groupedEdits.size());
        assertEquals("Header", groupedEdits.get(0).component());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:00:00Z"), groupedEdits.get(0).startTime());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:05:00Z"), groupedEdits.get(0).endTime());
        assertEquals("Footer", groupedEdits.get(1).component());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:10:00Z"), groupedEdits.get(1).startTime());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:15:00Z"), groupedEdits.get(1).endTime());
    }

    @Test
    public void groupChangelogEditsWithIdenticalTimestamps() {
        var edits = List.of(
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:00:00Z", "Header"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:00:00Z", "Header"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:00:00Z", "Footer")
        );

        var groupedEdits = GroupChangelogEdits.groupChangelogEdits(edits);

        assertEquals(2, groupedEdits.size());
        assertEquals("Header", groupedEdits.get(0).component());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:00:00Z"), groupedEdits.get(0).startTime());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:00:00Z"), groupedEdits.get(0).endTime());
        assertEquals("Footer", groupedEdits.get(1).component());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:00:00Z"), groupedEdits.get(1).startTime());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:00:00Z"), groupedEdits.get(1).endTime());
    }

    @Test
    public void groupChangelogEditsWithNonChronologicalInput() {
        var edits = List.of(
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:10:00Z", "Header"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:00:00Z", "Header"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:05:00Z", "Header")
        );

        var groupedEdits = GroupChangelogEdits.groupChangelogEdits(edits);

        assertEquals(1, groupedEdits.size());
        assertEquals("Header", groupedEdits.get(0).component());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:00:00Z"), groupedEdits.get(0).startTime());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:10:00Z"), groupedEdits.get(0).endTime());
    }

    @Test
    public void groupChangelogEditsWithLargeTimeGap() {
        var edits = List.of(
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:00:00Z", "Header"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T09:00:00Z", "Header")
        );

        var groupedEdits = GroupChangelogEdits.groupChangelogEdits(edits);

        assertEquals(2, groupedEdits.size());
        assertEquals("Header", groupedEdits.get(0).component());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:00:00Z"), groupedEdits.get(0).startTime());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:00:00Z"), groupedEdits.get(0).endTime());
        assertEquals("Header", groupedEdits.get(1).component());
        assertEquals(ZonedDateTime.parse("2025-10-06T09:00:00Z"), groupedEdits.get(1).startTime());
        assertEquals(ZonedDateTime.parse("2025-10-06T09:00:00Z"), groupedEdits.get(1).endTime());
    }

    @Test
    public void groupChangelogEditsWithThreeComponents() {
        var edits = List.of(
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:00:00Z", "Header"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:05:00Z", "Header"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:08:00Z", "Header"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:20:00Z", "Header"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:07:00Z", "Footer"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:15:00Z", "Footer"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:09:00Z", "Footer"),
                new GroupChangelogEdits.RawChangelog("2025-10-06T08:10:00Z", "Body")
        );

        var groupedEdits = GroupChangelogEdits.groupChangelogEdits(edits);

        assertEquals(4, groupedEdits.size());
        assertEquals("Header", groupedEdits.get(0).component());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:00:00Z"), groupedEdits.get(0).startTime());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:08:00Z"), groupedEdits.get(0).endTime());
        assertEquals("Header", groupedEdits.get(1).component());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:20:00Z"), groupedEdits.get(1).startTime());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:20:00Z"), groupedEdits.get(1).endTime());
        assertEquals("Footer", groupedEdits.get(2).component());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:07:00Z"), groupedEdits.get(2).startTime());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:15:00Z"), groupedEdits.get(2).endTime());
        assertEquals("Body", groupedEdits.get(3).component());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:10:00Z"), groupedEdits.get(3).startTime());
        assertEquals(ZonedDateTime.parse("2025-10-06T08:10:00Z"), groupedEdits.get(3).endTime());
    }
}
