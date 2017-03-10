/**
 * Main class of the Java program.
 *
 */

public class Main {

    public static void main(String[] args) {
        final String url_countries = "http://services.groupkt.com/country/get/all";
        final String url_countries_info = "http://api.worldbank.org/countries/?format=xml&per_page=304";
        final String url_countries_life_expectancy = "http://api.worldbank.org/countries/all/indicators/SP.DYN.LE00.IN/?format=xml&date=2014&per_page=264";

        String countries_txt = InternetContent.get(url_countries);
        String countries_xml = InternetContent.get(url_countries_info);
        String life_expectancy_xml = InternetContent.get(url_countries_life_expectancy);
<<<<<<< HEAD

        System.out.println(countries_txt);
        System.out.println(countries_xml);
        System.out.println(life_expectancy_xml);

        /*Countries countries = new Countries();
        CountriesJSONParser.setCountries(countries,countries_txt);
        CountriesSAXParser.setWorldBankData(countries,countries_xml);
        CountriesDOMParser.setLifeExpectancy(countries,life_expectancy_xml);*/
=======
        Countries countries = new Countries();

        CountriesJSONParser.setCountries(countries,countries_txt);
        /*CountriesSAXParser.setWorldBankData(countries,countries_xml);
        CountriesDOMParser.setLifeExpectancy(countries,life_expectancy_xml);*/
        System.out.println(countries_txt);
>>>>>>> a9ee8b81319b3d4f5b30d6997635037360af1608
    }
}
