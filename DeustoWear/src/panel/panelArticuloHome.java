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
import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.html.ImageView;

import clases.Articulo;
import clases.BD;
import clases.Usuario;
import clases.Venta;

import javax.swing.ImageIcon;
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
		
		ImageIcon im = new ImageIcon(a.getImagen());
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(200,200,ImageView.CENTER));
		
		JLabel lblImagenLoop = new JLabel();
		lblImagenLoop.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenLoop.setIcon(imagenConDimensiones);
		lblImagenLoop.setPreferredSize(new DimensionUIResource(200, 200));
		panelCentro.add(lblImagenLoop);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(255, 255, 255));
		add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new MigLayout("", "[140.00][grow,center]", "[55.00][48.00][47.00]"));
		
		JLabel lblNombreLoop = new JLabel("Nombre : "+ a.getName());
		lblNombreLoop.setForeground(new Color(255, 255, 255));
		lblNombreLoop.setBackground(new Color(255, 255, 255));
		panelSur.add(lblNombreLoop, "cell 0 0");
		
		JButton btnFavoritos = new JButton("Corazon");
		panelSur.add(btnFavoritos, "cell 1 0");
		
		JLabel lblPrecio = new JLabel("Precio : " + a.getPrecio());
		panelSur.add(lblPrecio, "cell 0 1,alignx center");
		
		JButton btnComprar = new JButton("Comprar");
		panelSur.add(btnComprar, "cell 1 1");
		
		JLabel lblColor = new JLabel("Color : " + a.getColor());
		lblColor.setHorizontalAlignment(SwingConstants.LEFT);
		panelSur.add(lblColor, "cell 0 2,alignx center");
		
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
