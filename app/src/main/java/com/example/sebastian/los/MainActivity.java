package com.example.sebastian.los;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    int mojaLiczba;
    int wprowadzonaLiczba;
    int odpowiedzi = 0;
    TextView textView2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public void NowaGra(View view){
        Random rand = new Random();
        mojaLiczba = rand.nextInt((100-0)+1)+0;
        odpowiedzi = 0;
        TextView textView=(TextView)findViewById(R.id.textView);
        textView.setText("Odpowiedzi:" + odpowiedzi);
        TextView textView2=(TextView)findViewById(R.id.textView2);
        textView2.setText("Najlepszy wynik: " + sharedPreferences.getInt("NajlepszyWynik",100));
    }

    public void zgadnij(View view){
        odpowiedzi++;
        EditText editText = (EditText)findViewById(R.id.editText);
        wprowadzonaLiczba = Integer.parseInt(editText.getText().toString());
        String wiadomosc = "";

        if(wprowadzonaLiczba>mojaLiczba){
            wiadomosc = "Twoja liczba jest za duża";
        }
        else if(wprowadzonaLiczba<mojaLiczba){
            wiadomosc ="Twoja liczba jest za mała";
        }
        else if(wprowadzonaLiczba==mojaLiczba){
            wiadomosc="Gratulacje! Odgadłeś liczbę!";

            if(odpowiedzi<sharedPreferences.getInt("NajlepszyWynik", 100)){
                editor.putInt("NajlepszyWynik", odpowiedzi);
                editor.commit();
            }
        }

        Context context = getApplicationContext();
        int czas=Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, wiadomosc, czas);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        TextView textView=(TextView)findViewById(R.id.textView);
        textView.setText("Liczba prób:" + odpowiedzi);
        TextView textView2=(TextView)findViewById(R.id.textView2);
        textView2.setText("Najlepszy wynik: " + sharedPreferences.getInt("NajlepszyWynik",100));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("com.example.sebastian.los", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
