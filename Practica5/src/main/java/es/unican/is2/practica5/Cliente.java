package es.unican.is2.practica5;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	
	//WMC DE LA CLASE:	1+1+6+1 = 9  
	//WMCn DE LA CLASE:	9/3 = 3
	//CBO DE LA CLASE:	
	//DIT DE LA CLASE:	
	//NOC DE LA CLASE:	
	//CCog DE LA CLASE:	10 
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  //WMC +1  Ccog=0
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String zip, String localidad) { //WMC +1 CCog=0
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	public double getSaldoTotal() { //WMC +1  TOTAL DEL METODO = 6 Ccog = 10
		double total = 0.0;
		for (Cuenta c: Cuentas) { //WMC +1  CCog +1
			if (c instanceof CuentaAhorro) { //WMC +1    CCog +2
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  {//WMC +1    CCog +3
				for (Valor v: ((CuentaValores) c).getValores()) { //WMC +1    CCog + 4
					total += v.getCotizacionActual()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	public void anhadeCuenta(Cuenta c) { //WMC +1 
		Cuentas.add(c);
	}
	
}