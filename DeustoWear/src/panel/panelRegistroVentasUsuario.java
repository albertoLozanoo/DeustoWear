package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class panelRegistroVentasUsuario extends JPanel {

	/**
	 * Create the panel.
	 */
	public panelRegistroVentasUsuario() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		panelSur.add(btnVolver);
		
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("REGISTRO DE TODAS LAS VENTAS");
		panelNorte.add(lblTitulo);

	}

}
