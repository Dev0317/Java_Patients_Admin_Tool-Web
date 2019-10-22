package ucl.ac.uk.Spring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSV {

    public static List<Patient> parse(File file) throws IOException, ParseException, IllegalArgumentException {
        List<Patient> patients = new ArrayList<>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.trim().split(",");
                if (data.length < 20) {
                    data = Arrays.copyOf(data, 20);
                    data[19] = "";
                }
                patients.add(new Patient(data));
            }
            return patients;
        }
    }

}
