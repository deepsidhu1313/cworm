
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.AttributedString;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

/**
 * A pie chart with a custom label generator.
 */
public class chart extends JPanel {
    JPanel cpnl;
   String readl,savel,name;
   JFreeChart jfc;
   ChartPanel chartPanel;
    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public chart( String title,String saveas, String input) {

       // super(title);
        try{
            savel= saveas;
            readl=input;
            name=title;
            final PieDataset dataset = createSampleDataset();
        
        final JFreeChart chart = createChart(dataset);
         chartPanel = new ChartPanel(chart);
         
      jfc= chartPanel.getChart();
       saveChart(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
        ///setContentPane(chartPanel);
        }catch (Exception io){}
    }
    
    public JFreeChart addchart(){
    
    return jfc;
    }
    
    
    public ChartPanel addcp(){
    
    return chartPanel;
    }
    
    
   	public void saveChart(JFreeChart chart) {
		
		try {
			/**
			 * This utility saves the JFreeChart as a JPEG First Parameter:
			 * FileName Second Parameter: Chart To Save Third Parameter: Height
			 * Of Picture Fourth Parameter: Width Of Picture
			 */
			ChartUtilities.saveChartAsPNG(new File(savel), chart, 600, 400);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Problem occurred creating chart.");
		}
	}
    /**
     * Creates a sample dataset for the demo.
     * 
     * @return A sample dataset.
     */
    public PieDataset createSampleDataset() throws IOException{
        
        final DefaultPieDataset result = new DefaultPieDataset();
       
        BufferedReader bReader =new BufferedReader(new FileReader(readl));
		    String s;
		    while ((s=bReader.readLine())!=null){
		      String datavalue [] = s.split("&&@");
		      String category = datavalue[0];
		      String value = datavalue [1];
		      result.setValue(category, Double.parseDouble(value));
		    }
		    bReader.close();

        
        

        result.setValue("Null", new Double(-10.0));
        return result;
        
    }
    
   /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A chart.
     *
    private*/public JFreeChart createChart(final PieDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createPieChart3D(
           "Scan Result\n Modeling & Detection Of C-Worm",  // chart title
            dataset,                // data
            true,                   // include legend
            true,
            true
        );
        chart.setBackgroundPaint(Color.ORANGE);
        double zoom=10;
        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        plot.setLabelGenerator(new chart.CustomLabelGenerator());
        plot.zoom(zoom);
        plot.setBackgroundPaint(Color.WHITE);
        //plot.setLabelPaint(Color.ORANGE);
        Font ft=new Font("Default",Font.PLAIN, 13);
       plot.setLabelFont(ft);
       plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} =  ({2})"));
        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {1} "));
        // plot.setLabelBackgroundPaint(Color.GRAY);
        return chart;
        
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        //final chart demo = new chart("Pie Chart 3D Demo 4");
        //demo.pack();
        //RefineryUtilities.centerFrameOnScreen(demo);
        //demo.setVisible(true);

    }
    
    /**
     * A custom label generator (returns null for one item as a test).
     */
    static class CustomLabelGenerator implements PieSectionLabelGenerator {
        
        /**
         * Generates a label for a pie section.
         * 
         * @param dataset  the dataset (<code>null</code> not permitted).
         * @param key  the section key (<code>null</code> not permitted).
         * 
         * @return the label (possibly <code>null</code>).
         */
        public String generateSectionLabel(final PieDataset dataset, final Comparable key) {
            String result = null;    
            if (dataset != null) {
                if (!key.equals("Null")) {
                    result = key.toString();   
                }
            }
            return result;
        }
        @Override
        public AttributedString generateAttributedSectionLabel(PieDataset pd, Comparable cmprbl) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
   
    }

}
