/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsondohack;

import com.google.gson.Gson;
import static gsondohack.GsonDoHack.answerPut;
import static gsondohack.GsonDoHack.pollGet;
import static gsondohack.GsonDoHack.pollPush;
import static gsondohack.GsonDoHack.resultGet;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 *
 * @author Pedigree
 */
public class DoPoll extends Application {
    
    private int number;
    private TextField[] textArr;
    private String question;
    private String topic;
    private String answer;
    Gson gson = new Gson();
    
    
    @Override
    public void start(Stage primaryStage) {
        
        BorderPane root = new BorderPane();
        
        
        Scene scene = new Scene(root, 300, 250);
        
      
        startScreen();

        
        
    }
    public void startScreen(){
    
    TextField tf = new TextField();
    Button button1 = new Button("Neue Umfrage");
    Button button2 = new Button("Bestätige");
    BorderPane bp = new BorderPane();
    HBox hb = new HBox();
    hb.setAlignment(Pos.CENTER);
    hb.setSpacing(20);
    bp.setPadding(new Insets(10,20,10,20));
    
    Stage stage = new Stage();
    hb.getChildren().addAll(button1, button2);
    bp.setBottom(hb);
    bp.setCenter(tf);
    Scene scene = new Scene(bp, 500, 400);
    
    button1.setOnAction( e ->
    {
        howManyAnswers();
        whatKindOfAnswers(number);
        
        String[] answers = new String[textArr.length];
        for(int i = 0; i < textArr.length; i++)
        {
            answers[i] = (String) textArr[i].getText();
        };
        Poll p1 = new Poll(0,topic,question,answers);
        String jsonPoll = gson.toJson(p1);
        jsonPoll = pollPush(jsonPoll);
        p1 = gson.fromJson(jsonPoll,Poll.class);
        int id = p1.id;
        System.out.println(id);
        stage.close();
    });
    button2.setOnAction( e ->
    {
        //Ich Poll Get
       int id = Integer.valueOf(tf.getText());
       String jsonPoll = pollGet(id);
       Poll p1 = gson.fromJson(jsonPoll,Poll.class);
       textArr = new TextField[p1.answer.length];
       for(int i=0;i<p1.answer.length;i++)
       {
           textArr[i]= new TextField(p1.answer[i]);
       }
       studentChoice();
       answerPut(answer,id);
       showResults(id);
    });
    stage.setScene(scene);
    stage.showAndWait();
    }
    
    public void howManyAnswers()
    {
        Stage dialog = new Stage();
        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        
        Button button = new Button("Send");
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(20);
        gp.setPadding(new Insets(10,20,10,20));
        
        Label label1 = new Label("Wie viele?");
        Label label2 = new Label("Was ist das Thema ?");
        Label label3 = new Label("Formuliere die Frage");        
                
        final TextField tf1 = new TextField();
        final TextField tfTopic = new TextField();
        final TextField tfQuestion = new TextField();
        button.setOnAction((e)-> 
        {
            number = Integer.parseInt(tf1.getText());
            topic = tfTopic.getText();
            question = tfQuestion.getText();
            dialog.close();
        });
        
        gp.getChildren().addAll(label1, label2, label3, tf1, tfTopic, tfQuestion);
        GridPane.setConstraints(label1, 0, 0);
        GridPane.setConstraints(label2, 0, 1);
        GridPane.setConstraints(label3, 0, 2);
        GridPane.setConstraints(tf1,1, 0);
        GridPane.setConstraints(tfTopic, 1, 1);
        GridPane.setConstraints(tfQuestion, 1, 2);
        bp.setCenter(gp);
        bp.setBottom(button);
        
        Scene scene = new Scene(bp, 300, 250);
        
        
        dialog.setScene(scene);
        dialog.showAndWait();
        
        
    }
    
    public void whatKindOfAnswers(int number){
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        textArr = new TextField[number];
        Button button = new Button("Senden");
        Stage stage = new Stage();
        vb.setSpacing(20.0);
        vb.setPadding(new Insets(10,20,10,20));
        for(int i = 0; i < number; i++)       
        {
            textArr[i] = new TextField();
            vb.getChildren().addAll(textArr[i]);
        }
        vb.getChildren().addAll(button);
        
        button.setOnAction(e -> 
        {
            stage.close();
        });
        
        Scene scene = new Scene(vb, 600, 550);
        
        stage.setScene(scene);
        stage.showAndWait();
        
    }
    
    public void studentChoice()
    {
        ToggleGroup tg = new ToggleGroup();
        RadioButton[] rb = new RadioButton[number];
        
        
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(10,20,10,20));
        Stage stage = new Stage();
        Scene scene = new Scene(gp, 600, 550);
        
        for(int i = 0; i< number; i++)
        {
            rb[i] =  new RadioButton(textArr[i].getText());
            rb[i].setToggleGroup(tg);
            
            gp.getChildren().addAll(rb[i]);
            gp.setConstraints(rb[i], 0, i);
            
        }
        
        Button button = new Button("Neu");
        button.setAlignment(Pos.CENTER);
        gp.getChildren().addAll(button);
        gp.setConstraints(button, 0, number);
        gp.setPadding(new Insets(20,10,20,10));
        button.setOnAction( e -> 
        {
            answer = ((RadioButton) tg.getSelectedToggle()).getText();
            stage.close();
            
        });
        
        
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public void showResults(int id)
    {
        VBox vb = new VBox();
        Stage stage = new Stage();
        Scene scene = new Scene(vb, 500, 400);
        
        
        String[] results = resultGet(id);
        Label[] label = new Label[results.length];
        
        //   Fenster mit Anzahl Resultlabeln generieren
        
        for(int j=0;j>results.length;j++)
        {
            label[j] = new Label(results[j]);
            vb.getChildren().addAll(label[j]);
        }
        Timer t = new Timer();
        t.schedule(new TimerTask(){
            @Override
            public void run()
            {
                for(int i=0;i>results.length;i++)
                {
                    label[i].setText(results[i]);
                    
                }
            }
        },1000,1000); 
        
        stage.setScene(scene);
        stage.showAndWait();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //Poll Pushen
    }
    
}
