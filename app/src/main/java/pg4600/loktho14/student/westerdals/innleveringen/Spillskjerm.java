package pg4600.loktho14.student.westerdals.innleveringen;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Spillskjerm extends AppCompatActivity {
    private final String DRAW = "Draw between ";
    private final String GET_ONE = "SEND_ONE";
    private final String GET_TWO = "SEND_TWO";
    private final String BTN_BACKGROUND_COLOR_ACTIVE = "#da8585";
    private final String BTN_BACKGROUND_COLOR_INACTIVE = "#40474A";
    private final String BTN_BACKGROUND_COLOR_X = "#509ABA";
    private final String BTN_BACKGROUND_COLOR_Y = "#FFFF99";
    private final ResultSaver RESULT_SAVER = ResultSaver.getInstance();
    private Button [] btnArray;
    private Button btnResults,btnRestart;
    private Button btnTop1,btnTop2,btnTop3,btnMid1,btnMid2,btnMid3,btnBot1,btnBot2,btnBot3;
    private int countForDraw = 0;
    private boolean whoseTurnIsIt = true;
    private boolean isThereAwinner = false;
    private String playerOneS, playerTwoS;
    private TextView playerOne, playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spillskjerm);

        getPlayers();
        initButtons();
        restartGame();
        goToResultathistorikkskjema();
    }


    private void initButtons() {
        btnBot1 = (Button) findViewById(R.id.btnBot1);
        btnBot2 = (Button) findViewById(R.id.btnBot2);
        btnBot3 = (Button) findViewById(R.id.btnBot3);

        btnMid1 = (Button) findViewById(R.id.btnMid1);
        btnMid2 = (Button) findViewById(R.id.btnMid2);
        btnMid3 = (Button) findViewById(R.id.btnMid3);

        btnTop1 = (Button) findViewById(R.id.btnTop1);
        btnTop2 = (Button) findViewById(R.id.btnTop2);
        btnTop3 = (Button) findViewById(R.id.btnTop3);

        btnArray = new Button[]{btnTop1,btnTop2,btnTop3,btnMid1,btnMid2,btnMid3,btnBot1,btnBot2,btnBot3};


        for(Button btnAll : btnArray){
            btnAll.setOnClickListener(gameBoardListner);
        }


    }

    private void getPlayers() {
        playerOne = (TextView)findViewById(R.id.txtPlayerOne);
        playerTwo = (TextView)findViewById(R.id.txtPlayerTwo);

        Intent getPlayers = getIntent();
        playerOneS = getPlayers.getExtras().getString(GET_ONE);
        playerTwoS = getPlayers.getExtras().getString(GET_TWO);

        playerOne.setText(playerOneS);
        playerTwo.setText(playerTwoS);
    }

    private View.OnClickListener gameBoardListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button btnAll = (Button) v;

            switch (v.getId()){
                case R.id.btnBot1:
                    buttonOnClickListner(btnAll);
                    break;
                case R.id.btnBot2:
                    buttonOnClickListner(btnAll);
                    break;
                case R.id.btnBot3:
                    buttonOnClickListner(btnAll);
                    break;
                case R.id.btnMid1:
                    buttonOnClickListner(btnAll);
                    break;
                case R.id.btnMid2:
                    buttonOnClickListner(btnAll);
                    break;
                case R.id.btnMid3:
                    buttonOnClickListner(btnAll);
                    break;
                case R.id.btnTop1:
                    buttonOnClickListner(btnAll);
                    break;
                case R.id.btnTop2:
                    buttonOnClickListner(btnAll);
                    break;
                case R.id.btnTop3:
                    buttonOnClickListner(btnAll);
                    break;
            }
        }
    };

    private void buttonOnClickListner(Button btnAll){
        if(whoseTurnIsIt){
            btnAll.setText("X");
            btnAll.setBackgroundColor(Color.parseColor(BTN_BACKGROUND_COLOR_X));
        }else {
            btnAll.setText("O");
            btnAll.setBackgroundColor(Color.parseColor(BTN_BACKGROUND_COLOR_Y));
        }
        countForDraw++;
        btnAll.setClickable(false);
        whoseTurnIsIt = !whoseTurnIsIt;

        checkIfThereIsaWinner();
    }

    private void checkIfThereIsaWinner() {
        isThereAwinner = false;

        checkAxes();
        if(isThereAwinner){
            if(!whoseTurnIsIt){
                toast(playerOneS + " is the winner");
                RESULT_SAVER.setWinner(playerOneS +  " won against " + playerTwoS);
            }else {
                toast(playerTwoS + " is the winner");
                RESULT_SAVER.setWinner(playerTwoS +  " won against " + playerOneS);
            }

            finishGame(false);
        }else if(countForDraw == 9){
            toast(DRAW + playerOneS + " and " + playerTwoS);
            finishGame(false);
            RESULT_SAVER.setWinner(DRAW + playerOneS + " and " + playerTwoS);
        }

    }

    private void checkAxes() {
        if(btnTop1.getText() == btnTop2.getText() && btnTop2.getText() == btnTop3.getText() && !btnTop1.isClickable()){
            isThereAwinner = true;
        }else if(btnMid1.getText() == btnMid2.getText() && btnMid2.getText() == btnMid3.getText() && !btnMid1.isClickable()){
            isThereAwinner = true;
        }else if(btnBot1.getText() == btnBot2.getText() && btnBot2.getText() == btnBot3.getText() && !btnBot1.isClickable()){
            isThereAwinner = true;
        }else if(btnTop1.getText() == btnMid1.getText() && btnMid1.getText() == btnBot1.getText() && !btnTop1.isClickable()){
            isThereAwinner = true;
        }else if(btnTop2.getText() == btnMid2.getText() && btnMid2.getText() == btnBot2.getText() && !btnTop2.isClickable()){
            isThereAwinner = true;
        }else if(btnTop3.getText() == btnMid3.getText() && btnMid3.getText() == btnBot3.getText() && !btnTop3.isClickable()){
            isThereAwinner = true;
        }else if(btnTop1.getText() == btnMid2.getText() && btnMid2.getText() == btnBot3.getText() && !btnTop1.isClickable()){
            isThereAwinner = true;
        }else if(btnTop3.getText() == btnMid2.getText() && btnMid2.getText() == btnBot1.getText() && !btnTop3.isClickable()){
            isThereAwinner = true;
        }
    }

    private void restartGame(){
        btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whoseTurnIsIt = true;
                countForDraw = 0;
                finishGame(true);
            }
        });
    }

    private void finishGame(boolean b) {
        for(Button btnAll : btnArray){
            btnAll.setClickable(b);

            if(b){
                btnAll.setBackgroundColor(Color.parseColor(BTN_BACKGROUND_COLOR_ACTIVE));
                btnAll.setText("");
            }else{
                btnAll.setBackgroundColor(Color.parseColor(BTN_BACKGROUND_COLOR_INACTIVE));
            }
        }
    }

    private void toast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    private void goToResultathistorikkskjema(){
        btnResults = (Button)findViewById(R.id.btnResults);

        btnResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToResultathistorikkskjema = new Intent(Spillskjerm.this, Resultathistorikkskjerm.class);
                startActivity(goToResultathistorikkskjema);
            }
        });
    }




}
