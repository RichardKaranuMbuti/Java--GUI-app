import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ComplaintManagementApp extends JFrame {
    private ArrayList<Complaint> complaints = new ArrayList<>();
    private DefaultListModel<Complaint> complaintListModel = new DefaultListModel<>();
    private JList<Complaint> complaintList = new JList<>(complaintListModel);
    private JTextField titleInput = new JTextField(20);
    private JTextArea descriptionInput = new JTextArea(5, 20);
    private JTextField nameInput = new JTextField(20);
    private JTextField phoneInput = new JTextField(20);

    public ComplaintManagementApp() {
        super("Complaint Management Module");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = createInputPanel();
        JScrollPane listScrollPane = createListScrollPane();

        add(inputPanel, BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Center on screen
        setResizable(true); // Make window resizable
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Title:"));
        panel.add(titleInput);

        panel.add(new JLabel("Description:"));
        descriptionInput.setLineWrap(true);
        descriptionInput.setWrapStyleWord(true);
        panel.add(new JScrollPane(descriptionInput));

        panel.add(new JLabel("Name:"));
        panel.add(nameInput);

        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneInput);

        JButton submitButton = new JButton("Submit Complaint");
        submitButton.addActionListener(this::submitComplaint);
        panel.add(submitButton);

        return panel;
    }

    private JScrollPane createListScrollPane() {
        complaintList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Complaint) {
                    Complaint complaint = (Complaint) value;
                    setText("<html>ID: " + complaint.getId() + "<br/>Title: " + complaint.getTitle() + "<br/>Name: " + complaint.getName() + "<br/>Phone: " + complaint.getPhoneNumber() + "<br/>Resolved: " + complaint.isResolved() + "</html>");
                }
                return c;
            }
        });

        complaintList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    // Double-click detected
                    int index = complaintList.locationToIndex(evt.getPoint());
                    if (index >= 0) {
                        showComplaintEditDialog(complaintListModel.getElementAt(index));
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(complaintList);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        return scrollPane;
    }

    private void submitComplaint(ActionEvent e) {
        Complaint complaint = new Complaint(titleInput.getText(), descriptionInput.getText(),
                nameInput.getText(), phoneInput.getText());
        complaints.add(complaint);
        complaintListModel.addElement(complaint);
        // Reset input fields
        titleInput.setText("");
        descriptionInput.setText("");
        nameInput.setText("");
        phoneInput.setText("");
    }

    private void showComplaintEditDialog(Complaint complaint) {
        JDialog dialog = new JDialog(this, "Edit Complaint", true);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));
        dialog.add(new JLabel("Title:"));
        JTextField titleField = new JTextField(complaint.getTitle(), 20);
        dialog.add(titleField);

        dialog.add(new JLabel("Description:"));
        JTextArea descriptionField = new JTextArea(complaint.getDescription(), 5, 20);
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionField);
        dialog.add(descriptionScrollPane);

        dialog.add(new JLabel("Name:"));
        JTextField nameField = new JTextField(complaint.getName(), 20);
        dialog.add(nameField);

        dialog.add(new JLabel("Phone Number:"));
        JTextField phoneField = new JTextField(complaint.getPhoneNumber(), 20);
        dialog.add(phoneField);

        JCheckBox resolvedCheckBox = new JCheckBox("Resolved", complaint.isResolved());
        dialog.add(resolvedCheckBox);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            complaint.setTitle(titleField.getText());
            complaint.setDescription(descriptionField.getText());
            complaint.setName(nameField.getText());
            complaint.setPhoneNumber(phoneField.getText());
            complaint.setResolved(resolvedCheckBox.isSelected());
            refreshComplaintsList();
            dialog.dispose();
        });
        dialog.add(updateButton);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void refreshComplaintsList() {
        complaintListModel.clear();
        for (Complaint complaint : complaints) {
            complaintListModel.addElement(complaint);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ComplaintManagementApp().setVisible(true));
    }
}
