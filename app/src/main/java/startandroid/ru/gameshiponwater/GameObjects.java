package startandroid.ru.gameshiponwater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GameObjects {

    protected float x; // координаты
    protected float y;
    protected float size; // размер
    protected float speed; // скорость
    protected int bitmapId; // id картинки
    protected Bitmap bitmap; // картинка

    void init(Context context) {
        // сжимаем картинку до нужных размеров
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int)(size * GameView.unitW), (int)(size * GameView.unitH), false);
        cBitmap.recycle();
    }

    // рисуем картинку
    void drow(Paint paint, Canvas canvas){
        canvas.drawBitmap(bitmap, x*GameView.unitW, y*GameView.unitH, paint);
    }
}