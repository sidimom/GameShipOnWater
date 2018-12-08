package startandroid.ru.gameshiponwater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable{

    public static int maxX = 30;
    public static int maxY = 20;
    public static float unitW = 0;
    public static float unitH = 0;

    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Ship ship;
    private ArrayList<ThingOnWater> thingsOnWaters = new ArrayList<>();
    private final int THING_INTERVAL = 50;
    private int currentTime = 0;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    public GameView(Context context) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();

        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            checkCollision();
            checkIfNewThing();
            control();
        }
    }

    //обновляем координаты расположения корабля и предметов
    private void update() {
        if(!firstTime) {
            ship.update();
            for (ThingOnWater thingOnWater : thingsOnWaters) {
                thingOnWater.update();
            }
        }
    }

    //рисуем элементы
    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            // инициализация при первом запуске
            if(firstTime){
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;

                ship = new Ship(getContext()); // добавляем корабль
            }

            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            //пытался фон добавить, позже сделаю
            /*Paint paintBack = new Paint();
            Bitmap cbitmapBack = BitmapFactory.decodeResource(getResources(), R.drawable.background_sea);
            Bitmap bitmapBack = Bitmap.createScaledBitmap(
                    cbitmapBack, (int)(maxX * GameView.unitW), (int)(maxY * GameView.unitH), false);
            cbitmapBack.recycle();
            canvas.drawBitmap(bitmapBack, 0, 0, paintBack);*/

            ship.drow(paint, canvas); // рисуем корабль

            for (ThingOnWater thingOnWater : thingsOnWaters) {
                thingOnWater.drow(paint, canvas);
            }

            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    // пауза на 17 миллисекунд
    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //проверяем столкновение корабля и предметов
    private void checkCollision(){
        for (ThingOnWater currentThing : thingsOnWaters) {
            if(currentThing.isCollision(ship.x, ship.y, ship.size)){
                gameRunning = false;
            }
        }
    }

    //периодически создаем новый предмет
    private void checkIfNewThing(){
        if(currentTime >= THING_INTERVAL){
            ThingOnWater thingOnWater = new ThingOnWater(getContext());
            thingsOnWaters.add(thingOnWater);
            currentTime = 0;
        }else{
            currentTime ++;
        }
    }
}
