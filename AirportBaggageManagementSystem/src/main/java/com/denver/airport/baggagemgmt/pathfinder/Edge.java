package com.denver.airport.baggagemgmt.pathfinder;

/**
 * 
 * This class holds source and destination vertex/node detail along with weight.
 *
 */
public class Edge {
	private final String id;
	private final Vertex source;
	private final Vertex destination;
	private final int weight;

	public Edge(String id, Vertex source, Vertex destination, int weight) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public Vertex getDestination() {
		return destination;
	}

	public Vertex getSource() {
		return source;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(source).append(" ")
				.append(destination).toString();
	}
}
