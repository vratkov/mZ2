package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;

public class averagePriceHandler extends DefaultHandler {

    boolean isElement;
    int count = 0;
    double sum = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if(qName.equals("PRICE_VAT")) {
            isElement = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("PRICE_VAT")) {
            isElement = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (isElement) {
            String price = new String(ch, start,length);
            if(length>0) {
                double fPrice = Double.parseDouble(price);
                sum = sum + fPrice;
                count ++;
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {

        System.out.println("Count of Hobby items: " + count);
        System.out.println("Sum of Hobby items " + sum);
        BigDecimal value = new BigDecimal((sum/count));
        value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("Average price Hobby items: " + value);

    }
}