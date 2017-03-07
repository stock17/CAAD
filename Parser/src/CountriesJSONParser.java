import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonString;
import java.io.StringReader;

public class CountriesJSONParser {
    public static void setCountries(Countries countries, String json) {
        JsonReader jsonReader = Json.createReader(new StringReader(json));
        //Complete here...
    }
}