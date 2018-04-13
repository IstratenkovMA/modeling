package solutions.task;

import solutions.Task;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 2.1. Равномерно распределенная случайная величина
 * <p>
 *  В корзине находятся 3 белых, 3 синих и 3 красных шара. По-
 следовательно выбираем шары из корзины без возвращения. Построить модель
 процесса выбора шаров из корзины. Оценить вероятность того, что первым бу-
 дет выбран белый, вторым – синий, а третьим – красный шар. Оценку провести
 на основании 1000 испытаний.
 */
public class Task3 extends JFrame implements Task {


    private List<List<Integer>> data = new ArrayList<>();

    @Override
    public Task solve() {
        //0-white 1-blue 2-red
        for (int index = 0; index < 1000; index++) {
            ArrayList<Integer> experiment = new ArrayList<>(10);
            while (experiment.size() < 9) {
                Integer ball = new Random().nextInt(3);
                if(isContainsBall(experiment, ball)) {
                    experiment.add(ball);
                }
            }
            data.add(experiment);
        }

        double expectedMean = (double) data.stream()
                .mapToInt(list -> (list.get(0) == 0 && list.get(1) == 1 && list.get(2) == 2)?1:0)
                .sum() / data.size();

        System.out.println("Expected mean is " + expectedMean);
        return this;
    }

    private boolean isContainsBall(ArrayList<Integer> list, int ballNum) {
        Integer finded = 0;
        for (Integer ball : list) {
            if(ballNum == ball) {
                finded++;
            }
        }
        return finded < 3;
    }

    @Override
    public void draw() {
        throw new RuntimeException("There is nothing to draw for this task");
    }
}