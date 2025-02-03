This is a note of the various things related to competitive programming / doing interviews that I've gathered so far.

## Things to do

- [ ] [Advent of Code](https://adventofcode.com/2024/about) problems
  - [ ] Advent of Code solutions in Kotlin 
- [ ] [**Blind75** - Curated List of Top 75 LeetCode Questions to Save Your Time | Tech Industry - Blind](https://www.teamblind.com/post/New-Year-Gift---Curated-List-of-Top-75-LeetCode-Questions-to-Save-Your-Time-OaM1orEU)
- [ ] [Hacker Rank skill certification / verification](https://www.hackerrank.com/skills-verification)
- [ ] [Build a SaaS product with Next.js, Supabase and Stripe | egghead.io](https://egghead.io/courses/build-a-saas-product-with-next-js-supabase-and-stripe-61f2bc20)
- [ ] Use this list as a possible way to search for companies: https://www.electronic-city.in/companies/index.php

## Useful resources

- [*CatalanCabbage/notes*: Info, gathered from across the cosmos](https://github.com/CatalanCabbage/notes/tree/master)
- Deep diveable: [basecs – Medium](https://medium.com/basecs)

### LeetCode & co.

- [*Top Interview 150* - Stdy Plan - LeetCode](https://leetcode.com/studyplan/top-interview-150/)
- [*Blind75* - Curated List of Top 75 LeetCode Questions to Save Your Time | Tech Industry - Blind](https://www.teamblind.com/post/New-Year-Gift---Curated-List-of-Top-75-LeetCode-Questions-to-Save-Your-Time-OaM1orEU)
- [*TopSWE*](https://topswe.com/)
- [fishercoder1534/Leetcode: **Solutions to LeetCode problems**; updated daily. Subscribe to my YouTube channel for more.](https://github.com/fishercoder1534/Leetcode)
- *Graph problems*
  - [Roads and Libraries | HackerRank](https://www.hackerrank.com/challenges/torque-and-development/problem?h_l=interview&isFullScreen=false&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs)
- [*Solve Algorithms* | HackerRank](https://www.hackerrank.com/domains/algorithms?utm_source=hrwCandidateFeedback)
- [NeetCode - Practice](https://neetcode.io/practice)

### System Design

- [*System design - Primer* · CatalanCabbage/notes](https://github.com/CatalanCabbage/notes/blob/master/system-design/0-primer.md)
- [*systemdesign42/system-design*: A resource to help you pass system design interview and become good at work 👇](https://github.com/systemdesign42/system-design?tab=readme-ov-file)
- [*ByteByteGoHq/system-design-101*: Explain complex systems using visuals and simple terms. Help you prepare for system design interviews.](https://github.com/ByteByteGoHq/system-design-101#database)
- [*System Design in a Hurry*](https://www.hellointerview.com/learn/system-design/in-a-hurry/introduction)
- *<u>More resources</u>*: [System design interview guide for Software Engineers | Tech Interview Handbook](https://www.techinterviewhandbook.org/system-design/)
- [Online Coding Courses - AI-Powered Interactive Learning](https://www.educative.io/explore)

### Interview

- [*Technical Interview Guide* for Busy Engineers | Tech Interview Handbook](https://www.techinterviewhandbook.org/) [**[repo](https://github.com/yangshun/tech-interview-handbook)**]
  - [How candidates are evaluated in coding interviews at top tech companies | Tech Interview Handbook](https://www.techinterviewhandbook.org/coding-interview-rubrics/)
- _Mock Interview_: [Hume AI](https://www.hume.ai/)

### Helpful point

> For prep i used 3 paid resources.
>
> - Coding ninjas (Started here, and i would still start here, considering the 24*7 doubt support )
> - Algoexpert ( Good question set)
> - LC Premium (Used for assessments)
>
> See also [buildyourownx GitHub profile](https://github.com/codecrafters-io/build-your-own-x) which is great.

Ref: [My Salary For the past 7 years. From 3.5 LPA , to 4L per month (Excluding Stocks) : r/developersIndia](https://www.reddit.com/r/developersIndia/comments/1f5g5yl/comment/lksm0vj/?utm_source=share&utm_medium=mweb3x&utm_name=mweb3xcss&utm_term=1&utm_content=share_button)

#### By Davis

> - I used educative.io for system design, and then random videos
>
>   https://neetcode.io/roadmap - This for DSA
>
> - I took some notes while I did it.
>   https://github.com/CatalanCabbage/notes/tree/master?tab=readme-ov-file#concepts
>
>   Go through these topics, you'll have all the lego blocks
>   Then "hld and lld for xyz" videos will show you how to put the blocks together
>
> - That way you'll be able to explain an overall system and then go into depth when they focus on a specific component as well

### Helpful advice about changing lifestyle

A good twitter thread that deeply resonates with me: [Akshaya Sivaraman on Twitter: "How did I go from a busily scheduled life to one with substantial unstructured time?](https://twitter.com/AksUnik/status/1373654933463920645 )

## Misc. notes

### Hashtable / HashMap

- _Initial capacity and load factor_ affect the performance of hash table / map.
- It is *open*: in the case of a "hash collision", a single bucket stores multiple entries.
- Iterators returned by "collection view methods" are *fail-fast*: i.e., they throw `ConcurrentModificationException` if they detect a concurrent modification of the underlying hashtable. This is *<u>not</u> fool-proof / guaranteed*. So, implementations should not depend on the same.
- Hashtable is *synchronized*. HashMap is not. For thread safe usage, a *ConcurrentHashMap* is recommended.

#### References

- [JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/util/Hashtable.html)
- [Hash table cheatsheet for coding interviews | Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/hash-table/)
- [What are hashtables and hashmaps and their typical use cases?](https://stackoverflow.com/q/138273/5614968)

### Solving algorithmic problems

#### 7 Steps to Solve Algorithm Problems

1. **Listen** - Listen carefully to the problem. *Every detail* is necessary to solve that problem.
2. **Example** - Come up with a good example. Make examples larger and avoid special cases.
3. **Come up with a brute-force algorithm** - It's better to start off with a slow and terrible algorithm than to start of with nothing at all. Don't code the brute-force algorithm. Just state the brute-force algorithm and it's runtime and then go into optimizing it.
4. **Optimize**
5. **Walk through your algorithm** - *Don't code prematurely*. Take a step back and verify that the algorithm is correct.
6. **Start coding** - For whiteboard: write the line straight, use space wisely; Whiteboard or computer: coding style matters, *consistent
   coding style*, naming is important for good style (ask the interviewer if you could abbreviate them the next time), *MODULARIZE*
7. **Start testing** - analyse, double check things that look weird/risky, small test first, special cases next and if time permits large test cases. *Remember*: Think as you test. Don't go through the code like a bot. Test your code and not the algorithm!. When you find a bug, don't panic! Give some thought about fixing the bug.

#### 3 techniques to solving algorithm problems

1. B.U.D - Bottleneck, Unnecessary, Duplicates - walk through the brute force (to identity bottlenecks, unnecessary and duplicated code)
2. Space-time tradeoffs - you can almost always save some time by trading off some space. Think about Hash tables. 
3. Do it yourself - use a large generic example and try to solve it yourself. You'll identify some nice ways to optimize when solving the problem for a large test case.

## Activity log

- **18/Sep/2024** - Continue working on the optimal solution for the Two sum problem.
- **17/Sep/2024** - Worked on the [Two sum problem](https://leetcode.com/problems/two-sum/description/). Completed the brute-force solution. Was working on optimizing the solution and was reading up on HashMap / HashTable a bit.