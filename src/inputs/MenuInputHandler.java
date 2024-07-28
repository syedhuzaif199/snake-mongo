package inputs;

import gamestates.Gamestate;
import main.Game;
import utils.LoadSave;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInputHandler implements ActionListener {

    private Game game;

    public MenuInputHandler(Game game) {
        this.game = game;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "play", "play again" -> {
                this.game.newGame();
                this.game.setContentPane(Gamestate.PLAYING);
            }
            case "resume" -> this.game.togglePaused();
            case "main menu" -> {
                Gamestate.state = Gamestate.MENU;
                this.game.setContentPane(Gamestate.MENU);
            }
            case "options" -> this.game.getMenu().changeToOptionsPane();
            case "select background" -> this.game.getMenu().changeToBgSelectPane();
            case "select difficulty" -> this.game.getMenu().changeToDifficultySelectPane();
            case "easy", "medium", "hard" -> {
                this.game.setDifficulty(e.getActionCommand());
                this.game.getMenu().setDiffSelectable(e.getActionCommand());
            }
            case "highscores" -> this.game.getMenu().changeToHighscorePane();
            case "<" -> this.game.getMenu().switchDiffPanels(-1);
            case ">" -> this.game.getMenu().switchDiffPanels(1);
            case "how to play" -> this.game.getMenu().changeToInfoPane();
            case "quit" -> System.exit(0);
            case "back" -> this.game.getMenu().changeToMenuPane();
            case "grid" -> {
                this.game.getMenu().setBgSelectable(e.getActionCommand());
                LoadSave.selectBg("background_grid.png");
            }
            case "dot matrix" -> {
                LoadSave.selectBg("background_dotted.png");
                this.game.getMenu().setBgSelectable(e.getActionCommand());
            }
            case "stripes" -> {
                LoadSave.selectBg("background_striped.png");
                this.game.getMenu().setBgSelectable(e.getActionCommand());
            }
            case "dense stripes" -> {
                LoadSave.selectBg("background_dense_striped.png");
                this.game.getMenu().setBgSelectable(e.getActionCommand());
            }
            case "click to continue" -> this.game.setGameOverPane();
            case "submit" -> {
                this.game.uploadScore();
                Gamestate.state = Gamestate.MENU;
                this.game.setContentPane(Gamestate.MENU);
            }
        }


    }
}
