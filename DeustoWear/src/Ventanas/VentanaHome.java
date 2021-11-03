package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import Clases.Articulo;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class VentanaHome extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboTalla, comboPrenda; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaHome frame = new VentanaHome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the frame.
	 */
	public VentanaHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1176, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));
		contentPane.setBackground(new Color(0, 153, 255));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(0, 153, 255));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new MigLayout("", "[59.00][372.00,grow,center]", "[]"));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblLogo, "cell 0 0,alignx center,aligny center");
		
		JLabel lblTitulo = new JLabel("DeustoWear Shop");
		lblTitulo.setFont(new Font("Lato", Font.BOLD, 28));
		lblTitulo.setForeground(new Color(0, 0, 0));
		panelNorte.add(lblTitulo, "cell 1 0,alignx center,aligny center");
		
		JPanel panelWest = new JPanel();
		panelWest.setBackground(new Color(0, 153, 255));
		contentPane.add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(new MigLayout("", "[106.00][109.00,grow]", "[][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblBuscarAqui = new JLabel("Buscar aqui...");
		lblBuscarAqui.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarAqui.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 19));
		panelWest.add(lblBuscarAqui, "cell 0 1,alignx center,aligny center");
		
		JLabel lblComboPrenda = new JLabel("Prenda");
		lblComboPrenda.setFont(new Font("Lato", Font.PLAIN, 18));
		panelWest.add(lblComboPrenda, "cell 0 5,alignx center,aligny center");
		
		comboPrenda = new JComboBox<String>();
		comboPrenda.setToolTipText("");
		panelWest.add(comboPrenda, "cell 1 5,growx,aligny center");
		
		JLabel lblComboTalla = new JLabel("Talla");
		lblComboTalla.setFont(new Font("Lato", Font.PLAIN, 18));
		lblComboTalla.setHorizontalAlignment(SwingConstants.CENTER);
		lblComboTalla.setBackground(new Color(240, 240, 240));
		panelWest.add(lblComboTalla, "cell 0 7,alignx center,aligny center");
		
		comboTalla = new JComboBox<String>();
		panelWest.add(comboTalla, "cell 1 7,growx");
		
		JRadioButton rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setFont(new Font("Lato", Font.PLAIN, 18));
		rdbtnHombre.setBackground(new Color(0, 153, 255));
		panelWest.add(rdbtnHombre, "cell 0 11,alignx left,aligny center");
		
		JRadioButton rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setFont(new Font("Lato", Font.PLAIN, 18));
		rdbtnMujer.setBackground(new Color(0, 153, 255));
		panelWest.add(rdbtnMujer, "cell 0 13,alignx left,aligny center");
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Lato", Font.PLAIN, 15));
		panelWest.add(btnBuscar, "cell 0 18 2 1,alignx center,aligny center");
		
		
		JPanel panelEste = new JPanel();
		panelEste.setBackground(new Color(0, 153, 255));
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(255, 102, 51));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout(0, 4, 0, 0));

	
		
		JButton btnWearHome = new JButton("Home");
		btnWearHome.setFont(new Font("Lato", Font.PLAIN, 19));
		btnWearHome.setForeground(new Color(0, 102, 204));
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
		
		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(new Color(255, 153, 102));
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 3, 0, 0));
	}
	
	

}
