import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.VectorGraphicsEncoder.VectorGraphicsFormat;
import org.knowm.xchart.PdfboxGraphicsEncoder;
import org.knowm.xchart.VectorGraphicsEncoder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.SeriesMarkers;


public class Grapher {
    



    public void Graph(String GraphName, ArrayList<Double> xData, ArrayList<Double> yData) {

        // Create Chart
        XYChart chart = new XYChartBuilder().width(1400).height(1000).title(GraphName).xAxisTitle("Median Runtime of Iteration").yAxisTitle("Percentage of Runtime of Method").build();

        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(12);
        chart.setTitle(GraphName);
        // Series
        chart.addSeries(GraphName, xData, yData);
        
        new SwingWrapper(chart).displayChart();

        
    }
    public void multiGraph(String GraphName, HashMap<String,ArrayList<Double>> xDatas, HashMap<String,ArrayList<Double>> yDatas) {

        XYChart chart = new XYChartBuilder().width(1200).height(1400).title(GraphName).xAxisTitle("Median Run Time of Invocation").yAxisTitle("% of Run Time").theme(ChartTheme.Matlab).build();

        for (String key : xDatas.keySet()) {
            System.out.println(key);
            ArrayList<Double> tempx = xDatas.get(key);
            ArrayList<Double> tempy = yDatas.get(key);
            for (int i = 0; i < tempx.size(); i++) {
                System.out.print(tempx.get(i)+" ");
            }
            System.out.println();
            System.out.println();
            for (int i = 0; i < tempy.size(); i++) {
                System.out.print(tempy.get(i)+" ");
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();

        }

        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
        chart.getStyler().setMarkerSize(8);
        
        chart.getStyler().setAxisTitlePadding(9);
        chart.getStyler().setAxisTitleFont(new Font("Verdana", Font.PLAIN, 20));
        chart.getStyler().setChartTitleFont(new Font("Verdana", Font.PLAIN, 20));
        chart.getStyler().setAxisTickLabelsFont(new Font("Verdana", Font.PLAIN, 12));

         //chart.getStyler().setAxisTickMarksStroke(BasicStroke.)

        Marker[] seriesMarkers = new Marker[] {SeriesMarkers.CIRCLE, SeriesMarkers.SQUARE, SeriesMarkers.DIAMOND, SeriesMarkers.TRIANGLE_UP, SeriesMarkers.TRIANGLE_DOWN, SeriesMarkers.CROSS};
        chart.getStyler().setSeriesMarkers(seriesMarkers);
        // chart.getStyler().setXAxisMax(100d);
        // chart.getStyler().setXAxisMin(0d);

        chart.getStyler().setYAxisMax(100d);
        chart.getStyler().setYAxisMin(0d);
        chart.getStyler().setXAxisMin(0d);
        chart.setTitle(GraphName);
        // Series
        for (String key : xDatas.keySet()) {
            chart.addSeries(key, xDatas.get(key), yDatas.get(key));
        }
        
        chart.getStyler().setLegendFont(new Font("Verdana", Font.PLAIN, 12));
        new SwingWrapper(chart).displayChart();

        try {
            BitmapEncoder.saveBitmap(chart, ""+GraphName, BitmapFormat.PNG);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

}