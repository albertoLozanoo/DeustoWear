package ventanas;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.html.ImageView;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.FlowLayout;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

import java.util.logging.SimpleFormatter;

import javax.swing.JTextPane;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import clases.Articulo;
import clases.BD;
import clases.Camiseta;
import clases.DeustoException;
import clases.Pantalon;
import clases.Sudadera;
import clases.Usuario;
import enumeration.Sexo;
import enumeration.Talla;
import enumeration.Colores;

import java.awt.Color;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

import java.util.logging.SimpleFormatter;

public class VentanaInicio extends JFrame {

	private JPanel contentPane,panelCentro,panelNorte,panelSur;
	private JTextPane txtpnRegistrado;
	private JButton btnIniciarSesion,btnRegistrarse;
	private JFrame ventanaActual;
	public static Connection con;
	private static Logger logger = Logger.getLogger("LogUserLogged"); 
	
	
	public static TreeMap<String, Usuario> tmUsuarios = new TreeMap<>();
	public static TreeMap<Integer,Articulo> tmArticulos = new TreeMap<>();
	private JPanel panelCentroIzquierda;
	private JPanel panelCentroDerecha;
	private JPanel panelCentroDerechaArriba;
	private JPanel panelCentroDerechaAbajo;
	private JLabel lblRegistrarEsGratis;
	private JLabel lblIzquierdaFrase1;
	private JLabel lblIzquierdaFrase2;
	private JLabel lblIzquierdaFrase3;
	private JLabel lblDerechos;
	

	private JPanel panelCentroInside;
	private JPanel panelNorteInside;
	private JLabel lblIniciarSesionNorte;
	private JLabel lblNick;
	private JTextField txtNick;
	private JLabel lblContraseya;
	private JTextField txtContraseya;
	private JPanel panelNorteInsideIzquierda;
	private JPanel panelCentroInsideIzquierda;
	private JButton btnLogo;
	
	//private static Logger log; 

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
	
	public static TreeMap<String, Usuario> getTmUsuarios() {
		return tmUsuarios;
	}

	public static void setTmUsuarios(TreeMap<String, Usuario> tmUsuarios) {
		VentanaInicio.tmUsuarios = tmUsuarios;
	}

	public static TreeMap<Integer, Articulo> getTmArticulos() {
		return tmArticulos;
	}

	public static void setTmArticulos(TreeMap<Integer, Articulo> tmArticulos) {
		VentanaInicio.tmArticulos = tmArticulos;
	}

	public VentanaInicio() throws DeustoException {
		/*Articulo a1 = new Camiseta(111,"camiseta","S",10,"Negro","Hombre","imagenes/camisetas/camiseta1.png");
		Articulo a2 = new Pantalon(123, "pantalon", "M", 25, "Azul","Mujer","imagenes/pantalones/pantalon1.png","Corto");
		Articulo a3 = new Sudadera(332,"sudadera","XXL",30,"Rojo","Hombre","imagenes/sudaderas/sudadera1.png","Con Capucha");*/
		setVisible(true);
		
		con = BD.initBD("baseDeDatos.db");
		BD.crearTablas(con);
		BD.cargarMapaUsuariosDeInfoBBDD(con);
		BD.cargarMapaArticulosDeInfoBBDD(con);
		/*BD.insertarCamisetaBBDD(con, a1);
		BD.insertarPantalonBBDD(con, a2);
		BD.insertarSudaderaBBDD(con, a3);*/
		BD.closeBD(con);
		ventanaActual = this;
		setTitle("Bienvenido a DeustoWear");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1176, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelNorte = new JPanel();
		panelNorte.setBackground(new Color(51, 153, 255));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(1, 0, 0, 0));
		
		panelCentroIzquierda = new JPanel();
		panelCentroIzquierda.setBackground(new Color(204, 102, 0));
		panelCentro.add(panelCentroIzquierda);
		panelCentroIzquierda.setLayout(new BorderLayout(10, 10));
		
		panelNorteInsideIzquierda = new JPanel();
		panelNorteInsideIzquierda.setBackground(new Color(255, 102, 0));
		panelCentroIzquierda.add(panelNorteInsideIzquierda, BorderLayout.NORTH);
		
