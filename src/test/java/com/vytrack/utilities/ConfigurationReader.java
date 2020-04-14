package com.vytrack.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties configFile;

    static {
        try {
            //*location of properties file                      file path
            String path = System.getProperty("user.dir") + "/configuration.properties";

            //*get the file as a stream
            FileInputStream input = new FileInputStream(path);
            //FileInputStream=>opens the door get the file/data and turn it to something that java understand

            //*Create object of properties class
            configFile = new Properties(); //Properties=>allows us load our own custom properties used in program execution
            //*load properties file into properties object
            configFile.load(input);//load configuration.properties file into properties object
            //close the input stream at the end
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file!");
        }
    }
    /**
     * This method returns property value from configuration.properties file
     * @param keyName property name
     * @return property value
     */
    public static String getProperty(String keyName) {
        return configFile.getProperty(keyName);
        //when we call getProperty(key) it will give us value
        // for example:configurationReader.getProperty("browser")

    }
}
//static block will be executed only once when class will be loaded
//order 1.static block, 2.instant block, 3.constructor
