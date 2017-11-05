package com.dijikstra.graphics.beans;

public class Edges {

	private int weight;
	private Vertice source;
	private Vertice destination;
	public Edges(Vertice source, Vertice destination) {
		super();
		this.source = source;
		this.destination = destination;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Vertice getSource() {
		return source;
	}
	public void setSource(Vertice source) {
		this.source = source;
	}
	public Vertice getDestination() {
		return destination;
	}
	public void setDestination(Vertice destination) {
		this.destination = destination;
	}

	
}