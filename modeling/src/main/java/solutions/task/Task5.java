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

    Random random = new Random();
    private static final Integer COUNT = 1000;

    @Override
    public Task solve() {
        List<Boolean> puasson1 = new ArrayList<>();
        List<Boolean> puasson2 = new ArrayList<>();
        for(int index = 0; index < COUNT; index++) {
            puasson1.add(random.nextBoolean());
            puasson2.add(random.nextBoolean());
        }

        List<Boolean> puasson = new ArrayList<>();
        for(int index = 0; index < COUNT; index++) {
            puasson.add(puasson1.get(index) || puasson2.get(index));
        }

        System.out.println(function(puasson));

        return this;
    }

    int function(List<Boolean> puasson) {
        int maxInterval = 0;
        int currentInterval = 0;
        
        for (Boolean piece : puasson) {
            if(piece) {
                if(currentInterval > maxInterval)
                    maxInterval = currentInterval;
                currentInterval = 0;
            } else {
                currentInterval++;
            }
        }
        
        return maxInterval;
    }

    @Override
    public void draw() {
        throw new RuntimeException("There is nothing to draw for this task");
    }
}