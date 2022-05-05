package ru.netology.javacore;

//import jdk.internal.icu.text.UnicodeSet;
//import ru.netology.javacore.Tasks;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    public String type;
    public String task;
    //public static List<String> tasksList = new ArrayList<>();

    public String getAction() {
        return type;
    }

    public String getTask() {
        return task;
    }

    public void addTask(String task, List<String> tasksList) {

        tasksList.add(task);
    }

    public void removeTask(String task, List<String> tasksList) {
        tasksList.remove(task);
    }

    public void clearTasksList(List<String> tasksList) {
        tasksList.clear();
    }

    public List<String> getTasksList(List<String> tasksList) {
        return tasksList;
    }


    public String getAllTasks(List<String> tasksList) {
        Optional<String> reduced = tasksList.stream()
                .sorted(Comparator.reverseOrder())
                .reduce((CurrValue, accumValue) -> accumValue + " " + CurrValue);
        return reduced.orElse("Задач пока нет");
    }


}
