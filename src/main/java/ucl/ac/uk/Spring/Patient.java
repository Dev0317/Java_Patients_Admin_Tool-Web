package ucl.ac.uk.Spring;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Patient {

    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    private String id;
    private Date birthDate;
    private Date deathDate;
    private String ssn;
    private String drivers = "";
    private String passport = "";
    private String prefix = "";
    private String first;
    private String last;
    private String suffix = "";
    private String maiden = "";
    private String marital = "";
    private String race;
    private String ethnicity;
    private String gender;
    private String birthPlace;
    private String address;
    private String city;
    private String state;
    private String zip = "";

    public Patient(String[] data) throws ParseException {
        setId(data[0]);
        setBirthDate(data[1]);
        setDeathDate(data[2]);
        setSsn(data[3]);
        setDrivers(data[4]);
        setPassport(data[5]);
        setPrefix(data[6]);
        setFirst(data[7]);
        setLast(data[8]);
        setSuffix(data[9]);
        setMaiden(data[10]);
        setMarital(data[11]);
        setRace(data[12]);
        setEthnicity(data[13]);
        setGender(data[14]);
        setBirthPlace(data[15]);
        setAddress(data[16]);
        setCity(data[17]);
        setState(data[18]);
        setZip(data[19]);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws IllegalArgumentException {
        if (id.matches("[a-z0-9]{8}-([a-z0-9]{4}-){3}[a-z0-9]{12}")) {
            this.id = id;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getBirthDate() {
        return format.format(birthDate);
    }

    public void setBirthDate(String birthDate) throws ParseException, IllegalArgumentException {
        if (birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            Date date = format.parse(birthDate);
            if (date.before(new Date())) {
                this.birthDate = date;
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getDeathDate() {
        return (deathDate == null)? "":format.format(deathDate);
    }

    public void setDeathDate(String deathDate) throws ParseException {
        if (deathDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            Date date = format.parse(deathDate);
            if (date.after(this.birthDate)) {
                this.deathDate = date;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) throws IllegalArgumentException {
        if (ssn.matches("\\d{3}-\\d{2}-\\d{4}")) {
            this.ssn = ssn;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getDrivers() {
        return drivers;
    }

    public void setDrivers(String drivers) {
        if (drivers.matches("S\\d{8}")) {
            this.drivers = drivers;
        }
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        if (passport.matches("X\\d{8}X")) {
            this.passport = passport;
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getMaiden() {
        return maiden;
    }

    public void setMaiden(String maiden) {
        this.maiden = maiden;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        if (marital.matches("[MS]")) {
            this.marital = marital;
        }
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        if (zip.matches("\\d{5}")) {
            this.zip = zip;
        }
    }

    @Override
    public String toString() {
        return getFirst();
    }
}

