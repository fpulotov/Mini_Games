import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main implements ActionListener {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JPanel guessNumberPanel;
    private JLabel titleLabel;
    private JLabel rangeLabel;
    private JLabel attemptsLabel;
    private JLabel resultLabel;
    private JTextField inputField;
    private JButton submitButton;
    private JButton restartGuessNumberButton;
    private JPanel ticTacToePanel;
    private JButton[][] buttons;
    private boolean gameOver;
    private JButton restartTicTacToeButton;

    private int minRange;
    private int maxRange;
    private int maxAttempts;
    private int attempts;
    private int secretNumber;

    public Main() {
        frame = new JFrame("Mini Games");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeGUI();
    }

    public void start() {
        frame.setVisible(true);
    }

    private void initializeGUI() {
        tabbedPane = new JTabbedPane();

        guessNumberPanel = new JPanel();
        initializeGuessNumberGame(guessNumberPanel);
        tabbedPane.addTab("Угадай число", guessNumberPanel);

        ticTacToePanel = new JPanel();
        initializeTicTacToeGame(ticTacToePanel);
        tabbedPane.addTab("Крестики-нолики", ticTacToePanel);

        frame.add(tabbedPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void initializeGuessNumberGame(JPanel panel) {
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.decode("#f0f0f0"));

        JPanel titlePanel = new JPanel();
        titleLabel = new JLabel("Угадай число");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        rangeLabel = new JLabel();
        rangeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        attemptsLabel = new JLabel();
        attemptsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        infoPanel.add(rangeLabel);
        infoPanel.add(attemptsLabel);
        infoPanel.add(resultLabel);

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel inputLabel = new JLabel("Ваше число:");
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        inputField = new JTextField(10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 18));
        submitButton = new JButton("Проверить");
        submitButton.addActionListener(this);
        submitButton.setFont(new Font("Arial", Font.BOLD, 18));
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(submitButton);

        JPanel restartPanel = new JPanel(new FlowLayout());
        restartGuessNumberButton = new JButton("Рестарт");
        restartGuessNumberButton.addActionListener(this);
        restartGuessNumberButton.setFont(new Font("Arial", Font.BOLD, 18));
        restartPanel.add(restartGuessNumberButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(Color.decode("#f0f0f0"));
        contentPanel.add(titlePanel, BorderLayout.NORTH);
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.add(inputPanel, BorderLayout.SOUTH);
        contentPanel.add(restartPanel, BorderLayout.EAST);

        panel.add(contentPanel);
    }

    private void initializeGuessNumberSettings(int level) {
        switch (level) {
            case 1:
                minRange = 1;
                maxRange = 100;
                break;
            case 2:
                minRange = 1;
                maxRange = 100;
                break;
            case 3:
                minRange = 1;
                maxRange = 200;
                break;
            default:
                minRange = 1;
                maxRange = 100;
        }

        maxAttempts = 5;
        attempts = 0;
        secretNumber = generateSecretNumber(minRange, maxRange);

        rangeLabel.setText("Загаданное число от " + minRange + " до " + maxRange);
        attemptsLabel.setText("Попытка: " + attempts + " из " + maxAttempts);
        resultLabel.setText("");
        inputField.setText("");
        inputField.requestFocus();
    }

    private int generateSecretNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private void checkGuess() {
        String inputText = inputField.getText();
        if (!inputText.isEmpty()) {
            int guess = Integer.parseInt(inputText);

            attempts++;
            attemptsLabel.setText("Попытка: " + attempts + " из " + maxAttempts);

            if (guess == secretNumber) {
                resultLabel.setText("Вы угадали число!");
                resultLabel.setForeground(Color.GREEN);
                submitButton.setEnabled(false);
            } else if (attempts >= maxAttempts) {
                resultLabel.setText("Вы не угадали число. Загаданное число: " + secretNumber);
                resultLabel.setForeground(Color.YELLOW);
                submitButton.setEnabled(false);
            } else if (guess < secretNumber) {
                resultLabel.setText("Загаданное число больше");
                resultLabel.setForeground(Color.BLUE);
            } else {
                resultLabel.setText("Загаданное число меньше");
                resultLabel.setForeground(Color.RED);
            }
        }
    }

    private void restartGuessNumberGame() {
        submitButton.setEnabled(true);
        resultLabel.setText("");
        resultLabel.setForeground(Color.BLACK);
        initializeGuessNumberSettings(1);
    }

    private void initializeTicTacToeGame(JPanel panel) {
        panel.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titleLabel = new JLabel("Крестики-нолики");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        JPanel gamePanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 48));
                buttons[row][col].addActionListener(this);
                gamePanel.add(buttons[row][col]);
            }
        }

        JPanel restartPanel = new JPanel(new FlowLayout());
        restartTicTacToeButton = new JButton("Рестарт");
        restartTicTacToeButton.addActionListener(this);
        restartTicTacToeButton.setFont(new Font("Arial", Font.BOLD, 18));
        restartPanel.add(restartTicTacToeButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(titlePanel, BorderLayout.NORTH);
        contentPanel.add(gamePanel, BorderLayout.CENTER);
        contentPanel.add(restartPanel, BorderLayout.EAST);

        panel.add(contentPanel);
    }

    private void restartTicTacToeGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        gameOver = false;
    }

    private void makeMove(int row, int col) {
        if (buttons[row][col].getText().isEmpty()) {
            buttons[row][col].setText("X");
            if (checkWin("X")) {
                JOptionPane.showMessageDialog(frame, "Вы победили!");
                gameOver = true;
            } else if (checkDraw()) {
                JOptionPane.showMessageDialog(frame, "Ничья!");
                gameOver = true;
            } else {
                makeComputerMove();
            }
        }
    }

    private void makeComputerMove() {
        Random random = new Random();

        while (true) {
            int row = random.nextInt(3);
            int col = random.nextInt(3);

            if (buttons[row][col].getText().isEmpty()) {
                buttons[row][col].setText("O");
                if (checkWin("O")) {
                    JOptionPane.showMessageDialog(frame, "Вы проиграли!");
                    gameOver = true;
                } else if (checkDraw()) {
                    JOptionPane.showMessageDialog(frame, "Ничья!");
                    gameOver = true;
                }
                break;
            }
        }
    }

    private boolean checkWin(String player) {
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(player) &&
                    buttons[row][1].getText().equals(player) &&
                    buttons[row][2].getText().equals(player)) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(player) &&
                    buttons[1][col].getText().equals(player) &&
                    buttons[2][col].getText().equals(player)) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][2].getText().equals(player)) {
            return true;
        }
        if (buttons[0][2].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][0].getText().equals(player)) {
            return true;
        }
        return false;
    }

    private boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            checkGuess();
        } else if (e.getSource() == restartGuessNumberButton) {
            restartGuessNumberGame();
        } else if (!gameOver) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (e.getSource() == buttons[row][col]) {
                        makeMove(row, col);
                        return;
                    }
                }
            }
        }
        if (e.getSource() == restartTicTacToeButton) {
            restartTicTacToeGame();
        }
    }
    public static void main(String[] args) {
        Main game = new Main();
        game.start();
    }
}