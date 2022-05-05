package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    public String type;
    public String task;
    public static List<String> tasksList = new ArrayList<>();

    public String getAction() {
        return type;
    }

    public String getTask() {
        return task;
    }

    public void addTask(String task) {
        tasksList.add(task);
    }

    public void removeTask(String task) {
        tasksList.remove(task);
    }

    public void clearTasksList() {
        tasksList.clear();
    }

    public List<String> getTasksList() {
        return tasksList;
    }


    public String getAllTasks() {
        Optional<String> reduced = tasksList.stream()
                .sorted(Comparator.reverseOrder())
                .reduce((CurrValue, accumValue) -> accumValue + " " + CurrValue);
        return reduced.orElse("Задач пока нет");
    }


}
