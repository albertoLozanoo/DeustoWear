package panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import clases.BD;
import clases.Camiseta;
import clases.DeustoException;
import clases.Sudadera;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import java.awt.Color;

public class panelAniadirSudadera extends JPanel {
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtPrecio;
	private JTextField txtURL;
	private Connection con;

	/**
	 * Create the panel.
	 */
	public panelAniadirSudadera() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(255, 153, 0));
		add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblCamisetaNorte = new JLabel("Sudadera");
		lblCamisetaNorte.setBackground(new Color(255, 153, 0));
		lblCamisetaNorte.setForeground(new Color(255, 255, 255));
		lblCamisetaNorte.setFont(new Font("Tahoma", Font.BOLD, 25));
		panelNorte.add(lblCamisetaNorte);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(153, 204, 255));
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnAniadirSudadera = new JButton("Aniadir");
		btnAniadirSudadera.setForeground(new Color(255, 255, 255));
		btnAniadirSudadera.setBackground(new Color(255, 153, 0));
		btnAniadirSudadera.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelSur.add(btnAniadirSudadera);
		
		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(new Color(153, 204, 255));
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new MigLayout("", "[172.00,grow,center]", "[42.00,bottom][][38.00,bottom][][37.00,bottom][28.00][37.00,bottom][27.00][37.00,bottom][29.00][39.00,bottom][36.00][32.00,bottom][30.00][33.00,bottom][42.00]"));
		
		JLabel lblID = new JLabel("ID");
		lblID.setForeground(new Color(0, 0, 102));
		lblID.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelCentro.add(lblID, "cell 0 0");
		
		txtID = new JTextField();
		txtID.setForeground(new Color(255, 255, 255));
		txtID.setBackground(new Color(255, 153, 0));
		panelCentro.add(txtID, "cell 0 1,growx");
		txtID.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(0, 0, 102));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelCentro.add(lblName, "cell 0 2");
		
		txtName = new JTextField();
		txtName.setForeground(new Color(255, 255, 255));
		txtName.setBackground(new Color(255, 153, 0));
		panelCentro.add(txtName, "cell 0 3,growx");
		txtName.setColumns(10);
		
		JLabel lblTalla = new JLabel("Talla");
		lblTalla.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTalla.setForeground(new Color(0, 0, 102));
		panelCentro.add(lblTalla, "cell 0 4");
		
		JComboBox cbTalla = new JComboBox();
		cbTalla.setForeground(new Color(255, 255, 255));
		cbTalla.setBackground(new Color(255, 153, 0));
		panelCentro.add(cbTalla, "cell 0 5,growx");
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecio.setForeground(new Color(0, 0, 102));
		panelCentro.add(lblPrecio, "cell 0 6");
		
		txtPrecio = new JTextField();
		txtPrecio.setForeground(new Color(255, 255, 255));
		txtPrecio.setBackground(new Color(255, 153, 0));
		panelCentro.add(txtPrecio, "cell 0 7,growx");
		txtPrecio.setColumns(10);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblColor.setForeground(new Color(0, 0, 102));
		panelCentro.add(lblColor, "cell 0 8");
		
		JComboBox cbColor = new JComboBox();
		cbColor.setForeground(new Color(255, 255, 255));
		cbColor.setBackground(new Color(255, 153, 0));
		panelCentro.add(cbColor, "cell 0 9,growx");
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setForeground(new Color(0, 0, 102));
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelCentro.add(lblSexo, "cell 0 10");
		
		JRadioButton rdbtnSexoHombre = new JRadioButton("Hombre");
		rdbtnSexoHombre.setBackground(new Color(153, 204, 255));
		rdbtnSexoHombre.setForeground(new Color(255, 255, 255));
		panelCentro.add(rdbtnSexoHombre, "flowx,cell 0 11");
		
		JLabel lblImagen = new JLabel("URL (img)");
		lblImagen.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblImagen.setForeground(new Color(0, 0, 102));
		panelCentro.add(lblImagen, "cell 0 12");
		
		JRadioButton rdbtnSexoMujer = new JRadioButton("Mujer");
		rdbtnSexoMujer.setForeground(new Color(255, 255, 255));
		rdbtnSexoMujer.setBackground(new Color(153, 204, 255));
		panelCentro.add(rdbtnSexoMujer, "cell 0 11");
		
		txtURL = new JTextField();
		panelCentro.add(txtURL, "cell 0 13,growx");
		txtURL.setColumns(10);
		
		JLabel lblCapucha = new JLabel("Capucha");
		lblCapucha.setForeground(new Color(0, 0, 102));
		lblCapucha.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelCentro.add(lblCapucha, "cell 0 14");
		
		JRadioButton rdbtnConCapucha = new JRadioButton("Con capucha");
		rdbtnConCapucha.setForeground(new Color(255, 255, 255));
		rdbtnConCapucha.setBackground(new Color(153, 204, 255));
		panelCentro.add(rdbtnConCapucha, "flowx,cell 0 15");
		
		JRadioButton rdbtnSinCapucha = new JRadioButton("Sin capucha");
		rdbtnSinCapucha.setBackground(new Color(153, 204, 255));
		rdbtnSinCapucha.setForeground(new Color(255, 255, 255));
		panelCentro.add(rdbtnSinCapucha, "cell 0 15");
		
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
		
		ButtonGroup bgCapucha = new ButtonGroup();
		bgCapucha.add(rdbtnConCapucha);
		bgCapucha.add(rdbtnSinCapucha);
		
		btnAniadirSudadera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt((txtID.getText()));
				String ERname = "(camiseta){1}[0-9]{1,3}";
				String name = txtName.getText();
				boolean correctoName = Pattern.matches(ERname, name);
				String talla = (String) cbTalla.getSelectedItem();
				Double precio = Double.parseDouble((txtPrecio.getText()));
				String color =(String) cbColor.getSelectedItem();
				String sexo = "";
				if(rdbtnSexoHombre.isSelected()) {
					sexo = "Hombre";
				}else if(rdbtnSexoMujer.isSelected()){
					sexo = "Mujer";
				}
				String url = txtURL.getText();
				String ERurl = "(0-9){1,3}";
				boolean correctoUrl = Pattern.matches(ERurl, url);
				String img =  "imagenes/sudaderas/"+url+".png";
				String capucha = "";
				if(rdbtnConCapucha.isSelected()) {
					capucha = "Con capucha";
				}else if(rdbtnSinCapucha.isSelected()){
					capucha = "Sin capucha";
				}
				
				try {
					con = BD.initBD("baseDeDatos.db");
					int existeArticulo = BD.existeArticulo(con, id);
					BD.closeBD(con);
					if(id < 1000 && id > 0 && precio < 100.0 && precio > 0.0 && existeArticulo == 0 && id!=0 && !name.equals("")&& !talla.equals("Seleccione una talla...") && precio>0.0 && !color.equals("Seleccione un color...") && !sexo.equals("") && !img.equals("")) {
						Sudadera s = new Sudadera(id,name,talla,precio,color,sexo,img,capucha);
						System.out.println(s);
						JOptionPane.showMessageDialog(null, "Articulo registrado con exito", "APPROVED", JOptionPane.INFORMATION_MESSAGE);
						con = BD.initBD("baseDeDatos.db");
						BD.insertarSudaderaBBDD(con, s);
						BD.closeBD(con);
						System.out.println("Sudadera insertada con exito en la BBDD");
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
