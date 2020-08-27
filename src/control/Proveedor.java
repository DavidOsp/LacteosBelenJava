package control;

public class Proveedor {
	
	private String tipoEnvase;
	private String nombre;
	private String lecheJueves;
	private String lecheViernes;
	private String lecheSabado;
	private String lecheDomingo;
	private String lecheLunes;
	private String lecheMartes;
	private String lecheMiercoles;
	private String totalLeche;
	
	
	public Proveedor(String tipoEnvase, String nombre, String lecheJueves, String lecheViernes, String lecheSabado,
			String lecheDomingo, String lecheLunes, String lecheMartes, String lecheMiercoles, String totalLeche) {
		super();
		this.tipoEnvase = tipoEnvase;
		this.nombre = nombre;
		this.lecheJueves = lecheJueves;
		this.lecheViernes = lecheViernes;
		this.lecheSabado = lecheSabado;
		this.lecheDomingo = lecheDomingo;
		this.lecheLunes = lecheLunes;
		this.lecheMartes = lecheMartes;
		this.lecheMiercoles = lecheMiercoles;
		this.totalLeche = totalLeche;
	}


	public Proveedor() {
		
	}


	public String getTipoEnvase() {
		return tipoEnvase;
	}


	public void setTipoEnvase(String tipoEnvase) {
		this.tipoEnvase = tipoEnvase;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getLecheJueves() {
		return lecheJueves;
	}


	public void setLecheJueves(String lecheJueves) {
		this.lecheJueves = lecheJueves;
	}


	public String getLecheViernes() {
		return lecheViernes;
	}


	public void setLecheViernes(String lecheViernes) {
		this.lecheViernes = lecheViernes;
	}


	public String getLecheSabado() {
		return lecheSabado;
	}


	public void setLecheSabado(String lecheSabado) {
		this.lecheSabado = lecheSabado;
	}


	public String getLecheDomingo() {
		return lecheDomingo;
	}


	public void setLecheDomingo(String lecheDomingo) {
		this.lecheDomingo = lecheDomingo;
	}


	public String getLecheLunes() {
		return lecheLunes;
	}


	public void setLecheLunes(String lecheLunes) {
		this.lecheLunes = lecheLunes;
	}


	public String getLecheMartes() {
		return lecheMartes;
	}


	public void setLecheMartes(String lecheMartes) {
		this.lecheMartes = lecheMartes;
	}


	public String getLecheMiercoles() {
		return lecheMiercoles;
	}


	public void setLecheMiercoles(String lecheMiercoles) {
		this.lecheMiercoles = lecheMiercoles;
	}


	public String getTotalLeche() {
		return totalLeche;
	}


	public void setTotalLeche(String totalLeche) {
		this.totalLeche = totalLeche;
	}


	@Override
	public String toString() {
		return "Proveedor [tipoEnvase=" + tipoEnvase + ", nombre=" + nombre + ", lecheJueves=" + lecheJueves
				+ ", lecheViernes=" + lecheViernes + ", lecheSabado=" + lecheSabado + ", lecheDomingo=" + lecheDomingo
				+ ", lecheLunes=" + lecheLunes + ", lecheMartes=" + lecheMartes + ", lecheMiercoles=" + lecheMiercoles
				+ ", totalLeche=" + totalLeche + "]";
	}
	
	
	
	
	
	

}
