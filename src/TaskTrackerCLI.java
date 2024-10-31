import controller.TaskManager;

public class TaskTrackerCLI {
    public static void main(String[] args) {

        TaskManager tm = new TaskManager();

        if (args.length <1) {
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
            return;
        }

        String option = args[0];

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
                    tm.update(Integer.parseInt(args[1]), args[2]);
                    break;
                case "delete":
                    if (args.length < 2) {
                        System.out.println("Usage: TaskTrackerCLI [delete] [id]");
                        break;
                    }
                    tm.delete(Integer.parseInt(args[1]));
                    break;
                case "mark-todo":
                    if (args.length < 2) {
                        System.out.println("Usage: TaskTrackerCLI [mark-todo] [id]");
                        break;
                    }
                    tm.markTodo(Integer.parseInt(args[1]));
                    break;
                case "mark-in-progress":
                    if (args.length < 2) {
                        System.out.println("Usage: TaskTrackerCLI [mark-in-progress] [id]");
                        break;
                    }
                    tm.markInProgres(Integer.parseInt(args[1]));
                    break;
                case "mark-done":
                    if (args.length < 2) {
                        System.out.println("Usage: TaskTrackerCLI [mark-done] [id]");
                        break;
                    }
                        tm.markDone(Integer.parseInt(args[1]));
                    break;
                case "list":
                    if (args.length == 1) {
                        tm.list("");
                    } else {
                        tm.list(args[1]);
                    }
                    break;
                default:
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
                    break;
            }
            tm.writeJSON();

    }
}