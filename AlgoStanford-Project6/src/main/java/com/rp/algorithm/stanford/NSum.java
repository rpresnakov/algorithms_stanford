package com.rp.algorithm.stanford;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class NSum
{
    public static void main( String[] args )
    {
        In in = new In(args[0]);

        String[] lines = in.readAllLines();

        NSum app = new NSum();
        app.prepare(lines);
        System.out.println(app.solve(-10_000, 10_000));
    }

    private Set<Long> numbers = new HashSet<Long>();
    private Set<Long> numbers2 = new HashSet<Long>();
    private Set<Integer> tt = new HashSet<>();

    public void prepare(String[] lines) {
        int N = lines.length;
        for (int i = 0; i < N; i++) {
            Long number = Long.valueOf(lines[i]);
            numbers.add(number);
            numbers2.add(number);
        }

    }

    public int solve(int t1, int t2) {
        int result = 0;

            int i = 0;

//        for (int t = t1; t <= t2; t++) {
//            tt.add(t);
//        }

        for (Long n : numbers) {
            for (int t = t1; t <= 0; t++) {
                if (!tt.contains(t)) {
                    if ((t - n) != n && numbers.contains(t - n)) {
                        result++;
                        tt.add(t);
                    }
                }
                if (!tt.contains((-1)*t)) {
                    if (((-1) * t - n) != n && numbers.contains((-1) * t - n)) {
                        result++;
                        tt.add((-1) * t);
                    }
                }
            }
            if (i % 100 == 0) {
                System.out.println(i + " " + result);
            }
            i++;
        }


//            for (int t = t1; t <= t2; t++) {
//                for (Long n : numbers) {
//                    if (n != (t - n) && numbers.contains(t - n)) {
//                        result++;
//                        break;
//                    }
//                }
//                System.out.println(t + " " + result);
////                if (i % 100 == 0) {
////                    System.out.println(i + " " + result);
////                }
////                i++;
//            }
//            for (Long n : numbers) {
//                for (int t = tl; t <= 0; t++) {
//                    if (numbers2.contains(t - n)) {
//                        result++;
//                    }
//                    if (numbers2.contains(-1 * t - n)) {
//                        result++;
//                    }
//                }
//                numbers2.remove(n);
//
//                if (i % 100 == 0) {
//                    System.out.println(i);
//                }
//                i++;
//            }
//            for (int j = 0; j < t/2; j++) {
//                if (numbers.contains(j) && numbers.contains(t - j)) {
//                    result++;
//                    break;
//                }
//            }

        return result;
    }
}
