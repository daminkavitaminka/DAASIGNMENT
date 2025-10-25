package com.city.transportation.optimization;

import java.util.*;

/** Implements Prim's and Kruskal's algorithms with performance tracking. */
public class MST_Solver {

    /** Helper class to store and return all required metrics. */
    public static class MSTResult {
        public List<Edge> mstEdges;
        public long totalCost;
        public long operationCount;
        public double executionTimeMs;
    }

    /** Prim's Algorithm (Priority Queue implementation). */
    public MSTResult runPrim(Map<String, List<Edge>> adj, List<String> nodes) {
        long startTime = System.nanoTime();
        MSTResult result = new MSTResult();
        result.operationCount = 0;

        if (nodes.isEmpty()) {
            result.mstEdges = Collections.emptyList();
            result.totalCost = 0;
            result.executionTimeMs = (System.nanoTime() - startTime) / 1_000_000.0;
            return result;
        }

        result.mstEdges = new ArrayList<>();
        result.totalCost = 0;
        Set<String> inMST = new HashSet<>();
        PriorityQueue<Edge.PrimEdge> pq = new PriorityQueue<>();

        // 1. Start from the first node
        String startNode = nodes.get(0);
        pq.add(new Edge.PrimEdge(startNode, null, 0));
        result.operationCount++;

        while (!pq.isEmpty() && inMST.size() < nodes.size()) {
            Edge.PrimEdge current = pq.poll();
            result.operationCount++; // PQ poll

            String u = current.node;
            if (inMST.contains(u)) {
                continue;
            }

            // 2. Add to MST
            inMST.add(u);
            if (current.parent != null) {
                Edge mstEdge = new Edge(current.parent, u, current.weight);
                result.mstEdges.add(mstEdge);
                result.totalCost += current.weight;
            }

            // 3. Relax neighbors
            List<Edge> neighbors = adj.getOrDefault(u, Collections.emptyList());
            for (Edge edge : neighbors) {
                result.operationCount++; // Edge examination
                String v = edge.from.equals(u) ? edge.to : edge.from;
                int weight = edge.weight;

                if (!inMST.contains(v)) {
                    pq.add(new Edge.PrimEdge(v, u, weight));
                    result.operationCount++; // PQ add
                }
            }
        }

        long endTime = System.nanoTime();
        result.executionTimeMs = (endTime - startTime) / 1_000_000.0;
        result.mstEdges.sort(Edge::compareTo); // For consistent output

        return result;
    }


    /** Kruskal's Algorithm (DSU implementation). */
    public MSTResult runKruskal(List<Edge> allEdges, List<String> nodes) {
        long startTime = System.nanoTime();
        MSTResult result = new MSTResult();

        // 1. Initialize DSU and counters
        DisjointSet ds = new DisjointSet(nodes);
        result.mstEdges = new ArrayList<>();
        result.totalCost = 0;
        Edge.comparisonCount = 0;

        // 2. Sort all edges (O(E log E))
        List<Edge> sortedEdges = new ArrayList<>(allEdges);
        sortedEdges.sort(Edge::compareTo);
        result.operationCount = Edge.comparisonCount; // Add sorting comparisons

        // 3. Iterate through sorted edges
        int edgesInMST = 0;
        int numNodes = nodes.size();
        for (Edge edge : sortedEdges) {
            // Stop when V - 1 edges are found
            if (edgesInMST == numNodes - 1) {
                break;
            }

            // Check for cycle and union (DSU operation)
            if (ds.union(edge.from, edge.to)) {
                // No cycle, add edge
                result.mstEdges.add(edge);
                result.totalCost += edge.weight;
                edgesInMST++;
            }
        }

        // 4. Add DSU operations count
        result.operationCount += ds.operationCount;

        long endTime = System.nanoTime();
        result.executionTimeMs = (endTime - startTime) / 1_000_000.0;
        result.mstEdges.sort(Edge::compareTo);

        return result;
    }
}