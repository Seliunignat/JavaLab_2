package bsu.rfe.java.group10.lab2.SELIUN.varC18;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;

    private JTextField textFieldX; //Добавили текстовые поля для ввода значений переменных
    private JTextField textFieldY;

    private JTextField textFieldResult; //Текстовое поле для вывода результата вычислений формулы

    private ButtonGroup radioButtons = new ButtonGroup(); //Создали группу радио-кнопок, в которую и будем радо-кнопки добавлять

    private Box hboxFormulaType = Box.createHorizontalBox(); //Создаем контейнер Коробочного вида, горизонтального типа

    private int formulaId = 1;

    public MainFrame()
    {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);

        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,(kit.getScreenSize().height - HEIGHT)/2);
    }

    public static void main(String[] args) {
	// write your code here
       MainFrame frame = new MainFrame();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
    }
}
