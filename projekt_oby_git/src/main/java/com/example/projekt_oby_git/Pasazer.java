package com.example.projekt_oby_git;

import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.Random;

public class Pasazer extends Thread {

    Statek statek;
    Random random = new Random();

    int kolejnosc_pasazera;
    public Pasazer( int a,Statek statek){
        this.statek=statek;
        this.kolejnosc_pasazera=a;
    }
    public void run() {
        Circle kolko = new Circle();//pasazer to kolko, tutaj tworzymy
        int b=kolejnosc_pasazera%16+1;
        int c=0;
        if(kolejnosc_pasazera>15){
            c++;
            if(kolejnosc_pasazera>31){
                c++;
            }
        }
        kolko.setCenterX(232+40*b);//wspolzedne kolka na brzegu
        kolko.setCenterY(20+c*40);
        kolko.setRadius(20);//promien
        kolko.setFill(Color.RED);
        Platform.runLater(() -> {
            HelloApplication.root.getChildren().add(kolko);//dodanie kola do planszy
        });
        try {
            statek.statek.acquire();  //zajmowanie miejsc na statku
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            statek.mostek.acquire();     //opuszcza semafor mostek

            while(statek.odplyw.availablePermits()==0){     //kapitan chce odplymac wiec pasazerowie czzekaja
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        statek.os_na_mostku++;
        statek.M=statek.M%statek.K;

        int x_m=statek.M%2,y_m=statek.M/2;

        statek.M++;
        System.out.println("Pasazer " + kolejnosc_pasazera + " znajduje się na mostku jako " + statek.os_na_mostku + " z " + statek.K);

        MoveTo mov = new MoveTo();
        mov.setX(kolko.getCenterX());
        mov.setY(kolko.getCenterY());
        LineTo lineTo = new LineTo();
        int x,y;
        lineTo.setX(x=544+50*x_m);
        lineTo.setY(y=166+50*y_m);
        Path path = new Path();
        path.getElements().addAll(mov, lineTo);
        PathTransition pathTransition = new PathTransition(Duration.millis(2000), path, kolko);
        pathTransition.setOnFinished(e->{
                    synchronized (this){
                        notifyAll();
                    }
                }

        );

        Platform.runLater(() -> {
            pathTransition.play();
        });
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            sleep(random.nextInt(1000));  //zatrzymanie na mostku watku
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Path sciezka = new Path();
        MoveTo pozycja = new MoveTo();
        pozycja.setX(x);
        pozycja.setY(y);
        LineTo linia = new LineTo();
        linia.setX(262+statek.os_na_statku%14*50);
        linia.setY(432+(statek.os_na_statku/14)*50);
        sciezka.getElements().addAll(pozycja, linia);
        PathTransition sciezka2 = new PathTransition(Duration.millis(2000), sciezka, kolko);
        sciezka2.setOnFinished(e->{
                    synchronized (this){
                        notifyAll();
                    }
                }
        );
        statek.os_na_statku++;
        Platform.runLater(() -> {
            sciezka2.play();
        });
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        statek.os_na_mostku--;


        System.out.println("Pasazer " + kolejnosc_pasazera + " znajduje się na statku jako " + statek.os_na_statku + " z " + statek.N + " os na mostku:" + statek.os_na_mostku);
        statek.mostek.release();

        while(true) {
            try {
                sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(statek.os_na_mostku==0 && statek.odplyw.availablePermits()==0) {
                Platform.runLater(() -> {
                    HelloApplication.root.getChildren().remove(kolko);
                });
                break;
            }
        }
    }
}
