package Clases;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;

public class Nota implements Comparable<Nota>, Serializable{

	private Calendar fecha;
	private String texto;
	private String categoria;
	private static String[] categorias = {"Urgente", "Importante", "Normal"};
	
	public Nota(Calendar fecha, String texto, String categoria) {
		this.fecha = fecha;
		this.texto = texto;
		this.categoria = comprobarCategoria(categoria);
	};
	
	private String comprobarCategoria(String categoria) {
		String aux = categorias[0];
		if(categoria.equals(categorias[1])) {
			aux = categorias[1];
		}else if(categoria.equals(categorias[2])) {
			aux = categorias[2];
		}
		return aux;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = comprobarCategoria(categoria);
	}

	public static String[] getListaCategorias() {
		return categorias;
	}

	@Override
	public String toString() {
		
		String cat = categoria.substring(0,3);
		DateLabelFormatter formatoFecha = new DateLabelFormatter();
		String fechaString = null;
		try {
			fechaString = formatoFecha.valueToString(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cat + "   " + fechaString + "  " + texto;
	}

	@Override
	public int compareTo(Nota no) {
		return this.fecha.compareTo(no.getFecha());
	}
}
