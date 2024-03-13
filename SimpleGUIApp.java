import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SimpleGUIApp extends JFrame implements ActionListener {
    // Constructor
    public SimpleGUIApp() {
        // Call the parent class constructor to set the title of the window
        super("Simple GUI Application");
        // Set the window size
        setSize(800, 600);
        // Specify what happens when the close button is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a button and add it to the frame
        JButton button = new JButton("Click Me!");
        button.addActionListener(this); // Register this class as the button's action listener
        add(button);
    }
    
    // Implement the actionPerformed method to handle button clicks
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Hello, World!");
    }


public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            SimpleGUIApp app = new SimpleGUIApp();
            app.setVisible(true);
        }
    });
}


}
