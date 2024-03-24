import java.util.*;

// TODO: Document the solution a bit.
class MyCharacter {
    char ch;
    int pos;

    public MyCharacter(char value, int position) {
        ch = value;
        pos = position; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyCharacter that = (MyCharacter) o;
        return ch == that.ch && pos == that.pos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ch, pos);
    }
}

class Solution {

    /*
     * Complete the 'makeAnagram' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */

    private static Set<MyCharacter> charSet(String str) {
        List<MyCharacter> chars = new ArrayList<>();
        // map of a character to the count of the same in the string
        Map<Character, Integer> charCounts = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            Integer prevCount = charCounts.putIfAbsent(ch, 1);
            int count;
            if (prevCount == null) {
                count = 1;
            }
            else {
                count = prevCount+1;
                charCounts.put(ch, count);
            }

            chars.add(new MyCharacter(ch, count));
        }
        return new HashSet<>(chars);
    }

    public static int makeAnagram(String a, String b) {
        Set<MyCharacter> aCharSet = charSet(a),
                       bCharSet = charSet(b);

        Set<MyCharacter> aDiffB = new HashSet<>(aCharSet),
                       bDiffA = new HashSet<>(bCharSet); // use the copy constructor
        aDiffB.removeAll(bCharSet);
        bDiffA.removeAll(aCharSet);

        Set<MyCharacter> differences = new HashSet<>(aDiffB);
        differences.addAll(bDiffA);

        return differences.size();
    }


    public static void main(String[] args) {
        System.out.println("Make anagram: " + makeAnagram("acf", "cde"));
    }
}
