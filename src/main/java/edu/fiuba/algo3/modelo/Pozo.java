package edu.fiuba.algo3.modelo;

public class Pozo extends Obstaculo {

	public void aplicarObstaculo(Vehiculo vehiculo) {
		vehiculo.pasarPozo();
	}

}
