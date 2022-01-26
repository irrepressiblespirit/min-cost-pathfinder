package com.skibnev.mincostpathfinder.logic;

import com.skibnev.mincostpathfinder.comparator.PathComparator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

@Component
public class PathFinder {

    private PriorityQueue<Path> pq,close;

    public PathFinder() {
        pq = new PriorityQueue<Path>(new PathComparator());
        close = new PriorityQueue<Path>(new PathComparator());
    }

    //implementation of Dejkstra algorithm
    public void runDejkstraAlgorithm(String startPoint, Graph graph) {
        Path path;
        boolean isFound = false;
        Vertex vertex = graph.getOrSaveVertex(startPoint);
        vertex.setDistance(0);
        pq.add(new Path(vertex, 0));
        while (!pq.isEmpty()) {
            path = pq.remove();
            Vertex tmp = path.getVertex();
            if (close.isEmpty()) {
                isFound = true;
            } else {
                for (Path p : close) {
                    if (!tmp.getVertexName().equals(p.getVertex().getVertexName())) {
                        isFound = true;
                    } else {
                        isFound = false;
                    }
                }
            }
            if (isFound) {
                close.add(new Path(tmp, tmp.getDistance()));
                int count = tmp.getEdgeList().size();
                isFound = false;
                for (int j=0; j<count; j++) {
                    Edge edge = tmp.getEdgeList().get(j);
                    Vertex vrt = edge.getVertex();
                    double cost = edge.getCost();
                    //if (vrt.getDistance() > tmp.getDistance() + cost) {
                    if (new BigDecimal(vrt.getDistance()).compareTo(new BigDecimal(tmp.getDistance() + cost)) > 0) {
                        vrt.setDistance(tmp.getDistance() + cost);
                        vrt.setPrevVertex(tmp);
                        pq.add(new Path(vrt, vrt.getDistance()));
                    }
                }
            }
        }
    }

    //find path from end point(@param endName) to start point
    public Path getPathWithMinCost(String endPoint){
        return close.stream().filter(pth -> pth.getVertex().getVertexName().equals(endPoint)).findFirst().orElse(null);
    }

    public List<String> findRouteWithMinCost(Path path) {
        List<String> vertexNames = new ArrayList<>();
        vertexNames.add(path.getVertex().getVertexName());
        Vertex vertex = path.getVertex().getPrevVertex();
        while(vertex != null) {
            String vertexName = vertex.getVertexName();
            vertexNames.add(vertexName);
            Path tmp = close.stream().filter(elem -> elem.getVertex().getVertexName().equals(vertexName)).findFirst().orElse(null);
            if (tmp == null) {
                break;
            }
            vertex = tmp.getVertex().getPrevVertex();
        }
        Collections.reverse(vertexNames);
        return vertexNames;
    }
}
