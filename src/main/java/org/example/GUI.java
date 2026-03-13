package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUI extends JFrame {
    private JTextArea textArea = new JTextArea();
    private JLabel statusLabel = new JLabel("Готов к работе");
    private MissionReader reader = new MissionReader();

    public GUI() {
        setTitle("Анализатор миссий");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton openBtn = new JButton("Открыть");
        openBtn.addActionListener(e -> {
            FileDialog dialog = new FileDialog(this, "Открыть", FileDialog.LOAD);
            dialog.setVisible(true);
            if (dialog.getFile() != null) {
                loadMission(new File(dialog.getDirectory() + dialog.getFile()));
            }
        });

        JButton clearBtn = new JButton("Очистить");
        clearBtn.addActionListener(e -> {
            textArea.setText("");
            statusLabel.setText("Готов к работе");
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(openBtn);
        bottomPanel.add(clearBtn);
        bottomPanel.add(statusLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void loadMission(File file) {
        try {
            Mission m = reader.read(file);
            String comment;
            if (m.getComment().isEmpty()) {
                comment = "";
            } else {
                comment = m.getComment();
            }

            String text = "ID миссии: " + m.getMissionId() + "\n" +
                    "Дата: " + m.getDate() + "\n" +
                    "Локация: " + m.getLocation() + "\n" +
                    "Результат: " + m.getOutcome() + "\n" +
                    "Ущерб: " + m.getDamageCost() + "\n" +
                    "Цель: " + m.getCurse() + "\n" +
                    "Комментарий: " + comment + "\n\n" +
                    "Участники:\n";

            if (m.getSorcerers() != null) {
                for (int i = 0; i < m.getSorcerers().size(); i++) {
                    text += (i + 1) + ") " + m.getSorcerers().get(i) + "\n";
                }
            }

            text += "\nТехники:\n";
            if (m.getTechniques() != null) {
                for (int i = 0; i < m.getTechniques().size(); i++) {
                    Technique t = m.getTechniques().get(i);
                    text += (i + 1) + ") " + t.getName() + " [Тип: " + t.getType() +
                            ", Владелец: " + t.getOwner() + ", Урон: " + t.getDamage() + "]\n";
                }
            }

            textArea.setText(text);
            statusLabel.setText("Загружен: " + file.getName());
        } catch (Exception e) {
            textArea.setText("Ошибка: " + e.getMessage());
        }
    }


}

