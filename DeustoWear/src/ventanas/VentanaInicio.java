package ventanas;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import clases.GestionFicheros;
import clases.Pantalon;
import clases.Sudadera;
import clases.Usuario;
import clases.Venta;
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
import javax.swing.border.BevelBorder;

public class VentanaInicio extends JFrame {

	private JPanel contentPane,panelCentro,panelNorte,panelSur;
	private JTextPane txtpnRegistrado;
	private JButton btnIniciarSesion,btnRegistrarse;
	private JFrame ventanaActual;
	public static Connection con;
	public static Usuario u = new Usuario();
	private static Logger logger = Logger.getLogger("LogUserLogged"); 
	
	
	public static TreeMap<String, String> tmUsuarios = new TreeMap<>();
	public static TreeMap<Integer,Articulo> tmArticulos = new TreeMap<>();
	public static HashMap<Usuario,ArrayList<Venta>> hmVentasTotales = new HashMap<>();

	
	
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
	private JPasswordField txtContraseya;
	private JPanel panelNorteInsideIzquierda;
	private JPanel panelCentroInsideIzquierda;
	private JButton btnLogo;
	private JButton btnVisualizar;
	private JButton btnOcultar;
	
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
	
	public static TreeMap<String, String> getTmUsuarios() {
		return tmUsuarios;
	}

	public static void setTmUsuarios(TreeMap<String, String> tmUsuarios) {
		VentanaInicio.tmUsuarios = tmUsuarios;
	}

	public static TreeMap<Integer, Articulo> getTmArticulos() {
		return tmArticulos;
	}

	public static void setTmArticulos(TreeMap<Integer, Articulo> tmArticulos) {
		VentanaInicio.tmArticulos = tmArticulos;
	}
	
	public static HashMap<Usuario, ArrayList<Venta>> getHMVentasTotales() {
		return hmVentasTotales;
	}

	public static void setHMVentasTotales(HashMap<Usuario,  ArrayList<Venta>> hmVentasTotales) {
		VentanaInicio.hmVentasTotales = hmVentasTotales;
	}

	public VentanaInicio() throws DeustoException {
		/*Articulo a1 = new Camiseta(111,"camiseta","S",10,"Negro","Hombre","imagenes/camisetas/camiseta1.png");
		Articulo a2 = new Pantalon(123, "pantalon", "M", 25, "Azul","Mujer","imagenes/pantalones/pantalon1.png","Corto");
		Articulo a3 = new Sudadera(332,"sudadera","XXL",30,"Rojo","Hombre","imagenes/sudaderas/sudadera1.png","Con Capucha");*/
		setVisible(true);
		
		
		con = BD.initBD("baseDeDatos.db");
		BD.crearTablas(con);
		tmUsuarios = BD.cargarMapaUsuariosDeInfoBBDD(con);
		tmArticulos = BD.cargarMapaArticulosDeInfoBBDD(con);
		/*BD.insertarCamisetaBBDD(con, a1);
		BD.insertarPantalonBBDD(con, a2);
		BD.insertarSudaderaBBDD(con, a3);*/
		BD.closeBD(con);
		
		
		ventanaActual = this;
		setTitle("Bienvenido a DeustoWear");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1176, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelNorte = new JPanel();
		panelNorte.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelNorte.setBackground(new Color(153, 204, 255));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		panelCentro = new JPanel();
		panelCentro.setBackground(new Color(255, 102, 0));
		panelCentro.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(1, 0, 0, 0));
		
		panelCentroIzquierda = new JPanel();
		panelCentroIzquierda.setBackground(new Color(255, 102, 0));
		panelCentro.add(panelCentroIzquierda);
		panelCentroIzquierda.setLayout(new BorderLayout(10, 10));
		
		panelNorteInsideIzquierda = new JPanel();
		panelNorteInsideIzquierda.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelNorteInsideIzquierda.setBackground(new Color(255, 102, 0));
		panelCentroIzquierda.add(panelNorteInsideIzquierda, BorderLayout.NORTH);
		panelNorteInsideIzquierda.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblIzquierdaFrase1 = new JLabel("Confianza");
		lblIzquierdaFrase1.setFont(new Font("Dialog", Font.BOLD, 22));
		lblIzquierdaFrase1.setForeground(new Color(255, 255, 255));
		panelNorteInsideIzquierda.add(lblIzquierdaFrase1);
		
		lblIzquierdaFrase2 = new JLabel("Perseverancia");
		lblIzquierdaFrase2.setForeground(new Color(255, 255, 255));
		lblIzquierdaFrase2.setFont(new Font("Dialog", Font.BOLD, 22));
		panelNorteInsideIzquierda.add(lblIzquierdaFrase2);
		
