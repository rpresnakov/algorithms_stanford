package com.rp.algorithm.stanford;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by presnakovr on 11/6/2015.
 */
public class Vertex {

    private int id;
    private List<Edge> edges = new ArrayList<Edge>();

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public String toString() {
        return Integer.toString(id);
    }
}
