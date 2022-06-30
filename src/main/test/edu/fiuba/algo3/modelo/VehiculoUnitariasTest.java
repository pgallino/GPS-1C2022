package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.modificadores.ControlPolicial;
import org.junit.jupiter.api.Test;

import javax.naming.ldap.Control;

import static org.mockito.Mockito.*;

public class VehiculoUnitariasTest {
    @Test
    public void VehiculoMover() {
        Posicion posicion = mock(Posicion.class);
        Direccion direccion = mock(Direccion.class);
        Vehiculo vehiculo = new Vehiculo(0, posicion);

        vehiculo.mover(direccion);

        assert (vehiculo.movimientos == 1);
        verify(direccion, times(1)).mover(posicion, vehiculo);
    }
    @Test
    public void VehiculoAplicarEstado() {
        Estado estado = mock(Estado.class);
        Vehiculo vehiculo = new Vehiculo(0, null);

        vehiculo.aplicarEstado(estado);

        assert(vehiculo.estado == estado);
    }
    @Test
    public void VehiculoIncrementarMovimiento() {
        Vehiculo vehiculo = new Vehiculo(0, null);

        vehiculo.incrementarMovimientos(1);

        assert(vehiculo.movimientos == 1);
    }
    @Test
    public void VehiculoAplicarSorpresaDesfavorable() {
        Vehiculo vehiculo = new Vehiculo(10, null);

        vehiculo.aplicarSorpresaPuntaje(1.25);

        assert(vehiculo.movimientos == (10*1.25));
    }
    @Test
    public void VehiculoAplicarSorpresaFavorable() {
        Vehiculo vehiculo = new Vehiculo(10, null);

        vehiculo.aplicarSorpresaPuntaje(0.8);

        assert (vehiculo.movimientos == (10 * 0.8));
    }
    @Test
    public void VehiculoAplicarSorpresaCambioVehiculo() {
        Estado estado = mock(Estado.class);
        Vehiculo vehiculo = new Vehiculo(0, null);
        vehiculo.aplicarEstado(estado);

        vehiculo.aplicarSorpresaCambioVehiculo();

        verify(estado, times(1)).aplicarSorpresaCambioVehiculo();
    }
    @Test
    public void VehiculoPasarPozo() {
        Estado estado = mock(Estado.class);
        when(estado.obtenerPenalizacionPozo()).thenReturn((double)3);
        Vehiculo vehiculo = new Vehiculo(0, null);
        vehiculo.aplicarEstado(estado);

        vehiculo.pasarPozo(estado.obtenerPenalizacionPozo());

        verify(estado, times(1)).pasarPozo(estado.obtenerPenalizacionPozo());
    }
    @Test
    public void VehiculoPasarPiquete() {
        Estado estado = mock(Estado.class);
        when(estado.obtenerPenalizacionPiquete()).thenReturn((double)0);
        Vehiculo vehiculo = new Vehiculo(0, null);
        vehiculo.aplicarEstado(estado);

        vehiculo.pasarPiquete(estado.obtenerPenalizacionPiquete());

        verify(estado, times(1)).pasarPiquete(estado.obtenerPenalizacionPiquete());
    }
    @Test
    public void VehiculoPasarControl() {
        Estado estado = mock(Estado.class);
        ControlPolicial controlPolicial = mock(ControlPolicial.class);
        Vehiculo vehiculo = new Vehiculo(0, null);
        vehiculo.aplicarEstado(estado);

        when(estado.obtenerProbabilidadControl()).thenReturn((double)5);
        when(estado.obtenerPenalizacionControl()).thenReturn((double)3);
        when(controlPolicial.pasoControlAleatorio()).thenReturn((double)1);

        vehiculo.pasarControlPolicial(estado.obtenerPenalizacionControl(), estado.obtenerProbabilidadControl(), controlPolicial.pasoControlAleatorio());

        verify(estado, times(1)).pasarControlPolicial(estado.obtenerPenalizacionControl(), estado.obtenerProbabilidadControl(), controlPolicial.pasoControlAleatorio());
    }
    @Test
    public void VehiculoPasarVacio() {
        Estado estado = mock(Estado.class);
        Vehiculo vehiculo = new Vehiculo(0, null);
        vehiculo.aplicarEstado(estado);

        vehiculo.aplicarVacio();

        verify(estado, times(1)).pasarVacio();
    }
}