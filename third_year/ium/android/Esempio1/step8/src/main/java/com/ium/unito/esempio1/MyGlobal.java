package com.ium.unito.esempio1;

import android.app.Application;

public class MyGlobal extends Application {
    private static String Variabile;

    public static String getVariabile() {
        return Variabile;
    }

    public static void setVariabile(String variabile) {
        MyGlobal.Variabile = variabile;
    }
}