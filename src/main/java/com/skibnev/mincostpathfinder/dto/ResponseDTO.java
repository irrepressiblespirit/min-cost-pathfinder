package com.skibnev.mincostpathfinder.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO {
    private List<String> routeWithMinCost;
    private double totalMinCost;
}
