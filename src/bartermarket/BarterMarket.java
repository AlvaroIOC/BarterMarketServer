/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bartermarket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Furial
 */
public class BarterMarket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
          ConnectionListener listener= new ConnectionListener();
          listener.listen();
             
             
}
        
        
        // TODO code application logic here
   
    
}
