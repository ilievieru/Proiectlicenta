package principal;

import util.AddRadio;
import util.ReadFromFile;
import xmlWork.AddVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Grafic {

    private JFrame frmMama;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Grafic window = new Grafic();
                    window.frmMama.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    private Grafic() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        final Resurses resurses = new Resurses();
        frmMama = new JFrame();
        frmMama.getContentPane().setBackground(UIManager.getColor("Button.darkShadow"));
        frmMama.getContentPane().setForeground(Color.BLACK);
        frmMama.setFont(new Font("Algerian", Font.ITALIC, 12));
        frmMama.setIconImage(Toolkit.getDefaultToolkit().getImage("Courses-48.png"));
        frmMama.setTitle("COROLA");
        frmMama.setForeground(Color.GRAY);
        frmMama.setBackground(Color.LIGHT_GRAY);
        frmMama.setBounds(100, 100, 450, 300);
        frmMama.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frmMama.getContentPane().setLayout(null);

        JPanel panel = new JPanel(new GridLayout(0, 1));

        AddRadio radio = new AddRadio();
        ArrayList list = (ArrayList) radio.showParsers("Parser.xml");

        ///aduagam parserele ca radio box
        for (int i = 0; i < list.size(); i++) {
            JRadioButton button = (JRadioButton) list.get(i);

            panel.add(button);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 45, 414, 167);
        frmMama.getContentPane().add(scrollPane);

        final JLabel lblNewLabel = new JLabel("No Selection");
        lblNewLabel.setBackground(new Color(99, 255, 55));
        lblNewLabel.setForeground(new Color(134, 175, 204));
        lblNewLabel.setBounds(96, 11, 328, 23);
        frmMama.getContentPane().add(lblNewLabel);

        JButton btnNewButton = new JButton("Browse");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                resurses.chooser(lblNewLabel);
            }
        });

        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnNewButton.setBounds(10, 11, 76, 23);
        frmMama.getContentPane().add(btnNewButton);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(10, 228, 315, 23);
        progressBar.setMinimum(0);
        progressBar.setMaximum(1000);
        frmMama.getContentPane().add(progressBar);

        JButton btnNewButton_1 = new JButton("Start");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //apelam serviciul
                String path = resurses.getParentPath() + "\\" + resurses.getNameOfFile();
                ReadFromFile read = new ReadFromFile();
                read.setPath(path);
                // Apelul
                System.out.println(read.read());

                // Adaugam tag-ul vesiune la fiserul xml asociat
                AddVersion vers = new AddVersion();
                vers.set(resurses.getParentPath() + "\\" + resurses.getNameOfFile() + ".xml");
            }
        });
        btnNewButton_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
        btnNewButton_1.setBackground(new Color(204, 204, 204));
        btnNewButton_1.setBounds(335, 228, 89, 23);
        frmMama.getContentPane().add(btnNewButton_1);
    }
}
