package com.example.smarthouseapp2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Imports Volley
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

// Imports JSON
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity2";
    private LinearLayout linearLayout;
    private static final String URL = "http://happyresto.enseeiht.fr/smartHouse/api/v1/devices/32";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        linearLayout = findViewById(R.id.linearLayout);

        // Initialisation de la file de requêtes Volley
        RequestQueue queue = Volley.newRequestQueue(this);

        // Création de la requête JSON
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
            Request.Method.GET,
            URL,
            null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        // Parcours de tous les appareils
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject device = response.getJSONObject(i);
                            View deviceView = createDeviceView(
                                device.getString("NAME"),
                                device.getString("MODEL"),
                                device.getInt("STATE") == 1
                            );
                            if (deviceView.getParent() != null) {
                                ((ViewGroup) deviceView.getParent()).removeView(deviceView);
                            }
                            linearLayout.addView(deviceView);
                        }
                    } catch (JSONException e) {
                        Log.e(TAG, "Erreur lors du parsing JSON: " + e.getMessage());
                        Toast.makeText(MainActivity2.this, "Erreur lors du chargement des données", Toast.LENGTH_SHORT).show();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Erreur Volley: " + error.getMessage());
                    Toast.makeText(MainActivity2.this, "Erreur lors de la connexion au serveur", Toast.LENGTH_SHORT).show();
                }
            }
        );

        // Ajout de la requête à la file
        queue.add(jsonArrayRequest);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public View createDeviceView(String nomAppareil, String informations, Boolean isOn) {
        RelativeLayout layout = new RelativeLayout ( this ) ;
        //paramètres de position relative
        RelativeLayout . LayoutParams paramsTopLeft =
                new RelativeLayout . LayoutParams (
                        RelativeLayout . LayoutParams .WRAP_CONTENT,
                        RelativeLayout . LayoutParams .WRAP_CONTENT) ;
        paramsTopLeft . addRule ( RelativeLayout .ALIGN_PARENT_LEFT,
                RelativeLayout .TRUE) ;
        paramsTopLeft . addRule ( RelativeLayout .ALIGN_PARENT_TOP,
                RelativeLayout .TRUE) ;

        RelativeLayout . LayoutParams paramsBottomLeft =
                new RelativeLayout . LayoutParams (
                        RelativeLayout . LayoutParams .WRAP_CONTENT,
                        RelativeLayout . LayoutParams .WRAP_CONTENT) ;
        paramsBottomLeft . addRule ( RelativeLayout .ALIGN_PARENT_LEFT,
                RelativeLayout .TRUE) ;
        paramsBottomLeft . addRule ( RelativeLayout .ALIGN_PARENT_BOTTOM,
                RelativeLayout .TRUE) ;

        RelativeLayout . LayoutParams paramsBottomRight =
                new RelativeLayout . LayoutParams (
                        RelativeLayout . LayoutParams .WRAP_CONTENT,
                        RelativeLayout . LayoutParams .WRAP_CONTENT) ;
        paramsBottomRight . addRule ( RelativeLayout .ALIGN_PARENT_RIGHT,
                RelativeLayout .TRUE) ;
        paramsBottomRight . addRule ( RelativeLayout .ALIGN_PARENT_BOTTOM,
                RelativeLayout .TRUE) ;

        //ajout du texte
        TextView textNomAppareil = new TextView ( this ) ;
        textNomAppareil.setText (nomAppareil);
        layout . addView ( textNomAppareil, paramsTopLeft ) ;

        TextView textInformationAppareil = new TextView ( this ) ;
        textInformationAppareil.setText (informations);
        layout . addView ( textInformationAppareil, paramsBottomLeft ) ;

        // Création du bouton ON/OFF
        ToggleButton toggleButton = new ToggleButton(this);
        toggleButton.setTextOn("ON");
        toggleButton.setTextOff("OFF");
        toggleButton.setChecked(false); // Par défaut sur OFF

        // Ajout du bouton au layout
        layout.addView(toggleButton, paramsBottomRight);

        return layout ;
    }
}