package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class EscribirFicheros {
	private FileOutputStream file;
	private ObjectOutputStream output;
	
	//abrir el fichero
	public void abrir(String ruta) throws IOException {
		file = new FileOutputStream(ruta);
		output = new ObjectOutputStream(file);
	}
	
	//cerrar fichero
	public void cerrar() throws IOException {
		if(output != null) {
			output.close();
		}
	}
	
	//escribir en el fichero
	public void escribir(Object objeto) throws IOException {
		if(output != null) {
			output.writeObject(objeto);
		}
	}
}
