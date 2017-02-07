package gui;

import data.sensors.ChartSensor;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************

/*
    NOTE FROM VALENTIN:
    
    Es gibt bei JFreeChart auch neben dem offiziellen guide viele gratis tutorials...

    ihr solltet allerdings mit der linechart das meiste abdecken können :)
*/

public class LineChart extends JPanel {

    private final XYSeriesCollection dataset;
    
    public LineChart(String chartname, String x, String y){      
        dataset = new XYSeriesCollection();
        final JFreeChart chart = createChart(chartname, x, y, dataset);
        
        this.setPreferredSize(new java.awt.Dimension(400, 200));
        final ChartPanel chartPanel = new ChartPanel(chart,400,200,
            200, 100, 500,
            300, true, true,
            true, true, true, true);
        this.add(chartPanel);
    }
    
    public LineChart(String chartname, String x, String y, String seriesname, ArrayList<ChartSensor> data) {        
        dataset = new XYSeriesCollection();
        addNewSeries(seriesname, data);
        final JFreeChart chart = createChart(chartname, x, y, dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        this.add(chartPanel);
    }

    private void addNewSeries(String name, ArrayList<ChartSensor> data){
        
        final XYSeries s = new XYSeries(name);
        
        for (ChartSensor sensor : data) {
            s.add(sensor.getData().getX(), sensor.getData().getY());
        }
       
        dataset.addSeries(s);
    }
    
    private void addToExisting(String name, ArrayList<ChartSensor> data){
        
        XYSeries s = dataset.getSeries(name);
        for (ChartSensor sensor : data) {
            s.add(sensor.getData().getX(), sensor.getData().getY());
        }
    }
    
    public void addSeries(String name, ArrayList<ChartSensor> data){
        for (Object s : dataset.getSeries()) {
            if(((XYSeries)s).getKey().equals(name)){
                addToExisting(name, data);
                return;
            }
        }
        
        addNewSeries(name, data);
    }
    
    /**
     * Creates a chart.
     *
     * @param dataset the data for the chart.
     *
     * @return a chart.
     */
    private JFreeChart createChart(String charttitle, String x, String y, final XYDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                charttitle, // chart title
                x, // x axis label
                y, // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
        chart.setAntiAlias(false);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
        //      legend.setDisplaySeriesShapes(true);
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
       
        plot.setBackgroundPaint(Color.lightGray);
        //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        //final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        final XYSplineRenderer r = new XYSplineRenderer();
        plot.setRenderer(r);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }

}
