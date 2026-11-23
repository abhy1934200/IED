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

        //day2
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setPreferredSize(new Dimension(285,0));
        sidePanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(205,212,208)),
            new EmptyBorder(14,14,14,14)
        ));

        JLabel rulesTitle = new JLabel("Breadboard Rules");
        rulesTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        rulesTitle.setForeground(HEADER_GREEN);

        JTextArea rules = new JTextArea(
            "1. Each vertical column of 5 holes (A-E or F-J) is connected underneath. \n\n" +
            "2. The center trench splits the top half from the bottom half. \n\n" +
            "3. Both the top and bottom edges have two horizontal power rails: (red) and - (blue). \n\n" + 
            "4. Put the two legs of a resistor or led in different columns so they dont short together."
        );

        rules.setWrapStyleWord(true);
        rules.setLineWrap(true);
        rules.setEditable(false);
        rules.setOpaque(false);
        rules.setFont(new Font("SansSerif", Font.PLAIN, 13));

        sidePanel.add(rulesTitle);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(rules);

        main.add(boardHolder, BorderLayout.CENTER);
        main.add(sidePanel, BorderLayout.EAST);
        return main;
       }

private JComponent createSimulatorPanel(){
        JPanel main = new JPanel (new BorderLayout(14,10 ));
        main.setBackground(BG_LIGHT);
        main.setBorder(new EmptyBorder(12,12,12,12));

        JLabel statusLabel = new JLabel("Pick a wire or part above, then click two holes on the board.");
        statusLabel.setOpaque(true);
        statusLabel.setBackground(TINT_GREEN);
        statusLabel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(180,205,190)),
            new EmptyBorder(8,10,8,10)
        ));

        JLabel meterReadout = new JLabel("<html><div style='text-align:center;'><b>0.00 V</b> &nbsp;|&nbsp; <b>0.00 mA</b><br><span style='font-size:10px;color:#cbd5e1;'>Select Multimeter & click 2 holes</span></div></html>", SwingConstants.CENTER);
        meterReadout.setOpaque(true);
        meterReadout.setBackground(new Color(28,36,32));
        meterReadout.setForeground(new Color(110,231,183));
        meterReadout.setFont(new Font("Monospaced", Font.BOLD, 15));
        meterReadout.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDER_GREEN, 2),
            new EmptyBorder(8,10,8,10)
        ));

        JTextArea analysisArea = new JTextArea("Build a circuit with a 5V Battery, Resistor, and LED, then click 'Calculate' to check current and voltage.");
        analysisArea.setWrapStyleWord(true);
        analysisArea.setLineWrap(true);
        analysisArea.setEditable(false);
        analysisArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        analysisArea.setBackground(new Color(248,250,249));
        analysisArea.setBorder(new EmptyBorder(8,8,8,8));


        BreadboardPanel simBoard = new BreadboardPanel(true, statusLabel, meterReadout, analysisArea);
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 4));
        toolbar.setBackground(Color.WHITE);

        String[] tools = {"Red Wire", "Black Wire", "5V Battery", "Resistor (510Ω)", "LED (Red)", "Switch", "Multimeter"};
        ButtonGroup toolGroup = new ButtonGroup();

        for(String t : tools){
            JToggleButton btn = new JToggleButton(t);
            btn.setFont(new Font("SansSerif", Font.PLAIN, 12));
            btn.setFocusPainted(false);
            if(t.equals("Red Wire")) btn.setSelected(true);
            btn.addActionListener(e -> {
                simBoard.setActiveTool(t);
                statusLabel.setText(t + " selected. Click two holes to place.");
            });
            toolGroup.add(btn);
            toolbar.add(btn);
       }

       JButton demoBtn = new JButton("Load LED Example");
       demoBtn.setFont(new Font("sansSerif", Font.BOLD, 12));
       demoBtn.addActionListener(e -> simBoard.clearBoard());

       JButton clearBtn = new JButton("Clear");
       clearBtn.setFont(new Font("SansSerif", Font.PLAIN, 12));
       clearBtn.addActionListener(e -> simBoard.clearBoard());

       toolbar.add(demoBtn);
       toolbar.add(clearBtn);

       JPanel centerPanel = new JPanel(new BorderLayout(0,8));
       centerPanel.setBackground(Color.WHITE);
       centerPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(205, 212, 208)),
            new EmptyBorder(8,10,8,10)
       ));

       JPanel boardHolder = new JPanel(new GridBagLayout());
       //day 3
       boardHolder.setBackground(new Color(225,228,226 ));
       boardHolder.setBorder(new LineBorder(new Color(185,190,187)));
       boardHolder.add(simBoard);

       centerPanel.add(toolbar, BorderLayout.NORTH);
       centerPanel.add(boardHolder, BorderLayout.CENTER);
       centerPanel.add(statusLabel, BorderLayout.SOUTH);

       JPanel rightPanel = new JPanel();
       rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
       rightPanel.setBackground(Color.WHITE);
       rightPanel.setPreferredSize((new Dimension(280,0)));
       rightPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(205,212,208)),
            new EmptyBorder(12,12,12,12)
       ));

       JLabel mmTitle = new JLabel("Multimeter");
       mmTitle.setFont(new Font("SansSerif", Font.BOLD, 15));
       mmTitle.setForeground(HEADER_GREEN);
       mmTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
       meterReadout.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));
       meterReadout.setAlignmentX(Component.LEFT_ALIGNMENT);

       JLabel caTitle = new JLabel("Ohm's Law Check");
       caTitle.setFont(new Font("SansSerif", Font.BOLD, 15));
       caTitle.setForeground(HEADER_GREEN);
       caTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

       JButton analyzeBtn = new JButton("Calculate");
       analyzeBtn.setBackground(ACCENT_GREEN);
       analyzeBtn.setForeground(Color.WHITE);
       analyzeBtn.setFocusPainted(false);
       analyzeBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
       analyzeBtn.addActionListener(e -> simBoard.analyzeCircuit());

       JScrollPane analysisScroll = new JScrollPane(analysisArea);
       analysisScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
       analysisScroll.setPreferredSize(new Dimension(255,180));

       JLabel tips = new JLabel("<html><body style='width: 240px; color:#555; font-size:11px;'>" +
                "<b>How to use:</b><br>• Click a placed <b>Switch</b> to flip it on/off.<br>" +
                "• Use <b>Multimeter</b> to measure voltage between any 2 holes.<br>" +
                "• Try <b>Load LED Example</b> to test a working circuit.</body></html>");
    
       tips.setAlignmentX(Component.LEFT_ALIGNMENT);
       rightPanel.add(mmTitle);
       rightPanel.add(Box.createVerticalStrut(6));
       rightPanel.add(meterReadout);
       rightPanel.add(Box.createVerticalStrut(14));
       rightPanel.add(caTitle);
       rightPanel.add(Box.createVerticalStrut(6));
       rightPanel.add(analyzeBtn);
       rightPanel.add(Box.createVerticalStrut(8));
       rightPanel.add(analysisScroll);
        rightPanel.add(Box.createVerticalStrut(12));
        rightPanel.add(tips);

        main.add(centerPanel, BorderLayout.CENTER);
        main.add(rightPanel, BorderLayout.EAST);
        return main;
}

