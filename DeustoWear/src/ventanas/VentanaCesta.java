package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.ImageView;

import clases.Articulo;
import clases.BD;
import clases.DeustoException;
import clases.Usuario;
import clases.Venta;
import enumeration.Colores;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;

public class VentanaCesta extends JFrame {

	private JPanel contentPane,panelCentro;
	private JComboBox<String> comboTalla, comboPrenda;
	private JFrame ventanaActual,ventanaAnterior;
	private JButton btnLogo,btnEliminarArticulo;
	private JTable tablaArticulos;
	private DefaultTableModel modeloTablaArticulos = new DefaultTableModel();
	public Usuario u;
	private JTextArea ticket;
	private JScrollPane scrollAreaResumen;
	private double precioTotal;
	
	/**
	 * Create the frame.
	 */
	
	public VentanaCesta(JFrame va,Usuario u) {
		ventanaAnterior = va;
		ventanaActual = this;
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1650, 750);
		setTitle("Cesta de " + u.getNick());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));
		contentPane.setBackground(new Color(0, 153, 255));
		ticket = new JTextArea();
		scrollAreaResumen = new JScrollPane(ticket);
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelNorte.setBackground(new Color(255, 153, 0));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new MigLayout("", "[59.00][372.00,grow,center]", "[]"));
		
		ImageIcon im = new ImageIcon("imagenes/logo.jpg");
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(70,70,ImageView.CENTER));
		btnLogo = new JButton();
		btnLogo.setBackground(new Color(255, 153, 0));
		btnLogo.setPreferredSize(new DimensionUIResource(70, 70));
		btnLogo.setIcon(imagenConDimensiones);
		
		panelNorte.add(btnLogo, "cell 0 0");
		
		JLabel lblTitulo = new JLabel("Mi cesta ");
		lblTitulo.setBackground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Lato", Font.BOLD, 28));
		lblTitulo.setForeground(new Color(255, 255, 255));
		panelNorte.add(lblTitulo, "cell 1 0,alignx center,aligny center");
		
		
		JLabel lblBuscarAqui = new JLabel("Buscar aqui...");
		lblBuscarAqui.setForeground(new Color(255, 255, 255));
		lblBuscarAqui.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarAqui.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 19));
		
		
		JLabel lblComboPrenda = new JLabel("Prenda");
		lblComboPrenda.setForeground(new Color(255, 255, 255));
		lblComboPrenda.setFont(new Font("Lato", Font.PLAIN, 18));
		
		comboPrenda = new JComboBox<String>();
		comboPrenda.setToolTipText("");
		
		
		JLabel lblComboTalla = new JLabel("Talla");
		lblComboTalla.setForeground(new Color(255, 255, 255));
		lblComboTalla.setFont(new Font("Lato", Font.PLAIN, 18));
		lblComboTalla.setHorizontalAlignment(SwingConstants.CENTER);
		lblComboTalla.setBackground(new Color(240, 240, 240));
		
		
		comboTalla = new JComboBox<String>();
		
		
		JRadioButton rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setForeground(new Color(255, 255, 255));
		rdbtnHombre.setFont(new Font("Lato", Font.PLAIN, 18));
		rdbtnHombre.setBackground(new Color(255, 153, 0));
		
		
		JRadioButton rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setForeground(new Color(255, 255, 255));
		rdbtnMujer.setFont(new Font("Lato", Font.PLAIN, 18));
		rdbtnMujer.setBackground(new Color(255, 153, 0));
		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(255, 102, 0));
		btnBuscar.setFont(new Font("Lato", Font.PLAIN, 15));
		
		
		JPanel panelSur = new JPanel();
		panelSur.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelSur.setBackground(new Color(255, 102, 51));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout(0, 4, 0, 0));

	
		
		JButton btnWearHome = new JButton("Home");
		btnWearHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnWearHome.setFont(new Font("Lato", Font.PLAIN, 19));
		btnWearHome.setForeground(Color.BLACK);
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
		btnPerfil.setBackground(new Color(204, 102, 51));
		panelSur.add(btnPerfil);
		
		panelCentro = new JPanel();
		panelCentro.setBackground(new Color(255, 153, 51));
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentroSur = new JPanel();
		panelCentroSur.setBackground(new Color(153, 204, 255));
		panelCentroSur.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCentro.add(panelCentroSur, BorderLayout.SOUTH);
		panelCentroSur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnEliminarCesta = new JButton("Eliminar cesta\r\n");
		btnEliminarCesta.setBackground(new Color(0, 0, 0));
		btnEliminarCesta.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnEliminarCesta.setForeground(Color.WHITE);
		panelCentroSur.add(btnEliminarCesta);
		
		btnEliminarArticulo = new JButton("Eliminar Articulo\r\n");
		btnEliminarArticulo.setBackground(new Color(200, 0, 30));
		btnEliminarArticulo.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnEliminarArticulo.setForeground(Color.WHITE);
		panelCentroSur.add(btnEliminarArticulo);
		
		JButton btnSeguirComprando = new JButton("Seguir comprando\r\n");
		btnSeguirComprando.setBackground(new Color(10, 100, 255));
		btnSeguirComprando.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnSeguirComprando.setForeground(Color.WHITE);
		panelCentroSur.add(btnSeguirComprando);
		cargarCarritoEnTextArea();
		
		
		JButton btnPagar = new JButton("Pagar\r\n");
		btnPagar.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnPagar.setBackground(Color.GREEN);
		btnCesta.setEnabled(false);
		panelCentroSur.add(btnPagar);
		
		JPanel panelCentroCentro = new JPanel();
		panelCentroCentro.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCentroCentro.setBackground(new Color(153, 204, 255));
		panelCentro.add(panelCentroCentro, BorderLayout.CENTER);
		panelCentroCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelCentroDerecha = new JPanel();
		panelCentroDerecha.setBackground(new Color(153, 204, 255));
		panelCentroDerecha.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelCentroDerecha, BorderLayout.EAST);
		panelCentroDerecha.setLayout(new MigLayout("", "[207px][1px]", "[202.00px][grow]"));
		
		JLabel lblPrecioTotal = new JLabel("Precio Total :");
		lblPrecioTotal.setFont(new Font("Microsoft YaHei", Font.BOLD, 19));
		panelCentroDerecha.add(lblPrecioTotal, "cell 0 0,alignx center,aligny bottom");
		
		precioTotal = u.sumaTotalAPagar();
		
		JLabel lblPrecioTotalInput = new JLabel(""+ precioTotal);
		panelCentroDerecha.add(lblPrecioTotalInput, "cell 0 1,alignx center,aligny top");
		
		
		/**
		 * Comprobacion del estado de la compra
		 * 	Si el carrito del usuario esta vacio el boton pagar estara desactivado
		 */
		if(u.getCarrito().size() <1) {
			btnPagar.setEnabled(false);
		}
		
		
		
		/**
		 * Tabla de los articulos del carrito del usuario
		 */
		String [] header = {"TAG","TALLA", "PRECIO","COLOR", "SEXO"};
		modeloTablaArticulos.setColumnIdentifiers(header);
		for(Articulo a : u.getCarrito()) {
			String dataRow[] = {a.getName(),a.getTalla(),String.valueOf(a.getPrecio()),a.getColor(),a.getSexo()};
			modeloTablaArticulos.addRow(dataRow);	
		}
		
		tablaArticulos = new JTable(modeloTablaArticulos);
		JScrollPane scroll =new JScrollPane(tablaArticulos);
		panelCentroCentro.add(tablaArticulos);
		panelCentroCentro.add(scroll);
		
		
		/*EVENTOS*/
		/**
		 * Boton que permite eliminar todos los articulos de la cesta
		 */
		btnEliminarCesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modeloTablaArticulos.setRowCount(0);
				u.limpiarCarrito();
				lblPrecioTotal.setForeground(new Color(240,240,240));
				lblPrecioTotalInput.setForeground(new Color(240,240,240));
			}
		});
		/**
		 * Boton que permite eliminar un articulo en contreto de la lista
		 */
		btnEliminarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = tablaArticulos.getSelectedRow();
				u.eliminarArticulo(index);
				double precioAreducir = Double.parseDouble((String)modeloTablaArticulos.getValueAt(index, 2));
				modeloTablaArticulos.removeRow(tablaArticulos.getSelectedRow());
				JOptionPane.showMessageDialog(null, "Art???culo eliminado del carrito ","DONE", JOptionPane.INFORMATION_MESSAGE);
				precioTotal = precioTotal - precioAreducir;
				lblPrecioTotalInput.setText(""+ precioTotal);
				panelCentro.updateUI();
			}
		});
		
		/**
		 * Boton que permite acceder al pago siempre y cuando haya agregado algun aritculo a su carrito
		 */
		btnPagar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				int resp = JOptionPane.showConfirmDialog(null, "?Estas seguro de que quieres finalizar la compra de "+precioTotal + "????");
				if(resp==0) {
				new VentanaEsperaTransaccion(va, u);
				Connection con = null;
				long s;
				try {
					con = BD.initBD("baseDeDatos.db");
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				u.comprar();
				//JOptionPane.showMessageDialog(null, "Compra registrada con exito " + u.getNick() + "\n gracias por tu visita te dejamos con tus \n articulos favoritos","??????GRACIAS!!", JOptionPane.INFORMATION_MESSAGE);
				Runnable r = new Runnable() {
					public void run() {
						for(int i=255;i>=0;i--) {
							ticket.setForeground(new Color(i,i,i));
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						ticket.setText("");
					}
				};
				Thread t = new Thread(r);
				t.start();
				generarTicket();
				try {
					BD.registrarVenta(con,u);
				} catch (DeustoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				precioTotal = 0.0;
				
				modeloTablaArticulos.setRowCount(0);
				u.eliminarCarrito(u.getCarrito());
				lblPrecioTotal.setForeground(new Color(240,240,240));
				lblPrecioTotalInput.setForeground(new Color(240,240,240));
				try {
					BD.closeBD(con);
				} catch (DeustoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				u.guardarVentasEnFichero();
				ventanaActual.dispose();
				//new VentanaFavoritos(va, u);
			}}
		});
		
		/**
		 * Boton que lleva a la ventana Inicio
		 */
		btnLogo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				try {
					new VentanaInicio();
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		/**
		 * Boton que lleva a la ventana Perfil
		 */
		btnPerfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				try {
					new VentanaPerfil(ventanaActual,u);
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		/**
		 * Boton que lleva a la ventana Home
		 */
		btnWearHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				try {
					new VentanaHome(ventanaActual,u);
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		/**
		 * Boton que lleva a la ventna Favoritos
		 */
		btnFavoritos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaFavoritos(ventanaActual,u);
				
			}
		});
		
		/**
		 * Boton que lleva de nuevo a la ventana Home para seguir comprando 
		 */
		btnSeguirComprando.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				try {
					new VentanaHome(ventanaActual,u);
				} catch (DeustoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(precioTotal);
				
			}
		});
	}
	
	/**
	 *Metodo que permite cargar los articulos del carrito del usuario en TextArea 
	 */
	private void cargarCarritoEnTextArea() {
		String texto = "";
		double total = 0;
		for(Articulo a : VentanaInicio.u.getCarrito()) {
			texto = texto + a + "\n";
			total = total + a.getPrecio();
		}
		texto = texto + "TOTAL: "+total+" ???";
		ticket.setText(texto);
	}
	
	/**
	 * Metodo que genera un ticket para el usuario reghistradno la hora de la compra 
	 */
	private void generarTicket() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy" + " ("+ System.currentTimeMillis()+")");
		
		Date d = new Date(System.currentTimeMillis());
		String nomfich ="tickets/"+ u.getNick()+" "+sdf.format(d) +".txt";
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(nomfich);
			pw.println(ticket.getText());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pw!=null) {
				pw.flush();
				pw.close();
			}
		}
	}
	
	
	

	
	

}
