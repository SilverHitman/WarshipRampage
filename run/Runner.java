/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Runner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new controller.MainController();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
