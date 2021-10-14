package net.cityzen.apx;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesCons {

    static final Properties prop = new Properties();
    static {
        try {
            InputStream input = PropertiesCons.class.getClassLoader().getResourceAsStream("config.properties");
            prop.load(input);
        }
        catch (Exception io) {
            io.printStackTrace();
        }
    }
}
