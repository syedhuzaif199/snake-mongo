package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UIButton extends JButton {

    private Color hoverForegroundColor;
    private Color pressedForegroundColor;
    private Color defaultForegroundColor;
    private Color selectedColor;
    private boolean pressed;
    private boolean selectable;
    private boolean selected;

    public UIButton() {
        this(null);

    }

    public UIButton(String text) {
        super(text);
        setBorderPainted(false);
        super.setContentAreaFilled(false);
        setOpaque(false);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                setForeground(pressedForegroundColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                setForeground(selectedColor);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(!pressed) {
                    setForeground(hoverForegroundColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!pressed) {
                    setForeground(selectedColor);
                }
            }
        });

    }


    @Override
    public void setContentAreaFilled(boolean b) {
    }

    public void setDefaultForegroundColor(Color color) {
        defaultForegroundColor = color;
        selectedColor = color;
        setForeground(color);
    }


    public Color getHoverForegroundColor() {
        return hoverForegroundColor;
    }

    public void setHoverForegroundColor(Color hoverForegroundColor) {
        this.hoverForegroundColor = hoverForegroundColor;
    }

    public Color getPressedForegroundColor() {
        return pressedForegroundColor;
    }

    public void setPressedForegroundColor(Color pressedForegroundColor) {
        this.pressedForegroundColor = pressedForegroundColor;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public void setSelected(boolean selected) {
        if (selected) {
            selectedColor = new Color(104, 151, 187);
            this.setForeground(selectedColor);
        }
        else {
            this.setForeground(defaultForegroundColor);
            selectedColor = defaultForegroundColor;
        }
        this.selected = selected;
    }

    public Color getDefaultForegroundColor() {
        return this.defaultForegroundColor;
    }
}
