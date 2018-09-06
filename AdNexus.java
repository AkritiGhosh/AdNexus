package adnexus;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.web.*;
import javafx.stage.Stage;

/**
 * @author AKRITI GHOSH
 */
/**
 * AdNexus is a JavaFX UI application. It is a web browser.
 * */

public class AdNexus extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        
        WebView web = new WebView();
        web.setPrefSize(1000, 725);
        final WebEngine eng = web.getEngine();
        
        WebHistory history = web.getEngine().getHistory();
        Worker<Void> worker = eng.getLoadWorker();
        WebView pop = new WebView();
        
        String homeURL ="https://www.google.com";
        eng.load(homeURL);
       
        //Panes
        HBox topPane = new AddressBar(primaryStage, eng, web, worker, history);
        
        VBox base= new VBox(topPane, web);
        
        Scene main = new Scene(base, 1000, 500);      

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.png")));
        primaryStage.setScene(main);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    
    public static void main(String[] args) 
    {
        launch(args);
    }
}