/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsondohack;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Jakob
 */
public class GsonDoHack extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

 public static String pollGet(int id)
        {
            String output = "";
            try{
            URL url = new URL("http://localhost:8080/rest/getById/"+id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) 
            {
		throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String temp;
            while((temp = br.readLine()) != null)
            {
                output += temp;
            }
            conn.disconnect();
            }catch(Exception e)
            {
               e.printStackTrace();
            }
            return output;
        }
 public static String pollPush(String input)
 {
     String jsonReturn = "";
     try {
         URL url = new URL("http://localhost:8080/rest/create");
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setDoOutput(true);
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Content-Type", "application/json");
         
         OutputStream os = conn.getOutputStream();
         os.write(input.getBytes());
         os.flush();
         
         /*if(conn.getResponseCode() != HttpURLConnection.HTTP_CREATED)
         {
             throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
         }*/
         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         String temp;
         while((temp =br.readLine()) != null)
         {
             jsonReturn +=temp;
         }
         conn.disconnect();
         
     }catch(Exception e){
     e.printStackTrace();}
     return jsonReturn;
 }
 public static void answerPut(String input, int id)
 {
     try {
         URL url = new URL("http://localhost:8080/rest/addAnswer/"+id+"/"+input);
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setDoOutput(true);
         conn.setRequestMethod("PUT");
         conn.setRequestProperty("Content-Type", "application/json");
         
         if(conn.getResponseCode() != HttpURLConnection.HTTP_CREATED)
         {
             throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
         }
         conn.disconnect();
         
     }catch(Exception e){
     e.printStackTrace();}
 }
 
 public static String[] resultGet(int id)
        {
            String[] output = null;
            try{
            URL url = new URL("http://localhost:8080/rest/getResult/"+id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) 
            {
		throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String temp;
            int i=0;
            while((temp = br.readLine()) != null)
            {
                output[i] = temp;
                i++;
            }
            conn.disconnect();
            }catch(Exception e)
            {
               e.printStackTrace();
            }
            return output;
        }
    /*public static void main(String[] args) 
    {
        Gson gson = new Gson();
        //Patricks GUI macht magie
        //Poll Pushen
        String[] string={"answers1","answer2"};
        Poll p1 = new Poll("Thema","question",string);
        String jsonPoll = gson.toJson(p1);
        jsonPoll = pollPush(jsonPoll);
        p1 = gson.fromJson(jsonPoll,Poll.class);
        int id = p1.id;
        
        //Poll per ID abfragen und bearbeiten, answers Pushen
        jsonPoll = gson.toJson(pollGet(id));
        p1 = gson.fromJson(jsonPoll,Poll.class);
        String answer = "";
        answerPut(answer,id);
       // jsonPush(json);
        //p = gson.fromJson(json, Poll.class);
        
        //Results getten
        Timer t = new Timer();
        t.schedule(new TimerTask(){
            @Override
            public void run()
            {
                String[] results = resultGet(id);
                for(int i=0;i>results.length;i++)
                {
                    //GUI aktualisierung
                   
                }
            }
        },1000,1000);   
    } */
}
