package startandroid.ru.gameshiponwater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

class GameObjects {

    float x; // координаты
    float y;
    float sizeX; // размер
    float sizeY;
    float speed; // скорость
    int bitmapId; // id картинки
    private Bitmap bitmap; // картинка

    void init(Context context) {
        // сжимаем картинку до нужных размеров
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int)(sizeX * GameView.unitW), (int)(sizeY * GameView.unitH), false);
        cBitmap.recycle();
    }

    // рисуем картинку
    void drow(Paint paint, Canvas canvas){
        canvas.drawBitmap(bitmap, x*GameView.unitW, y*GameView.unitH, paint);
    }
}