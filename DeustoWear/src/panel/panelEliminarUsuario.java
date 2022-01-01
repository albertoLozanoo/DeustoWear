package panel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Articulo;
import clases.Usuario;
import ventanas.VentanaInicio;

import java.awt.BorderLayout;
import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class panelEliminarUsuario extends JPanel {

	private JTable tablaUsuarios;
	private DefaultTableModel modeloTablaUsuarios;	
	//private TreeMap<String,Usuario> tmUsuarios = new TreeMap<>();
	/**
	 * Create the panel.
	 */
	public panelEliminarUsuario() {
		setLayout(new BorderLayout(0, 0));
		
		tablaUsuarios = new JTable(modeloTablaUsuarios);
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(255, 102, 0));
		add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTItulo = new JLabel("ELIMINAR USUARIO");
		lblTItulo.setForeground(new Color(255, 255, 255));
		lblTItulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		panelNorte.add(lblTItulo);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(255, 102, 0));
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
		btnEliminarUsuario.setForeground(new Color(255, 255, 255));
		btnEliminarUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEliminarUsuario.setBackground(new Color(255, 153, 0));
		panelSur.add(btnEliminarUsuario);
		
		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(new Color(153, 204, 255));
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		String [] header = {"NICK","CONTRASEYA"};
		modeloTablaUsuarios.setColumnIdentifiers(header);
		for(String nick : VentanaInicio.tmUsuarios.keySet()) {
			for(Usuario u: VentanaInicio.tmUsuarios.values()) {
				String dataRow [] = {u.getNick(),u.getContraseya()};
				modeloTablaUsuarios.addRow(dataRow);
			}
		}
		panelCentro.add(tablaUsuarios);
		
		
		
	}

}
