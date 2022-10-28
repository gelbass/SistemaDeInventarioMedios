package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.util.function.ToLongBiFunction;
import java.awt.event.ActionEvent;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.Point;
import java.awt.ComponentOrientation;
import java.awt.Frame;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import java.awt.Font;
import javax.swing.JLabel;


public class Principal extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JPanel contentPane;
	public static JDesktopPane desktopPane = new JDesktopPane();
	public static JLabel lbNomUsuario;
	public static JLabel lbUsuario;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setAlwaysOnTop(false);
		
		componentes();
		
	}
	
	public void componentes() {
		this.setExtendedState(MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/SAO_MAINICON.png")));
		setFont(null);
        
		setTitle("Inventario Medios Backup");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 732);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Medios de respaldo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nuevo medio");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VMedios vm = new VMedios();
			    desktopPane.add(vm); 
			    Dimension desktopSize = desktopPane.getSize();
			    Dimension FrameSize = vm.getSize();
			    vm.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
			    vm.show(); 
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ver Inventario");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VInventario vi = new VInventario();
				desktopPane.add(vi);
				Dimension desktopSize = desktopPane.getSize();
				Dimension FrameSize = vi.getSize();
				vi.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
				vi.show();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Movimientos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Nuevo Movimiento");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VMovimiento vm = new VMovimiento();
				desktopPane.add(vm);
				Dimension desktopSize = desktopPane.getSize();
				Dimension FrameSize = vm.getSize();
				vm.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
				vm.show();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JSeparator separator = new JSeparator();
		mnNewMenu_1.add(separator);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Panel de Ubicaci\u00F3n");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VUbicaciones vu = new VUbicaciones();
				desktopPane.add(vu);
				Dimension desktopSize = desktopPane.getSize();
				Dimension FrameSize = vu.getSize();
				vu.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
				vu.show();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Administraci\u00F3n");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Baja de medios");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VDestruir vd = new VDestruir();
				desktopPane.add(vd);
				Dimension desktopSize = desktopPane.getSize();
				Dimension FrameSize = vd.getSize();
				vd.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
				vd.show();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu_2.add(separator_1);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Ver Actividad");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VActividad va = new VActividad();
				desktopPane.add(va);
				Dimension desktopSize = desktopPane.getSize();
				Dimension FrameSize = va.getSize();
				va.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
				va.show();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JSeparator separator_2 = new JSeparator();
		mnNewMenu_2.add(separator_2);
		
		JMenu mnNewMenu_3 = new JMenu("Usuarios");
		mnNewMenu_2.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Cambiar contrase\u00F1a");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VCambioPassUS vc = new VCambioPassUS();
				desktopPane.add(vc);
				Dimension desktopSize = desktopPane.getSize();
				Dimension FrameSize = vc.getSize();
				vc.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
				vc.show();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Nuevo Usuario");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VUsuario vu = new VUsuario();
				desktopPane.add(vu);
				Dimension desktopSize = desktopPane.getSize();
				Dimension FrameSize = vu.getSize();
				vu.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
				vu.show();
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		desktopPane.setBackground(new Color(173, 216, 230));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Usuario: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(lblNewLabel);
		
		
		
		setLocationRelativeTo(null);
		 	
		
		lbNomUsuario = new JLabel("-");
		lbNomUsuario.setMinimumSize(new Dimension(200, 14));
		lbNomUsuario.setMaximumSize(new Dimension(200, 14));
		lbNomUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(lbNomUsuario);
		
		lbUsuario = new JLabel("-");
		lbUsuario.setVisible(false);
		lbUsuario.setMaximumSize(new Dimension(100, 14));
		toolBar.add(lbUsuario);
		
		
	}
}
