package control;

public enum ValoresDeHigiene {
	
	A25("1","0 - 25,000" ),
	A50("2","25,001 - 50,000"),
	A100("3","50,001 - 100,000"),
	A150("4","100,001 - 150,000"),
	A171("5","150,001 - 175,000"),
	A200("6","175,001 - 200,000"),
	A300("7","200,001 - 300,000"),
	A400("8","300,001 - 400,000"),
	A500("9","400,001 - 500,000"),
	A600("10","500,001 - 600,000"),
	A700("11","Mayores a 600.000");

	private final String bacterias;
	private final String id;

	private ValoresDeHigiene(String id, String bacterias) {
		this.bacterias = bacterias;
		this.id=id;
	}

	public String getBacterias() {
		return bacterias;
	}

	public String getId() {
		return id;
	}
	



	
	

}
