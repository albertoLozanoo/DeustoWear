package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Articulo;
import Clases.BD;
import Clases.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JTextPane;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VentanaInicio extends JFrame {

	private JPanel contentPane,panelCentro,panelNorte,panelSur;
	private JTextPane txtpnRegistrado;
	private JButton btnIniciarSesion,btnRegistrarse;
	private JFrame ventanaActual;
	public static Connection con;
	
	public static TreeMap<String, Usuario> tmUsuarios;
	public static TreeMap<Integer,Articulo> tmArticulos;
	private JPanel panelCentroIzquierda;
	private JPanel panelCentroDerecha;
	private JPanel panelCentroDerechaArriba;
	private JPanel panelCentroDerechaAbajo;
	private JLabel lblRegistrarEsGratis;
	private JPanel panel_2;
	private JLabel lblIzquierdaFrase1;
	private JLabel lblIzquierdaFrase2;
	private JLabel lblIzquierdaFrase3;
	private JLabel lblDerechos;
	private JPanel panelCentroInside;
	private JPanel panelWestInside;
	private JPanel panelNorteInside;
	private JPanel panelEsteInside;
	private JLabel lblIniciarSesionNorte;
	private JLabel lblNick;
	private JTextField txtNick;
	private JLabel lblContraseya;
	private JTextField txtContraseya;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaInicio() {
		con = BD.initBD("baseDeDatos.db");
		BD.crearTablas(con);
		BD.closeBD(con);
		ventanaActual = this;
		tmUsuarios = new TreeMap<>();
		tmArticulos = new TreeMap<>();
		setTitle("Bienvenido a DeustoWear");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1239, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(1, 0, 0, 0));
		
		panelCentroIzquierda = new JPanel();
		panelCentro.add(panelCentroIzquierda);
		panelCentroIzquierda.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panelCentroIzquierda.add(panel_2, BorderLayout.NORTH);
		
		lblIzquierdaFrase1 = new JLabel("Confianza");
		panel_2.add(lblIzquierdaFrase1);
		
		lblIzquierdaFrase2 = new JLabel("Perseverancia");
		panel_2.add(lblIzquierdaFrase2);
		
		lblIzquierdaFrase3 = new JLabel("Constancia");
		panel_2.add(lblIzquierdaFrase3);
		
		panelCentroDerecha = new JPanel();
		panelCentro.add(panelCentroDerecha);
		panelCentroDerecha.setLayout(new BoxLayout(panelCentroDerecha, BoxLayout.Y_AXIS));
		
		panelCentroDerechaArriba = new JPanel();
		panelCentroDerecha.add(panelCentroDerechaArriba);
		panelCentroDerechaArriba.setLayout(new BorderLayout(20, 20));
		
		panelCentroInside = new JPanel();
		panelCentroDerechaArriba.add(panelCentroInside, BorderLayout.CENTER);
		panelCentroInside.setLayout(new MigLayout("", "[160.00,grow]", "[][][][][][][][][][][]"));
		
		lblNick = new JLabel("Nick :");
		lblNick.setFont(new Font("Lato", Font.BOLD, 31));
		panelCentroInside.add(lblNick, "cell 0 2,alignx center,aligny center");
		
		txtNick = new JTextField();
		txtNick.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentroInside.add(txtNick, "cell 0 4,alignx center,aligny center");
		txtNick.setColumns(25);
		
		lblContraseya = new JLabel("Contrase\u00F1a :");
		lblContraseya.setFont(new Font("Lato", Font.BOLD, 31));
		panelCentroInside.add(lblContraseya, "cell 0 7,alignx center,aligny center");
		
		txtContraseya = new JTextField();
		txtContraseya.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentroInside.add(txtContraseya, "cell 0 9,alignx center,aligny center");
		txtContraseya.setColumns(25);
		
		panelWestInside = new JPanel();
		panelCentroDerechaArriba.add(panelWestInside, BorderLayout.WEST);
		
		panelNorteInside = new JPanel();
		panelCentroDerechaArriba.add(panelNorteInside, BorderLayout.NORTH);
		
		lblIniciarSesionNorte = new JLabel("Inicia sesion aqui");
		lblIniciarSesionNorte.setFont(new Font("Lato", Font.BOLD, 42));
		panelNorteInside.add(lblIniciarSesionNorte);
		
		panelEsteInside = new JPanel();
		panelCentroDerechaArriba.add(panelEsteInside, BorderLayout.EAST);
		
		panelCentroDerechaAbajo = new JPanel();
		panelCentroDerecha.add(panelCentroDerechaAbajo);
		panelCentroDerechaAbajo.setLayout(new MigLayout("", "[318px,grow,right]", "[14px]"));
		
		lblDerechos = new JLabel("Autenticacion de Privacidad y Derechos de Uso @CompanyVersion");
		lblDerechos.setFont(new Font("Lato", Font.ITALIC, 9));
		lblDerechos.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDerechos.setBackground(new Color(255, 255, 255));
		panelCentroDerechaAbajo.add(lblDerechos, "cell 0 0,alignx right,aligny bottom");
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelNorte.setLayout(new MigLayout("", "[936.00px,grow,center]", "[30px][][30px]"));
		
		
		txtpnRegistrado = new JTextPane();
		txtpnRegistrado.setBackground(new Color(255, 153, 102));
		txtpnRegistrado.setForeground(Color.WHITE);
		panelNorte.add(txtpnRegistrado, "cell 0 0,alignx center");
		txtpnRegistrado.setFont(new Font("Verdana", Font.BOLD, 19));
		txtpnRegistrado.setText("¿Todavia no te has registrado?");
		

		btnIniciarSesion = new JButton("Iniciar Sesion");
		panelSur.add(btnIniciarSesion);
		
		btnRegistrarse = new JButton("Haz click aqui para registrarte");
		btnRegistrarse.setForeground(new Color(255, 255, 255));
		btnRegistrarse.setFont(new Font("Verdana", Font.PLAIN, 14));
		panelNorte.add(btnRegistrarse, "cell 0 1,alignx center,aligny center");
		btnRegistrarse.setBackground(new Color(255, 153, 102));
		
		lblRegistrarEsGratis = new JLabel("Registrarse gratis");
		lblRegistrarEsGratis.setForeground(Color.BLUE);
		lblRegistrarEsGratis.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 15));
		panelNorte.add(lblRegistrarEsGratis, "cell 0 2,aligny top");
		
		/*EVENTOS*/
		
		/**
		 * Boton que al activarse cierra la ventana actual y abre la ventanaRegistro
		 */
		btnRegistrarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaRegistroo(ventanaActual);
				//new VentantaRegistro(ventanaActual);
			}
		});
		
		/**
		 * Boton que al activarse verifica si el usuario esta registrado ya en la BBDD
		 */
		btnIniciarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nick = txtNick.getText();
				String c = txtContraseya.getText();
				if(!nick.equals("") && !c.equals("")) {
					Connection con = BD.initBD("newton.db");
					int resul = BD.obtenerUsuario(con, nick, c);
					if(resul == 0) {
						JOptionPane.showMessageDialog(null, "Todavia no te has registrado");
					}else if(resul==1) {
						JOptionPane.showMessageDialog(null, "La contraseña no es correcta");
					}else {
						JOptionPane.showMessageDialog(null, "Cargando WearHome, bienvenido "+ nick);
					}
				}
				txtNick.setText("");
				txtContraseya.setText("");
			}
		});
	}
}
		
		
	
