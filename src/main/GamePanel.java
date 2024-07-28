package main;

import ui.UIButton;
import utils.LoadSave;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    private Game game;
    private JPanel pausedPanel, buttonsPanel, gameOverPanel, inputPanel;
    private JLabel pausedTitle, gameOverTitle, countdownLabel,scoreLabel;
    private JTextField nameTextField;
    private UIButton resumeButton, menuButton, quitButton, continueText;
    private Color buttonColor, titleColor, hoverButtonColor, pressedButtonColor;

    public boolean paused, gameOver, displayScore;

    public GamePanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(Game.GRID_WIDTH,Game.GRID_HEIGHT));

        buttonColor =Color.lightGray; //new Color(200,92,39);
        titleColor = new Color(106, 180, 89);
        hoverButtonColor = Color.gray;
        pressedButtonColor = Color.darkGray;

        setUpPausedPanel();

        setUpGameOverPanel();

    }

    public void setUpGameOverPanel() {
        gameOverPanel = new JPanel(new BorderLayout());
        gameOverPanel.setOpaque(false);
        //gameOverPanel.setPreferredSize(new Dimension(Game.GRID_WIDTH, Game.GRID_HEIGHT));
        gameOverPanel.setVisible(false);

        JPanel gOTitlePanel = new JPanel();
        gOTitlePanel.setOpaque(false);
        gOTitlePanel.setPreferredSize(new Dimension(Game.GRID_WIDTH, 8 * Game.UNIT_SQUARE_SIZE));
        gameOverTitle = new JLabel("game over");
        gameOverTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverTitle.setForeground(titleColor);
        gameOverTitle.setFont(LoadSave.GetFont(LoadSave.SQUARE_FONT, 72f));
        gOTitlePanel.add(gameOverTitle);
        gameOverPanel.add(gOTitlePanel, BorderLayout.NORTH);

        continueText = new UIButton("click to continue");
        continueText.setDefaultForegroundColor(Color.lightGray);
        continueText.setHoverForegroundColor(Color.lightGray);
        continueText.setPressedForegroundColor(Color.lightGray);
        continueText.setVerticalAlignment(SwingConstants.TOP);
        continueText.setFont(new Font("Consolas", Font.PLAIN, 28));
        continueText.setPreferredSize(new Dimension(Game.GRID_WIDTH, 30 * Game.UNIT_SQUARE_SIZE));
        continueText.addActionListener(game.getMenuInputHandler());

        gameOverPanel.add(continueText, BorderLayout.CENTER);

        inputPanel = new JPanel(new GridLayout(6, 1, 0, 0));
        inputPanel.setOpaque(false);

        scoreLabel = new JLabel();
        scoreLabel.setText(String.valueOf(game.getScore()));
        scoreLabel.setForeground(Color.lightGray);
        scoreLabel.setFont(new Font("Consolas", Font.PLAIN, 72));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(scoreLabel);

        JLabel enterNameLabel = new JLabel("Enter your name");
        enterNameLabel.setFont(LoadSave.GetFont(LoadSave.SQUARE_FONT, 48f));
        enterNameLabel.setForeground(Color.lightGray);
        enterNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(enterNameLabel);

        nameTextField = new JTextField(10);
        nameTextField.setBackground(new Color(0,0,0,200));
        nameTextField.setForeground(Color.lightGray);
        nameTextField.setCaretColor(Color.lightGray);
        nameTextField.setFont(LoadSave.GetFont(LoadSave.SQUARE_FONT, 36));
        nameTextField.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel tfPanel = new JPanel();
        tfPanel.setOpaque(false);
        tfPanel.setPreferredSize(new Dimension(16 * Game.UNIT_SQUARE_SIZE, 4 * Game.UNIT_SQUARE_SIZE));
        tfPanel.add(nameTextField);
        inputPanel.add(tfPanel);

        UIButton submitButton = new UIButton("submit");
        submitButton.setDefaultForegroundColor(new Color(200,92,39));
        submitButton.setHoverForegroundColor(hoverButtonColor);
        submitButton.setPressedForegroundColor(pressedButtonColor);
        submitButton.setFont(LoadSave.GetFont(LoadSave.SQUARE_FONT, 36f));
        submitButton.addActionListener(game.getMenuInputHandler());
        inputPanel.add(submitButton);

        UIButton restartButton = new UIButton("play again");
        restartButton.setDefaultForegroundColor(new Color(200,92,39));
        restartButton.setHoverForegroundColor(hoverButtonColor);
        restartButton.setPressedForegroundColor(pressedButtonColor);
        restartButton.setFont(LoadSave.GetFont(LoadSave.SQUARE_FONT, 36f));
        restartButton.addActionListener(game.getMenuInputHandler());
        inputPanel.add(restartButton);

        UIButton mainMenuBtn = new UIButton("main menu");
        mainMenuBtn.setDefaultForegroundColor(new Color(200,92,39));
        mainMenuBtn.setHoverForegroundColor(hoverButtonColor);
        mainMenuBtn.setPressedForegroundColor(pressedButtonColor);
        mainMenuBtn.setFont(LoadSave.GetFont(LoadSave.SQUARE_FONT, 36f));
        mainMenuBtn.addActionListener(game.getMenuInputHandler());
        inputPanel.add(mainMenuBtn);

    }

    public void setUpPausedPanel() {
        pausedPanel = new JPanel(new BorderLayout());
        pausedPanel.setOpaque(false);

        pausedTitle = new JLabel("paused");
        pausedTitle.setHorizontalAlignment(SwingConstants.CENTER);
        pausedTitle.setForeground(titleColor);
        pausedTitle.setFont(LoadSave.GetFont(LoadSave.SQUARE_FONT, 72f));
        JPanel pauseTitlePanel = new JPanel();
        pauseTitlePanel.setOpaque(false);
        pauseTitlePanel.setPreferredSize(new Dimension(Game.GRID_WIDTH, 6 * Game.UNIT_SQUARE_SIZE));
        pauseTitlePanel.add(pausedTitle);
        pausedPanel.add(pauseTitlePanel, BorderLayout.NORTH);

        countdownLabel = new JLabel("0");
        countdownLabel.setHorizontalAlignment(SwingConstants.CENTER);
        countdownLabel.setForeground(Color.lightGray);
        countdownLabel.setFont(new Font("Consolas", Font.BOLD, 120));
        pausedPanel.add(countdownLabel, BorderLayout.CENTER);

        buttonsPanel = new JPanel(new GridLayout(3, 1,0,40));
        buttonsPanel.setOpaque(false);

        resumeButton = new UIButton("resume");
        resumeButton.setDefaultForegroundColor(buttonColor);
        resumeButton.setHoverForegroundColor(hoverButtonColor);
        resumeButton.setPressedForegroundColor(pressedButtonColor);
        resumeButton.setFont(LoadSave.GetFont(LoadSave.SQUARE_FONT, 48f));
        resumeButton.addActionListener(game.getMenuInputHandler());
        buttonsPanel.add(resumeButton);

        menuButton = new UIButton("main menu");
        menuButton.setDefaultForegroundColor(buttonColor);
        menuButton.setHoverForegroundColor(hoverButtonColor);
        menuButton.setPressedForegroundColor(pressedButtonColor);
        menuButton.setFont(LoadSave.GetFont(LoadSave.SQUARE_FONT, 48f));
        menuButton.addActionListener(game.getMenuInputHandler());
        buttonsPanel.add(menuButton);

        quitButton = new UIButton("quit");
        quitButton.setDefaultForegroundColor(buttonColor);
        quitButton.setHoverForegroundColor(hoverButtonColor);
        quitButton.setPressedForegroundColor(pressedButtonColor);
        quitButton.setFont(LoadSave.GetFont(LoadSave.SQUARE_FONT, 48f));
        quitButton.addActionListener(game.getMenuInputHandler());
        buttonsPanel.add(quitButton);

        pausedPanel.add(buttonsPanel, BorderLayout.CENTER);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage background = LoadSave.GetImage(LoadSave.getSelectedBg());
        g.drawImage(background, 0, 0, background.getWidth(), background.getHeight(), null);
        game.draw(g);
        if(paused) {
            g.setColor(new Color(0, 0, 0, 50));
            g.fillRect(0, 0, Game.GRID_WIDTH, Game.GRID_HEIGHT);
        }
        if(gameOver) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, Game.GRID_WIDTH, Game.GRID_HEIGHT);
        }
        if(displayScore) {
            g.setColor(new Color(255, 255, 255, 20));
            g.fillRoundRect(Game.GRID_WIDTH / 2 - 3 * Game.UNIT_SQUARE_SIZE, inputPanel.getY() - Game.UNIT_SQUARE_SIZE, 6 * Game.UNIT_SQUARE_SIZE, scoreLabel.getHeight() + Game.UNIT_SQUARE_SIZE, Game.UNIT_SQUARE_SIZE / 2, Game.UNIT_SQUARE_SIZE / 2);
        }

    }

    public void addPausePanel() {
        paused = true;
        add(pausedPanel);
        onPauseAction(true);
    }

    public void removePausedPanel(int count) {
        onPauseAction(false);
        if(count > 0)
            countdownLabel.setText(String.valueOf((int)Math.ceil(count/(double)10)));
        else {
            paused = false;
            remove(pausedPanel);
            repaint();
        }
    }

    public void onPauseAction(boolean action) {
        if(action) {
            pausedPanel.add(buttonsPanel, BorderLayout.CENTER);
            pausedTitle.setText("paused");
            pausedTitle.setForeground(titleColor);
            countdownLabel.setVisible(false);
        }
        else {
            pausedTitle.setText("resuming in...");
            pausedTitle.setForeground(Color.lightGray);
            pausedPanel.add(countdownLabel, BorderLayout.CENTER);
            countdownLabel.setVisible(true);
        }
        buttonsPanel.setVisible(action);
    }

    public void gameOver() {
        gameOver = true;
        add(gameOverPanel);
        gameOverPanel.add(continueText);
        inputPanel.setVisible(false);
        gameOverPanel.setVisible(true);
        System.out.println("Added gameOver panel!");
    }

    public void setGameOverPane() {
        displayScore = true;
        gameOverPanel.remove(continueText);
        gameOverPanel.add(inputPanel);
        inputPanel.setVisible(true);
    }

    public void reset() {
        gameOver = false;
        displayScore = false;
        nameTextField.setText("");
        remove(gameOverPanel);


    }

    public String getPlayerName() {
        return nameTextField.getText();
    }
}
