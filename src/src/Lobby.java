/*package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Lobby extends JPanel{
    private final ActionListener startGameListener;

    public Lobby(ActionListener startGameListener) {
        this.startGameListener = startGameListener;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // Titre
        JLabel titleLabel = new JLabel("Linkd in", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        // Bouton Jouer
        JButton startButton = new JButton("Ne pas Jouer");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.addActionListener(startGameListener);

        // Ajout des composants
        add(titleLabel, BorderLayout.NORTH);
        add(startButton, BorderLayout.CENTER);
    }
}*/


 //amelioration de l ecran titre :/
package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Lobby extends JPanel {
    private final ActionListener startGameListener;

    public Lobby(ActionListener startGameListener) {
        this.startGameListener = startGameListener;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 23, 60));

        // Titre Cyberpunk (Dégradé de couleurs flashy)
        JLabel titleLabel = new JLabel("Linkd in", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, Color.MAGENTA, getWidth(), getHeight(), Color.CYAN, true);
                g2d.setPaint(gradient);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() / 2) + fm.getAscent() / 2;
                g2d.drawString(getText(), x, y);
            }
        };
        titleLabel.setFont(new Font("Arial", Font.BOLD, 42));
        titleLabel.setForeground(Color.WHITE);

        // Bouton transparent avec contour blanc
        JButton startButton = new JButton("Ne pas Jouer");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        startButton.setForeground(new Color(0,120,16));
        startButton.setFocusPainted(false);
        startButton.addActionListener(startGameListener);

        // Ajout des composants
        add(titleLabel, BorderLayout.NORTH);
        add(startButton, BorderLayout.CENTER);
    }
}




