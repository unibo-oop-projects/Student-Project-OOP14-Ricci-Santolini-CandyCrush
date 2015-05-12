package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.Controller;
import controller.Utility;

/**
 * Classe che disegna la schermata principale del gioco con la matrice di caramelle, label con i punti, le mosse e l'target
 * che devono aggiornarsi ad ogni mossa.
 * 
 * @author Beatrice Ricci
 */
public class PlayGameMenu extends AbstractMenu implements IPlayMenu {

	private static final long serialVersionUID = 6751617017799096695L;
	
	private static final int WIDTH_DIMENSION = 250;
	private static final int HEIGHT_DIMENSION = 150;
	
	private final JPanel matrixPanel;
	
	private final Butt[][] matrix = new Butt[Utility.DIM1][Utility.DIM2];
	private final Controller c = new Controller(this);
		
	private final JLabel step = new JLabel(" ");
	private final JLabel tot = new JLabel(" 0 punti"); 
	private final JLabel target = new JLabel(" ");
	private final JLabel level = new JLabel(" ");
	
	/**
	 * Costruttore della classe contenente la schermata principale di gioco.
	 */
	public PlayGameMenu() {
		
		this.setSize(Utility.DIM1 * Utility.HBUTT + WIDTH_DIMENSION, Utility.DIM1 * Utility.LBUTT + HEIGHT_DIMENSION);
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel = new JPanel(new BorderLayout());
		
		this.matrixPanel = new JPanel(new GridLayout(Utility.DIM1, Utility.DIM2));
		this.matrixPanel.setSize(Utility.DIM1 * Utility.HBUTT, Utility.DIM2 * Utility.LBUTT);
		
		new SetPlayPanels().updatePanel(panel, this.matrixPanel, this.level, this.step, this.target, this.tot);
		this.updateStep(c.getModelStep());
		this.drawFirstMatrix();
		this.getContentPane().add(panel);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * Metodo per la creazione iniziale della schermata di gioco. In base agli interi che identificano il colore
	 * presenti nella matrice di Element vengono creati dei Butt (e aggiunti alla matrice matrix[][]) con le rispettive icone.
	 */
	private void drawFirstMatrix() {
		for (int i = 0; i < Utility.DIM1; i++) {
			for (int j = 0; j < Utility.DIM2; j++) {
				matrix[i][j] = new Butt(i, j);
				if (this.c.getModelNum(i, j) == Utility.BLUE) {
					matrix[i][j].setIcon(IconUtility.BLUEIC);
				} else if (this.c.getModelNum(i, j) == Utility.YELLOW) {
					matrix[i][j].setIcon(IconUtility.YELLOWIC);
				} else if (this.c.getModelNum(i, j) == Utility.GREEN) {
					matrix[i][j].setIcon(IconUtility.GREENIC);
				} else if (this.c.getModelNum(i, j) == Utility.VIOLET) {
					matrix[i][j].setIcon(IconUtility.VIOLETIC);
				} else if (this.c.getModelNum(i, j) == Utility.ORANGE) {
					matrix[i][j].setIcon(IconUtility.ORANGEIC);
				} else if (this.c.getModelNum(i, j) == Utility.RED) {
					matrix[i][j].setIcon(IconUtility.REDIC);
				}
				matrix[i][j].addActionListener(c.getObserver());
				matrixPanel.add(matrix[i][j]);	
			}
		}
	}
	
	@Override
	public void updateTarget(final int n) {
		this.target.setText("target: " + n + " p");
		this.c.setModelTarget(n);
	}
	
	@Override
	public void setDiff(final String s) {
		this.level.setText(s + " LEVEL");
	}
	
	@Override
	public void updateStep(final int n) {
		this.step.setText("  " + n + "  ");
		this.c.setModelStep(n);
	}
	
	@Override
	public void updateTot() {
		this.tot.setText(" " + c.getModelTot() + " punti");
	}
	
	@Override
	public Butt getAMatrixButt(final int i, final int j) {
		return this.matrix[i][j];
	}
}