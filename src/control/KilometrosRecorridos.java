package control;

public enum KilometrosRecorridos {
	
	RANGO1("1","0 - 25"),
	RANGO2("2","26 - 50"),
	RANGO3("3","51 - 75"),
	RANGO4("4","76 - 100"),
	RANGO5("5","101 - 125"),
	RANGO6("6","126 - 150"),
	RANGO7("7","151 - 175"),
	RANGO8("8","176 - 200"),
	RANGO9("9","201 - 225"),
	RANGO10("10","226 - 250"),
	RANGO11("11","251 - 275"),
	RANGO12("12","276 - 300"),
	RANGO13("13","301 - 325"),
	RANGO14("14","326 - 350"),
	RANGO15("15","351 - 3752"),
	RANGO16("16","376 - 400");
	
	private final String kilometros;
	private final String id;
	
	private KilometrosRecorridos(String id, String kilometros) {
		this.kilometros = kilometros;
		this.id=id;
		
	}

	public String getKilometros() {
		return kilometros;
	}

	public String getId() {
		return id;
	}
	
	
	
	


}
