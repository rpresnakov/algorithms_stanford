package com.rp.algorithm.stanford;

import edu.princeton.cs.algs4.In;

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
        int N = 200;

        String[] lines = in.readAllLines();
        Vertex[] vertices = new Vertex[lines.length];
        Set<Edge> allEdges = new HashSet<Edge>();

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
                Edge edge = (v1.getId() < v2.getId()) ? new Edge(v1, v2) : new Edge(v2, v1);
                if (!allEdges.contains(edge)) {
                    v1.addEdge(edge);
                    v2.addEdge(edge);
                    allEdges.add(edge);
                }
            }
        }

        int minCuts = minCuts(vertices, allEdges);
        System.out.println(minCuts);
    }

    public static int minCuts(Vertex[] vertices, Set<Edge> edges) {
        int N = vertices.length;
        int min = N * (N -1) / 2; // maximum number of unique edges

        for (int i = 1; i <= N * N; i++) {
            Vertex[] newVertices = new Vertex[vertices.length];
            Set<Edge> newEdges = new HashSet<Edge>();
            cloneElements(vertices, newVertices, edges, newEdges);
            int minCut = minCut(newVertices, new ArrayList<Edge>(newEdges));

            if (min > minCut) {
                min = minCut;
            }
            if (i % 100 == 0) {
                System.out.println("Attempt #" + i + " (" + i * 1. / (N * N) * 100 + "%): mincut=" + min);
            }
        }

        return min;
    }

    private static void cloneElements(Vertex[] oldVertices, Vertex[] newVertices, Set<Edge> oldEdges, Set<Edge> newEdges) {
        int N = oldVertices.length;
        for (int i = 0; i < N; i++) {
            Vertex oldVertex = oldVertices[i];
            newVertices[i] = new Vertex(oldVertex.getId());
        }

        for (Edge edge : oldEdges) {
            int v1 = edge.getV1().getId();
            int v2 = edge.getV2().getId();
            Edge newEdge = new Edge(newVertices[v1-1], newVertices[v2-1]);
            newVertices[v1-1].addEdge(newEdge);
            newVertices[v2-1].addEdge(newEdge);
            newEdges.add(newEdge);
        }
    }

    public static int minCut(Vertex[] vertices, List<Edge> edges) {
        int size = vertices.length;

        while (size > 2) {
            Random random = new Random(System.currentTimeMillis());
            int index = random.nextInt(edges.size());
            //int index = 3;
            Edge edge = edges.remove(index);

            Vertex v1 = edge.getV1();
            Vertex v2 = edge.getV2();

            if (!v1.equals(v2)) {
                // creating new vertex with Id of the 1st vertex
                Vertex newVertex = new Vertex(v1.getId());
                // relacing 1st vertex with new vertex
                vertices[v1.getId() - 1] = newVertex;
                // removing 2nd vertex
                vertices[v2.getId() - 1] = null;

                //iterate through each edge of 1st vertex and update reference to the 1st vertex
                for (Edge edgeOfv1 : v1.getEdges()) {
                    if (edgeOfv1 == edge) {
                        continue;
                    }
                    if (edgeOfv1.getV1().equals(v1)) {
                        edgeOfv1.setV1(newVertex);
                    }
                    if (edgeOfv1.getV2().equals(v1)) {
                        edgeOfv1.setV2(newVertex);
                    }
                    newVertex.addEdge(edgeOfv1);
                }
                //iterate through each edge of 2nd vertex and replace all occurence of 2nd vertex with new vertex
                for (Edge edgeOfv2 : v2.getEdges()) {
                    // don't consider current removed edge
                    if (edgeOfv2 == edge) {
                        continue;
                    }
                    if (edgeOfv2.getV1().equals(v2)) {
                        edgeOfv2.setV1(newVertex);
                    }
                    if (edgeOfv2.getV2().equals(v2)) {
                        edgeOfv2.setV2(newVertex);
                    }
                    boolean exist = false;
                    for (Edge edge_ : newVertex.getEdges()) {
                        if (edge_ == edgeOfv2) {
                            exist = true;
                            break;
                        }
                    }
                    if (!exist) {
                        newVertex.addEdge(edgeOfv2);
                    }
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

        int j = 0;
        for (Edge edge : vertices[i].getEdges()) {
            if (!edge.getV1().equals(edge.getV2())) {
                j++;
                //System.out.print(edge.toString() + ";");
            }
        }
        //System.out.println();
        return j;
    }
}
