/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h2hparsertest;

import nbch.h2h.parser.*;
/**
 *
 * @author d97roldang
 */
public class H2HParserTest {

    static String msgiso = "ISO0160000700200B23A800128E18418000000001680011A711000000000002499092112572300159712545609210000092111987654321  37501056999999905005=991210100000000008000014      11111111        00000000000    Noll's                O'Fallon     840US079000000000149116    01   40020000000000000000       400000000    000000000000000840023                       012MDS MDS +0000130311HOM61100P11563        280003000006368905            28                            030000000000150000046651500      033Noll's                   00000005001 001 0430005000014000000180000000000000000000000000?";
    
    public static void main(String[] args) {
        H2HParser parser = new H2HParser();
        String campos[] = parser.parsearMSGISO(msgiso);
        for (int i = 0 ; i < campos.length ; i++) {
            System.out.println(campos[i]);
        }
    }
}
