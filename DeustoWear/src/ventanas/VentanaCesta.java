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
import clases.Usuario;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import java.awt.FlowLayout;

public class VentanaCesta extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboTalla, comboPrenda;
	private JFrame ventanaActual,ventanaAnterior;
	private JButton btnLogo;
	
	
	
	/**
	 * Create the frame.
	 */
	
	public VentanaCesta(JFrame va,Usuario u) {
		ventanaAnterior = va;
		ventanaActual = this;
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1046, 593);
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
		
		JLabel lblTitulo = new JLabel("Mi cesta ");
		lblTitulo.setBackground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Lato", Font.BOLD, 28));
		lblTitulo.setForeground(new Color(255, 255, 255));
		panelNorte.add(lblTitulo, "cell 1 0,alignx center,aligny center");
		
		
		JLabel lblBuscarAqui = new JLabel("Buscar aqui...");
		lblBuscarAqui.setForeground(new Color(255, 255, 255));
		lblBuscarAqui.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarAqui.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 19));
		
		
		JLabel lblComboPrenda = new JLabel("Prenda");
		lblComboPrenda.setForeground(new Color(255, 255, 255));
		lblComboPrenda.setFont(new Font("Lato", Font.PLAIN, 18));
		
		comboPrenda = new JComboBox<String>();
		comboPrenda.setToolTipText("");
		
		
		JLabel lblComboTalla = new JLabel("Talla");
		lblComboTalla.setForeground(new Color(255, 255, 255));
		lblComboTalla.setFont(new Font("Lato", Font.PLAIN, 18));
		lblComboTalla.setHorizontalAlignment(SwingConstants.CENTER);
		lblComboTalla.setBackground(new Color(240, 240, 240));
		
		
		comboTalla = new JComboBox<String>();
		
		
		JRadioButton rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setForeground(new Color(255, 255, 255));
		rdbtnHombre.setFont(new Font("Lato", Font.PLAIN, 18));
		rdbtnHombre.setBackground(new Color(255, 153, 0));
		
		
		JRadioButton rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setForeground(new Color(255, 255, 255));
		rdbtnMujer.setFont(new Font("Lato", Font.PLAIN, 18));
		rdbtnMujer.setBackground(new Color(255, 153, 0));
		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(255, 102, 0));
		btnBuscar.setFont(new Font("Lato", Font.PLAIN, 15));
		
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(255, 102, 51));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout(0, 4, 0, 0));

	
		
		JButton btnWearHome = new JButton("Home");
		btnWearHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnWearHome.setFont(new Font("Lato", Font.PLAIN, 19));
		btnWearHome.setForeground(Color.BLACK);
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
		
		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(new Color(255, 153, 51));
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JList listCesta = new JList();
		panelCentro.add(listCesta, BorderLayout.CENTER);
		
		JPanel panelCentroSur = new JPanel();
		panelCentro.add(panelCentroSur, BorderLayout.SOUTH);
		panelCentroSur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSeguirComprando = new JButton("Seguir comprando\r\n");
		btnSeguirComprando.setBackground(new Color(255, 140, 0));
		btnSeguirComprando.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnSeguirComprando.setForeground(Color.BLACK);
		panelCentroSur.add(btnSeguirComprando);
		
		JButton btnPagar = new JButton("Pagar\r\n");
		btnPagar.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnPagar.setBackground(Color.GREEN);
		btnCesta.setEnabled(false);
		panelCentroSur.add(btnPagar);
		
		
		/*EVENTOS*/
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		btnLogo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaInicio();
			}
		});
		
		
		btnPerfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaPerfil(ventanaActual,u);
				
			}
		});
		
		btnWearHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaHome(ventanaActual,u);
				
			}
		});
		btnFavoritos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaFavoritos(ventanaActual,u);
				
			}
		});
		
		btnSeguirComprando.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaHome(ventanaActual,u);
				
			}
		});
	}
	
	

}
