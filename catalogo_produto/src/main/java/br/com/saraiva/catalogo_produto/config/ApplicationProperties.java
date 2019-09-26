package br.com.saraiva.catalogo_produto.config;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ApplicationProperties
 */
public enum ApplicationProperties {
    INSTANCE;

    private final Properties
     properties;

    ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public String getUrlServiceCarrinho() {
        return properties.getProperty("url.service.carrinho");
    }
    
}