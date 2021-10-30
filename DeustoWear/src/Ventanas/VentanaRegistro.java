package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.DimensionUIResource;

import Clases.Usuario;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame ventanaActual,ventanaAnterior;
	private JPanel contentPane;
	private JPanel panelCentro,panelNorte,panelSur;
	private JLabel lblNick,lblContraseya,lblRepetirContraseya;
	private JTextField txtNick,txtContraseya,txtRepetirContraseya;
	private JButton btnRegistrarse,btnLogo;


	public VentanaRegistro(JFrame va) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1118, 661);
		ventanaAnterior = va;
		ventanaActual = this;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelNorte = new JPanel();
		panelNorte.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelNorte.setBackground(new Color(51, 102, 255));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		btnLogo = new JButton(new ImageIcon("imagenes/logo.jpg"));
		btnLogo.setFont(new Font("Lato", Font.PLAIN, 11));
		btnLogo.setForeground(new Color(204, 0, 0));
		btnLogo.setBackground(new Color(51, 102, 255));
		btnLogo.setPreferredSize(new DimensionUIResource(200, 50));
		panelNorte.add(btnLogo);
		
		panelCentro = new JPanel();
		panelCentro.setSize(100,100);
		panelCentro.setBackground(new Color(255, 153, 102));
		contentPane.add(panelCentro, BorderLayout.CENTER);
		
		lblNick = new JLabel("Nick");
		lblNick.setHorizontalAlignment(SwingConstants.CENTER);
		lblNick.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 18));
		
		txtNick = new JTextField();
		txtNick.setFont(new Font("Lato", Font.PLAIN, 13));
		txtNick.setColumns(10);
		txtNick.setBackground(new Color(255, 153, 102));
		txtNick.setForeground(new Color(0, 0, 0));
		txtNick.setHorizontalAlignment(JTextField.CENTER);
		txtNick.setPreferredSize(new DimensionUIResource(20, 20));
		
		lblContraseya = new JLabel("Contraseya");
		lblContraseya.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 18));
		lblContraseya.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraseya.setBackground(new Color(255, 153, 102));
		
		txtContraseya = new JTextField();
		txtContraseya.setFont(new Font("Lato", Font.PLAIN, 13));
		txtContraseya.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseya.setBackground(new Color(255, 153, 102));
		txtContraseya.setColumns(10);
		
		lblRepetirContraseya = new JLabel("Repetir contraseya");
		lblRepetirContraseya.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepetirContraseya.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 18));
		
		txtRepetirContraseya = new JTextField();
		txtRepetirContraseya.setFont(new Font("Lato", Font.PLAIN, 13));
		txtRepetirContraseya.setBackground(new Color(255, 153, 102));
		txtRepetirContraseya.setHorizontalAlignment(SwingConstants.CENTER);
		txtRepetirContraseya.setColumns(10);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		panelCentro.add(lblNick);
		panelCentro.add(txtNick);
		panelCentro.add(lblContraseya);
		panelCentro.add(txtContraseya);
		panelCentro.add(lblRepetirContraseya);
		panelCentro.add(txtRepetirContraseya);
		
		panelSur = new JPanel();
		panelSur.setBackground(new Color(51, 102, 204));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		
		btnRegistrarse = new JButton("Registrarme");
		btnRegistrarse.setBackground(new Color(204, 102, 51));
		btnRegistrarse.setFont(new Font("Lato", Font.BOLD, 14));
		panelSur.add(btnRegistrarse);
		
		
		
		/**EVENTOS**/
		
		btnLogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaActual.setVisible(false);
				ventanaAnterior.setVisible(true);
			}
		});
		
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nick = txtNick.getText();
				String con = txtContraseya.getText();
				String con2 = txtRepetirContraseya.getText();
				
				if(con.equals(con2) && nombreRepetido(nick)==0) {
					Usuario u = new Usuario(nick,con);
					VentanaInicio.tmUsuarios.put(u.getNick(), u);
					JOptionPane.showMessageDialog(null, "Persona registrada correctamente", "REGISTRO CORRECTO", JOptionPane.INFORMATION_MESSAGE);
					vaciarCampos();
				
				}else if(!con.equals(con2) && nombreRepetido(nick)==0){
					JOptionPane.showMessageDialog(null, "Las contraseyas no coinciden", "REGISTRO CORRECTO", JOptionPane.ERROR_MESSAGE);
					txtRepetirContraseya.setText("");
						
				}else if(nombreRepetido(nick)==1) {
					JOptionPane.showMessageDialog(null, "El nombre ya esta existente", "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
					vaciarCampos();
				}
			}
		});
		
		setVisible(true);
		
		
	
	}
	public void vaciarCampos() {
		txtNick.setText("");
		txtContraseya.setText("");
		txtRepetirContraseya.setText("");
	}
	
	
	/**
	 * Metodo que comprueba si el nombre del usuario ya esta registrado
	 * @param nick, nombre del usuario que introducira a la hora de registrarse
	 * @return
	 * 		0: Si el nombre no esta repetido
	 * 		1: Si el nombre esta repetido
	 */
	public int nombreRepetido(String nick) {
		int solucion = 0;
		for(String clave: VentanaInicio.tmUsuarios.keySet()) {
			Usuario valor = VentanaInicio.tmUsuarios.get(clave);
			if(valor.getNick().equals(nick)) {
				solucion = 1;
			}else {
				solucion = 0;
			}
		}
		System.out.println(solucion);
		return solucion;
	}
}
