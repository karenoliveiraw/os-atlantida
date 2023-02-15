package views;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import models.DAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class Relatorios extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorios dialog = new Relatorios();
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
	public Relatorios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorios.class.getResource("/img/1622837_analytics_docs_documents_graph_pdf_icon (1).png")));
		setTitle("Relatórios");
		setBounds(100, 100, 450, 341);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setToolTipText("Relatórios de Clientes");
		btnNewButton.setIcon(new ImageIcon(
				Relatorios.class.getResource("/img/7632703_presentation_business_office_chart_icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relClientes();
			}
		});
		btnNewButton.setBounds(10, 70, 128, 128);
		getContentPane().add(btnNewButton);

		JButton btnRelatoriosPendentes = new JButton("");
		btnRelatoriosPendentes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatoriosPendentes.setToolTipText("Relatorios Pendentes");
		btnRelatoriosPendentes.setIcon(new ImageIcon(Relatorios.class.getResource("/img/report waiting.png")));
		btnRelatoriosPendentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relPendentes();
			}
		});
		btnRelatoriosPendentes.setBounds(159, 70, 128, 128);
		getContentPane().add(btnRelatoriosPendentes);

		JButton btnRelatoriosEntregues = new JButton("");
		btnRelatoriosEntregues.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatoriosEntregues.setToolTipText("Relatorios Entregues");
		btnRelatoriosEntregues.setIcon(new ImageIcon(Relatorios.class.getResource("/img/report done.png")));
		btnRelatoriosEntregues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relEntregues();
			}
		});
		btnRelatoriosEntregues.setBounds(301, 70, 128, 128);
		getContentPane().add(btnRelatoriosEntregues);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Relatorios.class.getResource("/img/icon120x120.png")));
		lblNewLabel.setBounds(311, 209, 120, 120);
		getContentPane().add(lblNewLabel);
		
		JPanel panelUsuarios = new JPanel();
		panelUsuarios.setLayout(null);
		panelUsuarios.setBackground(new Color(32, 178, 170));
		panelUsuarios.setBounds(-14, 286, 458, 20);
		getContentPane().add(panelUsuarios);
		
		JPanel panelUsuarios_1 = new JPanel();
		panelUsuarios_1.setLayout(null);
		panelUsuarios_1.setBackground(new Color(32, 178, 170));
		panelUsuarios_1.setBounds(-14, 0, 458, 20);
		getContentPane().add(panelUsuarios_1);

	}// fim do construtor

	/**
	 * criar um objeto para acessar o metodo conectar () da classe DAO
	 */

	DAO dao = new DAO();

	// relatórios de serviços (pendentes)
	private void relPendentes() {

		// criar objeto para construir a p�gina pdf
		Document document = new Document();
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("relpendentes.pdf"));
			document.open();
			// gerar o conte�do do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			// document.add (adicionar um par�grafo)
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("RELATÓRIO: Serviços pendentes "));
			document.add(new Paragraph(" "));
			// ... Demais conte�dos (imagem, tabela, gr�fico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("OS"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Cliente"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Placa"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Situação"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);

			// Acessar o banco de dados
			String relPendentes = "select tbos.os as OS, tbclientes.nome as Cliente, tbos.placa as Placa, tbos.Status_OS from tbos inner join tbclientes on tbos.id= tbclientes.id where status_os = 'Pendente'";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relPendentes);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));

				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o c�digo independente do resultado OK ou n�o
			document.close();
		}

		// abrir o documento que foi gerado no leitor padr�o de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("relpendentes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// relatórios de serviços ( entregues)
	private void relEntregues() {

		// criar objeto para construir a p�gina pdf
		Document document = new Document();
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("relentregues.pdf"));
			document.open();
			// gerar o conte�do do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			// document.add (adicionar um par�grafo)
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("RELATÓRIO: Serviços entregues "));
			document.add(new Paragraph(" "));
			// ... Demais conte�dos (imagem, tabela, gr�fico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("OS"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Cliente"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Placa"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Situação"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);

			// Acessar o banco de dados
			String relPendentes = "select tbos.os as OS, tbclientes.nome as Cliente, tbos.placa as Placa, tbos.Status_OS from tbos inner join tbclientes on tbos.id= tbclientes.id where status_os = 'Entregue'";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relPendentes);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));

				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o c�digo independente do resultado OK ou n�o
			document.close();
		}

		// abrir o documento que foi gerado no leitor padr�o de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("relentregues.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// relatórios de clientes
	private void relClientes() {

		// criar objeto para construir a p�gina pdf
		Document document = new Document();
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			
			PdfWriter.getInstance(document, new FileOutputStream("relclientes.pdf"));
			document.open();
			// gerar o conte�do do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			// document.add (adicionar um par�grafo)
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("RELATÓRIO: Clientes "));
			document.add(new Paragraph(" "));
			// ... Demais conte�dos (imagem, tabela, gr�fico, etc)
			PdfPTable tabela = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("Cliente"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Email"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Contato"));
		

			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);

			// Acessar o banco de dados
			String relPendentes = "select tbclientes.nome , tbclientes.email, tbclientes.fone from tbclientes";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relPendentes);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
				
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o c�digo independente do resultado OK ou n�o
			document.close();
		}

		// abrir o documento que foi gerado no leitor padr�o de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("relclientes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

}// fim do código
