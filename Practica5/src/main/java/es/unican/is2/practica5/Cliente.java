package es.unican.is2.practica5;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	
	//WMC DE LA CLASE:	1+1+5+1 = 8
	//WMCn DE LA CLASE:	8/4 = 2
	//CBO DE LA CLASE:	3 (Cuenta, CuentaValores, CuentaAhorro, Valor)
	//DIT DE LA CLASE:	0
	//NOC DE LA CLASE:	0
	//CCog DE LA CLASE:	10 
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>(); //CBO crea objeto de tipo cuenta

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
	
	public double getSaldoTotal() { //WMC +1  TOTAL DEL METODO = 5,  Ccog = 10
		double total = 0.0;
		for (Cuenta c: Cuentas) { //WMC +1  CCog +1
			if (c instanceof CuentaAhorro) { //WMC +1    CCog +2
				total += ((CuentaAhorro) c).getSaldo(); //CBO llama a método de cuenta ahorro
			} else if (c instanceof CuentaValores)  {//WMC +1    CCog +3
				for (Valor v: ((CuentaValores) c).getValores()) { //WMC +2    CCog + 4   CBO: Llama a metodo de cuenta valores
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