package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import ventanas.VentanaInicio;

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
import clases.Camiseta;
import clases.GestionFicheros;
import clases.Pantalon;
import clases.Sudadera;
import clases.Usuario;
import clases.Venta;
import enumeration.Colores;
import enumeration.Talla;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.BevelBorder;

public class panelArticuloHome extends JPanel {

	
	/**
	 * Create the panel.corazon favorito
	 */
	public panelArticuloHome(Articulo a) {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		setLayout(new BorderLayout(0, 0));
		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(new Color(153, 204, 255));
		add(panelCentro, BorderLayout.CENTER);
		ImageIcon im = new ImageIcon(a.getImagen());
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(300,300,ImageView.CENTER));
		
		JLabel lblImagenLoop = new JLabel();
		lblImagenLoop.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenLoop.setIcon(imagenConDimensiones);
		lblImagenLoop.setPreferredSize(new DimensionUIResource(400, 400));
		panelCentro.add(lblImagenLoop);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(153, 204, 255));
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
		
		JLabel lblPrecio = new JLabel("Precio : " + a.getPrecio()+ "??? ");
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
		
		
		
		JComboBox cbColor = new JComboBox();
		panelSur.add(cbColor, "cell 0 3,growx");
		
		ArrayList<Colores> colores = new ArrayList<>();
		colores.add(Colores.AZUL);
		colores.add(Colores.BLANCO);
		colores.add(Colores.DEUSTO);
		colores.add(Colores.NARANJA);
		colores.add(Colores.NEGRO);
		
		//String sel = "Seleccione un color...";
		//cbColor.addItem(sel);
		for(Colores c: colores) {
			cbColor.addItem(c);
		}
	
		
		
		JComboBox cbTalla = new JComboBox();
		panelSur.add(cbTalla, "cell 0 5,growx");
		
		ArrayList<Talla> tallas = new ArrayList<>();
		tallas.add(Talla.XS);
		tallas.add(Talla.S);
		tallas.add(Talla.M);
		tallas.add(Talla.L);
		tallas.add(Talla.XL);
		tallas.add(Talla.XXL);
		
		//String sel2 = "Seleccione una talla...";
		//cbTalla.addItem(sel2);
		for(Talla t:tallas) {
			cbTalla.addItem(t);
		}
		
		
		
		/*EVENTOS*/
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(a instanceof Camiseta){
					Camiseta c = new Camiseta(a.getID(),a.getName(), ((Talla)cbTalla.getSelectedItem()).toString(), a.getPrecio(),((Colores)cbColor.getSelectedItem()).toString(), a.getSexo(), a.getImagen());
					VentanaInicio.u.addCarrito(c);
				}else if(a instanceof Pantalon) {
					Pantalon p = new Pantalon(a.getID(), a.getName(), ((Talla)cbTalla.getSelectedItem()).toString(), a.getPrecio(),((Colores)cbColor.getSelectedItem()).toString(),a.getSexo(), a.getImagen(),((Pantalon) a).getTipoPantalon());
					VentanaInicio.u.addCarrito(p);
				}else if(a instanceof Sudadera) {
					Sudadera s = new Sudadera(a.getID(), a.getName(), ((Talla)cbTalla.getSelectedItem()).toString(), a.getPrecio(), ((Colores)cbColor.getSelectedItem()).toString(), a.getSexo(), a.getImagen(),((Sudadera) a).getCapucha());
					VentanaInicio.u.addCarrito(s);
				}
				JOptionPane.showMessageDialog(null, "Art???culo a???adido a compras ","DONE", JOptionPane.INFORMATION_MESSAGE);
				for(Articulo a : VentanaInicio.u.getCarrito()) {
					System.out.println(a);
				}
				
			}
		});
		btnFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					if(a instanceof Camiseta){
						System.out.println("Vamos a a??adir una camiseta");
						Camiseta c = new Camiseta(a.getID(),a.getName(), ((Talla)cbTalla.getSelectedItem()).toString(), a.getPrecio(),((Colores)cbColor.getSelectedItem()).toString(), a.getSexo(), a.getImagen());
						VentanaInicio.u.addFavorito(new Camiseta(a.getID(),a.getName(), ((Talla)cbTalla.getSelectedItem()).toString(), a.getPrecio(),((Colores)cbColor.getSelectedItem()).toString(), a.getSexo(), a.getImagen()));
					}else if(a instanceof Pantalon) {
						Pantalon p = new Pantalon(a.getID(), a.getName(), ((Talla)cbTalla.getSelectedItem()).toString(), a.getPrecio(),((Colores)cbColor.getSelectedItem()).toString(),a.getSexo(), a.getImagen(),((Pantalon) a).getTipoPantalon());
						VentanaInicio.u.addFavorito(p);
					}else if(a instanceof Sudadera) {
						Sudadera s = new Sudadera(a.getID(), a.getName(), ((Talla)cbTalla.getSelectedItem()).toString(), a.getPrecio(), ((Colores)cbColor.getSelectedItem()).toString(), a.getSexo(), a.getImagen(),((Sudadera) a).getCapucha());
						VentanaInicio.u.addFavorito(s);
					}else if(cbTalla.getSelectedItem().equals("Seleccione una talla...")|cbTalla.getSelectedItem().equals("Seleccione un color...")){
						JOptionPane.showMessageDialog(null, "Seleccione un color y una talla","??????ERROR!!", JOptionPane.ERROR_MESSAGE);
					}
					JOptionPane.showMessageDialog(null, "Art???culo a???adido a favoritos ","DONE", JOptionPane.INFORMATION_MESSAGE);
					/*for(Articulo a : VentanaInicio.u.getFavoritos()) {
						System.out.println(a);
					}*/
					//VentanaInicio.u.guardarFavoritosEnFichero();
					GestionFicheros.guardarFavoritos(VentanaInicio.u.getNick(), VentanaInicio.u.getFavoritos());
					
				}
			
		});
	}
	
	

}
