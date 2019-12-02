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
        try (BufferedReader br = new BufferedReader(new FileReader("src/files/SSS.csv"))) {
            while ((line = br.readLine()) != null) {
                // use pipe as separator
                String[] data = line.split(",");
                if(!data[0].equalsIgnoreCase("Min Compensation")) {
                    master.getSSS().add(new SSS(Float.parseFloat(data[0]), Float.parseFloat(data[1]), Float.parseFloat(data[2]),
                            Float.parseFloat(data[3]), Float.parseFloat(data[4]), Float.parseFloat(data[5]),
                            Float.parseFloat(data[6])));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
