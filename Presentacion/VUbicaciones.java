package Presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DataType.Inventario;
import DataType.Medio;
import Logica.FabricaInterfaz;
import LogicaInterfaces.InterInventario;
import Utilidades.Mensajes;
import Utilidades.ValidarDatos;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;
import java.awt.event.ActionEvent;

public class VUbicaciones extends JInternalFrame {
	private JTable tCintoteca;
	private JTable tCPD1;
	private JTable tCPD2;
	private JTable tCompuSeguridad;
	private JTable tCont417;
	private JTable tCimo;
	private JTable tCont418;
	private JPanel panel_2 = new JPanel();
	private JPanel panel_CINTOTECA = new JPanel();
	private JPanel panel_3 = new JPanel();
	private JPanel panel_7 = new JPanel();
	private JPanel panel_8 = new JPanel();
	private JPanel panel_4 = new JPanel();
	private JPanel panel_6 = new JPanel();
	private JPanel panel_5 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VUbicaciones frame = new VUbicaciones();
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
	
	DefaultTableModel modeloCintoteca;
	DefaultTableModel modeloCPD1;
	DefaultTableModel modeloCPD2;
	DefaultTableModel modeloCompuSeguridad;
	DefaultTableModel modeloCont417;
	DefaultTableModel modeloCont418;
	DefaultTableModel modeloCimo;
	@Override
	public void doDefaultCloseAction() {
		// TODO Apéndice de método generado automáticamente
		super.doDefaultCloseAction();
	}
	
