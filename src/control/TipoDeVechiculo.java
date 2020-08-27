package control;

public enum TipoDeVechiculo {
	
	TRACTO_CAMION("1","Tracto camion"),
	CAMION_GRANDE_TANQUE("2","Camion grande tanque"),	
	CAMION_GRANDE_CANTINAS("3","Camion grande cantinas"),	
	CAMION_PEQUEÑO_TANQUE("4","Camion pequeño tanque"),	
	CAMION_PEQUEÑO_CANTINAS("5","Camion pequeño cantinas"),
	NINGUNO("0","Ninguno");
	
	
	private final String vehiculo;
	private final String id;

	
	private TipoDeVechiculo(String id ,String vehiculo) {
		this.vehiculo = vehiculo;
		this.id= id;
	}


	public String getVehiculo() {
		return vehiculo;
	}


	public String getId() {
		return id;
	}
	
	
	


}
