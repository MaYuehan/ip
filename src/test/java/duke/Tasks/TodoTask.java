package duke.Tasks;

public class TodoTask extends Task {
    public TodoTask(String task) {
        super(task);
    }

    @Override
    public String tag() {
        return "[T]";
    }

    @Override
    public String toString() {
        return tag() + mark() + " " + task;
    }
}
