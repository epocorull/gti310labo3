package ca.ets.writer;

import ca.ets.InformationKeeper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ConcreteWriter implements the Writer interface.
 *
 * @author Elsa Pocorull, Valentin Gaillard
 */
public class ConcreteWriter implements Writer {

    /**
     * This method is designed to write a solution into an output file.
     *
     * @param filename The complete path to the file that will be created.
     * @param cycles An instance of the InformationKeeper class.
     * @throws IOException
     */
    @Override
    public void write(String filename, InformationKeeper cycles) throws IOException {
        File file=new File(filename);
        FileWriter fileWriter= null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String aCyclesEulerien : cycles.cyclesEulerien) {
            fileWriter.write(aCyclesEulerien);
            fileWriter.write("\n");
        }

        assert fileWriter != null;
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
