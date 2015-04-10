package com.denver.airport.baggagemgmt.pathfinder;

import java.util.Set;

/**
 * 
 * This class holds vertex and edges detail
 *
 */
public class Graph {
	private final Set<Vertex> vertexes;
	private final Set<Edge> edges;

	public Graph(Set<Vertex> vertexes, Set<Edge> edges) {
		this.vertexes = vertexes;
		this.edges = edges;
	}

	public Set<Vertex> getVertexes() {
		return vertexes;
	}

	public Set<Edge> getEdges() {
		return edges;
	}

}
