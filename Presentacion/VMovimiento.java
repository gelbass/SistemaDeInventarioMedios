package Presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import com.mxrck.autocompleter.TextAutoCompleter;

import DataType.Inventario;
import DataType.Medio;
import DataType.Usuarios;
import Logica.ComboBox;
import Logica.FabricaInterfaz;
import LogicaInterfaces.InterInventario;
import LogicaInterfaces.InterUsuario;
import Persistencia.PersistenciaMedios;
import Utilidades.ConvertirDatos;
import Utilidades.Mensajes;
import Utilidades.ValidarDatos;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VMovimiento extends JInternalFrame {
	private TextAutoCompleter acEtq;
	private TextAutoCompleter acNumInv;
	
	private JTextField txtBusqueda;
	private JComboBox<String> cbxUbicacion = new JComboBox<String>();
	private JLabel lbUbicacionActual = new JLabel("-");
	private JLabel lbEtiqueta = new JLabel("-");
	private JLabel lbNumInv = new JLabel("-");
	private JButton btnBuscar = new JButton("Buscar");
	private JButton btnMover = new JButton("Mover");
	private JLabel lbFechaUltMov = new JLabel("              -");
	private JButton btnLimpiar = new JButton("Limpiar");
	private String statusMedio;
	private String fechaActual;
	private LocalDateTime f = LocalDateTime.now();
	private Date fa;
	private ArrayList<String> ubicacion = new ArrayList<String>();
	private String usuarioConectado = Principal.lbUsuario.getText(); 
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VMovimiento frame = new VMovimiento();
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
	public VMovimiento() {
		componentes();
		
		fechaActual = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(f);
	}
	public void componentes() {
		
		setFrameIcon(new ImageIcon(VMovimiento.class.getResource("/img/SAO_MAINICON.png")));
		setClosable(true);
		setTitle("Movimientos");
		setBounds(100, 100, 522, 276);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		JLabel lblNmeroDeInventario = new JLabel("N\u00FAmero de Inventario o etiqueta");
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Movimiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Busqueda de medios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 489, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addGap(93))
		);
		
		
		lblNmeroDeInventario.setFocusable(false);
		
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
		btnBuscar.setIcon(new ImageIcon(VMovimiento.class.getResource("/img/buscar.png")));
		
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarBuscar();
				cargarUbicacion();
				InterInventario operacion = FabricaInterfaz.getInventario();
				if (txtBusqueda.getText() == null) {
					Mensajes.errorGenerico("Debe ingresar parametros para la busqueda");
				}else {
					if (ValidarDatos.isNumeric(txtBusqueda.getText())) {
						Inventario inv = operacion.ultimoMovimientoNumInv(Integer.parseInt(txtBusqueda.getText()));
						try {
							statusMedio = inv.getMed().getStatus();
							if (statusMedio.equals("DESTRUIDA")) {
								Mensajes.errorGenerico("No se puede realizar movimientos a este medio. Su status es DESTRUIDA");
								limpiar();
							}else {
								cargarDatosMedios(Integer.parseInt(txtBusqueda.getText()));
								lbNumInv.setText(String.valueOf(inv.getMed().getNumMedio()));
								lbEtiqueta.setText(inv.getMed().getEtiqueta());
								lbUbicacionActual.setText(inv.getUbicacion());
								lbFechaUltMov.setText(ConvertirDatos.FechaAString(inv.getFechaMovimiento()));
								lbUbicacionActual.setText(inv.getMed().getUbicacion());
								cbxUbicacion.removeItem(lbUbicacionActual.getText());
							}
						} catch (NullPointerException ex) {
							Mensajes.errorGenerico("No existe información para en número de medio ingresado");
						}
						
						
						
					}else {
						Inventario inv = operacion.ultimoMovimientoEtq(txtBusqueda.getText());
						try {
							statusMedio = inv.getMed().getStatus();
							if (statusMedio.equals("DESTRUIDA")) {
								Mensajes.errorGenerico("No se puede realizar movimientos a este medio. Su status es DESTRUIDA");
								limpiar();
							}else {
								lbNumInv.setText(String.valueOf(inv.getMed().getNumMedio()));
								lbEtiqueta.setText(inv.getMed().getEtiqueta());
								lbUbicacionActual.setText(inv.getUbicacion());
								lbFechaUltMov.setText(ConvertirDatos.FechaAString(inv.getFechaMovimiento()));
								
								lbUbicacionActual.setText(inv.getMed().getUbicacion());
							}
						} catch (NullPointerException e) {
							Mensajes.errorGenerico("No existe información para la etiqueta ingresada");
						}	
					}					
				}
			}
		});
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(30, Short.MAX_VALUE)
					.addComponent(lblNmeroDeInventario)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtBusqueda, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(btnBuscar)
					.addGap(33))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBuscar)
						.addComponent(lblNmeroDeInventario)
						.addComponent(txtBusqueda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMover, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(btnMover, GroupLayout.PREFERRED_SIZE, 57, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnLimpiar))
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
					.addContainerGap())
		);
		btnMover.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMover.setIcon(new ImageIcon(VMovimiento.class.getResource("/img/mover.png")));
		btnMover.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLimpiar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLimpiar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLimpiar.setIcon(new ImageIcon(VMovimiento.class.getResource("/img/iconoBorrar.png")));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			limpiar();
			}
		});
		btnMover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				InterInventario operacion = FabricaInterfaz.getInventario();
				InterUsuario log = FabricaInterfaz.getUsuarios();
				Medio m = new Medio();
				m.setNumMedio(Integer.parseInt(lbNumInv.getText()));
				m.setUbicacion((String)cbxUbicacion.getSelectedItem());
				Usuarios usuario = new Usuarios();
				usuario.setUser(usuarioConectado);
				usuario.setActividadLog("ACTUALIZAR MOVIMIENTO");
				try {
					fa =ConvertirDatos.StringAFecha(fechaActual);
				} catch (ParseException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				Inventario inv = new Inventario(m,fa);
				
				
				if (cbxUbicacion.getSelectedIndex() == -1) {
					Mensajes.errorGenerico("Debe agregar nueva ubicación");
				}else {
					operacion.agregarInventario(inv,usuarioConectado);
					operacion.actualizarUbicacion(Integer.parseInt(lbNumInv.getText()), (String)cbxUbicacion.getSelectedItem(), usuarioConectado);
					log.addActividad(usuario, (new Medio(Integer.parseInt(lbNumInv.getText()),lbEtiqueta.getText(),(String)cbxUbicacion.getSelectedItem(),fa,statusMedio)));
					limpiar();
				}	
			}
		});
		btnMover.setEnabled(false);
		
		JLabel label_2 = new JLabel("N\u00FAmero de Inventario");
		label_2.setFocusable(false);
		
		
		lbNumInv.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel label_3 = new JLabel("Etiqueta");
		
		
		lbEtiqueta.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel = new JLabel("Ubicaci\u00F3n actual");
		
		
		lbUbicacionActual.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblUbicacinNueva = new JLabel("Ubicaci\u00F3n nueva");
		
		JLabel lblFechaDelltimo = new JLabel("Fecha del \u00FAltimo movimiento");
		
		
		lbFechaUltMov.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblUbicacinNueva))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(cbxUbicacion, 0, 237, Short.MAX_VALUE)
								.addComponent(lbUbicacionActual, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(lbNumInv, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(label_3)
									.addGap(6)
									.addComponent(lbEtiqueta, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addGap(10)
									.addComponent(lbFechaUltMov, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblFechaDelltimo, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(3)
							.addComponent(label_2))
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbNumInv, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblFechaDelltimo)))
					.addGap(6)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(3)
							.addComponent(label_3))
						.addComponent(lbEtiqueta, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbFechaUltMov, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lbUbicacionActual, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUbicacinNueva)
						.addComponent(cbxUbicacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		cbxUbicacion.setEnabled(false);
		panel_4.setLayout(gl_panel_4);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
	}
	
	public void habilitarBuscar() {
		btnMover.setEnabled(true);
		cbxUbicacion.setEnabled(true);
	}
	
	public void limpiar() {
		btnMover.setEnabled(false);
		cbxUbicacion.setEnabled(false);
		lbNumInv.setText(null);
		lbFechaUltMov.setText(null);
		lbEtiqueta.setText(null);
		lbFechaUltMov.setText(null);
		lbUbicacionActual.setText(null);
		cbxUbicacion.setSelectedIndex(-1);
		txtBusqueda.setText(null);
	}
	
	private void cargarUbicacion() {
		cbxUbicacion.removeAllItems();
		ubicacion = ComboBox.listarUbicacion();
		for (int i = 0; i < ubicacion.size(); i++) {
			cbxUbicacion.addItem(ubicacion.get(i));
		}
		cbxUbicacion.setSelectedIndex(-1);
	}

	private void cargarDatosMedios(int valor) {

			InterInventario operacion = FabricaInterfaz.getInventario();
			Medio medio = operacion.buscarMedioInventario(Integer.parseInt(txtBusqueda.getText()));
			lbNumInv.setText(String.valueOf(medio.getNumMedio()));
			lbEtiqueta.setText(medio.getEtiqueta());
		
	}
	
	
}