	public VUbicaciones() {
		setClosable(true);
		setFrameIcon(new ImageIcon(VUbicaciones.class.getResource("/img/SAO_MAINICON.png")));
		setTitle("Ubicaci\u00F3n de Medios");
		componentes();
		String Columnas[] = {"NUM INVENTARIO", "ETIQUETA"};
        Object datos[][] = {};
        modeloCintoteca = new DefaultTableModel(datos, Columnas);
        modeloCPD1 = new DefaultTableModel(datos, Columnas);
        modeloCPD2 = new DefaultTableModel(datos, Columnas);
        modeloCompuSeguridad = new DefaultTableModel(datos, Columnas);
        modeloCont417 = new DefaultTableModel(datos, Columnas);
        modeloCont418 = new DefaultTableModel(datos, Columnas);
        modeloCimo = new DefaultTableModel(datos, Columnas);
        tCintoteca.setModel(modeloCintoteca);
        tCPD1.setModel(modeloCPD1);
        tCPD2.setModel(modeloCPD2);
        tCompuSeguridad.setModel(modeloCompuSeguridad);
        tCont417.setModel(modeloCont417);
        tCont418.setModel(modeloCont418);
        tCimo.setModel(modeloCimo);
        cargarCintoteca();
        cargarCimo();
        cargarCompuSeguridad();
        cargarCont417();
        cargarCont418();
        cargarCPD1();
        cargarCPD2();
        panel_5.setBorder(new TitledBorder(null, "CONTENEDOR 417 - Cantidad: " + String.valueOf(modeloCont417.getRowCount()), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBorder(new TitledBorder(null, "CIMO - Cantidad: " + String.valueOf(modeloCimo.getRowCount()), TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBorder(new TitledBorder(null, "ROBOT CPD1 - Cantidad: " + String.valueOf(modeloCPD1.getRowCount()), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBorder(new TitledBorder(null, "ROBOT CPD2 - Cantidad: " + String.valueOf(modeloCPD2.getRowCount()), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBorder(new TitledBorder(null, "COMPUSEGURIDAD - Cantidad: " + String.valueOf(modeloCompuSeguridad.getRowCount()), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBorder(new TitledBorder(null, "CONTENEDOR 418 - Cantidad: " + String.valueOf(modeloCont418.getRowCount()), TitledBorder.LEADING, TitledBorder.TOP, null, null)); 
		panel_CINTOTECA.setBorder(new TitledBorder(null, "CINTOTECA - Cantidad: " + String.valueOf(modeloCintoteca.getRowCount()), TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}


	

		
	
	public void componentes() {
		setBounds(100, 100, 917, 650);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		tCont418 = new JTable();
		tCont418.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tCont418.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		panel_6.add(tCont418);

		JScrollPane spCont418 = new JScrollPane(tCont418);
		spCont418.setRequestFocusEnabled(false);
		spCont418.setPreferredSize(new Dimension(225, 155));
		spCont418.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spCont418.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		spCont418.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		
				
		tCimo = new JTable();
		//scrollPane_5.setColumnHeaderView(tCimo);
		panel_8.add(tCimo);
		
		JScrollPane spCimo = new JScrollPane(tCimo);
		spCimo.setRequestFocusEnabled(false);
		spCimo.setPreferredSize(new Dimension(225, 88));
		spCimo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spCimo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		spCimo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_8.add(spCimo);
				
		tCont417 = new JTable();
		//pCont417.setColumnHeaderView(tCont417);
		panel_5.add(tCont417);
		
		JScrollPane spCont417 = new JScrollPane(tCont417);
		spCont417.setRequestFocusEnabled(false);
		spCont417.setPreferredSize(new Dimension(225, 170));
		spCont417.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spCont417.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		spCont417.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(1)
					.addComponent(spCont417, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(5)
					.addComponent(spCont417, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panel_5.setLayout(gl_panel_5);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_CINTOTECA, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_7, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 601, Short.MAX_VALUE)
						.addComponent(panel_CINTOTECA, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
								.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(8))
		);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(1)
					.addComponent(spCont418, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(5)
					.addComponent(spCont418, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panel_6.setLayout(gl_panel_6);
		
		
		tCompuSeguridad = new JTable();
		//spCompuseguridad.setColumnHeaderView(tCompuSeguridad);
		panel_4.add(tCompuSeguridad);
		
		JScrollPane spCompuseguridad = new JScrollPane(tCompuSeguridad);
		spCompuseguridad.setRequestFocusEnabled(false);
		spCompuseguridad.setPreferredSize(new Dimension(225, 235));
		spCompuseguridad.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spCompuseguridad.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		spCompuseguridad.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(1)
					.addComponent(spCompuseguridad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(5)
					.addComponent(spCompuseguridad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panel_4.setLayout(gl_panel_4);
		
		
		tCPD2 = new JTable();
		//spCpd2.setColumnHeaderView(tCPD2);
		panel_3.add(tCPD2);
		

		JScrollPane spCpd2 = new JScrollPane(tCPD2);
		spCpd2.setRequestFocusEnabled(false);
		spCpd2.setPreferredSize(new Dimension(225, 170));
		spCpd2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spCpd2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(1)
					.addComponent(spCpd2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(5)
					.addComponent(spCpd2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panel_3.setLayout(gl_panel_3);
		
		
		tCPD1 = new JTable();
		
		panel_2.add(tCPD1);
		
		JScrollPane spCpd1 = new JScrollPane(tCPD1);
		spCpd1.setPreferredSize(new Dimension(225, 170));
		spCpd1.setRequestFocusEnabled(false);
		spCpd1.setPreferredSize(new Dimension(225, 170));
		spCpd1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spCpd1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		spCpd1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_2.add(spCpd1);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(spCpd1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(192, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(spCpd1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		
		
		tCintoteca = new JTable();
		tCintoteca.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tCintoteca.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		
		panel_CINTOTECA.add(tCintoteca);
		
		JScrollPane spCintoteca = new JScrollPane(tCintoteca);
		spCintoteca.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spCintoteca.setBackground(Color.BLACK);
		spCintoteca.setRequestFocusEnabled(false);
		spCintoteca.setPreferredSize(new Dimension(225, 570));
		spCintoteca.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_panel_CINTOTECA = new GroupLayout(panel_CINTOTECA);
		gl_panel_CINTOTECA.setHorizontalGroup(
			gl_panel_CINTOTECA.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_CINTOTECA.createSequentialGroup()
					.addGap(1)
					.addComponent(spCintoteca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_CINTOTECA.setVerticalGroup(
			gl_panel_CINTOTECA.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_CINTOTECA.createSequentialGroup()
					.addGap(5)
					.addComponent(spCintoteca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panel_CINTOTECA.setLayout(gl_panel_CINTOTECA);
		
		JButton btnNuevoMovimiento = new JButton("Nuevo Movimiento");
		btnNuevoMovimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VMovimiento movimiento = new VMovimiento();	
				Principal.desktopPane.add(movimiento);
				movimiento.toFront();
				movimiento.setVisible(true);
				movimiento.show();
				 
				
			}
		});
		btnNuevoMovimiento.setIcon(new ImageIcon(VUbicaciones.class.getResource("/img/nuevoMovimiento.png")));
		btnNuevoMovimiento.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNuevoMovimiento.setIconTextGap(2);
		btnNuevoMovimiento.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNuevoMovimiento.setBackground(Color.WHITE);
		
		JButton btnActualizar = new JButton("Actualizar Ubicaci\u00F3n");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarListados();
			}
		});
		btnActualizar.setIcon(new ImageIcon(VUbicaciones.class.getResource("/img/actualizarUbicacion.png")));
		btnActualizar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnActualizar.setIconTextGap(2);
		btnActualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnActualizar.setBackground(Color.WHITE);
		
		JButton btnBuscarUbicacion = new JButton("Buscar Ubicaci\u00F3n");
		btnBuscarUbicacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String respuesta = JOptionPane.showInputDialog(null,"Ingrese número de inventario o etiqueta","Busqueda de medios",JOptionPane.QUESTION_MESSAGE);
				InterInventario busqueda = FabricaInterfaz.getInventario();
				try {
					if (ValidarDatos.isNumeric(respuesta)) {
						int valor = Integer.parseInt(respuesta);
						Medio medio = busqueda.buscarMedioInventario(valor);
						Mensajes.confirmacion("El medio con el numero de Inventario: "+ respuesta+ ", Esta en: "+ medio.getUbicacion());
					}else {
						Medio medio = busqueda.buscarMedioEtiqueta(respuesta);
						Mensajes.confirmacion("En medio con la etiqueta: "+ respuesta+ ", Esta en: "+ medio.getUbicacion());
					}
				} catch (NullPointerException e) {
					Mensajes.errorGenerico("El dato ingresado no existe");
				}
				
			}
		});
		btnBuscarUbicacion.setIcon(new ImageIcon(VUbicaciones.class.getResource("/img/buscarMovimiento.png")));
		btnBuscarUbicacion.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBuscarUbicacion.setIconTextGap(2);
		btnBuscarUbicacion.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBuscarUbicacion.setBackground(Color.WHITE);
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
							.addComponent(btnNuevoMovimiento, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnBuscarUbicacion, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnActualizar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGap(119)
					.addComponent(btnNuevoMovimiento, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnBuscarUbicacion, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(143, Short.MAX_VALUE))
		);
		panel_7.setLayout(gl_panel_7);
		panel.setLayout(gl_panel);
	}
	
	
	public void cargarCintoteca() {
		String valor = "CINTOTECA";
		InterInventario operacion = FabricaInterfaz.getInventario();
		List<Inventario> lista = operacion.listadoUbicacion(valor,"ACTIVO");
		modeloCintoteca.setRowCount(0);
		for (int i = 0; i < lista.size(); i++) {
			Inventario li = lista.get(i);
			
			Object[] celda = {li.getMedios().get(i).getNumMedio(), li.getMedios().get(i).getEtiqueta()};
			modeloCintoteca.addRow(celda);
			
		}
	}
	
	public void cargarCPD1() {
		String valor = "ROBOT CPD1";
		InterInventario operacion = FabricaInterfaz.getInventario();
		List<Inventario> lista = operacion.listadoUbicacion(valor,"ACTIVO");
		modeloCPD1.setRowCount(0);
		for (int i = 0; i < lista.size(); i++) {
			Inventario li = lista.get(i);
			
			Object[] celda = {li.getMedios().get(i).getNumMedio(), li.getMedios().get(i).getEtiqueta()};
			modeloCPD1.addRow(celda);
		}
	}
	
	public void cargarCPD2() {
		String valor = "ROBOT CPD2";
		InterInventario operacion = FabricaInterfaz.getInventario();
		List<Inventario> lista = operacion.listadoUbicacion(valor,"ACTIVO");
		modeloCPD2.setRowCount(0);
		for (int i = 0; i < lista.size(); i++) {
			Inventario li = lista.get(i);
			
			Object[] celda = {li.getMedios().get(i).getNumMedio(), li.getMedios().get(i).getEtiqueta()};
			modeloCPD2.addRow(celda);
		}
	}
	
	public void cargarCompuSeguridad() {
		String valor = "COMPU SEGURIDAD";
		InterInventario operacion = FabricaInterfaz.getInventario();
		List<Inventario> lista = operacion.listadoUbicacion(valor,"ACTIVO");
		modeloCompuSeguridad.setRowCount(0);
		for (int i = 0; i < lista.size(); i++) {
			Inventario li = lista.get(i);
			
			Object[] celda = {li.getMedios().get(i).getNumMedio(), li.getMedios().get(i).getEtiqueta()};
			modeloCompuSeguridad.addRow(celda);
		}
	}
	
	public void cargarCont417() {
		String valor = "CONTENEDOR 417";
		InterInventario operacion = FabricaInterfaz.getInventario();
		List<Inventario> lista = operacion.listadoUbicacion(valor,"ACTIVO");
		modeloCont417.setRowCount(0);
		for (int i = 0; i < lista.size(); i++) {
			Inventario li = lista.get(i);
			
			Object[] celda = {li.getMedios().get(i).getNumMedio(), li.getMedios().get(i).getEtiqueta()};
			modeloCont417.addRow(celda);
		}
	}
	
	public void cargarCont418() {
		String valor = "CONTENEDOR 418";
		InterInventario operacion = FabricaInterfaz.getInventario();
		List<Inventario> lista = operacion.listadoUbicacion(valor,"ACTIVO");
		modeloCont418.setRowCount(0);
		for (int i = 0; i < lista.size(); i++) {
			Inventario li = lista.get(i);
			
			Object[] celda = {li.getMedios().get(i).getNumMedio(), li.getMedios().get(i).getEtiqueta()};
			modeloCont418.addRow(celda);
		}		
	}
	
	public void cargarCimo() {
		String valor = "CIMO";
		InterInventario operacion = FabricaInterfaz.getInventario();
		List<Inventario> lista = operacion.listadoUbicacion(valor,"ACTIVO");
		modeloCimo.setRowCount(0);
		for (int i = 0; i < lista.size(); i++) {
			Inventario li = lista.get(i);
			
			Object[] celda = {li.getMedios().get(i).getNumMedio(), li.getMedios().get(i).getEtiqueta()};
			modeloCimo.addRow(celda);
		}
	}
	
	public void actualizarListados() {
		cargarCintoteca();
        cargarCimo();
        cargarCompuSeguridad();
        cargarCont417();
        cargarCont418();
        cargarCPD1();
        cargarCPD2();
		
	}
	
	
}
