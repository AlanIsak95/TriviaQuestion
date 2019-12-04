package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trivia.data.AnswerAsyncResponse;
import com.example.trivia.data.QuestionBank;
import com.example.trivia.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaraciones
    private CardView cardView;
    private Button FalseBtn;
    private Button TrueBtn;
    private ImageButton NextBtn;
    private ImageButton BackBtn;
    private TextView questionTxt;
    private TextView numQuestion;
    private int Bandera =0;
    protected List <Question> listaDePreguntas;


    //ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hacemos match vista controllador
        FalseBtn = findViewById(R.id.FalseBtn);
        TrueBtn = findViewById(R.id.TrueBtn);
        NextBtn = findViewById(R.id.NextBtn);
        BackBtn = findViewById(R.id.PrevBtn);
        questionTxt = findViewById(R.id.textquestion);
        numQuestion = findViewById(R.id.ContadorPregunta);

        //cardView
        cardView = findViewById(R.id.cardView);

        //botones
        FalseBtn.setOnClickListener(this);
        TrueBtn.setOnClickListener(this);
        NextBtn.setOnClickListener(this);
        BackBtn.setOnClickListener(this);





        //Iniciamos un listado de Preguntas y lo llenamos por el metodo getQuestion de la clase QuestionBank
          listaDePreguntas = new QuestionBank().getQuestion(new AnswerAsyncResponse() {
            @Override
            public void processisFinish(ArrayList<Question> questionArrayList) {
                //Colocamos la primera pregunta en el cardView
                questionTxt.setText(questionArrayList.get(Bandera).getAnswer());
                numQuestion.setText((Bandera+1)+" of "+questionArrayList.size()+" Questions");

            }
        });




    }


    //Cuando se haga cualquier click en los objetos Clickeables se entra al sig metodo
    @Override
    public void onClick(View view) {


        //obtenemos el ID de los botones y hacemos algo dependiendo cual sea
        switch (view.getId()){


            //BtnRegresar
            case R.id.PrevBtn:
                if (Bandera > 0){
                    Bandera = (Bandera -1 ) % listaDePreguntas.size();
                    updateQuestions();
                }
                else {
                    Toast.makeText(this, "IT IS THE FIRST", Toast.LENGTH_SHORT).show();
                }
                break;

            //BtnNext
            case R.id.NextBtn:
               Bandera = (Bandera +1 )% listaDePreguntas.size();
                updateQuestions();
                break;


            //BtnTrue
            case R.id.TrueBtn:

                //desactivamos los botones
                habilitarBTNs(false);
                creckAnswer(true);

                break;


            //BtnFalse
            case R.id.FalseBtn:
                //desactivamos los botones
                habilitarBTNs(false);
                creckAnswer(false);

                break;


        }

    }


    //Revisa que las Respuestas sean las correctas
    private void creckAnswer(boolean answer) {

         updateQuestions();
         boolean CorrectAnswerFromList = listaDePreguntas.get(Bandera).isAnswerTrue();



         if (answer == CorrectAnswerFromList){

                FadeCard();

         }else {

             shakeAnimWrong();

         }



    }

    //Enable Btns True and False
    private void habilitarBTNs(boolean valor){

        if (!valor){
            TrueBtn.setEnabled(false);
            FalseBtn.setEnabled(false);
        }
        else {
            TrueBtn.setEnabled(true);
            FalseBtn.setEnabled(true);
        }

    }


    //Metodo Para cambiar Preguntas mostradas.
    private void updateQuestions() {

        String NextQuestionToUpdate = listaDePreguntas.get(Bandera).getAnswer();
        questionTxt.setText(NextQuestionToUpdate);
        numQuestion.setText((Bandera+1)+" of "+listaDePreguntas.size()+" Questions");
        cardView.setCardBackgroundColor(Color.WHITE);

    }




    //ANIMACIONES



    //INCORRECTO (SHAKE)
    private void shakeAnimWrong (){

        Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
        cardView.setAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Bandera = (Bandera +1 )% listaDePreguntas.size();
                updateQuestions();

                //Activamos los botones
                habilitarBTNs(true);



            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    //Correcto (Fade)
    private void FadeCard(){

        final CardView cardView= findViewById(R.id.cardView);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(250);
        alphaAnimation.setRepeatCount(0);//no regresa solo se desvanece
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        cardView.setAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.GREEN);


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Bandera = (Bandera +1 )% listaDePreguntas.size();
                updateQuestions();
                //Activamos los botones
                habilitarBTNs(true);



            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


}
