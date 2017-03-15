import java.util.ArrayList;
import java.util.List;

public class Countries {
    private List<Country> countries;

    public Countries() {
        //Complete the constructor
        countries = new ArrayList<>();
    }

    public void addCountry(String name, String iso2code) {
        countries.add(new Country(name, iso2code));
    }

    public Country getCountryByName(String name) {

        for(Country c: countries)
            if (name.equals(c.getName()))
                return c;
        return null;
    }

    public int getCountryByIso2Code(String iso2code) {

        for(Country c: countries)
            if (iso2code.equals(c.getIso2Code()))
                return countries.indexOf(c);
        return -1;
    }

    public void setRegion(String iso2code, String region) {

        for(Country c: countries)
            if (iso2code.equals(c.getIso2Code()))
                c.setRegion(region);
    }

    public void setIncomeLevel(String region, String incomeLevel) {
        for(Country c: countries)
            if (region.equals(c.getRegion()))
                c.setIncomeLevel(incomeLevel);

    }

    public void setLifeExpectancy(String region, Double lifeExpectancy) {
        for(Country c: countries)
            if (region.equals(c.getRegion()))
                c.setLifeExpectancy(lifeExpectancy);

    }

    public String report() {

        StringBuilder result = new StringBuilder();
        for (Country c: countries){
            result.append(c.getReportRow());
        }

        return result.toString();
    }
}
