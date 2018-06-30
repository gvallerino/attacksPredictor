package ar.com.fiuba.modelosIII.attacksPredictor;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.GeneticAlgorithmElite;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.GeneticAlgorithmRandom;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Factory;

public class App 
{
    public static void main (String[] args)
    {
    		Factory.makeEnviorement();
    		GeneticAlgorithmRandom.execute();
//    		GeneticAlgorithmElite.execute();
    }
}
