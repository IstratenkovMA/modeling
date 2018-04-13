package solutions.task;

import solutions.Task;
import view.Frame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 1.2. Системы дифференциальных уравнений
 * <p>
 * Задача 19. Уравнение Дуффинга: аттрактор Холмса
 * <p>
 * dx/dt = y,
 * dy/dt = -ay + 0,5x(1-x*x) + bcos(wt)
 * <p>
 * при a = 0,15 w = 0,8 0,1<=b<=0,3. Эксперимент повторить при a > 0 .
 */
public class Task2 extends JFrame implements Task {

    private static final int windowHeight = 600;

    private static final int windowWidth = 550;

    private static final double a = 0.15;
    private static final double w = 0.8;

    private List<List<Double>> xCoordinates = new ArrayList<>();

    private List<List<Double>> yCoordinates = new ArrayList<>();



    @Override
    public Task solve() {
        double[] bs = {0.1, 0.15, 0.3};

        for (int index = 0; index < bs.length; index++) {
            double time = 0;

            double x = 1;
            double y = 1;

            List<Double> xInnerCoordinates = new ArrayList<>();
            List<Double> yInnerCoordinates = new ArrayList<>();

            while (time < 40) {

                double newX = x + 0.1 * function(y);
                double newY = y + 0.1 * gunction(x, y, bs[index], time);

                System.out.println("x : " + newX + " - y : " + newY);

                x = newX;
                y = newY;

                xInnerCoordinates.add(x * 100 + (windowWidth / 2) );
                yInnerCoordinates.add(y * 100 + (windowHeight / 2));

                time += 0.1;
            }

            xCoordinates.add(xInnerCoordinates);
            yCoordinates.add(yInnerCoordinates);
        }

        return this;
    }

    private Double function(double y) {
        return y;
    }

    private Double gunction(double x, double y, double b, double t) {
        return -1 * a * y + 0.5 * x * (1 - x * x) + b * Math.cos(w * t);
    }

    @Override
    public void draw() {
        Frame.draw(this, new DrawingComponent(), windowHeight, windowWidth);
    }

    class DrawingComponent extends JPanel {

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics;

            for (int index = 0; index < xCoordinates.size(); index++) {
                graphics2D.drawPolyline(generatePoints(xCoordinates.get(index), 0),
                        generatePoints(yCoordinates.get(index), windowHeight),
                        xCoordinates.get(index).size());
            }
        }

        private int[] generatePoints(Collection<Double> collection, Integer offset) {
            int index = 0;
            int[] coordinates = new int[collection.size()];
            for (Double value : collection) {
                coordinates[index++] = generatePoint(value, offset);
            }
            return coordinates;
        }

        private int generatePoint(Double value, Integer offset) {
            int point = value.intValue();
            if (!offset.equals(0)) {
                point = offset - point;
            }
            return point;
        }
    }
}