package adnexus;

import java.io.File;
import java.net.MalformedURLException;
import javafx.beans.value.*;
import javafx.concurrent.Worker;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.web.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

class Actions {

    public Actions(WebEngine eng, TextField url) {
        url.setOnAction((ActionEvent event) -> {
            eng.load(url.getText().startsWith("http://") || url.getText().startsWith("https://") ? url.getText() : "http://" + url.getText());
        });

        eng.locationProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            url.setText(newValue);
        });
    }

    public Actions(WebEngine eng, Button home) {
        home.setOnAction((ActionEvent event) -> {
            eng.load("https://www.google.com");
        });
    }

    public Actions(WebEngine eng, Button refresh, TextField url) {
        refresh.setOnAction((ActionEvent event) -> {
            eng.load(url.getText());
        });
    }

    public Actions(WebEngine eng, TextField search, TextField url) {
        search.setOnAction((ActionEvent event) -> {
            eng.load("https://www.google.com/search?q=" + search.getText());
        });
        eng.locationProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            url.setText(newValue);
        });
    }

    public Actions(WebEngine eng, Stage pStage, Worker worker, Label name, ProgressBar p) {
        worker.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    pStage.setTitle(eng.getTitle() + " - AdNexus");
                    if (eng.getLocation().startsWith("https://")) {
                        name.setText("Secure");
                        p.setEffect(new ColorAdjust(-0.4, 0, 0, 0));
                    } else {
                        name.setText("Unsecure");
                        p.setEffect(new ColorAdjust(0, -1, 0, 0));
                    }
                } else {
                    pStage.setTitle("AdNexus");
                    name.setText("Loading");
                    p.setEffect(new ColorAdjust(-0.4, -1, 0, 0));

                }
            }
        });
    }

    public Actions(Button back, Button next, WebHistory history) {
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                history.go(-1);
            }
        });
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                history.go(1);
            }
        });
        history.currentIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, final Number oldvalue, final Number newvalue) {
                int currentIndex = newvalue.intValue();

                if (currentIndex <= 0) {
                    back.setDisable(true);
                } else {
                    back.setDisable(false);
                }
                if (currentIndex >= history.getEntries().size()) {
                    next.setDisable(true);
                } else {
                    next.setDisable(false);
                }
            }
        });

    }

    public Actions(WebView web, WebEngine eng, MenuItem mOpen, FileChooser htmlFile) {
        mOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File selectedFile = htmlFile.showOpenDialog(web.getScene().getWindow());

                if (selectedFile != null) {
                    try {
                        eng.load(selectedFile.toURI().toURL().toExternalForm());
                    } catch (MalformedURLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

}
