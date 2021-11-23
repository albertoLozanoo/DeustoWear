package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Articulo;
import clases.Usuario;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;

public class VentanaAdmin extends JFrame {

	private JPanel contentPane;
	public Connection con;
	public JFrame ventanaAnterior,ventanaActual;
	private JList<Articulo> listaArticulos;
	private DefaultListModel<Articulo> modeloListaArticulos;
	
	
	/**
	 * Create the frame.
	 */
	public VentanaAdmin(JFrame va,Usuario u) {
		ventanaAnterior = va;
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 421);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelIZQayadirArticulo = new JPanel();
		panelCentro.add(panelIZQayadirArticulo);
		panelIZQayadirArticulo.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTituloIZQ = new JPanel();
		panelTituloIZQ.setBackground(new Color(255, 102, 0));
		panelIZQayadirArticulo.add(panelTituloIZQ, BorderLayout.NORTH);
		
		JLabel lblAnyadirArticulo = new JLabel("A\u00D1ADIR ARTICULO");
		lblAnyadirArticulo.setForeground(new Color(255, 255, 255));
		lblAnyadirArticulo.setFont(new Font("Lato", Font.BOLD, 20));
		panelTituloIZQ.add(lblAnyadirArticulo);
		
		JPanel panelIZQSur = new JPanel();
		panelIZQSur.setBackground(new Color(255, 102, 0));
		panelIZQayadirArticulo.add(panelIZQSur, BorderLayout.SOUTH);
		
		JButton btnCamiseta = new JButton("Camiseta");
		btnCamiseta.setForeground(new Color(255, 255, 255));
		btnCamiseta.setBackground(new Color(255, 153, 0));
		btnCamiseta.setFont(new Font("Lato", Font.BOLD, 15));
		panelIZQSur.add(btnCamiseta);
		
		JButton btnSudadera = new JButton("Sudadera");
		btnSudadera.setForeground(new Color(255, 255, 255));
		btnSudadera.setBackground(new Color(255, 153, 0));
		btnSudadera.setFont(new Font("Lato", Font.BOLD, 15));
		panelIZQSur.add(btnSudadera);
		
		JButton btnPantalon = new JButton("Pantalon");
		btnPantalon.setForeground(new Color(255, 255, 255));
		btnPantalon.setBackground(new Color(255, 153, 0));
		btnPantalon.setFont(new Font("Lato", Font.BOLD, 15));
		panelIZQSur.add(btnPantalon);
		
		JPanel panelCNTeliminarArticulo = new JPanel();
		panelCentro.add(panelCNTeliminarArticulo);
		panelCNTeliminarArticulo.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTituloDCH = new JPanel();
		panelTituloDCH.setBackground(new Color(255, 102, 0));
		panelCNTeliminarArticulo.add(panelTituloDCH, BorderLayout.NORTH);
		
		JLabel lblEliminarArticulo = new JLabel("ELIMINAR ARTICULO");
		lblEliminarArticulo.setForeground(new Color(255, 255, 255));
		lblEliminarArticulo.setFont(new Font("Lato", Font.BOLD, 20));
		panelTituloDCH.add(lblEliminarArticulo);
		
		JPanel panelDCHsur = new JPanel();
		panelDCHsur.setBackground(new Color(255, 102, 0));
		panelCNTeliminarArticulo.add(panelDCHsur, BorderLayout.SOUTH);
		
		JButton btnElimiarArticulo = new JButton("Eliminar Articulo");
		btnElimiarArticulo.setForeground(new Color(255, 255, 255));
		btnElimiarArticulo.setBackground(new Color(255, 153, 0));
		btnElimiarArticulo.setFont(new Font("Lato", Font.BOLD, 15));
		panelDCHsur.add(btnElimiarArticulo);
		
		JPanel panelDerechaCentroLista = new JPanel();
		panelCNTeliminarArticulo.add(panelDerechaCentroLista, BorderLayout.CENTER);
		panelDerechaCentroLista.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTituloListaArticulos = new JPanel();
		panelDerechaCentroLista.add(panelTituloListaArticulos, BorderLayout.NORTH);
		
		JLabel lblListaArticulos = new JLabel("Lista de articulos");
		lblListaArticulos.setFont(new Font("Lato", Font.BOLD, 23));
		panelTituloListaArticulos.add(lblListaArticulos);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(0, 153, 204));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnInicio = new JButton("Inicio");
		btnInicio.setForeground(new Color(255, 255, 255));
		btnInicio.setBackground(new Color(255, 153, 0));
		panelSur.add(btnInicio);
		
		modeloListaArticulos = new DefaultListModel<>();
		listaArticulos = new JList<>(modeloListaArticulos);
		
		/*for(Integer clave : VentanaInicio.tmArticulos.keySet()) {
			for(Articulo valor : VentanaInicio.tmArticulos.get()) {
				modeloListaArticulos.addElement(valor);
			}
		}*/
		
		/**EVENTOS*/
		btnInicio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaInicio();
				//new VentanaPerfil(ventanaActual);
			}
		});
	}
	
}
