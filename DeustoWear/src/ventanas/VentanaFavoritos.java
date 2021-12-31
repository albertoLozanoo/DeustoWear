package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Articulo;
import clases.BD;
import clases.DeustoException;
import clases.Usuario;
import clases.Venta;;

public class VentanaFavoritos extends JFrame {

	private JPanel contentPane;
	public static Connection con;
	public static String nombreBD = "baseDeDatos.db";
	private JFrame ventanaActual, ventanaAnterior;
	 
	private JList<Articulo> listaArticulosFavoritos;
	private DefaultListModel<Articulo> modeloArticulosFavoritos; 
	
	private JTable tablaArticulos;
	private DefaultTableModel modeloTablaArticulos= new DefaultTableModel();
		
	private JScrollPane scrollListaArticulosFavoritos;
	

	/**
	 * Create the frame.
	 * @param ventanaActual2 
	 */
	
	public VentanaFavoritos(JFrame va, Usuario u) {
		ventanaAnterior = va;
		ventanaActual = this;
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Favoritos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1046, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));
		contentPane.setBackground(new Color(0, 153, 255));
		
		
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setBackground(new Color(255, 102, 51));
		panelSur.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton btnWearHome = new JButton("Home");
		btnWearHome.setFont(new Font("Lato", Font.PLAIN, 19));
		btnWearHome.setBackground(new Color(204, 102, 51));
		panelSur.add(btnWearHome);
		
		JButton btnFavoritos = new JButton("Favorites");
		btnFavoritos.setFont(new Font("Lato", Font.PLAIN, 19));
		btnFavoritos.setForeground(new Color(255, 255, 255));
		btnFavoritos.setEnabled(false);
		btnFavoritos.setBackground(new Color(204, 102, 51));
		panelSur.add(btnFavoritos);
		
		JButton btnCesta = new JButton("Cesta");
		btnCesta.setFont(new Font("Lato", Font.PLAIN, 19));
		btnCesta.setBackground(new Color(204, 102, 51));
		panelSur.add(btnCesta);
		
		JButton btnPerfil = new JButton("Profile");
		btnPerfil.setFont(new Font("Lato", Font.PLAIN, 19));
		btnPerfil.setBackground(new Color(204, 102, 51));
		panelSur.add(btnPerfil);
		
		
		
		JPanel panelNorte = new JPanel();
		getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setBackground(new Color(255, 102, 51));
		
		JLabel lblCabecera = new JLabel("Articulos favoritos de " + Usuario.getNick());
		lblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCabecera.setBackground(new Color(255, 255, 255));
		lblCabecera.setFont(new Font("Lato", Font.BOLD, 28));
		lblCabecera.setForeground(new Color(255, 255, 255));
		panelNorte.add(lblCabecera, "cell 1 0,alignx center,aligny center");
		panelNorte.add(lblCabecera);
		
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		panelOeste.setBackground(new Color(0, 153, 255));
	
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Lato", Font.PLAIN, 19));
		btnEliminar.setBackground(new Color(204, 102, 51));
		panelOeste.add(btnEliminar);
		
		JButton btnLimpiar = new JButton("Borrar todo");
		btnLimpiar.setFont(new Font("Lato", Font.PLAIN, 19));
		btnLimpiar.setBackground(new Color(204, 102, 51));
		panelOeste.add(btnLimpiar);
		
		
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setBackground(new Color(255, 153, 51));
		panelCentral.setLayout(new GridLayout(1, 1, 0, 0));
		
		/*modeloArticulosFavoritos = new DefaultListModel<Articulo>();
		listaArticulosFavoritos = new JList<Articulo>(modeloArticulosFavoritos);
		scrollListaArticulosFavoritos = new JScrollPane(listaArticulosFavoritos);
		panelCentral.add(scrollListaArticulosFavoritos);*/
		
		
		String [] header = {"TAG","TALLA", "PRECIO","COLOR", "SEXO"};
		modeloTablaArticulos.setColumnIdentifiers(header);
		for(Articulo a : u.getFavoritos()) {
			String dataRow[] = {a.getName(),a.getTalla(),String.valueOf(a.getPrecio()),a.getColor(),a.getSexo()};
			modeloTablaArticulos.addRow(dataRow);	
		}
		
		tablaArticulos = new JTable(modeloTablaArticulos);
		JScrollPane scroll =new JScrollPane(tablaArticulos);
		panelCentral.add(tablaArticulos);
		
		
		
		//cargarFavoritosDelFichero();
		setVisible(true);
		
		/**
		 * Boton que lleva a la ventana Home
		 */
		btnWearHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				try {
					new VentanaHome(ventanaActual,u);
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		/**
		 * Boton que lleva a la ventana Perfil
		 */
		btnPerfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				try {
					new VentanaPerfil(ventanaActual,u);
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//new VentanaPerfil(ventanaActual);
			}
		});
		
		/**
		 * Boton que permite eliminar un fvorito de su Array
		 */
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int index = tablaArticulos.getSelectedRow();
				u.eliminarFavorito(index);
				modeloTablaArticulos.removeRow(tablaArticulos.getSelectedRow());
				JOptionPane.showMessageDialog(null, "Articulo eliminado de favoritos ","DONE", JOptionPane.INFORMATION_MESSAGE);
				panelCentral.updateUI();	
			}
		});
		
		
		btnLimpiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				modeloTablaArticulos.setRowCount(0);
				u.limpiarFavoritos();
				JOptionPane.showMessageDialog(null, "Lista de favoritos eliminada ","DONE", JOptionPane.INFORMATION_MESSAGE);
				panelCentral.updateUI();	
			}
		});
		
		/**
		 * Boton que lleva a la ventana Home
		 */
		btnWearHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				try {
					new VentanaHome(ventanaActual,u);
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//new VentanaPerfil(ventanaActual);
			}
		});
		
		/**
		 * Boton que lleva a la ventana Perfil
		 */
		btnPerfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				try {
					new VentanaPerfil(ventanaActual,u);
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//new VentanaPerfil(ventanaActual);
			}
		});
		
		/**
		 * Boton que lleva a la ventana Cesta
		 */
		btnCesta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaCesta(ventanaActual,u);
				//new VentanaPerfil(ventanaActual);
			}
		});
	}

	/**
	 * Metodo que permite cargar los favoritos del usuario en una lista
	 */
	/*private void cargarFavoritosDelFichero() {
		// TODO Auto-generated method stub
		modeloArticulosFavoritos.removeAllElements();
		for(Articulo v: Usuario.getFavoritos()) {
			modeloArticulosFavoritos.addElement(v);
		}
		listaArticulosFavoritos.setModel(modeloArticulosFavoritos);
		
	}*/
	
	/**
	 * Metodo que lee el fichero txt del usuario y lo carga en un array 
	 * @return Array con los articulos favoritos, pero en forma de string
	 */
	/*
	private ArrayList<String> leerTxt(){
		ArrayList<String> afavoritostxt= new ArrayList<String>();
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(Usuario.getNick()));
			ArrayList<String> afavoritosleidos= new ArrayList<>();
			String temp ="";
			String bfRead;
			while((bfRead = bf.readLine()) != null) {
				temp = bfRead;
				afavoritosleidos.add(temp);
			}
			bf.close();
		
		afavoritostxt = afavoritosleidos;
			
		}catch (Exception e) {
			System.err.println("No se encontró el fichero");
		}
		
		return afavoritostxt;
	}
	lo dejo comentado porque falta implementarlo*/

	
}


