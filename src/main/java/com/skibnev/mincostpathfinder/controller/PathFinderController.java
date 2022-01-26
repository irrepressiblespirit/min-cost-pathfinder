package com.skibnev.mincostpathfinder.controller;

import com.skibnev.mincostpathfinder.dto.Query;
import com.skibnev.mincostpathfinder.dto.ResponseDTO;
import com.skibnev.mincostpathfinder.service.PathFinderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pathfinder")
@Api(value = "pathfinder")
public class PathFinderController {

    @Autowired
    private PathFinderService pathFinderService;

    @PostMapping(value = "/findPathWithMinCost")
    @ApiOperation(value = "Find path with min cost using dejksta algorithm")
    @ApiResponses(
            @ApiResponse(message = "", code = 200, response = ResponseDTO.class)
    )
    public ResponseDTO getPathWithMinCost(@RequestBody Query query) {
        return pathFinderService.findPathWithMinCost(query);
    }

}
