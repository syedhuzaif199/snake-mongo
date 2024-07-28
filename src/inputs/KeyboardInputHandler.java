package inputs;

import entities.Snake;
import main.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyboardInputHandler extends AbstractAction {
    private Game game;

    public KeyboardInputHandler(Game game) {
        this.game = game;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("\u001b".equals(e.getActionCommand())) {
            game.togglePaused();
        }
    }

}

