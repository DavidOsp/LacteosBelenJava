package control;

public enum EstadoDelHato {

	SIN_UNA_ENFERMEDAD("1","Hato libre de una enfermedad"),
	SIN_DOS_ENFERMEDADES("2","Hato libre de dos enfermedades"),
	SIN_CERTIFICACION("3","Sin Certificación");
	
	private final String enunciado;
	private final String id;
	private EstadoDelHato(String id , String enunciado) {
		this.enunciado = enunciado;
		this.id=id;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public String getId() {
		return id;
	}
	
	
	
	
}
