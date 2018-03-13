package ca.ets.writer;

import ca.ets.InformationKeeper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConcreteWriter implements Writer {

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
