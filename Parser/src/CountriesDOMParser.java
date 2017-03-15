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

        Element doc = document.getDocumentElement();
        NodeList nodeList = doc.getChildNodes();


        int len = nodeList.getLength();
        for (int i = 0; i < len; i++) {
            Node node = nodeList.item(i);
            NodeList nl = node.getChildNodes();
            Node node_country = nl.item(1);
            Node node_value = nl.item(3);

            String cbuffer = node_country.getTextContent();
            String vbuffer = node_value.getTextContent();
            Double lifeEx = 0.0;
            if (vbuffer != null && !vbuffer.equals("")) lifeEx = Double.parseDouble(vbuffer);

            countries.setLifeExpectancy(cbuffer, lifeEx);
        }

        //Complete here...
        

    }
}
