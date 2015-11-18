package com.rp.algorithm.stanford;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        In in = new In(args[0]);

        String[] lines = in.readAllLines();
        int N = lines.length;
        // won't consider the first element with zero index just to avoid confuse
        Vertex[] vertices = new Vertex[N + 1];
        for (int i = 1; i <= N; i++) {
            vertices[i] = new Vertex(i);
        }

        // initialize all vercises
        for (int i = 0; i < N; i++) {
            String[] elements = lines[i].split("\\s+|,+");
            Vertex v1 = vertices[Integer.valueOf(elements[0])];
            int j = 1;
            while (j < elements.length) {
                Vertex v2 = vertices[Integer.valueOf(elements[j])];
                int length = Integer.valueOf(elements[++j]);
                Edge edge = new Edge(v1, v2, length);
                v1.addEdge(edge);
                v2.addEdge(edge);
                j++;
            }
        }

        System.out.println("Reading of verices done");
        new App(vertices, N).solve(1);
        int targets[] = new int[] {7,37,59,82,99,115,133,165,188,197};
        for (int i = 0; i < targets.length; i++) {
            System.out.print(vertices[targets[i]].getShortPathValue() + ",");
        }
    }

    private int N;
    private int t = 0;
    private Vertex[] vertices;

    public App(Vertex[] vertices, int N) {
        this.N = N;
        this.vertices = vertices;
    }

    public void solve(int source) {
        Set<Vertex> visited = new HashSet<>();
        vertices[source].setShortPathValue(0);
        IndexMinPQ<Vertex> minHeap = new IndexMinPQ<>(this.N + 1);
        for (int i = 1; i <= N; i++) {
            minHeap.insert(i, vertices[i]);
        }

        while (!minHeap.isEmpty()) {
            //remove a vertex with min short path from heap
            Vertex minShortPathVertex = minHeap.minKey();
            minHeap.delMin();
            visited.add(minShortPathVertex);

            //iterate and recalculate all non-visited vertices connected to the removed one
            for (Edge edge : minShortPathVertex.getEdges()) {
                Vertex neighborVertex = edge.getV1().equals(minShortPathVertex) ? edge.getV2() : edge.getV1();
                if (!visited.contains(neighborVertex)) {
                    int newShortPath = minShortPathVertex.getShortPathValue() + edge.getLength();
                    if (newShortPath < neighborVertex.getShortPathValue()) {
                        //re-compute neighbor vertex with removing/recalculation/reinserting into min heap
                        minHeap.delete(neighborVertex.getId());
                        neighborVertex.setShortPathValue(newShortPath);
                        minHeap.insert(neighborVertex.getId(), neighborVertex);
                    }
                }
            }
        }
    }
}
