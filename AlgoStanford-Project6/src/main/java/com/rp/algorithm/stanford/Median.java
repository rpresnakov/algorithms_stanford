package com.rp.algorithm.stanford;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

/**
 * Created by Roman on 11/29/2015.
 */
public class Median {

    public static void main( String[] args )
    {
        In in = new In(args[0]);

        String[] lines = in.readAllLines();

        long medianSum = 0;

        for (int i = 0; i < lines.length; i++) {
            Long number = Long.parseLong(lines[i]);
            medianSum += nextMedian(number);
        }

        System.out.println("Sum of medians = " + medianSum);
        System.out.println("Modulo 10000 = " + (medianSum % 10000));
    }

    private static MinPQ<Long> minPQ = new MinPQ<>();
    private static MaxPQ<Long> maxPQ = new MaxPQ<>();

    private static long nextMedian(long number) {
        // if maxPQ is not initialized yet
        if (maxPQ.isEmpty()) {
            maxPQ.insert(number);
            return maxPQ.max();
        }

        if (number <= maxPQ.max()) {
            maxPQ.insert(number);
        } else {
            minPQ.insert(number);
        }

        if ((maxPQ.size() - minPQ.size()) > 1) {
            minPQ.insert(maxPQ.delMax());
        }

        if ((minPQ.size() - maxPQ.size()) > 0) {
            maxPQ.insert(minPQ.delMin());
        }

        return maxPQ.max();
    }
}
