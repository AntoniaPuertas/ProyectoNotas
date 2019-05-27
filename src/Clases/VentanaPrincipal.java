package Clases;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;


public class VentanaPrincipal extends JFrame{
	
	private JList<Nota> listNota;
	private DefaultListModel<Nota> model;
	private JPanel contentPane;
	private JDatePickerImpl datePicker;
	private ArrayList<Nota> listaNotas;

	
	public VentanaPrincipal(ArrayList<Nota> listaNotas) {
		//titulo de la ventana
		super("Proyecto Notas");
		
		//tamaño
		setBounds(100,100,750,400);
		
		//acción que se realiza al cerrar la ventana: cerrar la aplicación
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.listaNotas = listaNotas;
		//crea un contenedor
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0,0));
			
		JPanel panelDatos = getPanelDatos();
		contentPane.add(panelDatos, BorderLayout.NORTH);
		contentPane.add(getPanelNotas(), BorderLayout.CENTER);
		contentPane.add(getPanelBotones(), BorderLayout.SOUTH);
		
		//accedo a los componentes del panel
		JDatePickerImpl datePicker = (JDatePickerImpl) panelDatos.getComponent(0);
    	JTextField cajaTexto =(JTextField) panelDatos.getComponent(1);
    	JComboBox combo = (JComboBox) panelDatos.getComponent(2);
    	JButton botonNueva = (JButton) panelDatos.getComponent(3);
		
    	cajaTexto.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(cajaTexto.getText().contains("Escribe aquí tu nota...")) {
					cajaTexto.setText("");
				}
				
			}
		});
    	
    	botonNueva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String texto = cajaTexto.getText().trim();
				String categoriaSeleccionada = (String) combo.getSelectedItem();
				Date fecha = (Date) datePicker.getModel().getValue();
				Calendar fechaCalendario = Calendar.getInstance();
				fechaCalendario.setTime(fecha);
				

				if(texto.length() > 0) {
					Nota notaAux = new Nota(fechaCalendario, texto, categoriaSeleccionada);
					model.addElement(notaAux);
					listaNotas.add(notaAux);
					cajaTexto.setText(null);
				}else {
					JOptionPane.showMessageDialog(null, "Debes escribir alguna nota", "UPSS!!", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		
	}
	
	private JPanel getPanelDatos() {
		//crea el panel para la primera fila
				JPanel panelDatos = new JPanel();
				panelDatos.setLayout(new FlowLayout());
				
				
				panelDatos.add(getCalendario());
				
				//caja de texto para el texto de la nota
				JTextField jTxtTextoNota = new JTextField(30);
				panelDatos.add(jTxtTextoNota);
				
				jTxtTextoNota.setText("Escribe aquí tu nota...");
				//combo desplegable para el tipo de nota
				JComboBox combo = new JComboBox();
				String[] listaCategorias = Nota.getListaCategorias();
				
				for (String categoria : listaCategorias) {
					combo.addItem(categoria);
				}
				panelDatos.add(combo);
				
				//boton para crear nota
				JButton btnCrear = new JButton("Nueva");
				panelDatos.add(btnCrear);
				
				return panelDatos;
	}
	
	private JDatePickerImpl getCalendario() {
		
	      Properties p = new Properties();
	      p.put("text.today", "Hoy");
	      p.put("text.month", "Mes");
	      p.put("text.year", "Año");
	      
	      UtilDateModel model=new UtilDateModel();
	      model.setValue(new Date());
	      JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
	      datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	      return datePicker;
	}
	
	private JPanel getPanelNotas() {
		//crea el panel para la segunda fila que contiene el listado de notas
				JPanel panelNotas = new JPanel(new BorderLayout());
				panelNotas.setBorder(new EmptyBorder(5,5,5,5));
				panelNotas.add(new JScrollPane(listNota = createListNotas()));

				
				return panelNotas;
	}
	
	private JPanel getPanelBotones() {
		//crea el panel para la tercera fila que contiene dos botones
				JPanel panelBotones = new JPanel();
				panelBotones.setLayout(new FlowLayout());
				
				//botones para eliminar y modificar nota
				JButton btnEliminar = new JButton("Eliminar");
				JButton btnModificar = new JButton("Modificar");
				
				//listener para el evento de pulsar btnEliminar
				btnEliminar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Nota nota = listNota.getSelectedValue();
						int selectedIndex = listNota.getSelectedIndex();
						int dialogButton = JOptionPane.YES_NO_OPTION;
						if (selectedIndex != -1) {
							int dialogResult = JOptionPane.showConfirmDialog (null, nota.toString(), "¿Seguro que quieres eliminar esta nota?", dialogButton);
							if(dialogResult == 0) {
								model.remove(selectedIndex);
								//TODO: eliminar la nota del arraylist
								listaNotas.remove(nota);
							} 
						}else {
							JOptionPane.showMessageDialog(null, "Debes seleccionar alguna nota", "UPSS!!", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				
				//listener para el evento de pulsar btnModificar
				btnModificar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						int selectedIndex = listNota.getSelectedIndex();
						if (selectedIndex != -1) {
							modificaNota();
						}else {
							JOptionPane.showMessageDialog(null, "Debes seleccionar alguna nota", "UPSS!!", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				
				panelBotones.add(btnModificar);
				panelBotones.add(btnEliminar);
				
				return panelBotones;
	}
	
	
    private JList<Nota> createListNotas() {
        // create List model
        model = new DefaultListModel<>();
        // add item to model
        for (Nota nota : this.listaNotas) {
			model.addElement(nota);
		}

        // create JList with model
        listNota = new JList<Nota>(model);
        listNota.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return listNota;
    }
    
    private void modificaNota() {
    	JFrame frame = new JFrame();
    	JPanel panel = getPanelDatos();
    	
    	frame.setTitle("Modifica nota");
    	
    	//tamaño
    	frame.setBounds(100,400,550,200);
    			
    	//acción que se realiza al cerrar la ventana
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    	//accedo a los componentes del panel
    	JDatePickerImpl datePicker = (JDatePickerImpl) panel.getComponent(0);
    	JTextField cajaTexto =(JTextField) panel.getComponent(1);
    	JComboBox combo = (JComboBox) panel.getComponent(2);
    	JButton botonModificar = (JButton) panel.getComponent(3);
    	
    	//accedo a la nota seleccionada
    	Nota nota = listNota.getSelectedValue();
		
    	//relleno los componentes con los valores de la nota
    	datePicker.getModel().setDate(nota.getFecha().get(Calendar.YEAR), nota.getFecha().get(Calendar.MONTH), nota.getFecha().get(Calendar.DAY_OF_MONTH));
    	cajaTexto.setText(nota.getTexto());
    	
    	combo.setSelectedItem(nota.getCategoria());
    	botonModificar.setText("Modificar");

    	botonModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Date fecha = (Date) datePicker.getModel().getValue();
				Calendar fechaCalendario = Calendar.getInstance();
				fechaCalendario.setTime(fecha);
				nota.setFecha(fechaCalendario);
				nota.setTexto(cajaTexto.getText());
				nota.setCategoria((String)combo.getSelectedItem());
			
				listNota.updateUI();
				frame.dispose();
			}
		});
		
		frame.add(panel);
		frame.setVisible(true);
    }

}
