package com.example.trivia.model;


//MODELO DE PREGUNTA
public class Question {

    //atributos
    private String answer;
    private boolean answerTrue;


    //Metodo para contruir pregutna
    public Question(String answer, boolean answerTrue) {
        this.answer = answer;
        this.answerTrue = answerTrue;
    }
    //contructor default
    public Question() {
    }


    //GETERS AND SETERS
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }




    //METODOS DE LA CLASE
    public boolean isAnswerTrue() {
        return answerTrue;
    }
    public String getAnswer() { return answer; }


    //METODO STRING para saber los valores de la clase cuando no se especifica por ejemplo PRINT(QUESTION) regresaria el lugar de memoria
    //pero si tenemos nuestra clase string nos regresa lo marcado en la case
    @Override
    public String toString() {
        return "Question{" +
                "answer='" + answer + '\'' +
                ", answerTrue=" + answerTrue +
                '}';
    }





}
