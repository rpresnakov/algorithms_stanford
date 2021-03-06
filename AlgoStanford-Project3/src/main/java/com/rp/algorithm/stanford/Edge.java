package com.rp.algorithm.stanford;

/**
 * Created by presnakovr on 11/6/2015.
 */
public class Edge {

    private Vertex v1;
    private Vertex v2;

    public Edge(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertex getV1() {
        return v1;
    }

    public void setV1(Vertex v1) {
        this.v1 = v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public void setV2(Vertex v2) {
        this.v2 = v2;
    }

    public String toString() {
        return v1.toString() + "-" + v2.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (!v1.equals(edge.v1)) return false;
        return v2.equals(edge.v2);

    }

    @Override
    public int hashCode() {
        int result = v1.getId();
        result = 31 * result + v2.getId();
        return result;
    }
}
