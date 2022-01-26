package com.skibnev.mincostpathfinder.logic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Path {
    private Vertex vertex;
    private double cost;
}