		lblIzquierdaFrase3 = new JLabel("Constancia");
		lblIzquierdaFrase3.setForeground(new Color(255, 255, 255));
		lblIzquierdaFrase3.setFont(new Font("Dialog", Font.BOLD, 22));
		panelNorteInsideIzquierda.add(lblIzquierdaFrase3);
		
		panelCentroInsideIzquierda = new JPanel();
		panelCentroInsideIzquierda.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelCentroInsideIzquierda.setBackground(new Color(255, 102, 0));
		panelCentroIzquierda.add(panelCentroInsideIzquierda, BorderLayout.CENTER);
		
		btnLogo = new JButton("");
		btnLogo.setBackground(new Color(255, 102, 0));
		ImageIcon im = new ImageIcon("imagenes/logo.jpg");
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(300,300,ImageView.CENTER));
		panelCentroInsideIzquierda.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnLogo.setIcon(imagenConDimensiones);
		btnLogo.setPreferredSize(new DimensionUIResource(350, 300));
		panelCentroInsideIzquierda.add(btnLogo);
		
		
		panelCentroDerecha = new JPanel();
		panelCentroDerecha.setBackground(new Color(255, 102, 0));
		panelCentro.add(panelCentroDerecha);
		panelCentroDerecha.setLayout(new BoxLayout(panelCentroDerecha, BoxLayout.Y_AXIS));
		
		panelCentroDerechaArriba = new JPanel();
		panelCentroDerechaArriba.setBackground(new Color(153, 204, 255));
		panelCentroDerecha.add(panelCentroDerechaArriba);
		panelCentroDerechaArriba.setLayout(new BorderLayout(10, 10));
		
		panelCentroInside = new JPanel();
		panelCentroInside.setBackground(new Color(153, 204, 255));
		panelCentroDerechaArriba.add(panelCentroInside, BorderLayout.CENTER);
		panelCentroInside.setLayout(new MigLayout("", "[160.00,grow]", "[][][][][][][][][][64.00][59.00][20.00,grow][][]"));
		
		lblNick = new JLabel("Nick :");
		lblNick.setForeground(new Color(255, 255, 255));
		lblNick.setFont(new Font("Lato", Font.BOLD, 31));
		panelCentroInside.add(lblNick, "cell 0 2,alignx center,aligny center");
		
		txtNick = new JTextField();
		txtNick.setBackground(new Color(255, 153, 0));
		txtNick.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentroInside.add(txtNick, "cell 0 5,alignx center,aligny center");
		txtNick.setColumns(25);
		
		lblContraseya = new JLabel("Contraseya :");
		lblContraseya.setBackground(new Color(240, 240, 240));
		lblContraseya.setForeground(new Color(255, 255, 255));
		lblContraseya.setFont(new Font("Lato", Font.BOLD, 31));
		panelCentroInside.add(lblContraseya, "cell 0 7,alignx center,aligny center");
		
		panelNorteInside = new JPanel();
		panelNorteInside.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
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
		panelSur.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelSur.setBackground(new Color(255, 153, 0));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelNorte.setLayout(new MigLayout("", "[936.00px,grow,center]", "[30px][][30px]"));
		
		
		txtpnRegistrado = new JTextPane();
		txtpnRegistrado.setEditable(false);
		txtpnRegistrado.setBackground(new Color(255, 102, 0));
		txtpnRegistrado.setForeground(Color.WHITE);
		panelNorte.add(txtpnRegistrado, "cell 0 0,alignx center");
		txtpnRegistrado.setFont(new Font("Verdana", Font.BOLD, 19));
		txtpnRegistrado.setText("¿Todavia no te has registrado?");
		
		btnRegistrarse = new JButton("Haz click aqui para registrarte");
		btnRegistrarse.setForeground(new Color(255, 255, 255));
		btnRegistrarse.setFont(new Font("Verdana", Font.PLAIN, 14));
		panelNorte.add(btnRegistrarse, "cell 0 1,alignx center,aligny center");
		btnRegistrarse.setBackground(new Color(255, 102, 0));
		
		lblRegistrarEsGratis = new JLabel("Registrarse gratis");
		lblRegistrarEsGratis.setBackground(new Color(255, 255, 255));
		lblRegistrarEsGratis.setForeground(new Color(255, 102, 0));
		lblRegistrarEsGratis.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 15));
		panelNorte.add(lblRegistrarEsGratis, "cell 0 2,aligny top");
		
		txtNick.setForeground(new Color(255,255,255));
		
		txtContraseya = new JPasswordField();
		txtContraseya.setBackground(new Color(255, 153, 0));
		txtContraseya.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentroInside.add(txtContraseya, "flowx,cell 0 9,alignx center,aligny center");
		txtContraseya.setColumns(25);
		txtContraseya.setForeground(new Color(255,255,255));
		
		txtContraseya.setEchoChar('*');
		
		
		ImageIcon imVisualizar = new ImageIcon("imagenes/iconoVerContraseya.png");
		ImageIcon imagenConDimensionesVisualizar = new ImageIcon(imVisualizar.getImage().getScaledInstance(40,40,ImageView.CENTER));
		
		ImageIcon imOcultar = new ImageIcon("imagenes/iconoOcultarContraseya.png");
		ImageIcon imagenConDimensionesOcultar = new ImageIcon(imOcultar.getImage().getScaledInstance(40,40,ImageView.CENTER));
		btnOcultar = new JButton();
		btnOcultar.setBackground(new Color(255,255,255));
		btnOcultar.setPreferredSize(new DimensionUIResource(10, 10));
		btnOcultar.setIcon(imagenConDimensionesOcultar);
		panelCentroInside.add(btnOcultar, "flowx,cell 0 10,alignx center,aligny top");
		btnOcultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtContraseya.setEchoChar('*');
			}
		});
		btnVisualizar = new JButton();
		btnVisualizar.setBackground(new Color(255,255,255));
		btnVisualizar.setPreferredSize(new DimensionUIResource(10, 10));
		btnVisualizar.setIcon(imagenConDimensionesVisualizar);
		panelCentroInside.add(btnVisualizar, "cell 0 10,alignx center,aligny top");
		
		btnVisualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource()==btnVisualizar)
					txtContraseya.setEchoChar((char)0);
			}
		});
		

		btnIniciarSesion = new JButton("Iniciar Sesion");
		panelCentroInside.add(btnIniciarSesion, "cell 0 12,alignx center");
		btnIniciarSesion.setForeground(new Color(0, 0, 153));
		btnIniciarSesion.setBackground(new Color(255, 153, 0));
		btnIniciarSesion.setFont(new Font("Dialog", Font.BOLD, 18));
	
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
						JOptionPane.showMessageDialog(null, "Todavia no te has registrado","ï¿½ï¿½ERROR!!", JOptionPane.ERROR_MESSAGE);
						txtNick.setText("");
						txtContraseya.setText("");
					}else if(resul==1) {
						JOptionPane.showMessageDialog(null, "La contraseï¿½a no es correcta","ï¿½ï¿½ERROR!!", JOptionPane.ERROR_MESSAGE);
						
						txtContraseya.setText("");
					}else {
						if((resul == 2) && (!nick.equals("admin") && !c.equals("admin"))){
							
							JOptionPane.showMessageDialog(null, "Cargando WearHome, bienvenid@ "+ nick,"WELCOME", JOptionPane.INFORMATION_MESSAGE);
							u = new Usuario(nick,c);
							//u.cargarFavoritosDelFichero();
							u.setFavoritos(GestionFicheros.cargarFavoritos(nick));
							u.cargarVentasDesdeFichero();
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
							try {
								new VentanaAdmin(ventanaActual, admin);
							} catch (DeustoException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ventanaActual.dispose();
						}
					}
				}
			}
				
			
		});
		
		
		
		/*EVENTOS*/
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					btnRegistrarse.setForeground(Color.WHITE);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					btnRegistrarse.setForeground(new Color(0, 0, 153));
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
		
		/**
		 * Boton que al activarse cierra la ventana actual y abre la ventanaRegistro
		 */
		btnRegistrarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtNick.setText("");
				txtContraseya.setText("");
				ventanaActual.dispose();
				new VentanaRegistroo(ventanaActual);
				//new VentantaRegistro(ventanaActual);
			}
		});
		
		/**
		 * Boton que al activarse verifica si el usuario esta registrado ya en la BBDD
		 * Ademas si los campos son rellenados con admin, entraremos en la VentanAdministrador
		 */
	}
	
	/**
	 * Metodo recursivo que recorre el HashMap de ventas y suma el precio total de todas las ventas
	 * @param hmVentasTotales
	 * @param it
	 * @return la suma de las ventasTotales
	 */
	public static int sumaHMVentasTotales(HashMap<String,ArrayList<Venta>> hmVentasTotales,Iterator<String> it) {
		if(it.hasNext()) {
			String clave = it.next();
			ArrayList<Venta> aVentas = hmVentasTotales.get(clave);
			int suma = 0;
			for(Venta v : aVentas) {
				suma = (int) (v.getPrecioTotal() + suma);
			}
			return suma + sumaHMVentasTotales(hmVentasTotales, it);
		}else {
			return 0;
		}
	}
	
	
}
		
		
	
