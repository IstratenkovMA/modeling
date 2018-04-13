package solutions.task;


import solutions.Task;
import view.Frame;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1.1. Дифференциальные уравнения первого порядка
 * <p>
 * Дифференциальное уравнение Мещерского
 определяет зависимость скорости V движения ракеты от изменения ее массы
 x . Здесь u – относительная скорость, с которой движется отделяющаяся от ра-
 кеты часть ее массы. Будем считать, что масса ракеты уменьшается с постоян-
 ной скоростью и равна 180 . кг с Правая часть уравнения равна силе тяги ракет-
 ного двигателя F = 583000H
 r
 . Пусть начальная скорость ракеты V 4453 м с 0 = ,
 начальная масса ракеты M = 66500 кг , конечная масса ракеты m = 23500 кг,
 10
 начальный момент времени t 337 c 0 = . Шаг времени моделирования h поло-
 жить равным 1 секунде.
 Определить момент времени и скорость движения ракеты, когда ее масса
 станет равной m.

 */
public class Task1 extends JFrame implements Task {

    private static final int windowHeight = 530;
    private static final int windowWidth = 500;

    private static final int STARTING_MASS = 66500;
    private static final int FINAL_MASS = 23500;

    private static final int STARTING_TIME = 337;
    private static final int STARTING_VELOCITY = 4453;

    private static final int DELTA_M = 120;
    private static final int DELTA_T = 1;

    private static final int F = 583000;



    private Map<Double, Double> coordinates = new TreeMap<>();
    private Map<Double, Double> succeedPoints = new TreeMap<>();

    @Override
    public Task solve() {
        int time = STARTING_TIME;
        double currentMass = STARTING_MASS;
        double currentVelosity = STARTING_VELOCITY;


        while (currentMass >= FINAL_MASS) {
            System.out.println("Current time: " + time + " Current velocity: " + currentVelosity);
            coordinates.put(currentVelosity, currentMass);
            currentVelosity += DELTA_T * deltaVelocity(currentMass);
            currentMass -= DELTA_M;
            time += DELTA_T;
        }
        System.out.println("End with mass: " + currentMass);
        return this;
    }

    private Double deltaVelocity(Double currentMass) {
        return F * DELTA_T / currentMass;
    }

    @Override
    public void draw() {
        Frame.draw(this, new DrawingComponent(), windowHeight, windowWidth);
    }

    class DrawingComponent extends JPanel {

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics;

            graphics2D.drawPolyline(new int[]{0, 500, 495, 500, 495}, new int[]{500, 500, 495, 500, 505}, 5); // x
            graphics2D.drawPolyline(new int[]{5, 5, 0, 5, 10}, new int[]{508, 0, 5, 0, 5}, 5); //y

            graphics2D.drawPolyline(generatePoints(coordinates.keySet(), 0),
                    generatePoints(coordinates.values(), windowHeight),
                    coordinates.keySet().size());

            int[] yPoints = generatePoints(succeedPoints.values(), windowHeight);
            for (int yPoint : yPoints) {
                graphics2D.drawPolyline(new int[]{5, 500},
                        new int[]{yPoint, yPoint},
                        2);
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
            int point = new Double(value * 3).intValue() + 5;
            if (!offset.equals(0)) {
                point = offset - point;
            }
            return point;
        }
    }
}