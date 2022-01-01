package panel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.BD;
import clases.DeustoException;
import clases.Venta;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class panelVentasUsuarios extends JPanel {
	
	private JTable tablaVentasUsuarios;
	private DefaultTableModel modeloTablaVentasUsuarios = new DefaultTableModel();
	public HashMap<String,ArrayList<Venta>> hmVentasTotales = new HashMap<>();
	private Connection con;

	/**
	 * Create the panel.
	 */
	public panelVentasUsuarios() {
		
		try {
			con = BD.initBD("baseDeDatos.db");
			hmVentasTotales = BD.conseguirVentasTotales(con);
			BD.closeBD(con);
		} catch (DeustoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(255, 102, 0));
		panelNorte.setForeground(new Color(255, 255, 255));
		add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTituloNorte = new JLabel("REGISTRO DE VENTAS");
		lblTituloNorte.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTituloNorte.setForeground(new Color(255, 255, 255));
		panelNorte.add(lblTituloNorte);
		
		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(new Color(153, 204, 255));
		panelCentro.setForeground(new Color(153, 204, 255));
		add(panelCentro, BorderLayout.CENTER);
		
		
		String [] header = {"USUARIO","TOKEN","NUM-ART","PRECIO"};
		modeloTablaVentasUsuarios.setColumnIdentifiers(header);
		modeloTablaVentasUsuarios.setColumnIdentifiers(header);
		for(String value : hmVentasTotales.keySet()) {
			for(ArrayList<Venta> av : hmVentasTotales.values()) {
				for(Venta v : av) {
					String dataRow[] = {value,String.valueOf(v.getToken()),String.valueOf(v.getNumArticulos()),String.valueOf(v.getPrecioTotal())};
					modeloTablaVentasUsuarios.addRow(dataRow);
				}
			}
		}
		
		tablaVentasUsuarios = new JTable(modeloTablaVentasUsuarios);
		panelCentro.add(tablaVentasUsuarios);
	}

}
