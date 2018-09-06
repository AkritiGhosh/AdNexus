/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adnexus;

import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.web.*;
import javafx.stage.Stage;

/**
 * @author AKRITI GHOSH
 */
public class AddressBar extends HBox
{

    public AddressBar(Stage stage, WebEngine eng, WebView web, Worker work, WebHistory history)
    {
        super();
        
        Nodes addNode= new Nodes(); 
        TextField url = addNode.textF("www.google.com"); 
        Button back = addNode.icon(getClass().getResourceAsStream("back.png"));
        Button next = addNode.icon(getClass().getResourceAsStream("next.png"));
        Button home =  addNode.icon(getClass().getResourceAsStream("home.png"));
        Button refresh =  addNode.icon(getClass().getResourceAsStream("refresh.png"));        
        MenuButton menu = addNode.menuB(getClass().getResourceAsStream("menu.png"), web, eng);
        ProgressBar progress = addNode.indicator(work);
        Label name = addNode.tag();
        
        new Actions(back, next, history);            //Back and Next button
        new Actions(eng, home);                      //Home button
        new Actions(eng, refresh, url);              //Refresh button
        new Actions(eng, url);                       //URL bar
        new Actions(eng, stage, work, name, progress);      //Set title as per page
        
        
        getChildren().addAll(back, next, home, refresh, new StackPane(progress,name), url , menu);
        setSpacing(10);
        setPadding(new Insets(5));
        
    }
    
    
}
