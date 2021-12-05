package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clases.DeustoException;
import clases.Usuario;

public class VentanaEsperaTransaccion extends JFrame{
		private JPanel pCentral;
		private JFrame ventanaActual, ventanaAnterior;
		
		
		
		public VentanaEsperaTransaccion(JFrame va, Usuario u) {
			ventanaAnterior = va;
			ventanaActual = this;
			setBounds(200, 200, 500, 100);
			setTitle("Ya casi esta unos segundos mas " + u.getNick() + "...");
			pCentral = new JPanel();
			getContentPane().add(pCentral,BorderLayout.CENTER);
			
			Runnable r = new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for(int i=0;i<5;i++) {
						JLabel lbl = new JLabel(".");
						lbl.setForeground(Color.WHITE);
						lbl.setFont(new Font(Font.SERIF,Font.BOLD, 30));
						pCentral.add(lbl);
						pCentral.setBackground(new Color(140,86,138));
						getContentPane().validate();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					ventanaActual.dispose();
					try {
						new VentanaHome(va, u);
					} catch (DeustoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*String [] opciones = {"SI", "NO"};
					int opc = JOptionPane.showOptionDialog(null, "Quieres seguir comprando " + u.getNick() + "?", "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);
					switch(opc) {
					case 0: try {
							new VentanaHome(va, u);
						} catch (DeustoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							u.eliminarCarrito(u.carrito);
							break;
					case 1: ventanaActual.dispose();
							u.eliminarCarrito(u.getCarrito());
							System.exit(0);
							break;
					}*/
				}
			};
			Thread t = new Thread(r);
			t.start();
			setVisible(true);
		}

}
