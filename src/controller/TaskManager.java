package controller;

import model.Status;
import model.Task;
import model.TaskManagerException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TaskManager {
    private final ArrayList<Task> tasks;
    private final Path F_PATH = Path.of("tasks.json");

    public TaskManager() {
        this.tasks = loadJSON();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTodo(int id) throws TaskManagerException {
        int pos= selectId(id);
        if (pos==-1) {
            throw new TaskManagerException("No existing ID");
        }else {
            tasks.get(pos).setStat(Status.TODO);
        }
    }

    public void markInProgres(int id) throws TaskManagerException {
        int pos = selectId(id);
        if (pos == -1) {
            throw new TaskManagerException("No existing ID");
        } else{
            tasks.get(pos).setStat(Status.INPROGES);
        }
    }

    public void markDone(int id) throws TaskManagerException {
        int pos = selectId(id);
        if (pos == -1) {
            throw new TaskManagerException("No existing ID");
        }else{
            tasks.get(pos).setStat(Status.DONE);
        }
    }

    public void add(String description) {
        tasks.add(new Task(description));
        System.out.println("Task added");
    }

    public void update(int id, String description) throws TaskManagerException {
       int pos= selectId(id);
       if (pos==-1) {
           throw new TaskManagerException("No existing ID");
       }else {
           tasks.get(pos).setDescription(description);
           tasks.get(pos).setUpdateDate(LocalDateTime.now());
           System.out.println("Task with id " + id + " updated");
       }
    }

    public void delete(int id) throws TaskManagerException {
        int pos= selectId(id);
        if (pos==-1) {
            throw new TaskManagerException("No existing ID");
        }else {
            tasks.remove(pos);
            System.out.println("Task with id " + id + " removed");
        }
    }

    public void list(String stat) {
        switch (stat) {
            case "todo":
                for (Task t : tasks) {
                    if (t.getStat().equals(Status.TODO)) {
                        System.out.println(t);
                        System.out.println();
                    }
                }
                break;
            case "in-progress":
                for (Task t : tasks) {
                    if (t.getStat().equals(Status.INPROGES)) {
                        System.out.println(t);
                        System.out.println();
                    }
                }
                break;
            case "done":
                for (Task t : tasks) {
                    if (t.getStat().equals(Status.DONE)) {
                        System.out.println(t);
                        System.out.println();
                    }
                }
                break;
            default:
                for (Task t : tasks) {
                    System.out.println(t.toString());
                    System.out.println();
                }
                break;
        }
    }

    public String toJason(Task task) {
        return String.format(
                "{\"Id\":\"%d\", \"Status\":\"%s\", \"Description\":\"%s\", \"CreationDate\":\"%s\", \"UpdateDate\":\"%s\"}",
                task.getId(),
                task.getStat(),
                task.getDescription(),
                task.getCreationDate(),
                task.getUpdateDate() != null ? task.getUpdateDate() : task.getCreationDate()
        );
    }

    public Task fromJason(String line) {
        Task jasonTask= new Task();
        String[] task = line.split(",");
        jasonTask.setId(Integer.parseInt(task[0].substring(task[0].indexOf(":") + 2,task[0].length()-1).trim()));
        jasonTask.setStat(Status.chooseStat(task[1].substring(task[1].indexOf(":") + 2, task[1].length()-1).trim()));
        jasonTask.setDescription(task[2].substring(task[2].indexOf(":") + 2, task[2].length()-1).trim());
        jasonTask.setCreationDate(LocalDateTime.parse(task[3].substring(task[3].indexOf(":") + 2,task[3].length()-1).trim()));
        jasonTask.setUpdateDate(LocalDateTime.parse(task[4].substring(task[4].indexOf(":") + 2,task[4].length()-2).trim()));
        return jasonTask;
    }

    public void writeJSON() {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (!Files.exists(F_PATH)) {
            try {
                Files.createFile(F_PATH);
            } catch (IOException e) {
                throw new RuntimeException("Can't create JSON file", e);
            }
        }
        sb.append("[");
        while (count<tasks.size()){
            if (count != 0) sb.append(",");

            sb.append(toJason(getTasks().get(count)));
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

            if (jsonContent.startsWith("[") && jsonContent.endsWith("]"))
                jsonContent = jsonContent.substring(1, jsonContent.length() - 1).trim();

            if (!jsonContent.isEmpty()) {
                String[] jasonTasks = jsonContent.split("},");

                //Adding task
                tasksFromJson = Arrays.stream(jasonTasks).map(this::fromJason).collect(Collectors.toCollection(ArrayList::new));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write JSON file", e);
        }

        return tasksFromJson;
    }
    private int selectId(int id){
        int pos=0;
        for (Task t: tasks){
            if (t.getId()==id) return pos;
            pos++;
        }
        return -1;
    }
}
