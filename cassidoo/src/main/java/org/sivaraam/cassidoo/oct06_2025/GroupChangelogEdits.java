package org.sivaraam.cassidoo.oct06_2025;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * You're building a tool that tracks component edits and groups them into a changelog.
 * Given an array of edit actions, each with a timestamp and a component name, return
 * an array of grouped changelog entries. Edits to the same component within a 10-minute
 * window should be merged into one changelog entry, showing the component name and the
 * range of timestamps affected.
 * <p>
 * <pre>
 *  const edits = [
 *   { timestamp: "2025-10-06T08:00:00Z", component: "Header" },
 *   { timestamp: "2025-10-06T08:05:00Z", component: "Header" },
 *   { timestamp: "2025-10-06T08:20:00Z", component: "Header" },
 *   { timestamp: "2025-10-06T08:07:00Z", component: "Footer" },
 *   { timestamp: "2025-10-06T08:15:00Z", component: "Footer" },
 * ];
 *
 * > groupChangelogEdits(edits)
 * > [
 *     {
 *         "component": "Footer",
 *         "start": "2025-10-06T08:07:00Z",
 *         "end": "2025-10-06T08:15:00Z"
 *     },
 *     {
 *         "component": "Header",
 *         "start": "2025-10-06T08:00:00Z",
 *         "end": "2025-10-06T08:05:00Z"
 *     },
 *     {
 *         "component": "Header",
 *         "start": "2025-10-06T08:20:00Z",
 *         "end": "2025-10-06T08:20:00Z"
 *     }
 * ]
 * </pre>
 *
 * <b>Interesting thing learnt</b>
 * <ul>
 *     <li>
 *         ZonedDateTime is a good way to store the date-time in case
 *         it has timezone.
 *     </li>
 *     <li>
 *         Record is immutable. We could have <code>with</code>-er
 *         like methods to mutate the methods.
 *     </li>
 *     <li>
 *         ListIterator is a better way to <i>iterate</i> lists
 *         in case we need to modify the entry in the list in-place.
 *     </li>
 * </ul>
 *
 * <b>Miscellaneous</b>
 * Some related resources:
 * <ul>
 *     <li>
 *         <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html">ListIterator</a>
 *     </li>
 *     <li>
 *         <a href="https://claude.ai/share/c72265a6-debc-434f-8829-f6f7418d75b4">
*            Claude chat link for some things we had looked up and got good information on
*          </a>
 *     </li>
 * </ul>
 */
public class GroupChangelogEdits {

    public static void main(String[] args) {
        var edits = List.of(
            new RawChangelog("2025-10-06T08:00:00Z", "Header"),
            new RawChangelog("2025-10-06T08:05:00Z", "Header"),
            new RawChangelog("2025-10-06T08:20:00Z", "Header"),
            new RawChangelog("2025-10-06T08:07:00Z", "Footer"),
            new RawChangelog("2025-10-06T08:15:00Z", "Footer"),
            new RawChangelog("2025-10-06T08:09:00Z", "Footer"),
            new RawChangelog("2025-10-06T08:10:00Z", "Body")
        );

        var groupedEdits = groupChangelogEdits(edits);

        System.out.println("Grouped edits: ");
        for (var groupedEdit : groupedEdits) {
            System.out.println(groupedEdit);
        }
    }


