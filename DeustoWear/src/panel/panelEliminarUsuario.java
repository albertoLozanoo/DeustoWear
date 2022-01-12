package panel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import clases.Articulo;
import clases.BD;
import clases.DeustoException;
import clases.Usuario;
import ventanas.VentanaInicio;
import ventanas.VentanaRegistroo;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.border.BevelBorder;

public class panelEliminarUsuario extends JPanel {

	private JTable tablaUsuarios;
	private DefaultTableModel modeloTablaUsuarios = new DefaultTableModel();	
	public TreeMap<String, String> tmUsuarios = new TreeMap<String, String>();
	public Connection con;
	//private TreeMap<String,Usuario> tmUsuarios = new TreeMap<>();
	/**
	 * Create the panel.
	 */
	public panelEliminarUsuario() {
		setLayout(new BorderLayout(0, 0));
		
		tablaUsuarios = new JTable(modeloTablaUsuarios);
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelNorte.setBackground(new Color(255, 102, 0));
		add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTItulo = new JLabel("ELIMINAR USUARIO");
		lblTItulo.setForeground(new Color(255, 255, 255));
		lblTItulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		panelNorte.add(lblTItulo);
		
		JPanel panelSur = new JPanel();
		panelSur.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
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
		try {
			con = BD.initBD("baseDeDatos.db");
			tmUsuarios = BD.cargarMapaUsuariosDeInfoBBDD(con);
			BD.closeBD(con);
		} catch (DeustoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String nick : tmUsuarios.keySet()) {
				
				String dataRow [] = {nick,tmUsuarios.get(nick)};
				modeloTablaUsuarios.addRow(dataRow);
		}
		tablaUsuarios = new JTable(modeloTablaUsuarios);
		panelCentro.add(tablaUsuarios);
		
		
		btnEliminarUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tablaUsuarios.getSelectedRow();
				String nick = (String) tablaUsuarios.getValueAt(index, 0);
				
				try {
					Connection con = BD.initBD("baseDeDatos.db");
					BD.eliminarUsuarioBBDD(con, nick);
					BD.closeBD(con);
					JOptionPane.showMessageDialog(null, "Usuario eliminado de la BBDD ","DONE", JOptionPane.INFORMATION_MESSAGE);
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				modeloTablaUsuarios.removeRow(tablaUsuarios.getSelectedRow());
				panelCentro.updateUI();
				
			}
		});
		
		
	}
	


}
