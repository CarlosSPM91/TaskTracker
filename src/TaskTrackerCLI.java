import controller.TaskManager;

public class TaskTrackerCLI {
    public static void main(String[] args) {

        TaskManager tm = new TaskManager();

        if (args.length <1) {
            System.out.println("Usage: TaskTrackerCLI [option]");
            return;
        }

        String option = args[0];


            option = args[1];

            switch (option) {
                case "add":
                    if (args.length < 2) {
                        System.out.println("Usage: TaskTrackerCLI [add] [description]");
                        break;
                    }
                    tm.add(args[2]);
                    break;
                case "update":
                    if (args.length < 3) {
                        System.out.println("Usage: TaskTrackerCLI [update] [id] [description]");
                        break;
                    }
                    tm.update(Integer.parseInt(args[2]), args[3]);
                    break;
                case "mark-todo":
                    if (args.length < 2) {
                        System.out.println("Usage: TaskTrackerCLI [mark-todo] [id]");
                        break;
                    }
                    tm.markTodo(Integer.parseInt(args[2]));
                    break;
                case "mark-in-progress":
                    if (args.length < 2) {
                        System.out.println("Usage: TaskTrackerCLI [mark-in-progress] [id]");
                        break;
                    }
                    tm.markInProgres(Integer.parseInt(args[2]));
                    break;
                case "mark-done":
                    if (args.length < 2) {
                        System.out.println("Usage: TaskTrackerCLI [mark-done] [id]");
                        break;
                    }
                        tm.markDone(Integer.parseInt(args[2]));
                    break;
                case "list":
                    if (args.length < 3) {
                        System.out.println("Usage: TaskTrackerCLI [list] or TaskTrackerCLI [list] [stat]");
                        break;
                    }
                    tm.list(args[2]);
                    break;
                default:
                    break;
            }
            tm.writeJSON();

    }
}