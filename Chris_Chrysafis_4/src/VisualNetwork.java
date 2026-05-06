import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class VisualNetwork extends JFrame{
	
	public VisualNetwork(Registry registry) {
		Graph<String, String> graph = new SparseGraph<>();
		
		for(Suspect suspect:registry.getSuspects()) {
			graph.addVertex(suspect.getCodeName());
		}
		int count = 0;
		for(Suspect suspect:registry.getSuspects()) {
			for(Suspect susp:suspect.getPartners()) {
				graph.addEdge(count+"grammi", suspect.getCodeName(), susp.getCodeName());
				count++;
			}
			
		}
		
		Layout<String, String> layout = new CircleLayout<String, String>(graph);
		layout.setSize(new Dimension(350, 350));
		
		BasicVisualizationServer<String, String> visualaizationServer = new BasicVisualizationServer<String, String>(layout);
		visualaizationServer.setSize(new Dimension(450, 450));
		visualaizationServer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
		JPanel panel = new JPanel();
		
		panel.add(visualaizationServer);
		
		setContentPane(panel);
		
		setSize(500, 500);
		setTitle("Suspects Network");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
