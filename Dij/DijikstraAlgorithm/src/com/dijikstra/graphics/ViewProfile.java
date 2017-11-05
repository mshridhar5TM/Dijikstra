package com.dijikstra.graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.dijikstra.graphics.beans.Edges;
import com.dijikstra.graphics.beans.Vertice;
import com.dijikstra.graphics.service.Dijkstra;
import com.dijikstra.graphics.service.Graph;

public class ViewProfile {

	public static List<Vertice> dijGraph(String file) {

		Graph g = new Graph();
		Vertice v;
		File f = new File(file);
		String vertices[];
		String line;
		ArrayList<String[]> s1 = new ArrayList<String[]>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(f));

			Map<String, Vertice> map = new HashMap<String, Vertice>();

			while ((line = br.readLine()) != null) {

				if (line.contains(",")) {
					s1.add(line.split("/"));
					vertices = s1.get(0)[0].split(",");

					v = (Vertice) map.get(vertices[0]);
					if (v == null)
						v = new Vertice();

					List<Vertice> verticeNeighbors = new ArrayList<Vertice>();
					List<Edges> currentEdge = new ArrayList<Edges>();
					v.setDescription(vertices[0]);
					map.put(vertices[0], v);

					if (line.contains("/")) {

						String edgeWeight[] = s1.get(0)[1].split(",");

						for (int i = 1; i < vertices.length; i++) {
							Vertice vertice;
							vertice = map.get(vertices[i]);
							if (vertice == null)
								vertice = new Vertice();
							vertice.setDescription(vertices[i]);
							verticeNeighbors.add(vertice);
							map.put(vertices[i], vertice);

							Edges ait = new Edges(v, vertice);
							ait.setWeight(Integer.parseInt(edgeWeight[i - 1]));
							currentEdge.add(ait);

						}
						v.setNeighbors(verticeNeighbors);
						v.setEdges(currentEdge);

					}

				}

				// Vertices finais
				else {

					v = (Vertice) map.get(line);
					if (v == null)
						v = new Vertice();
					v.setDescription(line);
					map.put(line, v);

				}

				g.adicionarVertice(v);
				s1.clear();

			}

			br.close();
		} catch (FileNotFoundException e) {
			
			System.out.println("Did not find the file");
			e.printStackTrace();
		}
		// catch do readLine
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g.getVertices();
	}

	public static void main(String args[]) {

		Graph teste = new Graph();

		teste.setVertices(dijGraph(args[0]));
		Vertice i1 = new Vertice();
		Vertice i2 = new Vertice();
		i1 = teste.encontrarVertice(args[1]);
		i2 = teste.encontrarVertice(args[2]);

		List<Vertice> result = new ArrayList<Vertice>();
		Dijkstra algorithm = new Dijkstra();
		result = algorithm.findSmallestPathDijkstra(teste, i1, i2);

		System.out.println("This is the smallest path made by the algorithm:"
				+ result);
	}

}