package com.example.trivia.data;

import com.example.trivia.model.Question;

import java.util.ArrayList;

public interface AnswerAsyncResponse {

     //Metodo de preguntaLista para conectarlo entre clases.
     void processisFinish(ArrayList <Question> questionArrayList);

}
