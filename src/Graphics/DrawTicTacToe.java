package Graphics;

import TicTacToe.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawTicTacToe extends JFrame{
    public DrawTicTacToe() {
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        JPanel jButtonPanel = new JPanel();
        jButtonPanel.setLayout(new GridLayout());

        JButton jbStart = new JButton("Новая игра");
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameField gf = new GameField();
                add(gf, BorderLayout.CENTER);
                setVisible(true);
            }
        });

        JButton jbExit = new JButton("Выход");
        jbExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        String [] choice = new String[] { "Крестик", "Нолик"};
        JComboBox<String> jcbChoice = new JComboBox<>(choice);
        jcbChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String)jcbChoice.getSelectedItem();
                if (item.equals("Крестик")) {
                    Game.human_selection = 'X';
                    Game.ai_selection = 'O';
                } else {
                    Game.human_selection = 'O';
                    Game.ai_selection = 'X';
                }
            }
        });

        jButtonPanel.add(jbStart);
        jButtonPanel.add(jbExit);
        jButtonPanel.add(jcbChoice);
        add(jButtonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
