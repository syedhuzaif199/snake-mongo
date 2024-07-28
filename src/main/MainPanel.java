package main;

import ui.UIButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainPanel extends JPanel implements ActionListener {

    private Game game;
    private GamePanel gamePanel;
    private JPanel gridPanel;
    private JLabel highscoreValueLabel, scoreValueLabel;
    private Timer timer;
    UIButton pauseButton;
    private boolean doingCountdown;
    private int countdown = 30;
    private String difficulty;


    public MainPanel(Game game) {
        this.game = game;
        gamePanel = new GamePanel(game);
        gridPanel = new JPanel(new GridLayout(1,3));
        gridPanel.setBackground(new Color(10,10,10));

        setUpPanels();
        setLayout(new BorderLayout());
        add(gridPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        doingCountdown = false;
        difficulty = game.getDifficulty();
        timer = new Timer(100, this);

        timer.start();

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();
        repaint();
        if(doingCountdown) {
            if(countdown >= 0) {
                gamePanel.removePausedPanel(countdown);
                countdown--;
            }
            else {
                doingCountdown = false;
                game.resumeGame();
            }
        }

        if(e.getActionCommand() == "||" && !game.isGameOver()) {
            game.togglePaused();
        }

        if((e.getActionCommand() == "|>" || e.getActionCommand() == "resume") && !game.isGameOver()) {
            game.togglePaused();
        }


        if (this.game.isPaused() && !this.game.isResumeRequested()) {
            this.pauseButton.setText("|>");
        } else {
            this.pauseButton.setText("||");
        }


    }

    public void setUpPanels() {
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        scorePanel.setBackground(new Color(0,0,0, 0));

        JLabel scoreLabel = new JLabel("score:");
        scoreLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        scoreLabel.setForeground(new Color(106,135,89));
        scorePanel.add(scoreLabel);

        scoreValueLabel = new JLabel("0");
        scoreValueLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        scoreValueLabel.setForeground(new Color(106,135,89));
        scoreValueLabel.setOpaque(false);
        scorePanel.add(scoreValueLabel);

        JPanel highscorePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        highscorePanel.setBackground(new Color(0,0,0,0));

        JLabel highscoreLabel = new JLabel("highscore:");
        highscoreLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        highscoreLabel.setForeground(new Color(106,135,89));
        highscorePanel.add(highscoreLabel);

        highscoreValueLabel = new JLabel("");
        highscoreValueLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        highscoreValueLabel.setForeground(new Color(106,135,89));
        highscoreValueLabel.setOpaque(false);
        highscorePanel.add(highscoreValueLabel);

        pauseButton = new UIButton("||");
        pauseButton.setFont(new Font("Consolas", Font.BOLD, 14));
        pauseButton.setDefaultForegroundColor(Color.gray);
        pauseButton.setHoverForegroundColor(Color.lightGray);
        pauseButton.setPressedForegroundColor(Color.darkGray);
        pauseButton.addActionListener(this);

        gridPanel.add(highscorePanel);
        gridPanel.add(pauseButton);
        gridPanel.add(scorePanel);

    }

    public void setScore(int score, int highscore) {
        scoreValueLabel.setText(String.valueOf(score));
        highscoreValueLabel.setText("" + highscore + " | " + this.difficulty);
    }

    public void pauseGame() {
        gamePanel.addPausePanel();
        doingCountdown = false;
    }

    public void resumeGame() {
        countdown = 30;
        doingCountdown = true;
    }

    public void forceResume() {
        gamePanel.removePausedPanel(0);
    }

    public void gameOver() {
        gamePanel.gameOver();
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        int delay = 100;
        switch (difficulty) {
            case "medium":
                delay = 50;
                break;
            case "hard":
                delay = 40;
        }

        this.timer.setDelay(delay);
    }

    public void setGameOverPane() {
        gamePanel.setGameOverPane();
    }

    public void resetGamePanel() {
        gamePanel.reset();
    }

    public String getPlayerName() {
        return gamePanel.getPlayerName();
    }


}