private JComponent createQuizPanel(){
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(Color.WHITE);
    panel.setBorder(new EmptyBorder(20,25,20,25));

    ButtonGroup g1 = new ButtonGroup();
    JRadioButton q1b = new JRadioButton("Limit current in a cicuit.");
    panel.add(createQuestionBlock("1. What is the main job of a resistor?", g1,
        new JRadioButton("Store data"), q1b, new JRadioButton("Generate power")));

    ButtonGroup g2 = new ButtonGroup();
    JRadioButton q2a = new JRadioButton("Connect parts temporarily without soldering");
        panel.add(createQuestionBlock("2. What is a breadboard used for?", g2,
                q2a, new JRadioButton("Increase battery voltage"), new JRadioButton("Measure resistance")));

    ButtonGroup g3 = new ButtonGroup();
    JRadioButton q3c = new JRadioButton("Breaks the loop so current stops");
        panel.add(createQuestionBlock("3. What happens when a switch is open?", g3,
                new JRadioButton("Current speeds up"), new JRadioButton("The LED gets brighter"), q3c));

    JButton gradeBtn = new JButton("Check Answers");
    gradeBtn.setBackground(ACCENT_GREEN);
    gradeBtn.setForeground(Color.WHITE);
    gradeBtn.setFocusPainted(false);
    gradeBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

    JLabel resultLabel = new JLabel(" ");
    resultLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
    resultLabel.setForeground(HEADER_GREEN);
    resultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

    gradeBtn.addActionListener(e -> {
        int score = 0;
        if(q1b.isSelected()) score++;
        if(q2a.isSelected()) score++;
        if(q3c.isSelected()) score++;
        resultLabel.setText("Score: " + score + "/3" + (score ==3 ? "- Nice Job" : "- Check the parts tab and try again"));
    });

    panel.add(Box.createVerticalStrut(10));
    panel.add(gradeBtn);
    panel.add(Box.createVerticalStrut(10));
    panel.add(resultLabel);

    return new JScrollPane(panel);
}







}