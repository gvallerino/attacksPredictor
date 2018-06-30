package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution;

import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.configurations.TypeEvolution;
import ar.com.fiuba.modelosIII.attacksPredictor.configurations.TypeFilter;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.Population;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.Cruzable;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.mutation.Mutation;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Logger;

public abstract class GeneticAlgorithm {
	
	protected static Cruzable cruza;
	protected static Mutation mutation;
	
	protected static List<TerroristAttack> poblacion;
	protected static int cantidadGeneraciones;
	
	protected static ManagerClusterRandom managerCluster;
	
	protected static void loadConfigurations() {
		Logger.printHeader("cargando configuraciones");
		cruza = TypeEvolution.getCruza();
		mutation  = TypeEvolution.getMutation();
		Logger.printEnter();
	}
	
	protected static void loadPopulation() {
		Population population = new Population();
		List<TerroristAttack> filter = getFilters();
		poblacion = population.populate(filter);
	}
	
	protected static void loadManagerCluster() {
		managerCluster = new ManagerClusterRandom(poblacion);
	}

	protected static List<TerroristAttack> getFilters() {
		return TypeFilter.getInstance().applyFilters();
	}
	
	protected static boolean mutate() {
		return Constants.getRandom(Constants.PORCENTAJE_MUTATION);
	}
	
	protected static void loadCountGenerations() {
		cantidadGeneraciones = Constants.COUNT_GENERATIONS;
		if (cantidadGeneraciones > Constants.COUNT_GENERATIONS_MAX) {
			cantidadGeneraciones = Constants.COUNT_GENERATIONS_MAX;
		}
	}
	
	protected static void closeGeneration(int generacion) {
		managerCluster.updateCentroides(generacion);
		managerCluster.saveClusters(generacion);
		managerCluster.printInercia();
	}
	
	protected static void printHeader(String type, int sizePoblacion) {
		Logger.printHeader("comenzando algoritmo genético "+ type);
		Logger.printEnter();
		Logger.print("Tamaño de generaciones: " + sizePoblacion + " ataques terroristas.");
		Logger.printEnter();
	}
	
	protected static void printGeneration(int generacion) {
		Logger.print("Procesando Generacion: " + (generacion + 1) + " => ");
	}
	
}
