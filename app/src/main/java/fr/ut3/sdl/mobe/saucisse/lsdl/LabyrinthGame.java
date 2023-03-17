package fr.ut3.sdl.mobe.saucisse.lsdl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import fr.ut3.sdl.mobe.saucisse.lsdl.parts.Ball;
import fr.ut3.sdl.mobe.saucisse.lsdl.parts.Labyrinth;
import fr.ut3.sdl.mobe.saucisse.lsdl.parts.OnSwipeTouchListener;

public class LabyrinthGame extends AppCompatActivity {
    private enum Strategy {
        ACCEL, ACCEL_INV, SWIPE, SWIPE_INV
    }
    public static final int COLUMN_NUMBER = 21;
    public static final int ROW_NUMBER = 7;
    public static final int BALL_STARTING_X = 0;
    public static final int BALL_STARTING_Y = 7;
    private Labyrinth labyrinth;
    private Ball ball;
    private TextView labyrinthTextView;
    private Strategy strategy = Strategy.SWIPE;
    private void displayLabyrinth(){
        String labyrinthString = labyrinth.transform(
                ball.getPosX(), ball.getPosY(), COLUMN_NUMBER, ROW_NUMBER);
        Spannable spannable = new SpannableString(labyrinthString);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        spannable.setSpan(colorSpan, 76, 77, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        labyrinthTextView.setText(spannable);
    }
    private void setup(){
        ball = new Ball(BALL_STARTING_X, BALL_STARTING_Y);
        labyrinth = new Labyrinth();
    }
    private void setupView(){
        labyrinthTextView = (TextView) this.findViewById(R.id.labyrinthText);
    }

    private void setupSwipeListener() {
        labyrinthTextView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                if (strategy == Strategy.SWIPE) {
                    ball.goUp();
                } else if (strategy == Strategy.SWIPE_INV) {
                    ball.goDown();
                }
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                if (strategy == Strategy.SWIPE) {
                    ball.goRight();
                } else if (strategy == Strategy.SWIPE_INV) {
                    ball.goLeft();
                }
            }
            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                if (strategy == Strategy.SWIPE) {
                    ball.goDown();
                } else if (strategy == Strategy.SWIPE_INV) {
                    ball.goUp();
                }
            }
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if (strategy == Strategy.SWIPE) {
                    ball.goLeft();
                } else if (strategy == Strategy.SWIPE_INV) {
                    ball.goRight();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labyrinth_game);

        setup();
        setupView();
        setupSwipeListener();


        displayLabyrinth();
    }
}