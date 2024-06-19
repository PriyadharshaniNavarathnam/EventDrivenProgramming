import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class studentRegistrationForm120 extends JFrame {
    private JTextField textName;
    private JTextField textRegNumber;
    private JTextField textemailId;
    private JTextField textdepName;
    private JButton btnADD;
    private JButton btnUPDATE;
    private JButton btnDELETE;
    private JPanel registrationPanel;
    private JTable studentDetails;
    private DefaultTableModel registrationModel;
    private int selectedRow = -1;

    public studentRegistrationForm120() {

        String[] columnNames = {"NAME", "REGISTRATION NUMBER", "Email ID", "DEPARTMENT"};
        registrationModel = new DefaultTableModel(columnNames, 0);
        registrationModel.addRow(columnNames);  // Add titles as the first row

        studentDetails.setModel(registrationModel); // Set the table model for the JTable

        // Add mouse listener to populate input fields when a row is clicked
        studentDetails.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = studentDetails.getSelectedRow();
                if (selectedRow >= 0) {
                    textName.setText(registrationModel.getValueAt(selectedRow, 0).toString());
                    textRegNumber.setText(registrationModel.getValueAt(selectedRow, 1).toString());
                    textemailId.setText(registrationModel.getValueAt(selectedRow, 2).toString());
                    textdepName.setText(registrationModel.getValueAt(selectedRow, 3).toString());
                }
            }
        });

        // Add action listeners for buttons
        btnADD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        btnUPDATE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedStudent();

            }
        });
        btnDELETE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedStudent();

            }
        });
    }

    private void addStudent() {

        String name = textName.getText();
        String regNumber = textRegNumber.getText();
        String email = textemailId.getText();
        String department = textdepName.getText();

        // Validate input fields
        if (name.isEmpty() || regNumber.isEmpty() || email.isEmpty() || department.isEmpty()) {
            JOptionPane.showMessageDialog(registrationPanel, "All fields are required.");
            return;
        }

        // Validate registration number using a regular expression (Example: 2020E120)
        if (!regNumber.matches("^\\d{4}[A-Za-z]\\d+$")) {
            JOptionPane.showMessageDialog(registrationPanel, "Invalid registration number format.");
            return;
        }

        // Validate email using a regular expression
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(registrationPanel, "Invalid email format.");
            return;
        }

        // Add the data to the table
        registrationModel.addRow(new Object[]{name, regNumber, email, department});

        // Clear input fields
        clearInputFields();
    }

    private void updateSelectedStudent() {


        // Logic to update student details
        if (selectedRow >= 0) {
            String name = textName.getText();
            String regNumber = textRegNumber.getText();
            String email = textemailId.getText();
            String department = textdepName.getText();

            // Validate input fields
            if (name.isEmpty() || regNumber.isEmpty() || email.isEmpty() || department.isEmpty()) {
                JOptionPane.showMessageDialog(registrationPanel, "All fields are required.");
                return;
            }

            // Update the data in the table model
            registrationModel.setValueAt(name, selectedRow, 0);
            registrationModel.setValueAt(regNumber, selectedRow, 1);
            registrationModel.setValueAt(email, selectedRow, 2);
            registrationModel.setValueAt(department, selectedRow, 3);

            // Clear input fields
            clearInputFields();
            selectedRow = -1; // Reset selected row
        } else {
            JOptionPane.showMessageDialog(registrationPanel, "Select a row to update.");
        }

    }


    private void deleteSelectedStudent() {
        // Logic to delete student details
        if (selectedRow >= 0) {
            registrationModel.removeRow(selectedRow);
            selectedRow = -1; // Reset selected row
        } else {
            JOptionPane.showMessageDialog(registrationPanel, "Select a row to delete.");
        }

        // Clear input fields
        clearInputFields();
    }

    private void clearInputFields() {
        textName.setText("");
        textRegNumber.setText("");
        textemailId.setText("");
        textdepName.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Student Registration Form");
            frame.setContentPane(new studentRegistrationForm120().registrationPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}

