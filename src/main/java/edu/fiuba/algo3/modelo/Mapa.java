package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.constructores.Director;
import edu.fiuba.algo3.modelo.modificadores.*;

import java.util.Random;

import java.util.LinkedList;

public class Mapa {
	public LinkedList<LinkedList<Calle>> callesHorizontales;
	public LinkedList<LinkedList<Calle>> callesVerticales;
	private int cantidadModificadoresIniciales;
	protected int dimension;

	private Obstaculos OBSTACULOS = new Obstaculos();
	private Sorpresas SORPRESAS = new Sorpresas();
	private VacioObstaculo VACIOOBSTACULO = new VacioObstaculo();
	private VacioSorpresa VACIOSORPRESA = new VacioSorpresa();

	Mapa(int dimension) {
		this.dimension = dimension;
		callesHorizontales = new LinkedList<>();
		callesVerticales = new LinkedList<>();
		cantidadModificadoresIniciales = dimension * 2;
		for (int i=0; i < dimension; i++) {
			callesHorizontales.add(new LinkedList<Calle>());
			callesVerticales.add(new LinkedList<Calle>());
			for (int j=0; j < dimension; j++) {
				callesHorizontales.get(i).add(new Calle());
				callesVerticales.get(i).add(new Calle());
			}
		}
		agregarObstaculos(cantidadModificadoresIniciales);
		agregarMeta();
		liberarElRestoDelMapa();
	}

	private void liberarElRestoDelMapa() {
		for (int i=0; i < dimension; i++){
			callesHorizontales.get(i).stream().filter(calle -> calle.obstaculo == null).forEach(calle -> calle.agregarObstaculo(VACIOOBSTACULO));
			callesVerticales.get(i).stream().filter(calle -> calle.obstaculo == null).forEach(calle -> calle.agregarObstaculo(VACIOOBSTACULO));
			callesHorizontales.get(i).stream().filter(calle -> calle.sorpresa == null).forEach(calle -> calle.agregarSorpresa(VACIOSORPRESA));
			callesVerticales.get(i).stream().filter(calle -> calle.sorpresa == null).forEach(calle -> calle.agregarSorpresa(VACIOSORPRESA));
		}
	}

	public Calle obtenerCalleHorizontal(int x, int y) {
		return callesHorizontales.get(x).get(y);
	}

	public Calle obtenerCalleVertical(int x, int y) {
		return callesVerticales.get(x).get(y);
	}

	public int dimension() {
		return dimension;
	}

	public void agregarObstaculos(Integer cantidadObstaculos) {
		int obstaculosAgregados = 0;
		while (obstaculosAgregados < cantidadObstaculos) {
			Posicion posicion = obtenerPosicionAleatoria(dimension);
			this.obtenerCalleVertical(posicion.x, posicion.y).agregarObstaculo(this.OBSTACULOS.devolverObstaculo());
			posicion = obtenerPosicionAleatoria(dimension);
			this.obtenerCalleHorizontal(posicion.x, posicion.y).agregarObstaculo(this.OBSTACULOS.devolverObstaculo());
			obstaculosAgregados += 2;
		}
	}

	public void agregarMeta() {
		Random rand = new Random();
		Calle calle = this.obtenerCalleHorizontal(dimension - 1, rand.nextInt(dimension - 1));
		calle.agregarObstaculo(new VacioObstaculo());
		calle.agregarSorpresa(new Meta());
	}

	private Posicion obtenerPosicionAleatoria(int dimension) {
		return new Posicion((int) (Math.random() * (dimension - 1)), (int) (Math.random() * (dimension - 1)), this);
	}
}
