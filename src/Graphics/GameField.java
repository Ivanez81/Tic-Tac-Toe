package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import TicTacToe.Game;

public class GameField extends JPanel {
    private int width, height;
    private int cellWidth, cellHeight;

    public GameField() {
        Game.map = new char[Game.size][Game.size];
        if (Game.human_selection == 'O') {
            Game.aiTurn(Game.ai_selection);
            repaint();
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(e.getX() + " " + e.getY());
                int clY = e.getX()/cellWidth;
                int clX = e.getY()/cellHeight;
                System.out.println(clX + " " + clY);

                if (Game.isCellValid(clX, clY, Game.map)) {
                    Game.map[clX][clY] = Game.human_selection;
                    repaint();
                    if(Game.checkWin(Game.map, Game.human_selection)) {
                        JOptionPane.showMessageDialog(null, "Победа!");
                        return;
                    }
                    if(Game.isMapFull(Game.map)) {
                        JOptionPane.showMessageDialog(null, "Ничья!");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Клетка уже занята, выбери другую.");
                    return;
                }

                Game.aiTurn(Game.ai_selection);
                repaint();
                Game.printMap();
                if(Game.checkWin(Game.map, Game.ai_selection)) {
                    JOptionPane.showMessageDialog(null, "Победа ИИ :-(");
                    return;
                }
                if(Game.isMapFull(Game.map)) {
                    JOptionPane.showMessageDialog(null, "Ничья!");
                    return;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        width = getWidth();
        height = getHeight();
        cellWidth = width / Game.size;
        cellHeight = height / Game.size;
        for (int i = 0; i < Game.size + 1; i++) {
            g2.drawLine(0, i*cellHeight, width, i*cellHeight); // горизонтали
            g2.drawLine(i*cellWidth, 0, i*cellWidth, height); // вертикали
        }
        for (int i = 0; i < Game.size; i++) {
            for (int j = 0; j < Game.size; j++) {
                if(Game.map[i][j] == 'X') {
                    g2.setColor(Color.BLUE);
                    g2.setStroke(new BasicStroke(10));
                    g2.drawLine(j*cellWidth+20, i*cellHeight+20, j*cellWidth+cellWidth-20, i*cellHeight+cellHeight-20);
                    g2.drawLine(j*cellWidth+cellWidth-20, i*cellHeight+20, j*cellWidth+20, i*cellHeight+cellHeight-20);
                }
                if(Game.map[i][j] == 'O') {
                    g2.setColor(Color.RED);
                    g2.setStroke(new BasicStroke(10));
                    g2.drawOval(j * cellWidth + 10, i * cellHeight + 10, cellWidth - 20, cellHeight - 20);
                }
            }
        }
    }
}
