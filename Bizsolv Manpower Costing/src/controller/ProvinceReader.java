package controller;

import model.Master;
import model.Province;
import model.SSS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProvinceReader {

    private Master master;
    private final String PIPE = " | ";
    private final String NEWLINE = "\n";

    public ProvinceReader(Master master) {
        this.master = master;
    }

    public void readProvince() {
        String line = "";
        master.getAllProvinces().clear();
        try (BufferedReader br = new BufferedReader(new FileReader("src/files/Province.csv"))) {
            while ((line = br.readLine()) != null) {
                // use pipe as separator
                String[] data = line.split(",");
                if(!data[0].equalsIgnoreCase("Province Name")) {
                    master.getAllProvinces().add(new Province(data[0], Float.parseFloat(data[1]), Float.parseFloat(data[2])));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
