import javax.json.*;
import java.io.StringReader;

public class CountriesJSONParser {
    public static void setCountries(Countries countries, String json) {
        JsonReader jsonReader = Json.createReader(new StringReader(json));
        //Complete here...
        JsonStructure js = jsonReader.read();
        JsonObject o1 = (JsonObject) js;
        JsonObject o2 = (JsonObject) o1.get("RestResponse");
        JsonArray ja = (JsonArray) o2.get("result");

        for(JsonValue jv: ja){
            JsonObject temp = (JsonObject) jv;
            String name = temp.getString("name");
            String code = temp.getString("alpha2code");
            countries.addCountry(name, code);
        }

    }
}