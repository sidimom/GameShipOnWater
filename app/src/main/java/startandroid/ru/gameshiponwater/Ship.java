package startandroid.ru.gameshiponwater;

import android.content.Context;

class Ship extends GameObjects {
    Ship(Context context) {
        // определяем начальные параметры
        bitmapId = R.drawable.ship;
        sizeX = 5;
        sizeY = 5;
        y = 10;
        x = 1;
        speed = (float) 0.2;

        init(context); // инициализируем корабль
    }

    // перемещаем корабль в зависимости от нажатой кнопки
    void update() {
        if(MainActivity.isDownPressed && y <= GameView.maxY - 5){
            y += speed;
        }
        if(MainActivity.isUpPressed && y >= 0){
            y -= speed;
        }
    }
}