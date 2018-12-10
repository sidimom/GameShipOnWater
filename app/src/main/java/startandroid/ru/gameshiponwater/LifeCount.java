package startandroid.ru.gameshiponwater;

import android.content.Context;

public class LifeCount extends GameObjects{

    public LifeCount(Context context , int i) {

        // определяем начальные параметры
        bitmapId = R.drawable.heart;
        y = 0;
        x = (float) (GameView.maxX - (i*1.5));
        sizeX = (float) 1.5;
        sizeY = (float) 1.5;

        init(context); // инициализируем предмет
    }
}
