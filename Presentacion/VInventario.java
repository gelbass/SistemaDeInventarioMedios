package Presentacion;

import java.awt.EventQueue;
import java.util.ArrayList;

import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import DataType.Inventario;
import Logica.ComboBox;
import Logica.FabricaInterfaz;
import LogicaInterfaces.InterInventario;
import Utilidades.ExportarDatosExcel;

import java.io.File;

import javax.swing.JTable;
import java.awt.ComponentOrientation;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Dimension;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.peer.LabelPeer;


@SuppressWarnings({"serial", "unused"})
public class VInventario extends JInternalFrame {
	private JPanel panel = new JPanel();
	private JPanel panel_1 = new JPanel();
	private JLabel lblNewLabel = new JLabel("Tipo");
	
	private JLabel lblNewLabel_1 = new JLabel("Status");
	
	private JButton btnExportar = new JButton("Exportar");
	
	private JComboBox<String> cbxBuscarTipo = new JComboBox<String>();
	private JComboBox<String> cbxBuscarStatus = new JComboBox<String>();
	
	private ArrayList<String> tipoMedio = new ArrayList<String>();
	private ArrayList<String> status = new ArrayList<String>();
	
	private JButton btnVerTodo = new JButton("Ver todo");
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VInventario frame = new VInventario();
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
	private JTable tInventario;
	private final JButton btnLimpiar = new JButton("");

	public VInventario() {
		setVisible(true);
		setAutoscrolls(true);
		setTitle("Inventario");
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		componentes();
		setClosable(true);
        setToolTipText("");		
          
        String Columnas[] = {"NUM INVENTARIO", "ETIQUETA","TIPO", "STATUS","UBICACIÓN"};
        Object datos[][] = {};
        modelo = new DefaultTableModel(datos, Columnas);
        tInventario.setModel(modelo);

        cargarStatus();
        cargarTipoMedio();
        limpiar();
        
        
	}
	 
	public void componentes() {
		setBounds(100, 100, 698, 545);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 660, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxBuscarTipo, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxBuscarStatus, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(25)
					.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVerTodo, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(btnExportar)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExportar, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVerTodo, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(27, Short.MAX_VALUE)
					.addComponent(cbxBuscarStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(31, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel)
						.addComponent(cbxBuscarTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28))
		);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});
		btnLimpiar.setIcon(new ImageIcon(VInventario.class.getResource("/img/iconoBorrar.png")));
		btnExportar.setIcon(new ImageIcon(VInventario.class.getResource("/img/export.png")));
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tInventario.getRowCount() > 0) {
		            JFileChooser chooser = new JFileChooser();
		            FileNameExtensionFilter filter = new FileNameExtensionFilter("Listado de medios", "xls");
		            chooser.setFileFilter(filter);
		            chooser.setDialogTitle("Guardar archivo");
		            chooser.setAcceptAllFileFilterUsed(false);
		            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		                List<JTable> tb = new ArrayList<JTable>();
		                List<String> nom = new ArrayList<String>();
		                tb.add(tInventario);
		                nom.add("Medios");
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
		        }else{
		            JOptionPane.showMessageDialog(rootPane, "No hay datos para exportar","Mensaje de error",JOptionPane.ERROR_MESSAGE);
		        } 
			}
		});
		cbxBuscarTipo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				cbxBuscarStatus.setSelectedIndex(-1);
			}
		});
		cbxBuscarStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				cbxBuscarTipo.setSelectedIndex(-1);
			}
		});
		btnVerTodo.setIcon(new ImageIcon(VInventario.class.getResource("/img/verTodo.png")));
		btnVerTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
				InterInventario operacion = FabricaInterfaz.getInventario();
				List<Inventario> lista = operacion.verInventario();
				modelo.setRowCount(0);
				for (int i = 0; i < lista.size(); i++) {
					Inventario li = lista.get(i);
					
					Object[] celda = {li.getMedios().get(i).getNumMedio(), li.getMedios().get(i).getEtiqueta(), 
							li.getMedios().get(i).getTipo(), li.getMedios().get(i).getStatus(), li.getUbicacion()};
					modelo.addRow(celda);
				}
			}
		});
       
        
        cbxBuscarTipo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		modelo.setRowCount(0);
        		if (cbxBuscarTipo.getSelectedIndex() > -1) {
        			InterInventario operacion = FabricaInterfaz.getInventario();
    				List<Inventario> lista = operacion.listadoTipo(cbxBuscarTipo.getSelectedItem().toString());
    				for (int i = 0; i < lista.size(); i++) {
    					Inventario li = lista.get(i);
    					
    					Object[] celda = {li.getMedios().get(i).getNumMedio(), li.getMedios().get(i).getEtiqueta(), 
    							li.getMedios().get(i).getTipo(), li.getMedios().get(i).getStatus(), li.getUbicacion()};
    					modelo.addRow(celda);
    					
    				}
				}
        		
        	}
        	
        });
        
        cbxBuscarStatus.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		modelo.setRowCount(0);
        		if (cbxBuscarStatus.getSelectedIndex() > -1) {
        			InterInventario operacion = FabricaInterfaz.getInventario();
    				List<Inventario> lista = operacion.listadoStatus(cbxBuscarStatus.getSelectedItem().toString());
    				for (int i = 0; i < lista.size(); i++) {
    					Inventario li = lista.get(i);
    					
    					Object[] celda = {li.getMedios().get(i).getNumMedio(), li.getMedios().get(i).getEtiqueta(), 
    							li.getMedios().get(i).getTipo(), li.getMedios().get(i).getStatus(), li.getUbicacion()};
    					modelo.addRow(celda);
    					
    				}
				}
        	}
        });
        
		panel.setLayout(gl_panel);
		
		tInventario = new JTable();
		tInventario.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tInventario.setBackground(Color.WHITE);
		panel_1.add(tInventario);
		
		JScrollPane scroll = new JScrollPane(tInventario);
		scroll.setPreferredSize(new Dimension(600, 402));
		scroll.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scroll);
		
		getContentPane().setLayout(groupLayout);
	}
	
	private void cargarTipoMedio() {
		//cbxBuscarTipo.setSelectedIndex(-1);
		tipoMedio = ComboBox.listarTipoMedio();
		for (int i = 0; i < tipoMedio.size(); i++) {
			cbxBuscarTipo.addItem(tipoMedio.get(i));
		}
	}
	
	private void cargarStatus() {
		//cbxBuscarStatus.setSelectedIndex(-1);
		status = ComboBox.listarStatus();
		for (int i = 0; i < status.size(); i++) {
			cbxBuscarStatus.addItem(status.get(i));
		}
	}
	private void limpiar() {
		cbxBuscarStatus.setEnabled(true);
		cbxBuscarStatus.setSelectedIndex(-1);
		cbxBuscarTipo.setEnabled(true);
		cbxBuscarTipo.setSelectedIndex(-1);
	}
}
