package entities;

import main.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Food {
    private int x, y;
    private Snake snake;

    public Food(Snake snake) {
        this.snake = snake;
        spawnFood(this.snake);
    }


    public void spawnFood(Snake snake) {
        Random rand = new Random();
        ArrayList<Rectangle> snakeBody = snake.getBody();
        int tempX = rand.nextInt(0, Game.GRID_WIDTH / Game.UNIT_SQUARE_SIZE) * Game.UNIT_SQUARE_SIZE;
        int tempY = rand.nextInt(0,Game.GRID_HEIGHT / Game.UNIT_SQUARE_SIZE) * Game.UNIT_SQUARE_SIZE;
        Point temp = new Point(tempX, tempY);
        boolean collides = false;
        for (Rectangle rectangle : snakeBody)
            if (rectangle.getLocation().equals(temp)) {
                collides = true;
                break;
            }
        if(collides)
            spawnFood(snake);
        else {
            x = tempX;
            y = tempY;
        }

    }

    public void draw(Graphics g) {
        g.setColor(new Color(200,92,39));
        g.fillRect(x , y, Game.UNIT_SQUARE_SIZE, Game.UNIT_SQUARE_SIZE);
    }

    public Point getLocation() {
        return new Point(x, y);
    }
}
