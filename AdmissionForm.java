import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AdmissionForm extends JFrame {

    private JTextField nameField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JComboBox<String> courseComboBox;
    private JButton submitButton;
    private JButton toggleBackgroundButton;

    public AdmissionForm() {
        setTitle("Admission Form");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Admission Form");
        titleLabel.setForeground(new Color(33, 150, 243));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(new Color(33, 150, 243));
        mainPanel.add(nameLabel, gbc);

        gbc.gridy = 2;
        nameField = new JTextField(20);
        nameField.setBackground(new Color(241, 248, 250));
        nameField.setForeground(Color.BLACK);
        nameField.setBorder(getRoundedBorderInstance());
        mainPanel.add(nameField, gbc);

        gbc.gridy = 3;
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setForeground(new Color(33, 150, 243));
        mainPanel.add(genderLabel, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        ButtonGroup genderGroup = new ButtonGroup();
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setForeground(new Color(33, 150, 243));
        maleRadioButton.setBackground(new Color(226, 240, 242));
        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setForeground(new Color(33, 150, 243));
        femaleRadioButton.setBackground(new Color(226, 240, 242));
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(new Color(226, 240, 242));
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        mainPanel.add(genderPanel, gbc);

        gbc.gridy = 5;
        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setForeground(new Color(33, 150, 243));
        mainPanel.add(courseLabel, gbc);

        gbc.gridy = 6;
        courseComboBox = new JComboBox<>(new String[] { "Computer Science", "ENTC", "MECH", "AIDS", "CIVIL" });
        courseComboBox.setBackground(new Color(241, 248, 250));
        courseComboBox.setForeground(Color.BLACK);
        courseComboBox.setBorder(getRoundedBorderInstance());
        mainPanel.add(courseComboBox, gbc);

        gbc.gridy = 7;
        gbc.gridwidth = 2;
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(33, 150, 243));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorderPainted(false);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your name.");
                } else {
                    saveFormData();
                    JOptionPane.showMessageDialog(null, "Form submitted successfully!");
                }
            }
        });
        mainPanel.add(submitButton, gbc);

        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        toggleBackgroundButton = new JButton("Dark mode");
        toggleBackgroundButton.setFont(new Font("Arial", Font.PLAIN, 10));
        toggleBackgroundButton.setForeground(new Color(100, 100, 100));
        toggleBackgroundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleBackground();
            }
        });
        mainPanel.add(toggleBackgroundButton, gbc);

        add(mainPanel);
        setVisible(true);
    }

    private Border getRoundedBorderInstance() {
        int radius = 10;
        return BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 10, 5, 10),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(33, 150, 243)),
                        BorderFactory.createLineBorder(new Color(226, 240, 242), radius)));
    }

    private void toggleBackground() {
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        Color currentColor = mainPanel.getBackground();
        if (currentColor.equals(new Color(240, 240, 240))) {
            mainPanel.setBackground(new Color(33, 33, 33));
        } else {
            mainPanel.setBackground(new Color(240, 240, 240));
        }
    }

    private void saveFormData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("admission_form_data.txt", true))) {
            String name = nameField.getText();
            String gender = maleRadioButton.isSelected() ? "Male" : "Female";
            String course = (String) courseComboBox.getSelectedItem();

            writer.write("Name: " + name + ", Gender: " + gender + ", Course: " + course);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdmissionForm::new);
    }
}
