package ru.netology.javacore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodosTests {

    Todos testTasks = new Todos();
    Tasks tList = new Tasks();

    @BeforeEach
    public void initData() {
        testTasks.addTask("Убраться",tList.tasksList);
        testTasks.addTask("Прогуляться",tList.tasksList);
        testTasks.addTask("Поесть",tList.tasksList);

    }

    @AfterEach
    public void clearTasks() {
        testTasks.clearTasksList(tList.tasksList);
    }


    @Test
    void addTaskTest() {
        List<String> expected = new ArrayList<>(Arrays.asList("Убраться", "Прогуляться", "Поесть",  "Проверить почту"));
        testTasks.addTask("Проверить почту", tList.tasksList);
        Assertions.assertEquals(expected, testTasks.getTasksList(tList.tasksList),  "Тест на добавление элемента списка не пройден");
    }

    @Test
    void remoteTaskTest() {
        List<String> expected = new ArrayList<>(Arrays.asList("Прогуляться", "Поесть"));
        testTasks.removeTask("Убраться",tList.tasksList);
        Assertions.assertEquals(expected, testTasks.getTasksList(tList.tasksList), "Тест на удаление элемента списка не пройден");
    }

    @Test
    void getAllTasksTest() {
        String expected = "Поесть Прогуляться Убраться";
        String result = testTasks.getAllTasks(tList.tasksList);
        Assertions.assertEquals(expected, result, "Тест на формирование сортированного списка не пройден");
    }

}
