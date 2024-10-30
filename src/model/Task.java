package model;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Task {
    private static int idCount;
    private int id;
    private Status stat;
    private String description;
    private final LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss dd:MM:yyyy");

    public Task (String description){
        this.id= ++idCount;
        this.stat= Status.TODO;
        this.description=description;
        this.creationDate= LocalDateTime.now();
    }

    public Task(int id, Status stat, String description, LocalDateTime creationDate, LocalDateTime updateDate) {
        this.id = id;
        this.stat = stat;
        this.description = description;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public void setStat(Status stat) {
        this.stat = stat;
        this.updateDate=LocalDateTime.now();
    }

    public Status getStat() {
        return this.stat;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Id: " + this.id+" Status: " + this.stat +
                System.lineSeparator()+"Description: " + this.description +
                System.lineSeparator()+"Creation Date: " + this.creationDate.format(format) +
                System.lineSeparator()+"Update Date: " + this.updateDate.format(format);
    }
}
