package uniminuto.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reporte {
	
	private String areasUniminuto;
	private String nivelSatisfaccion;
	private String fechainitemp;
	private String fechafintemp;
	private String errorValidFormu;
	
	public Reporte() {	
	}

	public String getAreasUniminuto() {
		return areasUniminuto;
	}

	public void setAreasUniminuto(String areasUniminuto) {
		this.areasUniminuto = areasUniminuto;
	}

	public String getNivelSatisfaccion() {
		return nivelSatisfaccion;
	}

	public void setNivelSatisfaccion(String nivelSatisfaccion) {
		this.nivelSatisfaccion = nivelSatisfaccion;
	}

	public String getFechainitemp() {
		return fechainitemp;
	}

	public void setFechainitemp(String fechainitemp) {
		this.fechainitemp = fechainitemp;
	}

	public String getFechafintemp() {
		return fechafintemp;
	}

	public void setFechafintemp(String fechafintemp) {
		this.fechafintemp = fechafintemp;
	}
	
	public String getErrorValidFormu() {
		return errorValidFormu;
	}

	public void setErrorValidFormu(String errorValidFormu) {
		this.errorValidFormu = errorValidFormu;
	}

	@Override
	public String toString() {
		return "Reporte [areasUniminuto=" + areasUniminuto + ", nivelSatisfaccion=" + nivelSatisfaccion
				+ ", fechainitemp=" + fechainitemp + ", fechafintemp=" + fechafintemp + ", errorValidFormu="
				+ errorValidFormu + "]";
	}

}
