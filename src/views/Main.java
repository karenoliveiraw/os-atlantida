package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Color;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//os objetos abaixo serão manipulado pelo 
	 JButton btnUsuarios;
	 JButton btnRelatorios;
    JPanel panelUsuarios;
	JLabel lblUsuario;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/icon120x120.png")));
		setTitle("OS - Ordem de Serviço");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnUsuarios = new JButton("");
		btnUsuarios.setEnabled(false);
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setToolTipText("Usuarios");
		btnUsuarios.setIcon(new ImageIcon(Main.class.getResource("/img/clientes].png")));
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Usuarios usuarios = new Usuarios ();
					usuarios.setVisible(true);
			}
		});
		btnUsuarios.setBounds(58, 11, 128, 128);
		contentPane.add(btnUsuarios);
		
		JButton btnOs = new JButton("");
		btnOs.setIcon(new ImageIcon(Main.class.getResource("/img/os.png")));
		btnOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OS  os= new OS();
				os.setVisible(true);
			}
		});
		btnOs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOs.setToolTipText("Ordem de Serviço");
		btnOs.setBounds(229, 11, 128, 128);
		contentPane.add(btnOs);
		
		JButton btnClientes = new JButton("");
		btnClientes.setIcon(new ImageIcon(Main.class.getResource("/img/users.png")));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes ();
				clientes.setVisible(true);
			}
		});
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setToolTipText("Clientes");
		btnClientes.setBounds(58, 163, 128, 128);
		contentPane.add(btnClientes);
		
		btnRelatorios = new JButton("");
		btnRelatorios.setIcon(new ImageIcon(Main.class.getResource("/img/relatorios.png")));
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});
		btnRelatorios.setEnabled(false);
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setToolTipText("Relatórios");
		btnRelatorios.setBounds(229, 163, 128, 128);
		contentPane.add(btnRelatorios);
		
		panelUsuarios = new JPanel();
		panelUsuarios.setBackground(new Color(102, 205, 170));
		panelUsuarios.setBounds(0, 302, 448, 77);
		contentPane.add(panelUsuarios);
		panelUsuarios.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(29, 34, 65, 14);
		panelUsuarios.add(lblNewLabel);
		
		lblUsuario = new JLabel("");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setBounds(79, 34, 158, 14);
		panelUsuarios.add(lblUsuario);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(286, -20, 132, 133);
		panelUsuarios.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/img/icon120x120.png")));
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setIcon(new ImageIcon(Main.class.getResource("/img/about.png")));
		btnNewButton.setToolTipText("Sobre:");
		btnNewButton.setBounds(378, 11, 32, 32);
		contentPane.add(btnNewButton);
	}//fim do construtor
}//fim do código 
