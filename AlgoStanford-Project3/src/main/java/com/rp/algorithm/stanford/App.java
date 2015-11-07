package com.rp.algorithm.stanford;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        In in = new In(args[0]);
        int N = 200;

        String[] lines = in.readAllLines();
        Vertex[] vertices = new Vertex[lines.length];
        List<Edge> allEdges = new ArrayList<Edge>();

        // initialize all vercises
        for (int i = 0; i < lines.length; i++) {
            vertices[i] = new Vertex(i + 1);
        }

        for (int i = 0; i < lines.length; i++) {
            String[] localVertices = lines[i].split("\\s+");
            Vertex v1 = vertices[Integer.valueOf(localVertices[0]) - 1];

            Vertex v2;
            for (int j = 1; j < localVertices.length; j++) {
                v2 = vertices[Integer.valueOf(localVertices[j]) - 1];
                Edge edge = new Edge(v1, v2);
                v1.addEdge(edge);
                v2.addEdge(edge);
                allEdges.add(edge);
            }
        }

        int minCuts = minCuts(vertices, allEdges);
        System.out.println(minCuts);
    }

    public static int minCuts(Vertex[] vertices, List<Edge> edges) {
        int N = vertices.length;
        int min = N * (N -1) / 2; // maximum number of unique edges

        for (int i = 1; i <= N*N; i++) {
            int minCut = minCut(vertices.clone(), new ArrayList<Edge>(edges));

            if (min > minCut) {
                min = minCut;
            }
        }

        return min;
    }

    public static int minCut(Vertex[] vertices, List<Edge> edges) {
        int size = vertices.length;
        Random random = new Random(System.currentTimeMillis());

        while (size > 2) {
            int index = random.nextInt(edges.size());
            Edge edge = edges.remove(index);

            Vertex v1 = edge.getV1();
            Vertex v2 = edge.getV2();

            if (v1 != v2) {
                // creating new vertex with Id of the 1st vertex
                Vertex newVertex = new Vertex(v1.getId());
                // relacing 1st vertex with new vertex
                vertices[v1.getId() - 1] = newVertex;
                // removing 2nd vertex
                vertices[v2.getId() - 1] = null;

                //iterate through each edge of 2nd vertex and replace all occurence of 2nd vertex with new vertex
                for (Edge edgeOfv2 : v2.getEdges()) {
                    // don't consider current removed edge
                    if (edgeOfv2 == edge) {
                        continue;
                    }
                    if (edgeOfv2.getV1() == v2) {
                        edgeOfv2.setV1(newVertex);
                    } else if (edgeOfv2.getV2() == v2) {
                        edgeOfv2.setV2(newVertex);
                    }
                    newVertex.addEdge(edgeOfv2);
                }
                //decrease number of vertices
                size--;
            }
            // else it's edge where vertices are the same vertex and we ignore it
        }

        int i = 0;
        while (vertices[i] == null) {
            i++;
        }

        return vertices[i].getEdges().size();
    }
}
