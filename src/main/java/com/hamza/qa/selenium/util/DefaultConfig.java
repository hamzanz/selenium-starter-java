package com.hamza.qa.selenium.util;

import java.util.Properties;

public class DefaultConfig {
    Properties configFile;

    public DefaultConfig(String configLocation){

        configFile = new java.util.Properties();
        try{
            configFile.load(this.getClass().getClassLoader().getResourceAsStream(configLocation));

        }catch(Exception ex){
            System.out.println("Failed loading the config file.");
            ex.printStackTrace();
        }
    }

    public String getProperty(String key){
        return this.configFile.getProperty(key);
    }
}
