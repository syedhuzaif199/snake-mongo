package entities;

import gamestates.Gamestate;
import main.Game;

import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private ArrayList<Rectangle> body;

    public enum Direction {
        UP, LEFT, DOWN, RIGHT, NULL
    }
    private Direction direction, standByDir, tailDirection;
    private boolean directionChangeable, tryDirectionChange;
    private Color headColor = Color.WHITE;
    private boolean paused;
    public Snake() {
        body = new ArrayList<>();
        paused = false;
        direction = Direction.RIGHT;
        standByDir = Direction.RIGHT;
        tailDirection = Direction.RIGHT;
        directionChangeable = true;
        tryDirectionChange = false;
        Rectangle[] temp = new Rectangle[3];
        for(int i = 0; i < 3; i++) {
            temp[i] = new Rectangle(Game.UNIT_SQUARE_SIZE, Game.UNIT_SQUARE_SIZE);
            temp[i].setLocation(Game.GRID_WIDTH / 2 - i*Game.UNIT_SQUARE_SIZE, Game.GRID_HEIGHT / 2);
            body.add(temp[i]);
        }

    }

    public ArrayList<Rectangle> getBody() {
        return body;
    }

    public void update() {
        Rectangle snakeTail = body.get(body.size() - 1);
        Rectangle tailLeader = body.get(body.size() - 2);
        move();
        if(tailLeader.x - snakeTail.x > 0)
            tailDirection = Direction.RIGHT;
        else if(tailLeader.x - snakeTail.x < 0)
            tailDirection = Direction.LEFT;

        if(tailLeader.y - snakeTail.y > 0)
            tailDirection = Direction.DOWN;
        else if(tailLeader.y - snakeTail.y < 0)
            tailDirection = Direction.UP;
        directionChangeable = true;
        if(tryDirectionChange) {
            setDirection(standByDir);
            System.out.println("Direction changed!");
            tryDirectionChange = false;
        }
    }

    public void move() {
        Rectangle snakeHead = body.get(0);
        Rectangle advancedHead = new Rectangle(Game.UNIT_SQUARE_SIZE, Game.UNIT_SQUARE_SIZE);
        if(direction == Direction.NULL)
            return;

        switch (direction) {
            case UP -> advancedHead.setLocation(snakeHead.x, snakeHead.y - Game.UNIT_SQUARE_SIZE);
            case LEFT -> advancedHead.setLocation(snakeHead.x - Game.UNIT_SQUARE_SIZE, snakeHead.y);
            case DOWN -> advancedHead.setLocation(snakeHead.x, snakeHead.y + Game.UNIT_SQUARE_SIZE);
            case RIGHT -> advancedHead.setLocation(snakeHead.x + Game.UNIT_SQUARE_SIZE, snakeHead.y);
            default -> {
            }
        }

        body.add(0, advancedHead);
        body.remove(body.size() -1);
    }

    public void growSnake() {
        Rectangle snakeTail = body.get(body.size() - 1);
        Rectangle tailLeader = body.get(body.size() - 2);
        Rectangle growth = new Rectangle(Game.UNIT_SQUARE_SIZE, Game.UNIT_SQUARE_SIZE);


        if(tailDirection == Direction.NULL)
            return;

        switch (tailDirection) {
            case UP -> growth.setLocation(snakeTail.x, snakeTail.y + Game.UNIT_SQUARE_SIZE);
            case LEFT -> growth.setLocation(snakeTail.x + Game.UNIT_SQUARE_SIZE, snakeTail.y);
            case DOWN -> growth.setLocation(snakeTail.x, snakeTail.y - Game.UNIT_SQUARE_SIZE);
            case RIGHT -> growth.setLocation(snakeTail.x - Game.UNIT_SQUARE_SIZE, snakeTail.y);
            default -> {
            }
        }

        body.add(body.size(), growth);
    }

    public void draw(Graphics g) {
        for (Rectangle r: body) {
            if(body.indexOf(r) == 0)
                g.setColor(headColor);

            else
                g.setColor(new Color(106, 200, 89));
            g.fillRect(r.x, r.y, r.width, r.height);
            g.setColor(Color.BLACK);
            g.drawRect(r.x, r.y, r.width, r.height);

        }
    }


    public void setDirection(Snake.Direction direction) {
        if(paused)
            return;
        if(!directionChangeable) {
            tryDirectionChange = true;
            standByDir = direction;
            System.out.println("Direction change requested...");
            return;
        }
        Direction temp = this.direction;

        if(temp == Direction.UP || temp == Direction.DOWN)
            if(direction == Direction.LEFT || direction == Direction.RIGHT) {
                this.direction = direction;
                directionChangeable = false;
            }

        if(temp == Direction.LEFT || temp == Direction.RIGHT)
            if(direction == Direction.UP || direction == Direction.DOWN) {
                this.direction = direction;
                directionChangeable = false;
            }

    }

    public void setHeadColor(Color headColor) {
        this.headColor = headColor;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
