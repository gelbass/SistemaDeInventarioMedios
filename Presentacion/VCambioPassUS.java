package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import DataType.Usuarios;
import Logica.FabricaInterfaz;
import LogicaInterfaces.InterUsuario;
import Persistencia.PersistenciaUsuarios;
import Utilidades.Mensajes;

public class VCambioPassUS extends JInternalFrame {

	private JTextField txtUsuario;
	private JTextField txtNomApe;
	private JPasswordField pfPass;
	private JPasswordField pfOKpass;
	private JLabel lbOK;
	private JButton btnGuardar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VCambioPassUS frame = new VCambioPassUS();
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
	public VCambioPassUS() {
		
		setClosable(true);
		setBounds(100, 100, 257, 324);
		componentes();
		
		InterUsuario operacion = FabricaInterfaz.getUsuarios();
		Usuarios conectado = operacion.activo(Principal.lbUsuario.getText());
		
			try {
				txtUsuario.setText(conectado.getUser());
				txtNomApe.setText(conectado.getNom_ape());
								
			} catch (NullPointerException e) {
				Logger.getLogger(VCambioPassUS.class.getName())
				.log(Level.SEVERE, null, e);
			}
		
	}
	
	public void componentes() {
JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		
		JLabel lblNewLabel = new JLabel("Nuevo usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		
		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre y Apellido");
		
		txtNomApe = new JTextField();
		txtNomApe.setEditable(false);
		txtNomApe.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Contrase\u00F1a");
		
		pfPass = new JPasswordField();
		
		JLabel lblNewLabel_4 = new JLabel("Repita contrase\u00F1a");
		
		pfOKpass = new JPasswordField();
		lbOK = new JLabel("X");
		lbOK.setVisible(false);
		lbOK.setForeground(Color.RED);
		lbOK.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		pfOKpass.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				validarContrasena();
			}
		});
		
		
		lbOK.setMaximumSize(new Dimension(50, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addGap(8)
							.addComponent(pfOKpass, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbOK, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNomApe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(pfPass, 86, 86, 86)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(49)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(48)
					.addComponent(lblNewLabel)
					.addContainerGap(56, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(txtNomApe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(pfPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_4))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(pfOKpass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbOK, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuarios u = null;
				
				if (String.valueOf(pfOKpass.getPassword()).equals("")) {
					Mensajes.errorGenerico("Ingrese contrase�a");
				}	else {
						InterUsuario operacion = FabricaInterfaz.getUsuarios();
						u = new Usuarios(txtUsuario.getText(),String.valueOf(pfOKpass.getPassword()),txtNomApe.getText());
						
						if (validarContrasena()) {
							operacion.cambiarContreasena(u);
							limpiar();
						}else {
							Mensajes.errorGenerico("Las contrase�as no coinciden");
							pfOKpass.getFocusListeners();
						}
						
					}
				}
				
			
		});
		btnGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGuardar.setIcon(new ImageIcon(VUsuario.class.getResource("/img/guardar.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);

	}
	
public void limpiar() {
		
		pfPass.setText(null);
		pfOKpass.setText(null);
		lbOK.setVisible(false);
		
	}
	
	
	public boolean validarContrasena() {
		if (String.valueOf(pfPass.getPassword()).equals(String.valueOf(pfOKpass.getPassword()))) {
			pfOKpass.setForeground(Color.black);
			lbOK.setVisible(true);
			lbOK.setText("OK");
			lbOK.setForeground(Color.green);
			return true;
		}else {
			lbOK.setVisible(true);
			lbOK.setText("X");
			lbOK.setForeground(Color.RED);
			pfOKpass.setForeground(Color.RED);
			return false;
		}
	}
	
	

}
