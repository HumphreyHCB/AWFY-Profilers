import java.io.IOException;
import java.util.ArrayList;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

public class Grapher {
    



    public void Graph(String GraphName, ArrayList<Double> xData, ArrayList<Double> yData) {

        // Create Chart
        XYChart chart = new XYChartBuilder().width(1400).height(1000).title(GraphName).xAxisTitle("Median Runtime of Iteration").yAxisTitle("Percentage of Runtime of Method").build();

        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(16);
        chart.setTitle(GraphName);
        // Series
        chart.addSeries(GraphName, xData, yData);
        
        new SwingWrapper(chart).displayChart();

        try {
            BitmapEncoder.saveBitmap(chart, GraphName, BitmapFormat.PNG);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

}