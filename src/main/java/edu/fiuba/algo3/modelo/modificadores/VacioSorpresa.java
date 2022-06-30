package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.Vehiculo;

public class VacioSorpresa implements ISorpresa {
    @Override
    public void aplicar(Vehiculo vehiculo) {
        vehiculo.aplicarVacio();
    }
}
