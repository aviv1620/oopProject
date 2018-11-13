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
    public Graph() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        DataTable data = new DataTable(Double.class, Double.class);
        Polynom p = new Polynom("0.2x^4+-1.5x^3+3.0x^2+-x+-5");
        for (double x = -2.0; x <= 6.0; x+=0.5) {
        	double y = p.f(x); 
            data.add(x, y);
        }
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);
        plot.getPointRenderers(data).get(0).setColor(color);
        plot.getLineRenderers(data).get(0).setColor(color);
    }

    public static void main(String[] args) {
        Graph frame = new Graph();
        frame.setVisible(true);
    }
}