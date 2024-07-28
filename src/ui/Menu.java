//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ui;

import inputs.MenuInputHandler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.ImageObserver;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import utils.LoadSave;

public class Menu extends JPanel {
    private final MenuInputHandler menuInputHandler;
    private JPanel menuPanel;
    private JPanel highscoresPanel;
    private JPanel optionsPanel;
    private JPanel bgSelectPanel;
    private JPanel difficultySelectPanel;
    private JPanel infoPanel;
    private JPanel centerPanel;
    private JPanel scoreDisplayPanel;
    private JPanel bgButtonsPanel;
    private JPanel diffButtonsPanel;
    private JLabel diffLabel;
    private final Font buttonFontMedium;
    private final Font buttonFontSmall;
    private final Font titleFont;
    private final Color buttonColor;
    private final Color hoverButtonColor;
    private final Color pressedButtonColor;
    private final Color titleColor;
    private String[][] easyNamedScores;
    private String[][] mediumNamedScores;
    private String[][] hardNamedScores;
    private final Color scoreLabelColor;
    private final Color scoreValueColor;
    private final Font scoreLabelFont;
    private final Font scoreValueFont;
    private boolean isInMenu;
    private int currentDifficulty;

    public Menu(MenuInputHandler menuInputHandler) {
        this.menuInputHandler = menuInputHandler;
        this.setPreferredSize(new Dimension(640, 640));
        this.buttonColor = Color.gray;
        this.titleColor = new Color(106, 200, 89);
        this.hoverButtonColor = Color.lightGray;
        this.pressedButtonColor = Color.darkGray;
        Font myFont = LoadSave.GetFontRaw("fonts/square.ttf");
        this.buttonFontSmall = myFont.deriveFont(36.0F);
        this.buttonFontMedium = myFont.deriveFont(48.0F);
        this.titleFont = myFont.deriveFont(54.0F);
        this.scoreValueColor = this.scoreLabelColor = Color.GRAY;
        this.scoreLabelFont = this.scoreValueFont = new Font("Consolas", 0, 36);
        this.setUpMenuPanel();
        this.isInMenu = true;
    }

