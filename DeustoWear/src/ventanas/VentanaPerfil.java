package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.html.ImageView;

import clases.BD;
import clases.Usuario;
import clases.Venta;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;

public class VentanaPerfil extends JFrame {

	private JPanel contentPane;
	public static Connection con;
	public static String nombreBD = "baseDeDatos.db";
	private JFrame ventanaActual, ventanaAnterior;
	private JLabel lblnumPedidos;
	private DefaultListModel<String> modeloListVentasUsuario;
	private JList<String> listaVentasUsuario;
	private JScrollPane scrollListaVentas;
	
	
	public Usuario u;

	/**
	 * Create the frame.
	 * @param ventanaActual2 
	 */
	
	public VentanaPerfil(JFrame va, Usuario u) {
		//cargarTMventasUsuarioAJlist();
		System.out.println("Este es el logo " + u.getLogoAvatar());
		System.out.println(u.getContraseya());
		ventanaAnterior = va;
		ventanaActual = this;
		setVisible(true);
		ventanaActual = this;
		setTitle("Su perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1192, 781);
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
		
		JButton btnEditar = new JButton("CAMBIAR CONTRASE\u00D1A");
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Lato", Font.PLAIN, 19));
		btnEditar.setBackground(new Color(204, 102, 51));
		panelNorte.add(btnEditar);
		
		
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setBackground(new Color(255, 153, 51));
		panelCentral.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panelCentroIzquierda = new JPanel();
		panelCentroIzquierda.setForeground(new Color(255, 255, 255));
		panelCentroIzquierda.setBackground(new Color(255, 153, 0));
		panelCentral.add(panelCentroIzquierda);
		panelCentroIzquierda.setLayout(new MigLayout("", "[378.00px,grow][1px]", "[93.00px][72.00][grow]"));
		
		JLabel lblMyProfile = new JLabel("MY PROFILE");
		lblMyProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyProfile.setForeground(new Color(255, 255, 255));
		lblMyProfile.setFont(new Font("Lato", Font.BOLD, 42));
		lblMyProfile.setBackground(Color.WHITE);
		panelCentroIzquierda.add(lblMyProfile, "cell 0 0,alignx center,aligny bottom");
		
		JLabel lblNombreUsuario = new JLabel(u.getNick());
		lblNombreUsuario.setForeground(new Color(255, 255, 255));
		lblNombreUsuario.setFont(new Font("Lato", Font.BOLD, 28));
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentroIzquierda.add(lblNombreUsuario, "cell 0 1,alignx center,aligny bottom");
		
		JLabel lblAvatar = new JLabel();
		panelCentroIzquierda.add(lblAvatar, "cell 0 2,alignx center,aligny center");
		
		con = BD.initBD(nombreBD);
		String avatar = BD.conseguirAvatar(con, u.getNick());
		BD.closeBD(con);
		
		ImageIcon im = new ImageIcon(avatar);

		

		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(300,300,ImageView.CENTER));
		lblAvatar.setIcon(imagenConDimensiones);
		lblAvatar.setPreferredSize(new DimensionUIResource(100, 100));
		lblAvatar.setIcon(imagenConDimensiones);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 0));
		panelCentral.add(panel);
		panel.setLayout(new MigLayout("", "[152px,grow]", "[49.00][55.00px][grow]"));
		
		JLabel lblEnunciado = new JLabel("\tPedidos realizados:");
		lblEnunciado.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnunciado.setForeground(new Color(255, 255, 255));
		lblEnunciado.setFont(new Font("Lato", Font.BOLD, 24));
		panel.add(lblEnunciado, "cell 0 0,alignx center,aligny bottom");
		
		lblnumPedidos = new JLabel("Num. de Pedidos : ");
		lblnumPedidos.setForeground(Color.WHITE);
		lblnumPedidos.setFont(new Font("Lato", Font.PLAIN, 18));
		lblnumPedidos.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblnumPedidos, "cell 0 1,alignx center,aligny center");
		
		
		JList list = new JList();
		panel.add(list, "cell 0 2,grow");
		
		
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
		btnCesta.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaCesta(ventanaActual,u);
				//new VentanaPerfil(ventanaActual);
			}
		});
		
		
	}
	
	private void cargarTMventasUsuarioAJlist(){
		for(String clave : u.tmVentasUsuario.keySet() ) {
			String venta = u.tmVentasUsuario.get(clave).toString();
			modeloListVentasUsuario.addElement(venta);
		}
	}
}


