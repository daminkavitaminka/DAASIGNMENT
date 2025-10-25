package com.city.transportation.optimization;

import java.util.List;

/** Main class to run MST optimization and print results for the report. */
public class CityNetworkOptimization {

    public static void main(String[] args) {
        List<GraphLoader.Graph> graphs = GraphLoader.loadGraphs("ass_3_input.json");

        MST_Solver solver = new MST_Solver();

        for (GraphLoader.Graph graph : graphs) {
            System.out.println("\n=======================================================");
            System.out.println("Processing Graph ID: " + graph.id + " (V=" + graph.getNumVertices() + ", E=" + graph.getNumEdges() + ")");
            System.out.println("-------------------------------------------------------");

            // --- 1. Run Prim's Algorithm ---
            MST_Solver.MSTResult primResult = solver.runPrim(graph.adjacencyList, graph.nodes);

            System.out.println("Algorithm: Prim's");
            System.out.println("  Total Cost: " + primResult.totalCost);
            System.out.println("  MST Edges: " + primResult.mstEdges);
            System.out.println("  Vertices (V): " + graph.getNumVertices() + ", Edges (E): " + graph.getNumEdges());
            System.out.println("  Operations Count (PQ + Edge Checks): " + primResult.operationCount);
            System.out.printf("  Execution Time (ms): %.3f\n", primResult.executionTimeMs);

            System.out.println("-------------------------------------------------------");

            // --- 2. Run Kruskal's Algorithm ---
            MST_Solver.MSTResult kruskalResult = solver.runKruskal(graph.edges, graph.nodes);

            System.out.println("Algorithm: Kruskal's");
            System.out.println("  Total Cost: " + kruskalResult.totalCost);
            System.out.println("  MST Edges: " + kruskalResult.mstEdges);
            System.out.println("  Vertices (V): " + graph.getNumVertices() + ", Edges (E): " + graph.getNumEdges());
            System.out.println("  Operations Count (Sort Comparisons + DSU Ops): " + kruskalResult.operationCount);
            System.out.printf("  Execution Time (ms): %.3f\n", kruskalResult.executionTimeMs);

            // 3. Comparison Check
            if (primResult.totalCost == kruskalResult.totalCost) {
                System.out.println("-> Result Check: SUCCESS. Total Cost is identical: " + primResult.totalCost);
            } else {
                System.out.println("-> Result Check: FAILED. Costs do not match (Prim: " + primResult.totalCost + ", Kruskal: " + kruskalResult.totalCost + ")");
            }
        }
        System.out.println("=======================================================");
    }
}