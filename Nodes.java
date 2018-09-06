package adnexus;

import java.io.*;
import javafx.concurrent.Worker;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


class Nodes
{
    public TextField textF(String text)
    {
        TextField txt = new TextField(text);
        txt.setPrefHeight(25);
        txt.setPrefWidth(1000);
        return txt;
    }
    
    public Button icon(InputStream is)
    {
        Button b= new Button();
        b.setMaxSize(25, 25);
        b.setGraphic(imgIcon(is));
        return b;
    }
    
    public ImageView imgIcon(InputStream is)
    {
        Image img = new Image(is);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(20);
        imgView.setFitWidth(20);
        return imgView;
    }
    
    public MenuButton menuB(InputStream is, WebView web, WebEngine eng)
    {
        MenuButton b= new MenuButton();        
        b.setMaxSize(25, 25);
        b.setGraphic(imgIcon(is));
        MenuItem mOpen = new MenuItem("         "+"Open File"+"         ");
        mOpen.setStyle("-fx-font: 16 Calibri;");
        MenuItem mExit = new MenuItem("         "+"Close Window"+"         Alt+F4");
        mExit.setStyle("-fx-font: 16 Calibri;");
        mExit.setOnAction((ActionEvent t) -> {
             System.exit(0);
        });
        
        FileChooser htmlFile = new FileChooser();
        htmlFile.setTitle("Open Web Content");
        htmlFile.getExtensionFilters().addAll(new ExtensionFilter("HTML Files", "*.html", "*.htm"));
        new Actions(web, eng, mOpen, htmlFile);
        b.getItems().addAll(mOpen,mExit); 
        return b;
    }
    
    public ProgressBar indicator(Worker worker)
    {
        ProgressBar p = new ProgressBar();
        p.progressProperty().bind(worker.progressProperty());
        p.setMinSize(75, 30);   
        p.setOpacity(0.5);
        p.setEffect(new ColorAdjust(-0.4, -0.6, 0, 0));
        
        return p;
    } 
    
    public Label tag ()
    {
        Label name = new Label();
        name.setPrefSize(100, 25);
        name.setAlignment(Pos.CENTER);
        name.setStyle("-fx-font: 16 Calibri; -fx-font-weight: bold");
        return name;
    }
}