package com.denver.airport.baggagemgmt;

import com.denver.airport.baggagemgmt.bean.BagDetail;
import com.denver.airport.baggagemgmt.bean.Conveyor;
import com.denver.airport.baggagemgmt.pathfinder.DijkstraPathFinderImpl;
import com.denver.airport.baggagemgmt.pathfinder.Graph;
import com.denver.airport.baggagemgmt.service.BaggageManagementService;
import com.denver.airport.baggagemgmt.service.BaggageManagementServiceImpl;
import com.denver.airport.baggagemgmt.util.BaggageManagementConstant;
import com.denver.airport.baggagemgmt.util.BaggageSystemInputReader;

/**
 * 
 * This class is entry point of Airport Baggage Management System.
 * input.txt file is configured in BaggageSystemUtil file.
 * 
 *
 */
public class AirportBaggageManagementSystem {

	
	public static void main(String[] args) {
		evaluteOptimalPath();
	}

	/**
	 * Evalute the shortest path of between source and destination node.
	 */
	static void evaluteOptimalPath() {
		BaggageSystemInputReader baggageSystemInputReader = BaggageSystemInputReader.getInstance();
		Graph graph = baggageSystemInputReader.getConveyorGraph();
		String destination = null;
		if (graph != null) {
			BaggageManagementService baggageMgmtService = new BaggageManagementServiceImpl();
			StringBuilder sb = new StringBuilder();
			sb.append("Output:");
			sb.append("\n");
			for (BagDetail bag : baggageSystemInputReader.getBagDetails()) {
				if (BaggageManagementConstant.ARRIVAL.equals(bag.getFlightId())) {
					destination = BaggageManagementConstant.BAGGAGE_CLAIM;
				} else {
					destination = baggageSystemInputReader.getDepartures().get(
							bag.getFlightId());
				}
				Conveyor conveyor = new Conveyor(bag.getEntryPoint(),
						destination, graph);
				String route = baggageMgmtService.getOptimalRoutingPath(
						conveyor, new DijkstraPathFinderImpl());
				sb.append(bag.getBagNumber()).append(" ");
				sb.append(route);
				sb.append("\n");
			}
			System.out.println(sb.toString());
		}
		else
		{
			System.out.println("Not able to load airport conveyor system graph ");
		}
	}
}
