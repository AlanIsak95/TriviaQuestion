package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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


    private Button FalseBtn;
    private Button TrueBtn;
    private ImageButton NextBtn;
    private ImageButton BackBtn;
    private TextView questionTxt;
    private TextView numQuestion;
    private int Bandera =0;
    protected List <Question> listaDePreguntas;



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
        //botones
        FalseBtn.setOnClickListener(this);
        TrueBtn.setOnClickListener(this);
        NextBtn.setOnClickListener(this);
        BackBtn.setOnClickListener(this);





        //Iniciamos un listado de Preguntas y lo llenamos por el metodo getQuestion de la clase QuestionBank
          listaDePreguntas = new QuestionBank().getQuestion(new AnswerAsyncResponse() {
            @Override
            public void processisFinish(ArrayList<Question> questionArrayList) {

                //Aqui se trabaja con la infomacion recibida por QuestionArray, o de igual forma se puede utilizar el listaDePreguntas
                questionTxt.setText(questionArrayList.get(Bandera).getAnswer());

            }
        });




    }


    //cuando se haga cualquier click en los objetos que le colocamos el metodo OnclickListener
    @Override
    public void onClick(View view) {


        //obtenemos el ID de los botones y hacemos algo dependiendo cual sea
        switch (view.getId()){


            //BtnRegresar
            case R.id.PrevBtn:
                if (Bandera >= 1){
                    Bandera = (Bandera -1 ) % listaDePreguntas.size();
                    updateQuestions();
                }
                else {
                    Toast.makeText(this, "Primera Pregunta Morro", Toast.LENGTH_SHORT).show();
                }
                break;

            //BtnNext
            case R.id.NextBtn:
               Bandera = (Bandera +1 )% listaDePreguntas.size();
                updateQuestions();
                break;
            //BtnTrue
            case R.id.TrueBtn:
                break;
            //BtnFalse
            case R.id.FalseBtn:
                break;


        }

    }

    //Metodo Para cambiar Preguntas mostradas
    private void updateQuestions() {

        String NextQuestionToUpdate = listaDePreguntas.get(Bandera).getAnswer();
        questionTxt.setText(NextQuestionToUpdate);

    }
}
