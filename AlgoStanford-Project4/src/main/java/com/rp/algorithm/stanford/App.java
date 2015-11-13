package com.rp.algorithm.stanford;

import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MaxPQ;

import java.util.*;

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
        // won't consider the first element with zero index just to avoid confuse
        Vertex[] vertices = new Vertex[lines.length * 2];

        int N = 0;
        // initialize all vercises
        for (int i = 0; i < lines.length; i++) {
            String[] vvertices = lines[i].split("\\s+");
            int tailIndex = Integer.valueOf(vvertices[0]);
            int headIndex = Integer.valueOf(vvertices[1]);

            if (vertices[tailIndex] == null) {
                vertices[tailIndex] = new Vertex(tailIndex);
                if (tailIndex > N) {
                    N = tailIndex;
                }
            }
            if (vertices[headIndex] == null) {
                vertices[headIndex] = new Vertex(headIndex);
                if (headIndex > N) {
                    N = headIndex;
                }
            }
            Vertex tail = vertices[tailIndex];
            Vertex head = vertices[headIndex];

            tail.addDirectV(head);
            head.addReverseV(tail);
        }

        System.out.println("Number of vertices: " + N);
        new App(vertices, N).solve();
    }

    private int N;
    private int t = 0;
    private boolean[] marked;
    private Vertex[] ratedV;
    private Vertex[] vertices;

    private int[] maxResults;

    public App(Vertex[] vertices, int N) {
        this.N = N;
        this.vertices = vertices;
        this.ratedV = new Vertex[N + 1];
        marked = new boolean[N + 1];
        maxResults = new int[5];
    }

    public int DFS1(Vertex[] vertices, int i, boolean useReverse) {
        marked[i] = true;

        Vertex v = vertices[i];
        List<Vertex> arcs = useReverse ? v.getReverseV() : v.getDirectV();

        int num = 0;
        for (Vertex arc : arcs) {
            int goToIndex = useReverse ? arc.getId() : arc.getRank();
            if (!marked[goToIndex]) {
                num = DFS1(vertices, goToIndex, useReverse);
            }
        }

        if (useReverse) {
            t++;
            ratedV[t] = vertices[i];
            vertices[i].setRank(t);
        }

        return num + 1;
    }

    public void solve() {
        for (int i = N; i > 0; i--) {
            if (!marked[i]) {
                //System.out.println("DFS for the vertex " + i);
                DFS1(vertices, i, true);
            }
        }
        System.out.println("DFS Cycle 1 completed");
        for (int i = N; i > 0; i--) {
            marked[i] = false;
        }

        int num;
        MaxPQ<Integer> maxHeap = new MaxPQ<Integer>();
        for (int i = N; i > 0; i--) {
            if (!marked[i]) {
                //System.out.println("DFS for the vertex " + i);
                num = DFS1(ratedV, i, false);
                maxHeap.insert(num);
            }
        }
        System.out.println("DFS Cycle 2 completed");

        boolean flag = true;
        System.out.println(maxHeap.delMax());
        System.out.println(maxHeap.delMax());
        System.out.println(maxHeap.delMax());
        System.out.println(maxHeap.delMax());
        System.out.println(maxHeap.delMax());
    }

}
