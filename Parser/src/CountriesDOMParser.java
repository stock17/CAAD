import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import org.w3c.dom.*;
import javax.xml.parsers.*;

public class CountriesDOMParser {
    public static void setLifeExpectancy(Countries countries, String xml) {
        //Removing the first blank space
        xml = xml.substring(1,xml.length());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Complete here...

    }
}
