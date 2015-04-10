package com.denver.airport.baggagemgmt.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.denver.airport.baggagemgmt.AirportBaggageManagementSystem;
import com.denver.airport.baggagemgmt.bean.BagDetail;
import com.denver.airport.baggagemgmt.pathfinder.Edge;
import com.denver.airport.baggagemgmt.pathfinder.Graph;
import com.denver.airport.baggagemgmt.pathfinder.Vertex;

/**
 * 
 * This is utility singleton class to parse the input file and populate conveyor
 * system graph, departures and bags detail.
 * 
 * makes parseInputFile() as public so that input file can be passed from other services.
 *  
 */
public class BaggageSystemInputReader {

	private static final String EDGE = "Edge_";
	private static final String CONVEYOR_SYSTEM = "Conveyor System";
	private static final String SECTION = "Section";

	// holds airport conveyor system graph
	private static Graph graph;
	// holds <flight_id>, <flight_gate> departure information
	private static Map<String, String> departuresMap = new HashMap<String, String>();
	// holds bags detail
	private static List<BagDetail> bagList = new LinkedList<BagDetail>();

	// Private constructor prevents instantiation from other classes
	private BaggageSystemInputReader() {
		readInputFile("input.txt", " ");
	}

	/**
	 * LazyHolder is loaded on the first execution of
	 * BaggageSystemUtil.getInstance() or the first access to
	 * LazyHolder.INSTANCE, not before.
	 */
	private static class LazyHolder {
		private static final BaggageSystemInputReader INSTANCE = new BaggageSystemInputReader();
	}

	public static BaggageSystemInputReader getInstance() {
		return LazyHolder.INSTANCE;
	}

	public Graph getConveyorGraph() {
		return graph;
	}

	public Map<String, String> getDepartures() {
		return departuresMap;
	}

	public List<BagDetail> getBagDetails() {
		return bagList;
	}
	
	/**
	 * This can be called from other service if want to provide other input file name to process.
	 * 
	 * @param file
	 * @param delimiter
	 */
	public static void parseInputFile(String file, String delimiter) {
		InputStream in = null;
		try {
			in = AirportBaggageManagementSystem.class.getClassLoader()
					.getResourceAsStream(file);
			parseInputFile(in, delimiter);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void readInputFile(String file, String delimiter) {
		InputStream in = null;
		try {
			in = AirportBaggageManagementSystem.class.getClassLoader()
					.getResourceAsStream(file);
			parseInputFile(in, delimiter);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.err.println("InputStream is not closed");
				}
			}
		}
	}
	
	private static void parseInputFile(InputStream in, String delimiter) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(in);
			// in input file, section order should be same
			readConveyorGraphFromFile(scanner, delimiter);
			readDeparturesInfoFromFile(scanner, delimiter);
			readBagsInfoFromFile(scanner, delimiter);
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	private static void readConveyorGraphFromFile(Scanner scanner,
			String delimiter) {
		Set<Vertex> vertexes = new HashSet<Vertex>();
		Set<Edge> edges = new HashSet<Edge>();
		int edgeCounter = 0;
		String line = scanner.nextLine();
		if (scanner.hasNextLine() && line.contains(CONVEYOR_SYSTEM)) {
			line = scanner.nextLine();
			while (line != null && !line.contains(SECTION)) {
				String[] values = line.split(delimiter);
				String point1 = values[0];
				String point2 = values[1];
				int weight = Integer.parseInt(values[2]);

				Vertex vertext1 = new Vertex(point1, point1);
				Vertex vertext2 = new Vertex(point2, point2);
				vertexes.add(vertext1);
				vertexes.add(vertext2);

				// adding bi-directional edge mapping
				Edge edge1 = new Edge(EDGE + edgeCounter++, vertext1, vertext2,
						weight);
				Edge edge2 = new Edge(EDGE + edgeCounter++, vertext2, vertext1,
						weight);
				edges.add(edge1);
				edges.add(edge2);

				line = scanner.nextLine();
			}
			graph = new Graph(vertexes, edges);
		}
	}

	private static void readDeparturesInfoFromFile(Scanner scanner,
			String delimiter) {
		String line = scanner.nextLine();
		while (line != null && !line.contains(SECTION)) {
			String[] values = line.split(delimiter);
			departuresMap.put(values[0], values[1]);
			line = scanner.nextLine();
		}
	}

	private static void readBagsInfoFromFile(Scanner scanner, String delimiter) {
		String line = scanner.nextLine();
		while (line != null && !line.contains(SECTION)) {
			String[] values = line.split(delimiter);
			// <bag_number> <entry_point> <flight_id>
			bagList.add(new BagDetail(values[0], values[1], values[2]));
			if (scanner.hasNextLine()) {
				line = scanner.nextLine();
			} else {
				line = null;
			}
		}
	}
}
