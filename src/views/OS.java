package views;

import java.awt.EventQueue;
import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import models.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class OS extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtOs;
	private JTextField txtPlaca;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtCor;
	private JTextField txtAno;
	private JTextField txtKm;
	private JTextField txtDefeito;
	private JTextField txtDataSaida;
	private JTextField txtMecanico;
	private JTextField txtValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OS dialog = new OS();
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
	public OS() {
		setResizable(false);
		setModal(true);
		setTitle("Ordem de Serviço");
		setIconImage(Toolkit.getDefaultToolkit().getImage(OS.class.getResource("/img/icon120x120.png")));
		setBounds(100, 100, 608, 736);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("N° OS");
		lblNewLabel.setBounds(31, 62, 46, 14);
		getContentPane().add(lblNewLabel);

		txtOs = new JTextField();
		txtOs.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarOsReleased();
			}
		});
		txtOs.setColumns(10);
		txtOs.setBounds(70, 59, 137, 20);
		getContentPane().add(txtOs);

		btnCreate = new JButton("");
		btnCreate.setToolTipText("Adicionar uma ordem de serviço");
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setIcon(new ImageIcon(OS.class.getResource("/img/add.png")));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adicionar();
			}
		});
		btnCreate.setBounds(148, 606, 64, 64);
		getContentPane().add(btnCreate);

		btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteOs();
			}
		});
		btnDelete.setIcon(new ImageIcon(OS.class.getResource("/img/delete.png")));
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setToolTipText("Deletar uma ordem de serviço");
		btnDelete.setBounds(291, 607, 64, 64);
		getContentPane().add(btnDelete);

		JLabel lblNewLabel_12 = new JLabel("Data");
		lblNewLabel_12.setBounds(407, 26, 63, 14);
		getContentPane().add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Descrição do veículo:");
		lblNewLabel_13.setBounds(127, 246, 156, 14);
		getContentPane().add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("Placa");
		lblNewLabel_14.setBounds(127, 271, 35, 14);
		getContentPane().add(lblNewLabel_14);

		txtPlaca = new JTextField();
		txtPlaca.setBounds(160, 265, 56, 20);
		getContentPane().add(txtPlaca);
		txtPlaca.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Marca");
		lblNewLabel_15.setBounds(248, 271, 43, 14);
		getContentPane().add(lblNewLabel_15);

		txtMarca = new JTextField();
		txtMarca.setBounds(288, 265, 56, 20);
		getContentPane().add(txtMarca);
		txtMarca.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("Modelo");
		lblNewLabel_16.setBounds(368, 271, 46, 14);
		getContentPane().add(lblNewLabel_16);

		txtModelo = new JTextField();
		txtModelo.setBounds(414, 265, 56, 20);
		getContentPane().add(txtModelo);
		txtModelo.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("Cor");
		lblNewLabel_17.setBounds(127, 296, 34, 14);
		getContentPane().add(lblNewLabel_17);

		txtCor = new JTextField();
		txtCor.setBounds(160, 293, 57, 20);
		getContentPane().add(txtCor);
		txtCor.setColumns(10);

		JLabel lblNewLabel_18 = new JLabel("Ano");
		lblNewLabel_18.setBounds(248, 296, 25, 14);
		getContentPane().add(lblNewLabel_18);

		txtAno = new JTextField();
		txtAno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtAno.setBounds(284, 293, 56, 20);
		getContentPane().add(txtAno);
		txtAno.setColumns(10);

		JLabel lblNewLabel_19 = new JLabel("Km");
		lblNewLabel_19.setBounds(378, 296, 31, 14);
		getContentPane().add(lblNewLabel_19);

		txtKm = new JTextField();
		txtKm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtKm.setBounds(414, 293, 56, 20);
		getContentPane().add(txtKm);
		txtKm.setColumns(10);

		JLabel lblNewLabel_20 = new JLabel("Defeito:");
		lblNewLabel_20.setBounds(114, 354, 80, 14);
		getContentPane().add(lblNewLabel_20);

		txtDefeito = new JTextField();
		txtDefeito.setBounds(159, 331, 311, 60);
		getContentPane().add(txtDefeito);
		txtDefeito.setColumns(10);

		JLabel lblNewLabel_21 = new JLabel("Data de Saída");
		lblNewLabel_21.setBounds(212, 560, 84, 14);
		getContentPane().add(lblNewLabel_21);

		txtDataSaida = new JTextField();
		txtDataSaida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789/";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtDataSaida.setBounds(291, 557, 86, 20);
		getContentPane().add(txtDataSaida);
		txtDataSaida.setColumns(10);

		JLabel lblNewLabel_22 = new JLabel("Pagamento");
		lblNewLabel_22.setBounds(276, 516, 138, 14);
		getContentPane().add(lblNewLabel_22);

		JLabel lblNewLabel_23 = new JLabel("Mecânico");
		lblNewLabel_23.setBounds(100, 476, 64, 14);
		getContentPane().add(lblNewLabel_23);

		JLabel lblNewLabel_24 = new JLabel("Status:");
		lblNewLabel_24.setBounds(270, 476, 73, 14);
		getContentPane().add(lblNewLabel_24);

		txtMecanico = new JTextField();
		txtMecanico.setBounds(160, 473, 102, 20);
		getContentPane().add(txtMecanico);
		txtMecanico.setColumns(10);

		cboPagamento = new JComboBox<Object>();
		cboPagamento.setBackground(new Color(32, 178, 170));
		cboPagamento.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "", "Pix", "Dinheiro", "Crédito", "Débito", "Boleto Bancário" }));
		cboPagamento.setBounds(348, 512, 122, 22);
		getContentPane().add(cboPagamento);

		JLabel lblNewLabel_25 = new JLabel("Valor");
		lblNewLabel_25.setBounds(126, 516, 35, 14);
		getContentPane().add(lblNewLabel_25);

		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}

			}
		});
		txtValor.setBounds(160, 513, 64, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);

		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarOs();
			}
		});
		btnPesquisar.setToolTipText("Pesquisar uma ordem de serviço");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setIcon(new ImageIcon(OS.class.getResource("/img/search 28x28.png")));
		btnPesquisar.setBounds(212, 54, 28, 28);
		getContentPane().add(btnPesquisar);

		cboStatus = new JComboBox<Object>();
		cboStatus.setBackground(new Color(32, 178, 170));
		cboStatus.setModel(new DefaultComboBoxModel(new String[] { "", "Aguardando aprovação", "Aguardando mêcanico",
				"Aguardando retirada de veículo ", "Aguardando peças", "Entregue ao cliente" }));
		cboStatus.setBounds(314, 472, 156, 22);
		getContentPane().add(cboStatus);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(306, 59, 64, 14);
		getContentPane().add(lblCliente);

		txtPesquisarCliente = new JTextArea();
		txtPesquisarCliente.addMouseListener(new MouseAdapter() {

		});
		txtPesquisarCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}

			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "abcdefghijklmnopqrstuvwxyz";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}

			}
		});
		txtPesquisarCliente.setBounds(348, 54, 137, 20);
		getContentPane().add(txtPesquisarCliente);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarIdCli();
			}
		});
		scrollPane.setBounds(301, 87, 270, 105);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarIdCli();

			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setBounds(500, 59, 25, 14);
		getContentPane().add(lblNewLabel_1);

		txtIdCli = new JTextField();
		txtIdCli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtIdCli.setEditable(false);
		txtIdCli.setBounds(525, 56, 46, 20);
		getContentPane().add(txtIdCli);
		txtIdCli.setColumns(10);

		dateEntrada = new JDateChooser();
		dateEntrada.setEnabled(false);
		dateEntrada.setBounds(438, 23, 133, 20);
		getContentPane().add(dateEntrada);

		btnUpdate = new JButton("");
		btnUpdate.setToolTipText("Atualizar uma ordem de serviço");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setIcon(new ImageIcon(OS.class.getResource("/img/update.png")));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateOs();
			}
		});
		btnUpdate.setBounds(217, 606, 64, 64);
		getContentPane().add(btnUpdate);

		JPanel panelUsuarios_1 = new JPanel();
		panelUsuarios_1.setLayout(null);
		panelUsuarios_1.setBackground(new Color(32, 178, 170));
		panelUsuarios_1.setBounds(0, 0, 595, 20);
		getContentPane().add(panelUsuarios_1);

		JPanel panelUsuarios = new JPanel();
		panelUsuarios.setLayout(null);
		panelUsuarios.setBackground(new Color(32, 178, 170));
		panelUsuarios.setBounds(0, 677, 595, 20);
		getContentPane().add(panelUsuarios);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(OS.class.getResource("/img/icon120x120.png")));
		lblNewLabel_2.setBounds(471, 596, 124, 120);
		getContentPane().add(lblNewLabel_2);

		RestrictedTextField validarMarca = new RestrictedTextField(txtMarca);
		validarMarca.setOnlyText(true);
		validarMarca.setLimit(15);
		RestrictedTextField validarModelo = new RestrictedTextField(txtModelo);
		validarModelo.setLimit(15);

		RestrictedTextField validarCor = new RestrictedTextField(txtCor);
		validarCor.setLimit(15);
		validarCor.setOnlyText(true);

		RestrictedTextField ano = new RestrictedTextField(txtAno);
		ano.setLimit(4);

		RestrictedTextField valor = new RestrictedTextField(txtValor);
		valor.setLimit(5);

		RestrictedTextField km = new RestrictedTextField(txtKm);
		km.setLimit(5);

		RestrictedTextField validarMecanico = new RestrictedTextField(txtMecanico);
		validarMecanico.setOnlyText(true);
		validarMecanico.setAcceptSpace(true);
		validarMecanico.setLimit(30);

		btnImprimirOs = new JButton("");
		btnImprimirOs.setToolTipText("Imprimir uma ordem de serviço");
		btnImprimirOs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImprimirOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirOs();
			}
		});
		btnImprimirOs.setIcon(new ImageIcon(OS.class.getResource("/img/impressora64x64.png")));
		btnImprimirOs.setBounds(365, 606, 64, 64);
		getContentPane().add(btnImprimirOs);

		scrollPaneOs = new JScrollPane();
		scrollPaneOs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarOs();
			}
		});
		scrollPaneOs.setBounds(20, 87, 270, 105);
		getContentPane().add(scrollPaneOs);

		tableOs = new JTable();
		tableOs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarOs();
			}
		});
		scrollPaneOs.setViewportView(tableOs);

		JPanel panelUsuarios_1_1 = new JPanel();
		panelUsuarios_1_1.setLayout(null);
		panelUsuarios_1_1.setBackground(new Color(32, 178, 170));
		panelUsuarios_1_1.setBounds(0, 197, 595, 8);
		getContentPane().add(panelUsuarios_1_1);

		JLabel lblNewLabel_20_1 = new JLabel("Serviço");
		lblNewLabel_20_1.setBounds(114, 425, 80, 14);
		getContentPane().add(lblNewLabel_20_1);

		txtServico = new JTextField();
		txtServico.setColumns(10);
		txtServico.setBounds(159, 402, 311, 60);
		getContentPane().add(txtServico);

		panelUsuarios_1_2 = new JPanel();
		panelUsuarios_1_2.setLayout(null);
		panelUsuarios_1_2.setBackground(new Color(32, 178, 170));
		panelUsuarios_1_2.setBounds(0, 235, 595, 8);
		getContentPane().add(panelUsuarios_1_2);

		cboOrcamento = new JComboBox<Object>();
		cboOrcamento.setModel(new DefaultComboBoxModel(new String[] { "", "Orçamento", "Ordem de serviço" }));
		cboOrcamento.setBackground(new Color(32, 178, 170));
		cboOrcamento.setBounds(260, 209, 149, 22);
		getContentPane().add(cboOrcamento);

		JLabel lblOs = new JLabel("OS:");
		lblOs.setBounds(237, 215, 46, 14);
		getContentPane().add(lblOs);

		btnCreate.setEnabled(true);
		txtPesquisarCliente.setEditable(true);
		btnImprimirOs.setEnabled(false);
		txtOs.setEditable(true);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);
		txtIdCli.setEditable(true);
		btnPesquisar.setEnabled(true);

	}// fim do construtor

	/**
	 * criar um objeto para acessar o metodo conectar () da classe DAO
	 */

	DAO dao = new DAO();
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTable table;
	private JComboBox<Object> cboStatus;
	private JComboBox<Object> cboPagamento;
	private JTextArea txtPesquisarCliente;
	private JTextField txtIdCli;
	private JDateChooser dateEntrada;
	private JButton btnPesquisar;
	private JButton btnImprimirOs;
	private JTable tableOs;
	private JScrollPane scrollPaneOs;
	private JTextField txtServico;
	private JPanel panelUsuarios_1_2;
	private JComboBox<Object> cboOrcamento;

	private void pesquisarOsReleased() {
		String read5 = "select os as OS, modelo as Modelo, placa as Placa, status_os as Status from tbos where os like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read5);
			pst.setString(1, txtOs.getText() + "%"); // atencao "%
			ResultSet rs = pst.executeQuery();
			// uso da biblioteca rs2xml para "popular" a tabela
			tableOs.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void pesquisarCliente() {

		String read2 = "select id, nome, fone  from tbclientes where nome like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtPesquisarCliente.getText() + "%"); // atencao "%
			ResultSet rs = pst.executeQuery();
			// uso da biblioteca rs2xml para "popular" a tabela
			table.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método responsável por setar o ID do cliente na OS
	 */
	private void setarOs() {
		int setar = tableOs.getSelectedRow();
		txtOs.setText(tableOs.getModel().getValueAt(setar, 0).toString());
	}

	/**
	 * Método responsável por setar o ID do cliente na OS
	 */
	private void setarIdCli() {
		int setar = table.getSelectedRow();
		txtIdCli.setText(table.getModel().getValueAt(setar, 0).toString());
	}

	private void pesquisarOs() {

		// validação
		if (txtOs.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o número da ordem de serviço");
			txtOs.requestFocus();
		} else {

			String read = "select * from tbos where os = ?";
			try {

				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(read);

				pst.setString(1, txtOs.getText());

				ResultSet rs = pst.executeQuery();

				if (rs.next()) {

					// setar as caixas de texto com o resultado da pesquisa
					txtIdCli.setEditable(false);
					btnCreate.setEnabled(false);
					((DefaultTableModel) table.getModel()).setRowCount(0);

					txtOs.setText(rs.getString(1));
					String setarData = rs.getString(2);
					Date dataFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarData);
					dateEntrada.setDate(dataFormatada);
					txtPlaca.setText(rs.getString(3));
					txtMarca.setText(rs.getString(4));
					txtModelo.setText(rs.getString(5));
					txtCor.setText(rs.getString(6));
					txtAno.setText(rs.getString(7));
					txtKm.setText(rs.getString(8));
					cboOrcamento.setSelectedItem(rs.getString(9));
					txtServico.setText(rs.getString(10));
					txtDefeito.setText(rs.getString(11));
					txtMecanico.setText(rs.getString(12));
					cboStatus.setSelectedItem(rs.getString(13));
					txtValor.setText(rs.getString(14));
					txtDataSaida.setText(rs.getString(15));
					
					cboPagamento.setSelectedItem(rs.getString(16));
					txtIdCli.setText(rs.getString(17));
					txtIdCli.setEditable(false);
					txtPesquisarCliente.setEditable(false);
					btnImprimirOs.setEnabled(true);
					btnCreate.setEnabled(false);
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Ordem de Serviço inexistente");
					limpar();
					btnCreate.setEnabled(true);
					txtPesquisarCliente.setEditable(true);
					btnImprimirOs.setEnabled(false);
					txtOs.setEditable(false);
					txtOs.setText(null);
					btnDelete.setEnabled(false);
					btnUpdate.setEnabled(false);
					txtIdCli.setEditable(true);
					txtIdCli.setText(null);
					btnPesquisar.setEnabled(false);

					((DefaultTableModel) table.getModel()).setRowCount(0);
					((DefaultTableModel) tableOs.getModel()).setRowCount(0);

				}

				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	// Método Adicionar

	private void Adicionar() {
		/**
		 * Validacao
		 */
	
			
		if (cboOrcamento.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira se é um orçamento ou uma ordem de serviço ");
			cboOrcamento.requestFocus();
		} else if (txtPlaca.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a Placa");
			txtPlaca.requestFocus();

		} else if (txtMarca.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a Marca");
			txtMarca.requestFocus();
		} else if (txtModelo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Modelo");
			txtModelo.requestFocus();
		} else if (txtCor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a Cor");
			txtCor.requestFocus();
		} else if (txtAno.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Ano");
			txtAno.requestFocus();
		} else if (txtKm.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Km");
			txtKm.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Serviço");
			txtDefeito.requestFocus();
		} else if (txtMecanico.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o nome do Mecânico");
			txtMecanico.requestFocus();

		} else if (cboStatus.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira o Status do Serviço");

		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Valor");
			txtValor.requestFocus();

		} else if (txtIdCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o ID do Cliente ");
			txtIdCli.requestFocus();
		} else {

			String create = "insert into tbos (placa,marca,modelo,cor,ano,km, orcamento, servico,defeito, mecanico,status_os,valor,data_saida,forma_pagamento,id) values (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);

				pst.setString(1, txtPlaca.getText());
				pst.setString(2, txtMarca.getText());
				pst.setString(3, txtModelo.getText());
				pst.setString(4, txtCor.getText());
				pst.setString(5, txtAno.getText());
				pst.setString(6, txtKm.getText());
				pst.setString(7, cboOrcamento.getSelectedItem().toString());
				pst.setString(8, txtServico.getText());
				pst.setString(9, txtDefeito.getText());
				pst.setString(10, txtMecanico.getText());
				pst.setString(11, cboStatus.getSelectedItem().toString());
				pst.setString(12, txtValor.getText());
				pst.setString(13, txtDataSaida.getText());
				pst.setString(14, cboPagamento.getSelectedItem().toString());
				pst.setString(15, txtIdCli.getText());

				txtOs.setEditable(false);
				txtOs.setText(null);
				txtIdCli.setEditable(true);

				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Ordem de serviço -  cadastrada com sucesso!");

					btnCreate.setEnabled(true);
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
					btnPesquisar.setEnabled(true);
					txtOs.setEditable(true);
					txtIdCli.setEditable(true);
					btnImprimirOs.setEnabled(true);
					con.close();
				}

				this.showClientOrders(txtIdCli.getText());

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Ordem de serviço não adicionada - OS Duplicada");
				btnCreate.setEnabled(true);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				btnImprimirOs.setEnabled(false);
				limpar();
			}

			catch (Exception e2) {
				System.out.println(e2);
				limpar();
				JOptionPane.showConfirmDialog(null, e2);

			}
		}
	}

	private void showClientOrders(String clientId) {
		String read5 = "select os as OS, marca as Marca, placa as Placa, status_os as Status from tbos where id = ? order by os desc";

		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read5);
			pst.setString(1, clientId);
			ResultSet rs = pst.executeQuery();
			// uso da biblioteca rs2xml para "popular" a tabela
			tableOs.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void updateOs() {

		/**
		 * Validacao
		 */

		if (cboOrcamento.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira se é um orçamento ou uma ordem de serviço");
			cboOrcamento.requestFocus();
		} else if (txtPlaca.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a Placa");
			txtPlaca.requestFocus();
		} else if (txtMarca.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a Marca");
			txtMarca.requestFocus();
		} else if (txtModelo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Modelo");
			txtModelo.requestFocus();
		} else if (txtCor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira a Cor");
			txtCor.requestFocus();
		} else if (txtAno.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Ano");
			txtAno.requestFocus();
		} else if (txtKm.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Km");
			txtKm.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Serviço");
			txtDefeito.requestFocus();
		} else if (txtMecanico.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o nome do Mecânico");
			txtMecanico.requestFocus();

		} else if (cboStatus.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira o Status do Serviço");
			cboStatus.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Valor");
			txtValor.requestFocus();

		} else if (txtIdCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o ID do cliente ");
			txtIdCli.requestFocus();
		} else {

			String update = "update tbos set placa = ?, marca = ?, modelo = ?, cor = ?, ano = ?, km = ?, orcamento = ?, servico = ?, defeito = ?,  mecanico = ?, status_os = ?, valor = ?, data_saida = ?, forma_pagamento = ?, id = ?  where os = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtPlaca.getText());
				pst.setString(2, txtMarca.getText());
				pst.setString(3, txtModelo.getText());
				pst.setString(4, txtCor.getText());
				pst.setString(5, txtAno.getText());
				pst.setString(6, txtKm.getText());
				pst.setString(7, cboOrcamento.getSelectedItem().toString());
				pst.setString(8, txtServico.getText());
				pst.setString(9, txtDefeito.getText());
				pst.setString(10, txtMecanico.getText());
				pst.setString(11, cboStatus.getSelectedItem().toString());
				pst.setString(12, txtValor.getText());
				pst.setString(13, txtDataSaida.getText());
				pst.setString(14, cboPagamento.getSelectedItem().toString());
				pst.setString(15, txtIdCli.getText());
				pst.setString(16, txtOs.getText());

				// Executar a query e confirmar a inser��o no banco

				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {

					limpar();
					JOptionPane.showMessageDialog(null, "Ordem de Serviço - atualizada com sucesso");
					btnCreate.setEnabled(true);
					btnUpdate.setEnabled(false);
					btnDelete.setEnabled(false);
					btnPesquisar.setEnabled(true);
					btnImprimirOs.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "ERRO - OS já existente");
					limpar();
					txtIdCli.setEnabled(true);
					btnCreate.setEnabled(true);
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
					limpar();

				}

				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "ATENÇÃO! OS: Duplicada");

			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	public void deleteOs() {

		// Validacao
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Exclusão desta OS?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {

			String delete = "delete from tbos where os = ?";
			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// preparar a query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtOs.getText());
				// executar o comando sql e confirmar a exclusão
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "OS excluida com sucesso");
					txtOs.setEnabled(true);
					btnCreate.setEnabled(true);
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);

				}
				// Encerrar a conexão
				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				limpar();
				JOptionPane.showMessageDialog(null, "ATENÇÃO! O relacionado a uma OS.");
				txtOs.setEnabled(true);
				txtOs.requestFocus();
				btnCreate.setEnabled(true);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				btnImprimirOs.setEnabled(true);

			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	// impressao os

	private void imprimirOs() {

		Document document = new Document();

		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("ordemdeservico.pdf"));
			document.open();

			// Acessar o banco de dados
			String relImprimirOs = "select tbos.os, date_format(tbos.data_os,'%d/%m/%Y - %H:%i'),tbclientes.nome, tbclientes.fone, tbos.servico, tbos.modelo , tbos.placa ,  tbos.forma_pagamento , tbos.valor, tbos.data_saida from tbos inner join tbclientes on tbos.id= tbclientes.id where os like ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relImprimirOs);
				pst.setString(1, txtOs.getText() + "%"); // atencao "%
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					// document.add(new Paragraph(" "));
					// Date data = new Date();
					// DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
					// document.add(new Paragraph(new Paragraph(formatador.format(data))));
					document.add(new Paragraph(" "));
					Paragraph ordemdeservico = new Paragraph("Ordem de Serviço: " + rs.getString(1));
					ordemdeservico.setAlignment(Element.ALIGN_CENTER);
					document.add(ordemdeservico);
					document.add(new Paragraph(" "));
					Paragraph dataentrada = new Paragraph("Data de entrada: " + rs.getString(2));
					dataentrada.setAlignment(Element.ALIGN_LEFT);
					document.add(dataentrada);
					document.add(new Paragraph(" "));
					Paragraph cliente = new Paragraph("Cliente: " + rs.getString(3));
					cliente.setAlignment(Element.ALIGN_LEFT);
					document.add(cliente);
					document.add(new Paragraph(" "));
					Paragraph telefone = new Paragraph("Telefone: " + rs.getString(4));
					telefone.setAlignment(Element.ALIGN_LEFT);
					document.add(telefone);
					document.add(new Paragraph(" "));

					Paragraph servico = new Paragraph("Serviço: " + rs.getString(5));
					servico.setAlignment(Element.ALIGN_LEFT);
					document.isInline();
					document.add(servico);
					document.add(new Paragraph(" "));

					Paragraph modelo = new Paragraph("Modelo: " + rs.getString(6));
					modelo.setAlignment(Element.ALIGN_LEFT);
					document.add(modelo);
					document.add(new Paragraph(" "));

					Paragraph placa = new Paragraph("Placa: " + rs.getString(7));
					placa.setAlignment(Element.ALIGN_LEFT);
					document.add(placa);
					document.add(new Paragraph(" "));

					Paragraph pagamento = new Paragraph("Forma de pagamento: " + rs.getString(8));
					pagamento.setAlignment(Element.ALIGN_LEFT);
					document.add(pagamento);
					document.add(new Paragraph(" "));

					Paragraph valor = new Paragraph("Valor: " + rs.getString(9));
					valor.setAlignment(Element.ALIGN_LEFT);
					document.add(valor);
					document.add(new Paragraph(" "));

					Paragraph datasaida = new Paragraph("Data de Saída: " + rs.getString(10));
					datasaida.setAlignment(Element.ALIGN_LEFT);
					document.add(datasaida);
					document.add(new Paragraph(" "));

					Image imagem = Image.getInstance("C:\\Users\\Karen\\Downloads\\atlantidaom/logo.png");
					imagem.scaleToFit(250, 200);
					imagem.setAbsolutePosition(200, 700);

					document.add(imagem);
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			document.close();
		}

		// abrir o documento que foi gerado no leitor padr�o de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("ordemdeservico.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void limpar() {
		btnImprimirOs.setEnabled(false);
		btnCreate.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		txtIdCli.setText(null);
		txtOs.setText(null);
		txtPlaca.setText(null);
		txtMarca.setText(null);
		txtModelo.setText(null);
		txtCor.setText(null);
		txtAno.setText(null);
		txtKm.setText(null);
		txtDefeito.setText(null);
		txtServico.setText(null);
		txtValor.setText(null);
		txtMecanico.setText(null);
		txtDataSaida.setText(null);
		cboStatus.setSelectedItem(null);
		cboOrcamento.setSelectedItem(null);
		cboPagamento.setSelectedItem(null);
		dateEntrada.setDate(null);
		((DefaultTableModel) table.getModel()).setRowCount(0);
		((DefaultTableModel) tableOs.getModel()).setRowCount(0);

	}
}// fim do código
