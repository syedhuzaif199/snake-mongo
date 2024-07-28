package inputs;

import entities.Snake;
import main.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LeftAction extends AbstractAction {
    private Snake snake;

    public LeftAction(Game game) {
        this.snake = game.getSnake();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.setDirection(Snake.Direction.LEFT);
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }
}
