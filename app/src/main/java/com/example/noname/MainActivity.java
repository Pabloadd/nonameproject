package com.example.noname;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnimg1,btnimg2;
    ImageView imgcontainer;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnimg1 = findViewById(R.id.button);
        btnimg2 = findViewById(R.id.button2);
        imgcontainer = findViewById(R.id.imageView);
        editText = findViewById(R.id.edtxtNombre);
        messaje.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cambiar_mario(View v){
        Drawable mario = getDrawable(R.drawable.mario);
        imgcontainer.setImageDrawable(mario);
//        imgcontainer.setImageResource(R.drawable.mario);

    }

    public void cambiar_luigi(View v){
        String mypackage = getPackageName();
        int id = getResources().getIdentifier(mypackage+":drawable/luigi",null,null);
        imgcontainer.setImageResource(id);
//        imgcontainer.setImageResource(R.drawable.luigi);
    }

    public void enviarMail(View v){
//        Intent sendEmail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","dominguezpablo265@gmail.com","contenido?"));
//        sendEmail.putExtra(Intent.EXTRA_SUBJECT,"Android APP Test");
//        startActivity(Intent.createChooser(sendEmail,getApplication().getString(R.string.app_name)));
        String user = "pablisky71@gmail.com";
        String pass = "pablisqui69";
        try{
            new MailJob(user,pass).execute(
                    new MailJob.Mail("Android Test Mail","Hola!","pablisky71@gmail.com","dominguezpablo265@gmail.com")
            );
            Toast.makeText(this, "Mail enviado!", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("MailApp","No se pudo enviar el mail.");
            Toast.makeText(this, "Error envio Mail!", Toast.LENGTH_SHORT).show();
        }


    }

    public Thread messaje = new Thread(){
        public void run(){
            try{
                //duerme por 5 segundos
                sleep(5*1000);

                //despues de los 5s, muestra mensaje
                editText.setText("Wake up!");

                finish();

            }catch (Exception e){
//                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                Log.d("SLEEP","Error: "+e.getMessage());
            }
        }
    };



}
