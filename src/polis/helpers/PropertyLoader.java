package polis.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/** 
 * Singleton klasse verantwoordelijk voor het inladen van de properties bestanden. Andere klasse kunnen een
 * property opvragen via de static methode getProperty.
 * **/
public class PropertyLoader {

    private static final Map<String, Properties> map = new HashMap<>();
    private static final PropertyLoader p = new PropertyLoader();

    public PropertyLoader(){
        loadProperties("engine");
        loadProperties("levels");
    }

    private void loadProperties(String name){
        Properties properties = new Properties();
        String location = "/polis/" + name + ".properties";
        try (InputStream in = PropertyLoader.class.getResourceAsStream(location)) {
            properties.load(in);
        } catch (IOException ex) {
            System.out.println("Couldn't read: " + ex + " in " + location);
        }
        map.put(name,properties);
    }

    public static String getProperty(String file, String name){
        return map.get(file).getProperty(name);
    }

}
