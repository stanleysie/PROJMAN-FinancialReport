package controller;

import model.Master;
import model.SSS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SSSReader {
    private Master master;
    private final String PIPE = " | ";
    private final String NEWLINE = "\n";

    public SSSReader(Master master) {
        this.master = master;
    }

    public void readSSS() {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src/files/SSS.psv"))) {
            while ((line = br.readLine()) != null) {
                // use pipe as separator
                String[] data = line.split("\\|");
                master.getSSS().add(new SSS(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]),
                                        Double.parseDouble(data[3]), Double.parseDouble(data[4]), Double.parseDouble(data[5]),
                                        Double.parseDouble(data[6])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
