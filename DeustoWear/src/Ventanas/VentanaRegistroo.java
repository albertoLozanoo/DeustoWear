package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;

import Clases.BD;
import Clases.Usuario;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;

public class VentanaRegistroo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNick;
	private JTextField txtContraseya;
	public JFrame ventanaActual,ventanaAnterior;
	private JButton btnVolver;
	

	/**
	 * Create the frame.
	 */
	public VentanaRegistroo(JFrame va) {
		ventanaAnterior=va;
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 992, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(51, 153, 255));
		contentPane.setLayout(new BorderLayout(10, 10));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(102, 153, 204));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JPanel panelWest = new JPanel();
		panelWest.setBackground(new Color(51, 153, 255));
		contentPane.add(panelWest, BorderLayout.WEST);
		
		JPanel panelEste = new JPanel();
		panelEste.setBackground(new Color(51, 153, 255));
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(51, 153, 255));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.X_AXIS));
		panelCentro.setBackground(new Color(255, 153, 0));
		
		JPanel panelCentroIzquierda = new JPanel();
		panelCentro.add(panelCentroIzquierda);
		panelCentroIzquierda.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnLogo = new JButton(new ImageIcon("imagenes/logo.jpg"));
		btnLogo.setPreferredSize(new DimensionUIResource(400, 400));
		panelCentroIzquierda.add(btnLogo);
		
		JPanel panelCentroDerecha = new JPanel();
		panelCentroDerecha.setBackground(new Color(102, 153, 204));
		panelCentro.add(panelCentroDerecha);
		panelCentroDerecha.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panelCentroDerechalblRegistro = new JPanel();
		panelCentroDerechalblRegistro.setBackground(new Color(204, 102, 0));
		panelCentroDerecha.add(panelCentroDerechalblRegistro);
		panelCentroDerechalblRegistro.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblRegistro = new JLabel("REGISTRO");
		lblRegistro.setForeground(new Color(255, 255, 255));
		lblRegistro.setBackground(new Color(204, 102, 0));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setFont(new Font("Lato", Font.BOLD, 30));
		panelCentroDerechalblRegistro.add(lblRegistro);
		
		JPanel panelCentroDerechaInput = new JPanel();
		panelCentroDerechaInput.setBackground(new Color(255, 255, 255));
		panelCentroDerechaInput.setForeground(new Color(255, 102, 51));
		panelCentroDerecha.add(panelCentroDerechaInput);
		
		txtNick = new JTextField();
		txtNick.setForeground(new Color(255, 255, 255));
		txtNick.setBackground(new Color(204, 102, 51));
		txtNick.setColumns(10);
		txtNick.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblContraseya = new JLabel("Contraseya :");
		lblContraseya.setForeground(new Color(0, 0, 153));
		lblContraseya.setFont(new Font("Lato", Font.BOLD, 21));
		
		txtContraseya = new JTextField();
		txtContraseya.setForeground(new Color(255, 255, 255));
		txtContraseya.setBackground(new Color(204, 102, 51));
		txtContraseya.setColumns(10);
		txtContraseya.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentroDerechaInput.setLayout(new MigLayout("", "[212px]", "[26px][28px][26px][28px][23px][][][]"));
		
		JLabel lblNick = new JLabel("Nick :");
		lblNick.setForeground(new Color(0, 0, 153));
		lblNick.setBackground(new Color(0, 0, 0));
		lblNick.setHorizontalAlignment(SwingConstants.CENTER);
		lblNick.setFont(new Font("Lato", Font.BOLD, 21));
		panelCentroDerechaInput.add(lblNick, "cell 0 0,alignx center,aligny center");
		panelCentroDerechaInput.add(txtNick, "cell 0 1,growx,aligny center");
		panelCentroDerechaInput.add(lblContraseya, "cell 0 2,alignx center,aligny center");
		panelCentroDerechaInput.add(txtContraseya, "cell 0 3,growx,aligny center");
		
		
		panelCentroDerecha.setBorder(new MatteBorder(15, 35, 15, 15, (Color) new Color(255, 153, 0)));
		
		panelCentroDerechaInput.setBorder(BorderFactory.createMatteBorder(0, 15, 15, 15,  new Color(255, 255, 255)));
		
		JButton btnRegistrarse = new JButton("Registrarme");
		btnRegistrarse.setForeground(new Color(255, 255, 255));
		btnRegistrarse.setBackground(new Color(204, 102, 0));
		btnRegistrarse.setFont(new Font("Lato", Font.BOLD, 12));
		panelCentroDerechaInput.add(btnRegistrarse, "cell 0 5,alignx center,aligny center");
		
		panelCentroIzquierda.setBorder(BorderFactory.createLineBorder(new Color(0,10,250)));
		
		panelNorte.setPreferredSize(new Dimension(100,100));
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblRegistroGratis = new JLabel("Registrate aqui, solo te llevara un minuto...");
		lblRegistroGratis.setForeground(new Color(255, 255, 255));
		lblRegistroGratis.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroGratis.setFont(new Font("Lato", Font.BOLD, 31));
		panelNorte.add(lblRegistroGratis);
		panelSur.setPreferredSize(new Dimension(100,50));
		panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnVolver = new JButton("Volver");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setBackground(new Color(255, 51, 0));
		btnVolver.setFont(new Font("Lato", Font.BOLD, 19));
		panelSur.add(btnVolver);
		panelEste.setPreferredSize(new Dimension(100,100));
		panelWest.setPreferredSize(new Dimension(100,100));
		
		
		
		setVisible(true);
		
		/**EVENTOS**/
		
		
		/**
		 * Cuando el boton Registrar se active, se comparara los datos introducios con los existentes en la BBBDD y aceptara el registrao en caso exitoso
		 */
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ERnick = "[A-Za-z]{1,15}";
				String nick = txtNick.getText();
				String contraseya = txtContraseya.getText();
				boolean correctoNick = Pattern.matches(ERnick, nick);
				
				if((correctoNick) && (!nick.equals("admin")) && (!nick.equals("") && !contraseya.equals(""))) {
					Connection con = BD.initBD("baseDeDatos");
					int valor = BD.estaRegistrado(con, nick);
					if(valor == 0) {
						Usuario u = new Usuario(nick, contraseya);
						VentanaInicio.tmUsuarios.put(u.getNick(), u);
						JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "REGISTRO CORRECTO", JOptionPane.INFORMATION_MESSAGE);
						Connection con2 = BD.initBD("baseDeDatos");
						BD.intertarUsuarioBBDD(con2,u);
						BD.closeBD(con2);
						vaciarCampos();
					}else {
						JOptionPane.showMessageDialog(null, "Nick ya en uso, prueba con otro distinto", "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
						txtNick.setText("");
					}
				}else {
					JOptionPane.showMessageDialog(null, "El nombre no es correcto, recuerda que tu nick: \n\t 1. No puede contener numeros, solo letras \n\t 2. No puedes crear cuenta con nick 'admin' \n\t 3. El campo contraseña no puede estar vacio", "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
					txtNick.setText("");
				}
			}
		});	
		
		
		/**
		 * Boton que al activarse cierra la ventana actual y abre la ventanaInicio
		 */
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaActual.setVisible(false);
				ventanaAnterior.setVisible(true);
			}
		});
	}
	
	/**
	 * Metodo que limpia el contenido en los textFields
	 */
	public void vaciarCampos() {
		txtNick.setText("");
		txtContraseya.setText("");
	}
}
	
	
	



