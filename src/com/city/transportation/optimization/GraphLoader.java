package com.city.transportation.optimization;

import java.util.*;

/** Utility class to load and represent graph data. Hardcoded from ass_3_input.json. */
public class GraphLoader {

    public static class Graph {
        public int id;
        public List<String> nodes;
        public List<Edge> edges;
        public Map<String, List<Edge>> adjacencyList;

        public int getNumVertices() { return nodes.size(); }
        public int getNumEdges() { return edges.size(); }
    }

    public static List<Graph> loadGraphs(String filename) {
        List<Graph> graphs = new ArrayList<>();

        // --- Graph 1 from ass_3_input.json ---
        Graph graph1 = new Graph();
        graph1.id = 1;
        graph1.nodes = Arrays.asList("A", "B", "C", "D", "E");

        List<Edge> edges1 = Arrays.asList(
                new Edge("A", "B", 4),
                new Edge("A", "C", 3),
                new Edge("B", "C", 2),
                new Edge("B", "D", 5),
                new Edge("C", "D", 7),
                new Edge("C", "E", 8),
                new Edge("D", "E", 6)
        );
        graph1.edges = edges1;
        graph1.adjacencyList = buildAdjacencyList(graph1.nodes, edges1);
        graphs.add(graph1);

        // --- Graph 2 from ass_3_input.json ---
        Graph graph2 = new Graph();
        graph2.id = 2;
        graph2.nodes = Arrays.asList("A", "B", "C", "D");

        List<Edge> edges2 = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("A", "C", 4),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("B", "D", 5)
        );
        graph2.edges = edges2;
        graph2.adjacencyList = buildAdjacencyList(graph2.nodes, edges2);
        graphs.add(graph2);

        System.out.println("Input data loaded for " + graphs.size() + " graphs.");
        return graphs;
    }

    // Creates an Adjacency List (required for Prim's)
    private static Map<String, List<Edge>> buildAdjacencyList(List<String> nodes, List<Edge> edges) {
        Map<String, List<Edge>> adj = new HashMap<>();
        for (String node : nodes) {
            adj.put(node, new ArrayList<>());
        }
        for (Edge edge : edges) {
            // Undirected graph
            adj.get(edge.from).add(edge);
            adj.get(edge.to).add(edge);
        }
        return adj;
    }
}