package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TabableView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import clases.Articulo;
import clases.BD;
import clases.DeustoException;
import clases.Usuario;
import clases.Venta;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import net.miginfocom.swing.MigLayout;
import panel.panelAniadirCamiseta;
import panel.panelAniadirPantalon;
import panel.panelAniadirSudadera;
import panel.panelArticuloHome;
import panel.panelEliminarUsuario;
import panel.panelVentasUsuarios;
import javax.swing.border.BevelBorder;

public class VentanaAdmin extends JFrame {
	public JPanel panelCentroDerechaLista;
	private JPanel contentPane;
	public Connection con;
	public JFrame ventanaAnterior,ventanaActual;
	private JMenu menuHerramientas,menuExit,menuEliminar;
	private JMenuBar menuBar;
	private JPanel panelCNTeliminarArticulo;
	/*private JList<Articulo> listaArticulos;
	private DefaultListModel<Articulo> modeloListaArticulos;*/
	
	private JTable tablaArticulos = new JTable();
	private DefaultTableModel modeloTablaArticulos;
	
	
	/**
	 * TreeModel
	 */
	
	private JTree arbol;
	private DefaultTreeModel modeloArbol;
		
	private static Logger logger = Logger.getLogger( "Admin" );
	//private TreeMap<Integer, Articulo> tmArticulosAdmin = new TreeMap<>();
	
	/**
	 * Create the frame.
	 * @throws DeustoException 
	 */
	public VentanaAdmin(JFrame va,Usuario u) throws DeustoException {
		
		con = BD.initBD("baseDeDatos.db");
		BD.cargarMapaUsuariosDeInfoBBDD(con);
		VentanaInicio.tmArticulos = BD.cargarMapaArticulosDeInfoBBDD(con);
		BD.closeBD(con);
		
		ventanaAnterior = va;
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1650, 950);
		setVisible(true);
		setTitle("ADMIN");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelSur = new JPanel();
		panelSur.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelSur.setBackground(new Color(0, 153, 204));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JPanel panelIZQayadirArticulo = new JPanel();
		panelCentro.add(panelIZQayadirArticulo);
		panelIZQayadirArticulo.setLayout(new BorderLayout(0, 0));
		panelAniadirCamiseta pc = new panelAniadirCamiseta();
		panelIZQayadirArticulo.add(pc);
		panelIZQayadirArticulo.updateUI();
		
		JPanel panelTituloIZQ = new JPanel();
		panelTituloIZQ.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelTituloIZQ.setBackground(new Color(255, 102, 0));
		panelIZQayadirArticulo.add(panelTituloIZQ, BorderLayout.NORTH);
		
		JLabel lblAnyadirArticulo = new JLabel("A\u00D1ADIR ARTICULO");
		lblAnyadirArticulo.setForeground(new Color(255, 255, 255));
		lblAnyadirArticulo.setFont(new Font("Lato", Font.BOLD, 20));
		panelTituloIZQ.add(lblAnyadirArticulo);
		
		JPanel panelIZQSur = new JPanel();
		panelIZQSur.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelIZQSur.setBackground(new Color(255, 102, 0));
		panelIZQayadirArticulo.add(panelIZQSur, BorderLayout.SOUTH);
		
		JPanel panelAniadirCentro = new JPanel();
		panelAniadirCentro.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelAniadirCentro.setBackground(new Color(153, 204, 255));
		panelIZQayadirArticulo.add(panelAniadirCentro, BorderLayout.CENTER);
		
		JButton btnCamiseta = new JButton("Camiseta");
		btnCamiseta.setForeground(new Color(255, 255, 255));
		btnCamiseta.setBackground(new Color(255, 153, 0));
		btnCamiseta.setFont(new Font("Lato", Font.BOLD, 15));
		panelIZQSur.add(btnCamiseta);
		
