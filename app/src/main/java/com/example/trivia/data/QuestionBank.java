package com.example.trivia.data;

import android.util.Log;

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



        private String URL = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

        public List <Question> getQuestion(){

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET, URL, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d("Json Stuff", "onResponse: "+response);
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }


            );

            AppController.getInstance().addToRequestQueue(jsonArrayRequest);

            return null;

        }





}