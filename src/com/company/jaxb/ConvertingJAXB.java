package com.company.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class ConvertingJAXB {

    public static <T> T readFromXML(String fileName, Class<T> tClass) {
        try {
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return tClass.cast(jaxbUnmarshaller.unmarshal(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeToXML(Object object, String fileName) {
        try {
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(object, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
