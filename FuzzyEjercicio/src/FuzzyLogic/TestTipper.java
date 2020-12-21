package FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.Gpr;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;


public class TestTipper {

	public static void main(String[] args) throws Exception {
		//para cargar un archivo FCL
		String fileName = "C:\\Users\\DAMIAN\\eclipse-workspace\\FuzzyEjercicio\\src\\FuzzyLogic\\tipper.fcl";
		FIS fis = FIS.load(fileName, true);
		
		//Si se detecta un error al cargar o analizar el archivo FCL, se FIS.load()devuelve null
		if (fis == null) { 
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}

		// Ahora podemos graficar todas las variables en el FIZ (cada LinguisticTerm en cada Variable en el FUNCTION_BLOCK). Esto puede resultar útil cuando esté depurando su código.
		FunctionBlock functionBlock = fis.getFunctionBlock(null);
		JFuzzyChart.get().chart(functionBlock);

		// Para hacer cálculos reales y ejecutar el FIS, debemos configurar las variables de entrada
		functionBlock.setVariable("service", 3);
		functionBlock.setVariable("food", 7);

		// Ahora podemos ejecutar el sistema
		functionBlock.evaluate();

		// Si queremos conocer la salida del sistema, podemos leer los valores de la variable de salida 
		//(en este caso, solo hay una variable de salida 'tip')
		Variable tip = functionBlock.getVariable("tip");
		JFuzzyChart.get().chart(tip, tip.getDefuzzifier(), true);
		Gpr.debug("poor[service]: " + functionBlock.getVariable("service").getMembership("poor"));

		// Print ruleSet
		System.out.println(functionBlock);
		System.out.println("TIP:" + functionBlock.getVariable("tip").getValue());
	}
}
