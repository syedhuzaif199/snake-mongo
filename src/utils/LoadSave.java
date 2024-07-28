package utils;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class LoadSave {
    public static final String BACKGROUND_GRID = "background_grid.png";
    public static final String BACKGROUND_DOT_MATRIX = "background_dotted.png";
    public static final String BACKGROUND_STRIPES = "background_striped.png";
    public static final String BACKGROUND_DENSE_STRIPES = "background_dense_striped.png";
    public static final String LOGO = "logo.png";
    public static final String LOGO_OPAQUE = "logo_opaque.png";
    public static final String SQUARE_FONT = "fonts/square.ttf";
    public static final String LINERAMA_FONT = "fonts/linerama.ttf";
    public static final String EAT_SFX = "/sounds/eat.wav";
    public static final String GAME_OVER_SFX = "/sounds/gameover.wav";
    private static String selectedBg = "background_grid.png";
    private static String selectedDiffBtn = "easy";
    private static String selectedBgBtn = "grid";
    public static BufferedImage GetImage(String filename) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + filename);
        try {
            img = ImageIO.read(is);

            }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return img;
    }

    public static Font GetFont(String fontFileName, float size) {
        Font font = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream(fontFileName);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return font;
    }

    public static Font GetFontRaw(String fontFileName) {
        Font font = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream(fontFileName);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return font;
    }

    public static URL GetSoundURL(String soundFileName) {
        return LoadSave.class.getResource(soundFileName);
    }

    public static void selectBg(String bg) {
        selectedBg = bg;
    }

    public static String getSelectedBg() {
        return selectedBg;
    }

    public static void setSelectedBgBtn(String selectedBgBtn) {
        LoadSave.selectedBgBtn = selectedBgBtn;
    }

    public static String getSelectedBgBtn() {
        return selectedBgBtn;
    }

    public static void setSelectedDiffBtn(String selectedDiffBtn) {
        LoadSave.selectedDiffBtn = selectedDiffBtn;
    }

    public static String getSelectedDiffBtn() {
        return selectedDiffBtn;
    }
}
