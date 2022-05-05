package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TodoServer {
    Todos todos;
    int port;
    Tasks allTasks;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
        this.allTasks = new Tasks();
    }

    public void start() throws IOException {
        System.out.println("Starting server at port " + port);
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is running");
        while (true) {
            try (
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                System.out.println("New connection accepted");

                String taskInput = in.readLine();

                if (taskInput.equals("client_exit")) {
                    System.out.println("Server shutdown");
                    break;
                }

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();


                todos = gson.fromJson(taskInput, Todos.class);
                String type = todos.getAction();
                String task = todos.getTask();

                switch (type) {
                    case "ADD":
                        todos.addTask(task, allTasks.tasksList);
                        break;
                    case "REMOVE":
                        todos.removeTask(task, allTasks.tasksList);
                        break;
                }
                String tasks = todos.getAllTasks(allTasks.tasksList);
                out.println("_________________________________________");
                out.println("Ваши задачи: " + tasks);
                out.println("_________________________________________");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
