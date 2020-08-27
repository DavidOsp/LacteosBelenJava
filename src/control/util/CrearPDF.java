package control.util;


import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;

import org.apache.poi.ss.usermodel.ClientAnchor;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;


import Vista.Interfaz;
import control.Proveedor;

public class CrearPDF {
	


	//Proveedor instanciaProveedor, Double subtotal, Double total, FechaUtil fechaDeCreacion
	public void crearPDF(Proveedor datosProveedor, Double precioPorLitro, Double totalApagar ) throws DocumentException, MalformedURLException, IOException {
		Document documento = new Document();
		FechaUtil fechaDeCreacion = new FechaUtil();
		
		//Se indica y crea la ruta de los archivos.
		java.io.File directorio = new File(System.getProperty("user.home").toString()+"\\Documents\\Facturas Lacteos Belen "+fechaDeCreacion.getAño());
		directorio.mkdir();
		
		FileOutputStream ficheroPDF = new FileOutputStream(directorio+"/Factura_"+datosProveedor.getNombre()+"_"+fechaDeCreacion.getFechaCompleta()+".pdf");
		//FileOutputStream ficheroPDF = new FileOutputStream("Factura.pdf");
	    // Se asocia el documento de OutPutStream
	    PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);
		
	    // se configura el tamaño de la hoja 
	    
	    Rectangle one = new Rectangle(216,279);
		documento.setPageSize(one);
		// se redacta el documento 
		documento.open();		
		
		        
		PdfContentByte contentByte = writer.getDirectContent();
		BaseFont bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		contentByte.setFontAndSize(bf, 6);
		contentByte.beginText();
		
		contentByte.setTextMatrix(10, 230);
		contentByte.showText("Lacteos Belen");
		
		Image imagen = Image.getInstance(CrearPDF.class.getResource("/imgs/Logo_belen.png"));
		imagen.setAbsolutePosition(10, 235);
		contentByte.addImage(imagen);

		contentByte.setTextMatrix(130, 235);
		contentByte.showText("NIT 99827487193");

		contentByte.setTextMatrix(130, 230);
		contentByte.showText("Calle 123");
		contentByte.setTextMatrix(130, 225);
		contentByte.showText("Belén Boyacá");

		contentByte.setTextMatrix(130, 220);
		contentByte.showText("Telefono: 3103100101");
		
		contentByte.setTextMatrix(130, 215);
		contentByte.showText("Lacteosbelen@gmail.com");
		

		contentByte.setTextMatrix(10, 180);
		contentByte.showText("-------------------------------------------------------");
		
							 
		contentByte.setTextMatrix(60, 200);
		contentByte.showText("Recibo de Pago a Proveedor");
		
		contentByte.setTextMatrix(10, 180);
		contentByte.showText("-------------------------------------------------------");
		
		contentByte.setTextMatrix(10, 175);
		contentByte.showText("Nombre : " + datosProveedor.getNombre());
		
		contentByte.setTextMatrix(10, 165);
		contentByte.showText("-------------------------------------------------------");
		
		contentByte.setTextMatrix(10, 155);
		contentByte.showText("Dia de la semana ");
				
		contentByte.setTextMatrix(160, 155);
		contentByte.showText("Cantidad");
		
		contentByte.setTextMatrix(10, 135);
		contentByte.showText("Jueves");
		
		contentByte.setTextMatrix(40, 135);
		contentByte.showText ("................................  ");
		
		contentByte.setTextMatrix(170, 135);
		contentByte.showText ( datosProveedor.getLecheJueves());
		
		contentByte.setTextMatrix(10, 125);
		contentByte.showText("Viernes");
		
		contentByte.setTextMatrix(40, 125);
		contentByte.showText("................................");
		
		contentByte.setTextMatrix(170, 125);
		contentByte.showText(datosProveedor.getLecheViernes());
		
		contentByte.setTextMatrix(10, 115);
		contentByte.showText("Sábado");
		
		contentByte.setTextMatrix(40, 115);
		contentByte.showText("................................");
		
		contentByte.setTextMatrix(170, 115);
		contentByte.showText(datosProveedor.getLecheSabado());
		
		contentByte.setTextMatrix(10, 105);
		contentByte.showText("Domingo");

		contentByte.setTextMatrix(40, 105);
		contentByte.showText("................................");
		
		contentByte.setTextMatrix(170, 105);
		contentByte.showText(datosProveedor.getLecheDomingo());
		
		contentByte.setTextMatrix(10, 95);
		contentByte.showText("Lunes");

		contentByte.setTextMatrix(40, 95);
		contentByte.showText("................................");
		
		contentByte.setTextMatrix(170, 95);
		contentByte.showText(datosProveedor.getLecheLunes());
				
		contentByte.setTextMatrix(10, 85);
		contentByte.showText("Martes");

		contentByte.setTextMatrix(40, 85);
		contentByte.showText("................................");
		
		contentByte.setTextMatrix(170, 85);
		contentByte.showText(datosProveedor.getLecheMartes());		
		
		contentByte.setTextMatrix(10, 75);
		contentByte.showText("Miércoles");

		contentByte.setTextMatrix(44, 75);
		contentByte.showText("...............................");
		
		contentByte.setTextMatrix(170, 75);
		contentByte.showText(datosProveedor.getLecheMiercoles());
		
//		contentByte.setTextMatrix(10, 65);
//		contentByte.showText("Semana pagada");
//
//		contentByte.setTextMatrix(54, 65);
//		contentByte.showText("............................");
		
		contentByte.setTextMatrix(10, 45);
		contentByte.showText("-------------------------------------------------------");
		
		contentByte.setTextMatrix(160, 40);
		contentByte.showText("Valor");
		
		contentByte.setTextMatrix(10, 30);
		contentByte.showText("Cantidad total : ");

		contentByte.setTextMatrix(160, 30);
		contentByte.showText(datosProveedor.getTotalLeche());		
				
		contentByte.setTextMatrix(10, 25);
		contentByte.showText("Valor a pagar por litro : ");
		
		contentByte.setTextMatrix(160, 25);
		contentByte.showText(precioPorLitro.toString());
		
		contentByte.setTextMatrix(10, 20);
		contentByte.showText("Total a pagar :");
		
		contentByte.setTextMatrix(160, 20);
		contentByte.showText(totalApagar.toString());
		
        contentByte.endText();
        documento.close();

	}
	
}
