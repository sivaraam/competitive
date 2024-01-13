1. The idea is that if occupies position, occupies position and so on, then there
will be some integer which will occupy position. So, this forms a cycle.

2. So, if any element is not at its correct position, we shift it to its correct
position, then shift to its correct position and so on. So, if is the length of
the cycle (number of elements in the cycle), then it will require a minimum of
swaps to rearrange the elements of the cycle to their correct positions.

3. We find all such cycles and compute our answer.

The correct positions of all the elements can be found by sorting the array by
value and keeping track of the old and new positions. You may gain more clarity
by the setters solution.

Problem Setter's code:

```cpp
#include<bits/stdc++.h>
using namespace std;

int a[100005];
bool visited[100005];

int solve(int n)
{
    pair<int, int> p[n];
    
    for (int i = 0; i < n; i++)
    {
        p[i].first = a[i];
        
        // Storing the original position of a[i]
        p[i].second = i;
    }
    
    sort(p, p+n);
    int ans = 0;
    
    for (int i = 0; i < n; i++)
    {	
    	//visited[i]=true indicates that index i belongs to a cycle that is already counted
        
        //p[i].second = i denotes that the ith element was at its correct position
        
        if (visited[i] || p[i].second == i)
            continue;
            
        int cycle_size = 0;
        int j = i;
        
        //Counting the size of the cycle
        while (!visited[j])
        {
            visited[j] = 1;
            j = p[j].second;
            cycle_size++;
        }
        
        ans += (cycle_size - 1);
    }
    
    return ans;
    
}

int main()
{

    int n;
    scanf("%d", &n);
    
    for(int i = 0; i < n; i++)
    {
        scanf("%d", &a[i]);
    }
    
    int ans = solve(n);
    printf("%d\n", ans);
    return 0;
    
}
```
