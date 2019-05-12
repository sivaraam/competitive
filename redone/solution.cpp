#include <iostream>
#include <set>

/*
 * The solution basically relies on the fact that
 * we use a sorted contianer (multiset) to hold the
 * set of values as a consequence of which we could take
 * first and second values of the set as X and Y correspondingly.
 * On iterating in this way helps us achieve the required
 * solution but the program seems to be taking more time
 * than it should as a consequence of which we face the
 * Time Limit Exceeded (TLE) error for larger inputs.
 *
 * The TLE might be due to the container we use (multiset) which
 * keeps the set sorted. Need to identify how to improve the
 * performance. May be parallelizing this or finding a better
 * container might help.
 */

using namespace std;

int main() {
    static const long int mod = 1000000007;
    int N, T;
    long int X, Y, reduced;
    multiset<int> L;
    multiset<int>::iterator it;

    // Get the number of test cases
    cin >> T;

    while (T--)
    {
        L.clear();

        // Get the input
        cin >> N;

        for (int i = 1; i <= N; i++)
            L.insert(i);

        N--; // we want to do this N-1 times
        while (N--)
        {
            it = L.begin();

            X = *(it);
            L.erase(it);

            std::advance(it, 1);
            Y = *(it);
            L.erase(it);

            reduced = X + Y * (1 + X);
            L.insert(reduced % mod);
        }

        it = L.begin();
        cout << *(it) << '\n'; // result
    }
}