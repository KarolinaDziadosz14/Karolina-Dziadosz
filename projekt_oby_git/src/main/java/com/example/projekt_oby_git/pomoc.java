package com.example.projekt_oby_git;

import javafx.scene.shape.Rectangle;

import java.util.Random;

import static java.lang.Thread.sleep;

public class pomoc extends Thread{


    Statek statek;

    int a;
    public pomoc( Statek statek){
        this.statek=statek;
    }
    public void run(){

        System.out.println(statek.czas_odplywu+" "+statek.N+" "+statek.K);
        int kolejnosc_pasazera = 1;
        while (true) {
            Kapitan K = new Kapitan(statek);

            K.start();

            while (statek.odplyw.availablePermits() == 1) {
                Pasazer P1 = new Pasazer(kolejnosc_pasazera, statek);

                kolejnosc_pasazera++;

                P1.start();

                try {
                    sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                K.join();//czekanie na koniec watku kapitan
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                sleep(2000);//statek odplywa
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            statek.statek.release(statek.os_na_statku);
            statek.os_na_statku = 0;
            statek.odplyw.release();

        }
    }
}