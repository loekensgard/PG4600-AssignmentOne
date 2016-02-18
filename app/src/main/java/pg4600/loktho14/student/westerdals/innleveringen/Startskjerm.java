package pg4600.loktho14.student.westerdals.innleveringen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Startskjerm extends AppCompatActivity {
    private Button btnGo;
    private EditText playerOne, playerTwo;
    private String playerOneS,playerTwoS;
    private final String SEND_ONE = "SEND_ONE";
    private final String SEND_TWO = "SEND_TWO";
    private final String ENTER_NAME = "Enter name here";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startskjerm);

        editTextPlayers();
        goToSpillskjerm();
    }

    private void editTextPlayers() {
        playerOne = (EditText) findViewById(R.id.eTplayerOne);
        playerTwo = (EditText) findViewById(R.id.eTplayerTwo);
        playerOne.setHint(ENTER_NAME);
        playerTwo.setHint(ENTER_NAME);
    }

    private void goToSpillskjerm() {
        btnGo = (Button) findViewById(R.id.btnGo);

            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerOneS = playerOne.getText().toString();
                    playerTwoS = playerTwo.getText().toString();

                    if (playerOneS.equals("") || playerTwoS.equals("")) {
                        toast("Please enter a name");
                    } else {
                        Intent goToSpillSkjermIntent = new Intent(Startskjerm.this, Spillskjerm.class);
                        goToSpillSkjermIntent.putExtra(SEND_ONE, playerOneS);
                        goToSpillSkjermIntent.putExtra(SEND_TWO, playerTwoS);
                        startActivity(goToSpillSkjermIntent);
                    }
                }
            });
    }

    private void toast(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }


}