		JButton btnSudadera = new JButton("Sudadera");
		btnSudadera.setForeground(new Color(255, 255, 255));
		btnSudadera.setBackground(new Color(255, 153, 0));
		btnSudadera.setFont(new Font("Lato", Font.BOLD, 15));
		panelIZQSur.add(btnSudadera);
		
		JButton btnPantalon = new JButton("Pantalon");
		btnPantalon.setForeground(new Color(255, 255, 255));
		btnPantalon.setBackground(new Color(255, 153, 0));
		btnPantalon.setFont(new Font("Lato", Font.BOLD, 15));
		panelIZQSur.add(btnPantalon);
		
		panelCNTeliminarArticulo = new JPanel();
		panelCentro.add(panelCNTeliminarArticulo);
		panelCNTeliminarArticulo.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTituloDCH = new JPanel();
		panelTituloDCH.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelTituloDCH.setBackground(new Color(255, 102, 0));
		panelCNTeliminarArticulo.add(panelTituloDCH, BorderLayout.NORTH);
		
		JLabel lblEliminarArticulo = new JLabel("ELIMINAR ARTICULO");
		lblEliminarArticulo.setForeground(new Color(255, 255, 255));
		lblEliminarArticulo.setFont(new Font("Lato", Font.BOLD, 20));
		panelTituloDCH.add(lblEliminarArticulo);
		
		JPanel panelDCHsur = new JPanel();
		panelDCHsur.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelDCHsur.setBackground(new Color(255, 102, 0));
		panelCNTeliminarArticulo.add(panelDCHsur, BorderLayout.SOUTH);
		
		
		
		JButton btnElimiarArticulo = new JButton("Eliminar Articulo");
		btnElimiarArticulo.setForeground(new Color(255, 255, 255));
		btnElimiarArticulo.setBackground(new Color(255, 153, 0));
		btnElimiarArticulo.setFont(new Font("Lato", Font.BOLD, 15));
		panelDCHsur.add(btnElimiarArticulo);
		
		JPanel panelDerechaCentroLista = new JPanel();
		panelCNTeliminarArticulo.add(panelDerechaCentroLista, BorderLayout.CENTER);
		panelDerechaCentroLista.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTituloListaArticulos = new JPanel();
		panelTituloListaArticulos.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelTituloListaArticulos.setBackground(new Color(102, 204, 255));
		panelDerechaCentroLista.add(panelTituloListaArticulos, BorderLayout.NORTH);
		
		JLabel lblListaArticulos = new JLabel("Lista de articulos");
		lblListaArticulos.setForeground(new Color(255, 255, 255));
		lblListaArticulos.setFont(new Font("Lato", Font.BOLD, 23));
		panelTituloListaArticulos.add(lblListaArticulos);
		
		panelCentroDerechaLista = new JPanel();
		panelCentroDerechaLista.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCentroDerechaLista.setBackground(new Color(153, 204, 255));
		panelDerechaCentroLista.add(panelCentroDerechaLista, BorderLayout.CENTER);
		
		JButton btnInicio = new JButton("INICIO");
		btnInicio.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnInicio.setForeground(new Color(255, 255, 255));
		btnInicio.setBackground(new Color(255, 153, 0));
		panelSur.add(btnInicio);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		menuBar = new JMenuBar();
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBackground(new Color(255, 153, 51));
		panelNorte.add(menuBar);
		
		menuHerramientas = new JMenu("Herramientas");
		menuHerramientas.setForeground(new Color(255, 255, 255));
		menuHerramientas.setBackground(new Color(255, 153, 0));
		menuHerramientas.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(menuHerramientas);
		
		JMenuItem mntmVerVentas = new JMenuItem("Ver Ventas");
		mntmVerVentas.setForeground(new Color(255, 255, 255));
		mntmVerVentas.setBackground(new Color(255, 153, 0));
		mntmVerVentas.setHorizontalAlignment(SwingConstants.LEFT);
		menuHerramientas.add(mntmVerVentas);
		
