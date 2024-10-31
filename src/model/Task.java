package model;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Task {
    private static int idCount=1;
    private int id;
    private Status stat;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss dd:MM:yyyy");

    public Task(){idCount++;}


    public Task (String description){
        this.id= idCount;
        this.stat= Status.TODO;
        this.description=description;
        this.creationDate= LocalDateTime.now();
        idCount++;
    }

    public Task(int id, Status stat, String description, LocalDateTime creationDate, LocalDateTime updateDate) {
        this.id = id;
        this.stat = stat;
        this.description = description;
        this.creationDate = creationDate;
        this.updateDate = updateDate;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStat(Status stat) {
        this.stat = stat;
        this.updateDate=LocalDateTime.now();
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
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


    public String toJason() {
        return "{"+ "Id:" + String.valueOf(this.id)+" Status:" + String.valueOf(this.stat) +
                "Description:" + this.description +
                "Creation Date:" + String.valueOf(this.creationDate.format(format)) +
                "Update Date:" + String.valueOf(this.updateDate.format(format)) +"}";
    }
    @Override
    public String toString() {
        return "Id: " + this.id+" Status: " + this.stat +
                System.lineSeparator()+"Description: " + this.description +
                System.lineSeparator()+"Creation Date: " + this.creationDate.format(format) +
                System.lineSeparator()+"Update Date: " + this.updateDate.format(format);
    }
}
