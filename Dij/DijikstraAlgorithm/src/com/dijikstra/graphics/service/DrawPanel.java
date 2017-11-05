package com.dijikstra.graphics.service;
/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */

/* ----------------------
 * JGraphAdapterDemo.java
 * ----------------------
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * Original Author:  Barak Naveh
 * Contributor(s):   -
 *
 * $Id: JGraphAdapterDemo.java 725 2010-11-26 01:24:28Z perfecthash $
 *
 * Changes
 * -------
 * 03-Aug-2003 : Initial revision (BN);
 * 07-Nov-2003 : Adaptation to JGraph 3.0 (BN);
 *
 */

import java.awt.*;
import java.awt.geom.*;
import java.util.List;

import javax.swing.*;

import org.jgraph.*;
import org.jgraph.graph.*;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

// resolve ambiguity
import org.jgrapht.graph.DefaultEdge;

import com.dijikstra.graphics.beans.Edges;
import com.dijikstra.graphics.beans.Vertice;

/**
 * @author Shridhar Sangamkar
 */
public class DrawPanel extends JPanel {
	// ~ Static fields/initializers
	// ---------------------------------------------

	private static final long serialVersionUID = 3256444702936019250L;
	@SuppressWarnings("unused")
	private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
	private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

	private JGraphModelAdapter<String, MyEdge> jgAdapter;

	public DrawPanel() {
		this.setBackground(Color.white);
		this.setSize(400, 400);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	@SuppressWarnings("deprecation")
	public void draw(List<Vertice> vertices) {
		ListenableGraph<String, MyEdge> g = new ListenableDirectedMultigraph<String, MyEdge>(MyEdge.class);
		jgAdapter = new JGraphModelAdapter<String, MyEdge>(g);

		JGraph jgraph = new JGraph(jgAdapter);
		adjustDisplaySettings(jgraph);

		this.removeAll();
		this.setLayout(new BorderLayout());
		this.add(jgraph, BorderLayout.CENTER);

		resize(DEFAULT_SIZE);

		for (Vertice v : vertices) {
			g.addVertex(v.getDescription().trim());
		}

		for (Vertice v1 : vertices) {
			for (Edges a : v1.getEdges())
				g.addEdge(v1.getDescription().trim(), a.getDestination().getDescription().trim(),
						new MyEdge(a.getWeight() + ""));
		}

		int size = vertices.size();
		int i = 0;
		int x = 20;
		int y = 20;

		for (Vertice v : vertices) {

			positionVertexAt(v.getDescription().trim(), x, y);
			x += 150;
			if (i == size / 2) {
				x = 20;
				y += 200;
			}

			i++;
		}

	}

	private void adjustDisplaySettings(JGraph jg) {
		jg.setPreferredSize(DEFAULT_SIZE);

		Color c = Color.white;

		jg.setBackground(c);
	}

	@SuppressWarnings("unchecked")
	private void positionVertexAt(Object vertex, int x, int y) {
		DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
		AttributeMap attr = cell.getAttributes();
		Rectangle2D bounds = GraphConstants.getBounds(attr);

		Rectangle2D newBounds = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());

		GraphConstants.setBounds(attr, newBounds);

		AttributeMap cellAttr = new AttributeMap();
		cellAttr.put(cell, attr);
		jgAdapter.edit(cellAttr, null, null, null);
	}

	/**
	 * a listenable directed multigraph that allows loops and parallel edges.
	 */
	private static class ListenableDirectedMultigraph<V, E> extends DefaultListenableGraph<V, E>
			implements DirectedGraph<V, E> {
		private static final long serialVersionUID = 1L;

		ListenableDirectedMultigraph(Class<E> edgeClass) {
			super(new DirectedMultigraph<V, E>(edgeClass));
		}
	}

	class MyEdge extends DefaultEdge {

		/**
		 * @author Shridhar Sangamkar 05 Nov 2017
		 */
		private static final long serialVersionUID = 1L;
		private String label;

		public MyEdge(String label) {
			this.label = label;
		}

		@Override
		public String toString() {
			return label;
		}
	}
}
