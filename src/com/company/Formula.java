package com.company;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.math.*;
import javax.imageio.*;
import java.io.*;
public class Formula extends JFrame {
    private int WIDTH = 500;
    private int HEIGHT = 420;
    private int formula;
    private int peremen;
    private Double result = 0.0;
    private Double mem1 = 0.0;
    private Double mem2 = 0.0;
    private Double mem3 = 0.0;
    private JButton button1 = new JButton("Вычислить");
    private JButton button2 = new JButton("Очистить поля");
    private JButton button3 = new JButton("M+");
    private JButton button4 = new JButton("MC");
    private JTextField textFieldX = new JTextField("0", 10);
    private JTextField textFieldY = new JTextField("0", 10);
    private JTextField textFieldZ = new JTextField("0", 10);
    private JTextField textFieldResult = new JTextField("0", 20);
    private JLabel labelX = new JLabel("X:");
    private JLabel labelY = new JLabel("Y:");
    private JLabel labelZ = new JLabel("Z:");
    private JLabel labelResult = new JLabel("Результат:");
    private JRadioButton radio1 = new JRadioButton("Формула 1");
    private JRadioButton radio2 = new JRadioButton("Формула 2");
    private JRadioButton radio3 = new JRadioButton("Переменная 1");
    private JRadioButton radio4 = new JRadioButton("Переменная 2");
    private JRadioButton radio5 = new JRadioButton("Переменная 3");

    public double calculate1(Double x, Double y, Double z) {
        if (z <= 0)
        {
            JOptionPane.showMessageDialog(getParent(),"z <= 0", "error", JOptionPane.ERROR_MESSAGE);
            return 0.0;
        }
        return ((Math.sin(y) + (y * y) + Math.exp(Math.cos(y))) * (Math.pow((Math.log(z) + Math.pow(Math.sin((Math.PI) * x), 2)), 1 / 4)));
    }

    public double calculate2(Double x, Double y, Double z) {
        if (z <= 0)
        {
            JOptionPane.showMessageDialog(getParent(),"z <= 0", "error", JOptionPane.ERROR_MESSAGE);
            return 0.0;
        }
        if (z == 1)
        {
            JOptionPane.showMessageDialog(getParent(),"ln(z) == 0", "error", JOptionPane.ERROR_MESSAGE);
            return 0.0;
        }
        return ((Math.pow((y + Math.pow(x, 3)), 1 / z)) / Math.log(z));
    }

    public Formula() {
        super("Вычисление формулы");
        this.setBounds(550, 150, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//реакция на крестик
        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        JPanel panel = new JPanel();
        panel.add(Box.createHorizontalGlue());
        radio1.setPreferredSize(new Dimension(100, 50));
        panel.add(radio1);
        radio1.setSelected(true);
        panel.add(Box.createRigidArea(new Dimension(50, 0)));
        panel.add(radio2);
        container.add(panel);
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(100, 50));
        panel2.add(labelX);
        panel2.add(textFieldX);
        panel2.add(Box.createRigidArea(new Dimension(50, 0)));
        panel2.add(labelY);
        panel2.add(textFieldY);
        panel2.add(Box.createRigidArea(new Dimension(50, 0)));
        panel2.add(labelZ);
        panel2.add(textFieldZ);
        container.add(panel2);
        ButtonGroup group2 = new ButtonGroup();
        group2.add(radio3);
        group2.add(radio4);
        group2.add(radio5);
        JPanel panel6 = new JPanel();
        panel6.add(radio3);
        panel6.add(Box.createRigidArea(new Dimension(50, 0)));
        panel6.add(radio4);
        panel6.add(Box.createRigidArea(new Dimension(50, 0)));
        panel6.add(radio5);
        container.add(panel6);
        JPanel panel3 = new JPanel();
        panel3.add(labelResult);
        panel3.add(textFieldResult);
        container.add(panel3);
        JPanel panel4 = new JPanel();
        panel4.add(button1);
        panel4.add(Box.createRigidArea(new Dimension(50, 0)));
        panel4.add(button2);
        container.add(panel4);
        JPanel panel5 = new JPanel();
        panel5.add(button3);
        panel5.add(Box.createRigidArea(new Dimension(20, 0)));
        panel5.add(button4);
        container.add(panel5);

        button1Listener hendler = new button1Listener();
        button1.addActionListener(hendler);
        button2.addActionListener(hendler);
        button3.addActionListener(hendler);
        button4.addActionListener(hendler);
        radio1.addActionListener(hendler);
        radio2.addActionListener(hendler);
        radio3.addActionListener(hendler);
        radio4.addActionListener(hendler);
        radio5.addActionListener(hendler);


    }
    //реакции при нажатии на наши кнопки
    public class button1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == radio1) {
                    formula = 1;
                } else if (e.getSource() == radio2) {
                    formula = 0;
                } else if (e.getSource() == button1) {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    if (formula == 1) {
                        result = calculate1(x, y, z);
                    } else if (formula == 0) {
                        result = calculate2(x, y, z);
                    }
                    textFieldResult.setText(result.toString());

                } else if (e.getSource() == button2) {//очистка полей
                    textFieldX.setText("0");
                    textFieldY.setText("0");
                    textFieldZ.setText("0");
                    textFieldResult.setText("0");
                } else if (e.getSource() == radio3) {
                    peremen = 1;
                } else if (e.getSource() == radio4) {
                    peremen = 2;
                } else if (e.getSource() == radio5) {
                    peremen = 3;
                } else if (e.getSource() == button3) {
                    if (peremen == 1) {//M+
                        mem1 = Double.parseDouble(textFieldX.getText());//активная переменная
                        result = Double.parseDouble(textFieldResult.getText());
                        result = result + mem1;
                        textFieldResult.setText(result.toString());
                    } else if (peremen == 2) {
                        mem2 = Double.parseDouble(textFieldY.getText());
                        result = Double.parseDouble(textFieldResult.getText());
                        result = result + mem2;
                        textFieldResult.setText(result.toString());
                    } else if (peremen == 3) {
                        mem3 = Double.parseDouble(textFieldZ.getText());
                        result = Double.parseDouble(textFieldResult.getText());
                        result = result + mem3;
                        textFieldResult.setText(result.toString());
                    }
                } else if (e.getSource() == button4) {//МС
                    if (peremen == 1) {
                        textFieldX.setText("0");
                    } else if (peremen == 2) {
                        textFieldY.setText("0");
                    } else if (peremen == 3) {
                        textFieldZ.setText("0");
                    }
                }

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(Formula.this,
                        "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}