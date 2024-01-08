import greenfoot.*;

public class SnakeGame extends World
{
    private Snake snake;
    private Food food;
    private int score;
    private int speed;
    private int actCount;

    public SnakeGame()
    {
        super(20, 20, 30);
        snake = new Snake();
        food = new Food();
        score = 0;
        speed = 10; // Atur kecepatan awal
        addObject(snake, 10, 10);
        addObject(food, Greenfoot.getRandomNumber(19), Greenfoot.getRandomNumber(19));
        showText("Score: " + score, 5, 5);
        actCount = 0;
    }

    public void act()
    {
        if (snake.isDead())
        {
            Greenfoot.stop();
            showText("Game Over!", getWidth() / 2, getHeight() / 2);
        }
        else
        {
            actCount++;
            if (actCount >= speed) { // Memeriksa apakah sudah waktunya untuk memproses tindakan
                actCount = 0; // Reset actCount
                if (snake.isFoodTouching()) // Memeriksa apakah karakter ular menyentuh makanan
                {
                    score++;
                    removeObject(food);
                    addObject(food, Greenfoot.getRandomNumber(19), Greenfoot.getRandomNumber(19));
                    showText("Score: " + score, 5, 5);
                    snake.grow();
                }
                snake.act(); // Panggil act dari Snake
            }
        }
    }

    public int getSpeed() {
        return speed;
    }
}
