package duke;

import duke.Command.*;


import java.util.Arrays;
public class Parser {
    public static Command parse(String input) throws DukeException {
        String[] parts = input.split(" ", 2);

        String command = parts[0].toLowerCase();

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return parseMarkCommand(parts);
            case "unmark":
                return parseUnmarkCommand(parts);
            case "delete":
                return parseDeleteCommand(parts);
            case "todo":
                return parseTodoCommand(parts);
            case "deadline":
                return parseDeadlineCommand(parts);
            case "event":
                return parseEventCommand(parts);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Command parseMarkCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please specify the task index to mark as done.");
        }

        try {
            int index = Integer.parseInt(parts[1].trim());
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task index to mark as done.");
        }
    }


    private static Command parseUnmarkCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please specify the task index to mark as not done.");
        }

        try {
            int index = Integer.parseInt(parts[1].trim());
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task index to mark as not done.");
        }
    }


    private static Command parseDeleteCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please specify the task index to delete.");
        }

        try {
            int index = Integer.parseInt(parts[1].trim());
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task index to delete.");
        }
    }


    private static Command parseTodoCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The description of a todo task cannot be empty.");
        }

        String description = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
        return new TodoCommand(description);
    }


    private static Command parseDeadlineCommand(String[] parts) throws DukeException {
        String task = null;
        String by = null;

        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The description of a deadline task cannot be empty.");
        }

        String[] split = parts[1].split("/by", 2);

        if (split.length < 2) {
            throw new DukeException("OOPS!!! The deadline of a deadline task cannot be empty.");
        }

        if (split.length == 2) {
            task = split[0];
            by = split[1].trim();
        }

        return new DeadlineCommand(task, by);
    }


    private static Command parseEventCommand(String[] parts) throws DukeException {
        String task = null;
        String from = null;
        String to = null;

        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The time of an event task cannot be empty.");
        }

        String[] split = parts[1].split("/from", 2);

        if (split.length < 2) {
            throw new DukeException("OOPS!!! The time of an event task cannot be empty.");
        }

        if (split.length == 2) {
            task = split[0];

            String[] details = split[1].split("/to", 2);
            if (details.length == 2) {
                from = details[0].trim();
                to = details[1].trim();
            } else {
                throw new DukeException("OOPS!!! The end time of an event task cannot be empty.");
            }
        }
        return new EventCommand(task, from, to);
    }

}