    private void setUpMenuPanel() {
        this.menuPanel = new JPanel();
        this.menuPanel.setLayout(new BorderLayout());
        this.menuPanel.setOpaque(false);
        JLabel menuTitle = new JLabel("snake");
        menuTitle.setFont(this.titleFont);
        menuTitle.setForeground(this.titleColor);
        menuTitle.setHorizontalAlignment(0);
        JPanel verticalSpacer = new JPanel();
        verticalSpacer.setPreferredSize(new Dimension(640, 112));
        verticalSpacer.setOpaque(false);
        this.menuPanel.add(verticalSpacer, "North");
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 1, 0, 10));
        buttonsPanel.setOpaque(false);
        UIButton playButton = new UIButton("play");
        playButton.setFont(this.buttonFontMedium);
        playButton.setDefaultForegroundColor(this.buttonColor);
        playButton.setHoverForegroundColor(this.hoverButtonColor);
        playButton.setPressedForegroundColor(this.pressedButtonColor);
        playButton.addActionListener(this.menuInputHandler);
        buttonsPanel.add(playButton);
        UIButton optionsButton = new UIButton("options");
        optionsButton.setFont(this.buttonFontMedium);
        optionsButton.setDefaultForegroundColor(this.buttonColor);
        optionsButton.setHoverForegroundColor(this.hoverButtonColor);
        optionsButton.setPressedForegroundColor(this.pressedButtonColor);
        optionsButton.addActionListener(this.menuInputHandler);
        buttonsPanel.add(optionsButton);
        UIButton highscoreButton = new UIButton("highscores");
        highscoreButton.setFont(this.buttonFontMedium);
        highscoreButton.setDefaultForegroundColor(this.buttonColor);
        highscoreButton.setHoverForegroundColor(this.hoverButtonColor);
        highscoreButton.setPressedForegroundColor(this.pressedButtonColor);
        highscoreButton.addActionListener(this.menuInputHandler);
        buttonsPanel.add(highscoreButton);
        UIButton infoButton = new UIButton("how to play");
        infoButton.setFont(this.buttonFontMedium);
        infoButton.setDefaultForegroundColor(this.buttonColor);
        infoButton.setHoverForegroundColor(this.hoverButtonColor);
        infoButton.setPressedForegroundColor(this.pressedButtonColor);
        infoButton.addActionListener(this.menuInputHandler);
        buttonsPanel.add(infoButton);
        UIButton quitButton = new UIButton("quit");
        quitButton.setFont(this.buttonFontMedium);
        quitButton.setDefaultForegroundColor(this.buttonColor);
        quitButton.setHoverForegroundColor(this.hoverButtonColor);
        quitButton.setPressedForegroundColor(this.pressedButtonColor);
        quitButton.addActionListener(this.menuInputHandler);
        buttonsPanel.add(quitButton);
        this.menuPanel.add(buttonsPanel, "Center");
        this.add(this.menuPanel);
    }

    public void setUpHighscorePanel(String difficulty) {
        this.highscoresPanel = new JPanel();
        this.highscoresPanel.setLayout(new BorderLayout());
        this.highscoresPanel.setOpaque(false);
        JLabel highscoreTitle = new JLabel("highscores");
        highscoreTitle.setForeground(this.titleColor);
        highscoreTitle.setFont(this.titleFont);
        highscoreTitle.setHorizontalAlignment(0);
        this.highscoresPanel.add(highscoreTitle, "North");
        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setOpaque(false);
        this.centerPanel.setPreferredSize(new Dimension(608, 512));
        JPanel diffSelectBtnPanel = new JPanel();
        diffSelectBtnPanel.setOpaque(false);
        UIButton prevBtn = new UIButton("<");
        prevBtn.setDefaultForegroundColor(this.buttonColor);
        prevBtn.setHoverForegroundColor(this.hoverButtonColor);
        prevBtn.setPressedForegroundColor(this.pressedButtonColor);
        prevBtn.setFont(this.buttonFontSmall);
        prevBtn.addActionListener(this.menuInputHandler);
        UIButton nextBtn = new UIButton(">");
        nextBtn.setDefaultForegroundColor(this.buttonColor);
        nextBtn.setHoverForegroundColor(this.hoverButtonColor);
        nextBtn.setPressedForegroundColor(this.pressedButtonColor);
        nextBtn.setFont(this.buttonFontSmall);
        nextBtn.addActionListener(this.menuInputHandler);
        this.diffLabel = new JLabel("easy");
        this.diffLabel.setForeground(Color.darkGray);
        this.diffLabel.setFont(this.buttonFontMedium);
        diffSelectBtnPanel.add(prevBtn);
        diffSelectBtnPanel.add(this.diffLabel);
        diffSelectBtnPanel.add(nextBtn);
        this.centerPanel.add(diffSelectBtnPanel, "North");
        this.currentDifficulty = 0;
        this.setUpScoreDisplayPanel(difficulty);
    }

    public void setUpScoreDisplayPanel(String difficulty) {
        if (this.scoreDisplayPanel != null) {
            this.centerPanel.remove(this.scoreDisplayPanel);
        }

        this.scoreDisplayPanel = new JPanel();
        this.scoreDisplayPanel.setBackground(new Color(0, 0, 0, 0));
        this.scoreDisplayPanel.setLayout(new GridLayout(5, 2));
        JLabel[] scoreLabels = new JLabel[5];
        JLabel[] scoreValues = new JLabel[5];

        for(int i = 0; i < 5; ++i) {
            scoreLabels[i] = new JLabel();
            scoreValues[i] = new JLabel();
            scoreLabels[i].setForeground(this.scoreLabelColor);
            scoreLabels[i].setFont(this.scoreLabelFont);
            scoreValues[i].setForeground(this.scoreValueColor);
            scoreValues[i].setFont(this.scoreValueFont);
            String[][] namedScores = this.easyNamedScores;
            switch (difficulty) {
                case "medium":
                    namedScores = this.mediumNamedScores;
                    break;
                case "hard":
                    namedScores = this.hardNamedScores;
            }

            scoreLabels[i].setText(namedScores[0][i]);
            scoreValues[i].setText(namedScores[1][i]);
            scoreValues[i].setHorizontalAlignment(4);
            this.scoreDisplayPanel.add(scoreLabels[i]);
            this.scoreDisplayPanel.add(scoreValues[i]);
        }

        this.centerPanel.add(this.scoreDisplayPanel, "Center");
        this.highscoresPanel.add(this.centerPanel, "Center");
        UIButton hsBackButton = new UIButton("back");
        hsBackButton.setBackground(new Color(0, 0, 0, 0));
        hsBackButton.setFont(this.buttonFontSmall);
        hsBackButton.setDefaultForegroundColor(new Color(200, 92, 39));
        hsBackButton.setHoverForegroundColor(this.hoverButtonColor);
        hsBackButton.setPressedForegroundColor(this.pressedButtonColor);
        hsBackButton.addActionListener(this.menuInputHandler);
        this.highscoresPanel.add(hsBackButton, "South");
        this.scoreDisplayPanel.setVisible(false);
        this.scoreDisplayPanel.setVisible(true);
        this.centerPanel.setVisible(false);
        this.centerPanel.setVisible(true);
    }

    public void setUpInfoPane() {
        this.infoPanel = new JPanel(new BorderLayout());
        this.infoPanel.setPreferredSize(new Dimension(640, 640));
        this.infoPanel.setOpaque(false);
        JLabel infoTitle = new JLabel("how to play");
        infoTitle.setForeground(this.titleColor);
        infoTitle.setFont(this.titleFont);
        infoTitle.setHorizontalAlignment(0);
        this.infoPanel.add(infoTitle, "North");
        UIButton infoBackBtn = new UIButton("back");
        infoBackBtn.setFont(this.buttonFontSmall);
        infoBackBtn.setDefaultForegroundColor(new Color(200, 92, 39));
        infoBackBtn.setHoverForegroundColor(this.hoverButtonColor);
        infoBackBtn.setPressedForegroundColor(this.pressedButtonColor);
        infoBackBtn.addActionListener(this.menuInputHandler);
        JLabel infoTextLabel = new JLabel();
        infoTextLabel.setForeground(Color.lightGray);
        JTextArea jTextArea = new JTextArea("Movement: Use the WASD keys or the arrow\nkeys to control the snake's direction.\n\nPress the escape key to pause or resume\nthe game.");
        jTextArea.setOpaque(false);
        jTextArea.setFont(LoadSave.GetFont("fonts/linerama.ttf", 34.0F));
        jTextArea.setForeground(Color.lightGray);
        jTextArea.setEditable(false);
        this.infoPanel.add(jTextArea, "Center");
        JPanel infoBackBtnPanel = new JPanel();
        infoBackBtnPanel.setOpaque(false);
        infoBackBtnPanel.setPreferredSize(new Dimension(640, 80));
        infoBackBtnPanel.add(infoBackBtn);
        this.infoPanel.add(infoBackBtnPanel, "South");
    }

    public void setUpOptionsPanel() {
        this.optionsPanel = new JPanel(new BorderLayout());
        this.optionsPanel.setPreferredSize(new Dimension(640, 640));
        this.optionsPanel.setOpaque(false);
        JLabel optionsTitle = new JLabel("options");
        optionsTitle.setForeground(this.titleColor);
        optionsTitle.setFont(this.titleFont);
        optionsTitle.setHorizontalAlignment(0);
        this.optionsPanel.add(optionsTitle, "North");
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));
        buttonsPanel.setOpaque(false);
        UIButton bgSelectBtn = new UIButton("select background");
        bgSelectBtn.setFont(this.buttonFontMedium);
        bgSelectBtn.setDefaultForegroundColor(this.buttonColor);
        bgSelectBtn.setHoverForegroundColor(this.hoverButtonColor);
        bgSelectBtn.setPressedForegroundColor(this.pressedButtonColor);
        bgSelectBtn.addActionListener(this.menuInputHandler);
        buttonsPanel.add(bgSelectBtn);
        UIButton diffSelectBtn = new UIButton("select difficulty");
        diffSelectBtn.setFont(this.buttonFontMedium);
        diffSelectBtn.setDefaultForegroundColor(this.buttonColor);
        diffSelectBtn.setHoverForegroundColor(this.hoverButtonColor);
        diffSelectBtn.setPressedForegroundColor(this.pressedButtonColor);
        diffSelectBtn.addActionListener(this.menuInputHandler);
        buttonsPanel.add(diffSelectBtn);
        this.optionsPanel.add(buttonsPanel, "Center");
        UIButton opBackButton = new UIButton("back");
        opBackButton.setFont(this.buttonFontSmall);
        opBackButton.setDefaultForegroundColor(new Color(200, 92, 39));
        opBackButton.setHoverForegroundColor(this.hoverButtonColor);
        opBackButton.setPressedForegroundColor(this.pressedButtonColor);
        opBackButton.addActionListener(this.menuInputHandler);
        this.optionsPanel.add(opBackButton, "South");
    }

    public void setUpBgSelectPanel() {
        this.bgSelectPanel = new JPanel(new BorderLayout());
        this.bgSelectPanel.setPreferredSize(new Dimension(640, 640));
        this.bgSelectPanel.setOpaque(false);
        JLabel bgSelectTitle = new JLabel("select background");
        bgSelectTitle.setForeground(this.titleColor);
        bgSelectTitle.setFont(this.titleFont);
        bgSelectTitle.setHorizontalAlignment(0);
        this.bgSelectPanel.add(bgSelectTitle, "North");
        this.bgButtonsPanel = new JPanel(new GridLayout(4, 1));
        this.bgButtonsPanel.setOpaque(false);
        UIButton gridBg = new UIButton("grid");
        gridBg.setFont(this.buttonFontMedium);
        gridBg.setDefaultForegroundColor(this.buttonColor);
        gridBg.setHoverForegroundColor(this.hoverButtonColor);
        gridBg.setPressedForegroundColor(this.pressedButtonColor);
        gridBg.addActionListener(this.menuInputHandler);
        gridBg.setSelectable(true);
        this.bgButtonsPanel.add(gridBg);
        UIButton dotMatrixBg = new UIButton("dot matrix");
        dotMatrixBg.setFont(this.buttonFontMedium);
        dotMatrixBg.setDefaultForegroundColor(this.buttonColor);
        dotMatrixBg.setHoverForegroundColor(this.hoverButtonColor);
        dotMatrixBg.setPressedForegroundColor(this.pressedButtonColor);
        dotMatrixBg.addActionListener(this.menuInputHandler);
        dotMatrixBg.setSelectable(true);
        this.bgButtonsPanel.add(dotMatrixBg);
        UIButton stripesBg = new UIButton("stripes");
        stripesBg.setFont(this.buttonFontMedium);
        stripesBg.setDefaultForegroundColor(this.buttonColor);
        stripesBg.setHoverForegroundColor(this.hoverButtonColor);
        stripesBg.setPressedForegroundColor(this.pressedButtonColor);
        stripesBg.addActionListener(this.menuInputHandler);
        stripesBg.setSelectable(true);
        this.bgButtonsPanel.add(stripesBg);
        UIButton denseStripesBg = new UIButton("dense stripes");
        denseStripesBg.setFont(this.buttonFontMedium);
        denseStripesBg.setDefaultForegroundColor(this.buttonColor);
        denseStripesBg.setHoverForegroundColor(this.hoverButtonColor);
        denseStripesBg.setPressedForegroundColor(this.pressedButtonColor);
        denseStripesBg.addActionListener(this.menuInputHandler);
        denseStripesBg.setSelectable(true);
        this.bgButtonsPanel.add(denseStripesBg);
        this.bgSelectPanel.add(this.bgButtonsPanel, "Center");
        UIButton bgSelectBackButton = new UIButton("back");
        bgSelectBackButton.setFont(this.buttonFontSmall);
        bgSelectBackButton.setDefaultForegroundColor(new Color(200, 92, 39));
        bgSelectBackButton.setHoverForegroundColor(this.hoverButtonColor);
        bgSelectBackButton.setPressedForegroundColor(this.pressedButtonColor);
        bgSelectBackButton.addActionListener(this.menuInputHandler);
        this.bgSelectPanel.add(bgSelectBackButton, "South");
    }

    public void setUpDifficultySelectPanel() {
        this.difficultySelectPanel = new JPanel(new BorderLayout());
        this.difficultySelectPanel.setPreferredSize(new Dimension(640, 640));
        this.difficultySelectPanel.setOpaque(false);
        JLabel diffSelectTitle = new JLabel("select difficulty");
        diffSelectTitle.setForeground(this.titleColor);
        diffSelectTitle.setFont(this.titleFont);
        diffSelectTitle.setHorizontalAlignment(0);
        this.difficultySelectPanel.add(diffSelectTitle, "North");
        this.diffButtonsPanel = new JPanel(new GridLayout(3, 1));
        this.diffButtonsPanel.setOpaque(false);
        UIButton easyDiffBtn = new UIButton("easy");
        easyDiffBtn.setFont(this.buttonFontMedium);
        easyDiffBtn.setDefaultForegroundColor(this.buttonColor);
        easyDiffBtn.setHoverForegroundColor(this.hoverButtonColor);
        easyDiffBtn.setPressedForegroundColor(this.pressedButtonColor);
        easyDiffBtn.addActionListener(this.menuInputHandler);
        easyDiffBtn.setSelectable(true);
        this.diffButtonsPanel.add(easyDiffBtn);
        UIButton mediumDiffBtn = new UIButton("medium");
        mediumDiffBtn.setFont(this.buttonFontMedium);
        mediumDiffBtn.setDefaultForegroundColor(this.buttonColor);
        mediumDiffBtn.setHoverForegroundColor(this.hoverButtonColor);
        mediumDiffBtn.setPressedForegroundColor(this.pressedButtonColor);
        mediumDiffBtn.addActionListener(this.menuInputHandler);
        mediumDiffBtn.setSelectable(true);
        this.diffButtonsPanel.add(mediumDiffBtn);
        UIButton hardDiffBtn = new UIButton("hard");
        hardDiffBtn.setFont(this.buttonFontMedium);
        hardDiffBtn.setDefaultForegroundColor(this.buttonColor);
        hardDiffBtn.setHoverForegroundColor(this.hoverButtonColor);
        hardDiffBtn.setPressedForegroundColor(this.pressedButtonColor);
        hardDiffBtn.addActionListener(this.menuInputHandler);
        hardDiffBtn.setSelectable(true);
        this.diffButtonsPanel.add(hardDiffBtn);
        this.difficultySelectPanel.add(this.diffButtonsPanel, "Center");
        UIButton diffSelectBackBtn = new UIButton("back");
        diffSelectBackBtn.setFont(this.buttonFontSmall);
        diffSelectBackBtn.setDefaultForegroundColor(new Color(200, 92, 39));
        diffSelectBackBtn.setHoverForegroundColor(this.hoverButtonColor);
        diffSelectBackBtn.setPressedForegroundColor(this.pressedButtonColor);
        diffSelectBackBtn.addActionListener(this.menuInputHandler);
        this.difficultySelectPanel.add(diffSelectBackBtn, "South");
    }

    public void setEasyNamedScores(String[][] easyNamedScores) {
        this.easyNamedScores = easyNamedScores;
    }

    public void setMediumNamedScores(String[][] mediumNamedScores) {
        this.mediumNamedScores = mediumNamedScores;
    }

    public void setHardNamedScores(String[][] hardNamedScores) {
        this.hardNamedScores = hardNamedScores;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(LoadSave.GetImage(LoadSave.getSelectedBg()), 0, 0, (ImageObserver)null);
        this.repaint();
        if (this.isInMenu) {
            g.drawImage(LoadSave.GetImage("logo.png"), 80, 16, (ImageObserver)null);
        }

    }

    public void switchDiffPanels(int offset) {
        this.currentDifficulty = this.currentDifficulty + offset < 0 ? 2 : (this.currentDifficulty + offset) % 3;
        switch (this.currentDifficulty) {
            case 0:
                this.diffLabel.setText("easy");
                this.setUpScoreDisplayPanel("easy");
                break;
            case 1:
                this.diffLabel.setText("medium");
                this.setUpScoreDisplayPanel("medium");
                break;
            case 2:
                this.diffLabel.setText("hard");
                this.setUpScoreDisplayPanel("hard");
        }

    }

    public void changeToMenuPane() {
        this.isInMenu = true;
        this.remove(0);
        this.setUpMenuPanel();
        this.add(this.menuPanel);
        this.validate();
        this.repaint();
    }

    public void changeToOptionsPane() {
        this.isInMenu = false;
        this.remove(0);
        this.setUpOptionsPanel();
        this.add(this.optionsPanel);
        this.validate();
        this.repaint();
    }

    public void changeToBgSelectPane() {
        this.isInMenu = false;
        this.remove(0);
        this.setUpBgSelectPanel();
        this.setBgSelectable(LoadSave.getSelectedBgBtn());
        this.add(this.bgSelectPanel);
        this.validate();
        this.repaint();
    }

    public void changeToDifficultySelectPane() {
        this.isInMenu = false;
        this.remove(0);
        this.setUpDifficultySelectPanel();
        this.setDiffSelectable(LoadSave.getSelectedDiffBtn());
        this.add(this.difficultySelectPanel);
        this.validate();
        this.repaint();
    }

    public void changeToHighscorePane() {
        this.isInMenu = false;
        this.remove(0);
        this.setUpHighscorePanel("easy");
        this.add(this.highscoresPanel);
        this.validate();
        this.repaint();
    }

    public void changeToInfoPane() {
        this.isInMenu = false;
        this.remove(0);
        this.setUpInfoPane();
        this.add(this.infoPanel);
        this.validate();
        this.repaint();
    }

    public void setBgSelectable(String text) {
        Component[] components = this.bgButtonsPanel.getComponents();

        for (Component b : components) {
            UIButton btn = (UIButton) b;
            if (!btn.getText().equals(text)) {
                btn.setSelected(false);
            } else {
                btn.setSelected(true);
                LoadSave.setSelectedBgBtn(btn.getText());
                System.out.println("set bgbtn to " + btn.getText());
            }
        }

    }

    public void setDiffSelectable(String text) {
        Component[] components = this.diffButtonsPanel.getComponents();

        for (Component b : components) {
            UIButton btn = (UIButton) b;
            if (!btn.getText().equals(text)) {
                btn.setSelected(false);
            } else {
                btn.setSelected(true);
                LoadSave.setSelectedDiffBtn(btn.getText());
                System.out.println("set diffbtn to " + btn.getText());
            }
        }

    }
}
