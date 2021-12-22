import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Scanner;

public class inputPanel {
    public static Scanner scanner = new Scanner(System.in);

    public static JPanel mainPanel = new JPanel();
    public static JPanel dalej = new JPanel();
    public static JPanel zapis_odczyt = new JPanel();
    public static JPanel zmien_chunk = new JPanel();

    public static void initialize() throws IOException {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        dalej_init();
        save_init();
        chunk_change();
    }

    private static void dalej_init() {
        dalej.setLayout(new GridLayout(4, 2));
        JLabel dalejRowLabel = new JLabel(lang.messages.get("row:"));
        dalej.add(dalejRowLabel);
        JTextField dalejRowField = new JTextField(2);
        dalejRowField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = dalejRowField.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    dalejRowField.setEditable(true);
                } else if (ke.getKeyChar() == '/') {
                    dalejRowField.setEditable(true);
                } else if (ke.getKeyChar() == '\b') {
                    dalejRowField.setEditable(true);
                } else {
                    dalejRowField.setEditable(false);
                }
            }
        });
        dalej.add(dalejRowField);
        JLabel dalejColLabel = new JLabel(lang.messages.get("col:"));
        dalej.add(dalejColLabel);
        JTextField dalejColField = new JTextField(2);
        dalejColField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = dalejColField.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    dalejColField.setEditable(true);
                } else if (ke.getKeyChar() >= '/') {
                    dalejColField.setEditable(true);
                } else if (ke.getKeyChar() == '\b') {
                    dalejColField.setEditable(true);
                } else {
                    dalejColField.setEditable(false);
                }
            }
        });
        dalej.add(dalejColField);
        JLabel dalejCodeLabel = new JLabel(lang.messages.get("code:"));
        dalej.add(dalejCodeLabel);
        JTextField dalejCodeField = new JTextField(2);
        dalejCodeField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = dalejCodeField.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    dalejCodeField.setEditable(true);
                } else if (ke.getKeyChar() == '\b') {
                    dalejCodeField.setEditable(true);
                } else {
                    dalejCodeField.setEditable(false);
                }
            }
        });
        dalej.add(dalejCodeField);
        JButton dalejBtn = new JButton(lang.messages.get("Place"));
        dalejBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int kod = 0;
                String[] wiersze = String.valueOf(dalejRowField.getText()).split("/");
                String[] kolumny = String.valueOf(dalejColField.getText()).split("/");
                kod = Integer.parseInt(String.valueOf(dalejCodeField.getText()));

                for (String y : kolumny) {
                    for (String x : wiersze) {
                        map.gameMAP[Integer.valueOf(x)][Integer.valueOf(y)] = kod;
                    }
                }

                miasta.reloadmap();
            }
        });
        dalej.add(dalejBtn);
        mainPanel.add(dalej);
    }

    private static void save_init() {
        zapis_odczyt.setLayout(new GridLayout(2, 2));
        JLabel zapis_Label = new JLabel(lang.messages.get("map_name:"));
        zapis_odczyt.add(zapis_Label);
        JTextField Field = new JTextField();
        zapis_odczyt.add(Field);
        JButton zapisBtn = new JButton(lang.messages.get("zapisz"));
        zapisBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.path = Field.getText();
                try {
                    map.save();
                } catch (IOException ioException) {
                    System.out.println(lang.messages.get("zapis_error"));
                }
            }
        });
        zapis_odczyt.add(zapisBtn);
        JButton wczytajBtn = new JButton(lang.messages.get("load"));
        wczytajBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.path = Field.getText();
                map.load();
                miasta.reloadmap();
            }
        });
        zapis_odczyt.add(wczytajBtn);
        mainPanel.add(zapis_odczyt);
    }

    private static void chunk_change() throws IOException {
        zmien_chunk.setLayout(new GridLayout(4, 3));
        JButton upBtn = new JButton();
        upBtn.setIcon(new ImageIcon(miasta.GetTexture(2000)));
        upBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    map.save();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                map.chunk_y++;
                map.load();
                miasta.reloadmap();
            }
        });
        JButton rightBtn = new JButton();
        rightBtn.setIcon(new ImageIcon(miasta.GetTexture(2001)));
        rightBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    map.save();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                map.chunk_x++;
                map.load();
                miasta.reloadmap();
            }
        });
        JButton downBtn = new JButton();
        downBtn.setIcon(new ImageIcon(miasta.GetTexture(2002)));
        downBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    map.save();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                map.chunk_y--;
                map.load();
                miasta.reloadmap();
            }

        });
        JButton leftBtn = new JButton();
        leftBtn.setIcon(new ImageIcon(miasta.GetTexture(2003)));
        leftBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    map.save();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                map.chunk_x--;
                map.load();
                miasta.reloadmap();
            }

        });
        JLabel nul = new JLabel(" ");
        JLabel text = new JLabel(lang.messages.get("opcja5"));
        zmien_chunk.add(nul);
        zmien_chunk.add(text);
        zmien_chunk.add(nul);
        zmien_chunk.add(upBtn);
        zmien_chunk.add(nul);
        zmien_chunk.add(leftBtn);
        zmien_chunk.add(nul);
        zmien_chunk.add(rightBtn);
        zmien_chunk.add(nul);
        zmien_chunk.add(downBtn);

        mainPanel.add(zmien_chunk);
    }
}
