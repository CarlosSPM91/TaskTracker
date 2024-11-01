import controller.TaskManager;
import model.TaskManagerException;

public class TaskTrackerCLI {
    public static void main(String[] args) {

        if (args.length < 1) {
            helpMenu();
        } else {
            String option = args[0];
            menuAction(option, args);
        }
    }

    public static void menuAction(String option, String[] args){
        TaskManager tm = new TaskManager();
        switch (option) {
            case "add":
                if (args.length > 2) {
                    System.out.println("Usage: TaskTrackerCLI [add] [description]");
                    break;
                }
                tm.add(args[1]);
                break;
            case "update":
                if (args.length < 3) {
                    System.out.println("Usage: TaskTrackerCLI [update] [id] [description]");
                    break;
                }
                try {
                    tm.update(Integer.parseInt(args[1]), args[2]);
                } catch (TaskManagerException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                if (args.length < 2) {
                    System.out.println("Usage: TaskTrackerCLI [delete] [id]");
                    break;
                }
                try {
                    tm.delete(Integer.parseInt(args[1]));
                } catch (TaskManagerException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "mark-todo":
                if (args.length < 2) {
                    System.out.println("Usage: TaskTrackerCLI [mark-todo] [id]");
                    break;
                }
                try {
                    tm.markTodo(Integer.parseInt(args[1]));
                } catch (TaskManagerException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "mark-in-progress":
                if (args.length < 2) {
                    System.out.println("Usage: TaskTrackerCLI [mark-in-progress] [id]");
                    break;
                }
                try {
                    tm.markInProgres(Integer.parseInt(args[1]));
                } catch (TaskManagerException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "mark-done":
                if (args.length < 2) {
                    System.out.println("Usage: TaskTrackerCLI [mark-done] [id]");
                    break;
                }
                try {
                    tm.markDone(Integer.parseInt(args[1]));
                } catch (TaskManagerException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "list":
                if (args.length == 1) {
                    tm.list("");
                } else {
                    tm.list(args[1]);
                }
                break;
            default:
                helpMenu();
                break;
        }
        tm.writeJSON();

    }

    private static void helpMenu(){
        System.out.println("----->TaskTrackerCLI Help Menu <-----");
        System.out.println("Usage: TaskTrackerCLI [option]");
        System.out.println("Usage: TaskTrackerCLI [add] [description]");
        System.out.println("Usage: TaskTrackerCLI [update] [id] [description]");
        System.out.println("Usage: TaskTrackerCLI [delete] [id]");
        System.out.println("Usage: TaskTrackerCLI [mark-todo] [id]");
        System.out.println("Usage: TaskTrackerCLI [mark-in-progress] [id]");
        System.out.println("Usage: TaskTrackerCLI [mark-done] [id]");
        System.out.println("Usage: TaskTrackerCLI [list] [status] or TaskTrackerCLI [list]");
        System.out.println("Thanks for use TaskTrackerCLI");
    }
}