    public static List<ChangelogGroup> groupChangelogEdits(List<RawChangelog> changelogEdits) {
        var parsedEdits = parseChangelogEdits(changelogEdits);
        var groupedEdits = new ArrayList<ChangelogGroup>();

        for (var ungroupedEdit : parsedEdits) {
            var groupedEditIterator = groupedEdits.listIterator();
            boolean entryHandledInExistingGroups = false;

            while (groupedEditIterator.hasNext()) {
                var groupedEdit = groupedEditIterator.next();

                if (groupedEdit.component.equalsIgnoreCase(ungroupedEdit.component)) {
                    DistanceAction distanceAction = checkEditDistance(groupedEdit, ungroupedEdit);

                    if (distanceAction == DistanceAction.SKIP) {
                        entryHandledInExistingGroups = true;
                        break;
                    } else if (distanceAction == DistanceAction.REPLACE_START) {
                        // In-place replacement in list
                        entryHandledInExistingGroups = true;
                        groupedEditIterator.set(
                            groupedEdit.withStartTime(ungroupedEdit.editTime)
                        );
                        break;
                    }
                    else if (distanceAction == DistanceAction.REPLACE_END) {
                        // In-place replacement in list
                        entryHandledInExistingGroups = true;
                        groupedEditIterator.set(
                            groupedEdit.withEndTime(ungroupedEdit.editTime)
                        );
                        break;
                    }
                }
            }

            if (!entryHandledInExistingGroups) {
                groupedEdits.add(
                    new ChangelogGroup(
                        ungroupedEdit.component,
                        ungroupedEdit.editTime,
                        ungroupedEdit.editTime
                    )
                );
            }
        }

        return groupedEdits;
    }

    /*
     * Assumed to invoked on a changelog group which has the
     * same component as the ungroupedEdit.
     */
    private static DistanceAction checkEditDistance(ChangelogGroup groupedEdit, Changelog ungroupedEdit) {
        if (ungroupedEdit.editTime.isEqual(groupedEdit.startTime) || ungroupedEdit.editTime.isEqual(groupedEdit.endTime)) {
            return DistanceAction.SKIP;
        }
        else if (ungroupedEdit.editTime.isAfter(groupedEdit.startTime)) {
            Duration fromStart = Duration.between(groupedEdit.startTime, ungroupedEdit.editTime);

            if (fromStart.toMinutes() <= 10) {
                if (ungroupedEdit.editTime.isAfter(groupedEdit.endTime)) {
                    return DistanceAction.REPLACE_END;
                }
                else {
                    return DistanceAction.SKIP;
                }
            }
            else {
                return DistanceAction.CHECK_NEXT_GROUP;
            }
        }
        else {
            // ungrouped edit time is before the start time
            Duration fromEnd = Duration.between(ungroupedEdit.editTime, groupedEdit.endTime);

            if (fromEnd.toMinutes() <= 10) {
                if (ungroupedEdit.editTime.isBefore(groupedEdit.startTime)) {
                    return DistanceAction.REPLACE_START;
                }
                else {
                    return DistanceAction.SKIP;
                }
            }
            else {
                return DistanceAction.CHECK_NEXT_GROUP;
            }
        }
    }

    private static List<Changelog> parseChangelogEdits(List<RawChangelog> edits) {
        var parsedEdits = new ArrayList<Changelog>();

        for (var edit : edits) {
            parsedEdits.add(new Changelog(ZonedDateTime.parse(edit.editTime), edit.component));
        }

        return parsedEdits;
    }

    /*
     * Data structures
     */

    /*
     * Used to represent the input we get.
     */
    record RawChangelog (String editTime, String component) { }

    /*
     * Used to get the input, process it and represent it in a way that's
     * easy to process.
     */
    record Changelog (ZonedDateTime editTime, String component) { }

    /*
     * Used to hold the final groups that's supposed to be output.
     */
    record ChangelogGroup (
            String component,
            ZonedDateTime startTime,
            ZonedDateTime endTime
    ) {
        ChangelogGroup withStartTime(ZonedDateTime startTime) {
            return new ChangelogGroup(this.component, startTime, this.endTime);
        }

        ChangelogGroup withEndTime(ZonedDateTime endTime) {
            return new ChangelogGroup(this.component, this.startTime, endTime);
        }

    }

    enum DistanceAction {
        CHECK_NEXT_GROUP,
        SKIP,
        REPLACE_START,
        REPLACE_END
    }

}
