package control.util;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class FechaUtil {
    private String hora;
    private String fecha;
    

    SimpleDateFormat sdf= new SimpleDateFormat("E-dd");


    
    public void formatoDeFechaExcel() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        hora = localDate.format(formatoHora);
        fecha = localDate.format(formatoFecha);
        
    }
    
    public String getFechaCompleta() {
    	String fecha = getDiaDelMes() + "-"+ getMes()+"-"+ getAño();
    	return fecha;
    }
    
    public String getDiaDeLaSemana(){
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("EEEE");
        return localDate.format(formatoFecha);

    }

    
    public String getDiaDelMes(){
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd");
        return localDate.format(formatoFecha);
    }

    
    public String getMes(){
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("MMMM");
        return localDate.format(formatoFecha);
    }

    
    public String getAño(){
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("YYYY");
        return localDate.format(formatoFecha);
    }

}
