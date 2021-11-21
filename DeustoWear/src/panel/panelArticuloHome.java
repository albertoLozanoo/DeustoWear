package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import clases.Articulo;
import clases.BD;
import clases.Usuario;
import clases.Venta;

import javax.swing.JButton;

public class panelArticuloHome extends JPanel {
	
	Usuario u;

	/**
	 * Create the panel.
	 */
	public panelArticuloHome(Articulo a) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		
		JLabel lblImagenLoop = new JLabel("");
		lblImagenLoop.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblImagenLoop);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(255, 255, 255));
		add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new MigLayout("", "[89.00][89.00,grow][]", "[55.00][48.00][47.00]"));
		
		JLabel lblNombreLoop = new JLabel("");
		lblNombreLoop.setForeground(new Color(255, 255, 255));
		lblNombreLoop.setBackground(new Color(255, 255, 255));
		panelSur.add(lblNombreLoop, "cell 0 0");
		
		JButton btnFavoritos = new JButton("Corazon");
		panelSur.add(btnFavoritos, "cell 2 0");
		
		JLabel lblPrecio = new JLabel("Precio : ");
		panelSur.add(lblPrecio, "cell 0 1,alignx center");
		
		JLabel lblPrecioLoop = new JLabel("");
		panelSur.add(lblPrecioLoop, "cell 1 1");
		
		JButton btnComprar = new JButton("Comprar");
		panelSur.add(btnComprar, "cell 2 1");
		
		JLabel lblColor = new JLabel("Color : ");
		lblColor.setHorizontalAlignment(SwingConstants.LEFT);
		panelSur.add(lblColor, "cell 0 2,alignx center");
		
		JComboBox comboBoxColor = new JComboBox();
		panelSur.add(comboBoxColor, "cell 1 2,growx");
		
		/*EVENTOS*/
		
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				u.ventaActual.addArticuloAventaActual(a);
			}
		});
		btnFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				u.addFavorito(a);
			}
		});
	}
	
	

}
