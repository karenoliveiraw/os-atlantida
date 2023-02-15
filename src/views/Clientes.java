package views;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import models.DAO;
import net.proteanit.sql.DbUtils;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clientes extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCpf;
	private JTextField txtNomecliente;
	private JTextField txtFone;
	private JTextField txtWhatsapp;
	private JTextField txtEmail;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
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
	public Clientes() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
		setResizable(false);
		setModal(true);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setTitle("Clientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/clientes].png")));
		setBounds(100, 100, 611, 541);
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(172, 187, 30, 14);
		getContentPane().add(lblId);

		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setIcon(new ImageIcon(Clientes.class.getResource("/img/search 28x28.png")));
		btnBuscar.setToolTipText("Buscar ID");
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBounds(260, 180, 30, 28);
		getContentPane().add(btnBuscar);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(329, 224, 66, 14);
		getContentPane().add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setToolTipText("Coloque o CPF");
		txtCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validacao (aceita somente os caracteres da String)
				String caracteres = "0987654321./-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCpf.setBounds(354, 221, 162, 20);
		getContentPane().add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblNomeCliente = new JLabel(" Cliente");
		lblNomeCliente.setBounds(48, 224, 78, 14);
		getContentPane().add(lblNomeCliente);

		txtNomecliente = new JTextField();

		txtNomecliente.setToolTipText("Coloque o nome do Cliente");
		txtNomecliente.setBounds(107, 221, 205, 20);
		getContentPane().add(txtNomecliente);
		txtNomecliente.setColumns(10);

		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(25, 269, 46, 14);
		getContentPane().add(lblFone);

		txtFone = new JTextField();
		txtFone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validacao (aceita somente os caracteres da String)
				String caracteres = "0987654321-()";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtFone.setToolTipText("Coloque o telefone");
		txtFone.setBounds(63, 266, 86, 20);
		getContentPane().add(txtFone);
		txtFone.setColumns(10);

		JLabel lblWhatsapp = new JLabel("Whatsapp");
		lblWhatsapp.setBounds(159, 269, 65, 14);
		getContentPane().add(lblWhatsapp);

		txtWhatsapp = new JTextField();
		txtWhatsapp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (aceita somente os caracteres da String)
				String caracteres = "0987654321-()";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtWhatsapp.setToolTipText("Coloque o Whatsapp");
		txtWhatsapp.setBounds(228, 266, 78, 20);
		getContentPane().add(txtWhatsapp);
		txtWhatsapp.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(316, 269, 46, 14);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "AaBbcCdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890@.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		txtEmail.setToolTipText("Coloque o e-mail");
		txtEmail.setBounds(354, 266, 162, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(420, 315, 46, 14);
		getContentPane().add(lblCep);

		txtCep = new JTextField();
		txtCep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// valida��o (aceita somente os caracteres da String)
				String caracteres = "0987654321-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCep.setToolTipText("Coloque o CEP");
		txtCep.setBounds(453, 312, 79, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);

		JLabel lblendereco = new JLabel("Endere\u00E7o");
		lblendereco.setBounds(10, 315, 58, 14);
		getContentPane().add(lblendereco);

		txtEndereco = new JTextField();
		txtEndereco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}
		});

		txtEndereco.setToolTipText("Coloque o Endereço");
		txtEndereco.setBounds(63, 312, 237, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNumero = new JLabel("N\u00FAmero");
		lblNumero.setBounds(309, 315, 53, 23);
		getContentPane().add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		txtNumero.setToolTipText("Coloque o Número ");
		txtNumero.setBounds(354, 312, 46, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(298, 355, 96, 14);
		getContentPane().add(lblComplemento);

		txtComplemento = new JTextField();
		txtComplemento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

		txtComplemento.setToolTipText("Coloque o Complemento");
		txtComplemento.setBounds(379, 352, 78, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(10, 351, 46, 20);
		getContentPane().add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}
		});

		txtBairro.setToolTipText("Coloque o Bairro");
		txtBairro.setBounds(61, 351, 86, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(155, 351, 50, 22);
		getContentPane().add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}
		});

		txtCidade.setToolTipText("Coloque a Cidade");
		txtCidade.setBounds(195, 353, 101, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(463, 353, 46, 14);
		getContentPane().add(lblUf);

		txtId = new JTextField();
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// valida��o (aceita somente os caracteres da String)
				String caracteres = "0987654321./-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtId.setToolTipText("Coloque ID");
		txtId.setBounds(195, 184, 58, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		cboUf = new JComboBox<Object>();
		cboUf.setBackground(new Color(102, 205, 170));
		cboUf.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(486, 347, 46, 22);
		getContentPane().add(cboUf);

		btnUpdate = new JButton("");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setToolTipText("Atualizar Cliente");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();

			}
		});
		btnUpdate.setIcon(new ImageIcon(Clientes.class.getResource("/img/update.png")));
		btnUpdate.setBounds(258, 400, 64, 64);
		getContentPane().add(btnUpdate);

		btnDelete = new JButton("");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setToolTipText("Excluir Cliente");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnDelete.setIcon(new ImageIcon(Clientes.class.getResource("/img/delete.png")));
		btnDelete.setBounds(352, 400, 64, 64);
		getContentPane().add(btnDelete);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(130, 43, 78, 14);
		getContentPane().add(lblCliente);

		txtPesquisar = new JTextArea();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(189, 38, 242, 22);
		getContentPane().add(txtPesquisar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 575, 105);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarIdCli();
			}
		});
		scrollPane.setViewportView(table);

		// Uso da biblioteca atxy2k para restringir o maximo de caracteres

		// txtId
		RestrictedTextField id = new RestrictedTextField(txtId);
		id.setLimit(7);
		// txtCnpj
		RestrictedTextField cnpj = new RestrictedTextField(txtCpf);
		cnpj.setLimit(20);

		// txtRazaosocial
		RestrictedTextField NomeCli = new RestrictedTextField(txtNomecliente);
		NomeCli.setLimit(60);
		NomeCli.setOnlyText(true);
		NomeCli.setAcceptSpace(true);
		NomeCli.setLimit(50);

		// txtCep
		RestrictedTextField CEP = new RestrictedTextField(txtCep);
		CEP.setLimit(10);
		// txtEndereco
		RestrictedTextField Endereco = new RestrictedTextField(txtEndereco);

		Endereco.setOnlyText(true);
		Endereco.setAcceptSpace(true);
		Endereco.setLimit(50);

		// txtNumero
		RestrictedTextField NumeroCasa = new RestrictedTextField(txtNumero);
		NumeroCasa.setLimit(6);
		// txtComplemento
		RestrictedTextField Complemento = new RestrictedTextField(txtComplemento);
		Complemento.setLimit(20);

		// txtBairro
		RestrictedTextField Bairro = new RestrictedTextField(txtBairro);
		Bairro.setLimit(30);
		Bairro.setOnlyText(true);
		Bairro.setAcceptSpace(true);
	

		// txtCidade
		RestrictedTextField Cidade = new RestrictedTextField(txtCidade);
		Cidade.setLimit(40);
		Cidade.setOnlyText(true);
		Cidade.setAcceptSpace(true);

		// txtFone
		RestrictedTextField Fone = new RestrictedTextField(txtFone);
		Fone.setLimit(15);
		// txtWhatsapp
		RestrictedTextField Whatsapp = new RestrictedTextField(txtWhatsapp);
		Whatsapp.setLimit(15);

		RestrictedTextField Email = new RestrictedTextField(txtEmail);
		Email.setLimit(50);

		lblStatus = new JLabel("");
		lblStatus.setIcon(null);
		lblStatus.setToolTipText("Status");
		lblStatus.setBounds(605, 348, 16, 16);
		getContentPane().add(lblStatus);

		btnCreate = new JButton("");
		btnCreate.setToolTipText("Adicionar Cliente");
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setIcon(new ImageIcon(Clientes.class.getResource("/img/add.png")));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnCreate.setBounds(169, 400, 64, 64);
		getContentPane().add(btnCreate);

		btnPesquisar = new JButton("");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();

			}
		});
		btnPesquisar.setIcon(new ImageIcon(Clientes.class.getResource("/img/search 28x28.png")));
		btnPesquisar.setToolTipText("Buscar ID");
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setBounds(542, 308, 28, 28);
		getContentPane().add(btnPesquisar);

		panelUsuarios = new JPanel();
		panelUsuarios.setLayout(null);
		panelUsuarios.setBackground(new Color(32, 178, 170));
		panelUsuarios.setBounds(0, 481, 595, 20);
		getContentPane().add(panelUsuarios);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Clientes.class.getResource("/img/icon120x120.png")));
		lblNewLabel.setBounds(471, 403, 124, 120);
		getContentPane().add(lblNewLabel);

		panelUsuarios_1 = new JPanel();
		panelUsuarios_1.setLayout(null);
		panelUsuarios_1.setBackground(new Color(32, 178, 170));
		panelUsuarios_1.setBounds(0, 0, 595, 20);
		getContentPane().add(panelUsuarios_1);

	}// fim do construtor

	DAO dao = new DAO();

	private JButton btnBuscar;

	private JComboBox<Object> cboUf;
	private JTextArea txtPesquisar;
	private JTable table;
	private JLabel lblStatus;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnCreate;
	private JButton btnPesquisar;
	private JPanel panelUsuarios;
	private JLabel lblNewLabel;
	private JPanel panelUsuarios_1;
	
	/**
	 * Método responsável por setar o ID do cliente na OS
	 */
	private void setarIdCli() {
		int setar = table.getSelectedRow();
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
	}

	/**
	 * buscarCep
	 */
	// cep

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			org.dom4j.Document documento = xml.read(url);
			Element root = ((org.dom4j.Document) documento).getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}

				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
				}
				if (resultado.equals("1")) {
					lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
				} else {
					JOptionPane.showMessageDialog(null, "CEP não encontrado");

				}
			}
