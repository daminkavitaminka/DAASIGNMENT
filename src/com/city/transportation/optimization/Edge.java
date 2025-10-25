package com.city.transportation.optimization;

import java.util.Comparator;

/** Represents a road (edge) with a construction cost (weight). */
public class Edge implements Comparable<Edge> {
    public String from;
    public String to;
    public int weight;

    // Counter for comparisons during Kruskal's sorting
    public static long comparisonCount = 0;

    public Edge(String from, String to, int weight) {
        // Canonical order for undirected edge representation
        if (from.compareTo(to) < 0) {
            this.from = from;
            this.to = to;
        } else {
            this.from = to;
            this.to = from;
        }
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        // Count comparison
        comparisonCount++;
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return "(" + from + " - " + to + ", Cost: " + weight + ")";
    }

    /** Helper class for Prim's algorithm to use in Priority Queue. */
    public static class PrimEdge implements Comparable<PrimEdge> {
        public String node;
        public String parent;
        public int weight;

        public PrimEdge(String node, String parent, int weight) {
            this.node = node;
            this.parent = parent;
            this.weight = weight;
        }

        @Override
        public int compareTo(PrimEdge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
}