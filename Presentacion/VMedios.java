package Presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;


import com.mxrck.autocompleter.TextAutoCompleter;

import DataType.Inventario;
import DataType.Medio;
import DataType.Usuarios;
import Logica.ComboBox;
import Logica.FabricaInterfaz;
import LogicaInterfaces.InterInventario;
import LogicaInterfaces.InterUsuario;
import Utilidades.ConvertirDatos;
import Utilidades.Mensajes;
import Utilidades.ValidarDatos;

import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class VMedios extends JInternalFrame {

    private ArrayList<String> tipoMedio = new ArrayList<String>();
    private ArrayList<String> ubicacion = new ArrayList<String>();
    private ArrayList<String> status = new ArrayList<String>();
    private ArrayList<Medio> buscar = new ArrayList<Medio>();
    private ArrayList<Medio> buscarNum = new ArrayList<Medio>();
    private Integer nInventario = mostarNumeroInventario();
    private String fechaActual;
    private String usuarioConectado = Principal.lbUsuario.getText();

    private JTextField txtEtiqueta;
    private JTextField txtDescripcion;
    private JComboBox<String> cbxTipoMedio;
    private JComboBox<String> cbxUbicacion;
    private JPanel panel;
    private JPanel panel_2;
    private JPanel panel_1;
    private JPanel panel_3;
    private JButton btnBuscar;
    private JButton btnActualizar;
    private JButton btnNuevo;
    private JButton btnLimpiar;
    private JButton btnGuardar;
    private JLabel lblNewLabel_3;
    private JLabel lbNumInv;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel;
    private JLabel lblStatus;
    private JLabel lblNewLabel_2;
    private JComboBox<String> cbxStatus;
    private LocalDateTime f = LocalDateTime.now();
    private JLabel fecha;
    private String ubicacionInicial;
    private JTextField txtBusqueda;
    private TextAutoCompleter acEtq;
    private TextAutoCompleter acNumInv;

    /**
     * Launch the application.
     *
     * @throws ParseException
     */
    public VMedios() {
        setFrameIcon(new ImageIcon(VMedios.class.getResource("/img/SAO_MAINICON.png")));
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        componentes();
        //cargarEtqTxt();
        //cargarNumInvTxt();
        fechaActual = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(f);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VMedios frame = new VMedios();
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
    // Activar llenado de datos
    private void componentes() {

        panel = new javax.swing.JPanel();
        panel_1 = new JPanel();
        panel_2 = new JPanel();

        panel_3 = new JPanel();

        btnBuscar = new JButton("Buscar");
        btnBuscar.setIcon(new ImageIcon(VMedios.class.getResource("/img/buscar.png")));
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cbxStatus.removeAllItems();
                cbxTipoMedio.removeAllItems();
                cbxUbicacion.removeAllItems();
                cargarStatus();
                cargarTipoMedio();
                cargarUbicacion();
                InterInventario operacion = FabricaInterfaz.getInventario();
                try {
                    if (ValidarDatos.isNumeric(txtBusqueda.getText())) {
                        Medio medio = operacion.buscarMedioInventario(Integer.parseInt(txtBusqueda.getText()));
                        lbNumInv.setText(String.valueOf(medio.getNumMedio()));
                        txtEtiqueta.setText(medio.getEtiqueta());
                        txtDescripcion.setText(medio.getDescripcion());
                        for (int j = 0; j < tipoMedio.size(); j++) {
                            if (medio.getTipo().equals(tipoMedio.get(j))) {
                                cbxTipoMedio.setSelectedIndex(j);
                            }
                        }
                        Inventario inv = operacion.buscarInventario(
                                Integer.parseInt(lbNumInv.getText()));
                        try {
                            for (int k = 0; k < ubicacion.size(); k++) {
                                if (inv.getUbicacion().equals(ubicacion.get(k))) {
                                    cbxUbicacion.setSelectedIndex(k);
                                }
                            }
                            ubicacionInicial = (String) cbxUbicacion.getSelectedItem().toString();
                        } catch (NullPointerException e) {
                            Mensajes.errorGenerico("Debe actualizar ubicaci�n del medio de respaldo");
                            cbxUbicacion.setSelectedIndex(-1);
                            ubicacionInicial = "";
                        }

                        try {
                            for (int l = 0; l < status.size(); l++) {
                                if (medio.getStatus().equals(status.get(l))) {
                                    cbxStatus.setSelectedIndex(l);
                                }
                            }
                        } catch (NullPointerException e) {
                            Mensajes.errorGenerico("Debe actualizar el status del medio de respaldo");
                        }
                        habilitar();

                    } else {

                        Medio medio = operacion.buscarMedioEtiqueta(txtBusqueda.getText());
                        lbNumInv.setText(String.valueOf(medio.getNumMedio()));
                        txtEtiqueta.setText(medio.getEtiqueta());
                        txtDescripcion.setText(medio.getDescripcion());

                        for (int j = 0; j < tipoMedio.size(); j++) {
                            if (medio.getTipo().equals(tipoMedio.get(j))) {
                                cbxTipoMedio.setSelectedIndex(j);
                            }
                        }

                        Inventario inv = operacion.buscarInventario(
                                Integer.parseInt(lbNumInv.getText()));
                        try {
                            for (int k = 0; k < ubicacion.size(); k++) {
                                if (inv.getUbicacion().equals(ubicacion.get(k))) {
                                    cbxUbicacion.setSelectedIndex(k);
                                }
                            }
                            ubicacionInicial = (String) cbxUbicacion.getSelectedItem().toString();
                        } catch (NullPointerException e) {
                            Mensajes.errorGenerico("Debe actualizar el ubicacion del medio de respaldo");
                            cbxUbicacion.setSelectedIndex(-1);
                            ubicacionInicial = "";
                        }

                        try {
                            for (int l = 0; l < status.size(); l++) {
                                if (medio.getStatus().equals(status.get(l))) {
                                    cbxStatus.setSelectedIndex(l);
                                }
                            }
                        } catch (NullPointerException e) {
                            Mensajes.errorGenerico("Debe actualizar el status del medio de respaldo");
                        }
                        ubicacionInicial = (String) cbxUbicacion.getSelectedItem().toString();
                        habilitar();
                    }
                } catch (NullPointerException e) {
                    Mensajes.errorGenerico("Ingrese un criterio para la busqueda correcto");
                }
            }
        });
        btnActualizar = new JButton("Actualizar");
        btnActualizar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnActualizar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnActualizar.setIcon(new ImageIcon(VMedios.class.getResource("/img/actualizar.png")));
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                InterInventario operacion = FabricaInterfaz.getInventario();
                InterUsuario log = FabricaInterfaz.getUsuarios();
                cbxUbicacion.setEnabled(false);
                Medio m = null;
                Inventario inv = null;
                Usuarios usuario = new Usuarios();
                usuario.setUser(usuarioConectado);
                usuario.setActividadLog("ACTUALIZACION DE MEDIO");

                try {
                    m = new Medio(Integer.parseInt(lbNumInv.getText()),
                            ConvertirDatos.StringAFecha(fechaActual),
                            (String) cbxStatus.getSelectedItem(),
                            txtDescripcion.getText(), txtEtiqueta.getText(),
                            (String) cbxTipoMedio.getSelectedItem(), (String) cbxUbicacion.getSelectedItem());

                    inv = new Inventario(m, ConvertirDatos.StringAFecha(fechaActual));
                    log.addActividad(usuario, m);

                } catch (ParseException | NullPointerException e) {
                    Logger.getLogger(VMedios.class.getName()).log(Level.SEVERE,
                            null, e);
                    JOptionPane.showMessageDialog(rootPane, "Error",
                            "Debe ingresar todos los datos",
                            JOptionPane.ERROR_MESSAGE);
                }

                String ubicacionFinal = (String) cbxUbicacion.getSelectedItem().toString();

                operacion.modificarMedio(Integer.parseInt(lbNumInv.getText()), m, usuarioConectado);
                //System.out.println(ubicacionInicial + " " +ubicacionFinal);

                if (!ubicacionInicial.equals(ubicacionFinal)) {
                    //System.out.println("cambio ubicacion");
                    operacion.agregarInventario(inv, usuarioConectado);
                }
                limpiar();
                cbxUbicacion.removeAllItems();
            }
        });
        btnNuevo = new JButton("Nuevo");
        btnNuevo.setIcon(new ImageIcon(VMedios.class.getResource("/img/add.png")));
        btnNuevo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                nuevo();
            }
        });
        btnGuardar = new JButton("Guardar");
        btnGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnGuardar.setIcon(new ImageIcon(VMedios.class.getResource("/img/guardar.png")));
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                InterInventario operacion = FabricaInterfaz.getInventario();
                InterUsuario log = FabricaInterfaz.getUsuarios();
                Medio m = null;
                Inventario inv = null;
                Usuarios usuario = new Usuarios();
                usuario.setNom_ape(Principal.lbUsuario.getText());
                usuario.setActividadLog("INGRESO NUEVO MEDIO");
                try {
                    m = new Medio(Integer.parseInt(lbNumInv.getText()),
                            ConvertirDatos.StringAFecha(fechaActual),
                            (String) cbxStatus.getSelectedItem(),
                            txtDescripcion.getText(), txtEtiqueta.getText(),
                            (String) cbxTipoMedio.getSelectedItem(), (String) cbxUbicacion.getSelectedItem());
                    inv = new Inventario(m, ConvertirDatos.StringAFecha(fechaActual));
                    log.addActividad(usuario, m);
                } catch (ParseException | NullPointerException e) {
                    Logger.getLogger(VMedios.class.getName()).log(Level.SEVERE,
                            null, e);
                    JOptionPane.showMessageDialog(rootPane, "Error",
                            "Debe ingresar todos los datos",
                            JOptionPane.ERROR_MESSAGE);
                }
                operacion.agregarMedio(m, usuarioConectado);
                operacion.agregarInventario(inv, usuarioConectado);
                limpiar();
            }
        });

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnLimpiar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnLimpiar.setIcon(new ImageIcon(VMedios.class.getResource("/img/iconoBorrar.png")));
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                limpiar();
            }
        });
        lblNewLabel_3 = new JLabel("N\u00FAmero de Inventario");
        lbNumInv = new JLabel("-");
        lblNewLabel_1 = new JLabel("Tipo medio");
        cbxTipoMedio = new javax.swing.JComboBox<>();
        txtDescripcion = new JTextField();
        txtDescripcion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ev) {
                ValidarDatos.textomayuscula(ev);
            }
        });
        txtEtiqueta = new JTextField();
        txtEtiqueta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ev) {
                ValidarDatos.textomayuscula(ev);
            }
        });
        cbxUbicacion = new JComboBox<>();

        lblNewLabel_5 = new JLabel("Ubicaci\u00F3n");
        lblNewLabel = new JLabel("Etiqueta");
        lblNewLabel_2 = new JLabel("Descripci\u00F3n");
        lblStatus = new JLabel("Status");
        // setClosed(true);
        setClosable(true);
        setTitle("Medios");
        setBounds(100, 100, 529, 355);
        getContentPane().setLayout(new BorderLayout(0, 0));

        panel.setToolTipText("Ingreso");
        panel.setAlignmentY(Component.TOP_ALIGNMENT);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setAutoscrolls(true);
        getContentPane().add(panel, BorderLayout.CENTER);

        panel_1.setBorder(new TitledBorder(null, "Medios", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panel_1.setAutoscrolls(true);
        panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
        panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel_3.setBorder(new TitledBorder(null, "Busqueda de Medios",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(panel_3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 483, Short.MAX_VALUE)
                                        .addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                                        .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(61, Short.MAX_VALUE))
        );
        panel_3.setLayout(null);

        btnBuscar.setBounds(325, 18, 103, 48);
        panel_3.add(btnBuscar);

        fecha = new JLabel("N\u00FAmero de Inventario o etiqueta");
        fecha.setBounds(46, 35, 164, 14);
        panel_3.add(fecha);

        txtBusqueda = new JTextField();
        txtBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ev) {
                ValidarDatos.textomayuscula(ev);
                if (ValidarDatos.isNumeric(txtBusqueda.getText())) {
                    acNumInv = new TextAutoCompleter(txtBusqueda);
                    ValidarDatos.cargarNumInvTxt(acNumInv);

                } else {
                    acEtq = new TextAutoCompleter(txtBusqueda);
                    ValidarDatos.cargarEtqTxt(acEtq);
                }
            }
        });

        txtBusqueda.setVisible(true);

        txtBusqueda.setBounds(208, 32, 62, 20);
        panel_3.add(txtBusqueda);
        txtBusqueda.setColumns(10);
        panel_2.setLayout(null);

        btnActualizar.setEnabled(false);
        btnActualizar.setBounds(257, 11, 89, 57);
        panel_2.add(btnActualizar);

        btnNuevo.setBounds(10, 11, 89, 57);
        panel_2.add(btnNuevo);

        btnGuardar.setEnabled(false);
        btnGuardar.setBounds(131, 11, 89, 57);
        panel_2.add(btnGuardar);

        btnLimpiar.setEnabled(false);
        btnLimpiar.setBounds(382, 11, 89, 57);
        panel_2.add(btnLimpiar);
        panel_1.setLayout(null);

        lblNewLabel_3.setBounds(10, 25, 105, 14);
        panel_1.add(lblNewLabel_3);
        lblNewLabel_3.setFocusable(false);
        lblNewLabel_3.setFocusTraversalKeysEnabled(false);

        lbNumInv.setBounds(135, 17, 30, 24);
        panel_1.add(lbNumInv);
        lbNumInv.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));

        lblNewLabel_1.setBounds(228, 25, 51, 14);
        panel_1.add(lblNewLabel_1);

        cbxTipoMedio.setEnabled(false);
        cbxTipoMedio.setBounds(289, 22, 181, 20);
        panel_1.add(cbxTipoMedio);

        txtDescripcion.setEnabled(false);
        txtDescripcion.setBounds(289, 61, 181, 20);
        panel_1.add(txtDescripcion);
        txtDescripcion.setColumns(10);

        txtEtiqueta.setEnabled(false);
        txtEtiqueta.setBounds(60, 61, 140, 20);
        panel_1.add(txtEtiqueta);
        txtEtiqueta.setAutoscrolls(false);
        txtEtiqueta.setColumns(10);

        cbxUbicacion.setEnabled(false);
        cbxUbicacion.setBounds(60, 97, 140, 20);
        panel_1.add(cbxUbicacion);

        lblNewLabel_5.setBounds(10, 100, 45, 14);
        panel_1.add(lblNewLabel_5);

        lblNewLabel.setBounds(10, 64, 40, 14);
        panel_1.add(lblNewLabel);

        lblNewLabel_2.setBounds(225, 64, 54, 14);
        panel_1.add(lblNewLabel_2);

        lblStatus.setBounds(248, 100, 31, 14);
        panel_1.add(lblStatus);

        cbxStatus = new JComboBox<String>();
        cbxStatus.setEnabled(false);
        cbxStatus.setBounds(289, 97, 181, 20);
        panel_1.add(cbxStatus);
        gl_panel.setAutoCreateGaps(true);
        gl_panel.setAutoCreateContainerGaps(true);
        panel.setLayout(gl_panel);

    }

    private Integer mostarNumeroInventario() {
        int nInventario;

        InterInventario ninv = FabricaInterfaz.getInventario();

        if (ninv.obtenerUltimoNumInventario() == null) {
            nInventario = 1;
        } else {
            nInventario = ninv.obtenerUltimoNumInventario() + 1;
        }
        return nInventario;
    }

    private void cargarTipoMedio() {
        cbxTipoMedio.removeAllItems();
        cbxTipoMedio.setSelectedIndex(-1);
        tipoMedio = ComboBox.listarTipoMedio();
        for (int i = 0; i < tipoMedio.size(); i++) {
            cbxTipoMedio.addItem(tipoMedio.get(i));
        }

    }

    private void cargarUbicacion() {
        cbxUbicacion.removeAllItems();
        cbxUbicacion.setSelectedIndex(-1);
        ubicacion = ComboBox.listarUbicacion();
        for (int i = 0; i < ubicacion.size(); i++) {
            cbxUbicacion.addItem(ubicacion.get(i));
        }
    }

    private void cargarStatus() {
        cbxStatus.removeAllItems();
        cbxStatus.setSelectedIndex(-1);
        status = ComboBox.listarStatus();
        for (int i = 0; i < status.size(); i++) {
            cbxStatus.addItem(status.get(i));
        }
    }

    public void nuevo() {
        lbNumInv.setText(Integer.toString(nInventario));
        txtBusqueda.setEnabled(false);
        txtBusqueda.setText(null);
        btnBuscar.setEnabled(false);
        txtDescripcion.setEnabled(true);
        txtEtiqueta.setEnabled(true);
        cbxStatus.setSelectedIndex(-1);
        cbxTipoMedio.setEnabled(true);
        cbxUbicacion.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnLimpiar.setEnabled(true);
        btnBuscar.setEnabled(false);
        cargarTipoMedio();
        cargarUbicacion();
        cargarStatus();

    }

    public void limpiar() {
        txtBusqueda.setEnabled(true);
        txtBusqueda.setText(null);
        txtDescripcion.setEnabled(false);;
        txtDescripcion.setText(null);
        txtEtiqueta.setEnabled(false);
        txtEtiqueta.setText(null);
        cbxStatus.setSelectedIndex(-1);
        cbxTipoMedio.setSelectedIndex(-1);
        cbxUbicacion.setSelectedIndex(-1);
        cbxStatus.setEnabled(false);
        cbxTipoMedio.setEnabled(false);
        cbxUbicacion.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnLimpiar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnBuscar.setEnabled(true);
        lbNumInv.setText("-");
    }

    public void habilitar() {
        txtBusqueda.setEnabled(false);
        txtBusqueda.setText(null);
        btnBuscar.setEnabled(false);
        txtDescripcion.setEnabled(true);
        txtEtiqueta.setEnabled(true);
        cbxStatus.setEnabled(true);
        cbxTipoMedio.setEnabled(true);
        cbxUbicacion.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnLimpiar.setEnabled(true);
        btnNuevo.setEnabled(false);
    }
}
