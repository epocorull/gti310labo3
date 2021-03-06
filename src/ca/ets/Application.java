package ca.ets;

import ca.ets.parser.ConcreteParser;
import ca.ets.solver.ConcreteSolver;
import ca.ets.writer.ConcreteWriter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * The Application class defines a template method to call the elements to
 * solve the problem Unreal-Networks is fa�ing.
 * 
 * @author Fran�ois Caron <francois.caron.7@ens.etsmtl.ca>
 */
public class Application {

	/**
	 * The Application's entry point.
	 * 
	 * The main method makes a series of calls to find a solution to the
	 * problem. The program awaits two arguments, the complete path to the
	 * input file, and the complete path to the output file.
	 * 
	 * @param args The array containing the arguments to the files.
	 */
	public static void main(String args[]) {
		System.out.println("Unreal Networks Solver !");
		ConcreteParser parser = new ConcreteParser();
		FileInformation fileInformation = (FileInformation) parser.parse(args[0]);

		ConcreteSolver solver = new ConcreteSolver();
		String[] cycles = solver.solve(fileInformation).getCyclesEulerien();

		System.out.println(Arrays.toString(cycles));

        ConcreteWriter writer = new ConcreteWriter();
        try {
            writer.write(args[1], solver.solve(fileInformation));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
