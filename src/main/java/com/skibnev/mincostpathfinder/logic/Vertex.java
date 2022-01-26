package com.skibnev.mincostpathfinder.logic;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Vertex {

    private String vertexName;
    private List<Edge> edgeList;
    private double distance;
    private Vertex prevVertex;

    public Vertex(String name) {
        this.vertexName = name;
        edgeList = new ArrayList<Edge>();
        this.distance = Integer.MAX_VALUE;
        this.prevVertex = null;
    }

    public void addEdge(Vertex vertex, double cost){
        edgeList.add(new Edge(vertex, cost));
    }
}
