package edu.fiuba.algo3.modelo;

import java.util.HashMap;

import java.util.Random;

public class Moto extends Vehiculo{
    @Override
    protected void initObsMap() {
        ObsMap = new HashMap<>();
        ObsMap.put(Pozo.class, (x) -> pasarPozo(x));
        ObsMap.put(Piquete.class, (x) -> pasarPiquete(x));
        ObsMap.put(Policial.class, (x) -> pasarPolicial(x));
    }

    private void pasarPozo(Obstaculo x) {
        this.movimientos += 3;
    }

    private void pasarPiquete(Obstaculo x) {
        this.movimientos += 2;
    }
    private void pasarPolicial(Obstaculo x) {
        Random rand = new Random();
        boolean val = rand.nextInt(10)<=7;
        if (val) {
            this.movimientos += 3;
        }
        this.movimientos+= 0;
    }

}
