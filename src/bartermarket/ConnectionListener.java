/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bartermarket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.mongodb.MongoClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Furial
 */
public class ConnectionListener {
  
    static final int PORT=9090;
    private boolean end=false;
   
    public void listen(){
        ServerSocket serverSocket=null;
        Socket clientSocket = null;
        
        
        try {
              
            serverSocket= new ServerSocket(PORT);
            while(!end){
                clientSocket = serverSocket.accept();
              UserLogin login= new UserLogin(clientSocket);
              login.start();
            }           
                       
        }catch(Exception e){
            
            
        }
    }
    }
     
