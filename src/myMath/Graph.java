package myMath;
import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class Graph extends JFrame {
    /** java obligating Version */
	private static final long serialVersionUID = 1L;

	/**show polynom on screen usin gral library.
	 * link for github:
	 * https://github.com/eseifert/gral
	 * 
	 * show also the extremums points.
	 * 
	 * calculate the area. print it and show on the title window screen.*/
	public Graph() {
		//star point and end point to show.
		double start = -2;
		double end = 6;
		double eps = 0.01;//eps size steps,
		
		//shoe graph from hare 
		//https://github.com/eseifert/gral/wiki/xyplot
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

		DataTable data = new DataTable(Double.class, Double.class);
		Polynom_able p = new Polynom("0.2x^4+-1.5x^3+3.0x^2+-x+-5");
        for (double x = start; x <=end; x+=eps) {
        	double y = p.f(x); 
            data.add(x, y);
        }
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);
        PointRenderer pontRenderer =  plot.getPointRenderers(data).get(0);
        plot.removePointRenderer(data, pontRenderer);//show just clear line.
        plot.getLineRenderers(data).get(0).setColor(color);
        
        //find extremums(nokodot kichon) and show
        DataTable dataExtremum = findExtremums(p,start,end,eps);
        plot.add(1,dataExtremum,true);
        plot.getPointRenderers(dataExtremum).get(0).setColor(Color.RED);
        
        //calculateArea
        calculateArea(p, start, end, eps);
    }
	
	/** 
	 * find the extremums(nokodot kichon).
	 * derivative the polynom, and search x value that close to zero in approximated epsilon.
	 * add is to dataTable and return.
	 * 
	 * @param p - some polynom.
	 * @param start - start point.
	 * @param end - end point.
	 * @param eps - eps size steps
	 * @return dataTable with extremums points(x and y).
	 */
	private DataTable findExtremums(Polynom_able p,double start,double end,double eps) {
		DataTable data = new DataTable(Double.class, Double.class);
		Polynom_able der = p.derivative();
		
		//search between every single part in size eps.
		 for (double x = start; x <= end; x+=eps) {
			 double y0 = der.f(x);
			 double y1 = der.f(x + eps);
			 
			//if y0*y1 is negative this mean have point equal to zero between the pert.
			 if(y0*y1 <= 0) {
				 //find the point in approximated eps, and add this to data.
				 double extremumX = p.root(x, x + eps, eps);
				 data.add(extremumX,p.f(extremumX));				
			 }
		 }
		 
		return data;
	}
	/**calculate the area on top function and bottom to axis x. 
	 * 
	 * @param p - some polynom.
	 * @param start - start point.
	 * @param end - end point.
	 * @param eps - eps size steps
	 */
	private void calculateArea(Polynom_able p,double start,double end,double eps) {
		double startArea = 0;
		double endArea;
		double sumArea = 0;
		
		//search between every single part in size eps.
		for (double x = start; x <= end; x+=eps) {
			double y0 = p.f(x);
			 double y1 = p.f(x + eps);
			 
			//if y0*y1 is negative this mean have point equal to zero between the pert.
			 if(y0*y1 <= 0) {
				 
				 if(y1 <= 0) {//the area start here
					 startArea = p.root(x, x+eps, eps);
				 }
				 
				 if(y0 <= 0) {//the area end here
					 endArea = p.root(x, x+eps, eps);
					 sumArea += p.area(startArea, endArea, eps);				
				 }
				 
			 }
		}
		
		//print
		String print = "area is: "+sumArea;
		System.out.println(print);
		setTitle(print);
		
	}

	/** show the  Graph*/
    public static void main(String[] args) {
        Graph frame = new Graph();
        frame.setVisible(true);
    }
}