		menuEliminar = new JMenu("Eliminar");
		menuEliminar.setForeground(new Color(0,0,0));
		menuEliminar.setBackground(new Color(255, 153, 51));
		menuEliminar.setHorizontalAlignment(SwingConstants.LEFT);
		menuHerramientas.add(menuEliminar);
		
		JMenuItem mntmEliminarUsuario = new JMenuItem("Eliminar Usuario");
		mntmEliminarUsuario.setForeground(new Color(255, 255, 255));
		mntmEliminarUsuario.setBackground(new Color(255, 153, 0));
		mntmEliminarUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		menuEliminar.add(mntmEliminarUsuario);
		
		JMenuItem mntmEliminarArticulo = new JMenuItem("Eliminar Articulo");
		mntmEliminarArticulo.setForeground(new Color(255, 255, 255));
		mntmEliminarArticulo.setBackground(new Color(255, 153, 0));
		mntmEliminarArticulo.setHorizontalAlignment(SwingConstants.LEFT);
		menuEliminar.add(mntmEliminarArticulo);
		
		menuExit = new JMenu("Salir");
		menuExit.setForeground(new Color(255, 255, 255));
		menuExit.setBackground(new Color(255, 51, 0));
		menuExit.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(menuExit);
		
		JMenuItem mntmSalir = new JMenuItem("Salir de la app");
		mntmSalir.setBackground(new Color(255, 153, 0));
		mntmSalir.setForeground(new Color(255, 255, 255));
		mntmSalir.setHorizontalAlignment(SwingConstants.LEFT);
		menuExit.add(mntmSalir);
		
