package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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

import java.awt.Font;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import panel.panelArticuloHome;

public class VentanaAdmin extends JFrame {

	private JPanel contentPane;
	public Connection con;
	public JFrame ventanaAnterior,ventanaActual;
	private JList<Articulo> listaArticulos;
	private DefaultListModel<Articulo> modeloListaArticulos;
	
	private JTable tablaArticulos;
	private DefaultTableModel modeloTablaArticulos= new DefaultTableModel();
		
	private TreeMap<Integer, Articulo> tmArticulosAdmin = new TreeMap<>();
	
	/**
	 * Create the frame.
	 * @throws DeustoException 
	 */
	public VentanaAdmin(JFrame va,Usuario u) throws DeustoException {
		con = BD.initBD("baseDeDatos.db");
		BD.cargarMapaUsuariosDeInfoBBDD(con);
		tmArticulosAdmin = BD.cargarMapaArticulosDeInfoBBDD(con);
		/*BD.insertarCamisetaBBDD(con, a1);
		BD.insertarPantalonBBDD(con, a2);
		BD.insertarSudaderaBBDD(con, a3);*/
		BD.closeBD(con);
		ventanaAnterior = va;
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 421);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelIZQayadirArticulo = new JPanel();
		panelCentro.add(panelIZQayadirArticulo);
		panelIZQayadirArticulo.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTituloIZQ = new JPanel();
		panelTituloIZQ.setBackground(new Color(255, 102, 0));
		panelIZQayadirArticulo.add(panelTituloIZQ, BorderLayout.NORTH);
		
		JLabel lblAnyadirArticulo = new JLabel("A\u00D1ADIR ARTICULO");
		lblAnyadirArticulo.setForeground(new Color(255, 255, 255));
		lblAnyadirArticulo.setFont(new Font("Lato", Font.BOLD, 20));
		panelTituloIZQ.add(lblAnyadirArticulo);
		
		JPanel panelIZQSur = new JPanel();
		panelIZQSur.setBackground(new Color(255, 102, 0));
		panelIZQayadirArticulo.add(panelIZQSur, BorderLayout.SOUTH);
		
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
		
		JPanel panelCNTeliminarArticulo = new JPanel();
		panelCentro.add(panelCNTeliminarArticulo);
		panelCNTeliminarArticulo.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTituloDCH = new JPanel();
		panelTituloDCH.setBackground(new Color(255, 102, 0));
		panelCNTeliminarArticulo.add(panelTituloDCH, BorderLayout.NORTH);
		
		JLabel lblEliminarArticulo = new JLabel("ELIMINAR ARTICULO");
		lblEliminarArticulo.setForeground(new Color(255, 255, 255));
		lblEliminarArticulo.setFont(new Font("Lato", Font.BOLD, 20));
		panelTituloDCH.add(lblEliminarArticulo);
		
		JPanel panelDCHsur = new JPanel();
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
		panelDerechaCentroLista.add(panelTituloListaArticulos, BorderLayout.NORTH);
		
		JLabel lblListaArticulos = new JLabel("Lista de articulos");
		lblListaArticulos.setFont(new Font("Lato", Font.BOLD, 23));
		panelTituloListaArticulos.add(lblListaArticulos);
		
		JPanel panelCentroDerechaLista = new JPanel();
		panelDerechaCentroLista.add(panelCentroDerechaLista, BorderLayout.CENTER);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(0, 153, 204));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnInicio = new JButton("Inicio");
		btnInicio.setForeground(new Color(255, 255, 255));
		btnInicio.setBackground(new Color(255, 153, 0));
		panelSur.add(btnInicio);
		
		JButton btnVentas = new JButton("Ver ventas totales");
		panelSur.add(btnVentas);
		
		/*modeloListaArticulos = new DefaultListModel<Articulo>();
		listaArticulos = new JList<Articulo>(modeloListaArticulos);
		panelCentroDerechaLista.add(listaArticulos);*/
		
		String [] header = {"TAG","TALLA", "PRECIO","COLOR", "SEXO"};
		modeloTablaArticulos.setColumnIdentifiers(header);
			for(Articulo a : tmArticulosAdmin.values()) {
				String dataRow[] = {a.getName(),a.getTalla(),String.valueOf(a.getPrecio()),a.getColor(),a.getSexo()};
				modeloTablaArticulos.addRow(dataRow);	
			}
		
		tablaArticulos = new JTable(modeloTablaArticulos);
		JScrollPane scroll =new JScrollPane(tablaArticulos);
		panelCentroDerechaLista.add(tablaArticulos);
		
		
		//anyadirArticulosALista();
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
		
		/**
		 * Boton que abre el panel para ver las ventas
		 */
		btnVentas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
