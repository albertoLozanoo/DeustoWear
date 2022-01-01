package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import ventanas.VentanaInicio;

import javax.swing.JTextField;





import clases.BD;
import clases.Camiseta;
import clases.DeustoException;
import enumeration.Colores;
import enumeration.Sexo;
import enumeration.Talla;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class panelAniadirCamiseta extends JPanel {
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtPrecio;
	private JTextField txtURL;
	private Connection con;

	/**
	 * Create the panel.
	 */
	public panelAniadirCamiseta() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblCamisetaNorte = new JLabel("Camiseta");
		panelNorte.add(lblCamisetaNorte);
		
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnAniadirCamiseta = new JButton("Aniadir Camiseta");
		panelSur.add(btnAniadirCamiseta);
		
		JPanel panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new MigLayout("", "[172.00,grow,center]", "[42.00,bottom][][38.00,bottom][][37.00,bottom][][37.00,bottom][][37.00,bottom][][39.00,bottom][][32.00,bottom][]"));
		
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
		
		JComboBox cbColor = new JComboBox();
		panelCentro.add(cbColor, "cell 0 9,growx");
		
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
		
		ArrayList<String> colores = new ArrayList<>();
		colores.add("AZUL");
		colores.add("BLANCO");
		colores.add("DEUSTO");
		colores.add("NARANJA");
		colores.add("NEGRO");
		
		String sel = "Seleccione un color...";
		cbColor.addItem(sel);
		for(String c: colores) {
			cbColor.addItem(c);
		}
	
		
		ArrayList<String> tallas = new ArrayList<>();
		tallas.add("XS");
		tallas.add("S");
		tallas.add("M");
		tallas.add("L");
		tallas.add("XL");
		tallas.add("XXL");
		
		String sel2 = "Seleccione una talla...";
		cbTalla.addItem(sel2);
		for(String t:tallas) {
			cbTalla.addItem(t);
		}

		ButtonGroup bgSexo = new ButtonGroup();
		bgSexo.add(rdbtnSexoHombre);
		bgSexo.add(rdbtnSexoMujer);
		
		btnAniadirCamiseta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt((txtID.getText()));
				String name = txtName.getText();
				String talla = (String) cbTalla.getSelectedItem();
				Double precio = Double.parseDouble((txtPrecio.getText()));
				String color =(String) cbColor.getSelectedItem();
				String sexo = "";
				if(rdbtnSexoHombre.isSelected()) {
					sexo = "Hombre";
				}else if(rdbtnSexoMujer.isSelected()){
					sexo = "Mujer";
				}
				String img =  "imagenes/camisetas/"+txtURL.getText()+".png";
				
				try {
					con = BD.initBD("baseDeDatos.db");
					int existeArticulo = BD.existeArticulo(con, id);
					BD.closeBD(con);
					if(existeArticulo == 0 && id!=0 && !name.equals("")&& !talla.equals("Seleccione una talla...") && precio>0.0 && !color.equals("Seleccione un color...") && !sexo.equals("") && !img.equals("")) {
						Camiseta c = new Camiseta(id,name,talla,precio,color,sexo,img);
						System.out.println(c);
						JOptionPane.showMessageDialog(null, "Articulo registrado con exito", "APPROVED", JOptionPane.INFORMATION_MESSAGE);
						con = BD.initBD("baseDeDatos.db");
						BD.insertarCamisetaBBDD(con, c);
						BD.closeBD(con);
						System.out.println("Camiseta insertada con exito en BBDD");
					}else {
						JOptionPane.showMessageDialog(null, "ERROR, Es posible que:\n	+Ese ID ya esta registrado\n	+Algun campo esta vacio","ERROR", JOptionPane.ERROR_MESSAGE);
						txtID.setText("");
					}
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
	}
}
