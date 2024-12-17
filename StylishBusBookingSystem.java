import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StylishBusBookingSystem extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;

    // Frame 1 components
    private JComboBox<String> fromLocationComboBox;
    private JComboBox<String> toLocationComboBox;
    private JTextField dateTextField;
    private JButton nextButton1;

    // Frame 2 components
    private JRadioButton acRadioButton;
    private JRadioButton nonAcRadioButton;
    private JButton nextButton2;
    private JButton backButton2;

    // Frame 3 components
    private JList<String> busList;
    private DefaultListModel<String> busliListModel;
    private JButton nextButton3;
    private JButton backButton3;

    // Frame 4 components
    private JPanel seatPanel;
    private JButton backButton4;
    private List<JButton> seatButtons;

    // State variables
    private String selectedFromLocation;
    private String selectedToLocation;
    private Date selectedDate;
    private String selectedBusType;
    private String selectedBus;

    public StylishBusBookingSystem() {
        setTitle("Stylish Bus Booking System");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set overall layout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize frames
        mainPanel.add(createFrame1(), "Frame1");
        mainPanel.add(createFrame2(), "Frame2");
        mainPanel.add(createFrame3(), "Frame3");
        mainPanel.add(createFrame4(), "Frame4");

        // Add main panel to frame
        add(mainPanel);

        // Show Frame 1 initially
        cardLayout.show(mainPanel, "Frame1");

        // Set custom styles
        setCustomStyles();
    }

    private void setCustomStyles() {
        // Set global styles
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("Label.font", new Font("Arial", Font.BOLD, 14));
        UIManager.put("ComboBox.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("List.font", new Font("Arial", Font.PLAIN, 14));
    }

    private JPanel createFrame1() {
        JPanel frame1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Add from location
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame1.add(new JLabel("From:"), gbc);
        fromLocationComboBox = new JComboBox<>(new String[] {"Kakinada","Jaggampeta","Podili","Rajahmundry","Kunkuduru","Gokavaram","Mallisala"});
        gbc.gridx = 1;
        frame1.add(fromLocationComboBox, gbc);

        // Add to location
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame1.add(new JLabel("To:"), gbc);
        toLocationComboBox = new JComboBox<>(new String[] {"Hyderabad","Vizag","Vijayawada","Bengaluru","Pune","Secundrabad","Kadapa"});
        gbc.gridx = 1;
        frame1.add(toLocationComboBox, gbc);

        // Add date input field
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame1.add(new JLabel("Date (MM/dd/yyyy):"), gbc);
        dateTextField = new JTextField(10);
        gbc.gridx = 1;
        frame1.add(dateTextField, gbc);

        // Add next button
        nextButton1 = new JButton("Next");
        nextButton1.addActionListener(e -> goToFrame2());
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        frame1.add(nextButton1, gbc);

        frame1.setBackground(new Color(230, 230, 250));

        return frame1;
    }

    private JPanel createFrame2() {
        JPanel frame2 = new JPanel(new BorderLayout());
        
        // Panel for the radio buttons
        JPanel radioButtonPanel = new JPanel(new FlowLayout());
        acRadioButton = new JRadioButton("AC");
        nonAcRadioButton = new JRadioButton("Non-AC");
        ButtonGroup group = new ButtonGroup();
        group.add(acRadioButton);
        group.add(nonAcRadioButton);
        radioButtonPanel.add(acRadioButton);
        radioButtonPanel.add(nonAcRadioButton);
        
        // Add the radio button panel to the top of the frame
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Choose bus type:"));
        topPanel.add(radioButtonPanel);
        frame2.add(topPanel, BorderLayout.NORTH);
        
        // Panel for back and next buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        backButton2 = new JButton("Back");
        backButton2.addActionListener(e -> goBackToFrame1());
        nextButton2 = new JButton("Next");
        nextButton2.addActionListener(e -> goToFrame3());
        buttonPanel.add(backButton2);
        buttonPanel.add(nextButton2);
        
        // Add the button panel to the bottom of the frame
        frame2.add(buttonPanel, BorderLayout.SOUTH);
        
        // Set the background color
        frame2.setBackground(new Color(230, 230, 250));
        
        return frame2;
    }
    

    private JPanel createFrame3() {
        JPanel frame3 = new JPanel(new BorderLayout());

        // Add bus list
        busListModel = new DefaultListModel<>();
        busList = new JList<>(busListModel);
        JScrollPane scrollPane = new JScrollPane(busList);
        frame3.add(scrollPane, BorderLayout.CENTER);

        // Add back and next buttons
        JPanel buttonPanel = new JPanel();
        backButton3 = new JButton("Back");
        backButton3.addActionListener(e -> goBackToFrame2());
        nextButton3 = new JButton("Next");
        nextButton3.addActionListener(e -> goToFrame4());
        buttonPanel.add(backButton3);
        buttonPanel.add(nextButton3);
        frame3.add(buttonPanel, BorderLayout.SOUTH);

        frame3.setBackground(new Color(230, 230, 250));

        return frame3;
    }

    private JPanel createFrame4() {
        JPanel frame4 = new JPanel(new BorderLayout());

        // Add seat panel
        seatPanel = new JPanel(new GridLayout(5, 5, 10, 10));
        seatButtons = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            JButton seatButton = new JButton(String.valueOf(i + 1));
            seatButton.setBackground(Color.RED);
            seatButton.setForeground(Color.WHITE);
            seatButton.setFont(new Font("Arial", Font.BOLD, 12));
            seatButton.addActionListener(e -> reserveSeat(seatButton));
            seatButtons.add(seatButton);
            seatPanel.add(seatButton);
        }
        frame4.add(seatPanel, BorderLayout.CENTER);

        // Add back button
        backButton4 = new JButton("Back");
        backButton4.addActionListener(e -> goBackToFrame3());
        frame4.add(backButton4, BorderLayout.SOUTH);

        frame4.setBackground(new Color(230, 230, 250));

        return frame4;
    }

    // Navigation methods
    private void goToFrame2() {
        selectedFromLocation = (String) fromLocationComboBox.getSelectedItem();
        selectedToLocation = (String) toLocationComboBox.getSelectedItem();

        // Parse and validate date input
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            selectedDate = dateFormat.parse(dateTextField.getText());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please enter a date in MM/dd/yyyy format.");
            return;
        }

        // Switch to Frame 2
        cardLayout.show(mainPanel, "Frame2");
    }

    private void goToFrame3() {
        selectedBusType = acRadioButton.isSelected() ? "AC" : "Non-AC";

        // Update the bus list based on the selected bus type
        busListModel.clear();
        if (selectedBusType.equals("AC")) {
            busListModel.addElement("AC Bus 1");
            busListModel.addElement("AC Bus 2");
        } else {
            busListModel.addElement("Non-AC Bus 1");
            busListModel.addElement("Non-AC Bus 2");
        }

        // Switch to Frame 3
        cardLayout.show(mainPanel, "Frame3");
    }

    private void goToFrame4() {
        selectedBus = busList.getSelectedValue();

        // Assume that some buses are booked and others are available
        // For this demonstration, let's assume that the first bus is booked and the second is not
        for (int i = 0; i < seatButtons.size(); i++) {
            JButton seatButton = seatButtons.get(i);
            if (i < 15) { // Let's assume the first 15 seats are booked (green)
                seatButton.setBackground(Color.GREEN);
            } else { // The rest of the seats are available (red)
                seatButton.setBackground(Color.RED);
            }
        }

        // Switch to Frame 4
        cardLayout.show(mainPanel, "Frame4");
    }

    private void reserveSeat(JButton seatButton) {
        if (seatButton.getBackground() == Color.RED) {
            // Reserve the seat and change its color to green
            seatButton.setBackground(Color.GREEN);
        } else {
            JOptionPane.showMessageDialog(this, "Seat is already reserved.");
        }
    }

    private void goBackToFrame1() {
        cardLayout.show(mainPanel, "Frame1");
    }

    private void goBackToFrame2() {
        cardLayout.show(mainPanel, "Frame2");
    }

    private void goBackToFrame3() {
        cardLayout.show(mainPanel, "Frame3");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StylishBusBookingSystem busBookingSystem = new StylishBusBookingSystem();
            busBookingSystem.setVisible(true);
        });
    }
}