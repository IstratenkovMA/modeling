package solutions;

import solutions.task.*;
import solutions.task.task6.Task6;
import solutions.task.task7.Task7;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        System.out.print("Enter task number: ");
        String code = new Scanner(System.in).next();
        switch (code) {
            case "1":
                new Task1().solve();
                break;
            case "2":
                new Task2().solve().draw();
                break;
            case "3":
                new Task3().solve();
                break;
            case "4":
                new Task4().solve();
                break;
            case "5":
                new Task5().solve();
                break;
            case "6":
                new Task6().solve();
                break;
            case "7":
                new Task7().solve();
                break;
            default:
                throw new RuntimeException("Error task number");
        }
    }
}
