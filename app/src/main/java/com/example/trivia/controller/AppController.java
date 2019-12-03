package com.example.trivia.controller;

import android.app.Application;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

//crear nuestra clase desde la plantilla en internet de Volley SINGLETON, es importante que nosotros hagamos el
// extend de Application
public class AppController extends Application {

    private static AppController instance;
    private RequestQueue requestQueue;



    //Se hace el extend de aplication para poder crearle un onCreate(), hacemos esto para que sea nuestra clase que

    //Controlara toda la APP, para esto necesitamos decirle en Manifest en la linea de
    // <application
    //Colocar lo siguiente
    //        android:name=".controller.AppController" y eso nos permite desde cualquier clase poder tomar sus metodos.
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    //Metodo mandado a llamar desde fuera recibe cualquier tipo de Request y manda a llamar getRequestQueue()
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


    //Singleton de RequetQueue
    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(this.getApplicationContext());

        return requestQueue;
    }

    //Singleton AppController
    public static synchronized AppController getInstance( ) {
        return instance;

    }








}
