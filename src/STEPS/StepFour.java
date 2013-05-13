package STEPS;


import java.awt.BasicStroke;
import java.awt.Color;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import PRINCIPALE.ImageDeFond;


public class StepFour extends JFrame  {
	public StepFour(String title, int W,int H)
	{
		setLayout(null);
		setTitle(title);
		setSize(W, H);
		setResizable(true);
		setVisible(true);
		setLocationRelativeTo(null);

		   ImageDeFond imgfond = new ImageDeFond(new ImageIcon("").getImage(),title);
		   this.add(imgfond);
	}
	
	@SuppressWarnings("deprecation")
	public void DrawGraph(Vector<Double> coordonees,String title,int debutX,int debutY)
	{
		XYSeries series  = new XYSeries(title);;
		double x = 0 , y = 0;
		for(int i = 0 ; i < coordonees.size()-1;i+=2)
		{
		
		x = coordonees.get(i);y = coordonees.get(i+1);
		series.add(x, y);
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		JFreeChart chart = ChartFactory.createXYLineChart(
				"", // Title
				"", // x-axis Label
				"", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				false, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		
		 XYPlot plot = (XYPlot) chart.getPlot();
	        plot.setDomainPannable(true);
	        plot.setRangePannable(true);
	        
	        plot.getRendererForDataset(dataset).setStroke( new BasicStroke(
	                5.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 
	                1.0f, new float[] {10.0f, 6.0f}, 0.0f
	            ));
	        
		ChartPanel crepart = new ChartPanel(chart);
		crepart.setBounds(debutX, debutY, 100,50); 
		add(crepart);
		
	}
	
	

	
}
