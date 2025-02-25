package com.example.webview;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private WebView webView;

    @FXML
    private TextField textField;

    private WebEngine engine;

    private String homePage;

    private double webZoom;

    private WebHistory history;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //This declares the variables initialized above.
        engine = webView.getEngine();
        loadPage();
        homePage = "www.google.com";
        textField.setText(homePage);
        webZoom = 1;

        //loads google.com as the base website so its not blank on open
        loadPage();

    }

    public void loadPage() {

        //loads the page onto the webview
        engine.load("https://"+ textField.getText());
    }

    public void reloadPage() {

        //reloads the page
        engine.reload();
    }

    public void zoomIn(){

        //zooms in the webview by .25
        webZoom += 0.25;
        webView.setZoom(webZoom);
    }

    public void zoomOut(){
        //zooms out webview by .25
        webZoom -= 0.25;
        webView.setZoom(webZoom);
    }

    public void displayHistory(){
        //accesses history of the webview engine into a list
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();

        //prints out history into the console
        for (WebHistory.Entry entry : entries) {
            System.out.println(entry.getUrl() + " " + entry.getLastVisitedDate());
        }
    }

    //takes you back to the previous page
    public void back(){
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(-1);
        textField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    //takes to page you were on before using back method
    public void forward(){
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(1);
        textField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    //executes javascript locally
    public void executeJS(){
        engine.executeScript("window.location = \"https://www.youtube.com\";");
    }
}