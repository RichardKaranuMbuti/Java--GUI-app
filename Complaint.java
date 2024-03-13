public class Complaint {
    private static int count = 0;
    private int id;
    private String title;
    private String description;
    private String name; // Complainant's name
    private String phoneNumber; // Complainant's phone number
    private boolean resolved;

    public Complaint(String title, String description, String name, String phoneNumber) {
        this.id = ++count;
        this.title = title;
        this.description = description;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.resolved = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public boolean isResolved() { return resolved; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }

    @Override
    public String toString() {
        return String.format("ID: %d, Title: %s, Name: %s, Phone: %s, Resolved: %b",
                id, title, name, phoneNumber, resolved);
    }
}
