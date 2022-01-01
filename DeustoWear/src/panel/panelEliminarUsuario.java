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
		add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTItulo = new JLabel("ELIMINAR USUARIO");
		panelNorte.add(lblTItulo);
		
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
		panelSur.add(btnEliminarUsuario);
		
		JPanel panelCentro = new JPanel();
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
