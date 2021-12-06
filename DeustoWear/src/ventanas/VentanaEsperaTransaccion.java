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
			setTitle("Ya casi esta unos segundos mas " + u.getNick() + " ...");
			pCentral = new JPanel();
			getContentPane().add(pCentral,BorderLayout.CENTER);
			
			Runnable r = new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for(int i=0;i<5;i++) {
						JLabel lbl = new JLabel(".");
						lbl.setForeground(Color.BLUE);
						lbl.setFont(new Font(Font.SERIF,Font.BOLD, 30));
						pCentral.add(lbl);
						pCentral.setBackground(new Color(255, 153, 51));
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
				}
			};
			Thread t = new Thread(r);
			t.start();
			setVisible(true);
		}

}
