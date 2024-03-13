import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class ComplaintCardPanel extends JPanel {
    private Complaint complaint;
    private JTextField titleField, nameField, phoneField;
    private JTextArea descriptionField;
    private JButton toggleResolvedButton;

    public ComplaintCardPanel(Complaint complaint, Runnable onUpdate) {
        this.complaint = complaint;
        setLayout(new GridLayout(0, 1, 5, 5));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        
        titleField = new JTextField(complaint.getTitle());
        descriptionField = new JTextArea(complaint.getDescription());
        nameField = new JTextField(complaint.getName());
        phoneField = new JTextField(complaint.getPhoneNumber());
        
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);

        toggleResolvedButton = new JButton(complaint.isResolved() ? "Mark as Not Resolved" : "Mark as Resolved");
        toggleResolvedButton.addActionListener(e -> {
            complaint.setResolved(!complaint.isResolved());
            toggleResolvedButton.setText(complaint.isResolved() ? "Mark as Not Resolved" : "Mark as Resolved");
            onUpdate.run(); // This is to refresh the complaint list
        });

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener((ActionEvent e) -> {
            complaint.setTitle(titleField.getText());
            complaint.setDescription(descriptionField.getText());
            complaint.setName(nameField.getText());
            complaint.setPhoneNumber(phoneField.getText());
            onUpdate.run(); // Refresh after update
        });

        add(titleField);
        add(new JScrollPane(descriptionField));
        add(nameField);
        add(phoneField);
        add(toggleResolvedButton);
        add(updateButton);
    }
}
