package edu.fiuba.algo3.modelo;

public class PasoBloqueado implements Paso{
    @Override
    public void modificarPosicion(Posicion posicion, int x, int y) {
        posicion.modificarPaso(new PasoAbierto());
    }
}