// setar o campo endereco
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void pesquisarCliente() {
		String read2 = "select id as ID, nome as cliente, fone, nome as contato from tbclientes where nome like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtPesquisar.getText() + "%"); // atencao "%
			ResultSet rs = pst.executeQuery();
			// uso da biblioteca rs2xml para "popular" a tabela
			table.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void pesquisar() {

		/**
		 * validacao
		 */
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Id do cliente");
			txtId.requestFocus();
		} else {
			String read = "select * from tbclientes where id = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtId.getText());
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					txtId.setText(rs.getString(1));
					txtNomecliente.setText(rs.getString(2));

					txtCpf.setText(rs.getString(3));
					txtFone.setText(rs.getString(4));
					txtWhatsapp.setText(rs.getString(5));

					txtCep.setText(rs.getString(6));
					txtEmail.setText(rs.getString(7));
					txtEndereco.setText(rs.getString(8));
					txtNumero.setText(rs.getString(9));
					txtComplemento.setText(rs.getString(10));
					txtBairro.setText(rs.getString(11));
					txtCidade.setText(rs.getString(12));
					cboUf.setSelectedItem(rs.getString(13));

					/**
					 * habilitar botoes update e delete
					 */
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
					btnCreate.setEnabled(false);
					txtId.setEnabled(false);

				} else {

					JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
					btnBuscar.setEnabled(false);
					txtId.setEditable(false);
					btnCreate.setEnabled(true);
					btnUpdate.setEnabled(false);
					btnDelete.setEnabled(false);
					txtNomecliente.requestFocus();
					limpar();
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public void adicionar() {
		/**
		 * Validacao
		 */

		if (txtNomecliente.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Nome do Cliente");
			txtNomecliente.requestFocus();

		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CEP");
			txtCep.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CPF");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o endereco");
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o numero");
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o bairro");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a cidade");
			txtCidade.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o telefone");
			txtFone.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o e-mail");
			txtEmail.requestFocus();

		} else {
			String create = "insert into tbclientes (nome,cpf,fone, whatsapp,cep ,email, endereco, numero, complemento, bairro, cidade, uf) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNomecliente.getText());
				pst.setString(2, txtCpf.getText());
				pst.setString(3, txtFone.getText());
				pst.setString(4, txtWhatsapp.getText());
				pst.setString(5, txtCep.getText());
				pst.setString(6, txtEmail.getText());
				pst.setString(7, txtEndereco.getText());
				pst.setString(8, txtNumero.getText());
				pst.setString(9, txtComplemento.getText());
				pst.setString(10, txtBairro.getText());
				pst.setString(11, txtCidade.getText());
				pst.setString(12, cboUf.getSelectedItem().toString());

				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
					btnCreate.setEnabled(true);
					txtId.setEnabled(true);
					btnBuscar.setEnabled(true);
					limpar();
					con.close();
				}

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "ATENÇÃO: Cliente Duplicado");
				limpar();
				txtCpf.setText(null);
				txtCpf.requestFocus();

			}

			catch (Exception e2) {
				System.out.println(e2);
				// JOptionPane.showConfirmDialog(null, e2);
				limpar();
			}
		}
	}

	public void atualizar() {

		// Valida�ao
		if (txtNomecliente.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o nome do Cliente");
			txtNomecliente.requestFocus();

		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CPF");
			txtCpf.requestFocus();

		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o CEP");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o endereco");
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o numero");
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o bairro");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a cidade");
			txtCidade.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a nome do contato");
			txtFone.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a nome do contato");
			txtEmail.requestFocus();
		} else {

			// Logica Principal
			String update = "update tbclientes set nome = ?, cpf = ?, fone = ?, whatsapp = ?, cep = ?, email = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ? where id = ?";

			try {
				// Abrir a conexao
				Connection con = dao.conectar();
				// Preparar a query (substituicao de parametros)
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtNomecliente.getText());

				pst.setString(2, txtCpf.getText());
				pst.setString(3, txtFone.getText());
				pst.setString(4, txtWhatsapp.getText());

				pst.setString(5, txtCep.getText());
				pst.setString(6, txtEmail.getText());
				pst.setString(7, txtEndereco.getText());
				pst.setString(8, txtNumero.getText());
				pst.setString(9, txtComplemento.getText());
				pst.setString(10, txtBairro.getText());
				pst.setString(11, txtCidade.getText());
				pst.setString(12, cboUf.getSelectedItem().toString());

				pst.setString(13, txtId.getText());
				// Executar a query e atualizar as informacoes no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Informações do cliente Atualizados com Sucesso.");
					txtId.setEnabled(true);
					btnCreate.setEnabled(true);
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);

					limpar();
				}

				// Encerrar a conexao
				con.close();
			}

			catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Cliente não Atualizado - Campo Duplicado");
				txtCpf.setText(null);
				txtCpf.requestFocus();

			}

			catch (Exception e2) {
				System.out.println(e2);
			 JOptionPane.showConfirmDialog(null, e2);
				limpar();
			}
		}
	}

	public void excluir() {

		// Validacao
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Exclusão deste Cliente?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {

			String delete = "delete from tbclientes where id = ?";
			try {
				// abrir a conexao
				Connection con = dao.conectar();
				// preparar a query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				// executar o comando sql e confirmar a exclusão
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
					txtId.setEnabled(true);
					btnCreate.setEnabled(true);
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);

				}
				// Encerrar a conexao
				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				limpar();
				JOptionPane.showMessageDialog(null, "ATENÇÃO! Cliente relacionado a uma OS.");
				txtId.setEnabled(true);
				txtId.requestFocus();
				btnCreate.setEnabled(true);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);

			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	public void limpar() {
		txtPesquisar.setText(null);
		txtId.setText(null);
		txtCpf.setText(null);

		txtNomecliente.setText(null);

		txtCep.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem("");

		txtFone.setText(null);
		txtWhatsapp.setText(null);

		txtEmail.setText(null);

		((DefaultTableModel) table.getModel()).setRowCount(0);

	}
}// fim do codigo
