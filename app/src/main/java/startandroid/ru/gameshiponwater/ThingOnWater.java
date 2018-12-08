package startandroid.ru.gameshiponwater;

import android.content.Context;

import java.util.Random;

public class ThingOnWater extends GameObjects{

    Random rand;
    private float minSpeed = (float) 0.1;
    private float maxSpeed = (float) 0.3;

    public ThingOnWater(Context context) {
        rand = new Random();

        // определяем начальные параметры
        bitmapId = getRandomThing();
        y = rand.nextInt(GameView.maxY - 5);
        x = GameView.maxX - 5;
        size = 5;
        speed = minSpeed + (maxSpeed - minSpeed) * rand.nextFloat();

        init(context); // инициализируем предмет
    }

    @Override
    public void update() {
        x -= speed;
    }

    //проверяем, столкнулся ли предмет с кораблем
    public boolean isCollision(float shipX, float shipY, float shipSize) {
        return !(((y+size) < shipY) // корабль ниже предмета
                ||(y > (shipY+shipSize)) //корабыль выше предмета
                ||((x+size) < shipX) //предмет перешел корабль
                ||(x > (shipX+shipSize))); // предмет не дошел до корабля
    }

    //получаем случайный предмет
    private int getRandomThing() {
        int idDrawable;
        int idThing = rand.nextInt(5) + 1;
        switch (idThing){
            case 1:
                idDrawable = R.drawable.banana;
                break;
            case 2:
                idDrawable = R.drawable.bottle;
                break;
            case 3:
                idDrawable = R.drawable.box;
                break;
            case 4:
                idDrawable = R.drawable.stone1;
                break;
            case 5:
                idDrawable = R.drawable.stone2;
                break;
            default:
                idDrawable = R.drawable.stone2;
                break;
        }
        return idDrawable;
    }
}
