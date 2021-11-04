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

public class VentanaRegistroo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNick;
	private JTextField txtContraseya;
	public JFrame ventanaActual,ventanaAnterior;

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
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setLayout(new BorderLayout(10, 10));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(255, 153, 0));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JPanel panelWest = new JPanel();
		panelWest.setBackground(new Color(255, 153, 0));
		contentPane.add(panelWest, BorderLayout.WEST);
		
		JPanel panelEste = new JPanel();
		panelEste.setBackground(new Color(255, 153, 0));
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(255, 153, 0));
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
		panelCentro.add(panelCentroDerecha);
		panelCentroDerecha.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panelCentroDerechalblRegistro = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelCentroDerechalblRegistro.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelCentroDerecha.add(panelCentroDerechalblRegistro);
		
		JLabel lblRegistro = new JLabel("REGISTRO");
		panelCentroDerechalblRegistro.add(lblRegistro);
		
		JPanel panelCentroDerechaInput = new JPanel();
		panelCentroDerecha.add(panelCentroDerechaInput);
		
		txtNick = new JTextField();
		txtNick.setColumns(10);
		
		JLabel lblContraseya = new JLabel("Contraseya :");
		
		txtContraseya = new JTextField();
		txtContraseya.setColumns(10);
		panelCentroDerechaInput.setLayout(new BoxLayout(panelCentroDerechaInput, BoxLayout.Y_AXIS));
		
		JLabel lblNick = new JLabel("Nick :");
		panelCentroDerechaInput.add(lblNick);
		panelCentroDerechaInput.add(txtNick);
		panelCentroDerechaInput.add(lblContraseya);
		panelCentroDerechaInput.add(txtContraseya);
		
		JButton btnRegistrarse = new JButton("Registrarme");
		panelCentroDerechaInput.add(btnRegistrarse);
		
		
		panelCentroDerecha.setBorder(BorderFactory.createMatteBorder(15, 35, 15, 15, new Color(255, 153, 0)));
		
		panelCentroDerechaInput.setBorder(BorderFactory.createMatteBorder(0, 15, 15, 15,  new Color(255, 255, 255)));
		panelCentroIzquierda.setBorder(BorderFactory.createLineBorder(new Color(0,10,250)));
		
		panelNorte.setPreferredSize(new Dimension(100,100));
		panelSur.setPreferredSize(new Dimension(100,50));
		panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelEste.setPreferredSize(new Dimension(100,100));
		panelWest.setPreferredSize(new Dimension(100,100));
		
		
		
		setVisible(true);
		
		/**EVENTOS**/
		
		
		/**
		 * Boton que al activarse verifica los campos de nick y contraseña para ver si es correcto e introducir la informacion en la BBDD
		 */
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ERnick = "[A-Za-z]{1,15}";
				String nick = txtNick.getText();
				boolean correctoNick = Pattern.matches(ERnick, nick);
				if(correctoNick) {
					String contraseya = txtContraseya.getText();
					Usuario u = new Usuario(nick, contraseya);
					VentanaInicio.tmUsuarios.put(u.getNick(), u);
					JOptionPane.showMessageDialog(null, "Usuario registrada correctamente", "REGISTRO CORRECTO", JOptionPane.INFORMATION_MESSAGE);
					vaciarCampos();
				}else {
					JOptionPane.showMessageDialog(null, "La contraseya no es correcta, intentelo de nuevo", "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		/**
		 * Boton que al activarse cierra la ventana actual y abre la ventanaInicio
		 */
		btnLogo.addActionListener(new ActionListener() {
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
	
	
	



