package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.Vehiculo;

public class Meta implements ISorpresa {
    public void aplicar(Vehiculo vehiculo) {
        vehiculo.ganar();
    }
}
