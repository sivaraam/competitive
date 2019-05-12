#include <iostream>
#include <set>

using namespace std;

int main() {
    int T;

    // Get the number of test cases
    cin >> T;

    while (T--)
    {
        int N;
        multiset<long int> L;

        // Get the input
        cin >> N;

        for (int i = 1; i <= N; i++)
            L.insert(i);

        N--; // we want to do this N-1 times
        while (N--)
        {
            static const long int mod = 1000000007;

            multiset<long int>::iterator it = L.begin();
            multiset<long int>::iterator end_it = L.end();

            std::advance(it, 0);
            long int X = *(it);
            L.erase(it);

            std::advance(end_it, -1);
            long int Y = *(end_it);
            L.erase(end_it);

            L.insert((X + Y + X*Y)%mod);
        }

        multiset<long int>::iterator it = L.begin();
        std::advance(it, 0);
        long int result = *(it);
        cout << result << '\n';

        // for (int elem : L)
        //     cout << elem << ' ';
        // cout << '\n';
    }
}