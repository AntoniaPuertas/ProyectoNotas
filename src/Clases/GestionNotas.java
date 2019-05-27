package Clases;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import io.EscribirFicheros;
import io.LeerFichero;

public class GestionNotas {
	
	static ArrayList<Nota> listaNotas;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		String rutaDatos = "datos.dt";
		listaNotas = new ArrayList<>(); 
		Nota nota;
		
		LeerFichero entradaDatos = new LeerFichero();
		EscribirFicheros salidaDatos = new EscribirFicheros();
		
		try {
			entradaDatos.abrir(rutaDatos);
			do {
				nota = (Nota) entradaDatos.leer();
				if(nota != null) {
					listaNotas.add(nota);
				}
			}while(nota != null);
			
			entradaDatos.cerrar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		VentanaPrincipal ventanaP = new VentanaPrincipal(listaNotas);
		ventanaP.setVisible(true);
		
		ventanaP.addWindowListener(new WindowListener() {

			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				try {
					salidaDatos.abrir(rutaDatos);
					for (Nota nota : listaNotas) {
						salidaDatos.escribir(nota);
					}
					salidaDatos.cerrar();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
	}
	
	

}
