package com.example.smarthouseapp2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

import com.example.smarthouseapp2.MainActivity2;
import com.example.smarthouseapp2.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button startServerButton = findViewById(R.id.serveur);
        Button startClientButton = findViewById(R.id.client);

        buttonListenerClient(startClientButton);
        buttonListenerServer(startServerButton);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //méthode pour gérer le click sur le boutton qui renvoie vers l'interface serveur
    protected void buttonListenerServer(Button button){
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Créer un Intent pour ouvrir la deuxième activité
                Intent intent = new Intent(MainActivity.this, ActivityServeur.class);
                startActivity(intent);
            }
        });
    }

    //méthode pour gérer le click sur le boutton qui renvoie vers l'interface client
    protected void buttonListenerClient(Button button){
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Créer un Intent pour ouvrir la deuxième activité
                Intent intent = new Intent(MainActivity.this, ActivityClient.class);
                startActivity(intent);
            }
        });
    }
}