package ucl.ac.uk.Spring;


import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

@Controller
@RequestMapping
public class SpringController {

    private DomainModel model;

    @GetMapping("/")
    public String index() {
        try {
            model = new DomainModel();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return "test";
    }

    @RequestMapping("/test")
    public ResponseEntity<String> handleRequest (RequestEntity<String> requestEntity) {

        String[] requestBody = requestEntity.getBody().split("=");
        StringBuilder responseBody = new StringBuilder();

        if (requestBody[0].equals("loaded")) {
            ArrayList<Patient> patients = model.getPatients();
            for (Patient patient : patients) {
                responseBody.append(patient.getFirst()).append(":").append(patient.getId()).append(",");
            }

            ArrayList stats = model.getPopulationDistributionByRace(patients);

            String[] columnNames = (String[]) stats.get(0);
            String[][] records = (String[][]) stats.get(1);

            responseBody.append("/");

            for (String columnName : columnNames) {
                responseBody.append(columnName).append(",");
            }

            for (String[] record : records) {
                responseBody.append("!");
                for (String field : record) {
                    responseBody.append(field).append(",");
                }
            }

        } else if (requestBody[0].equals("patient")) {
            ArrayList<Patient> patients = model.getPatients();
            String id = requestBody[1];
            for (Patient patient : patients) {
                if (patient.getId().matches(id)) {
                    responseBody.append(id).append(",");
                    responseBody.append(patient.getBirthDate()).append(",");
                    responseBody.append(patient.getDeathDate()).append(",");
                    responseBody.append(patient.getSsn()).append(",");
                    responseBody.append(patient.getDrivers()).append(",");
                    responseBody.append(patient.getPassport()).append(",");
                    responseBody.append(patient.getPrefix()).append(",");
                    responseBody.append(patient.getFirst()).append(",");
                    responseBody.append(patient.getLast()).append(",");
                    responseBody.append(patient.getSuffix()).append(",");
                    responseBody.append(patient.getMaiden()).append(",");
                    responseBody.append(patient.getMarital()).append(",");
                    responseBody.append(patient.getEthnicity()).append(",");
                    responseBody.append(patient.getGender()).append(",");
                    responseBody.append(patient.getRace()).append(",");
                    responseBody.append(patient.getBirthPlace()).append(",");
                    responseBody.append(patient.getAddress()).append(",");
                    responseBody.append(patient.getCity()).append(",");
                    responseBody.append(patient.getState()).append(",");
                    responseBody.append(patient.getZip()).append(",");
                }
            }
        } else if (requestBody[0].equals("search")) {
            ArrayList<Patient> patients = model.getPatients();

            String[] searchData = requestBody[1].split("%2C");

            String pattern = searchData[0];

            for (int i = 1; i < searchData.length; i++) {
                patients = filterBy(patients, pattern, searchData[i]);
            }

            for (Patient patient : patients) {
                responseBody.append(patient.getFirst()).append(":").append(patient.getId()).append(",");
            }

            ArrayList stats = model.getPopulationDistributionByRace(patients);

            String[] columnNames = (String[]) stats.get(0);
            String[][] records = (String[][]) stats.get(1);

            responseBody.append("/");

            for (String columnName : columnNames) {
                responseBody.append(columnName).append(",");
            }

            for (String[] record : records) {
                responseBody.append("!");
                for (String field : record) {
                    responseBody.append(field).append(",");
                }
            }

        }

        return new ResponseEntity<>(responseBody.toString(), HttpStatus.OK);
    }

    private ArrayList<Patient> filterBy(ArrayList<Patient> patients, String pattern, String searchField) {
        switch (searchField) {
            case "Id":
                return model.filterbyId(pattern, patients);
            case "Birthdate":
                return model.filterbyBirthDate(pattern, patients);
            case "Deathdate":
                return model.filterbyDeathDate(pattern, patients);
            case "Ssn":
                return model.filterbySsn(pattern, patients);
            case "Drivers":
                return model.filterbyDrivers(pattern, patients);
            case "Passport":
                return model.filterbyPassport(pattern, patients);
            case "Prefix":
                return model.filterbyPrefix(pattern, patients);
            case "First":
                return model.filterbyFirst(pattern, patients);
            case "Last":
                return model.filterbyLast(pattern, patients);
            case "Suffix":
                return model.filterbySuffix(pattern, patients);
            case "Maiden":
                return model.filterbyMaiden(pattern, patients);
            case "Marital":
                return model.filterbyMarital(pattern, patients);
            case "Ethnicity":
                return model.filterbyEthnicity(pattern, patients);
            case "Gender":
                return model.filterbyGender(pattern, patients);
            case "Race":
                return model.filterbyRace(pattern, patients);
            case "Birthplace":
                return model.filterbyBirthPlace(pattern, patients);
            case "Address":
                return model.filterbyAddress(pattern, patients);
            case "City":
                return model.filterbyCity(pattern, patients);
            case "State":
                return model.filterbyState(pattern, patients);
            case "Zip":
                return model.filterbyZip(pattern, patients);
            default:
                return patients;
        }
    }

}
