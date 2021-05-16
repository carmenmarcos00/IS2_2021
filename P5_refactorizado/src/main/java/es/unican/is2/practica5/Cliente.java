package es.unican.is2.practica5;

import java.util.LinkedList;
import java.util.List;

public class Cliente {

	//WMC DE LA CLASE:	1+1+1+3+3+1 = 10
	//WMCn DE LA CLASE: 10/6 == 1,66
	//CBO DE LA CLASE:	
	//DIT DE LA CLASE:	0
	//NOC DE LA CLASE:	0
	//CCog DE LA CLASE:	6

	public String nombre;
	public Direccion direccionCliente;
	public String telefono;
	public String dni;

	private List<Cuenta> Cuentas = new LinkedList<Cuenta>(); 

	public Cliente(String nombre, String calle, String zip, String localidad,  //WMC +1 //CCog = 0
			String telefono, String dni) { 
		this.nombre = nombre;
		this.direccionCliente = new Direccion(calle, zip, localidad);
		this.telefono = telefono;
		this.dni = dni;
	}

	//Ahora le podría pasar una direccion directamente
	public void cambiaDireccion(String calle, String zip, String localidad) {  //WMC+1 //CCog = 0
		this.direccionCliente.setCalle(calle);
		this.direccionCliente.setZip(zip);
		this.direccionCliente.setLocalidad(localidad);
	}

	public double getSaldoTotal() { //WMC+1 //CCog = 0
		return this.getSaldoCuentasAhorros() + this.getSaldoCuentasValores();
	}

	public double getSaldoCuentasAhorros() { //WMC+1 WMC TOTAL = 3 //CCog total = 3
		double totalCuentaAhorros = 0;
		for (Cuenta c: Cuentas) {  //WMC+1 //Ccog = 1
			if (c instanceof CuentaAhorro) { //WMC+1  //Ccog = 2
				totalCuentaAhorros += ((CuentaAhorro) c).getSaldo(); 
			}
		}	
		return totalCuentaAhorros;
	}

	public double getSaldoCuentasValores() { //WMC+1  //WMC TOTAL = 3 //Ccog Total = 3
		double totalCuentaValores = 0;

		for (Cuenta c: Cuentas) { //WMC+1 //Ccog = 1
			if (c instanceof CuentaValores)  { //WMC+1  //Ccog = 2
				((CuentaValores) c).getCotizacionValores();
			}
		}
		return totalCuentaValores;
	}

	public void anhadeCuenta(Cuenta c) { //WMC+1
		Cuentas.add(c);
	}

}