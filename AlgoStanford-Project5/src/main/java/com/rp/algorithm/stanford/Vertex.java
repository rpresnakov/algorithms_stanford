package com.rp.algorithm.stanford;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by presnakovr on 11/6/2015.
 */
public class Vertex implements Comparable<Vertex> {

    private int id;
    private Integer shortPathValue = 1_000_000;

    private Set<Edge> edges = new HashSet<>();

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }
    public String toString() {
        return String.valueOf(id);
    }

    public int getShortPathValue() {
        return shortPathValue;
    }

    public void setShortPathValue(int shortPathValue) {
        this.shortPathValue = shortPathValue;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.shortPathValue - o.getShortPathValue();
    }
}
