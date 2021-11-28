package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.html.ImageView;

import clases.Articulo;
import clases.BD;
import clases.DeustoException;
import clases.Usuario;
import net.miginfocom.swing.MigLayout;
import panel.panelArticuloHome;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;



public class VentanaHome extends JFrame {

	private JPanel contentPane;
	private JPanel panelCentro;
	private JComboBox<String> comboTalla, comboPrenda;
	private JFrame ventanaActual,ventanaAnterior;
	private JButton btnLogo;
	private ButtonGroup bg;
	
	/**
	 * Create the frame.
	 * @throws DeustoException 
	 */
	public VentanaHome(JFrame va,Usuario u) throws DeustoException {
		
		ventanaAnterior = va;
		ventanaActual = this;
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1446, 793);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));
		contentPane.setBackground(new Color(0, 153, 255));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(255, 153, 0));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new MigLayout("", "[59.00][372.00,grow,center]", "[]"));
		
		ImageIcon im = new ImageIcon("imagenes/logo.jpg");
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(70,70,ImageView.CENTER));
		btnLogo = new JButton();
		btnLogo.setBackground(new Color(255, 153, 0));
		btnLogo.setPreferredSize(new DimensionUIResource(70, 70));
		btnLogo.setIcon(imagenConDimensiones);
		
		panelNorte.add(btnLogo, "cell 0 0");
		
		JLabel lblTitulo = new JLabel("DeustoWear Shop " + u.getNick() );
		lblTitulo.setBackground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Lato", Font.BOLD, 28));
		lblTitulo.setForeground(new Color(255, 255, 255));
		panelNorte.add(lblTitulo, "cell 1 0,alignx center,aligny center");
		
		JPanel panelWest = new JPanel();
		panelWest.setBackground(new Color(255, 153, 0));
		contentPane.add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(new MigLayout("", "[106.00][109.00,grow]", "[][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblBuscarAqui = new JLabel("Buscar aqui...");
		lblBuscarAqui.setForeground(new Color(255, 255, 255));
		lblBuscarAqui.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarAqui.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 19));
		panelWest.add(lblBuscarAqui, "cell 0 1,alignx center,aligny center");
		
		JLabel lblComboPrenda = new JLabel("Prenda");
		lblComboPrenda.setForeground(new Color(255, 255, 255));
		lblComboPrenda.setFont(new Font("Lato", Font.PLAIN, 18));
		panelWest.add(lblComboPrenda, "cell 0 5,alignx center,aligny center");
		
		
		/*ComboBox Articulos*/
		comboPrenda = new JComboBox<String>();
		comboPrenda.setToolTipText("");
		panelWest.add(comboPrenda, "cell 1 5,growx,aligny center");
		
		ArrayList<String> tiposArticulos = new ArrayList<>();
		tiposArticulos.add("Articulos");
		tiposArticulos.add("Camiseta");
		tiposArticulos.add("Pantalon");
		tiposArticulos.add("Sudadera");
		
		for(String articulo : tiposArticulos) {
			comboPrenda.addItem(articulo);
		}
	
	
		
		
		
		JRadioButton rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setForeground(new Color(255, 255, 255));
		rdbtnHombre.setFont(new Font("Lato", Font.PLAIN, 18));
		rdbtnHombre.setBackground(new Color(255, 153, 0));
		panelWest.add(rdbtnHombre, "cell 0 11,alignx left,aligny center");
		
		JRadioButton rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setForeground(new Color(255, 255, 255));
		rdbtnMujer.setFont(new Font("Lato", Font.PLAIN, 18));
		rdbtnMujer.setBackground(new Color(255, 153, 0));
		panelWest.add(rdbtnMujer, "cell 0 13,alignx left,aligny center");
		
		bg = new ButtonGroup();
		bg.add(rdbtnHombre);
		bg.add(rdbtnMujer);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(255, 102, 0));
		btnBuscar.setFont(new Font("Lato", Font.PLAIN, 15));
		panelWest.add(btnBuscar, "cell 0 18 2 1,alignx center,aligny center");
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(255, 102, 51));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout(0, 4, 0, 0));

	
		
		JButton btnWearHome = new JButton("Home");
		btnWearHome.setFont(new Font("Lato", Font.PLAIN, 19));
		btnWearHome.setForeground(new Color(255, 255, 255));
		btnWearHome.setEnabled(false);
		btnWearHome.setBackground(new Color(204, 102, 51));
		panelSur.add(btnWearHome);
		
		JButton btnFavoritos = new JButton("Favorites");
		btnFavoritos.setFont(new Font("Lato", Font.PLAIN, 19));
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
		
		panelCentro = new JPanel();
		panelCentro.setBackground(new Color(255, 153, 51));
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 3, 0, 0));
	
		this.cargarPaneles();
		
		
		
		btnPerfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				//new VentanaPerfil(ventanaActual);
			}
		});
		
		btnLogo.addActionListener(new ActionListener() {
			
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
		
		btnFavoritos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaFavoritos(ventanaActual,u);
				//new VentanaPerfil(ventanaActual);
			}
		});
		
		btnCesta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaCesta(ventanaActual,u);
				//new VentanaPerfil(ventanaActual);
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboPrenda.getSelectedIndex() ==0){
					panelCentro.removeAll();
					try {
						cargarPaneles();
					} catch (DeustoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if(comboPrenda.getSelectedIndex()==1) {
					panelCentro.removeAll();
					try {
						cargarPanelesConCamisetas();
					} catch (DeustoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if(comboPrenda.getSelectedIndex()==2){
					panelCentro.removeAll();
					try {
						cargarPanelesConPantalones();
					} catch (DeustoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}		
				}else if(comboPrenda.getSelectedIndex()==3) {
					panelCentro.removeAll();
					try {
						cargarPanelesConSudaderas();
					} catch (DeustoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
	}
	
	
	public void cargarPaneles() throws DeustoException {
		Connection con = BD.initBD("baseDeDatos.db");
		TreeMap<Integer , Articulo> tm = BD.cargarMapaArticulosDeInfoBBDD(con);
		for(Articulo a: tm.values()) {
			panelArticuloHome pi = new panelArticuloHome(a);
			panelCentro.add(pi);
			panelCentro.updateUI();
		}
		BD.closeBD(con);
	}
	
	/*
	 * Metodos para el sistema de busqueda de articulos
	 */
	public void cargarPanelesConCamisetas() throws DeustoException {
		Connection con = BD.initBD("baseDeDatos.db");
		TreeMap<Integer , Articulo> tm = BD.cargarCamisetasDeInfoDeBBDD(con);
		for(Articulo a: tm.values()) {
			panelArticuloHome pi = new panelArticuloHome(a);
			panelCentro.add(pi);
			panelCentro.updateUI();
		}
		BD.closeBD(con);
	}
	
	public void cargarPanelesConPantalones() throws DeustoException {
		Connection con = BD.initBD("baseDeDatos.db");
		TreeMap<Integer , Articulo> tm = BD.cargarPantalonesDeInfoDeBBDD(con);
		for(Articulo a: tm.values()) {
			panelArticuloHome pi = new panelArticuloHome(a);
			panelCentro.add(pi);
			panelCentro.updateUI();
		}
		BD.closeBD(con);
	}
	
	public void cargarPanelesConSudaderas() throws DeustoException {
		Connection con = BD.initBD("baseDeDatos.db");
		TreeMap<Integer , Articulo> tm = BD.cargarSudaderasDeInfoDeBBDD(con);
		for(Articulo a: tm.values()) {
			panelArticuloHome pi = new panelArticuloHome(a);
			panelCentro.add(pi);
			panelCentro.updateUI();
		}
		BD.closeBD(con);
	}
	
	
	
	
	

}
