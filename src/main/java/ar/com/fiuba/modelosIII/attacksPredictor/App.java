package ar.com.fiuba.modelosIII.attacksPredictor;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.Selection;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Factory;

public class App 
{
    public static void main (String[] args)
    {
    		Factory.makeEnviorement();
    		Selection.execute();
    }
}
