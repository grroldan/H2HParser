/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nbch.h2h.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author d97roldang
 */
public class H2HParser {
    private String posiciones = "";
    private String[] campos = new String[135];
    private String camposParm[] = new String[128];
    
    public H2HParser() {
        this.leerparms();
    }
    
    public String[] parsearMSGISO(String msgiso) {
        String[] longitudes = null;
        int pos = 32;
        int hasta = 0;
        campos[0] = msgiso.substring(0, 3);
        campos[1] = msgiso.substring(3, 5);
        campos[2] = msgiso.substring(5, 7);
        campos[3] = msgiso.substring(7, 10);
        campos[4] = msgiso.substring(10, 12);
        campos[5] = msgiso.substring(13, 16);
        campos[6] = msgiso.substring(16, 32);
        
        posiciones = this.HextoBin(campos[6]);
        if (posiciones.substring(0, 1).equals("1")) {
            campos[7] = msgiso.substring(32, 48);
            posiciones = posiciones + this.HextoBin(campos[7]);
        }else {            
            posiciones = String.format("%-128s", posiciones).replace(' ', '0');
        }
        
        char[] bitc = posiciones.toCharArray();        
         for(int i=0; i <= 127; i++) {
             if (bitc[i] == '1') {
                longitudes = camposParm[i].split(";");
                if (longitudes[1].equals("F")) {
                   hasta =  Integer.parseInt(longitudes[2]) + pos;
                   campos[i + 7] = msgiso.substring(pos, hasta);
                   pos += Integer.parseInt(longitudes[2]);
                }else {
                    hasta = pos + Integer.parseInt(longitudes[2]);
                    hasta += Integer.parseInt(msgiso.substring(pos, hasta));
                            
                    campos[i + 7] = msgiso.substring(pos, hasta);
                    pos = hasta;
                }
             } else {
                 campos[i + 7] = "No Informado";
             }  
         }
        return campos;
    }  
   
    
    private String HextoBin(String bitmap) {
        String campos = "";
        char[] arr1 = bitmap.toCharArray();        
        for(int i=0; i <= 15; i++) {
            
            if (arr1[i] == '0')
                campos = campos + "0000";            
            if (arr1[i] == '1')
                campos = campos + "0001";            
            if (arr1[i] == '2')
                campos = campos + "0010";            
            if (arr1[i] == '3')
                campos = campos + "0011";            
            if (arr1[i] == '4')
                campos = campos + "0100";            
            if (arr1[i] == '5')
                campos = campos + "0101";            
            if (arr1[i] == '6')
                campos = campos + "0110";            
            if (arr1[i] == '7')
                campos = campos + "0111";            
            if (arr1[i] == '8')
                campos = campos + "1000";            
            if (arr1[i] == '9')
                campos = campos + "1001";            
            if (arr1[i] == 'A')
                campos = campos + "1010";            
            if (arr1[i] == 'B')
                campos = campos + "1011";            
            if (arr1[i] == 'C')
                campos = campos + "1100";            
            if (arr1[i] == 'D')
                campos = campos + "1101";            
            if (arr1[i] == 'E')
                campos = campos + "1110";            
            if (arr1[i] == 'F')
                campos = campos + "1111";
        }
        
        return campos;

    }
    
    private void leerparms() {
        Properties propiedades = new Properties();        
        String directorio = System.getProperty("user.dir") + "\\camposlink.properties";
        try {
            propiedades.load(new FileInputStream(directorio));
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        for(int i=1; i <= 128; i++) {
            camposParm[i-1] = propiedades.getProperty(String.valueOf(i));
        }
        
        
    }
}
