package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.itextpdf.text.DocumentException;

import conexion.ConexionBD;
import control.CadenaDeFrio;
import control.Certificacion;
import control.EstadoDelHato;
import control.KilometrosRecorridos;
import control.Proveedor;
import control.TipoDeVechiculo;
import control.ValoresDeHigiene;
import control.util.CrearPDF;
import control.util.GeneratePDFFileIText;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.List;

import javax.swing.JTable;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class Interfaz extends JFrame {

	private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo Excel", "xls");
	String rutaImagen;
	
	boolean proteinayGrasa = false;
	boolean solidosTotales = false;
	
    ArrayList<Proveedor>proveedores = new ArrayList<Proveedor>();
    ArrayList<String>nombres = new ArrayList<String>();


	private JPanel contentPane;
	private JPanel contentPane2;

	/**
	 * clase para cargar la interfaz y auxiliar para leer el excel
	 */

	JFileChooser fileChooser = new JFileChooser();
	
	private JTextField proteinaTxF1;
	private JTextField GrasaTxF1;
	private JTextField SolidosTxF1;
	private JTextField ProteinaTxF2;
	private JTextField GrasaTxF2;
	private JTextField SolidosTxF2;
	private JTextField ProteinaTxFTotal;
	private JTextField GrasaTxFTotal;
	private JTextField SolidosTxFTotal;
	private JTextField precioProteina;
	private JTextField precioGrasa;
	private JTextField precioSolidosT;
	private JTextField otrasBonificaionesTXT;
	
	/**
	 *  Elementos para calcular la proteina,grasa y solidos de la leche
	 */
	
	private double decimasProteina;
	private double decimasGrasa;
	private double decimasSolidosTotales;
	
	private double gramosTotProteina;
	private double gramosTotGrasa;
	private double gramosTotSolidosTotales;
	
	private double precioProteinaGramo;
	private double precioGrasaGramo;
	private double precioSolidosGramo;

	private double totalValorProteina;
	private double totalValorGrasa;
	private double totalValorSolidos;
	
	private double bonoPorBActerias;
	private double bonoPorCadenaDeFrio;
	private double bonoEstatusSanitario;
	private double bonoPorCertificacionBPG;
	
	private double descuentoPorKilometraje;
	
	private double bonoAdicional;
	
	
	/**
	 * Variables para el calculo de pago al proveedor
	 */
	private double totalAPagar ;
	
	/**
	 * clase que genera la factura
	 */
	CrearPDF pdf = new CrearPDF();	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
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
	public Interfaz() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Interfaz.class.getResource("/imgs/Logo_belen.png")));
		setTitle("Facturador Lacteos Belen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 30, 672, 684);
		contentPane = new JPanel();
		contentPane.setToolTipText("Grasas\r\n");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.rutaImagen = "";

		JButton btnNewButton = new JButton("Buscar Informe");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// crea un objeto de tipo JFileChooser (movido al metodo main)
				// se restringe el tipo de extencion de archivo
				fileChooser.setFileFilter(filter);
				// se abre ventana de dialogo de examinar
				int opcion = fileChooser.showOpenDialog(null);

				if (opcion == JFileChooser.APPROVE_OPTION) {
					//System.out.println("accede al archivo!!");
					leerExcelSemanal();
					contentPane.setVisible(false);
					contentPane= new JPanel();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					setContentPane(contentPane);
					contentPane.setLayout(null);
							
					JLabel nombrelabel = new JLabel();
					nombrelabel.setBounds(188, 11, 135, 23);
					nombrelabel.setText("Nombre del proveedor :");
					contentPane.add(nombrelabel);
					
					JComboBox comboBox = new JComboBox<>();
					for (Proveedor proveedor : proveedores) {
						comboBox.addItem(proveedor.getNombre());
					}
					comboBox.setBounds(351, 11, 106, 23);
					contentPane.add(comboBox);
					
					JLabel lblNewLabel = new JLabel("Descuentos");
					lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel.setBounds(298, 491, 86, 14);
					contentPane.add(lblNewLabel);

					JLabel lblNewLabel_1 = new JLabel("Bonificaciones");
					lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel_1.setBounds(298, 297, 97, 14);
					contentPane.add(lblNewLabel_1);
					
					JRadioButton proteinaYGrasaRB = new JRadioButton("Proteina y Grasa");
					ActionListener actionListener = new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
						
							if (proteinaYGrasaRB.isSelected()) {
								SolidosTxF1.setVisible(false);
								SolidosTxF2.setVisible(false);
								SolidosTxFTotal.setVisible(false);
							}else {
								SolidosTxF1.setVisible(true);
								SolidosTxF2.setVisible(true);
								SolidosTxFTotal.setVisible(true);
							}
							
						
						}
					};
					proteinaYGrasaRB.setBounds(215, 146, 124, 23);
					proteinaYGrasaRB.addActionListener(actionListener);
					
					contentPane.add(proteinaYGrasaRB);
					
					JRadioButton solidosTotalesRB = new JRadioButton("S\u00F3lidos totales");
					ActionListener actionListener2 = new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (solidosTotalesRB.isSelected()) {
								GrasaTxF1.setVisible(false);
								GrasaTxF2.setVisible(false);
								GrasaTxFTotal.setVisible(false);
								
								proteinaTxF1.setVisible(false);
								ProteinaTxF2.setVisible(false);
								ProteinaTxFTotal.setVisible(false);
							}else{
								
								GrasaTxF1.setVisible(true);
								GrasaTxF2.setVisible(true);
								GrasaTxFTotal.setVisible(true);
								
								proteinaTxF1.setVisible(true);
								ProteinaTxF2.setVisible(true);
								ProteinaTxFTotal.setVisible(true);
							}
						}
					};
					solidosTotalesRB.setBounds(352, 146, 109, 23);
					solidosTotalesRB.addActionListener(actionListener2);
					contentPane.add(solidosTotalesRB);
					
					JLabel tipoDeAnalisisLabel = new JLabel("Tipo de an\u00E1lisis");
					tipoDeAnalisisLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					tipoDeAnalisisLabel.setBounds(300, 125, 106, 14);
					contentPane.add(tipoDeAnalisisLabel);
					
					JLabel ProteinaLabel = new JLabel("Proteina");
					ProteinaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
					ProteinaLabel.setBounds(37, 213, 63, 11);
					contentPane.add(ProteinaLabel);
					
					JLabel GrasaLabel = new JLabel("Grasa");
					GrasaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
					GrasaLabel.setBounds(37, 240, 46, 14);
					contentPane.add(GrasaLabel);
					
					JLabel SolidosTotalesLabel = new JLabel("Solidos Totales");
					SolidosTotalesLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
					SolidosTotalesLabel.setBounds(37, 267, 85, 14);
					contentPane.add(SolidosTotalesLabel);
					
					JLabel fraccionesDecimasLabel = new JLabel("Fracciones de D\u00E9cimas %");
					fraccionesDecimasLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
					fraccionesDecimasLabel.setBounds(152, 185, 152, 14);
					contentPane.add(fraccionesDecimasLabel);
					
					JLabel gramosTotalesLabel = new JLabel("Gramos Totales");
					gramosTotalesLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
					gramosTotalesLabel.setBounds(314, 185, 105, 14);
					contentPane.add(gramosTotalesLabel);
					
					JLabel valorPorComposicionLabel = new JLabel("Valor Total por Composici\u00F3n (gr/lt)");
					valorPorComposicionLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
					valorPorComposicionLabel.setBounds(429, 185, 203, 14);
					contentPane.add(valorPorComposicionLabel);
					
					proteinaTxF1 = new JTextField();
					proteinaTxF1.setBounds(192, 209, 86, 20);
					contentPane.add(proteinaTxF1);
					proteinaTxF1.setColumns(10);
					
					
					GrasaTxF1 = new JTextField();
					GrasaTxF1.setBounds(192, 236, 86, 20);
					contentPane.add(GrasaTxF1);
					GrasaTxF1.setColumns(10);
					
					SolidosTxF1 = new JTextField();
					SolidosTxF1.setBounds(192, 266, 86, 20);
					contentPane.add(SolidosTxF1);
					SolidosTxF1.setColumns(10);
					
					ProteinaTxF2 = new JTextField();
					ProteinaTxF2.setBounds(320, 208, 86, 20);
					contentPane.add(ProteinaTxF2);
					ProteinaTxF2.setColumns(10);
					ProteinaTxF2.setEditable(false);
					
					GrasaTxF2 = new JTextField();
					GrasaTxF2.setBounds(320, 236, 86, 20);
					contentPane.add(GrasaTxF2);
					GrasaTxF2.setColumns(10);
					GrasaTxF2.setEditable(false);
					
					SolidosTxF2 = new JTextField();
					SolidosTxF2.setBounds(320, 266, 86, 20);
					contentPane.add(SolidosTxF2);
					SolidosTxF2.setColumns(10);
					SolidosTxF2.setEditable(false);
					
					ProteinaTxFTotal = new JTextField();
					ProteinaTxFTotal.setBounds(454, 209, 86, 20);
					contentPane.add(ProteinaTxFTotal);
					ProteinaTxFTotal.setColumns(10);
					ProteinaTxFTotal.setEditable(false);
					
					GrasaTxFTotal = new JTextField();
					GrasaTxFTotal.setBounds(454, 236, 86, 20);
					contentPane.add(GrasaTxFTotal);
					GrasaTxFTotal.setColumns(10);
					GrasaTxFTotal.setEditable(false);
					
					SolidosTxFTotal = new JTextField();
					SolidosTxFTotal.setBounds(454, 266, 86, 20);
					contentPane.add(SolidosTxFTotal);
					SolidosTxFTotal.setColumns(10);
					SolidosTxFTotal.setEditable(false);
					
					JLabel lblNewLabel_9 = new JLabel("<html><body> Bonificaciones o descuentos  <br> según la Calidad Higiénica \r\n" + 
							"del Productor (UFC / ml)</body></html>");
					lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblNewLabel_9.setBounds(36, 320, 165, 58);
					contentPane.add(lblNewLabel_9);
					
					JLabel lblNewLabel_10 = new JLabel("<html><body> Bonificaciones según  <br> el Estatus Sanitario del hato \r\n" + 
							"productor de leche</body></html>\"");
					lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblNewLabel_10.setBounds(36, 377, 165, 58);
					contentPane.add(lblNewLabel_10);
				
					List bacteria = new List();
					JComboBox valoresHigieneCB = new JComboBox();
					for (ValoresDeHigiene bacterias : ValoresDeHigiene.values()) {
						bacteria.add(bacterias.getBacterias());
					}
					valoresHigieneCB.setModel(new DefaultComboBoxModel<>(bacteria.getItems()));			
					valoresHigieneCB.setBounds(214, 333, 97, 23);
					contentPane.add(valoresHigieneCB);
					
					
					JComboBox cadenaDeFrioCB = new JComboBox();
					List cadena = new List();
					for (CadenaDeFrio frio : CadenaDeFrio.values()) {
						cadena.add(frio.getRefrigeracion());
					}
					cadenaDeFrioCB.setModel(new DefaultComboBoxModel(cadena.getItems()));
					cadenaDeFrioCB.setBounds(428, 333, 93, 23);
					contentPane.add(cadenaDeFrioCB);
					
					JComboBox certificacionSanitariaCB = new JComboBox();
					List hatos = new List();
					for (EstadoDelHato hato : EstadoDelHato.values()) {
						hatos.add(hato.getEnunciado());
					}
					certificacionSanitariaCB.setModel(new DefaultComboBoxModel(hatos.getItems()));
					certificacionSanitariaCB.setBounds(214, 389, 204, 23);
					contentPane.add(certificacionSanitariaCB);
					
					JComboBox certificacionBGP = new JComboBox();
					List certificados = new List();
					for (Certificacion certificacion: Certificacion.values()) {
						certificados.add(certificacion.getEnunciado());
					}
					certificacionBGP.setModel(new DefaultComboBoxModel(certificados.getItems()));
					certificacionBGP.setBounds(428, 389, 152, 23);
					contentPane.add(certificacionBGP);
					
					JLabel lblNewLabel_11 = new JLabel("<html><body>Rango de Ruta en Kil\u00F3metros <br> ( PLanta - Finca -  Planta)</body></html>");
					lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblNewLabel_11.setBounds(139, 528, 165, 28);
					contentPane.add(lblNewLabel_11);
					
					JLabel lblNewLabel_12 = new JLabel("Tipo de Veh\u00EDculo");
					lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblNewLabel_12.setBounds(397, 528, 93, 14);
					contentPane.add(lblNewLabel_12);
					
					JComboBox kilometrosRutaCB = new JComboBox();
					List kilometros = new List();
					for (KilometrosRecorridos kilometro : KilometrosRecorridos.values()) {
						kilometros.add(kilometro.getKilometros());
					}
					kilometrosRutaCB.setModel(new DefaultComboBoxModel(kilometros.getItems()));
					kilometrosRutaCB.setBounds(185, 567, 71, 20);
					contentPane.add(kilometrosRutaCB);
					
					JComboBox TipoDeVehiculosRB = new JComboBox();
					List vehiculos = new List();
					for (TipoDeVechiculo vehiculo : TipoDeVechiculo.values()) {
						vehiculos.add(vehiculo.getVehiculo());
					}
					TipoDeVehiculosRB.setModel(new DefaultComboBoxModel(vehiculos.getItems()));
					TipoDeVehiculosRB.setBounds(394, 567, 165, 20);
					contentPane.add(TipoDeVehiculosRB);
					
					JButton btnNewButton_2 = new JButton("Limpiar");
					btnNewButton_2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							comboBox.setSelectedItem(0);
							precioGrasa.setText("");
							precioProteina.setText("");
							precioSolidosT.setText("");
							proteinaTxF1.setText("");
							ProteinaTxF2.setText("");
							ProteinaTxFTotal.setText("");
							GrasaTxF1.setText("");
							GrasaTxF2.setText("");
							GrasaTxFTotal.setText("");
							SolidosTxF1.setText("");
							SolidosTxF2.setText("");
							SolidosTxFTotal.setText("");
							proteinaYGrasaRB.setSelected(false);
							solidosTotalesRB.setSelected(false);
							otrasBonificaionesTXT.setText("");
							
						}
					});
					btnNewButton_2.setBounds(388, 611, 89, 23);
					contentPane.add(btnNewButton_2);
					
					JLabel lblNewLabel_2 = new JLabel("Calidad Composicional");
					lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel_2.setBounds(266, 45, 165, 23);
					contentPane.add(lblNewLabel_2);
					
					JLabel lblNewLabel_3 = new JLabel("Concepto");
					lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblNewLabel_3.setBounds(67, 75, 85, 14);
					contentPane.add(lblNewLabel_3);
					
					JLabel lblNewLabel_4 = new JLabel("Valor de cada gramo ($)");
					lblNewLabel_4.setBounds(67, 100, 124, 14);
					contentPane.add(lblNewLabel_4);
					
					JLabel labelProteina = new JLabel("Proteina");
					labelProteina.setBounds(241, 75, 63, 14);
					contentPane.add(labelProteina);
					
					JLabel labelGrasa = new JLabel("Grasa");
					labelGrasa.setBounds(352, 75, 46, 14);
					contentPane.add(labelGrasa);
					
					JLabel labelSolidosT = new JLabel("Solidos totales");
					labelSolidosT.setBounds(454, 75, 86, 14);
					contentPane.add(labelSolidosT);
					
					precioProteina = new JTextField();
					precioProteina.setBounds(234, 97, 86, 20);
					contentPane.add(precioProteina);
					precioProteina.setColumns(10);
					
					precioGrasa = new JTextField();
					precioGrasa.setBounds(345, 97, 86, 20);
					contentPane.add(precioGrasa);
					precioGrasa.setColumns(10);
					
					precioSolidosT = new JTextField();
					precioSolidosT.setBounds(454, 97, 86, 20);
					contentPane.add(precioSolidosT);
					precioSolidosT.setColumns(10);
					
					JLabel otrosDescuentosLabel = new JLabel("Otras bonificaciones.");
					otrosDescuentosLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
					otrosDescuentosLabel.setBounds(37, 455, 154, 23);
					contentPane.add(otrosDescuentosLabel);
					
					otrasBonificaionesTXT = new JTextField();
					otrasBonificaionesTXT.setBounds(215, 456, 86, 20);
					contentPane.add(otrasBonificaionesTXT);
					otrasBonificaionesTXT.setColumns(10);
					
					
					JButton generarReciboBTN = new JButton("Generar Recibo");
					generarReciboBTN.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							if(proteinaYGrasaRB.isSelected() == false && solidosTotalesRB.isSelected( )== false) {
								JOptionPane.showConfirmDialog(null, "Selecciones el tipo de análisis " , "Atención",
										 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
							}
							
							
							
							DecimalFormat df = new DecimalFormat("#.00");
							
							ConexionBD conexion = new ConexionBD();
							conexion.ConexionBD();
							
							
							int idKilometros = kilometrosRutaCB.getSelectedIndex();
							idKilometros = idKilometros+1;
							
							int idVehiculo = TipoDeVehiculosRB.getSelectedIndex();					
							idVehiculo= idVehiculo+1;
							
							
							try {
								Double descuento = conexion.consultaDescuentoPorTransporte(String.valueOf(idKilometros), String.valueOf(idVehiculo));
								descuentoPorKilometraje = descuento;
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							int idValoresHigiene = valoresHigieneCB.getSelectedIndex();
							idValoresHigiene = idValoresHigiene+1;
							
							bonoPorBActerias = conexion.consultaValoresDeHigiene(String.valueOf(idValoresHigiene));
							
							
							int idCadenaDefrio = cadenaDeFrioCB.getSelectedIndex();
							idCadenaDefrio = idCadenaDefrio+1;
							
							if (idCadenaDefrio <2) {
								bonoPorCadenaDeFrio=conexion.consultaCadenaDeFrio(String.valueOf(idValoresHigiene));
								
							}else {
								bonoPorCadenaDeFrio = 0.00;
							}
								
							
							int certificadoBPG = certificacionBGP.getSelectedIndex();
							certificadoBPG = certificadoBPG+1;
							
							bonoPorCertificacionBPG = conexion.consultaCertificadoBPG(String.valueOf(certificadoBPG));
							
							
							
							int certificadoSanitario = certificacionSanitariaCB.getSelectedIndex();
							certificadoSanitario = certificadoSanitario+1;
							
							bonoEstatusSanitario= conexion.consultaCertificadoSanitario(String.valueOf(certificadoSanitario));
							
							
							if (otrasBonificaionesTXT.getText().isEmpty()) {
								bonoAdicional = 0.00;
							}else {
								bonoAdicional  = Double.parseDouble(df.format(Double.parseDouble(otrasBonificaionesTXT.getText())).replace(',', '.'));	
								
							}
							
							
							double totalBonos =  bonoPorBActerias + bonoPorCadenaDeFrio + bonoEstatusSanitario + bonoPorCertificacionBPG+ bonoAdicional;
							double totalConDescuentos= totalBonos + descuentoPorKilometraje; 
							System.out.println(df.format(totalConDescuentos));
							
							conexion.cerrarConexion();
							
							
							
							if(proteinaYGrasaRB.isSelected()||solidosTotalesRB.isSelected()) {
								totalConDescuentos = totalConDescuentos+totalValorGrasa+ totalValorProteina ;
								System.out.println("Total! : "+df.format(totalConDescuentos));
								int response = JOptionPane.showConfirmDialog(null, "Precio neto a pagar $/Litro = "+ df.format(totalConDescuentos)+". ¿Desea generar recibo de pago?" , "Atención",
										 JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
								if (response == JOptionPane.YES_OPTION) {
								calcularPagoProveedor(comboBox.getSelectedItem().toString(), totalConDescuentos);
								}
								
							}
							
						}
					});
					generarReciboBTN.setBounds(150, 611, 129, 23);
					contentPane.add(generarReciboBTN);
					
					
					
			/**
			 *  listeners 
			 */
					 
					KeyListener keyListenerProteinas = new KeyListener() {
							
						@Override
						public void keyTyped(KeyEvent e) {
							// TODO Auto-generated method stub
							
							if (precioProteina.getText().isEmpty()) {
								//JOptionPane.showConfirmDialog(null, "para calcular la proteina, indique su precio a convenir" , JOptionPane.DEFAULT_OPTION);
								JOptionPane.showConfirmDialog(null, "para calcular la proteina, indique su precio a convenir", "Falta información",
										JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
							}else if (proteinaTxF1.getText().isEmpty()) {
												
							}else {
								calcularProteinas(Double.parseDouble(proteinaTxF1.getText()), Double.parseDouble(precioProteina.getText()));
								
							}
							
						}
						
						@Override
						public void keyReleased(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void keyPressed(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}
					}; 
					proteinaTxF1.addKeyListener(keyListenerProteinas);
					
				
				
				KeyListener keyListenerGasas = new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
						if (precioGrasa.getText().isEmpty()) {
							//JOptionPane.showConfirmDialog(null, "para calcular la proteina, indique su precio a convenir" , JOptionPane.DEFAULT_OPTION);
							JOptionPane.showConfirmDialog(null, "para calcular la grasa, indique su precio a convenir", "Falta información",
									JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						}else if (GrasaTxF1.getText().isEmpty()) {
											
						}else {
							calcularGrasas(Double.parseDouble(GrasaTxF1.getText()), Double.parseDouble(precioGrasa.getText()));
							
						}
						
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
				}; 
				GrasaTxF1.addKeyListener(keyListenerGasas);

				
			KeyListener keyListenerSolidos = new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						if (precioSolidosT.getText().isEmpty()) {
							JOptionPane.showConfirmDialog(null, "para calcular los solidos, indique su precio a convenir", "Falta información",
									JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						}else if (SolidosTxF1.getText().isEmpty()) {
											
						}else {
							calcularSolidosTotales(Double.parseDouble(SolidosTxF1.getText()), Double.parseDouble(precioProteina.getText()));
						}
						
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
				}; 
				SolidosTxF1.addKeyListener(keyListenerSolidos);

								
				} else {
					System.out.println("no entra al archivo ");
				}

			}
		});
		
		/**
		 * Separador de las dos interfaces
		 */
		
		
		btnNewButton.setBounds(188, 11, 135, 23);
		contentPane.add(btnNewButton);			
		
		JComboBox comboBox = new JComboBox<>(nombres.toArray());
		comboBox.setBounds(351, 11, 106, 23);
		comboBox.setEnabled(false);
		contentPane.add(comboBox);

		JLabel lblNewLabel = new JLabel("Descuentos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(298, 491, 86, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Bonificaciones");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(298, 297, 97, 14);
		contentPane.add(lblNewLabel_1);
		
		JRadioButton proteinaYGrasaRB = new JRadioButton("Proteina y Grasa");
		proteinaYGrasaRB.setBounds(215, 146, 124, 23);
		proteinaYGrasaRB.setEnabled(false);		
		contentPane.add(proteinaYGrasaRB);
		
		JRadioButton solidosTotalesRB = new JRadioButton("S\u00F3lidos totales");
		solidosTotalesRB.setBounds(352, 146, 109, 23);
		solidosTotalesRB.setEnabled(false);
		contentPane.add(solidosTotalesRB);
		
		JLabel tipoDeAnalisisLabel = new JLabel("Tipo de an\u00E1lisis");
		tipoDeAnalisisLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		tipoDeAnalisisLabel.setBounds(300, 125, 106, 14);
		contentPane.add(tipoDeAnalisisLabel);
		
		JLabel ProteinaLabel = new JLabel("Proteina");
		ProteinaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		ProteinaLabel.setBounds(37, 213, 63, 11);
		contentPane.add(ProteinaLabel);
		
		JLabel GrasaLabel = new JLabel("Grasa");
		GrasaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GrasaLabel.setBounds(37, 240, 46, 14);
		contentPane.add(GrasaLabel);
		
		JLabel SolidosTotalesLabel = new JLabel("Solidos Totales");
		SolidosTotalesLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		SolidosTotalesLabel.setBounds(37, 267, 85, 14);
		contentPane.add(SolidosTotalesLabel);
		
		JLabel fraccionesDecimasLabel = new JLabel("Fracciones de D\u00E9cimas %");
		fraccionesDecimasLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		fraccionesDecimasLabel.setBounds(152, 185, 152, 14);
		contentPane.add(fraccionesDecimasLabel);
		
		JLabel gramosTotalesLabel = new JLabel("Gramos Totales");
		gramosTotalesLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		gramosTotalesLabel.setBounds(314, 185, 105, 14);
		contentPane.add(gramosTotalesLabel);
		
		JLabel valorPorComposicionLabel = new JLabel("Valor Total por Composici\u00F3n (gr/lt)");
		valorPorComposicionLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		valorPorComposicionLabel.setBounds(429, 185, 203, 14);
		contentPane.add(valorPorComposicionLabel);
		
		proteinaTxF1 = new JTextField();
		proteinaTxF1.setBounds(192, 209, 86, 20);
		contentPane.add(proteinaTxF1);
		proteinaTxF1.setEnabled(false);
		proteinaTxF1.setColumns(10);
		
		
		GrasaTxF1 = new JTextField();
		GrasaTxF1.setBounds(192, 236, 86, 20);
		contentPane.add(GrasaTxF1);
		GrasaTxF1.setEnabled(false);
		GrasaTxF1.setColumns(10);
		
		SolidosTxF1 = new JTextField();
		SolidosTxF1.setBounds(192, 266, 86, 20);
		contentPane.add(SolidosTxF1);
		SolidosTxF1.setEnabled(false);
		SolidosTxF1.setColumns(10);
		
		ProteinaTxF2 = new JTextField();
		ProteinaTxF2.setBounds(320, 208, 86, 20);
		contentPane.add(ProteinaTxF2);
		ProteinaTxF2.setColumns(10);
		ProteinaTxF2.setEditable(false);
		ProteinaTxF2.setEnabled(false);
		
		GrasaTxF2 = new JTextField();
		GrasaTxF2.setBounds(320, 236, 86, 20);
		contentPane.add(GrasaTxF2);
		GrasaTxF2.setColumns(10);
		GrasaTxF2.setEditable(false);
		GrasaTxF2.setEnabled(false);
		
		SolidosTxF2 = new JTextField();
		SolidosTxF2.setBounds(320, 266, 86, 20);
		contentPane.add(SolidosTxF2);
		SolidosTxF2.setColumns(10);
		SolidosTxF2.setEditable(false);
		SolidosTxF2.setEnabled(false);
		
		ProteinaTxFTotal = new JTextField();
		ProteinaTxFTotal.setBounds(454, 209, 86, 20);
		contentPane.add(ProteinaTxFTotal);
		ProteinaTxFTotal.setColumns(10);
		ProteinaTxFTotal.setEditable(false);
		ProteinaTxFTotal.setEnabled(false);

		GrasaTxFTotal = new JTextField();
		GrasaTxFTotal.setBounds(454, 236, 86, 20);
		contentPane.add(GrasaTxFTotal);
		GrasaTxFTotal.setColumns(10);
		GrasaTxFTotal.setEditable(false);
		GrasaTxFTotal.setEnabled(false);
		
		SolidosTxFTotal = new JTextField();
		SolidosTxFTotal.setBounds(454, 266, 86, 20);
		contentPane.add(SolidosTxFTotal);
		SolidosTxFTotal.setColumns(10);
		SolidosTxFTotal.setEditable(false);
		SolidosTxFTotal.setEnabled(false);
		
		JLabel lblNewLabel_9 = new JLabel("<html><body> Bonificaciones o descuentos  <br> según la Calidad Higiénica \r\n" + 
				"del Productor (UFC / ml)</body></html>");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(36, 320, 165, 58);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("<html><body> Bonificaciones según  <br> el Estatus Sanitario del hato \r\n" + 
				"productor de leche</body></html>\"");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_10.setBounds(36, 377, 165, 58);
		contentPane.add(lblNewLabel_10);
	
		List bacteria = new List();
		JComboBox valoresHigieneCB = new JComboBox();
		for (ValoresDeHigiene bacterias : ValoresDeHigiene.values()) {
			bacteria.add(bacterias.getBacterias());
		}
		valoresHigieneCB.setModel(new DefaultComboBoxModel<>(bacteria.getItems()));			
		valoresHigieneCB.setBounds(214, 333, 97, 23);
		valoresHigieneCB.setEnabled(false);
		contentPane.add(valoresHigieneCB);
		
		
		JComboBox cadenaDeFrioCB = new JComboBox();
		List cadena = new List();
		for (CadenaDeFrio frio : CadenaDeFrio.values()) {
			cadena.add(frio.getRefrigeracion());
		}
		cadenaDeFrioCB.setModel(new DefaultComboBoxModel(cadena.getItems()));
		cadenaDeFrioCB.setBounds(428, 333, 93, 23);
		cadenaDeFrioCB.setEnabled(false);
		contentPane.add(cadenaDeFrioCB);
		
		
		JComboBox certificacionSanitariaCB = new JComboBox();
		List hatos = new List();
		for (EstadoDelHato hato : EstadoDelHato.values()) {
			hatos.add(hato.getEnunciado());
		}
		certificacionSanitariaCB.setModel(new DefaultComboBoxModel(hatos.getItems()));
		certificacionSanitariaCB.setBounds(214, 389, 204, 23);
		certificacionSanitariaCB.setEnabled(false);
		contentPane.add(certificacionSanitariaCB);
		
		JComboBox certificacionBGP = new JComboBox();
		List certificados = new List();
		for (Certificacion certificacion: Certificacion.values()) {
			certificados.add(certificacion.getEnunciado());
		}
		certificacionBGP.setModel(new DefaultComboBoxModel(certificados.getItems()));
		certificacionBGP.setBounds(428, 389, 152, 23);
		certificacionBGP.setEnabled(false);
		contentPane.add(certificacionBGP);
		
		JLabel lblNewLabel_11 = new JLabel("<html><body>Rango de Ruta en Kil\u00F3metros <br> ( PLanta - Finca -  Planta)</body></html>");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_11.setBounds(139, 528, 165, 28);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Tipo de Veh\u00EDculo");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_12.setBounds(397, 528, 93, 14);
		contentPane.add(lblNewLabel_12);

		
		JComboBox kilometrosRutaCB = new JComboBox();
		List kilometros = new List();
		for (KilometrosRecorridos kilometro : KilometrosRecorridos.values()) {
			kilometros.add(kilometro.getKilometros());
		}
		kilometrosRutaCB.setModel(new DefaultComboBoxModel(kilometros.getItems()));
		kilometrosRutaCB.setBounds(185, 567, 71, 20);
		kilometrosRutaCB.setEnabled(false);
		contentPane.add(kilometrosRutaCB);
		
		JComboBox TipoDeVehiculosRB = new JComboBox();
		List vehiculos = new List();
		for (TipoDeVechiculo vehiculo : TipoDeVechiculo.values()) {
			vehiculos.add(vehiculo.getVehiculo());
		}
		TipoDeVehiculosRB.setModel(new DefaultComboBoxModel(vehiculos.getItems()));
		TipoDeVehiculosRB.setBounds(394, 567, 165, 20);
		TipoDeVehiculosRB.setEnabled(false);
		contentPane.add(TipoDeVehiculosRB);
		
		JButton btnNewButton_2 = new JButton("Limpiar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
			}
		});
		btnNewButton_2.setBounds(388, 611, 89, 23);
		btnNewButton_2.setEnabled(false);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("Calidad Composicional");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(266, 45, 165, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Concepto");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(67, 75, 85, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Valor de cada gramo ($)");
		lblNewLabel_4.setBounds(67, 100, 124, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel labelProteina = new JLabel("Proteina");
		labelProteina.setBounds(241, 75, 63, 14);
		contentPane.add(labelProteina);
		
		JLabel labelGrasa = new JLabel("Grasa");
		labelGrasa.setBounds(352, 75, 46, 14);
		contentPane.add(labelGrasa);
		
		JLabel labelSolidosT = new JLabel("Solidos totales");
		labelSolidosT.setBounds(454, 75, 86, 14);
		contentPane.add(labelSolidosT);
		
		precioProteina = new JTextField();
		precioProteina.setBounds(234, 97, 86, 20);
		precioProteina.setEnabled(false);
		contentPane.add(precioProteina);
		precioProteina.setColumns(10);
		
		precioGrasa = new JTextField();
		precioGrasa.setBounds(345, 97, 86, 20);
		contentPane.add(precioGrasa);
		precioGrasa.setEnabled(false);
		precioGrasa.setColumns(10);
		
		precioSolidosT = new JTextField();
		precioSolidosT.setBounds(454, 97, 86, 20);
		contentPane.add(precioSolidosT);
		precioSolidosT.setEnabled(false);
		precioSolidosT.setColumns(10);
		
		JLabel otrosDescuentosLabel = new JLabel("Otras bonificaciones.");
		otrosDescuentosLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		otrosDescuentosLabel.setBounds(37, 455, 154, 23);
		contentPane.add(otrosDescuentosLabel);
		
		otrasBonificaionesTXT = new JTextField();
		otrasBonificaionesTXT.setBounds(215, 456, 86, 20);
		contentPane.add(otrasBonificaionesTXT);
		otrasBonificaionesTXT.setEnabled(false);
		otrasBonificaionesTXT.setColumns(10);
		
		
		JButton generarReciboBTN = new JButton("Generar Recibo");
		generarReciboBTN.setBounds(150, 611, 129, 23);
		generarReciboBTN.setEnabled(false);
		contentPane.add(generarReciboBTN);

}
	
	/**
	 * Lector de xls 
	 */
	 
    public void leerExcelSemanal() {
      ArrayList<String> lista = new ArrayList<String>();        


        {
            File file = new File(this.fileChooser.getSelectedFile().getPath());
            FileInputStream inputStream = null;

            String datos = "";

            try {
                inputStream = new FileInputStream(file);
                POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);

                HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);

                HSSFSheet sheet = workbook.getSheetAt(0);

                Iterator<Row> rowIterator = sheet.rowIterator();
                while (rowIterator.hasNext()) {
                    HSSFRow row = (HSSFRow) rowIterator.next();
                    
                    Proveedor proveedor = new Proveedor();

                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        HSSFCell cell = (HSSFCell) cellIterator.next();
  	
                    switch (cell.toString().toLowerCase()) {
					case "tipo de envase":
						break;
					case "nombre del proveedor":
						break;
					case "jueves":
						break;
					case "viernes":
						break;
					case "sabado":
						break;
					case "domingo":
						break;
					case "lunes":
						break;
					case "martes":
						break;
					case "miércoles":
						break;
					case "total":
						break;
					case "fecha del informe":
						break;
					default:
						if (cell.toString().toLowerCase().contains("litros")||cell.toString().toLowerCase().contains("botellas")) {
							proveedor.setTipoEnvase(cell.toString());
						}else if (Pattern.matches("^[^0-9+]+$", cell.toString())) {
							proveedor.setNombre(cell.toString());
						}else if (Pattern.matches("^[0-9+]+$", cell.toString())) {
							lista.add(cell.toString());
						}						
						for (int i = 0; i < lista.size(); i++) {
							 if (i==0) {
								proveedor.setLecheJueves(lista.get(i).toString());
							}else if (i==1) {
								proveedor.setLecheViernes(lista.get(i).toString());
							}else if (i==2) {
								proveedor.setLecheSabado(lista.get(i).toString());
							}else if (i==3) {
								proveedor.setLecheDomingo(lista.get(i).toString());
							}else if (i==4) {
								proveedor.setLecheLunes(lista.get(i).toString());
							}else if (i==5) {
								proveedor.setLecheMartes(lista.get(i).toString());
							}else if (i==6) {
								proveedor.setLecheMiercoles(lista.get(i).toString());
							}
							}
						}
						
						if (Pattern.matches("^[0-9+]+[.][0]?$", cell.toString())) {
							if (proveedor.getTipoEnvase().equals("botellas")) {
								Double botellas = Double.parseDouble(cell.toString());
								Double botellasALitros = botellas*0.75;
								proveedor.setTotalLeche(botellasALitros.toString());
							}else {
								proveedor.setTotalLeche(cell.toString());	
							}
							
						}						
					}    
                    if (proveedor.getNombre() != null) {
                    	proveedores.add(proveedor);	
                    	lista.clear();
					}
                    
                }
                
              /**
               *   
               
                for (int i = 0; i < ruta.size(); i++) {
                    //Pedido pedido = new Pedido(ruta.get(i).toString(), nombre.get(i).toString(), cantLeche.get(i).toString(), envase.get(i).toString());
                    Proveedor proveedor = new Proveedor(ruta.get(i), nombre.get(i), lecheJueves, lecheViernes, lecheSabado, lecheDomingo, lecheLunes, lecheMartes, lecheMiercoles, totalLeche)
                	lista.add(pedido);
                }
               */ 
               
                // Log.d("Datos", datos);
                //Log.d("Lectura", lista.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
    
    /**
     *  Metodos matematicos
     */
    
    public void calcularProteinas(double decimasProteina , double precioProteina) {
    	this.precioProteinaGramo=precioProteina;
    	this.decimasProteina = decimasProteina;
    	this.gramosTotProteina = this.decimasProteina * 10.32;	
    	this.totalValorProteina = this.gramosTotProteina*this.precioProteinaGramo;
    	ProteinaTxF2.setText(String.valueOf(this.gramosTotProteina));
    	ProteinaTxFTotal.setText(String.valueOf(this.totalValorProteina));
    
    }
    
    public void calcularGrasas(double decimasGrasa , double precioGrasa) {
    	this.precioGrasaGramo=precioGrasa;
    	this.decimasGrasa = decimasGrasa;
    	this.gramosTotGrasa = this.decimasGrasa* 10.32;	
    	this.totalValorGrasa = this.gramosTotGrasa*this.precioGrasaGramo;
    	GrasaTxF2.setText(String.valueOf(this.gramosTotGrasa));
    	GrasaTxFTotal.setText(String.valueOf(this.totalValorGrasa));

    }
    
    public void calcularSolidosTotales(double decimasSolidos, double precioSolidos) {
    	this.precioSolidosGramo=precioSolidos;
    	this.decimasSolidosTotales = decimasSolidos;
    	this.gramosTotSolidosTotales = this.decimasSolidosTotales* 10.32;	
    	this.totalValorSolidos = this.gramosTotSolidosTotales*this.precioSolidosGramo;
    	SolidosTxF2.setText(String.valueOf(this.gramosTotSolidosTotales));
    	SolidosTxFTotal.setText(String.valueOf(this.totalValorSolidos));
  
    }
    
   
	public void calcularPagoProveedor(String nombreProveedor, double precioUnitario) {
    	Proveedor proveedor ;
    	for (int i = 0; i < proveedores.size(); i++) {
			if (nombreProveedor.contains(proveedores.get(i).getNombre())) {
				totalAPagar=precioUnitario*Double.parseDouble(proveedores.get(i).getTotalLeche());
				try {
					pdf.crearPDF(proveedores.get(i), precioUnitario, totalAPagar);
				} catch (DocumentException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	
    }
}



