package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] tasksArr = new String[]{"Заняться спортом", "Почитать", "Сходить в бассейн", "Убраться", "Прогуляться", "Поесть", "Поспать", "Проверить почту"};
        int usrResp;
        String usrRespStr;
        int taskNumber;
        System.out.println("_________________________________________" + "\n" +
                "Добро пожаловать в менеджер задач TODOs!" + "\n" +
                "_________________________________________" + "\n" +
                "Что желаете сделать?" + "\n");

        while (true) {
            try (
                    Socket socket = new Socket("localhost", 8989);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            ) {

                System.out.println("1 Добавить новую задачу" + "\n"
                        + "2 Удалить задачу" + "\n"
                        + "0 Выход из программы" + "\n");
                System.out.print("Выберите действие: ");
                usrResp = scanner.nextInt();
                if (usrResp == 0) {
                    System.out.println("Завершаем работу планировщика");
                    out.println("client_exit");
                    break;
                }

                for (int i = 0; i < tasksArr.length; i++) {
                    System.out.println(i + 1 + " " + tasksArr[i]);
                }
                System.out.print("Введите номер задачи: ");
                taskNumber = scanner.nextInt();
                usrRespStr = (usrResp == 1) ? "ADD" : "REMOVE";
                out.println("{ \"type\": \"" + usrRespStr + "\", \"task\": \"" + tasksArr[taskNumber - 1] + "\" }");
                System.out.println(in.readLine());
                System.out.println(in.readLine());
                System.out.println(in.readLine());
            }
        }

    }
}
