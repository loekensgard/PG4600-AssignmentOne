package pg4600.loktho14.student.westerdals.innleveringen;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;



public class Resultathistorikkskjerm extends AppCompatActivity {
    private ListView resultatView;
    private List<String> results;
    private ArrayAdapter<String> adapter;
    private Context context;
    private Button btnGoback;
    private final ResultSaver RESULT_SAVER = ResultSaver.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultathistorikkskjerm);

        initListView();
        goToSpillskjerm();
    }

    private void initListView(){
        context = this;
        results = RESULT_SAVER.getWinner();
        resultatView = (ListView) findViewById(R.id.resultatView);
        adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,results);
        resultatView.setAdapter(adapter);
    }

    private void goToSpillskjerm(){
        btnGoback = (Button)findViewById(R.id.btnBack);
        btnGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resultathistorikkskjerm.this.finish();
            }
        });
    }



}
