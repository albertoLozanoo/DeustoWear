package panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class panelAniadirPantalon extends JPanel {

	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtPrecio;
	private JTextField txtURL;

	/**
	 * Create the panel.
	 */
	public panelAniadirPantalon() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblCamisetaNorte = new JLabel("Pantalon");
		panelNorte.add(lblCamisetaNorte);
		
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnAniadirCamiseta = new JButton("Aniadir Pantalon");
		panelSur.add(btnAniadirCamiseta);
		
		JPanel panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new MigLayout("", "[172.00,grow,center]", "[42.00,bottom][][38.00,bottom][][37.00,bottom][][37.00,bottom][][37.00,bottom][][39.00,bottom][][32.00,bottom][][31.00,bottom][]"));
		
		JLabel lblID = new JLabel("ID");
		panelCentro.add(lblID, "cell 0 0");
		
		txtID = new JTextField();
		panelCentro.add(txtID, "cell 0 1,growx");
		txtID.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		panelCentro.add(lblName, "cell 0 2");
		
		txtName = new JTextField();
		panelCentro.add(txtName, "cell 0 3,growx");
		txtName.setColumns(10);
		
		JLabel lblTalla = new JLabel("Talla");
		panelCentro.add(lblTalla, "cell 0 4");
		
		JComboBox cbTalla = new JComboBox();
		panelCentro.add(cbTalla, "cell 0 5,growx");
		
		JLabel lblPrecio = new JLabel("Precio");
		panelCentro.add(lblPrecio, "cell 0 6");
		
		txtPrecio = new JTextField();
		panelCentro.add(txtPrecio, "cell 0 7,growx");
		txtPrecio.setColumns(10);
		
		JLabel lblColor = new JLabel("Color");
		panelCentro.add(lblColor, "cell 0 8");
		
		JComboBox comboBox = new JComboBox();
		panelCentro.add(comboBox, "cell 0 9,growx");
		
		JLabel lblSexo = new JLabel("Sexo");
		panelCentro.add(lblSexo, "cell 0 10");
		
		JRadioButton rdbtnSexoHombre = new JRadioButton("Hombre");
		panelCentro.add(rdbtnSexoHombre, "flowx,cell 0 11");
		
		JLabel lblImagen = new JLabel("URL (img)");
		panelCentro.add(lblImagen, "cell 0 12");
		
		JRadioButton rdbtnSexoMujer = new JRadioButton("Mujer");
		panelCentro.add(rdbtnSexoMujer, "cell 0 11");
		
		txtURL = new JTextField();
		panelCentro.add(txtURL, "cell 0 13,growx");
		txtURL.setColumns(10);
		
		JLabel lblTipoPantalon = new JLabel("Tipo de pantalon");
		panelCentro.add(lblTipoPantalon, "cell 0 14");
		
		JRadioButton rdbtnTipoPantalonCorto = new JRadioButton("Corto");
		panelCentro.add(rdbtnTipoPantalonCorto, "flowx,cell 0 15,aligny bottom");
		
		JRadioButton rdbtnTipoPantalonLargo = new JRadioButton("Largo");
		panelCentro.add(rdbtnTipoPantalonLargo, "cell 0 15");

	}

}
