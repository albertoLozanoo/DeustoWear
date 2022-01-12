package panel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import clases.BD;
import clases.DeustoException;
import clases.Venta;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.BevelBorder;

public class panelVentasUsuarios extends JPanel {
	
	private JTable tablaVentasUsuarios;
	private DefaultTableModel modeloTablaVentasUsuarios = new DefaultTableModel();
	public HashMap<String,ArrayList<Venta>> hmVentasTotales = new HashMap<>();
	
	private Connection con;

	private JTree arbol;
	private DefaultTreeModel modeloArbol;
	
	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws DeustoException 
	 */
	public panelVentasUsuarios() throws DeustoException, SQLException {
		setBackground(new Color(153, 204, 255));
		
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
		panelNorte.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
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
		
		
		String [] header = {"USUARIO","TOKEN","NUM-ART","PRECIO TOTAL","FECHA"};
		modeloTablaVentasUsuarios.setColumnIdentifiers(header);
		/*for(String value : hmVentasTotales.keySet()) {
			for(ArrayList<Venta> av : hmVentasTotales.values()) {
				for(Venta v : av) {
					String dataRow[] = {value,String.valueOf(v.getToken()),String.valueOf(v.getNumArticulos()),String.valueOf(v.getPrecioTotal()),v.getFechaVenta().toString()};
					modeloTablaVentasUsuarios.addRow(dataRow);
				}
			}
		}*/
		ArrayList<Venta> a = new ArrayList<Venta>();
		try {
			con = BD.initBD("baseDeDatos.db");
			a = BD.obtenerComprasUsuario(con, "USUARIOS");
			BD.closeBD(con);
		} catch (DeustoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while(modeloTablaVentasUsuarios.getRowCount()>0)
			modeloTablaVentasUsuarios.removeRow(0);
		for(Venta v : a) {
			String [] fila = {v.nick,String.valueOf(v.getToken()),String.valueOf(v.getNumArticulos()),String.valueOf(v.getPrecioTotal()),sdf.format(v.getFechaVenta())};
			modeloTablaVentasUsuarios.addRow(fila);
		}
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		tablaVentasUsuarios = new JTable(modeloTablaVentasUsuarios);
		tablaVentasUsuarios.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tablaVentasUsuarios.setBackground(new Color(255, 255, 255));
		panelCentro.add(tablaVentasUsuarios);
		
		tablaVentasUsuarios.getColumnModel().getColumn(0).setMinWidth(40);
		tablaVentasUsuarios.getColumnModel().getColumn(0).setMaxWidth(120);
		tablaVentasUsuarios.getColumnModel().getColumn(1).setPreferredWidth(90);
		tablaVentasUsuarios.getColumnModel().getColumn(2).setPreferredWidth(10);
		tablaVentasUsuarios.getColumnModel().getColumn(3).setPreferredWidth(30);
		tablaVentasUsuarios.getColumnModel().getColumn(4).setPreferredWidth(200);
		
		
		
		JPanel panelDerechoTreeModel = new JPanel();
		panelDerechoTreeModel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelDerechoTreeModel.setBackground(new Color(153, 204, 255));
		panelCentro.add(panelDerechoTreeModel, BorderLayout.EAST);
		
		crearModeloArbol();
		arbol = new JTree(modeloArbol);
		arbol.setFont(new Font("Tahoma", Font.PLAIN, 12));
		arbol.setForeground(new Color(255, 153, 0));
		arbol.setBackground(new Color(153, 204, 255));
		panelDerechoTreeModel.add(arbol);
		
		arbol.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath t = e.getPath();
				String nick = t.getLastPathComponent().toString();
				System.out.println(nick);
				ArrayList<Venta> a = new ArrayList<Venta>();
				try {
					con = BD.initBD("baseDeDatos.db");
					a = BD.obtenerComprasUsuario(con, nick);
					BD.closeBD(con);
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				while(modeloTablaVentasUsuarios.getRowCount()>0)
					modeloTablaVentasUsuarios.removeRow(0);
				for(Venta v : a) {
					String [] fila = {v.nick,String.valueOf(v.getToken()),String.valueOf(v.getNumArticulos()),String.valueOf(v.getPrecioTotal()),sdf.format(v.getFechaVenta())};
					modeloTablaVentasUsuarios.addRow(fila);
				}
				
			}
		});
		
	}
	
	/**
	 * Metodo que crea el arbol de usuarios cargando cada nodo con un nombre de usuario registrado en la BBDD
	 * @throws DeustoException
	 * @throws SQLException
	 */
	private void crearModeloArbol() throws DeustoException, SQLException {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("USUARIOS");
		modeloArbol = new DefaultTreeModel(raiz);
		con = BD.initBD("baseDeDatos.db");
		ArrayList<String> nombresUsuarios = BD.conseguirNombresDeUsuariosDeLasVentas(con);
		BD.closeBD(con);
		int i=0;
		for(String name: nombresUsuarios) {
			DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(name);
			modeloArbol.insertNodeInto(nodo, raiz, i);
			i++;
		}
		
	}

}
