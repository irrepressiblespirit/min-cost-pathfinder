package com.skibnev.mincostpathfinder.validator;

import com.skibnev.mincostpathfinder.dto.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InputDataValidator {

	public boolean checkNeighborsCount(Query query) {
		return query.getVertex().stream().allMatch(vertex -> vertex.getNeighborsInfo().size() == vertex.getNeighborsCount());
	}

	public boolean checkMutualRelations(Query query) {
		return query.getVertex().stream().allMatch(vertex -> checkNeighborsRelations(query ,vertex.getName(), vertex.getNeighborsInfo()));
	}

	private boolean checkNeighborsRelations(Query query, String name, List<Query.NeighborsInfo> neighborsInfo) {
		return neighborsInfo.stream().allMatch(vert -> {
			Query.Vertex vrt1 = query.getVertex().stream().filter(v -> v.getName().equals(vert.getName())).findFirst().orElse(null);
			if (vrt1 != null) {
				Query.NeighborsInfo neighbors = vrt1.getNeighborsInfo().stream().filter(ver -> ver.getName().equals(name)).findFirst().orElse(null);
				return neighbors != null;
			} else {
				return false;
			}
		});
	}
}
