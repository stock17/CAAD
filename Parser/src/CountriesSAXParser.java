import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.StringReader;


public class CountriesSAXParser {
    public static void setWorldBankData(Countries countries, String xml) {
        //Removing the first blank space
        xml = xml.substring(1,xml.length());
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = null;
        SAXHandler handler = new SAXHandler(countries);
        try {
            parser = parserFactor.newSAXParser();
            parser.parse(new InputSource(new StringReader(xml)), handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SAXHandler extends DefaultHandler {

    Countries saxcountries;
    String element, country, region, incomeLevel;


    public SAXHandler(Countries countries) {
        saxcountries = countries;
        element = region = "";
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //Complete here...

        if (qName.equals("wb:country")){

            Country saxCountry = saxcountries.getCountryByName(country);
            if (saxCountry != null) {
                saxCountry.setRegion(region);
                saxCountry.setIncomeLevel(incomeLevel);
            }
            element  = country = region = incomeLevel = "";
        } else if (qName.equals("wb:iso2Code")){
                element = "name";
        } else if (qName.equals("wb:name")) {
            element = "region";
        } else if (qName.equals("wb:adminregion")) {
            element = "incomeLevel";
        } else if (qName.equals("wb:region")) {
            element = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //Complete here...
        switch (element){
            case "name" :   country = new String(ch,start,length);
                element = "";
                if (country.equals("Aruba"))
                { int i = 1;}
                break;
            case "region" :   region += new String(ch,start,length);
                //element = "";
                break;
            case "incomeLevel" :   incomeLevel = new String(ch,start,length);
                element = "";
                break;
                default: break;
        }

    }
}