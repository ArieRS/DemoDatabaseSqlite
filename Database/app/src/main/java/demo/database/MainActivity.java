package demo.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btInsert, btGet, btNext;
    EditText edtNamaInput,edtTelpnInput, edtAgamaInput;
    EditText edtIdOutput, edtNamaOutput,edtTelpnOutput, edtAgamaOutput;
    KTP mKtp = new KTP();
    int id_row;

    DatabaseHandler mDatabaseHandler ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGet = (Button) findViewById(R.id.btGet);
        btInsert = (Button) findViewById(R.id.btInsert);
        btNext = (Button) findViewById(R.id.btNext);

        edtNamaInput = (EditText) findViewById(R.id.edtNamaInput);
        edtTelpnInput = (EditText) findViewById(R.id.edtTelpnInput);
        edtAgamaInput = (EditText) findViewById(R.id.edtAgamaInput);

        edtIdOutput = (EditText) findViewById(R.id.edtIdOutput);
        edtNamaOutput = (EditText) findViewById(R.id.edtNamaOutput);
        edtTelpnOutput = (EditText) findViewById(R.id.edtTelpnOutput);
        edtAgamaOutput = (EditText) findViewById(R.id.edtAgamaOutput);


        final DatabaseHandler mHandler = new DatabaseHandler(getApplicationContext());

        //masukin button Insert
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KTP inputKTP = new KTP();
                inputKTP.setNama(edtNamaInput.getText().toString());
                inputKTP.setAgama(edtAgamaInput.getText().toString());
                inputKTP.setTelpn(edtTelpnInput.getText().toString());
                id_row = mHandler.insertDataKTP(inputKTP);
            }
        });
        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //masukin button Get
                KTP mKtp = mHandler.getDataKTP(id_row);
                if(mKtp!=null){
                    edtIdOutput.setText(String.valueOf(mKtp.getId()));
                    edtNamaOutput.setText(mKtp.getNama());
                    edtTelpnOutput.setText(mKtp.getTelpn());
                    edtAgamaOutput.setText(mKtp.getAgama());
                }
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(),RecyclerViewActivity.class);
                startActivity(mIntent);
            }
        });
    }
}





