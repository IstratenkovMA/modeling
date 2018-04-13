package solutions.task;

import solutions.Task;

import javax.swing.*;
import java.util.*;

/**
 * 2.2. Нормально распределенная случайная величина
 * <p>
 * Задача 7. Ученик выходит из дома в школу за 40 минут до начала уроков.
 Длительность интервала времени перехода от дома до остановки трамвая есть
 нормально распределенная случайная величина с параметрами 10 µ1 = мин.
 и 1 σ 1 = мин. Длительность интервала времени от момента ожидания трамвая,
 последующей поездки и до момента выхода из трамвая есть нормально распре-
 деленная случайная величина с параметрами 20 µ 2 = мин. и 5 σ 2 = мин. Дли-
 тельность интервала времени перехода от остановки трамвая до школы есть
 нормально распределенная случайная величина с параметрами 5 µ3 = мин.
 и 1 σ 3 = мин. Построить модель определения времени перехода ученика от до-
 ма до школы. На основании 1000 испытаний с моделью оценить вероятность
 того, что ученик опоздает к началу уроков.
 */
public class Task4 extends JFrame implements Task {

    private static final HashMap<Integer, Integer> STAGES = new HashMap<>();

    private static Random random = new Random();

    static {
        STAGES.put(10, 1);
        STAGES.put(20, 5);
        STAGES.put(5, 1);
    }

    private List<Double> data = new ArrayList<>();

    @Override
    public Task solve() {
        for(int index = 0; index < 1000; index++) {
            double time = 0;
            for (Map.Entry<Integer, Integer> stage : STAGES.entrySet()) {
                time += calculate(stage.getKey(), stage.getValue());
            }
            data.add(time);
        }
        System.out.println("Chances to late to school: " + (double)data.stream().mapToInt(time -> time>40?1:0).sum() / data.size());
        return this;
    }

    private double calculate(Integer mue, Integer sigma) {
        return mue + random.nextDouble() * sigma;
    }

    @Override
    public void draw() {
        throw new RuntimeException("There is nothing to draw for this task");
    }
}