		/*modeloListaArticulos = new DefaultListModel<Articulo>();
		listaArticulos = new JList<Articulo>(modeloListaArticulos);
		panelCentroDerechaLista.add(listaArticulos);*/
		
		
		/**
		 * TABLA DE ARTICULOS
		 */
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("ID","TAG","TALLA","PRECIO","COLOR","SEXO"));
		modeloTablaArticulos = new DefaultTableModel(new Vector<Vector<Object>>(),cabeceras);
		//String [] header = {"ID","TAG","TALLA", "PRECIO","COLOR", "SEXO"};
		modeloTablaArticulos.setColumnIdentifiers(cabeceras);
			for(Articulo a : VentanaInicio.tmArticulos.values()) {
				String dataRow[] = {String.valueOf(a.getID()),a.getName(),a.getTalla(),String.valueOf(a.getPrecio()),a.getColor(),a.getSexo()};
				modeloTablaArticulos.addRow(dataRow);	
			}
		panelCentroDerechaLista.setLayout(new BorderLayout(0, 0));
		tablaArticulos.setBackground(new Color(153, 204, 255));
		tablaArticulos.setModel(modeloTablaArticulos);
		panelCentroDerechaLista.add(tablaArticulos);
		

		
		
		
		tablaArticulos.getColumnModel().getColumn(0).setMinWidth(40);
		tablaArticulos.getColumnModel().getColumn(0).setMaxWidth(40);
		tablaArticulos.getColumnModel().getColumn(1).setPreferredWidth(150);
		tablaArticulos.getColumnModel().getColumn(2).setPreferredWidth(35);
		tablaArticulos.getColumnModel().getColumn(3).setMinWidth(40);
		tablaArticulos.getColumnModel().getColumn(3).setMaxWidth(40);		
		tablaArticulos.getColumnModel().getColumn(4).setMinWidth(100);
		tablaArticulos.getColumnModel().getColumn(4).setMaxWidth(100);
		tablaArticulos.getColumnModel().getColumn(5).setMinWidth(100);
		tablaArticulos.getColumnModel().getColumn(5).setMaxWidth(100);
		
		tablaArticulos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if(column == 3) {
					double precio = Double.parseDouble(String.valueOf(value));
					if(precio>15) {
						c.setBackground(Color.red);
						c.setForeground(Color.WHITE);
					}else {
						c.setBackground(Color.white);
						c.setForeground(Color.black);
					}
				}
				return c;
			}
		});
		
		tablaArticulos.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int fila = e.getFirstRow();
				int id = Integer.parseInt((String) modeloTablaArticulos.getValueAt(fila, 0));
				String name = (String) modeloTablaArticulos.getValueAt(fila, 1);
				String talla = (String) modeloTablaArticulos.getValueAt(fila, 2);
				double precio = Double.parseDouble((String) modeloTablaArticulos.getValueAt(fila, 3));
				String color = (String) modeloTablaArticulos.getValueAt(fila, 4);
				String sexo = (String) modeloTablaArticulos.getValueAt(fila, 5);
				
				try {
					con = BD.initBD("baseDeDatos.db");
					BD.modificarArticulo(con, id, name, talla, precio, color, sexo);
					System.out.println("Articulo modificado correctamente");
					logger.log( Level.INFO, "Articulo modificado correctamente" );
					BD.closeBD(con);
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		/*tablaArticulos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if(column==3) {
					if( Integer.parseInt(value.toString()) > 10.0){
						c.setBackground(new Color(100,100,100) );
					}
				}
				return c;
			}
		});*/
		
		
		
		
		
		panelAniadirCentro.removeAll();
		panelAniadirCamiseta pc2 = new panelAniadirCamiseta();
		panelAniadirCentro.add(pc2);
		panelAniadirCentro.updateUI();
		
		/**EVENTOS*/
		
		/**
		 * Boton que vuelve a la ventana Inicio
		 */
		btnInicio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				try {
					new VentanaInicio();
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//new VentanaPerfil(ventanaActual);
			}
		});
		
		btnCamiseta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAniadirCentro.removeAll();
				panelAniadirCamiseta pc = new panelAniadirCamiseta();
				panelAniadirCentro.add(pc);
				panelAniadirCentro.updateUI();
			}
		});
		
		btnPantalon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAniadirCentro.removeAll();
				panelAniadirPantalon pp = new panelAniadirPantalon();
				panelAniadirCentro.add(pp);
				panelAniadirCentro.updateUI();
			}
		});
		
		btnSudadera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAniadirCentro.removeAll();
				panelAniadirSudadera ps = new panelAniadirSudadera();
				panelAniadirCentro.add(ps);
				panelAniadirCentro.updateUI();
			}
		});
		
		btnElimiarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = tablaArticulos.getSelectedRow();
				int id = Integer.parseInt((String) modeloTablaArticulos.getValueAt(index, 0));
				
				try {
					con = BD.initBD("baseDeDatos.db");
					BD.eliminarArticuloBBDD(con,id );
					BD.closeBD(con);
					JOptionPane.showMessageDialog(null, "Artículo eliminado de la BBDD ","DONE", JOptionPane.INFORMATION_MESSAGE);
				} catch (DeustoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				modeloTablaArticulos.removeRow(tablaArticulos.getSelectedRow());
				
				panelCentro.updateUI();
			}
		});
		
		mntmSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		mntmVerVentas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCNTeliminarArticulo.removeAll();
				panelVentasUsuarios pv;
				try {
					pv = new panelVentasUsuarios();
					panelCNTeliminarArticulo.add(pv);
					panelCNTeliminarArticulo.updateUI();
				} catch (DeustoException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		mntmEliminarUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCNTeliminarArticulo.removeAll();
				panelEliminarUsuario pe = new panelEliminarUsuario();
				panelCNTeliminarArticulo.add(pe);
				panelCNTeliminarArticulo.updateUI();
				
			}
		});
		
		mntmEliminarArticulo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				try {
					new VentanaAdmin(ventanaActual, u);
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	

	
	/**
	 * Metodo que permite añadir los aritculos a la lista
	 */
	/*private void anyadirArticulosALista() {
		for(int clave : tmArticulosAdmin.keySet()) {
			Articulo valor = tmArticulosAdmin.get(clave);
			modeloListaArticulos.addElement(valor);
		}
		listaArticulos.setModel(modeloListaArticulos);
	}*/
	
	
}
