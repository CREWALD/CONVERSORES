import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Conversores {

	private JFrame frmConversorAluraG;
	private JTextField txt;
	private JButton btn;
	
	@SuppressWarnings("rawtypes")
	private JComboBox cmb;	
	private JLabel lbl;
	private JButton Boton_Salir;
	public double valorInput = 0.00;	
	private JSlider sldValor;
	double valor = 1;
	private JLabel lblValor;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	
	@SuppressWarnings("rawtypes")
	private JComboBox cboDe;	
	
	@SuppressWarnings("rawtypes")
	private JComboBox cboA;	
	private JLabel lblResultado;
	String de = "C°", a = "C°";
	double temp = 0.0;

	
	
													// APLICACIÓN DE INICIO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversores window = new Conversores();
					window.frmConversorAluraG.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
													// CREAR LAS FUNCIONES
	public Conversores() {
		initialize();
	}
	
	
													// Conversor de Moneda
	// Función cambio moneda
	public enum Moneda{
	quetzal_dolar,
	quetzal_euro,
	quetzal_libraesterlina,
	dolar_quetzal,
	euro_quetzal,
	libraesterlina_quetzal  		
	}
	
	// Creando variables de monedas a convertir
	public double dolar = 7.88;
	public double euro = 8.42;
	public double quetzal = 1;
	public double libraesterlina = 9.76;
	private JLabel lblNewLabel_5;
	
	
	// Esta función es la que dara función de convertir al dar click
	public void Convertir() {
		
		// Llamando función de validar pasando lo que tiene el input
		if(Validar(txt.getText())) {
			
			Moneda moneda = (Moneda) cmb.getSelectedItem();			// se hace un casteo convirtiendolo tipo moneda
			switch (moneda) {
			
			case quetzal_dolar:
				QuetzalAMoneda(dolar);
				break;
				
			case quetzal_euro:
				QuetzalAMoneda(euro);
				break;
				
			case quetzal_libraesterlina:
				QuetzalAMoneda(libraesterlina);
				break;
				
				//Inverso
			case dolar_quetzal:
				MonedaAQuetzal(dolar);
				break;
				
			case euro_quetzal:
				MonedaAQuetzal(euro);
				break;
				
			case libraesterlina_quetzal:
				MonedaAQuetzal(libraesterlina);
				break;
				
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
			
			}				
		}				
	}
	
	// Función Quetzal A Moneda
	public void QuetzalAMoneda(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res));
	}
	
	// Función Moneda A Quetzal
	public void MonedaAQuetzal(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Redondear(res));				
	}
	
	// Función de Redondear 
	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);				
	}
	
	// Función validar si es número, si es letra no se ejecutara la conversión
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if (x > 0);
			valorInput = x;
			return true;
		} catch (NumberFormatException e) {
			lbl.setText("Solamente numeros !!");
			return false;
		}
	}	
	
		
														// Conversor de Temperatura
	
	// Función para redondear el resultado de temperatura
	public double redondear(double temp) {			
		return Math.round(temp * 100.0) / 100.0;
	}
														
	public void convertirTemperatura() {
		
		switch(de) {
		
		// GRADOS CELCIUS
		case "C°":
			if(a.equals("F°")) {
				temp = ((9 * valor) / 5) + 32;	// Operación de la conversión de C° a F°			
			}else if(a.equals("K°")) {
				temp = valor + 273.15;			// Operación de la conversión de C° a K°	
			}else {
				temp = valor;				
			}
			break;
			
		
		// GRADOS FARENHEIT
		case "F°":
			if(a.equals("C°")) {
				temp = (5 * (valor - 32)) / 9;				// Operación de la conversión de F° a C°
			}else if(a.equals("K°")) {
				temp = ((5 * (valor - 32)) / 9) + 273.15; 	// Operación de la conversión de F° a K°
			}else {
				temp = valor;				
			}
			break;
			
			
		// GRADOS KELVIN	
		case "K°":
			if(a.equals("F°")) {
				temp = ((9 * (valor - 273.15)) / 5) + 32;	// Operación de la conversión de K° a F°
			}else if(a.equals("C°")) {
				temp = valor - 273.15;
			}else {
				temp = valor;
				
			}
			break;	
		}
		
		// Impresión de resultado
		lblResultado.setText(valor + " " + de + " -> " + redondear (temp) + " " + a);
	}
	

													// CONTENIDO DEL MARCO
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		
		frmConversorAluraG = new JFrame();
		frmConversorAluraG.getContentPane().setBackground(SystemColor.activeCaption);
		frmConversorAluraG.setBackground(new Color(213, 0, 0));
		frmConversorAluraG.setFont(new Font("Baskerville Old Face", Font.BOLD | Font.ITALIC, 16));
		frmConversorAluraG.setTitle("CONVERSORES DE MONEDA Y TEMPERATURA ALURA G5");	// Titulo de la aplicación
		frmConversorAluraG.setBounds(100, 100, 594, 621);
		frmConversorAluraG.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConversorAluraG.getContentPane().setLayout(null);
		
	
		
														// CAJON CONVERSOR MONEDA		
		txt = new JTextField();
		txt.setHorizontalAlignment(SwingConstants.CENTER);
		txt.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txt.setBounds(347, 92, 140, 28);
		frmConversorAluraG.getContentPane().add(txt);
		txt.setColumns(10);
				
		
		JLabel lblNewLabel_3 = new JLabel("Ingrese el monto");
		lblNewLabel_3.setBounds(347, 67, 96, 14);
		frmConversorAluraG.getContentPane().add(lblNewLabel_3);
		
		
		// Elegir la moneda que se va a convertir
		cmb = new JComboBox<Moneda>();
		cmb.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cmb.setForeground(new Color(128, 0, 0));
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setBounds(99, 89, 137, 35);
		frmConversorAluraG.getContentPane().add(cmb);
		
		// Boton Convertir
		btn = new JButton("Convertir");
		btn.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btn.addMouseListener(new MouseAdapter() {
			
			//Función de clickeado en boton convertir
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
				
		
		btn.setBounds(147, 149, 89, 31);
		frmConversorAluraG.getContentPane().add(btn);
		
		// Monto brindado
		lbl = new JLabel("00.00");
		lbl.setBounds(306, 150, 137, 28);
		frmConversorAluraG.getContentPane().add(lbl);
				
		
		// Titulo Conversor Monedas, no se modifica es solo texto
		JLabel TituloConversorMoneda = new JLabel("CONVERSOR DE MONEDAS");
		TituloConversorMoneda.setHorizontalTextPosition(SwingConstants.CENTER);
		TituloConversorMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		TituloConversorMoneda.setForeground(Color.BLUE);
		TituloConversorMoneda.setFont(new Font("Sitka Banner", Font.BOLD | Font.ITALIC, 16));
		TituloConversorMoneda.setBounds(172, 33, 202, 20);
		frmConversorAluraG.getContentPane().add(TituloConversorMoneda);
		
		
													// CAJON CONVERSOR TEMPERATURA
		JLabel lblNewLabel = new JLabel("Valor");
		lblNewLabel.setBounds(146, 389, 46, 20);
		frmConversorAluraG.getContentPane().add(lblNewLabel);
		
		lblValor = new JLabel("1");
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setFont(new Font("Sitka Banner", Font.BOLD | Font.ITALIC, 20));
		lblValor.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblValor.setBounds(431, 394, 56, 47);
		frmConversorAluraG.getContentPane().add(lblValor);
		
		lblResultado = new JLabel("1");
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setFont(new Font("Sitka Banner", Font.BOLD | Font.ITALIC, 28));
		lblResultado.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblResultado.setBounds(148, 457, 263, 38);
		frmConversorAluraG.getContentPane().add(lblResultado);
		
		// Slider
		sldValor = new JSlider();
		sldValor.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		sldValor.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				valor = sldValor.getValue();
				lblValor.setText("" + valor);	// Función slider
				convertirTemperatura();
			}
		});
		
		
		
		sldValor.setPaintLabels(true);
		sldValor.setPaintTicks(true);
		sldValor.setValue(1);
		sldValor.setMinimum(1);
		sldValor.setBounds(148, 411, 263, 35);
		frmConversorAluraG.getContentPane().add(sldValor);
		
		lblNewLabel_1 = new JLabel("DE");
		lblNewLabel_1.setBounds(125, 351, 25, 20);
		frmConversorAluraG.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("A:");
		lblNewLabel_2.setBounds(349, 351, 25, 20);
		frmConversorAluraG.getContentPane().add(lblNewLabel_2);
		
		cboDe = new JComboBox();
		cboDe.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cboDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				de = cboDe.getSelectedItem().toString();
				convertirTemperatura();
			}
		});
		
		
		
		cboDe.setModel(new DefaultComboBoxModel(new String[] {"C°", "F°", "K°"}));
		cboDe.setBounds(146, 344, 46, 35);
		frmConversorAluraG.getContentPane().add(cboDe);
		
		cboA = new JComboBox();
		cboA.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cboA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a = cboA.getSelectedItem().toString();
				convertirTemperatura();
			}
		});			
		
		cboA.setModel(new DefaultComboBoxModel(new String[] {"C°", "F°", "K°"}));
		cboA.setBounds(365, 344, 46, 35);
		frmConversorAluraG.getContentPane().add(cboA);
		
		JLabel TituloConversorTemperatura = new JLabel("CONVERSOR DE TEMPERATURA");
		TituloConversorTemperatura.setHorizontalAlignment(SwingConstants.CENTER);
		TituloConversorTemperatura.setForeground(Color.BLUE);
		TituloConversorTemperatura.setFont(new Font("Sitka Banner", Font.BOLD | Font.ITALIC, 16));
		TituloConversorTemperatura.setBounds(158, 516, 234, 20);
		frmConversorAluraG.getContentPane().add(TituloConversorTemperatura);
				
		JLabel lblNewLabel_4 = new JLabel("Resultado");
		lblNewLabel_4.setBounds(70, 469, 63, 14);
		frmConversorAluraG.getContentPane().add(lblNewLabel_4);
		
		// Imagen agregada
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\CRYPT\\Desktop\\aluraoracle.png"));
		lblNewLabel_5.setBounds(0, 151, 578, 189);
		frmConversorAluraG.getContentPane().add(lblNewLabel_5);
		
				
		// Función Boton Salir
		Boton_Salir = new JButton("Salir");
		Boton_Salir.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Boton_Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				System.exit(0);
			}									
			
		});			
		
		Boton_Salir.setForeground(new Color(255, 128, 64));
		Boton_Salir.setFont(new Font("Sitka Banner", Font.BOLD | Font.ITALIC, 13));
		Boton_Salir.setBounds(499, 513, 69, 29);
		frmConversorAluraG.getContentPane().add(Boton_Salir);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\CRYPT\\eclipse-workspace\\Challenge_Conversor\\aluraoracle.png"));
		lblNewLabel_6.setBounds(0, 199, 578, 141);
		frmConversorAluraG.getContentPane().add(lblNewLabel_6);
	}
}