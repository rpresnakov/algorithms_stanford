package com.rp.algorithm.stanford;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by presnakovr on 10/23/2015.
 */
public class Quicksort {

    public static long countComparison = 0;

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int partInd = partition(a, lo, hi);
        sort(a, lo, partInd - 1);
        sort(a, partInd + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        countComparison += (hi - lo);

        //takeLastAsPivot(a, lo, hi);
        takeMedianOfThreeAsPivot(a, lo, hi);

        int pivotIndex = lo;
        Comparable pivot = a[pivotIndex];
        int i = lo + 1;
        for (int j = i; j <= hi; j++) {
            if (less(a[j], pivot)) {
                exch(a, i, j);
                i++;
            }
        }
        exch(a, pivotIndex, i - 1);
        return i - 1;
    }

    private static void takeLastAsPivot(Comparable[] a, int lo, int hi) {
        //making pivot the last element
        exch(a, lo, hi);
    }

    private static void takeMedianOfThreeAsPivot(Comparable[] a, int lo, int hi) {
        int med = lo + (hi - lo) / 2;

        int pivotInd;

        if (less(a[med], a[lo])) {
            if (less(a[hi], a[med])) {
                pivotInd = med;
            } else if (less(a[hi], a[lo])) {
                pivotInd = hi;
            } else {
                pivotInd = lo;
            }
        } else {
            if (less(a[hi], a[lo])) {
                pivotInd = lo;
            } else if (less(a[hi], a[med])) {
                pivotInd = hi;
            } else {
                pivotInd = med;
            }
        }

        exch(a, lo, pivotInd);
    }

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

   // print array to standard output
    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

}
