import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.QuadCurve2D;
import java.util.*;
import java.util.List;

public class Electronics101 extends JFrame {


    private static final Color HEADER_GREEN = new Color(34, 84, 61);     // Deep forest/PCB green
    private static final Color BORDER_GREEN = new Color(24, 62, 45);     // Darker trim
    private static final Color ACCENT_GREEN = new Color(47, 133, 90);    // Medium green for buttons/highlights
    private static final Color TINT_GREEN   = new Color(235, 244, 239);  // Soft sage tint for status bars
    private static final Color BG_LIGHT     = new Color(245, 246, 245);  // Neutral off-white background
    private static final Color BOARD_BG     = new Color(247, 246, 238);  // Classic cream breadboard plastic
    private static final Color RAIL_RED     = new Color(197, 48, 48);
    private static final Color RAIL_BLUE    = new Color(43, 108, 176);

    public Electronics101(){
        setTitle("Electronics 101");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 780);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_LIGHT);
        add(createHeader(), BorderLayout.NORTH); 
        add(createTabs(), BorderLayout.CENTER);//same
        add(createFooter(), BorderLayout.SOUTH)//will add next commit so far just planning what methods to make
    }

    private JPanel createHeader(){
       JPanel header = new JPanel();
       header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
       header.setBackground(HEADER_GREEN);
       header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 3, 0, BORDER_GREEN),
            new EmptyBorder(16, 22, 16, 22)
       ));

       JLabel title = new JLabel("Electronics 101");
       title.setFont(new Font("SansSerif", Font.BOLD, 26));
       title.setForeground(Color.WHITE);

       JLabel desc = new JLabel("Notes on basic parts, how breadboard rails connect, and a simple circuit simulator");
       desc.setFont(new Font("SansSerif", Font.BOLD, 26));
       desc.setForeground(new Color(220, 235, 226));

       header.add(title);
       header.add(Box.createVericalStrut(4));
       header.add(desc);
       return header;
       }

       private JTabbedPane createTabs(){
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabs.setBorder(new EmptyBorder(8,12,8,12));

        tabs.addTab("1. Parts", createLearnPanel());
        tabs.addTab("2. BreadBoard Layout", createBreadboardBasicsPanel());
        tabs.addTab("3. Circuit Simulator", createSimulatorPanel());
        tabs.addTab("4. Quiz", createQuizPanel());


        return tabs;
       }

       private JComponent createLearnPanel(){
            JPanel container = new JPanel(new BorderLayout(0, 14));
            container.setBackground(BG_LIGHT);
            container.setBorder(new EmptyBorder(14, 14, 14, 14));

            JPanel topText = new JPanel(new GridLayout(2, 1));
            topText.setBackground(BG_LIGHT);
            JLabel h2 = new JLabel("Basic Electronics Components");
            h2.setFont(new Font("SansSerif", Font.BOLD, 20));
            JLabel intro = new JLabel("What each part does in a simple DC circuit, and how to identify them by their physical appearance");
            intro.setForeground(new Color(90,90,90));
            topText.add(h2);
            topText.add(intro);

            JPanel grid = new JPanel(new GridLayout(2,3,12,12));
            grid.setBackground(BG_LIGHT);

            String[][] parts = { // I actually hate 2d arrays so much actual bane of my existence
            {"Breadboard", "Lets you prototype circuit without soldering, Metal clips underneath connect rows of holes together."},
            {"Jumper Wires", "Connect differetn points on the board, Red is usually power/positive, and Black for ground/negative."},
            {"Power Supply", "Provides DC voltage (like 5V) to push current  through the circuit loop."},
            {"Resistor", "Limits the amount of current that can flow through a circuit, measured in Ohms (Ω)."},
            {"LED", "Light Emitting Diode, emits light when current flows through it in the correct direction."},
            {"Switch", "Controls the flow of current in a circuit, can be used to turn devices on or off."}
            };

            for(String[] part : parts){
                JPanel card = new JPanel(new BorderLayout(0,6));
                card.setBackground(BG_WHITE);
                card.setBorder(BorderFactory.createCompuoundBorder(
                    new LineBorder(new Color(205,212,202), 1),
                    new EmptyBorder(12,14,12,14)
                ));

                JLabel name = new JLabel(part[0]);
                name.setFont(new Font("SansSerif", Font.BOLD, 16));
                name.setForeground(HEADER_GREEN);

                JTextArea body = new JTextArea(part[1]);
                body.setWrapStyleWord(true);
                body.setLineWrap(true);
                body.setEditable(false);
                body.setOpaque(false);
                body.setFont(new Font("SansSerif", Font.PLAIN, 13));
                body.setForeground(new Color(55,55,55));

                card.add(name, BorderLayout.NORTH);
                card.add(body, BorderLayout.CENTER);
                grid.add(card);
            }

            JLabel note = new JLabel("<html><b>Remember:</b> Current needs a complete loop from the positive (+) terminal back to ground (−) to work.</html>");
            note.setOpaque(true);
            note.setBackground(TINT_GREEN);
            note.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0,4,0,0, ACCENT_GREEN),
                    new EmptyBorder(10,12,10,12)
            ));

            container.add(topText, BorderLayout.NORTH);
            container.add(grid, BorderLayout.CENTER);
            container.add(note, BorderLayout.SOUTH);
        return container;
       }

       private JComponent createBreadboardBasicsPanel(){
        JPanel main = new JPanel(new BorderLayout(14,10));
        main.setBackground(BG_LIGHT);
        main.setBorder(new EmptyBorder(14, 14, 14, 14));

        JLabel h2 = new JLabel("How the Holes Are Connected");
        h2.setFont(new Font("SansSerif", Font.BOLD, 20));
        main.add(h2, BorderLayout.NORTH);

        BreadboardPanel demoBoard = new BreadboardPanel(false, null, null, null);
        JPanel boardHolder = new JPanel(new GridBagLayout());
        boardHolder.setBackground(new Color(225,228,226));
        boardHolder.setBorder(new LineBorder(new Color(185,190,187), 2));
        boardHolder.add(demoBoard);

        return main;
       }



}