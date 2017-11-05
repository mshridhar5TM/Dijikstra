package com.dijikstra.graphics.service;
import java.util.ArrayList;
import java.util.List;

import com.dijikstra.graphics.beans.Vertice;

public class Graph {

	private List<Vertice> graph = new ArrayList<Vertice>();

	public void setVertices(List<Vertice> vertices) {

		this.graph.addAll(vertices);
	}

	public void adicionarVertice(Vertice novoVertice) {

		this.graph.add(novoVertice);
	}

	public List<Vertice> getVertices() {

		return this.graph;
	}

	//Method that returns the vertex whose description is equal to the searched
	public Vertice encontrarVertice(String nome) {

		for (int i = 0; i < this.getVertices().size(); i++) {

			if (nome.equalsIgnoreCase(this.getVertices().get(i).getDescription())) {

				return this.getVertices().get(i);

			}
		}

		return null;

	}

}