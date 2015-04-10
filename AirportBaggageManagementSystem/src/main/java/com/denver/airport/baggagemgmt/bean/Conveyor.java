package com.denver.airport.baggagemgmt.bean;

import com.denver.airport.baggagemgmt.pathfinder.Graph;

/**
 * 
 * This class holds conveyor details
 * 
 */
public class Conveyor {

	private String source;
	private String destination;
	private Graph conveyorGraph;

	public Conveyor() {

	}

	public Conveyor(String source, String destination, Graph conveyorGraph) {
		super();
		this.source = source;
		this.destination = destination;
		this.conveyorGraph = conveyorGraph;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Graph getConveyorGraph() {
		return conveyorGraph;
	}

	public void setConveyorGraph(Graph conveyorGraph) {
		this.conveyorGraph = conveyorGraph;
	}
}
