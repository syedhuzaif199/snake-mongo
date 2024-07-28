package inputs;

import entities.Snake;
import main.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpAction extends AbstractAction {
    private Snake snake;

    public UpAction(Game game) {
        this.snake = game.getSnake();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.setDirection(Snake.Direction.UP);
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }
}
