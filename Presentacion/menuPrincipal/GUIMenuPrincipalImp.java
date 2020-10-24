package menuPrincipal;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import presentacion.IGUI;
import net.miginfocom.swing.MigLayout;
import controlador.Controlador;
import controlador.Evento;

public class GUIMenuPrincipalImp extends IGUIMenuPrincipal {
	
	
	Controlador cont;
	JPanel contentPane;
	
	public GUIMenuPrincipalImp(){
		super();
		initGUI();
	}
	
	public void initGUI(){
		super.setSize(500, 400);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actualizar(Evento evento, Object transfer) {
		if (evento.equals(Evento.INICIALIZARMENU)) {
			iniciaMenuPrincipal();
		} else {
			JOptionPane.showMessageDialog(null,
					"Evento no valido");
		}
	}

	private void iniciaMenuPrincipal(){
	
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][]", "[][][][][]"));
		super.add(contentPane);
		
		
		
		JLabel lblHighway = new JLabel("HIGHWAY ");
		lblHighway.setForeground(SystemColor.inactiveCaptionBorder);
		lblHighway.setFont(new Font("Arial Black", Font.ITALIC, 18));
		contentPane.add(lblHighway, "cell 4 1,alignx center");
		
		JLabel lblMenPrincipal = new JLabel("Men\u00FA Principal");
		lblMenPrincipal.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblMenPrincipal, "cell 4 2,alignx center");
		
		JButton btnPlegarias = new JButton("Plegarias");
		btnPlegarias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRVISTAPLEGARIA, null);
				dispose();
			}
		});
		contentPane.add(btnPlegarias, "cell 3 4");
		
		JButton btnCreyentes = new JButton("Creyentes");
		btnCreyentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont = Controlador.getInstancia();
				cont.accion(Evento.ABRIRVISTACREYENTE, null);
				dispose();
			}
		});
		contentPane.add(btnCreyentes, "cell 5 4");
		contentPane.revalidate();
		
	}


}
