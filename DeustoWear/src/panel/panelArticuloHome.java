package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
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
import enumeration.Colores;
import enumeration.Talla;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class panelArticuloHome extends JPanel {

	Usuario u;
	
	/**
	 * Create the panel.corazon favorito
	 */
	public panelArticuloHome(Articulo a) {
		
		setLayout(new BorderLayout(0, 0));
		JPanel panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		ImageIcon im = new ImageIcon(a.getImagen());
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(300,300,ImageView.CENTER));
		
		JLabel lblImagenLoop = new JLabel();
		lblImagenLoop.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenLoop.setIcon(imagenConDimensiones);
		lblImagenLoop.setPreferredSize(new DimensionUIResource(400, 400));
		panelCentro.add(lblImagenLoop);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(51, 153, 255));
		add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new MigLayout("", "[196.00,grow][grow,center]", "[55.00][48.00][45.00][][47.00][]"));
		
		JLabel lblNombreLoop = new JLabel(a.getName());
		lblNombreLoop.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 26));
		lblNombreLoop.setForeground(new Color(255, 255, 255));
		lblNombreLoop.setBackground(new Color(255, 255, 255));
		panelSur.add(lblNombreLoop, "cell 0 0,aligny center");
		
		
		ImageIcon im2 = new ImageIcon("imagenes/favorito.png");
		ImageIcon imagenConDimensiones2 = new ImageIcon(im2.getImage().getScaledInstance(30,30,ImageView.CENTER));
		JButton btnFavoritos = new JButton(imagenConDimensiones2);
		btnFavoritos.setBackground(new Color(204, 51, 0));
		btnFavoritos.setForeground(new Color(255, 255, 255));
		btnFavoritos.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		panelSur.add(btnFavoritos, "cell 1 0");
		
		JLabel lblPrecio = new JLabel("Precio : " + a.getPrecio()+ "€ ");
		lblPrecio.setForeground(new Color(255, 255, 255));
		lblPrecio.setFont(new Font("Microsoft YaHei", Font.PLAIN, 19));
		panelSur.add(lblPrecio, "cell 0 1,alignx left,aligny bottom");
		
		ImageIcon im3 = new ImageIcon("imagenes/carrito.png");
		ImageIcon imagenConDimensiones3 = new ImageIcon(im3.getImage().getScaledInstance(30,30,ImageView.CENTER));
		JButton btnComprar = new JButton(imagenConDimensiones3);
		btnComprar.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnComprar.setForeground(new Color(51, 51, 153));
		btnComprar.setBackground(new Color(153, 255, 0));
		panelSur.add(btnComprar, "cell 1 1");
		
		JLabel lblColor = new JLabel("Color : "+a.getColor());
		lblColor.setForeground(new Color(255, 255, 255));
		lblColor.setFont(new Font("Microsoft YaHei", Font.PLAIN, 19));
		lblColor.setHorizontalAlignment(SwingConstants.LEFT);
		panelSur.add(lblColor, "flowx,cell 0 2,alignx left,aligny bottom");
		
		
		
		JLabel lblTalla = new JLabel("Talla (ES: "+ a.getTalla()+ ")");
		lblTalla.setForeground(new Color(255, 255, 255));
		lblTalla.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 19));
		panelSur.add(lblTalla, "cell 0 4,alignx left,aligny bottom");
		
		JLabel lblSexo = new JLabel(a.getSexo()+ "  ");
		lblSexo.setFont(new Font("Microsoft YaHei", Font.ITALIC, 15));
		lblSexo.setForeground(new Color(255, 255, 255));
		panelSur.add(lblSexo, "cell 1 4,alignx center,aligny bottom");
		
		/*ComboBox Color*/
		JComboBox cbColor = new JComboBox();
		panelSur.add(cbColor, "cell 0 3,growx");
		
		ArrayList<Colores> colores = new ArrayList<>();
		colores.add(Colores.AZUL);
		colores.add(Colores.BLANCO);
		colores.add(Colores.DEUSTO);
		colores.add(Colores.NARANJA);
		colores.add(Colores.NEGRO);
		
		String sel = "Seleccione un color...";
		cbColor.addItem(sel);
		for(Colores c: colores) {
			cbColor.addItem(c);
		}
	
		
		/*ComboBox Talla*/
		JComboBox cbTalla = new JComboBox();
		panelSur.add(cbTalla, "cell 0 5,growx");
		
		ArrayList<Talla> tallas = new ArrayList<>();
		tallas.add(Talla.XS);
		tallas.add(Talla.S);
		tallas.add(Talla.M);
		tallas.add(Talla.L);
		tallas.add(Talla.XL);
		tallas.add(Talla.XXL);
		
		String sel2 = "Seleccione una talla...";
		cbTalla.addItem(sel2);
		for(Talla t:tallas) {
			cbTalla.addItem(t);
		}
		
		
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
