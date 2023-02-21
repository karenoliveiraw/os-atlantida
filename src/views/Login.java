package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import models.DAO;
import java.awt.Cursor;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblStatus;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/users 32x32.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAcessar = new JButton("");
		btnAcessar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAcessar.setToolTipText("Fazer login");
		btnAcessar.setIcon(new ImageIcon(Login.class.getResource("/img/accesslogin.png")));
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnAcessar.setBounds(167, 147, 32, 32);
		contentPane.add(btnAcessar);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(10, 155, 48, 48);
		contentPane.add(lblStatus);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/img/users 32x32.png")));
		lblNewLabel.setBounds(85, 47, 32, 32);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/img/password 32x32.png")));
		lblNewLabel_1.setBounds(85, 101, 32, 32);
		contentPane.add(lblNewLabel_1);

		txtLogin = new JTextField();
		txtLogin.setBounds(127, 58, 128, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(127, 110, 128, 20);
		contentPane.add(txtSenha);

		// uso da tecla <enter> junto com um bot�o (construtor)
		getRootPane().setDefaultButton(btnAcessar);
		
		panelUsuarios = new JPanel();
		panelUsuarios.setLayout(null);
		panelUsuarios.setBackground(new Color(32, 178, 170));
		panelUsuarios.setBounds(0, 0, 354, 20);
		contentPane.add(panelUsuarios);
		
		panelUsuarios_1 = new JPanel();
		panelUsuarios_1.setLayout(null);
		panelUsuarios_1.setBackground(new Color(32, 178, 170));
		panelUsuarios_1.setBounds(0, 222, 354, 20);
		contentPane.add(panelUsuarios_1);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/img/icon120x120.png")));
		lblNewLabel_2.setBounds(234, 147, 120, 120);
		contentPane.add(lblNewLabel_2);
		
		//validações
		
		RestrictedTextField senha = new RestrictedTextField(txtSenha);
		senha.setLimit(15);
		RestrictedTextField login = new RestrictedTextField(txtLogin);
		login.setLimit(15);
		login.setOnlyText(true);
		
		
		

	}// fim do construtor

	/**
	 * criar um objeto para acessar o metodo conectar () da classe DAO
	 */

	DAO dao = new DAO();
	private JButton btnAcessar;
	private JPanel panelUsuarios;
	private JPanel panelUsuarios_1;
	private JLabel lblNewLabel_2;

	/**
	 * M�todo respons�vel por verificar o status da conex�o com o banco
	 */
	private void status() {
		// System.out.println("teste - Janela Ativada");
		// uso da classe Connection (JDBC) para estabelecer a conex�o
		try {
			Connection con = dao.conectar();
			if (con == null) {
				// System.out.println("Erro de conex�o");
				lblStatus.setIcon(new ImageIcon(Usuarios.class.getResource("/img/dboff.png")));
			} else {
				// System.out.println("Banco conectado!");
				lblStatus.setIcon(new ImageIcon(Usuarios.class.getResource("/img/dbon.png")));
			}
			// Nunca esquecer de encerrar a conex�o
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do status

	private void logar() {

		// validação da senha (captura segura)
		String capturaSenha = new String(txtSenha.getPassword());
		// validação de campos obrigatórios
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o seu Login");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0) {
			JOptionPane.showMessageDialog(null, "Digite a sua Senha");
			txtSenha.requestFocus();
		} else {

			// lógica principal (pesquisar login e senha correspondente)

			String read = "select * from tbusuarios where login = ? and senha = md5(?)";
			try {
				// abrir conexão
				Connection con = dao.conectar();
				// preparar a query
				PreparedStatement pst = con.prepareStatement(read);
				// setar os argumentos (?)
				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				// executar a query e executar o login, se existir login e senha correspondente
				// no banco
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {

					// System.out.println("logar");
					Main main = new Main();
					// a linha abaixo
					String perfil = rs.getString(5);
					// comportamento de login em função do perfil
					if (perfil.equals("admin")) {
						main.setVisible(true);
						// alterar a label da tela principal (inserir nome do usuario no rodapé)
						// apoio ao entendimento da lógica
						// System.out.println(rs.getString(2));
						main.lblUsuario.setText(rs.getString(2));
						//habilitar os botoes 
						main.btnRelatorios.setEnabled(true);
						main.btnUsuarios.setEnabled(true);
						//alterar a cor do rodapé 
						main.panelUsuarios.setBackground(Color.black);
						// fechar o jframe
						this.dispose();
					} else {
						main.setVisible(true);
						// alterar a label da tela principal (inserir nome do usuario no rodapé)
						// apoio ao entendimento da lógica
						// System.out.println(rs.getString(2));
						main.lblUsuario.setText(rs.getString(2));
						// fechar o jframe
						this.dispose();
						//desabilitar os botões 
						
						main.btnRelatorios.setEnabled(false);
						main.btnUsuarios.setEnabled(false);
					}

				} else {
					JOptionPane.showMessageDialog(null, " Login e/ou senha inválido(s)");
					
				}
				// encerrar a conexão
				con.close();

			} catch (Exception e) {
				System.out.println(e);

			}

		}

	}
	
	void limpar () {
	txtLogin.setText(null);
	txtSenha.setText(null);
		
	}
	
	

}// fim do codigo
