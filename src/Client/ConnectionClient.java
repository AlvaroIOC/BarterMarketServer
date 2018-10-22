/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Furial
 */
public class ConnectionClient {
  static Scanner lector = new Scanner(System.in);
    Socket socket;
     private static BufferedReader in;
    private static PrintWriter out;
      public static void main(String[] args) {
      
          ConnectionClient client = new ConnectionClient();
          
          try { 
           
        client.connectToServer();
          System.out.println(in.readLine());
          System.out.println("Send Function (Login or Create)");
          out.println(lector.next());
          out.flush();
          
          System.out.println("Send User");
          out.println(lector.next());
          out.flush();
          System.out.println("Send password");
          out.println(lector.next());
          out.flush();
          
          System.out.println(in.readLine());
          System.out.println(in.readLine()) ;
       
            
            
        } catch (IOException ex) {
            Logger.getLogger(ConnectionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
      
             
             
}
      
      
      public void connectToServer() throws IOException{
      
          
      //   System.out.println("INSERT ADDRESS");
      //   String address= lector.next();
     //    lector.nextLine();
    //     System.out.println("INSERT PORT");
   //      int port= lector.nextInt();
   //      lector.nextLine();
       socket= new Socket("localhost",9090);
          in = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
          out = new PrintWriter(socket.getOutputStream(), true);
         
         
         
          
          
      }
}
