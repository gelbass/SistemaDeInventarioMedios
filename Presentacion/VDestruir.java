package Presentacion;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DataType.Medio;
import DataType.Usuarios;
import Logica.ComboBox;
import Logica.FabricaInterfaz;
import LogicaInterfaces.InterInventario;
import LogicaInterfaces.InterUsuario;
import Utilidades.ConvertirDatos;
import Utilidades.Mensajes;
import Utilidades.ValidarDatos;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class VDestruir extends JInternalFrame {
	private JTable tADestruir;
	private JTable tDestruidas;
	private JComboBox<String> cbxTipoMedio = new JComboBox<String>();
	private JButton btnBuscar = new JButton("Buscar");
	private JLabel lbFecha = new JLabel("                   -");
	private ArrayList<String> tipoMedio = new ArrayList<String>();
	private int cantidad = 0;
	private JButton btnLimpiar = new JButton("Limpiar Tabla");
	private JLabel lblCantidad = new JLabel("       -");
	private JLabel lblNewLabel_2 = new JLabel("Medios a destruir:");
	private String fechaActual;
	private LocalDateTime f = LocalDateTime.now();
	private Date fa;
	private int cantDestruidas = 0;
	private String usuarioConectado = Principal.lbUsuario.getText(); 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VDestruir frame = new VDestruir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	DefaultTableModel modelo;
	DefaultTableModel modelodes;
	private final JPanel panelSeleccion = new JPanel();
	private JPanel panel;
	private JPanel panelMedios;
	private GroupLayout gl_panelMedios;
	private final JPanel panelDestruidas = new JPanel();
    
	
	
	public VDestruir() {
		
		cargarTipoMedio();
		componentes();
		componentes2();
		String Columnas[] = {"NUM INVENTARIO", "ETIQUETA", "TIPO"};
        Object datos[][] = {};
        modelo = new DefaultTableModel(datos, Columnas);
        modelodes = new DefaultTableModel(datos, Columnas);
        tADestruir.setModel(modelo);
        tDestruidas.setModel(modelodes);
        cbxTipoMedio.setSelectedIndex(-1);
        fechaActual = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(f);
        lbFecha.setText("       -");
	}
	public void componentes2() {
		setClosable(true);
		
        GroupLayout gl_panelSeleccion = new GroupLayout(panelSeleccion);
        gl_panelSeleccion.setHorizontalGroup(
        	gl_panelSeleccion.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelSeleccion.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(panelMedios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(panelDestruidas, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_panelSeleccion.setVerticalGroup(
        	gl_panelSeleccion.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelSeleccion.createSequentialGroup()
        			.addGroup(gl_panelSeleccion.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(panelDestruidas, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
        				.addComponent(panelMedios, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        			.addContainerGap(23, Short.MAX_VALUE))
        );
        
		panelDestruidas.setBorder(new TitledBorder(null, "Destruidas: "+ cantDestruidas , TitledBorder.LEADING, TitledBorder.TOP, null, null));
        tDestruidas = new JTable();
        tDestruidas.setPreferredScrollableViewportSize(new Dimension(310, 310));
        tDestruidas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        tDestruidas.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		panelDestruidas.add(tDestruidas);
		
        JScrollPane scrollPane_1 = new JScrollPane(tDestruidas);
        GroupLayout gl_panelDestruidas = new GroupLayout(panelDestruidas);
        gl_panelDestruidas.setHorizontalGroup(
        	gl_panelDestruidas.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelDestruidas.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_panelDestruidas.setVerticalGroup(
        	gl_panelDestruidas.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelDestruidas.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        			.addContainerGap())
        );
        
        
		
        
        panelDestruidas.setLayout(gl_panelDestruidas);
        panelSeleccion.setBorder(new TitledBorder(null, "Selecci\u00F3n de medios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelSeleccion.setLayout(gl_panelSeleccion);
        
	}
	
	public void componentes() {
		setTitle("Destrucci\u00F3n de Medios");
		setBounds(100, 100, 977, 453);
		
		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setBorder(new TitledBorder(null, "Busqueda por tipo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelBusqueda, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSeleccion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelBusqueda, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
						.addComponent(panelSeleccion, GroupLayout.PREFERRED_SIZE, 404, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Medios activos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		
		tADestruir = new JTable();
		tADestruir.setPreferredScrollableViewportSize(new Dimension(310, 310));
		tADestruir.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tADestruir.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		panel.add(tADestruir);
		
		JScrollPane scrollPane = new JScrollPane(tADestruir);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(149, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
					.addGap(58)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		
		panelMedios = new JPanel();
		panelMedios.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Medios activos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		
		tADestruir = new JTable();
		tADestruir.setPreferredScrollableViewportSize(new Dimension(310, 310));
		tADestruir.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tADestruir.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		panelMedios.add(tADestruir);
		
		JScrollPane scrollPane1;
		scrollPane1 = new JScrollPane(tADestruir);
		GroupLayout gl_panel1;
		gl_panelMedios = new GroupLayout(panelMedios);
		gl_panelMedios.setHorizontalGroup(gl_panelMedios.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelMedios.createSequentialGroup()
					.addContainerGap(149, Short.MAX_VALUE)
					.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
					.addGap(58)));
		gl_panelMedios.setVerticalGroup(gl_panelMedios.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMedios.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		
		
		
		
		JButton btnBaja = new JButton("Destruir");
		btnBaja.setIconTextGap(25);
		btnBaja.setIcon(new ImageIcon(VDestruir.class.getResource("/img/destruir.png")));
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcion = JOptionPane.showConfirmDialog(rootPane, "Cambio de Status","Destruir",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (opcion == JOptionPane.YES_OPTION) {
					InterInventario operacion = FabricaInterfaz.getInventario();
					InterUsuario log = FabricaInterfaz.getUsuarios();
					ArrayList<Medio> lista = operacion.SeleccionTipo((String)cbxTipoMedio.getSelectedItem(),"ACTIVO");
					Medio m = null;
					Usuarios usuario = new Usuarios();
					usuario.setUser(usuarioConectado);
					usuario.setActividadLog("DESTRUCCION DE MEDIOS");
					try {
						
						fa = ConvertirDatos.StringAFecha(fechaActual);
						for (int i = 0; i < lista.size(); i++) {
							Medio lm = lista.get(i);
							m = new Medio(fa, "DESTRUIDA");
							operacion.destruir(lm.getNumMedio(), m, usuarioConectado);
							log.addActividad(usuario, (new Medio(lm.getNumMedio(), lm.getEtiqueta(), lm.getTipo(),fa, lm.getStatus())));
							cantidad = cantidad + ValidarDatos.actualizados();
						}
						Mensajes.confirmacion("fueron destruidas: "+ cantidad + " medios.");
						buscar();
					} catch (ParseException e) {
						// TODO Bloque catch generado automáticamente
						e.printStackTrace();
					}
					
					
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Tipo:");
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Fecha:");
		
		
		lbFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		

		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panelBusqueda = new GroupLayout(panelBusqueda);
		gl_panelBusqueda.setHorizontalGroup(
			gl_panelBusqueda.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBusqueda.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBusqueda.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelBusqueda.createSequentialGroup()
							.addGroup(gl_panelBusqueda.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
								.addGroup(gl_panelBusqueda.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cbxTipoMedio, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelBusqueda.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lbFecha, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
								.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(gl_panelBusqueda.createSequentialGroup()
							.addGroup(gl_panelBusqueda.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBaja, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
								.addGroup(gl_panelBusqueda.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblCantidad, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
							.addGap(7))))
		);
		btnBuscar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnBuscar.setIconTextGap(30);
		btnBuscar.setIcon(new ImageIcon(VDestruir.class.getResource("/img/buscar.png")));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});
		btnLimpiar.setIconTextGap(25);
		btnLimpiar.setIcon(new ImageIcon(VDestruir.class.getResource("/img/iconoBorrar.png")));
		gl_panelBusqueda.setVerticalGroup(
			gl_panelBusqueda.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelBusqueda.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panelBusqueda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(cbxTipoMedio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelBusqueda.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(lbFecha, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addGroup(gl_panelBusqueda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblCantidad))
					.addGap(18)
					.addComponent(btnBaja, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar();
			}
		});
		panelBusqueda.setLayout(gl_panelBusqueda);
		getContentPane().setLayout(groupLayout);
	}
	
	private void cargarTipoMedio() {
		cbxTipoMedio.removeAllItems();
		cbxTipoMedio.setSelectedIndex(-1);
		tipoMedio = ComboBox.listarTipoMedio();
		for (int i = 0; i < tipoMedio.size(); i++) {
			cbxTipoMedio.addItem(tipoMedio.get(i));
		}

	}
	
	private void limpiar() {

		modelo.setRowCount(0);
		modelodes.setRowCount(0);
		lblCantidad.setText(null);
		lbFecha.setText(null);
		
	}
	
	private void buscar() {
		InterInventario operacion = FabricaInterfaz.getInventario();
		ArrayList<Medio> lista = operacion.SeleccionTipo((String)cbxTipoMedio.getSelectedItem(),"ACTIVO");
		modelo.setRowCount(0);
		for (int i = 0; i < lista.size(); i++) {
			Medio lm = lista.get(i);
			Object[] celda = {lm.getNumMedio(),lm.getEtiqueta(),lm.getTipo()}; 
			modelo.addRow(celda);
		}
		lblCantidad.setText(String.valueOf(modelo.getRowCount()));
		ArrayList<Medio> listaDes = operacion.SeleccionTipo((String)cbxTipoMedio.getSelectedItem(),"DESTRUIDA");
		modelodes.setRowCount(0);
		for (int j = 0; j < listaDes.size(); j++) {
			Medio lm = listaDes.get(j);
			Object[] celda = {lm.getNumMedio(),lm.getEtiqueta(),lm.getTipo()}; 
			modelodes.addRow(celda);
		}
		cantDestruidas = modelodes.getRowCount();
		panelDestruidas.setBorder(new TitledBorder(null, "Destruidas: "+ cantDestruidas , TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}
}
