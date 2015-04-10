package com.denver.airport.baggagemgmt.pathfinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.denver.airport.baggagemgmt.BaggageManagementException;
import com.denver.airport.baggagemgmt.util.BaggageManagementConstant;

/**
 * 
 * Dijkstra algorithm for path finder.
 *
 */
public class DijkstraPathFinderImpl implements PathFinder {

	private Set<Vertex> nodes;
	private Set<Edge> edges;
	private Set<Vertex> visitedNodes;
	private Set<Vertex> notVisitedNodes;
	private Map<Vertex, Vertex> predecessors;
	private Map<Vertex, Integer> distance;

	public void calculateVertexDistanceFromSource(Vertex source) {
		visitedNodes = new HashSet<Vertex>();
		notVisitedNodes = new HashSet<Vertex>();
		distance = new HashMap<Vertex, Integer>();
		predecessors = new HashMap<Vertex, Vertex>();
		distance.put(source, 0);
		notVisitedNodes.add(source);
		while (notVisitedNodes.size() > 0) {
			Vertex node = getMinimum(notVisitedNodes);
			visitedNodes.add(node);
			notVisitedNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Vertex node) {
		List<Vertex> adjacentNodes = getNeighbors(node);
		for (Vertex target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node)
					+ getDistance(node, target)) {
				distance.put(target,
						getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				notVisitedNodes.add(target);
			}
		}
	}

	private int getDistance(Vertex node, Vertex target) {
		for (Edge edge : edges) {
			if (edge.getSource().equals(node)
					&& edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		throw new BaggageManagementException(
				BaggageManagementConstant.NO_FOUND_SOURCE_DESTINATION);
	}

	private List<Vertex> getNeighbors(Vertex node) {
		List<Vertex> neighbors = new ArrayList<Vertex>();
		for (Edge edge : edges) {
			if (edge.getSource().equals(node)
					&& !isVisited(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	private Vertex getMinimum(Set<Vertex> vertexes) {
		Vertex minimum = null;
		for (Vertex vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isVisited(Vertex vertex) {
		return visitedNodes.contains(vertex);
	}

	private int getShortestDistance(Vertex destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public LinkedList<Vertex> getPath(Vertex target) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		Vertex step = target;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}

	public String findShortestPath(String source, String destination,
			Graph graph) {

		// working on defensive copy of array
		edges = new HashSet<Edge>(graph.getEdges());
		nodes = new HashSet<Vertex>(graph.getVertexes());

		Vertex sourceVertex = new Vertex(source, source);
		Vertex destinationVertex = new Vertex(destination, destination);

		if(!nodes.contains(sourceVertex) || !nodes.contains(destinationVertex))
		{
			throw new BaggageManagementException(BaggageManagementConstant.SOURCE_OR_DESTINATION_NOT_PRESENT);
		}
		
		// calculate the cost/distance of each node/vertex from source vertex
		calculateVertexDistanceFromSource(sourceVertex);

		// get shortest path of source to destination
		LinkedList<Vertex> path = getPath(destinationVertex);

		StringBuilder sb = new StringBuilder();
		for (Vertex vertex : path) {
			sb.append(vertex.toString());
			sb.append(" ");
		}
		sb.append(":");
		// get total cost/distance of destination node/vertex
		sb.append(getDistance(destinationVertex));

		return sb.toString();
	}

	public Map<Vertex, Integer> getDistance() {
		return distance;
	}

	private int getDistance(Vertex vertex) {
		return distance.get(vertex);
	}
}
