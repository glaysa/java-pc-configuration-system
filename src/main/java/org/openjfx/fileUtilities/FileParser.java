package org.openjfx.fileUtilities;

import org.openjfx.dataModels.PCComponents;

/** This class is responsible for parsing data. */

public class FileParser {

    public static <T> T setParser(String dataToParse){
        String[] attributes = dataToParse.split(";");
        switch (attributes.length) {
            case 5: return componentsParser(attributes);
            case 6: return configurationParser(attributes);
            default: return standardParser(dataToParse);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T standardParser(String data){
        return (T) data;
    }

    @SuppressWarnings("unchecked")
    public static <T> T componentsParser(String[] attributes){
        int a1 = Integer.parseInt(attributes[0]);
        String a2 = attributes[1];
        String a3 = attributes[2];
        String a4 = attributes[3];
        String a5 = attributes[4];

        PCComponents c = new PCComponents(a1, a2, a3, a4, a5);
        return (T) c;
    }

    public static <T> T configurationParser(String[] dataToParse){
        throw new UnsupportedOperationException("Not yet implemented");
    }
}