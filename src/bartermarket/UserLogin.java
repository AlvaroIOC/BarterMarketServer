/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bartermarket;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.*;
import org.bson.Document;
import org.bson.conversions.Bson;


/**
 *
 * @author Furial
 */
public class UserLogin extends Thread {
    private Socket socket;
    private String userName="Alvaro";
    private String password = "IOC";
    private String function; 
    MongoDatabase mongodb;
    MongoClient mongoClient;
    
    
   MongoCollection<Document> collection;
    
    final String DATABASE = "BarterMarket";
    final String COLLECTION= "Users";
    public UserLogin(Socket socket){
        this.socket = socket;
                   
        
    }
         
    
    public void run(){
      
            
        PrintWriter out = null;
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Connected");
            while (true){
                
                try {
                    function= in.readLine();
                    userName= in.readLine();
                    password= in.readLine();
                        
                    connectToMongo();
                    
                   switch(function){
                       
                       case "CREATE":
                           out.println(CreateUser(userName,password,in,out));
                           break;
                       case "LOGIN":
                          out.println(login(userName,password,in,out));   
                          break;
                   } 
                    
                 
                   
                } catch (IOException ex) {
                    Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
        } catch (IOException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
             
                  
  
        }
    
    
    public String CreateUser(String user, String password, BufferedReader in, PrintWriter out){
        String result="ERROR";
        
        BasicDBObject  findAccountObj= new BasicDBObject("NAME",user);
        Document account = collection.find(findAccountObj).first();
     if(account ==null){
        account = new Document();
        account.append("NAME", user);
        account.append("PASSWORD",password);
        account.append("ADMIN", "NO");
        collection.insertOne(account);
        out.println("User Created!");
        result = "CREATED";
            
            
        }  else{
         out.println("User Repeated!");
         result= "REPEATED";
     }     
         
  
      
       return result;
        
        
    }

    public String login(String user, String password, BufferedReader in,PrintWriter out){
      String result= "UNAUTH";
      BasicDBObject  findAccountObj= new BasicDBObject("NAME",user).append("PASSWORD",password );
      
      Document account = collection.find(findAccountObj).first();
      
      if(account !=null){
        out.println("Logged Succes!");
        String isAdmin = account.get("ADMIN").toString();
        if("YES".equals(isAdmin)){
            result="AUTHADMIN";
                   
            
        }else{
            out.println("Login Success!");
            result="AUTH";
          
            
        }       
        
      }else{
          out.println("Login Failed");
      }
          
                
      return result; 
    }
    public void connectToMongo(){
   
    MongoClientURI uri = new MongoClientURI("mongodb://AdminIOC:cuL7ahsJ4U0zfxFr@ioccluster-shard-00-00-amktq.gcp.mongodb.net:27017,ioccluster-shard-00-01-amktq.gcp.mongodb.net:27017,ioccluster-shard-00-02-amktq.gcp.mongodb.net:27017/test?ssl=true&replicaSet=IOCCluster-shard-0&authSource=admin");
    mongoClient= new MongoClient(uri);
    mongodb= mongoClient.getDatabase(DATABASE);
    collection = mongodb.getCollection(COLLECTION);
    
    }   
}
  
//password cuL7ahsJ4U0zfxFr