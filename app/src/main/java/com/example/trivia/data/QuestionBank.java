package com.example.trivia.data;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivia.controller.AppController;
import com.example.trivia.model.Question;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

        ArrayList <Question> questionArrayList = new ArrayList<>();


        //URL API de JSON preguntas
        private String URL = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";


        //se crea peticion a espera de respuesta de Interfaz
        public List <Question> getQuestion(final AnswerAsyncResponse callback){

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET, URL, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                        //si el resultado de el WS nos regresa un arreglo de arreglos por asi decirlo un
                            // [
                            //      [],
                            //      [],
                            //      []
                            // ]

                            //se debe de crear un getJsonArray cada que se quiera entrar un nivel mas.
                            //for para recorrer todos los valores del arreglo
                            for (int i = 0;i < response.length();i++){

                                try {
                                    //Creamos una nueva pregunta
                                    Question question =new Question();
                                    //Asginamos valores a cada Objeto
                                    question.setAnswer(response.getJSONArray(i).get(0).toString());
                                    question.setAnswerTrue(response.getJSONArray(i).getBoolean(1));
                                    //Añadimos el objeto a la lista
                                    questionArrayList.add(question);

                                }catch (Exception e){ }

                            }//fin For


                            if (callback != null) callback.processisFinish(questionArrayList);


                        }//Fin Response TRUE
                    },

                    //Inicio Response False
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) { }
                    }
            );//fin de linea de declaracion



            //se manda a llamar app controller de esta manera ya que esta definido en el manifest name del application
           AppController.getInstance().addToRequestQueue(jsonArrayRequest);

           //regresamos en el metodo esta lista de preguntas que ya ha pasado por la interfaz
            return questionArrayList;

        }





}
