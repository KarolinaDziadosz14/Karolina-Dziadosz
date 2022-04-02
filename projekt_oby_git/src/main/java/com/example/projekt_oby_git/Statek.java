package com.example.projekt_oby_git;

import javafx.scene.control.TextField;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Statek {
    public  int czas_odplywu;
    public static int N;
    public  static int K;

    public static int M=0;//pomocnicza
    public int os_na_mostku = 0;
    public int os_na_statku = 0;
    public  static volatile Semaphore mostek = new Semaphore(0);
    public static volatile Semaphore odplyw = new Semaphore(1);
    public  static volatile Semaphore statek = new Semaphore(0);
    public Statek( int a,int b, int c){
        this.czas_odplywu=a;
        this.N=b;
        this.K=c;
        this.mostek.release(K);
        this.statek.release(N);
    }

}
