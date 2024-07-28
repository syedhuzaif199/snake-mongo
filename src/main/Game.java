package main;

import entities.*;
import gamestates.*;
import ui.Menu;
import inputs.*;
import utils.DBHandler;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game {
    private Snake snake;
    private Food food;
    private final GameWindow window;
    private final MainPanel mainPanel;
    private final Menu menu;
    private final Action inputHandler;
    private final UpAction upAction;
    private final DownAction downAction;
    private final LeftAction leftAction;
    private final RightAction rightAction;
    private final MenuInputHandler menuInputHandler;

    private SoundManager soundManager;
    private int score;
    private int highscore;
    private boolean paused;
    private boolean gameOver;
    private boolean resumeRequested;
    public static final int UNIT_SQUARE_SIZE = 16;
    public static final int GRID_WIDTH = 640;
    public static final int GRID_HEIGHT = 640;
    private String difficulty;

    public Game() {
        snake = new Snake();
        food = new Food(this.snake);
        score = 0;
        difficulty = "easy";
        soundManager = new SoundManager();
        gameOver = false;
        paused = false;
        resumeRequested = false;
        inputHandler = new KeyboardInputHandler(this);
        upAction = new UpAction(this);
        downAction = new DownAction(this);
        leftAction = new LeftAction(this);
        rightAction = new RightAction(this);
        menuInputHandler = new MenuInputHandler(this);
        menu = new Menu(this.menuInputHandler);
        setUpHighscores();
        mainPanel = new MainPanel(this);
        setUpInput();
        window = new GameWindow(this.menu);
        mainPanel.setScore(this.score, this.highscore);

    }

    private void setUpHighscores() {
        String[][] easyNamedScores = DBHandler.retrieveHighscores(5, "easy");
        String[][] mediumNamedScores = DBHandler.retrieveHighscores(5, "medium");
        String[][] hardNamedScores = DBHandler.retrieveHighscores(5, "hard");
        this.menu.setEasyNamedScores(easyNamedScores);
        this.menu.setMediumNamedScores(mediumNamedScores);
        this.menu.setHardNamedScores(hardNamedScores);
        this.menu.setUpHighscorePanel(this.difficulty);
        switch (this.difficulty) {
            case "easy":
                try {
                    this.highscore = Integer.parseInt(easyNamedScores[1][0]);
                } catch (NumberFormatException e) {
                    this.highscore = 0;
                }
                break;
            case "medium":
                try {
                    this.highscore = Integer.parseInt(mediumNamedScores[1][0]);
                } catch (NumberFormatException e) {
                    this.highscore = 0;
                }
                break;
            case "hard":
                try {
                    this.highscore = Integer.parseInt(hardNamedScores[1][0]);
                } catch (NumberFormatException e) {
                    this.highscore = 0;
                }
        }

    }

    public void uploadScore() {
        String name = this.mainPanel.getPlayerName();
        if (name == null || name.equals("")) {
            name = "Anonymous";
        }

        DBHandler.uploadScore(name, this.score, this.difficulty);
        this.setUpHighscores();
    }

    private void setUpInput() {
        this.mainPanel.getInputMap().put(KeyStroke.getKeyStroke('w'),"upAction");
        this.mainPanel.getInputMap().put(KeyStroke.getKeyStroke("UP"),"upAction");
        this.mainPanel.getActionMap().put("upAction", upAction);
        this.mainPanel.getInputMap().put(KeyStroke.getKeyStroke('a'),"leftAction");
        this.mainPanel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"leftAction");
        this.mainPanel.getActionMap().put("leftAction", leftAction);
        this.mainPanel.getInputMap().put(KeyStroke.getKeyStroke('s'),"downAction");
        this.mainPanel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"downAction");
        this.mainPanel.getActionMap().put("downAction", downAction);
        this.mainPanel.getInputMap().put(KeyStroke.getKeyStroke('d'),"rightAction");
        this.mainPanel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"rightAction");
        this.mainPanel.getActionMap().put("rightAction", rightAction);
        this.mainPanel.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),"pauseAction");
        this.mainPanel.getActionMap().put("pauseAction", inputHandler);
    }

    public void update() {
        if (Gamestate.state == Gamestate.PLAYING) {
            if (!this.gameOver) {
                if (this.paused) {
                    this.snake.setPaused(true);
                } else {
                    this.mainPanel.requestFocus();
                    this.snake.setPaused(false);
                    this.snake.update();
                    this.checkCollision();
                    this.eatFood();
                }
            }
        }
    }

    private void eatFood() {
        if(snake.getBody().get(0).getLocation().equals(food.getLocation())) {
            food.spawnFood(snake);
            this.soundManager.PlayAudio("/sounds/eat.wav");
            snake.growSnake();
            score++;
            if(score > highscore)
                highscore = score;
            mainPanel.setScore(score, highscore);


        }

    }

    public void checkCollision() {
        ArrayList<Rectangle> snakeBody = snake.getBody();
        for(int i = 1; i < snakeBody.size(); i++) {
            if(snakeBody.get(0).getLocation().equals(snakeBody.get(i).getLocation()))
                gameOver();
        }

        Rectangle snakeHead = snakeBody.get(0);
        if(snakeHead.x < 0 || snakeHead.x >= Game.GRID_WIDTH)
            gameOver();
        if(snakeHead.y < 0 || snakeHead.y >= Game.GRID_HEIGHT)
            gameOver();
    }

    public void newGame() {
        Gamestate.state = Gamestate.PLAYING;
        snake = new Snake();
        food = new Food(this.snake);
        score = 0;
        gameOver = false;
        paused = false;
        resumeRequested = false;
        setUpHighscores();
        mainPanel.forceResume();
        mainPanel.setDifficulty(this.difficulty);
        mainPanel.setScore(this.score, this.highscore);
        mainPanel.resetGamePanel();
        upAction.setSnake(this.snake);
        leftAction.setSnake(this.snake);
        downAction.setSnake(this.snake);
        rightAction.setSnake(this.snake);
    }

    public void gameOver() {
        gameOver = true;
        snake.setHeadColor(new Color(200, 50, 50));
        soundManager.PlayAudio("/sounds/gameover.wav");
        mainPanel.gameOver();

    }

    public void setGameOverPane() {
        mainPanel.setGameOverPane();
    }


    public void draw(Graphics g) {
        food.draw(g);
        snake.draw(g);

    }


    public void togglePaused() {
        if (!gameOver) {
            if (paused && !resumeRequested) {
                mainPanel.resumeGame();
                resumeRequested = true;
            } else {
                mainPanel.pauseGame();
                paused = true;
                resumeRequested = false;
            }

        }

    }

    public void resumeGame() {
        if (!gameOver) {
            paused = false;
            resumeRequested = false;
        }
    }

    public void setContentPane(Gamestate gamestate) {
        if (gamestate == Gamestate.MENU) {
            this.window.setContentPane(this.menu);
        } else if (gamestate == Gamestate.PLAYING) {
            this.window.setContentPane(this.mainPanel);
        }

        this.window.setVisible(true);
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public boolean isResumeRequested() {
        return this.resumeRequested;
    }

    public String getDifficulty() {
        return this.difficulty;
    }


    public Snake getSnake() {
        return snake;
    }

    public int getScore() {
        return score;
    }

    public GameWindow getWindow() {
        return window;
    }

    public Menu getMenu() {
        return menu;
    }

    public MenuInputHandler getMenuInputHandler() {
        return menuInputHandler;
    }

}
