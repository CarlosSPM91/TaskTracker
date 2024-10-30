package controller;

import model.Status;
import model.Task;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private final Path F_PATH = Path.of("tasks.json");

    public TaskManager() {
        this.tasks = loadJSON();
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTodo(int id) {
        tasks.get(id).setStat(Status.TODO);
    }

    public void markInProgres(int id) {
        tasks.get(id).setStat(Status.INPROGES);
    }

    public void markDone(int id) {
        tasks.get(id).setStat(Status.DONE);
    }

    public void add(String description) {
        tasks.add(new Task(description));
    }

    public void update(int id, String description) {
        int idUp = id + 1;
        tasks.get(id).setDescription(description);
        tasks.get(id).setUpdateDate(LocalDateTime.now());
    }

    public void delete(int id) {
        int idDel = id - 1;
        tasks.removeIf(taskID -> taskID.getId() == idDel);
    }

    public void list(String stat) {
        switch (stat) {
            case "todo":
                for (Task t : tasks) {
                    if (t.getStat().equals(Status.TODO)) {
                        t.toString();
                        System.lineSeparator();
                    }
                }
                break;
            case "in-progress":
                for (Task t : tasks) {
                    if (t.getStat().equals(Status.INPROGES)) {
                        t.toString();
                        System.lineSeparator();
                    }
                }
                break;
            case "done":
                for (Task t : tasks) {
                    if (t.getStat().equals(Status.DONE)) {
                        t.toString();
                        System.lineSeparator();
                    }
                }
                break;
            default:
                for (Task t : tasks) {
                    t.toString();
                    System.lineSeparator();
                }
                break;
        }
    }

    public void writeJSON() {
        StringBuilder sb = new StringBuilder("[");
        int size = tasks.size();
        int count = 0;
        if (!Files.exists(F_PATH)) {
            try {
                Files.createFile(F_PATH);
            } catch (IOException e) {
                throw new RuntimeException("Can't create JSON file", e);
            }
        }

        for (Task t : tasks) {
            sb.append("{").append("\"ID:\"").append("\"").append(String.valueOf(t.getId())).append("\"").append(",")
                    .append("\"Status:\"").append("\"").append(String.valueOf(t.getStat())).append("\"").append(",")
                    .append("\"Description:\"").append("\"").append(t.getDescription()).append("\"").append(",")
                    .append("\"CreationDate:\"").append("\"").append(String.valueOf(t.getCreationDate())).append("\"").append(",")
                    .append("\"UpdateDate:\"").append("\"").append(String.valueOf(t.getUpdateDate())).append("\"").append("}");
            if (count == size - 1) {
                sb.append(",");
            }
            count++;
        }
        sb.append("]");
        try {
            Files.writeString(F_PATH, sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Task> loadJSON() {
        ArrayList<Task> tasksFromJson = new ArrayList<>();

        if (!Files.exists(F_PATH)) {
            return tasksFromJson;
        }

        try {
            String jsonContent = Files.readString(F_PATH);

            if (jsonContent.startsWith("[") && jsonContent.endsWith("]")) {
                jsonContent = jsonContent.substring(1, jsonContent.length() - 1).trim();
            }

            if (!jsonContent.isEmpty()) {
                String[] jasonTasks = jsonContent.split("},");

                for (int i = 0; i < jasonTasks.length; i++) {
                    String[] task = jasonTasks[i].split(",");

                    //saving parameters
                    // id in position 0, Status in position 1, Description position 2, creation Date position 3 and Update date position 4
                    int id = Integer.parseInt(task[0]);
                    Status stat = Status.chooseStat(task[1]);
                    String description = task[2];
                    LocalDateTime creationDate = LocalDateTime.parse(task[3]);
                    LocalDateTime updateDate = LocalDateTime.parse(task[4]);
                    //creating task
                    Task taskfromJson = new Task(id, stat, description, creationDate, updateDate);
                    //Adding task
                    tasksFromJson.add(taskfromJson);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write JSON file", e);
        }

        return tasksFromJson;
    }

}
