package com.dijikstra.graphics.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dijikstra.graphics.beans.Vertice;

public class Dijkstra {

	// Attributes used in the Find Smallest Path function

	// List that stores the vertices belonging to the smallest path found
	List<Vertice> smallestPath = new ArrayList<Vertice>();

	// Variable that receives the vertices belonging to the smallest path
	Vertice verticePath = new Vertice();

	// Variable that keeps the vertex being visited
	Vertice current = new Vertice();

	// Variable that marks the neighbor of the currently visited vertex
	Vertice neighbor = new Vertice();

	// List of vertices that have not yet been visited
	List<Vertice> notVisited = new ArrayList<Vertice>();

	// Dijikstra's Algorithm
	public List<Vertice> findSmallestPathDijkstra(Graph graph, Vertice v1, Vertice v2) {

		// Adds the source to the shortest path list
		smallestPath.add(v1);

		// Placing in initial distances
		for (int i = 0; i < graph.getVertices().size(); i++) {

			// Vertice current has distance zero, and all others
			// 9999 ( "infinite")
			if (graph.getVertices().get(i).getDescription().equals(v1.getDescription())) {

				graph.getVertices().get(i).setDistance(0);

			} else {

				graph.getVertices().get(i).setDistance(9999);

			}
			// Inserts the vertex in the list of unvisited vertices
			this.notVisited.add(graph.getVertices().get(i));
		}

		Collections.sort(notVisited);

		// The algorithm continues until all vertices are visited
		while (!this.notVisited.isEmpty()) {

			/*
			 * Always take the vertice as a shorter distance, than the first one
			 * gives list
			 */

			current = this.notVisited.get(0);
			System.out.println("Vertex visited  " + current);
			/*
			 * For each neighbor (each edge), it is calculated its possible
			 * distance, adding distance from the current vertex to that of the
			 * edge corresponding. If this distance is less than the neighbor's
			 * distance, it is updated.
			 */
			for (int i = 0; i < current.getEdges().size(); i++) {

				neighbor = current.getEdges().get(i).getDestination();
				System.out.println("Looking at the neighbor of" + current + ": " + neighbor);
				if (!neighbor.isVisited()) {

					// Comparing the distance of the neighbor with the possible
					// distance
					if (neighbor.getDistance() > (current.getDistance() + current.getEdges().get(i).getWeight())) {

						neighbor.setDistance(current.getDistance() + current.getEdges().get(i).getWeight());
						neighbor.setFather(current);

						/*
						 * If the neighbor is the searched vertex, and a change
						 * has been made in the distance, the list with the
						 * smallest previous path is deleted, since there is a
						 * minor parent vertex path, until the vertex origin.
						 */
						if (neighbor == v2) {
							smallestPath.clear();
							verticePath = neighbor;
							smallestPath.add(neighbor);
							while (verticePath.getFather()!= null) {

								smallestPath.add(verticePath.getFather());
								verticePath = verticePath.getFather();

							}
							//Sorts the list of the shortest path, so that it is displayed from the source to the destination
							Collections.sort(smallestPath);

						}
					}
				}

			}
			//Checks the current vertex as visited and removes it from the list of not
			// visited
			current.isVisited();
			this.notVisited.remove(current);
			/*
			 *Sort the list, so that the vertice with the lowest distance is in the
			 * first position
			 */

			Collections.sort(notVisited);
			System.out.println("Not Visited:" + notVisited);

		}

		return smallestPath;
	}

}
