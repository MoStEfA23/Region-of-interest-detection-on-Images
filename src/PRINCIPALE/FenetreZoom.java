package PRINCIPALE;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class FenetreZoom extends JFrame {
	
	static int poX=310,poY=213;
	
	public FenetreZoom(String titre,int W,int H){
		setLayout(null);
		setTitle(titre);
		setSize(W, H);
		setResizable(false);
		setVisible(true);
	    setLocationRelativeTo(null);
		
		setAlwaysOnTop(true);
		setLocation(poX, poY);
		poX+=20;poY-=10;
		
		
	}
   
	public void DrawGraphe(Vector<Double> coordonees,String title,String Xaxis,String Yaxis){
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
				title, // Title
				Xaxis, // x-axis Label
				Yaxis, // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		
		
		// set a few custom plot features
		    XYPlot plot = (XYPlot) chart.getPlot();
	        plot.setDomainPannable(true);
	        plot.setRangePannable(true);
	        plot.setBackgroundPaint(new Color(0xffffe0));
	        plot.setDomainGridlinePaint(Color.lightGray);//les ligne des y
            plot.setRangeGridlinePaint(Color.lightGray);//les ligne des x
            

	     // render shapes and lines
	        XYLineAndShapeRenderer renderer =new XYLineAndShapeRenderer(true, true);
	        plot.setRenderer(renderer);
        // set the renderer's stroke
	        Stroke stroke = new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
	        renderer.setBaseOutlineStroke(stroke);
	        
	        renderer.setUseOutlinePaint(true);
	        renderer.setSeriesOutlinePaint(0, Color.BLUE);//somme
	        
	        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
	        //renderer.setURLGenerator(new StandardXYURLGenerator());
 
	     // label the points
	        
            renderer.setSeriesPaint(0, Color.RED);//la ligne
	    	    
          plot.getRendererForDataset(dataset).setStroke( new BasicStroke(3f, BasicStroke.JOIN_ROUND, BasicStroke.JOIN_BEVEL)  );
         
          
         
          
		ChartPanel crepart = new ChartPanel(chart);
		crepart.setBounds(0,0, 600,300); 
		
		 
		add(crepart);
	}
	/*********************************Methode2*********************************/
	public void DrawNuageDePoint(double [] x,double[] y){
		  XYSeries series = new XYSeries("nuage de points");
		  for(int i=0;i<144;i++){
		    series.add(x[i], y[i]);
		  }
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series);
			
			JFreeChart chart = ChartFactory.createScatterPlot(
					"representation des imagettes dans un nuage de points", // Title
					"L'entropie deux", // x-axis Label
					"l'aire", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Plot Orientation
					true, // Show Legend
					true, // Use tooltips
					false // Configure chart to generate URLs?
					);
			
			 XYPlot plot = (XYPlot) chart.getPlot();
			 plot.setBackgroundPaint(new Color(0xF0DDF4));
			// render shapes and lines
	        XYLineAndShapeRenderer renderer =new XYLineAndShapeRenderer(false, true);
	        plot.setRenderer(renderer);
			 // set the renderer's stroke
	        Stroke stroke = new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
	        renderer.setBaseOutlineStroke(stroke);
	        
	        renderer.setUseOutlinePaint(true);
	        renderer.setSeriesOutlinePaint(0, Color.magenta);//somme
	        renderer.setSeriesPaint(0, Color.BLACK);//la ligne
			
			 ChartPanel crepart = new ChartPanel(chart);
			 crepart.setBounds(0, 0, 915, 510);
			 add(crepart);
	}
}