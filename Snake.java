import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Snake extends Actor
{
    private List<SnakeSegment> segments = new ArrayList<>();
    private boolean dead;
    private int moveCount;

    public Snake()
    {
        dead = false;
        moveCount = 0;
        setImage("snake_up.png"); // Gambar karakter ular saat menghadap ke atas
    }

    protected void addedToWorld(World world) {
        // Ketika karakter ular ditambahkan ke dunia, tambahkan kepala ular ke dunia
        SnakeSegment head = new SnakeSegment(this);
        segments.add(head);
        world.addObject(head, getX(), getY());
    }

    public void act()
    {
        if (!dead)
        {
            checkCollision();
            controlSnake();
            moveSnake();
        }
    }

    public void checkCollision()
    {
        if (isTouching(Snake.class) || isAtEdge())
        {
            dead = true;
        }
    }

    public void controlSnake()
    {
        if (Greenfoot.isKeyDown("up"))
        {
            setRotation(270); // Menghadap ke Utara (North)
            setImage("snake_up.png"); // Mengganti gambar karakter ular saat menghadap ke atas
        }
        else if (Greenfoot.isKeyDown("right"))
        {
            setRotation(0); // Menghadap ke Timur (East)
            setImage("snake_right.png"); // Mengganti gambar karakter ular saat menghadap ke kanan
        }
        else if (Greenfoot.isKeyDown("down"))
        {
            setRotation(90); // Menghadap ke Selatan (South)
            setImage("snake_down.png"); // Mengganti gambar karakter ular saat menghadap ke bawah
        }
        else if (Greenfoot.isKeyDown("left"))
        {
            setRotation(180); // Menghadap ke Barat (West)
            setImage("snake_left.png"); // Mengganti gambar karakter ular saat menghadap ke kiri
        }
    }

    public void moveSnake()
    {
        if (moveCount >= getWorldOfType(SnakeGame.class).getSpeed())
        {
            moveCount = 0; // Reset moveCount
            int lastX = getX();
            int lastY = getY();
            move(1);
            for (SnakeSegment segment : segments)
            {
                int tempX = segment.getX();
                int tempY = segment.getY();
                segment.setLocation(lastX, lastY);
                lastX = tempX;
                lastY = tempY;
            }
        }
        else
        {
            moveCount++;
        }
    }

    public boolean isDead()
    {
        return dead;
    }

    public void grow()
    {
        SnakeSegment newSegment = new SnakeSegment(this);
        segments.add(newSegment);
        getWorld().addObject(newSegment, getX(), getY());
    }

    public boolean isFoodTouching()
    {
        List<Food> food = getObjectsAtOffset(0, 0, Food.class);
        return !food.isEmpty();
    }
}
