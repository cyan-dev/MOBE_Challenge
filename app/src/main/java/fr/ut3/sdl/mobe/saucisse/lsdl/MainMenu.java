package fr.ut3.sdl.mobe.saucisse.lsdl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import fr.ut3.sdl.mobe.saucisse.lsdl.parts.Labyrinth;

public class MainMenu extends AppCompatActivity {
    public void startMedicActivity(View view){
        Intent intent = new Intent(this, MedicGame.class);
        startActivity(intent);
    }

    public void startLabyrinthActivity(View view){
        Intent intent = new Intent(this, LabyrinthGame.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Log.d("LAB", new Labyrinth().transform(9, 5, 20, 5));
    }
}