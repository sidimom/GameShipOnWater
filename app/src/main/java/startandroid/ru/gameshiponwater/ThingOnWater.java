package startandroid.ru.gameshiponwater;

import android.content.Context;

import java.util.Random;

class ThingOnWater extends GameObjects{

    private Random rand;
    private boolean dangereous;
    private boolean isHeart;
    private int points = 0;

    ThingOnWater(Context context) {
        rand = new Random();

        // определяем начальные параметры
        setRandomThing();
        y = rand.nextInt(GameView.maxY - 5);
        x = GameView.maxX - 5;
        sizeX = 5;
        sizeY = 5;
        float minSpeed = (float) 0.1;
        float maxSpeed = (float) 0.3;
        speed = minSpeed + (maxSpeed - minSpeed) * rand.nextFloat();

        init(context); // инициализируем предмет
    }

    void update() {
        x -= speed;
    }

    //проверяем, столкнулся ли предмет с кораблем
    boolean isCollision(float shipX, float shipY, float shipSizeX, float shipSizeY) {
        return !(((y+sizeY) < shipY) // корабль ниже предмета
                ||(y > (shipY+shipSizeY)) //корабыль выше предмета
                ||((x+sizeY) < shipX) //предмет перешел корабль
                ||(x > (shipX+shipSizeX))); // предмет не дошел до корабля
    }

    //получаем случайный предмет
    private void setRandomThing() {
        int idThing = rand.nextInt(6) + 1;
        switch (idThing){
            case 1:
                bitmapId = R.drawable.banana;
                dangereous = false;
                isHeart = false;
                points = 10;
                break;
            case 2:
                bitmapId = R.drawable.bottle;
                dangereous = false;
                isHeart = false;
                points = 20;
                break;
            case 3:
                bitmapId = R.drawable.box;
                dangereous = true;
                isHeart = false;

                break;
            case 4:
                bitmapId = R.drawable.stone1;
                dangereous = true;
                isHeart = false;
                break;
            case 5:
                bitmapId = R.drawable.stone2;
                dangereous = true;
                isHeart = false;
                break;
            case 6:
                bitmapId = R.drawable.heart1;
                dangereous = false;
                isHeart = true;
                break;
            default:
                bitmapId = R.drawable.stone2;
                dangereous = true;
                isHeart = false;
                break;
        }
    }

    boolean isDangereous() {
        return dangereous;
    }

    boolean isHeart() {
        return isHeart;
    }

    int getPoints() {
        return points;
    }
}
