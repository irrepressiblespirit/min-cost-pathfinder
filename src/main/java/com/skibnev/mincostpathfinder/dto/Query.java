package com.skibnev.mincostpathfinder.dto;

import lombok.Data;

import java.util.List;

@Data
public class Query {
    private String startVertexName;
    private List<Vertex> vertex;
    private String endVertexName;

    @Data
    public static class Vertex {
        private String name;
        private int neighborsCount;
        private List<NeighborsInfo> neighborsInfo;
    }

    @Data
    public static class NeighborsInfo {
        private String name;
        private double cost;
    }
}
