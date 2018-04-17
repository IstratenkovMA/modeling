package solutions.task;

import solutions.Task;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 2.3. Экспоненциально распределенная случайная величина
 * <p>
 * Задача 7. Построить модель процесса слияния двух пуассоновских пото-
 ков (с параметрами соответственно λ1 и λ2
 ) частиц в один поток. Построить
 выборочную функцию плотности распределения длительности интервала вре-
 мени между двумя частицами общего потока.

 */
public class Task5 extends JFrame implements Task {

    private static final double LAMBDA1 = 0.2;
    private static final double LAMBDA2 = 0.3;

    Random random = new Random();
    private static final Integer COUNT = 1000;

    @Override
    public Task solve() {
        List<Double> puasson1 = generatePuassonStream(LAMBDA1);
        List<Double> puasson2 = generatePuassonStream(LAMBDA2);

        List<Double> puasson = mergePuassons(puasson1, puasson2);


        System.out.println(function(puasson));

        return this;
    }

    private List<Double> mergePuassons(List<Double> first, List<Double> second) {
        List<Double> puasson = new ArrayList<>(2001);
        for (int index = 0; index < COUNT; index++) {
            puasson.add(first.get(index));
            puasson.add(second.get(index));
        }
        return puasson;
    }

    private double function(List<Double> puasson) {
        double maxInterval = 0.0;
        for(int index = 1; index < puasson.size(); index++) {
            double currentInterval = puasson.get(index) - puasson.get(index - 1);
            if(maxInterval < currentInterval) {
                maxInterval = currentInterval;
            }
        }
        
        return maxInterval;
    }

    List<Double> generatePuassonStream(Double lambda) {
        List<Double> result = new ArrayList<>();
        result.add(0.0);
        for(int index = 1; index < COUNT; index++) {
            result.add(result.get(index - 1) + -1d/lambda*Math.log(random.nextDouble()));
        }
        return result;
    }

    @Override
    public void draw() {
        throw new RuntimeException("There is nothing to draw for this task");
    }
}