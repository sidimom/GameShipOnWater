package startandroid.ru.gameshiponwater;

import android.content.Context;

public class Ship extends GameObjects {
    public Ship(Context context) {
        // определяем начальные параметры
        bitmapId = R.drawable.ship;
        size = 5;
        y = 10;
        x = 1;
        speed = (float) 0.2;

        init(context); // инициализируем корабль
    }

    // перемещаем корабль в зависимости от нажатой кнопки
    public void update() {
        if(MainActivity.isDownPressed && y <= GameView.maxY - 5){
            y += speed;
        }
        if(MainActivity.isUpPressed && y >= 0){
            y -= speed;
        }
    }
}