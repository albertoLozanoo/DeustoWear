package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.html.ImageView;

import clases.BD;
import clases.DeustoException;
import clases.Usuario;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.BevelBorder;

public class VentanaRegistroo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField txtNick;
	private static JPasswordField txtContraseya;
	public JFrame ventanaActual,ventanaAnterior;
	private JButton btnVolver,btnAvatar,btnComp;
	private JFileChooser fc;
	private File ficheroSeleccionado;
	private static JLabel lblAvatarSeleccionado;
	

	private static Logger log = Logger.getLogger("LogUserRegistered"); 
	

	/**
	 * Create the frame.
	 */
	public VentanaRegistroo(JFrame va) {
		ventanaAnterior=va;
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1176, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(255, 102, 0));
		contentPane.setLayout(new BorderLayout(10, 10));
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelNorte.setForeground(new Color(0, 0, 0));
		panelNorte.setBackground(new Color(255, 153, 0));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JPanel panelWest = new JPanel();
		panelWest.setBackground(new Color(255, 102, 0));
		contentPane.add(panelWest, BorderLayout.WEST);
		
		JPanel panelEste = new JPanel();
		panelEste.setBackground(new Color(255, 102, 0));
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(255, 102, 0));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.X_AXIS));
		panelCentro.setBackground(new Color(255, 153, 0));
		
		JPanel panelCentroIzquierda = new JPanel();
		panelCentro.add(panelCentroIzquierda);
		panelCentroIzquierda.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnLogo = new JButton(new ImageIcon("imagenes/logo.jpg"));
		btnLogo.setBackground(new Color(153, 204, 255));
		btnLogo.setPreferredSize(new DimensionUIResource(400, 400));
		panelCentroIzquierda.add(btnLogo);
		
		JPanel panelCentroDerecha = new JPanel();
		panelCentroDerecha.setBackground(new Color(255, 153, 0));
		panelCentro.add(panelCentroDerecha);
		panelCentroDerecha.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel panelCentroDerechaInput = new JPanel();
		panelCentroDerechaInput.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCentroDerechaInput.setBackground(new Color(153, 204, 255));
		panelCentroDerechaInput.setForeground(new Color(255, 102, 51));
		panelCentroDerecha.add(panelCentroDerechaInput);
		panelCentroDerechaInput.setLayout(new MigLayout("", "[212px,grow]", "[82.00px][28px][26px][28px][][][37.00px][24.00,top][26.00][40.00][28.00][]"));
		
		JLabel lblTituloRegistro = new JLabel("REGISTRO");
		lblTituloRegistro.setForeground(new Color(255, 153, 0));
		lblTituloRegistro.setBackground(new Color(255, 102, 0));
		lblTituloRegistro.setFont(new Font("Dialog", Font.BOLD, 42));
		panelCentroDerechaInput.add(lblTituloRegistro, "cell 0 0,alignx center,aligny bottom");
		
		JLabel lblNick = new JLabel("Nick :");
		lblNick.setForeground(new Color(255, 255, 255));
		lblNick.setBackground(new Color(0, 0, 0));
		lblNick.setHorizontalAlignment(SwingConstants.CENTER);
		lblNick.setFont(new Font("Lato", Font.BOLD, 21));
		panelCentroDerechaInput.add(lblNick, "cell 0 2,alignx center,aligny center");
		
		txtNick = new JTextField();
		txtNick.setForeground(new Color(255, 255, 255));
		txtNick.setBackground(new Color(255, 153, 0));
		txtNick.setColumns(10);
		txtNick.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentroDerechaInput.add(txtNick, "cell 0 3,growx,aligny center");
		
		JLabel lblContraseya = new JLabel("Contraseya :");
		lblContraseya.setForeground(new Color(255, 255, 255));
		lblContraseya.setFont(new Font("Lato", Font.BOLD, 21));
		panelCentroDerechaInput.add(lblContraseya, "cell 0 5,alignx center,aligny center");
		
		txtContraseya = new JPasswordField();
		txtContraseya.setForeground(new Color(255, 255, 255));
		txtContraseya.setBackground(new Color(255, 153, 0));
		txtContraseya.setColumns(10);
		txtContraseya.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentroDerechaInput.add(txtContraseya, "cell 0 6,growx,aligny center");
		
		JLabel lblAvatar = new JLabel("Avatar :");
		lblAvatar.setForeground(new Color(255, 255, 255));
		lblAvatar.setFont(new Font("Lato", Font.BOLD, 21));
		panelCentroDerechaInput.add(lblAvatar, "cell 0 8,alignx center");
		
		btnAvatar = new JButton("Escoger avatar");
		btnAvatar.setForeground(new Color(255, 255, 255));
		btnAvatar.setBackground(new Color(255, 102, 0));
		btnAvatar.setFont(new Font("Lato", Font.BOLD, 15));
		panelCentroDerechaInput.add(btnAvatar, "cell 0 9,alignx center");
		
	
	
		
		JButton btnRegistrarse = new JButton("Registrarme");
		btnRegistrarse.setForeground(new Color(255, 255, 255));
		btnRegistrarse.setBackground(new Color(255, 102, 0));
		btnRegistrarse.setFont(new Font("Lato", Font.BOLD, 12));
		panelCentroDerechaInput.add(btnRegistrarse, "cell 0 11,alignx center,aligny center");
		
		
		panelCentroIzquierda.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		panelNorte.setPreferredSize(new Dimension(100,100));
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblRegistroGratis = new JLabel("Registrate aqui, solo te llevara un minuto...");
		lblRegistroGratis.setBackground(new Color(255, 153, 0));
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
		panelEste.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblAvatarSeleccionado = new JLabel("");
		panelEste.add(lblAvatarSeleccionado);
		
		System.out.println(ficheroSeleccionado);
	
		setVisible(true);
		
		/**EVENTOS**/
		
		
		/**
		 * Cuando el boton Registrar se active, se comparara los datos introducios con los existentes en la BBBDD y aceptara el registrao en caso exitoso
		 */
		
		btnAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser("avatares"); //Objeto que me va a permitir seleccionar un fichero
				FileNameExtensionFilter fnef = new FileNameExtensionFilter("JPG & PNG", "jpg","png");
				fc.setFileFilter(fnef);
				
				int sel = fc.showOpenDialog(null); //Abre la ventana de selección de fichero
				if(sel == JFileChooser.APPROVE_OPTION) { //Si ha seleccionado abrir
					ficheroSeleccionado = fc.getSelectedFile();
					System.out.println("Nombre del fichero seleccinado: "+ ficheroSeleccionado.getName());
					
					System.out.println("Ruta del fichero seleccionado: "+ ficheroSeleccionado.getAbsolutePath());	
				}
				ImageIcon im = new ImageIcon(ficheroSeleccionado.getAbsolutePath());
				ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(100,100,ImageView.CENTER));
				lblAvatarSeleccionado.setIcon(imagenConDimensiones);
				lblAvatarSeleccionado.setPreferredSize(new DimensionUIResource(100, 100));
			}
		});
	
		/**
		 * Boton que valida si es posible registrar al usuario con los datos introducidos
		 */
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ERnick = "[A-Za-z]{1,15}";
				String nick = txtNick.getText();
				String contraseya = txtContraseya.getText();
				String avatar = ficheroSeleccionado.getAbsolutePath();
				System.out.println(avatar);
				boolean correctoNick = Pattern.matches(ERnick, nick);
				
				if((correctoNick) && (!nick.equals("admin")) && (!nick.equals("") && !contraseya.equals("") )) {
					Connection con = null;
					Connection con2 = null;
					try {
						con = BD.initBD("baseDeDatos.db");
					} catch (DeustoException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					int valor = 0;
					try {
						valor = BD.estaRegistrado(con, nick);
					} catch (DeustoException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if(valor == 0) {
						Usuario u = new Usuario(nick, contraseya,avatar);
						VentanaInicio.tmUsuarios.put(u.getNick(), u);
						try {
							con2 = BD.initBD("baseDeDatos.db");
						} catch (DeustoException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						try {
							BD.intertarUsuarioBBDD(con2,u);
						} catch (DeustoException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						try {
							BD.closeBD(con2);
						} catch (DeustoException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						try {
							Handler handler = new FileHandler("loggers/LogUserRegistered");
							handler.setFormatter(new SimpleFormatter());
							log.addHandler(handler);
							log.log(Level.INFO, "Se ha añadido un usuario");
							
						} catch (SecurityException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						
						JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "REGISTRO CORRECTO", JOptionPane.INFORMATION_MESSAGE);
						vaciarCampos();
						ventanaActual.dispose();
						try {
							new VentanaInicio();
							
						} catch (DeustoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nick ya en uso, prueba con otro distinto", "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
						txtNick.setText("");
					}
				}else {
					JOptionPane.showMessageDialog(null, "El nombre no es correcto, recuerda que tu nick: \n\t 1. No puede contener numeros, solo letras \n\t 2. No puedes crear cuenta con nick 'admin' \n\t 3. El campo contraseña no puede estar vacio", "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
					txtNick.setText("");
				}
				txtNick.setText("");
				txtContraseya.setText("");
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
	public static void vaciarCampos() {
		txtNick.setText("");
		txtContraseya.setText("");
		lblAvatarSeleccionado.setIcon(null);
	}
}
	
	
	



