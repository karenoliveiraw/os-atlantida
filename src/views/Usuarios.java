package views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import models.DAO;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

import Atxy2k.CustomTextField.RestrictedTextField;

import javax.swing.JPanel;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JTextField txtUsuario;
	private JTextField txtLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Usuarios() {
		getContentPane().setBackground(UIManager.getColor("Button.background"));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

			}
		});
		setResizable(false);
		setModal(true);
		setTitle("Usuarios");
		setBounds(100, 100, 450, 353);
		getContentPane().setLayout(null);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(336, 31, 46, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(314, 34, 46, 14);
		getContentPane().add(lblNewLabel);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(147, 120, 173, 20);
		getContentPane().add(txtUsuario);

		JLabel lblUsuarios = new JLabel("Usuario");
		lblUsuarios.setBounds(99, 103, 48, 48);
		getContentPane().add(lblUsuarios);

		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(147, 89, 173, 20);
		getContentPane().add(txtLogin);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(99, 89, 46, 14);
		getContentPane().add(lblLogin);

		btnCreate = new JButton("");
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnCreate.setToolTipText("Adicionar novo usu\u00E1rio");
		btnCreate.setIcon(new ImageIcon(Usuarios.class.getResource("/img/add.png")));
		btnCreate.setBounds(115, 204, 64, 64);
		getContentPane().add(btnCreate);

		btnUpdate = new JButton("");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//verificar se o checkbox 
				//para verificar se nao esta selecionado use NOT (!)
				if (!chckSenha.isSelected()) {
					alterarUsuarioSenha();
				} else {
					alterarUsuario();
				}
			}
		});
		btnUpdate.setToolTipText("Atualizar usu\u00E1rio");
		btnUpdate.setIcon(new ImageIcon(Usuarios.class.getResource("/img/update.png")));
		btnUpdate.setBounds(198, 204, 64, 64);
		getContentPane().add(btnUpdate);

		btnDelete = new JButton("");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnDelete.setToolTipText("Apagar usu\u00E1rio");
		btnDelete.setIcon(new ImageIcon(Usuarios.class.getResource("/img/delete.png")));
		btnDelete.setBounds(282, 204, 64, 64);
		getContentPane().add(btnDelete);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(99, 151, 46, 14);
		getContentPane().add(lblSenha);

		btnRead = new JButton("");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
			}
		});
		btnRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRead.setToolTipText("Pesquisar usu\u00E1rio");
		btnRead.setIcon(new ImageIcon(Usuarios.class.getResource("/img/search 28x28.png")));
		btnRead.setBounds(337, 99, 28, 28);
		getContentPane().add(btnRead);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(147, 151, 173, 20);
		getContentPane().add(txtPassword);

		JLabel lblNewLabel_1 = new JLabel("Perfil");
		lblNewLabel_1.setBounds(170, 53, 64, 14);
		getContentPane().add(lblNewLabel_1);

		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboPerfil.setBounds(210, 49, 64, 22);
		getContentPane().add(cboPerfil);

		chckSenha = new JCheckBox("Alterar senha");
		chckSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// fazer o check na caixa JcheckBox
				txtPassword.setEditable(true);
				txtPassword.setText(null);
				txtPassword.requestFocus();
				txtPassword.setBackground(Color.yellow);
			}
		});
		chckSenha.setVisible(false);
		chckSenha.setBounds(184, 178, 103, 23);
		getContentPane().add(chckSenha);
		
		panelUsuarios = new JPanel();
		panelUsuarios.setLayout(null);
		panelUsuarios.setBackground(new Color(32, 178, 170));
		panelUsuarios.setBounds(0, 294, 434, 20);
		getContentPane().add(panelUsuarios);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Usuarios.class.getResource("/img/icon120x120.png")));
		lblNewLabel_2.setBounds(314, 223, 120, 120);
		getContentPane().add(lblNewLabel_2);
		
		panelUsuarios_1 = new JPanel();
		panelUsuarios_1.setLayout(null);
		panelUsuarios_1.setBackground(new Color(32, 178, 170));
		panelUsuarios_1.setBounds(0, 0, 434, 20);
		getContentPane().add(panelUsuarios_1);
		
		
		RestrictedTextField login = new RestrictedTextField(txtLogin);
		login.setLimit(15);
		login.setOnlyText(true);
		RestrictedTextField usuario = new RestrictedTextField(txtUsuario);
		usuario.setLimit(15);
		RestrictedTextField senha = new RestrictedTextField(txtPassword);
		senha.setLimit(15);
		
		btnCreate.setEnabled(true);
		btnRead.setEnabled(true);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);

	}// fim do construtor

	/**
	 * criar um objeto para acessar o metodo conectar () da classe DAO
	 */

	DAO dao = new DAO();
	private JButton btnRead;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JPasswordField txtPassword;
	@SuppressWarnings("rawtypes")
	private JComboBox cboPerfil;
	private JCheckBox chckSenha;
	private JPanel panelUsuarios;
	private JLabel lblNewLabel_2;
	private JPanel panelUsuarios_1;

	// pesquisar Usuario

	void pesquisarUsuario() {
		// validação
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do login");
			txtLogin.requestFocus();
			
		} else {
			// System.out.println("Teste pesquisar");
			// Iniciar com a instru��o sql
			// ? � um par�metro a ser substitu�do
			String read = "select * from  tbusuarios where login = ?";
			try {
				// Estabelecer a conex�o ("abrir a porta da geladeira")
				Connection con = dao.conectar();
				// Preparar o c�digo sql para execu��o
				PreparedStatement pst = con.prepareStatement(read);
				// a linha abaixo substitui o ? pelo conte�do da caixa de texto txtNome, o 1 faz
				// refer�ncia a interroga��o
				pst.setString(1, txtLogin.getText());
				// Obter os dados do contato (resultado)
				ResultSet rs = pst.executeQuery();
				// verificar se existe um contato cadastrado
				// rs.next() significa ter um contato correspondente ao nome pesquisado
				if (rs.next()) {
					// setar as caixas de texto com o resultado da pesquisa
					txtLogin.setText(rs.getString(2));
					txtUsuario.setText(rs.getString(3));
					txtId.setText(rs.getString(1));
					txtPassword.setText(rs.getString(4));
					cboPerfil.setSelectedItem(rs.getString(5));
					// exibir a caixa checkbox
					chckSenha.setVisible(true);
					// desativar a edi��o da senha
					txtPassword.setEditable(false);
					// habilitar bot�es (alterar e excluir)
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
					btnCreate.setEnabled(false);



				} else {
					JOptionPane.showMessageDialog(null, "Usuário inexistente");
				
					limpar();
					txtLogin.requestFocus();
					
					btnCreate.setEnabled(true);
					btnRead.setEnabled(true);
					btnDelete.setEnabled(false);
					btnUpdate.setEnabled(false);
					

				}
				// fechar a conex�o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	} // fim do pesquisar Usuario

	/**
	 * Método respons�vel pelo cadastro de um novo usuario
	 */
	private void adicionarUsuario() {
		// captura segura de senha
		String capturaSenha = new String(txtPassword.getPassword());
		// validação de campos obrigatórios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuario");
			txtUsuario.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login ");
			txtLogin.requestFocus();
			
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira o perfil");
				cboPerfil.requestFocus();

		} else if (capturaSenha.length() == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a Senha ");
			txtLogin.requestFocus();

		} else {
			String create = "insert into tbusuarios (usuario, login, senha, perfil) values (?, ?, md5(?), ?)";
			try {
				// Abrir a conex�o
				Connection con = dao.conectar();
				// Preparar a query (substitui��o de par�metros)
				PreparedStatement pst = con.prepareStatement(create);

				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				

			

				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
					limpar();
					btnCreate.setEnabled(true);
					btnRead.setEnabled(true);
					btnDelete.setEnabled(false);
					btnUpdate.setEnabled(false);
					
				} else {
					JOptionPane.showMessageDialog(null, "ERRO - Usuário não adicionado");
				}
				limpar();
				// Encerrar a conex�o
				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Usuário não adicionado - Login existente");
				limpar();
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	/**
	 * M�todo respons�vel pela edi��o do contato
	 */
	private void alterarUsuario() {

		// valida��o de campos obrigat�rios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuario");
			txtUsuario.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login ");
			txtLogin.requestFocus();

		} else {
			// l�gica principal

			String update = "update tbusuarios set usuario = ?, login = ?, perfil = ? where id = ?";
			try {
				// Abrir a conex�o
				Connection con = dao.conectar();
				// Preparar a query (substitui��o de par�metros)
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtId.getText());

				// Executar a query e confirmar a inser��o no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					
					JOptionPane.showMessageDialog(null, "Dados do usuário atualizados com sucesso");
					limpar();
					btnCreate.setEnabled(true);
					btnRead.setEnabled(true);
					btnDelete.setEnabled(true);
					btnUpdate.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "ERRO - Dados do usuário não foram alterados");
					limpar();
				}
				// Encerrar a conex�o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M�todo para alterar usuario e senha
	 */
	private void alterarUsuarioSenha() {
		// captura segura de senha
		String capturaSenha = new String(txtPassword.getPassword());
		// valida��o de campos obrigat�rios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuario");
			txtUsuario.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login ");
			txtLogin.requestFocus();

		} else if (capturaSenha.length() == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a Senha ");
			txtLogin.requestFocus();
		} else {
			// l�gica principal

			String update = "update tbusuarios set usuario = ?, login = ?, senha = md5 (?), perfil = ? where id = ?";
			try {
				// Abrir a conex�o
				Connection con = dao.conectar();
				// Preparar a query (substitui��o de par�metros)
				PreparedStatement pst = con.prepareStatement(update);

				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtId.getText());

				// Executar a query e confirmar a inser��o no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do usuário atualizados com sucesso");
					limpar();
					btnCreate.setEnabled(true);
					btnRead.setEnabled(true);
					btnDelete.setEnabled(false);
					btnUpdate.setEnabled(false);

				} else {
					JOptionPane.showMessageDialog(null, "ERRO - Dados do usuário  não foram alterados");
					limpar();
				}
				// Encerrar a conex�o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M�todo usado para excluir um contato
	 */

	private void excluirUsuario() {

	
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste usuário?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);

		if (confirma == JOptionPane.YES_OPTION) {
			// logica principal
			String delete = "delete from tbusuarios where id = ?";
			try {
				// abrir conex�o
				Connection con = dao.conectar();
				
				// preparar a query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				
				// executar o comando sql e confirmar a exclus�o
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso!");
					limpar();
					btnCreate.setEnabled(true);
					btnRead.setEnabled(true);
					btnDelete.setEnabled(false);
					btnUpdate.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "ERRO na exclusãoo do usuario");
				}
				
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	/**
	 * M�todo usado para limpar os campos e setar os bot�es
	 */
	private void limpar() {
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtPassword.setText(null);
		txtPassword.setBackground(Color.WHITE);
		txtUsuario.requestFocus();
		btnCreate.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnRead.setEnabled(true);
		txtPassword.setEditable(true);
		cboPerfil.setSelectedItem("");
		chckSenha.setSelected(false); //desmarcar a caixa check
		chckSenha.setVisible(false);
		
		

	}
}// fim do codigo
