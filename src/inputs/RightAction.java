package inputs;

import entities.Snake;
import main.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RightAction extends AbstractAction {
    private Snake snake;

    public RightAction(Game game) {
        this.snake = game.getSnake();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.setDirection(Snake.Direction.RIGHT);
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }
}
