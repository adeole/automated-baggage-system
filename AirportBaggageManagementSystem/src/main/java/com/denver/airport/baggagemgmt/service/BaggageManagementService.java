package com.denver.airport.baggagemgmt.service;

import com.denver.airport.baggagemgmt.bean.Conveyor;
import com.denver.airport.baggagemgmt.pathfinder.PathFinder;

public interface BaggageManagementService {
	
	/**
	 * Get Optimal Routing Path from source to destination using Conveyor details.
	 * Routing path format:
	 * Bag_Number point_1 point_2 .. : total_travel_time
	 * 
	 * @param conveyor
	 * @param pathFinder, instance of path finder algorithm
	 * @return shortest routing path
	 */
	public String getOptimalRoutingPath(Conveyor conveyor, PathFinder pathFinder);
}
