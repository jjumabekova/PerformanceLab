package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        try {
            Scanner circleScanner = new Scanner(new File("src/task2/circle.txt"));
            double centerX = circleScanner.nextDouble();
            double centerY = circleScanner.nextDouble();
            double radius = circleScanner.nextDouble();
            circleScanner.close();

            Scanner pointScanner = new Scanner(new File("src/task2/points.txt"));
            while (pointScanner.hasNextDouble()) {
                double pointX = pointScanner.nextDouble();
                double pointY = pointScanner.nextDouble();

                double distance = Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));

                if (distance < radius) {
                    System.out.println("0"); // Точка внутри окружности
                } else if (distance > radius) {
                    System.out.println("2"); // Точка вне окружности
                } else {
                    System.out.println("1"); // Точка на окружности
                }
            }
            pointScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        }
    }
}
