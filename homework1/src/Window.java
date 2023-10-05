import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class Window extends JFrame {
    public final int WINDOW_HEIGHT = 500;
    public final int WINDOW_WIDTH = 500;
    public final int LOCATE_X = 300;
    public final int LOCATE_Y = 200;

    Panel panelInput = new Panel(new GridLayout(2, 1));
    Panel panelExit = new Panel(new GridLayout(1, 3));

    JLabel text = new JLabel("OLOLOOOG");
    JButton submit = new JButton("Enter");

    JTextField writeLog = new JTextField(10);
    JButton btnExit = new JButton("Exit");
    final Path FILE = Paths.get("./logs.txt");


    

    Window() {
        btnExit.addActionListener(new ActionExit());

        submit.addActionListener((e) -> {
            submitAction();
        });

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        setLocation(LOCATE_X, LOCATE_Y);
        setTitle("Регистрация");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelExit.add(btnExit);
        add(panelExit, BorderLayout.SOUTH);
        panelInput.add(writeLog);
        panelInput.add(submit);
        add(panelInput);

    }

    class ActionExit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private void submitAction() {
        String log = writeLog.getText();
        System.out.println(log);
        log = log + "\n";
        writeLogToFile(log);
    }

    private void writeLogToFile(String log){
        try {
            Files.write(FILE, log.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readLogs(){
        Path file = Paths.get("./logs.txt");
         try {
            List<String> logs = Arrays.asList();
            logs = Files.readAllLines(file);
            for (String log : logs){
                System.out.println(log);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    
}
