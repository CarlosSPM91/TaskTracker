<div align=center>
  
# TaskTrackerCLI 


</div>

**TaskTrackerCLI** is a command-line application designed to help users track and manage their tasks. With this tool, users can keep tabs on their to-do items, monitor ongoing tasks, and review completed ones.

## Features

**Add a Task:** 
Add a new task with a description and TODO status.

**Update a Task:**
Update the description of an existing task.

**Delete a Task:**
Remove a task by its own ID.

**Mark a Task:**
Mark a task as "todo", "in progress" or "done."

**List Tasks:**
List all tasks or filter them by status.

## Task Properties

**ID:** A unique identifier for the task

**Description:** A short description of the task

**Status:** The status of the task (todo, in-progress, done)

**CreatedAt:** The date and time when the task was created

**UpdatedAt:** The date and time when the task was last updated


## Installation

**Clone the repository:**
 ```bash
git clone https://github.com/CarlosSPM91/TaskTracker.git
cd TaskTracker
```
 **Change directory to:**
 ```bash
cd TaskTracker
```

**Compile the source code:**
 ```bash
javac TaskTrackerCLI.java 
```

 ## How to use:
 ```bash
+ java TaskTrackerCLI <command> [arguments]

+ Adding a new task
java TaskTrackerCLI add "Buy groceries"

+ Updating a task
java TaskTrackerCLI update 1 "Buy groceries and cook dinner"

+ Deleting a task
java TaskTrackerCLI delete 1

+ Marking a task as in progress
java TaskTrackerCLI mark-in-progress 1

+ Marking a task as done
java TaskTrackerCLI mark-done 1

+ Listing  tasks
java TaskTrackerCLI list
java TaskTrackerCLI list todo
java TaskTrackerCLI list in-progress
java TaskTrackerCLI list done
```
