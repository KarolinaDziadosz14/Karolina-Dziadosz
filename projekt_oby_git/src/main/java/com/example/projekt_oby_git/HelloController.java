package com.example.projekt_oby_git;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

import static java.lang.Math.ceil;

public class HelloController {
    int a,b,c;

    @FXML
    private TextField czas;

    @FXML
    private TextField pojemnosc_m;

    @FXML
    private TextField pojemnosc_s;

    @FXML
    private Button uruchom;

    @FXML
    private Button zapis;

    @FXML
    void uruchom_program(ActionEvent event) {
        Statek statek = new Statek(a,b,c);
        pomoc pomoc = new pomoc(statek);
        pomoc.start();
    }

    @FXML
    void zapisz(ActionEvent event) {
        a=Integer.parseInt(czas.getText());
        b=Integer.parseInt(pojemnosc_s.getText());
        c=Integer.parseInt(pojemnosc_m.getText());
        int k;
        if(c%2==0){
            k=0;
        }
        else{
            k=1;
        }
        int x=0;
        int y=0;
        for(int i=0;i<c;i++){
            if(i>0 && i%((c/2)+k)==0) {
                y = 0;
                x++;
            }
            Rectangle kwadrat = new Rectangle();
            kwadrat.setX(521+50*x);
            kwadrat.setY(143+50*y);
            kwadrat.setHeight(45);
            kwadrat.setWidth(45);
            Platform.runLater(() -> {           //dodanie kwadratu do sceny
                HelloApplication.root.getChildren().add(kwadrat);
            });
            y++;
        }
        for(int i=0;i<b;i++) {

            Rectangle kwadrat_s = new Rectangle();
            kwadrat_s.setX(239 + 50 * (i%14));
            kwadrat_s.setY(409 + 50 * (i/14));//15%14 czyli nowy wiersz 1 rzad
            kwadrat_s.setHeight(45);
            kwadrat_s.setWidth(45);
            Platform.runLater(() -> {           //dodanie kwadratow na planszy do statku
                HelloApplication.root.getChildren().add(kwadrat_s);
            });
        }
    }

}