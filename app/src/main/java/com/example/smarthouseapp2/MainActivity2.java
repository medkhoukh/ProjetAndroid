package com.example.smarthouseapp2;

import static com.google.android.material.color.utilities.Variant.CONTENT;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        View widget =  createDeviceView("applephone","v16",Boolean.FALSE);
        if (widget.getParent() != null) {
            ((ViewGroup) widget.getParent()).removeView(widget);
        }
        linearLayout.addView(widget);

        View widget2 =  createDeviceView("apple","v16",Boolean.FALSE);
        if (widget2.getParent() != null) {
            ((ViewGroup) widget2.getParent()).removeView(widget2);
        }
        linearLayout.addView(widget2);


       /* for (int i=0 ; i<4 ; i++){

        }*/


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