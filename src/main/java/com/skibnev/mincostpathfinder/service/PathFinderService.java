package com.skibnev.mincostpathfinder.service;

import com.skibnev.mincostpathfinder.dto.Query;
import com.skibnev.mincostpathfinder.dto.ResponseDTO;
import com.skibnev.mincostpathfinder.logic.Graph;
import com.skibnev.mincostpathfinder.logic.Path;
import com.skibnev.mincostpathfinder.logic.PathFinder;
import com.skibnev.mincostpathfinder.validator.InputDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PathFinderService {

    @Autowired
    private PathFinder pathFinder;
    @Autowired
    private InputDataValidator inputDataValidator;

    public ResponseDTO findPathWithMinCost(Query query) {
        Graph graph = new Graph();
        ResponseDTO response = new ResponseDTO();
        if (inputDataValidator.checkNeighborsCount(query) && inputDataValidator.checkMutualRelations(query)) {
            query.getVertex().forEach(vertex -> vertex.getNeighborsInfo().forEach(neighborsInfo -> graph.addEdge(vertex.getName(), neighborsInfo.getName(), neighborsInfo.getCost())));
            pathFinder.runDejkstraAlgorithm(query.getStartVertexName(), graph);
            Path path = pathFinder.getPathWithMinCost(query.getEndVertexName());
            if (path != null) {
                response.setTotalMinCost(path.getCost());
                response.setRouteWithMinCost(pathFinder.findRouteWithMinCost(path));
            }
        }
        return response;
    }
}
