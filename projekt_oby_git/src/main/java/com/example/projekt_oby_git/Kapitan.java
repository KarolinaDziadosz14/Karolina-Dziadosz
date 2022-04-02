package com.example.projekt_oby_git;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Kapitan extends Thread {
    Statek statek;

    public Kapitan(Statek statek) {
        this.statek = statek;
    }

    public void run() {

        try {
            sleep(statek.czas_odplywu); //Kapitan zasypia, pasazerowie wchodza na statek, statek odplywa po podanym czasie
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("czas wchodzenia na statek zakonczyl sie, kapitan wstal");
        try {
            statek.odplyw.acquire(); //opuszcza semafor, blokuje wejscie na mostek Pasazerow
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (statek.os_na_mostku == 0) {

                Label komunikat = new Label("Statek odplynal");
                komunikat.setLayoutX(537);
                komunikat.setLayoutY(583);

                komunikat.setTextFill(Color.BLACK);
                Platform.runLater(() -> {
                    HelloApplication.root.getChildren().add(komunikat);
                });
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }Platform.runLater(() -> {
                    HelloApplication.root.getChildren().remove(komunikat);
                });


                System.out.println("Kapiten sprawdza mostek. Liczba osob na mostku to: " + statek.os_na_mostku);
                System.out.println("Statek odplywa");
                break;
            }
        }
    }

}