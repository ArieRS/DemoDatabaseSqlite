package demo.database;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    Context mContext;
    Button btBack, btUpdate, btDelete;
    EditText edtId, edtNama, edtTelpn, edtAgama;
    DatabaseHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mContext = getApplicationContext();
        btUpdate = (Button) findViewById(R.id.btUpdate3);
        btDelete = (Button) findViewById(R.id.btDel3);
        btBack = (Button) findViewById(R.id.btBack3);

        edtId = (EditText) findViewById(R.id.edtId3);
        edtNama = (EditText) findViewById(R.id.edtNama3);
        edtAgama = (EditText) findViewById(R.id.edtAgama3);
        edtTelpn = (EditText) findViewById(R.id.edtTelpn3);

        Intent mIntent = getIntent();
        mIntent.getIntExtra("id",0);
        mIntent.getStringExtra("nama");
        mIntent.getStringExtra("telpn");
        mIntent.getStringExtra("agama");

        edtId.setText(String.valueOf(mIntent.getIntExtra("id",0)));
        edtNama.setText(mIntent.getStringExtra("nama"));
        edtTelpn.setText(mIntent.getStringExtra("telpn"));
        edtAgama.setText(mIntent.getStringExtra("agama"));

        mHandler = new DatabaseHandler(mContext);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KTP ktp = new KTP();
                ktp.setId(Integer.parseInt(edtId.getText().toString()));
                ktp.setNama(edtNama.getText().toString());
                ktp.setTelpn(edtTelpn.getText().toString());
                ktp.setAgama(edtAgama.getText().toString());

                int affected = mHandler.updateDataKTP(ktp);

                String pesan ="";
                if (affected>0) pesan ="Berhasil Update";
                else pesan ="Gagal Update";

                Toast.makeText(mContext,pesan,Toast.LENGTH_SHORT).show();
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int affected = mHandler.deleteDataKTP(Integer.parseInt(edtId.getText().toString()));

                String pesan ="";
                if (affected>0) pesan ="Berhasil Delete";
                else pesan ="Gagal Delete";

                Toast.makeText(mContext,pesan,Toast.LENGTH_SHORT).show();
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,RecyclerViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
