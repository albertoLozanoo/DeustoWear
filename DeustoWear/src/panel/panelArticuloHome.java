package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import java.awt.Font;

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
		panelSur.setBackground(new Color(51, 153, 255));
		add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new MigLayout("", "[196.00][grow,center]", "[55.00][48.00][47.00]"));
		
		JLabel lblNombreLoop = new JLabel(a.getName());
		lblNombreLoop.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 18));
		lblNombreLoop.setForeground(new Color(255, 255, 255));
		lblNombreLoop.setBackground(new Color(255, 255, 255));
		panelSur.add(lblNombreLoop, "cell 0 0,aligny center");
		
		JButton btnFavoritos = new JButton("Corazon");
		btnFavoritos.setBackground(new Color(204, 51, 0));
		btnFavoritos.setForeground(new Color(255, 255, 255));
		btnFavoritos.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		panelSur.add(btnFavoritos, "cell 1 0");
		
		JLabel lblPrecio = new JLabel("Precio : " + a.getPrecio());
		lblPrecio.setForeground(new Color(255, 255, 255));
		lblPrecio.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		panelSur.add(lblPrecio, "cell 0 1,alignx left,aligny bottom");
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnComprar.setForeground(new Color(255, 255, 255));
		btnComprar.setBackground(new Color(153, 255, 0));
		panelSur.add(btnComprar, "cell 1 1");
		
		JLabel lblColor = new JLabel("Color : "+a.getColor());
		lblColor.setForeground(new Color(255, 255, 255));
		lblColor.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 15));
		lblColor.setHorizontalAlignment(SwingConstants.LEFT);
		panelSur.add(lblColor, "cell 0 2,alignx left,aligny top");
		
		/*EVENTOS*/
		
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				u.addCarrito(a);
				System.out.println("Articulo anyadido a carrito");
				for(Articulo a : u.carrito) {
					System.out.println(a);
				}
			}
		});
		btnFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				u.addFavorito(a);
				System.out.println("Articulo anyadido a favoritos");
				for(Articulo a : u.favoritos) {
					System.out.println(a);
				}
				/*JOptionPane.showMessageDialog(null, "Articulo anyadido a favoritos","Favorito", JOptionPane.INFORMATION_MESSAGE);
				u.guardarFavoritosEnFichero();
				System.out.println("Guardando los favoritos en fichero...");*/
			}
		});
	}
	
	

}
