package com.rp.algorithm.stanford;

import edu.princeton.cs.algs4.In;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        In in = new In(args[0]);
        int N = 10_000;
        //int N = 9;
        Integer[] points = new Integer[N];
        for (int i = 0; i < N; i++) {
            points[i] = in.readInt();
        }

        Quicksort.sort(points, 0, points.length - 1);

        System.out.println(Quicksort.countComparison);
        Quicksort.show(points);
    }
}
