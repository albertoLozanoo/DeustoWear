package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Clases.BD;
import Clases.Usuario;

public class VentanaPerfil extends JFrame {

	private JPanel contentPane;
	public static Connection con;
	public static String nombreBD = "baseDeDatos.db";
	private JFrame ventanaActual, ventanaAnterior;
	

	/**
	 * Create the frame.
	 * @param ventanaActual2 
	 */
	
	public VentanaPerfil(JFrame va, Usuario u) {
		ventanaAnterior = va;
		ventanaActual = this;
		setVisible(true);
		ventanaActual = this;
		setTitle("Su perfil");
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
		btnFavoritos.setBackground(new Color(204, 102, 51));
		panelSur.add(btnFavoritos);
		
		JButton btnCesta = new JButton("Cesta");
		btnCesta.setFont(new Font("Lato", Font.PLAIN, 19));
		btnCesta.setBackground(new Color(204, 102, 51));
		panelSur.add(btnCesta);
		
		JButton btnPerfil = new JButton("Profile");
		btnPerfil.setFont(new Font("Lato", Font.PLAIN, 19));
		btnPerfil.setForeground(new Color(255, 255, 255));
		btnPerfil.setEnabled(false);
		btnPerfil.setBackground(new Color(204, 102, 51));
		panelSur.add(btnPerfil);
		
		
		
		
		JPanel panelNorte = new JPanel();
		getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setBackground(new Color(255, 102, 51));
		panelNorte.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Lato", Font.PLAIN, 19));
		btnEditar.setBackground(new Color(204, 102, 51));
		panelNorte.add(btnEditar);
		
		
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setBackground(new Color(255, 153, 51));
		panelCentral.setLayout(new GridLayout(4, 4, 0, 0));
		
		JLabel lblCabecera = new JLabel("Mi perfil");
		lblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCabecera.setBackground(new Color(255, 255, 255));
		lblCabecera.setFont(new Font("Lato", Font.BOLD, 28));
		lblCabecera.setForeground(new Color(255, 255, 255));
		panelCentral.add(lblCabecera, "cell 1 0,alignx center,aligny center");
		panelCentral.add(lblCabecera);
		
		
		JLabel lblNombreUsuario = new JLabel(Usuario.getNick());
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblNombreUsuario);
		
		
		
		
		
		JLabel lblEnunciado = new JLabel("	Pedidos realizados:");
		lblEnunciado.setForeground(Color.BLACK);
		lblEnunciado.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnunciado.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		panelCentral.add(lblEnunciado);
		
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contra = JOptionPane.showInputDialog("Introduzca la nueva contraseña:");
				String ERcontraseya = "[0-9]{1,15}";
				boolean correctoContra = Pattern.matches(ERcontraseya, contra);
				if(correctoContra && !contra.equals(u.getContraseya())) {
					con = BD.initBD(nombreBD);
					BD.cambiarContrasenya(con, u.getNick(), contra);
					BD.closeBD(con);
					JOptionPane.showMessageDialog(null, "Contraseña cambiada correctamente", "CAMBIO REALIZADO", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Loading...Error", "!!ERROR!!", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		
		btnWearHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaHome(ventanaActual,u);
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
		
		
	}
}


