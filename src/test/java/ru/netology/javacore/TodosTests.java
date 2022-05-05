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

    @BeforeEach
    public void initData() {
        testTasks.addTask("Убраться");
        testTasks.addTask("Прогуляться");
        testTasks.addTask("Поесть");

    }

    @AfterEach
    public void clearTasks() {
        testTasks.clearTasksList();
    }


    @Test
    void addTaskTest() {
        List<String> expected = new ArrayList<>(Arrays.asList("Убраться", "Прогуляться", "Поесть",  "Проверить почту"));
        testTasks.addTask("Проверить почту");
        Assertions.assertEquals(expected, testTasks.getTasksList(),  "Тест на добавление элемента списка не пройден");
    }

    @Test
    void remoteTaskTest() {
        List<String> expected = new ArrayList<>(Arrays.asList("Прогуляться", "Поесть"));
        testTasks.removeTask("Убраться");
        Assertions.assertEquals(expected, testTasks.getTasksList(), "Тест на удаление элемента списка не пройден");
    }

    @Test
    void getAllTasksTest() {
        String expected = "Поесть Прогуляться Убраться";
        String result = testTasks.getAllTasks();
        Assertions.assertEquals(expected, result, "Тест на формирование сортированного списка не пройден");
    }

}
