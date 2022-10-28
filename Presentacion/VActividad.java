package Presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.mxrck.autocompleter.TextAutoCompleter;

import DataType.Actividad;
import DataType.Usuarios;
import Logica.ComboBox;
import Logica.FabricaInterfaz;
import LogicaInterfaces.InterUsuario;
import Utilidades.ConvertirDatos;
import Utilidades.ExportarDatosExcel;
import Utilidades.Mensajes;
import Utilidades.ValidarDatos;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class VActividad extends JInternalFrame {
	private JTable tActividad;
	private JTextField txtBusqueda;
	private JButton btnExportar;
	private JButton btnBuscar;
	private JComboBox<String> cbxUsuarios;
	private JButton btnLimpiar;
	private TextAutoCompleter acEtq;
	private TextAutoCompleter acNumInv;
	private org.jdesktop.swingx.JXDatePicker fechaDesde;
    private org.jdesktop.swingx.JXDatePicker fechaHasta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VActividad frame = new VActividad();
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
	public VActividad() {
		componentes();
		String Columnas[] = {"USUARIO", "FECHA","NUM INVENTARIO", "ETIQUETA","UBICACIÓN", "STATUS", "ACTIVIDAD"};
        Object datos[][] = {};
        modelo = new DefaultTableModel(datos, Columnas);
        tActividad.setModel(modelo);
        cargarUsuarios();
        txtBusqueda.setText(null);
        cbxUsuarios.setSelectedIndex(-1);
		
	}
	
	private void componentes() {
	
		setFrameIcon(new ImageIcon(VActividad.class.getResource("/img/SAO_MAINICON.png")));
		setClosable(true);
		setBounds(100, 100, 960, 545);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Panel de busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 927, Short.MAX_VALUE))
					.addGap(7))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(69, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("Num Medio o Etiqueta");
		
		txtBusqueda = new JTextField();
		txtBusqueda.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent ev) {
				ValidarDatos.textomayuscula(ev);
				if (ValidarDatos.isNumeric(txtBusqueda.getText())) {
					acNumInv = new TextAutoCompleter(txtBusqueda);
					ValidarDatos.cargarNumInvTxt(acNumInv);
					
				}else {
					acEtq = new TextAutoCompleter(txtBusqueda);
					ValidarDatos.cargarEtqTxt(acEtq);
				}
			}
		});
		txtBusqueda.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		
		cbxUsuarios = new JComboBox();
		
		btnLimpiar = new JButton("");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});
		btnLimpiar.setIcon(new ImageIcon(VActividad.class.getResource("/img/iconoBorrar.png")));
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				InterUsuario operacion = FabricaInterfaz.getUsuarios();
				modelo.setRowCount(0);
				
				try {
					if (txtBusqueda.getText().equals("") && cbxUsuarios.getSelectedIndex() == -1) {
						
						List<Actividad> lista = operacion.busquedaFecha(fechaDesde.getDate(),fechaHasta.getDate());
						
						for (int i = 0; i < lista.size(); i++) {
							Actividad la = lista.get(i);
							
							Object[] celdas = {la.getUsuario(),la.getFecha(),la.getNumMedio(),la.getEtiqueta(),la.getUbicacion(),la.getStatus(),la.getActividad()};
							modelo.addRow(celdas);
						}
						bloqueo();
						if (modelo.getRowCount() == 0) {
							Mensajes.errorGenerico("No existe información para esa busqueda");
							limpiar();
						}
					}else {
						if (txtBusqueda.getText().equals("")) {
							
							List<Actividad> lista = operacion.busquedaActUsurio((String)cbxUsuarios.getSelectedItem());
							
							for (int i = 0; i < lista.size(); i++) {
								Actividad la = lista.get(i);
								
								Object[] celdas = {la.getUsuario(),la.getFecha(),la.getNumMedio(),la.getEtiqueta(),la.getUbicacion(),la.getStatus(),la.getActividad()};
								modelo.addRow(celdas);
							}
							bloqueo();
							if (modelo.getRowCount() == 0) {
								Mensajes.errorGenerico("No existe información para esa busqueda");
								limpiar();
							}
							
							
						}else {
							if (ValidarDatos.isNumeric(txtBusqueda.getText())) {
								List<Actividad> lista = operacion.busquedaActNumInv(Integer.parseInt(txtBusqueda.getText()));
								
								for (int i = 0; i < lista.size(); i++) {
									Actividad la = lista.get(i);
									
									Object[] celdas = {la.getUsuario(),la.getFecha(),la.getNumMedio(),la.getEtiqueta(),la.getUbicacion(),la.getStatus(),la.getActividad()};
									modelo.addRow(celdas);
								}
								bloqueo();
								if (modelo.getRowCount() == 0) {
									Mensajes.errorGenerico("No existe información para esa busqueda");
									limpiar();
								}
								
							}
							else {
								List<Actividad> lista = operacion.busquedaActEtq(txtBusqueda.getText());
								
								for (int i = 0; i < lista.size(); i++) {
									Actividad la = lista.get(i);
									
									Object[] celdas = {la.getUsuario(),la.getFecha(),la.getNumMedio(),la.getEtiqueta(),la.getUbicacion(),la.getStatus(),la.getActividad()};
									modelo.addRow(celdas);
								}
								bloqueo();
								if (modelo.getRowCount() == 0) {
									Mensajes.errorGenerico("No existe información para esa busqueda");
									limpiar();
								}
								
							} 
							
						}
					}
				} catch (NullPointerException ex) {
					Mensajes.valornulo(ex);
				}
			}
		});
		
		btnBuscar.setIcon(new ImageIcon(VActividad.class.getResource("/img/buscar.png")));
		
		btnExportar = new JButton("Exportar");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tActividad.getRowCount() > 0) {
		            JFileChooser chooser = new JFileChooser();
		            FileNameExtensionFilter filter = new FileNameExtensionFilter("xls", "Reporte_actividad");
		            chooser.setFileFilter(filter);
		            chooser.setDialogTitle("Guardar archivo");
		            chooser.setAcceptAllFileFilterUsed(false);
		            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		                List<JTable> tb = new ArrayList<JTable>();
		                List<String> nom = new ArrayList<String>();
		                tb.add(tActividad);
		                nom.add("logActividad");
		                String file = chooser.getSelectedFile().toString().concat(".xls");
		                try {
		                    ExportarDatosExcel e = new ExportarDatosExcel(new File(file), tb, nom);
		                    if (e.exportar()) {
		                        JOptionPane.showMessageDialog(null, "Los datos fueron exportados a excel en el directorio seleccionado", "Mensaje de Informacion", JOptionPane.INFORMATION_MESSAGE);
		                    }
		                } catch (Exception e) {
		                    JOptionPane.showMessageDialog(null, "Hubo un error " + e.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        }
			}
		});
		btnExportar.setEnabled(false);
		
		btnExportar.setIcon(new ImageIcon(VActividad.class.getResource("/img/export.png")));
		
		JLabel lblNewLabel_2 = new JLabel("Fecha desde");
		fechaDesde = new org.jdesktop.swingx.JXDatePicker();
		fechaDesde.getEditor().setEditable(false);
		fechaHasta = new org.jdesktop.swingx.JXDatePicker();
		fechaHasta.getEditor().setEditable(false);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(84)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(fechaDesde, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
						.addComponent(txtBusqueda, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
					.addGap(35)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGap(25)
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblFechaHasta, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(fechaHasta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxUsuarios, 0, 106, Short.MAX_VALUE))
					.addGap(34)
					.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 104, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnExportar)
					.addGap(70))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnExportar, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(2)
											.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel)
												.addComponent(txtBusqueda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
											.addComponent(cbxUsuarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblNewLabel_1)))
									.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(fechaHasta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblFechaHasta)
										.addComponent(fechaDesde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(17)
							.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		tActividad = new JTable();
		
		panel_1.add(tActividad);
		JScrollPane scrollPane = new JScrollPane(tActividad);
		scrollPane.setPreferredSize(new Dimension(920, 335));
		panel_1.add(scrollPane);

		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addGap(131)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 507, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(131, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(113, Short.MAX_VALUE))
		);
		
		
		getContentPane().setLayout(groupLayout);

	
	}
	
	private void cargarUsuarios() {
		cbxUsuarios.removeAllItems();
		cbxUsuarios.setSelectedIndex(-1);
		ArrayList<String> status = ComboBox.listarUsuario();
		for (int i = 0; i < status.size(); i++) {
			cbxUsuarios.addItem(status.get(i));
		}
	}
	
	private void limpiar() {
		txtBusqueda.setText(null);
		txtBusqueda.setEnabled(true);
		cbxUsuarios.setSelectedIndex(-1);
		fechaDesde.setEnabled(true);
		fechaHasta.setEnabled(true);
		fechaDesde.setDate(null);
		fechaHasta.setDate(null);
		cbxUsuarios.setEnabled(true);
		modelo.setRowCount(0);
		btnExportar.setEnabled(false);
	}
	private void bloqueo() {
		txtBusqueda.setEnabled(false);
		fechaDesde.setEnabled(false);
		fechaHasta.setEnabled(false);
		cbxUsuarios.setEnabled(false);
		btnExportar.setEnabled(true);
	}
}
