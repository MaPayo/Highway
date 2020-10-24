package creyente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import presentacion.IGUI;
import net.miginfocom.swing.MigLayout;
import controlador.Controlador;
import controlador.Evento;
import controlador.TOAModificar;

public class GUICreyenteImp extends IGUICreyente {

	Controlador cont;
	JPanel contentPane;

	public GUICreyenteImp() {
		super();
		initGUI();
	}

	public void initGUI() {
		super.setSize(500, 400);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actualizar(Evento evento, Object transfer) {
		switch (evento) {
		case CREARCREYENTEOK:
			JOptionPane.showMessageDialog(null, "El creyente se ha creado correctamente.");
			menuCreyente();
			break;
		case CREARCREYENTEREACTIVACION:
			JOptionPane.showMessageDialog(null, "El creyente se ha reactivado.");
			menuCreyente();
			break;
		case CREARCREYENTEFAIL:
			JOptionPane.showMessageDialog(null, "El creyente ya existía en la base de datos.");
			menuCreyente();
			break;
		case BORRARCREYENTEOK:
			JOptionPane.showMessageDialog(null, "El creyente se ha borrado correctamente.");
			menuCreyente();
			break;
		case BORRARCREYENTEFAIL:
			JOptionPane.showMessageDialog(null, "No se ha podido borrar el creyente");
			menuCreyente();
			break;
		case MODIFICARCREYENTEOK:
			JOptionPane.showMessageDialog(null, "Se ha podido modificar el creyente.");
			mostrarCreyente((TCreyente) transfer);
			break;
		case MODIFICARCREYENTEFAIL:
			JOptionPane.showMessageDialog(null, "No se ha podido modificar el creyente.");
			break;
		case MOSTRARCREYENTEOK:
			mostrarCreyente((TCreyente) transfer);
			break;
		case MOSTRARCREYENTEFAIL:
			JOptionPane.showMessageDialog(null, "No se ha podido encontrar al creyente.");
			break;
		case MOSTRARCREYENTESOK:
			mostrarCreyentes((ArrayList<TCreyente>) transfer);
			break;
		case ABRIRVISTACREYENTE:
			menuCreyente();
			break;
		case ABRIRCREARCREYENTE:
			abrirCrearCreyente();
			break;
		case ABRIRMODIFICARCREYENTE:
			abrirModificarCreyente((TCreyente) transfer);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Evento no contemplado en Comentarios");
			break;
		}
	}

	private void menuCreyente() {
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		super.setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[115px][][][][][][][]", "[23px][][][][][][]"));

		JButton btnCrearCreyente = new JButton("Crear Creyente");
		btnCrearCreyente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRCREARCREYENTE, null);
				dispose();
			}
		});
		contentPane.add(btnCrearCreyente, "cell 2 2,growx");

		JButton btnMostrarCreyentes = new JButton("Mostrar Creyentes");
		contentPane.add(btnMostrarCreyentes, "cell 2 4,growx,aligny top");
		btnMostrarCreyentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.MOSTRARCREYENTES, null);
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

	private void mostrarCreyente(TCreyente creyente) {
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setLayout(new BorderLayout(0, 0));
		super.setContentPane(contentPane);

		JPanel botones = new JPanel();
		botones.setBackground(SystemColor.inactiveCaption);
		contentPane.add(botones, BorderLayout.EAST);
		botones.setLayout(new MigLayout("", "[111px]", "[23px][][][]"));

		JButton btnVerComentariosC = new JButton("Ver Comentarios");
		btnVerComentariosC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.MOSTRARCOMENTARIOPORCREYENTE, creyente.getIDC());
			}
		});
		botones.add(btnVerComentariosC, "cell 0 0,alignx left,aligny top");

		JButton btnBorrarCreyente = new JButton("Borrar Creyente");
		btnBorrarCreyente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.BORRARCREYENTE, creyente.getIDC());
				dispose();
			}
		});
		botones.add(btnBorrarCreyente, "cell 0 1");

		JButton btnModificarC = new JButton("Modificar");
		btnModificarC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRMODIFICARCREYENTE, creyente);
				dispose();
			}
		});
		botones.add(btnModificarC, "cell 0 2,growx");

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRVISTACREYENTE, null);
				dispose();
			}
		});
		botones.add(btnVolver, "cell 0 3,growx");

		JPanel panelTextos = new JPanel();
		panelTextos.setBackground(SystemColor.activeCaption);
		contentPane.add(panelTextos, BorderLayout.CENTER);
		panelTextos.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][]"));

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelTextos.add(lblNombre, "cell 1 1");

		JLabel label = new JLabel(creyente.getNombre());
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelTextos.add(label, "cell 1 2");

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelTextos.add(lblEmail, "cell 1 4");

		JLabel label_1 = new JLabel(creyente.getEmail());
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelTextos.add(label_1, "cell 1 5");

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelTextos.add(lblEdad, "cell 1 7");

		JLabel label_2 = new JLabel(creyente.getEdad().toString());
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelTextos.add(label_2, "cell 1 8");
		contentPane.revalidate();

	}

	private void mostrarCreyentes(ArrayList<TCreyente> lista) {
		JTable table;

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setLayout(new BorderLayout(0, 0));
		super.setContentPane(contentPane);

		JPanel botones = new JPanel();
		botones.setBackground(SystemColor.inactiveCaption);
		contentPane.add(botones, BorderLayout.EAST);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRVISTACREYENTE, null);
				dispose();
			}
		});
		botones.add(btnNewButton);

		JPanel tablaPlegarias = new JPanel();
		tablaPlegarias.setBackground(SystemColor.activeCaption);
		contentPane.add(tablaPlegarias, BorderLayout.CENTER);
		tablaPlegarias.setLayout(new MigLayout("", "[grow]", "[][365.00,grow]"));

		JLabel lblMostrandoTodasLas = new JLabel("Mostrando todos los creyentes ");
		lblMostrandoTodasLas.setFont(new Font("Tahoma", Font.BOLD, 16));
		tablaPlegarias.add(lblMostrandoTodasLas, "cell 0 0");

		JScrollPane scrollPane = new JScrollPane();
		tablaPlegarias.add(scrollPane, "cell 0 1,grow");

		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.addColumn("Nombre");
		modelo.addColumn("E-mail");

		String filas[] = new String[lista.size() + 1];
		int j = 0;
		for (int i = 0; i < lista.size(); i++) {
			j = 0;
			filas[j] = lista.get(i).getNombre();
			filas[j + 1] = lista.get(i).getEmail();
			modelo.addRow(filas);

		}

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				int id = lista.get(fila).getIDC();
				cont = Controlador.getInstancia();
				cont.accion(Evento.MOSTRARCREYENTE, id);
				dispose();
			}
		});

		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

	}

	private void abrirCrearCreyente() {

		JTextField textField;
		JTextField textField_1;
		JTextField textField_2;

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setLayout(new BorderLayout(0, 0));
		super.setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new MigLayout("", "[111px]", "[23px][][][]"));

		JButton btnVolver = new JButton("Cancelar");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRVISTACREYENTE, null);
				dispose();
			}
		});
		panel.add(btnVolver, "cell 0 1,growx");

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][]"));

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNombre, "cell 1 1");

		textField = new JTextField();
		panel_1.add(textField, "cell 1 2,growx");
		textField.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblEmail, "cell 1 4");

		textField_1 = new JTextField();
		panel_1.add(textField_1, "cell 1 5,growx");
		textField_1.setColumns(10);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblEdad, "cell 1 7");

		textField_2 = new JTextField();
		panel_1.add(textField_2, "cell 1 8,growx");
		textField_2.setColumns(10);

		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().isEmpty() && !textField_1.getText().isEmpty()
						&& !textField_2.getText().isEmpty()) {
					String Nombre = textField.getText();
					String email = textField_1.getText();
					String edad = textField_2.getText();
					TCreyente creyente = new TCreyente(Nombre, email, Integer.parseInt(edad));
					cont = Controlador.getInstancia();
					cont.accion(Evento.CREARCREYENTE, creyente);
					dispose();
				} else {
					if (textField.getText().isEmpty())// Si nos meten un
														// resultado vacío
						JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio");
					if (textField_1.getText().isEmpty())// Si nos meten un
														// resultado vacío
						JOptionPane.showMessageDialog(null, "El email no puede estar vacío.");
					if (textField_2.getText().isEmpty())// Si nos meten un
														// resultado vacío
						JOptionPane.showMessageDialog(null, "La edad no puede estar vacia");
				}
			}
		});
		panel.add(btnNewButton, "cell 0 0,growx,aligny top");
		contentPane.revalidate();
	}

	private void abrirModificarCreyente(TCreyente creyente) {
		JTextField textField;

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		super.setContentPane(contentPane);
		contentPane
				.setLayout(new MigLayout("", "[][][][][][grow][][][][][][][][][][][][][grow]", "[][][][][][][][][][]"));

		JLabel lblCampo = new JLabel("Campo");
		lblCampo.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblCampo, "cell 8 1");

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Nombre");
		comboBox.addItem("Edad");

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
				TOAModificar toam = new TOAModificar(creyente.getIDC(), valor, cuerpo);
				cont = Controlador.getInstancia();
				dispose();
				cont.accion(Evento.MODIFICARCREYENTE, toam);

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
