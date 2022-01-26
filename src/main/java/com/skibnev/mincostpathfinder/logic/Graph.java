package com.skibnev.mincostpathfinder.logic;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Vertex> vertexList;

    public Graph() {
        vertexList = new ArrayList<>();
    }

    //add edge between two vertex and save his cost
    public void addEdge(String sourceName, String secondName, double cost) {
        Vertex oneVertex = getOrSaveVertex(sourceName);
        Vertex secondVertex = getOrSaveVertex(secondName);
        oneVertex.addEdge(secondVertex, cost);
    }

    //add vertex to vertex list if vertex is not found create new vertex
    public Vertex getOrSaveVertex(String vertexName) {
        Vertex vertex = vertexList.stream().filter(vrt -> vrt.getVertexName().equals(vertexName)).findFirst().orElse(null);
        if (vertex == null) {
            vertex = new Vertex(vertexName);
            vertexList.add(vertex);
        }
        return vertex;
    }
}
