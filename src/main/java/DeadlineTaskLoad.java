public class DeadlineTaskLoad extends Task {
    private String by;

    public DeadlineTaskLoad(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String tag() {
        return "[D]";
    }

}