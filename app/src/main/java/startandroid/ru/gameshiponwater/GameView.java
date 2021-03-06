package startandroid.ru.gameshiponwater;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewDebug;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable{

    public static int maxX = 30;
    public static int maxY = 20;
    public static float unitW = 0;
    public static float unitH = 0;

    private boolean firstTime = true;
    private boolean gameRunning = true;
    private int amountOfLifes = 5;
    private int score = 0;
    private Ship ship;
    private BackgroundSea background;
    private ArrayList<LifeCount> lifesCount = new ArrayList<>();
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

                Context context = getContext();
                ship = new Ship(context); // добавляем корабль
                background = new BackgroundSea(context); //добавляем фон
                for (int i = 1; i <= amountOfLifes; i++) {
                    LifeCount lifeCount = new LifeCount(context, i);
                    lifesCount.add(lifeCount);
                }
            }

            canvas = surfaceHolder.lockCanvas();
            //canvas.drawColor(Color.BLACK);
            background.drow(paint, canvas);
            for (LifeCount lifeCount : lifesCount) {
                lifeCount.drow(paint, canvas);
            }
            ship.drow(paint, canvas); // рисуем корабль
            for (ThingOnWater thingOnWater : thingsOnWaters) {
                thingOnWater.drow(paint, canvas);
            }
            paint.setStrokeWidth(3);
            paint.setTextSize(100);
            paint.setColor(Color.BLUE);
            canvas.drawText(Integer.toString(score), 0, 100, paint); //рисуем счет

            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    // пауза на 17 миллисекунд
    private void control() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //проверяем столкновение корабля и предметов
    private void checkCollision(){

        ArrayList<ThingOnWater> thingsForRemote = new ArrayList<>();
        for (ThingOnWater currentThing : thingsOnWaters) {
            if(currentThing.isCollision(ship.x, ship.y, ship.sizeX, ship.sizeY)){
                if (currentThing.isDangereous()){
                    //удаляем сердце
                    int lastCount = lifesCount.size();
                    lifesCount.remove(lastCount - 1);
                }

                if (currentThing.isHeart()) {
                    int lastCount = lifesCount.size();
                    LifeCount lifeCount = new LifeCount(getContext(), lastCount + 1);
                    lifesCount.add(lifeCount);
                }
                //фиксируем предметы для удаления
                thingsForRemote.add(currentThing);

                if (lifesCount.size() == 0){
                    gameRunning = false;
                }

                int pointOfThing = currentThing.getPoints();
                score = score + pointOfThing;
            }
        }

        //удаляем предметы
        thingsOnWaters.removeAll(thingsForRemote);
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
