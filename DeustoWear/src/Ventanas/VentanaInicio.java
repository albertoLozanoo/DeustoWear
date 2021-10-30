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

public class VentanaInicio extends JFrame {

	private JPanel contentPane,panelCentro,panelNorte,panelSur;
	private JTextField txtNombre;
	private JTextField txtContraseya;
	private JTextPane txtpnRegistrado;
	private JLabel lblNombre,lblContraseya;
	private JButton btnIniciarSesion,btnRegistrarse;
	private JFrame ventanaActual;
	public static Connection con;
	
	public static TreeMap<String, Usuario> tmUsuarios;
	public static TreeMap<Integer,Articulo> tmArticulos;

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
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		
		lblNombre = new JLabel("Nombre:");
		panelCentro.add(lblNombre);
		
		
		txtNombre = new JTextField();
		panelCentro.add(txtNombre);
		txtNombre.setColumns(10);
		lblContraseya = new JLabel("Contrasenya:");
		panelCentro.add(lblContraseya);
		txtContraseya = new JTextField();
		panelCentro.add(txtContraseya);
		txtContraseya.setColumns(10);
		
		
		txtpnRegistrado = new JTextPane();
		panelNorte.add(txtpnRegistrado);
		txtpnRegistrado.setFont(new Font("Verdana", Font.BOLD, 19));
		txtpnRegistrado.setText("¿Todavia no te has registrado?");
		

		btnIniciarSesion = new JButton("Iniciar Sesion");
		panelSur.add(btnIniciarSesion);
		
		btnRegistrarse = new JButton("Haz click aqui para registrarte");
		btnRegistrarse.setFont(new Font("Verdana", Font.PLAIN, 14));
		panelNorte.add(btnRegistrarse);
		
		/*EVENTOS*/
		
		btnRegistrarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaRegistro(ventanaActual);
				//new VentantaRegistro(ventanaActual);
			}
		});
		
		btnIniciarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
		
		
	
