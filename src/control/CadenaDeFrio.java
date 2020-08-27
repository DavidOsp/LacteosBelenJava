package control;

public enum CadenaDeFrio {
	
	CON_FRIO("1","Con Frio"),
	SIN_FRIO("2","Sin Frio");
	
	
	private final String Refrigeracion;
	private final String id;
	
	private CadenaDeFrio(String id,String refrigeracion) {
		this.Refrigeracion = refrigeracion;
		this.id=id;
	}
	
	public String getRefrigeracion() {
		return Refrigeracion;
	}
	public String getId() {
		return id;
	}
	
	

}
