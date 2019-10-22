package ucl.ac.uk.Spring;


import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DomainModel {
    private ArrayList<Patient> patients;

    public DomainModel() throws IOException, ParseException {
        readCSVFile(new File("Data/patients100.csv"));
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void readCSVFile(File file) throws IOException, ParseException, IllegalArgumentException {
        patients = (ArrayList<Patient>) ReadCSV.parse(file);
    }

    public ArrayList<Patient> filter(Predicate<Patient> p, ArrayList<Patient> patients) {
        if (!patients.isEmpty()) {
            return (ArrayList<Patient>) patients.stream().filter(p).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public ArrayList<Patient> filterbyId(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getId().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyBirthDate(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getBirthDate().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyDeathDate(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getDeathDate().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbySsn(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getSsn().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyDrivers(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getDrivers().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyPassport(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getPassport().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyPrefix(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getPrefix().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyFirst(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getFirst().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyLast(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getLast().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbySuffix(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getSuffix().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyMaiden(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getMaiden().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyMarital(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getMarital().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyRace(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getRace().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyEthnicity(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getEthnicity().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyGender(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getGender().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyBirthPlace(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getBirthPlace().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyAddress(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getAddress().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyCity(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getCity().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyState(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getState().contains(pattern), patients);
    }

    public ArrayList<Patient> filterbyZip(String pattern, ArrayList<Patient> patients) {
        return filter(patient -> patient.getZip().contains(pattern), patients);
    }

    public ArrayList getPopulationDistributionByRace(ArrayList<Patient> patients) {
        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();
        HashMap<String, Integer> population = new HashMap<>();
        HashSet<String> races = new HashSet<>();

        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            String city = patient.getCity();
            population.merge(city, 1, Integer::sum);
            String race = patient.getRace();
            races.add(race);
            HashMap<String, Integer> map2 = map.get(city);
            if (map2 == null) {
                map2 = new HashMap<>();
                map2.put(race, 1);
                map.put(city, map2);
            } else {
                map2.merge(race, 1, Integer::sum);
            }
        }

        int columns = races.size() + 1;
        String[] columnNames = new String[columns];
        String[][] records = new String[population.size()][columns];
        columnNames[0] = "Location";
        int i = 1;
        for (String race : races) {
            columnNames[i++] = race;
        }

        DecimalFormat df = new DecimalFormat(".##");
        int j = 0;
        for (Map.Entry<String, HashMap<String, Integer>> entry : map.entrySet()) {
            String[] record = new String[columns];
            HashMap<String, Integer> stateData = entry.getValue();
            String city = entry.getKey();
            record[0] = city;
            for (int k = 1; k < columns; k++) {
                Integer value = stateData.get(columnNames[k]);
                if (value == null) {
                    record[k] = "0%";
                } else {
                    record[k] = df.format((double) value / population.get(city) * 100) + "%";
                }
            }
            records[j++] = record;
        }

        ArrayList tuple = new ArrayList();
        tuple.add(columnNames);
        tuple.add(records);
        return tuple;
    }

}