		lblIzquierdaFrase1 = new JLabel("Confianza");
		lblIzquierdaFrase1.setFont(new Font("Lato", Font.BOLD, 18));
		lblIzquierdaFrase1.setForeground(new Color(255, 255, 255));
		panelNorteInsideIzquierda.add(lblIzquierdaFrase1);
		
		lblIzquierdaFrase2 = new JLabel("Perseverancia");
		lblIzquierdaFrase2.setForeground(new Color(255, 255, 255));
		lblIzquierdaFrase2.setFont(new Font("Lato", Font.BOLD, 18));
		panelNorteInsideIzquierda.add(lblIzquierdaFrase2);
		
		lblIzquierdaFrase3 = new JLabel("Constancia");
		lblIzquierdaFrase3.setForeground(new Color(255, 255, 255));
		lblIzquierdaFrase3.setFont(new Font("Lato", Font.BOLD, 18));
		panelNorteInsideIzquierda.add(lblIzquierdaFrase3);
		
		panelCentroInsideIzquierda = new JPanel();
		panelCentroInsideIzquierda.setBackground(new Color(255, 102, 0));
		panelCentroIzquierda.add(panelCentroInsideIzquierda, BorderLayout.CENTER);
		
		btnLogo = new JButton("");
		btnLogo.setBackground(new Color(255, 102, 0));
		ImageIcon im = new ImageIcon("imagenes/logo.jpg");
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(300,300,ImageView.CENTER));
		panelCentroInsideIzquierda.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnLogo.setIcon(imagenConDimensiones);
		btnLogo.setPreferredSize(new DimensionUIResource(350, 300));
		btnLogo.setIcon(imagenConDimensiones);
		panelCentroInsideIzquierda.add(btnLogo);
		
		
		panelCentroDerecha = new JPanel();
		panelCentroDerecha.setBackground(new Color(255, 102, 0));
		panelCentro.add(panelCentroDerecha);
		panelCentroDerecha.setLayout(new BoxLayout(panelCentroDerecha, BoxLayout.Y_AXIS));
		
		panelCentroDerechaArriba = new JPanel();
		panelCentroDerechaArriba.setBackground(new Color(0, 153, 255));
		panelCentroDerecha.add(panelCentroDerechaArriba);
		panelCentroDerechaArriba.setLayout(new BorderLayout(10, 10));
		
		panelCentroInside = new JPanel();
		panelCentroInside.setBackground(new Color(51, 153, 255));
		panelCentroDerechaArriba.add(panelCentroInside, BorderLayout.CENTER);
		panelCentroInside.setLayout(new MigLayout("", "[160.00,grow]", "[][][][][][][][][][][][][][]"));
		
		lblNick = new JLabel("Nick :");
		lblNick.setForeground(new Color(0, 0, 153));
		lblNick.setFont(new Font("Lato", Font.BOLD, 31));
		panelCentroInside.add(lblNick, "cell 0 2,alignx center,aligny center");
		
		txtNick = new JTextField();
		txtNick.setBackground(new Color(255, 153, 0));
		txtNick.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentroInside.add(txtNick, "cell 0 5,alignx center,aligny center");
		txtNick.setColumns(25);
		
		lblContraseya = new JLabel("Contraseya :");
		lblContraseya.setBackground(new Color(240, 240, 240));
		lblContraseya.setForeground(new Color(0, 0, 153));
		lblContraseya.setFont(new Font("Lato", Font.BOLD, 31));
		panelCentroInside.add(lblContraseya, "cell 0 8,alignx center,aligny center");
		
		txtContraseya = new JTextField();
		txtContraseya.setBackground(new Color(255, 153, 0));
		txtContraseya.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentroInside.add(txtContraseya, "cell 0 11,alignx center,aligny center");
		txtContraseya.setColumns(25);
		
		panelNorteInside = new JPanel();
		panelNorteInside.setForeground(new Color(255, 255, 255));
		panelNorteInside.setBackground(new Color(255, 102, 0));
		panelCentroDerechaArriba.add(panelNorteInside, BorderLayout.NORTH);
		
		lblIniciarSesionNorte = new JLabel("Inicia sesion aqui");
		lblIniciarSesionNorte.setForeground(new Color(255, 255, 255));
		lblIniciarSesionNorte.setFont(new Font("Lato", Font.BOLD, 42));
		panelNorteInside.add(lblIniciarSesionNorte);
		
		panelCentroDerechaAbajo = new JPanel();
		panelCentroDerechaAbajo.setBackground(new Color(255, 102, 0));
		panelCentroDerecha.add(panelCentroDerechaAbajo);
		panelCentroDerechaAbajo.setLayout(new MigLayout("", "[318px,grow,right]", "[14px]"));
		
		lblDerechos = new JLabel("Autenticacion de Privacidad y Derechos de Uso @CompanyVersion");
		lblDerechos.setFont(new Font("Lato", Font.ITALIC, 9));
		lblDerechos.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDerechos.setBackground(new Color(255, 255, 255));
		panelCentroDerechaAbajo.add(lblDerechos, "cell 0 0,alignx right,aligny bottom");
		
		panelSur = new JPanel();
		panelSur.setBackground(new Color(255, 153, 0));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelNorte.setLayout(new MigLayout("", "[936.00px,grow,center]", "[30px][][30px]"));
		
		
		txtpnRegistrado = new JTextPane();
		txtpnRegistrado.setBackground(new Color(255, 153, 102));
		txtpnRegistrado.setForeground(Color.WHITE);
		panelNorte.add(txtpnRegistrado, "cell 0 0,alignx center");
		txtpnRegistrado.setFont(new Font("Verdana", Font.BOLD, 19));
		txtpnRegistrado.setText("¿Todavia no te has registrado?");
		

		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setForeground(new Color(0, 0, 153));
		btnIniciarSesion.setBackground(new Color(153, 255, 153));
		btnIniciarSesion.setFont(new Font("Lato", Font.BOLD, 20));
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
		
		txtNick.setForeground(new Color(255,255,255));
		txtContraseya.setForeground(new Color(255,255,255));
		
		
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
		 * Ademas si los campos son rellenados con admin, entraremos en la VentanAdministrador
		 */
		btnIniciarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nick = txtNick.getText();
				String c = txtContraseya.getText();
				
				if((!nick.equals("") && !c.equals("")) || (!nick.equals("admin") && !c.equals("admin"))) {
					Connection con = null;
					try {
						con = BD.initBD("baseDeDatos.db");
					} catch (DeustoException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					int resul = 0;
					try {
						resul = BD.obtenerUsuario(con, nick, c);
					} catch (DeustoException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if((resul == 0) && !nick.equals("admin") && !c.equals("admin")){
						JOptionPane.showMessageDialog(null, "Todavia no te has registrado","¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
						txtNick.setText("");
						txtContraseya.setText("");
					}else if(resul==1) {
						JOptionPane.showMessageDialog(null, "La contraseña no es correcta","¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
						
						txtContraseya.setText("");
					}else {
						if((resul == 2) && (!nick.equals("admin") && !c.equals("admin"))){
							
							JOptionPane.showMessageDialog(null, "Cargando WearHome, bienvenid@ "+ nick,"WELCOME", JOptionPane.INFORMATION_MESSAGE);
							Usuario u = new Usuario(nick,c);
							u.cargarFavoritosDelFichero();
							Handler handler = null;
							try {
								handler = new FileHandler("loggers/LogUserLogged");
							} catch (SecurityException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							handler.setFormatter(new SimpleFormatter());
							logger.addHandler(handler);
							logger.log(Level.INFO, "Se ha loggoeado");
							try {
								new VentanaHome(ventanaActual, u);
							} catch (DeustoException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ventanaActual.dispose();
						}else if(nick.equals("admin") && c.equals("admin")){
							/*Handler handler = new FileHandler("loggers/LogUserLoggin");
							handler.setFormatter(new SimpleFormatter());
							log.addHandler(handler);
							log.log(Level.INFO, "Se ha loggeado el usuario" + nick + "\nHora: " + System.currentTimeMillis());*/
							JOptionPane.showMessageDialog(null, "Cargando WearHome VISUAL admin web","WELCOME", JOptionPane.INFORMATION_MESSAGE);
							Usuario admin = new Usuario("admin","admin");
							new VentanaAdmin(ventanaActual, admin);
							ventanaActual.dispose();
						}
					}
				}
			}
				
			
		});
	}
}
		
		
	
