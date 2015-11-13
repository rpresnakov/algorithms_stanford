package com.rp.algorithm.stanford;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.StreamHandler;

/**
 * Created by presnakovr on 11/6/2015.
 */
public class Vertex {

    private int id;
    private int rank;

    private List<Vertex> directV = new ArrayList<Vertex>();
    private List<Vertex> reverseV = new ArrayList<Vertex>();

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Vertex> getDirectV() {
        return directV;
    }

    public void setDirectV(List<Vertex> directV) {
        this.directV = directV;
    }

    public List<Vertex> getReverseV() {
        return reverseV;
    }

    public void setReverseV(List<Vertex> reverseV) {
        this.reverseV = reverseV;
    }

    public void addDirectV(Vertex v) {
        directV.add(v);
    }

    public void addReverseV(Vertex v) {
        reverseV.add(v);
    }

    public String toString() {
        return String.valueOf(id);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
