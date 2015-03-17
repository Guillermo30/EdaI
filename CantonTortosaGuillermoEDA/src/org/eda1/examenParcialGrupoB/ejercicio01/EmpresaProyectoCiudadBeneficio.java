package org.eda1.examenParcialGrupoB.ejercicio01;

public class EmpresaProyectoCiudadBeneficio implements Comparable<Object> {
	private String empresa;
	private String proyecto;
	private String ciudad;
	private Double beneficio;
	
	public EmpresaProyectoCiudadBeneficio(){
		this.empresa="";
		this.proyecto="";
		this.ciudad="";
		this.beneficio=0.0;
	}
	public EmpresaProyectoCiudadBeneficio(String empresa, String proyecto, String ciudad, Double beneficio){
		this.empresa=empresa;
		this.proyecto=proyecto;
		this.ciudad=ciudad;
		this.beneficio=beneficio;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public void setBeneficio(Double beneficio) {
		this.beneficio = beneficio;
	}
	public String getEmpresa() {
		return empresa;
	}
	public String getProyecto() {
		return proyecto;
	}
	public String getCiudad() {
		return ciudad;
	}
	public Double getBeneficio() {
		return beneficio;
	}
	@Override
	public int compareTo(Object otraEmpresaProyectoCiudadBeneficio) {
		EmpresaProyectoCiudadBeneficio aux = (EmpresaProyectoCiudadBeneficio) otraEmpresaProyectoCiudadBeneficio;
		return getBeneficio().compareTo(aux.getBeneficio());
	}
	
}
