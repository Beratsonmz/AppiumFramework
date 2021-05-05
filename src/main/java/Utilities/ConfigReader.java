package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;

    /**
     * This method is used for initialize the properties from src/test/resources/Configuration/config.properties file.
     * @return properties prop obj.
     */
    public Properties initProp(){
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("src/main/java/global.properties");
            prop.load(ip);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return prop;
    }
}
