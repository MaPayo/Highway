package comentario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import plegaria.TPlegaria;
import presentacion.IGUI;
import controlador.Controlador;
import controlador.Evento;
import controlador.TOAModificar;

public class GUIComentarioImp extends IGUIComentario {

	Controlador cont;
	JPanel contentPane;

	public GUIComentarioImp() {
		super();
		initGUI();
	}

	public void initGUI() {
		super.setSize(500, 400);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void actualizar(Evento evento, Object transfer) {
		switch (evento) {
		case CREARCOMENTARIOOK:
			JOptionPane.showMessageDialog(null, "Comentario guardado.");
			mostrarComentario((TComentario) transfer);
			break;
		case CREARCOMENTARIOFAIL:
			JOptionPane.showMessageDialog(null, "No se ha podido comentar.");
			dispose();
			break;
		case CUMPLIRCOMENTARIOOK:
			JOptionPane.showMessageDialog(null, "Se ha cumplido el comentario.");
			mostrarComentario((TComentario)transfer);
			break;
		case CUMPLIRCOMENTARIOFAIL:
			JOptionPane.showMessageDialog(null, "No se ha podido cumplir el comentario.");
			dispose();
			break;
		case MODIFICARCOMENTARIOOK:
			JOptionPane.showMessageDialog(null, "Se ha modificado el comentario.");
			mostrarComentario((TComentario) transfer);
			break;
		case MOSTRARCOMENTARIOOK:
			mostrarComentario((TComentario) transfer);
			break;
		case MOSTRARCOMENTARIOFAIL:
			JOptionPane.showMessageDialog(null, "No se ha podido mostar el comentario.");
			dispose();
			break;
		case MODIFICARCOMENTARIOFAIL:
			JOptionPane.showMessageDialog(null, "No se ha podido modificar el comnetario.");
			dispose();
			break;
		case MOSTRARCOMENTARIOPORCREYENTEOK:
			mostrarPorCreyente((TOAMostrarComentarios) transfer);
			break;
		case MOSTRARCOMENTARIOPORCREYENTEFAIL:
			dispose();
			JOptionPane.showMessageDialog(null, "No se han podido mostrar los comentarios de este creyente.");
			break;
		case MOSTRARCOMENTARIOPORPLEGARIAOK:
			mostrarPorPlegaria((TOAMostrarComentarios) transfer);
			break;
		case MOSTRARCOMENTARIOPORPLEGARIAFAIL:
			dispose();
			JOptionPane.showMessageDialog(null, "No se han podidmo mostrar los comentarios de esta plegaria.");
			break;
		case ABRIRCREARCOMENTARIO:
			abrirCrearComentario((TPlegaria) transfer);
			break;
		case ABRIRMODIFICARCOMENTARIO:
			abrirModificarComentario((TComentario) transfer);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Evento no contemplado en Comentarios");
			break;
		}
	}

	protected void mostrarComentario(TComentario comentario) {
		JTextField textField;
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		super.add(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow]", "[][][][grow]"));

		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblContenido, "cell 1 2,alignx left");

		JTextPane textPane = new JTextPane();
		if (comentario.isCumplido()) {
			textPane.setBackground(new Color(152, 251, 152));
		}
		textPane.setForeground(SystemColor.desktop);
		textPane.setText(comentario.getComentario());// Mete el cuerpo en su
														// sitio
		textPane.setEditable(false);
		contentPane.add(textPane, "cell 1 3,grow");

		JPanel botones = new JPanel();
		contentPane.add(botones, BorderLayout.EAST);
		botones.setBackground(SystemColor.inactiveCaption);
		botones.setLayout(new MigLayout("", "[]", "[][][][]"));

		JButton btnCumplir = new JButton("Cumplir Comentario");
		if (comentario.isCumplido()) {
			btnCumplir.setEnabled(false);
		}
		btnCumplir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.CUMPLIRCOMENTARIO, comentario.getIDCo());
				dispose();
			}
		});
		botones.add(btnCumplir, "cell 0 0,growx");

		JButton btnModificarP = new JButton("Modificar");
		if(comentario.isCumplido()){
			btnModificarP.setEnabled(false);
		}
		btnModificarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				dispose();
				cont.accion(Evento.ABRIRMODIFICARCOMENTARIO, comentario);
				
			}
		});
		botones.add(btnModificarP, "cell 0 1,growx");

		JButton btnVolver = new JButton("Cerrar");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		botones.add(btnVolver, "cell 0 2,growx");
		contentPane.revalidate();

	}

	protected void mostrarPorCreyente(TOAMostrarComentarios lista) {

		JTable table;// introducir en la funcion

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setLayout(new BorderLayout(0, 0));
		super.add(contentPane);

		JPanel panel = new JPanel();// esto se sustituye por el panel de fuera
		panel.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panel, BorderLayout.EAST);

		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblMostrandoLosComentarios = new JLabel("Mostrando los comentarios del creyente:");
		lblMostrandoLosComentarios.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblMostrandoLosComentarios = new GridBagConstraints();
		gbc_lblMostrandoLosComentarios.insets = new Insets(0, 0, 5, 0);
		gbc_lblMostrandoLosComentarios.gridx = 0;
		gbc_lblMostrandoLosComentarios.gridy = 0;
		panel_1.add(lblMostrandoLosComentarios, gbc_lblMostrandoLosComentarios);

		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel_1.add(label, gbc_label);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		// modelo.addColumn("T\u00EDtulo");
		scrollPane.setViewportView(table);
		table.setCellSelectionEnabled(true);
		Object filas[] = new Object[2];
		modelo.addColumn("Título");
		modelo.addColumn("Comentario");
		//for (HashMap.Entry<String, TComentario> entry : comentarios.entrySet()) {
		for (int i = 0; i < lista.getComentarios().size(); i++) {
			
			filas[0] = lista.getInformacion().get(i);
			filas[1] = lista.getComentarios().get(i).getComentario();
			modelo.addRow(filas);
		}
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				ArrayList<TComentario> tcomentarios = new ArrayList<TComentario>(lista.getComentarios());
				TComentario tComentario = tcomentarios.get(fila);
				// int id = lista.get(fila).getiDP();
				cont = Controlador.getInstancia();
				cont.accion(Evento.MOSTRARCOMENTARIO, tComentario.getIDCo());
				dispose();
			}
		});

		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		contentPane.revalidate();

	}

	protected void mostrarPorPlegaria(TOAMostrarComentarios lista) {

		JTable table;// introducir en la funcion

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setLayout(new BorderLayout(0, 0));
		super.add(contentPane);

		JPanel panel = new JPanel();// esto se sustituye por el panel de fuera
		panel.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panel, BorderLayout.EAST);

		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblMostrandoLosComentarios = new JLabel("Mostrando los comentarios de la plegaria:");
		lblMostrandoLosComentarios.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblMostrandoLosComentarios = new GridBagConstraints();
		gbc_lblMostrandoLosComentarios.insets = new Insets(0, 0, 5, 0);
		gbc_lblMostrandoLosComentarios.gridx = 0;
		gbc_lblMostrandoLosComentarios.gridy = 0;
		panel_1.add(lblMostrandoLosComentarios, gbc_lblMostrandoLosComentarios);

		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel_1.add(label, gbc_label);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel_1.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		// modelo.addColumn("T\u00EDtulo");
		scrollPane.setViewportView(table);
		table.setCellSelectionEnabled(true);
		String filas[] = new String[2];
		modelo.addColumn("Nombre");
		modelo.addColumn("Comentario");

			for (int i = 0; i < lista.getComentarios().size(); i++) {
				filas[0] = lista.getInformacion().get(i);
				filas[1] = lista.getComentarios().get(i).getComentario();
				modelo.addRow(filas);
				
			}// modelo.addRow(filas);
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int fila = table.rowAtPoint(e.getPoint());
					ArrayList<TComentario> tcomentarios = new ArrayList<TComentario>(lista.getComentarios());
					TComentario tComentario = tcomentarios.get(fila);

					// int id = lista.get(fila).getiDP();
					cont = Controlador.getInstancia();
					cont.accion(Evento.MOSTRARCOMENTARIO, tComentario.getIDCo());
					dispose();
				}
			});
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		contentPane.revalidate();
	}

	protected void abrirCrearComentario(TPlegaria plegaria) {

		JTextField textField;
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		super.add(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new MigLayout("", "[]", "[][]"));

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnCancelar, "cell 0 1,growx");

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[grow]", "[][][][][][grow]"));

		JLabel lblEscribiendoComentarioEn = new JLabel("Escribiendo comentario en '" + plegaria.getTitulo()+"'");
		lblEscribiendoComentarioEn.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblEscribiendoComentarioEn, "cell 0 0");

		JLabel lblEmailDelEscritor = new JLabel("Email del escritor");
		lblEmailDelEscritor.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblEmailDelEscritor, "cell 0 2");

		textField = new JTextField();
		panel_1.add(textField, "cell 0 3,growx");
		textField.setColumns(10);

		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblContenido, "cell 0 4");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(scrollPane, "cell 0 5,grow");

		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);

		JButton btnComentar = new JButton("Comentar");
		btnComentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().isEmpty() && !textPane.getText().isEmpty()) {
					String email = textField.getText();
					String cuerpo = textPane.getText();
					TComentarioAlt com = new TComentarioAlt(email, plegaria.getTitulo(), cuerpo);
					cont = Controlador.getInstancia();
					cont.accion(Evento.CREARCOMENTARIO, com);
					dispose();
				} else {
					if(textField.getText().isEmpty())// Si nos meten un resultado vacío
					JOptionPane.showMessageDialog(null, "No se ha introducido el email");
					if(textPane.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "No se puede mandar un comentario vacio");
				}

			}
		});
		panel.add(btnComentar, "cell 0 0");
		contentPane.revalidate();

	}

	protected void abrirModificarComentario(TComentario comentario) {
		JTextField textField;

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		super.add(contentPane);
		contentPane
				.setLayout(new MigLayout("", "[][][][][][grow][][][][][][][][][][][][][grow]", "[][][][][][][][][][]"));

		JLabel lblValor = new JLabel("Nuevo cuerpo");
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblValor, "cell 8 5");

		textField = new JTextField();

		contentPane.add(textField, "cell 4 6 11 2,growx,aligny top");
		textField.setColumns(10);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valor = "cuerpo";
				String cuerpo = textField.getText();
				TOAModificar toam = new TOAModificar(comentario.getIDCo(), valor, cuerpo);
				cont = Controlador.getInstancia();
				cont.accion(Evento.MODIFICARCOMENTARIO, toam);
				dispose();
			}
		});
		contentPane.add(btnAceptar, "cell 5 9");

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.INICIALIZARMENU, null);
				dispose();
			}
		});
		contentPane.add(btnCancelar, "cell 13 9");
		contentPane.revalidate();

	}

}
