import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * WIP
 */
public class CarFleet {

    public static void main(String[] args) {
//      Initially failed
//        int target = 12;
//        int[] position = new int[] { 10,8,0,5,3 };
//        int[] speed = new int[] { 2,4,1,1,3 };

//        int target = 10;
//        int[] position = new int[] { 3 };
//        int[] speed = new int[] { 3 };

//        int target = 100;
//        int[] position = new int[] { 0, 2, 4 };
//        int[] speed = new int[] { 4, 2, 1 };

//        Failed when submitted
//        int target = 20;
//        int[] position = new int[] { 6, 2, 17 };
//        int[] speed = new int[] { 3, 9, 2 };

        int target = 13;
        int[] position = new int[] { 10,2,5,7,4,6,11 };
        int[] speed = new int[] { 7,5,10,5,9,4,1 };

        System.out.println("Total fleets: " + carFleet(target, position, speed));
    }

    public static int carFleet(int target, int[] position, int[] speed) {
        Map<Integer, Integer> fleetLocVsSpeed = new HashMap<>();
        Map<Integer, Integer> newLocVsSpeed = new HashMap<>();
        int fleets = 0;

        for (int carIndex = 0; carIndex < position.length; carIndex++) {
            fleetLocVsSpeed.put(position[carIndex], speed[carIndex]);
        }

        for (int i = 1; i <= target + 1; i++) {

            Iterator<Map.Entry<Integer, Integer>> fleetCarIterator = fleetLocVsSpeed.entrySet().iterator();
            while (fleetCarIterator.hasNext()) {
                Map.Entry<Integer, Integer> fleetCar = fleetCarIterator.next();
                int currentFleetPosition = fleetCar.getKey();
                int currentFleetSpeed = fleetCar.getValue();
                if (currentFleetPosition >= target) {
                    fleets++;
                    fleetCarIterator.remove();
                    continue;
                }

                int newPosition = currentFleetPosition + currentFleetSpeed;
                boolean needToInsertFleet = true;

                for (Map.Entry<Integer, Integer> otherNewFleet : newLocVsSpeed.entrySet()) {
                    int otherNewPosition = otherNewFleet.getKey();
                    int otherNewSpeed = otherNewFleet.getValue();
                    int otherOldPosition = otherNewPosition - otherNewSpeed;

                    if (
                        (currentFleetPosition < otherOldPosition && otherNewPosition <= newPosition)
//                        || (otherOldPosition < currentFleetPosition && newPosition <= otherNewPosition)
                    ) {
//                        if (otherNewSpeed > currentFleetSpeed) {
                            otherNewFleet.setValue(currentFleetSpeed);
//                        }

                        needToInsertFleet = false;
                        break;
                    }
                }

                if (needToInsertFleet) {
                    newLocVsSpeed.put(newPosition, currentFleetSpeed);
                }

                fleetCarIterator.remove();
            }

            Map<Integer, Integer> tempMap = fleetLocVsSpeed;
            fleetLocVsSpeed = newLocVsSpeed;
            tempMap.clear();
            newLocVsSpeed = tempMap;
        }

        return fleets;
    }
}
