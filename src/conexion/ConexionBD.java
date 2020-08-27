package conexion;

import java.io.File;
import java.math.BigDecimal;
import java.sql.*;



public class ConexionBD {
	
	Connection connection;

	public Connection ConexionBD() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 connection=DriverManager.getConnection("jdbc:mysql://localhost/lacteosdb","root","");			 	
			 
		} catch (SQLException e) {
			System.out.println("ERROR EN CONEXON A BASE DE DATOS");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("CLASE NO ENCONTRADA DRIVERSQL");
		}
		
		
		return connection;
	}
	
	public Double consultaDescuentoPorTransporte(String idKilometros, String idVehiculos) throws SQLException{
		Statement st;
		ResultSet rs;
		Double descuento = 0.00;
		
			st=connection.createStatement();
			rs=st.executeQuery("SELECT  `descuento` FROM `descuento_por_transporte` WHERE id_kilometros ="
					+ idKilometros+ " and id_vehiculos ="+ idVehiculos);
			while (rs.next()){
				descuento= rs.getDouble("descuento");	
			}	
			return descuento;
	}
	
	public Double consultaValoresDeHigiene(String id){
		Statement st;
		ResultSet rs;
		Double valor=0.00;
		try {
			st=connection.createStatement();
			rs=st.executeQuery("SELECT  `valor_a_pagar` FROM `valores_de_higiene` WHERE id ="+ id);
			
			while (rs.next()) {
				valor = rs.getDouble("valor_a_pagar");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valor;
	}

	
	
	public Double consultaCadenaDeFrio(String idBacterias){
		Statement st;
		ResultSet rs;
		Double retorno=0.00;
		try {
			st=connection.createStatement();
			rs=st.executeQuery("SELECT `bonificacion` FROM `cadena_de_frio` WHERE id_bacterias = "+ idBacterias);
			
			while (rs.next()) {
			retorno = rs.getDouble("bonificacion");		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public Double consultaCertificadoBPG(String idCertificado){
		Statement st;
		ResultSet rs;
		Double retorno= 0.00;
		try {
			st=connection.createStatement();
			rs=st.executeQuery("SELECT `valor` FROM `certificado_bpg` WHERE id = "+ idCertificado);
			
			while (rs.next()) {
			retorno = rs.getDouble("valor");		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
		
		public Double consultaCertificadoSanitario(String id){
			Statement st;
			ResultSet rs;
			double retorno = 0.00;
			try {
				st=connection.createStatement();
				rs=st.executeQuery("SELECT  `valor` FROM `certificado_hato` WHERE id = "+ id);
				
				while (rs.next()) {
					
					retorno = rs.getDouble("valor");

				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return retorno;

	}

		public void cerrarConexion() {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


}
