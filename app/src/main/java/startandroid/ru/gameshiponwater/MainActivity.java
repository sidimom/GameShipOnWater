package startandroid.ru.gameshiponwater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    public static boolean isUpPressed = false;
    public static boolean isDownPressed = false;
    Button btn_up, btn_down;
    LinearLayout ll_ganeField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GameView gameView = new GameView(this);
        ll_ganeField = findViewById(R.id.ll_gameField);
        ll_ganeField.addView(gameView);

        btn_up = findViewById(R.id.btn_up);
        btn_down = findViewById(R.id.btn_down);

        btn_up.setOnTouchListener(this);
        btn_down.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(view.getId()) {
            case R.id.btn_up:
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isUpPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isUpPressed = false;
                        break;
                }
                break;
            case R.id.btn_down:
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isDownPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isDownPressed = false;
                        break;
                }
                break;
            default:
                return false;
        }
        return true;
    }
}
