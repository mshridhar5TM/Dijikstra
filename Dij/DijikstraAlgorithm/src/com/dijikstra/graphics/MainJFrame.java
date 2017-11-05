package com.dijikstra.graphics;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dijikstra.graphics.beans.Vertice;
import com.dijikstra.graphics.service.Dijkstra;
import com.dijikstra.graphics.service.DrawPanel;
import com.dijikstra.graphics.service.Graph;



public class MainJFrame extends JFrame {

	/**
	 * @author Shridhar Sangamkar
	 * 05 Nov,2017
	 */
	private static final long serialVersionUID = 1L;
	private DrawPanel drawPanel = new DrawPanel();

	public MainJFrame() {
		setTitle("Diikstra's algorithm");
		setSize(750, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		// south
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new BorderLayout());
		final JTextField arg0 = new JTextField(20);
		arg0.setText("teste.txt");
		final JTextField arg1 = new JTextField(20);
		arg1.setText("v1");
		final JTextField arg2 = new JTextField(20);
		arg2.setText("v4");
		final JTextField res = new JTextField(50);
		panelSouth.add(arg0, BorderLayout.WEST);
		panelSouth.add(arg1, BorderLayout.CENTER);
		panelSouth.add(arg2, BorderLayout.EAST);
		panelSouth.add(res, BorderLayout.SOUTH);

		// adding
		c.add(panelSouth, BorderLayout.SOUTH);
		c.add(drawPanel, BorderLayout.CENTER);

		JButton okButton = new JButton("OK");
		c.add(okButton, BorderLayout.NORTH);
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String a0 = arg0.getText();
				String a1 = arg1.getText();
				String a2 = arg2.getText();

				Graph test = new Graph();
				test.setVertices(ViewProfile.dijGraph(a0));
				Vertice i1 = new Vertice();
				Vertice i2 = new Vertice();
				i1 = test.encontrarVertice(a1);
				i2 = test.encontrarVertice(a2);

				List<Vertice> resultado = new ArrayList<Vertice>();
				Dijkstra algorithm = new Dijkstra();
				resultado = algorithm.findSmallestPathDijkstra(test, i1, i2);

				drawPanel.draw(test.getVertices());
				drawPanel.updateUI();

				System.out.println("This is the smallest path made by the algorithm:" + resultado);
				res.setText("This is the smallest path made by the algorithm:" + resultado);
			}
		});

		this.setVisible(true);
		this.setResizable(false);
	}

	public static void main(String[] args) {
		new MainJFrame();
	}

}