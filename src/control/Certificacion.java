package control;

public enum Certificacion {

	CON_CERTIFICACION_BGP("1","Con certificación en BGP"),
	SIN_CERTIFICACION_BGP("2","Sin certificación en BGP");
	
	private final String enunciado;
	private final String id;
	
	private Certificacion(String id, String enunciado) {
		this.id= id;
		this.enunciado = enunciado;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public String getId() {
		return id;
	}

	
	
	
	
}
