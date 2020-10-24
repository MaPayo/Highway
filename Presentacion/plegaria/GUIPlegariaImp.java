package plegaria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import presentacion.IGUI;
import net.miginfocom.swing.MigLayout;
import controlador.Controlador;
import controlador.Evento;
import controlador.TOAModificar;

public class GUIPlegariaImp extends IGUIPlegaria {

	public GUIPlegariaImp() {
		super();
		initGUI();
	}

	public void initGUI() {
		super.setSize(500, 400);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	Controlador cont;
	JPanel contentPane;

	public void actualizar(Evento evento, Object transfer) {
		switch (evento) {
		case CREARPLEGARIAOK:
			JOptionPane.showMessageDialog(null, "Felicidades, ha creado una plegaria");
			menuPlegaria();
			break;
		case CREARPLEGARIAREACTIVACION:
			JOptionPane.showMessageDialog(null, "Su plegaria ha sido reactivada");
			menuPlegaria();
			break;
		case CREARPLEGARIAFAIL:
			JOptionPane.showMessageDialog(null, "Lo sentimos, esta plegaria ya existe");
			menuPlegaria();
			break;
		case BORRARPLEGARIAOK:
			JOptionPane.showMessageDialog(null, "Su plegaria ha sido borrada con exito");
			menuPlegaria();
			break;
		case BORRARPLEGARIAFAIL:
			JOptionPane.showMessageDialog(null, "No hemos podido borrar su plegaria");
			menuPlegaria();
			break;
		case MODIFICARPLEGARIAOK:
			JOptionPane.showMessageDialog(null, "Se ha podido modificar sin problemas");
			mostrarPlegaria((TPlegaria) transfer);
			break;
		case MODIFICARPLEGARIAFAIL:
			JOptionPane.showMessageDialog(null, "Su plegaria no ha podido ser modificada");
			menuPlegaria();
			break;
		case MOSTRARPLEGARIAOK:
			TPlegaria plegaria = (TPlegaria) transfer;
			mostrarPlegaria(plegaria);
			break;
		case MOSTRARPLEGARIAFAIL:
			JOptionPane.showMessageDialog(null, "No hemos encontrado tu plegaria");
			break;
		case MOSTRARPLEGARIASOK:
			ArrayList<TPlegaria> listas = (ArrayList<TPlegaria>) transfer;
			mostrarPlegarias(listas);
			break;
		case ABRIRVISTAPLEGARIA:
			menuPlegaria();
			break;
		case ABRIRCREARPLEGARIA:
			abrirCrearPlegaria();
			break;
		case ABRIRMODIFICARPLEGARIA:
			abrirModificarPlegaria((TPlegaria) transfer);
			break;
		default:
			JOptionPane.showMessageDialog(null, "ERROR: A GUIPlegariaImp le ha llegado un evento no contemplado.");
			break;
		}
	}

	private void menuPlegaria() {
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		super.setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[115px][][][][][][][]", "[23px][][][][][][]"));

		JButton btnCrearPlegaria = new JButton("Crear Plegaria");
		btnCrearPlegaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRCREARPLEGARIA, null);
				dispose();
			}
		});
		contentPane.add(btnCrearPlegaria, "cell 2 2,growx");

		JButton btnMostrarPlegarias = new JButton("Mostrar Plegarias");
		contentPane.add(btnMostrarPlegarias, "cell 2 4,growx,aligny top");
		btnMostrarPlegarias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.MOSTRARPLEGARIAS, null);
				dispose();
			}
		});

		JButton btnMenPrincipal = new JButton("Men\u00FA Principal");
		btnMenPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.INICIALIZARMENU, null);
				dispose();
			}
		});
		contentPane.add(btnMenPrincipal, "cell 2 6,growx");
		contentPane.revalidate();
	}

	private void mostrarPlegaria(TPlegaria plegaria) {
		JTextField textField;
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		super.add(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow]", "[][][][grow]"));

		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblTtulo, "cell 1 0");

		textField = new JTextField();
		textField.setBackground(SystemColor.text);
		textField.setEditable(false);
		textField.setText(plegaria.getTitulo()); // Mete el titulo en su campo
		contentPane.add(textField, "cell 1 1,growx");
		textField.setColumns(10);

		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblContenido, "cell 1 2,alignx left");

		JTextPane textPane = new JTextPane();
		textPane.setForeground(SystemColor.desktop);
		textPane.setText(plegaria.getContenido());// Mete el cuerpo en su sitio
		textPane.setEditable(false);
		contentPane.add(textPane, "cell 1 3,grow");

		JPanel botones = new JPanel();
		contentPane.add(botones, BorderLayout.EAST);
		botones.setBackground(SystemColor.inactiveCaption);
		botones.setLayout(new MigLayout("", "[]", "[][][][]"));

		JButton btnVerComentariosP = new JButton("Ver Comentarios");
		btnVerComentariosP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.MOSTRARCOMENTARIOPORPLEGARIA, plegaria.getiDP());
			}
		});
		botones.add(btnVerComentariosP, "cell 0 0,growx");

		JButton btnCreaComentario = new JButton("Comentar");
		btnCreaComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRCREARCOMENTARIO, plegaria);
			}
		});
		botones.add(btnCreaComentario, "cell 0 1,growx");

		JButton btnBorrarPlegaria = new JButton("Borrar Plegaria");
		btnBorrarPlegaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.BORRARPLEGARIA, plegaria.getiDP());
				dispose();
			}
		});
		botones.add(btnBorrarPlegaria, "cell 0 2,growx");

		JButton btnModificarP = new JButton("Modificar");
		btnModificarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRMODIFICARPLEGARIA, plegaria);
				dispose();
			}
		});
		botones.add(btnModificarP, "cell 0 3,growx");

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRVISTAPLEGARIA, null);
				dispose();
			}
		});
		botones.add(btnVolver, "cell 0 4,growx");
		contentPane.revalidate();

	}

	private void mostrarPlegarias(ArrayList<TPlegaria> lista) {

		JTable table;

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setLayout(new BorderLayout(0, 0));
		super.add(contentPane);

		JPanel botones = new JPanel();
		botones.setBackground(SystemColor.inactiveCaption);
		contentPane.add(botones, BorderLayout.EAST);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRVISTAPLEGARIA, null);
				dispose();
			}
		});
		botones.add(btnNewButton);

		JPanel tablaPlegarias = new JPanel();
		tablaPlegarias.setBackground(SystemColor.activeCaption);
		contentPane.add(tablaPlegarias, BorderLayout.CENTER);
		tablaPlegarias.setLayout(new MigLayout("", "[grow]", "[][365.00,grow]"));

		JLabel lblMostrandoTodasLas = new JLabel("Mostrando todas las plegarias");
		lblMostrandoTodasLas.setFont(new Font("Tahoma", Font.BOLD, 16));
		tablaPlegarias.add(lblMostrandoTodasLas, "cell 0 0");

		JScrollPane scrollPane = new JScrollPane();
		tablaPlegarias.add(scrollPane, "cell 0 1,grow");

		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.addColumn("T\u00EDtulo");
		String filas[] = new String[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			filas[0] = lista.get(i).getTitulo();
			modelo.addRow(filas);
		}
		// modelo.addRow(filas);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				int id = lista.get(fila).getiDP();
				cont = Controlador.getInstancia();
				cont.accion(Evento.MOSTRARPLEGARIA, id);
				dispose();
			}
		});

		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.revalidate();

	}

	private void abrirCrearPlegaria() {
		JTextField textField;
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		super.add(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow]", "[][][][grow]"));

		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblTtulo, "cell 1 0");

		textField = new JTextField();
		textField.setBackground(SystemColor.text);
		textField.setEditable(true);
		contentPane.add(textField, "cell 1 1,growx");
		textField.setColumns(10);

		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblContenido, "cell 1 2,alignx left");

		JTextPane textPane = new JTextPane();
		textPane.setForeground(SystemColor.desktop);
		textPane.setEditable(true);
		contentPane.add(textPane, "cell 1 3,grow");

		JPanel botones = new JPanel();
		contentPane.add(botones, BorderLayout.EAST);
		botones.setBackground(SystemColor.inactiveCaption);
		botones.setLayout(new MigLayout("", "[]", "[][][][]"));

		JButton btnVerComentariosP = new JButton("Crear");
		btnVerComentariosP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().isEmpty() && !textPane.getText().isEmpty()) {
					String titulo = textField.getText();
					String cuerpo = textPane.getText();
					TPlegaria plegaria = new TPlegaria(titulo, cuerpo);
					cont = Controlador.getInstancia();
					cont.accion(Evento.CREARPLEGARIA, plegaria);
					dispose();
				} else {
					if (textField.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "El titulo esta vacio");
					if (textPane.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "No puedes mandar una plegaria vacia");

				}

			}
		});
		botones.add(btnVerComentariosP, "cell 0 0,growx");

		JButton btnBorrarPlegaria = new JButton("Volver");
		btnBorrarPlegaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRVISTAPLEGARIA, null);
				dispose();
			}
		});
		botones.add(btnBorrarPlegaria, "cell 0 1,growx");
		contentPane.revalidate();
	}

	private void abrirModificarPlegaria(TPlegaria plegaria) {
		JTextField textField;

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		super.add(contentPane);
		contentPane
				.setLayout(new MigLayout("", "[][][][][][grow][][][][][][][][][][][][][grow]", "[][][][][][][][][][]"));

		JLabel lblCampo = new JLabel("Campo");
		lblCampo.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblCampo, "cell 8 1");

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Titulo");
		comboBox.addItem("Cuerpo");

		contentPane.add(comboBox, "cell 4 2 11 2,growx");

		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblValor, "cell 8 5");

		textField = new JTextField();

		contentPane.add(textField, "cell 4 6 11 2,growx,aligny top");
		textField.setColumns(10);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valor;
				valor = comboBox.getSelectedItem().toString();
				String cuerpo = textField.getText();
				TOAModificar toam = new TOAModificar(plegaria.getiDP(), valor, cuerpo);
				cont = Controlador.getInstancia();
				dispose();
				cont.accion(Evento.MODIFICARPLEGARIA, toam);
				// dispose();
			}
		});
		contentPane.add(btnAceptar, "cell 5 9");

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRVISTAPLEGARIA, null);
				dispose();
			}
		});
		contentPane.add(btnCancelar, "cell 13 9");
		contentPane.revalidate();
	}

}
