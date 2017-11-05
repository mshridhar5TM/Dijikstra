package com.dijikstra.graphics.beans;
import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice> {

	private String description;
	private int distance;
	private boolean visited = false;
	private Vertice father;
	private List<Edges> edges = new ArrayList<Edges>();
	private List<Vertice> neighbors = new ArrayList<Vertice>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Vertice getFather() {
		return father;
	}

	public void setFather(Vertice father) {
		this.father = father;
	}

	public List<Edges> getEdges() {
		return edges;
	}

	public void setEdges(List<Edges> edges) {
		this.edges = edges;
	}

	public List<Vertice> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(List<Vertice> neighbors) {
		this.neighbors = neighbors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + distance;
		result = prime * result + ((edges == null) ? 0 : edges.hashCode());
		result = prime * result + ((father == null) ? 0 : father.hashCode());
		result = prime * result + ((neighbors == null) ? 0 : neighbors.hashCode());
		result = prime * result + (visited ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (distance != other.distance)
			return false;
		if (edges == null) {
			if (other.edges != null)
				return false;
		} else if (!edges.equals(other.edges))
			return false;
		if (father == null) {
			if (other.father != null)
				return false;
		} else if (!father.equals(other.father))
			return false;
		if (neighbors == null) {
			if (other.neighbors != null)
				return false;
		} else if (!neighbors.equals(other.neighbors))
			return false;
		if (visited != other.visited)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String s = " ";
		s += this.getDescription();
		return s;
	}

	public int compareTo(Vertice vertice) {
		if (this.getDistance() < vertice.getDistance())
			return -1;
		else if (this.getDistance() == vertice.getDistance())
			return 0;

		return 1;

	}

}