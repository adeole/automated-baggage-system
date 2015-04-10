package com.denver.airport.baggagemgmt;

import com.denver.airport.baggagemgmt.bean.BagDetail;
import com.denver.airport.baggagemgmt.bean.Conveyor;
import com.denver.airport.baggagemgmt.pathfinder.DijkstraPathFinderImpl;
import com.denver.airport.baggagemgmt.pathfinder.Graph;
import com.denver.airport.baggagemgmt.service.BaggageManagementService;
import com.denver.airport.baggagemgmt.service.BaggageManagementServiceImpl;
import com.denver.airport.baggagemgmt.util.BaggageManagementConstant;
import com.denver.airport.baggagemgmt.util.BaggageSystemInputReader;

import junit.framework.TestCase;

/**
 * Unit test for Airport Baggage Management System
 */
public class AirportBaggageManagementSystemTest extends TestCase {

	private BaggageSystemInputReader baggageSystemInputReader = null;
	private BaggageManagementService baggageMgmtService = null;
	
	protected void setUp() {
		baggageSystemInputReader = BaggageSystemInputReader.getInstance();
		baggageMgmtService = new BaggageManagementServiceImpl();
	}

	public void testOptimalRoutingPath() {
		Graph graph = baggageSystemInputReader.getConveyorGraph();
		String destination = null;
		if (graph != null) {
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
			assertTrue(!sb.toString().isEmpty());
		}
	}

	public void testWithWrongSourceDestination() {
		try {
			Graph graph = baggageSystemInputReader.getConveyorGraph();

			if (graph != null) {
				Conveyor conveyor = new Conveyor("Z1", "Z5", graph);
				baggageMgmtService.getOptimalRoutingPath(conveyor,
						new DijkstraPathFinderImpl());
			}
		} catch (BaggageManagementException bme) {
			assertSame(BaggageManagementConstant.SOURCE_OR_DESTINATION_NOT_PRESENT,bme.getMessage());
		}
	}
	
	protected void tearDown() {
		// releasing resource
		
	}

}
