package bsu.rfe.java.group10.lab2.SELIUN.varC18;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame{

    private static final int WIDTH = 500;
    private static final int HEIGHT = 420;

    private Double[] mem = {0.0, 0.0, 0.0};
    /*
    private Double mem1 = 0.0;
    private Double mem2 = 0.0;
    private Double mem3 = 0.0;
    */

    private JTextField textFieldX; //Добавили текстовые поля для ввода значений переменных
    private JTextField textFieldY;
    private JTextField textFieldZ;

    private JTextField textFieldResult; //Текстовое поле для вывода результата вычислений формулы

    private JLabel imageLabel = new JLabel();

    private ButtonGroup radioButtons = new ButtonGroup(); //Создали группу радио-кнопок, в которую и будем радо-кнопки добавлять
    private ButtonGroup memoryButtons = new ButtonGroup(); //Создали группа радио-кнопок для переключения действующей ячейки памяти М, в которую и будем радио-кнопки добавлять

    private Box hboxFormulaType = Box.createHorizontalBox(); //Создаем контейнер Коробочного вида, горизонтального типа
    private Box hboxMemoryType = Box.createHorizontalBox(); //Создаем контейнер Коробочного вида, горизонтального типа
    //private Box imageBox = Box.createHorizontalBox(); //Создаем горизонтальную коробку для хранения картинки
    private String[] functionsImagesPath = { "C:\\Documents\\Programming\\Java\\JavaLab_2\\src\\bsu\\rfe\\java\\group10\\lab2\\SELIUN\\varC18\\images\\1.png", "C:\\Documents\\Programming\\Java\\JavaLab_2\\src\\bsu\\rfe\\java\\group10\\lab2\\SELIUN\\varC18\\images\\2.png"};
    BufferedImage imageFunction;

    {
        try {
            imageFunction = ImageIO.read(new File(functionsImagesPath[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private int formulaId = 1;
    private static int memoryId = 1; //Делаем чтатической, чтобы понимать, какая переменная сейчас активна

    public Double calculate1(Double x, Double y, Double z)
    {
        return ((Math.sin(Math.PI*y*y) + Math.log(y*y))/(Math.sin(Math.PI*z*z) + Math.sin(x) + Math.log(z*z) + x*x + Math.exp(Math.cos(z*x))));
    }

    public Double calculate2(Double x, Double y, Double z)
    {
        return ((Math.pow(Math.cos(Math.exp(y)) + Math.exp(y*y) + Math.sqrt(1/x), 1/4))/(Math.pow( Math.cos(Math.PI*z*z*z) + Math.log((1+z)*(1+z)), Math.sin(y))));
    }

    private void addRadioButton(String buttonName, final int formulaId)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.formulaId = formulaId;
                try {
                    MainFrame.this.imageFunction = ImageIO.read(new File(functionsImagesPath[formulaId - 1]));
                    imageLabel.setIcon(new ImageIcon(imageFunction));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                //imagePane.updateUI(); //???
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    private void addMemoryRadioButton(String buttonName, final int memoryId)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.memoryId = memoryId;
            }
        });
        memoryButtons.add(button); //Добавляем в группу кнопок
        hboxMemoryType.add(button); //Добавляем в коробочный контейнер
    }

    public MainFrame()
    {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));



        imageLabel.setIcon(new ImageIcon(imageFunction));
        Box imageBox = Box.createHorizontalBox();
        imageBox.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        imageBox.add(Box.createHorizontalGlue());
        imageBox.add(imageLabel);
        imageBox.add(Box.createHorizontalGlue());

// Создать область с полями ввода для X и Y
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));

        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);

        hboxVariables.add(Box.createHorizontalStrut(40));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);

        hboxVariables.add(Box.createHorizontalStrut(40));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());

        //Создаем контейнер с радио-кнопками, позволяющие выбирать ячейки памяти
        hboxMemoryType.add(Box.createHorizontalGlue());
        addMemoryRadioButton("Переменная 1", 1);
        addMemoryRadioButton("Переменная 2", 2);
        addMemoryRadioButton("Переменная 3", 3);
        memoryButtons.setSelected(memoryButtons.getElements().nextElement().getModel(), true);
        hboxMemoryType.add(Box.createHorizontalGlue());
        hboxMemoryType.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));

        //Создаем кнопки, для работы с ячейками памяти
        JButton mc = new JButton("MC");
        mc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mem[memoryId-1] = 0.0;
            }
        });
        JButton mPlus = new JButton("M+");
        mPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mem[memoryId-1] += Double.parseDouble(textFieldResult.getText());
                textFieldResult.setText(mem[memoryId-1].toString());
            }
        });
        //Создаем контейнер, для работы с ячейками памяти
        Box mButtons = Box.createHorizontalBox();
        mButtons.add(Box.createHorizontalGlue());
        mButtons.add(mc);
        mButtons.add(Box.createHorizontalStrut(10));
        mButtons.add(mPlus);
        mButtons.add(Box.createHorizontalGlue());
        mButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK));

// Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
//labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 15);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        textFieldResult.setEditable(false);
        //Создаем контейнер в котором будет отображаться результат
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

// Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        //Контейнер для кнопок
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

    // Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox(); //Создаем отдельный коробочный компановщик, и в него закидываем наши состовляющие, созданные до этого
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(imageBox);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(hboxMemoryType);
        contentBox.add(mButtons);
        contentBox.add(Box.createVerticalGlue());

        getContentPane().add(contentBox, BorderLayout.CENTER); //Помещаем нашу коробку(со всеми коробками(со всеми состовляющими)) в наш Frame
    }

    public static void main(String[] args) {
	// write your code here
       MainFrame frame = new MainFrame();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
    }
}
