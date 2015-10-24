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
        int N = 100_000;
        //int N = 6;
        Integer[] points = new Integer[N];
        for (int i = 0; i < N; i++) {
            points[i] = in.readInt();
        }

        MergeImproved.sort(points);

        System.out.println(MergeImproved.countInversions);
    }
}
