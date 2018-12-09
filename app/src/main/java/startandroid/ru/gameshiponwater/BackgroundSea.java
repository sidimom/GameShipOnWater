package startandroid.ru.gameshiponwater;

import android.content.Context;

class BackgroundSea extends GameObjects{

    BackgroundSea(Context context) {

        // определяем начальные параметры
        bitmapId = R.drawable.background_sea1;
        y = 0;
        x = 0;
        sizeX = GameView.maxX;
        sizeY = GameView.maxY;

        init(context); // инициализируем предмет
    }
}
