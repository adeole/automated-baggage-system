package com.denver.airport.baggagemgmt.service;

import com.denver.airport.baggagemgmt.bean.Conveyor;
import com.denver.airport.baggagemgmt.pathfinder.PathFinder;

public class BaggageManagementServiceImpl implements BaggageManagementService {

	public String getOptimalRoutingPath(Conveyor conveyor, PathFinder pathFinder) {
		return pathFinder.findShortestPath(conveyor.getSource(),
				conveyor.getDestination(), conveyor.getConveyorGraph());
	}
}
