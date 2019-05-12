#include <iostream>
#include <set>

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

            reduced = X + Y * X*Y;
            L.insert(reduced % mod);
        }

        it = L.begin();
        cout << *(it) << '\n'; // result
    }
}