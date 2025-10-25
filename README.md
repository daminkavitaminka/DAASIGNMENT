1. Introduction and Project Goal
This report is about finding the cheapest way to connect all the cities (nodes) in our transportation network. To do this, I used two classic algorithms: Prim's Algorithm and Kruskal's Algorithm, which are designed to find the Minimum Spanning Tree (MST).
 main goals for this project were:
1.	To find the absolute minimum total cost required for the new road network.
2.	To verify that both algorithms produce the identical correct result.
3.	To compare their execution time and total operation count to see which is more efficient for our data.
2. Process of the work
I implemented both MST algorithms and used them to process two different test graphs (Graph 1 and Graph 2).
•	Prim's Algorithm: This approach works by growing the network from a starting point, always selecting the cheapest road that connects to a city not yet in the network. I used a Priority Queue to manage the edges efficiently.
•	Kruskal's Algorithm: This approach first sorts all roads by cost. Then, it adds them to the network one by one, but only if they do not create a cycle. For fast cycle checking, I used the highly optimized Disjoint Set Union (DSU) data structure.
I tracked the total cost, the specific roads chosen, the overall number of operations (comparisons, queue insertions, DSU finds/unions), and the exact execution time in milliseconds.
3. Test Results
The tables below present the data recorded from running both algorithms on the two input graphs.
Table 3.1: Comparison for Graph 1 (V=5, E=7)
Metric	Prim's Algorithm	Kruskal's Algorithm
Total Cost	16	16
MST Edges	(B-C, 2), (A-C, 3), (B-D, 5), (D-E, 6)	(B-C, 2), (A-C, 3), (B-D, 5), (D-E, 6)
Vertices ($V$)	5	5
Edges ($E$)	7	7
Operation Count	28	29
Execution Time (ms)	0.616	0.495
Table 3.2: Comparison for Graph 2 (V=4, E=5)
Metric	Prim's Algorithm	Kruskal's Algorithm
Total Cost	6	6
MST Edges	(A-B, 1), (B-C, 2), (C-D, 3)	(A-B, 1), (B-C, 2), (C-D, 3)
Vertices ($V$)	4	4
Edges ($E$)	5	5
Operation Count	20	19
Execution Time (ms)	0.021	0.014
 
4. Analysis
4.1. Correctness
The most critical finding is that both algorithms produced the exact same Total Cost (16 and 6) and the same set of MST Edges for both graphs. This confirms that implementation is correct and robust, successfully identifying the optimal solution.
4.2. Efficiency
Criteria	Kruskal's	Prim's	Analysis
Speed (Time)	Faster in both tests.	Slower in both tests.	Kruskal's generally has lower overhead for small/sparse graphs.
Operations	Slightly less or similar.	Slightly more.	The efficient DSU structure in Kruskal's minimizes operation count.
For our test cases, which are small and relatively sparse (not many edges), Kruskal's Algorithm was consistently faster. This is expected, as Prim's algorithm has a small overhead from managing the Priority Queue, which can be noticeable on small input sizes.
5. Conclusion
•	Graph 1: The minimum construction cost is 16 units.
•	Graph 2: The minimum construction cost is 6 units.
Both MST algorithms confirmed the results, but based on the recorded execution times, Kruskal's Algorithm is the slightly preferred choice for small, non-dense networks
