package com.city.transportation.optimization;

import java.util.HashMap;
import java.util.Map;

/** Implements Disjoint Set Union (DSU) for cycle detection in Kruskal's. */
public class DisjointSet {
    private final Map<String, String> parent;
    private final Map<String, Integer> rank;
    public long operationCount; // Find and union operations counter

    public DisjointSet(Iterable<String> nodes) {
        this.parent = new HashMap<>();
        this.rank = new HashMap<>();
        this.operationCount = 0;
        for (String node : nodes) {
            makeSet(node);
        }
    }

    // Initialize set
    private void makeSet(String element) {
        parent.put(element, element);
        rank.put(element, 0);
    }

    /** Finds the root of the set using Path Compression. */
    public String find(String element) {
        operationCount++; // Count find operation
        if (parent.get(element).equals(element)) {
            return element;
        }
        // Path Compression
        String root = find(parent.get(element));
        parent.put(element, root);
        return root;
    }

    /** Unites two sets using Union by Rank. Returns true if union occurred. */
    public boolean union(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);

        if (rootA.equals(rootB)) {
            return false; // Cycle detected
        }

        // Union by Rank
        if (rank.get(rootA) < rank.get(rootB)) {
            parent.put(rootA, rootB);
        } else if (rank.get(rootA) > rank.get(rootB)) {
            parent.put(rootB, rootA);
        } else {
            parent.put(rootB, rootA);
            rank.put(rootA, rank.get(rootA) + 1);
        }
        operationCount++; // Count successful union
        return true;
    }
}