package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class LeerFichero {
	private FileInputStream file;
	private ObjectInputStream input;
	
	//abrir el fichero
	public void abrir(String ruta) throws IOException {
		file = new FileInputStream(ruta);
		input = new ObjectInputStream(file);
	}
	
	//cerrar
	public void cerrar() throws IOException {
		if(input != null) {
			input.close();
		}
	}
	
	public Object leer() {
		Object objeto = null;
		if(input != null) {
			try {
				objeto = (Object) input.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
		}
		return objeto;
	}
}
