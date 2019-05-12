#include <iostream>
#include <vector>
#include <set>
#include <numeric>

using namespace std;

int main() {
    int T;
    vector<int> inputs;

    // Get the number of test cases
    cin >> T;
    inputs = vector<int>(T);

    // Get the inputs for the test cases
    for (int i = 0; i < T; i++)
        cin >> inputs[i];

    while (T--)
    {
        int N;
        multiset<long int> L;
        vector<long int> initial_vect;
        
        N = inputs.front();
        inputs.erase(inputs.begin());

        initial_vect = vector<long int>(N);
        
        std::iota(initial_vect.begin(), initial_vect.end(), 1);
        L = multiset<long int>(initial_vect.begin(), initial_vect.end());
        // L.insert(10);
        // L.insert(6);

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