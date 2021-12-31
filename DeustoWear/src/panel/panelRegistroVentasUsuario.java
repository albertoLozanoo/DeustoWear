package panel;

import javax.swing.JPanel;

import clases.BD;
import clases.DeustoException;
import clases.Venta;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.GridLayout;

public class panelRegistroVentasUsuario extends JPanel {

	private JList<Venta> listaVentas;
	private DefaultListModel<Venta> modeloListaVentas;
	private Connection con;
	public HashMap<String,ArrayList<Venta>> hmVentasTotales = new HashMap<>();
	/**
	 * Create the panel.
	 */
	public panelRegistroVentasUsuario() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		panelSur.add(btnVolver);
		
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("REGISTRO DE TODAS LAS VENTAS");
		panelNorte.add(lblTitulo);
		
		JPanel panelCentroLista = new JPanel();
		add(panelCentroLista, BorderLayout.CENTER);
		panelCentroLista.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentroListaCentro = new JPanel();
		panelCentroLista.add(panelCentroListaCentro, BorderLayout.CENTER);
		
		JPanel panelCentroListaNorte = new JPanel();
		panelCentroLista.add(panelCentroListaNorte, BorderLayout.NORTH);
		
		JLabel lblTituoCentroLista = new JLabel("DEUSTO WEAR");
		panelCentroListaNorte.add(lblTituoCentroLista);
		
		JPanel panelCentroListaEste = new JPanel();
		panelCentroLista.add(panelCentroListaEste, BorderLayout.EAST);
		panelCentroListaEste.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNumVentasTotales = new JLabel("Numero de Ventas :");
		panelCentroListaEste.add(lblNumVentasTotales);
		
		JLabel lblInputNumVentas = new JLabel("");
		panelCentroListaEste.add(lblInputNumVentas);

		
		
	}

	private void cargarVentasTotalesEnLista() {
		try {
			BD.initBD("baseDeDatos.db");
			hmVentasTotales = BD.conseguirVentasTotales(con);
			BD.closeBD(con);
			
		} catch (DeustoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
