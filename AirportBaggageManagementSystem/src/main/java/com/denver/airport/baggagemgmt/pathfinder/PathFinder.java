package com.denver.airport.baggagemgmt.pathfinder;


/**
 * 
 * This interface allow client to use multiple implementation of path finder
 * alogorithms.
 * 
 */
public interface PathFinder {
	/**
	 * Get shortest path string in below format
	 * <Node_1> <Node_2> [<Node_3>, …] : <total_cost>
	 *  
	 * @param source
	 * @param destination
	 * @param graph, weighted graph
	 * @return
	 */
	public String findShortestPath(String source, String destination, Graph graph);
}
