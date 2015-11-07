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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return (id == vertex.id);
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }

}
