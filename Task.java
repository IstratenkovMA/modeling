package solutions.task.task7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task implements Comparable<Task> {

    private double arriveTime;

    private double startResolveTime;

    private double finishResolveTime;

    private boolean isReturned = false;

    private Map<Double, Double> timeWorking = new HashMap<>();

    private List<Double> timeWaiting = new ArrayList<>();

    public Task(double arriveTime) {
        this.arriveTime = arriveTime;
    }

    public void setArriveTime(double arriveTime) {
        timeWaiting.add(arriveTime);
        this.arriveTime = arriveTime;
    }

    public double getArriveTime() {
        return arriveTime;
    }

    public void setStartResolveTime(double startResolveTime) {
        this.startResolveTime = startResolveTime;
//        timeWorking.put(startResolveTime, finishResolveTime);
    }

    public double getStartResolveTime() {
        return startResolveTime;
    }

    public void setFinishResolveTime(double finishResolveTime) {
        timeWorking.put(startResolveTime, finishResolveTime);
        this.finishResolveTime = finishResolveTime;
    }

    public double getFinishResolveTime() {
        return finishResolveTime;
    }

    public double getSummaryWaiting() {
        Double time = 0.0;
        for (Double arrive : timeWaiting) {
            time += arrive;
        }
        return time;
    }

    public double getSummaryTime() {
        Double time = 0.0;
        for (Double startingTime : timeWorking.keySet()) {
            time += timeWorking.get(startingTime) - startingTime;
        }
        return time;
    }

    public void markReturned() {
        isReturned = true;
    }

    public boolean isReturned() {
        return isReturned;
    }

    @Override
    public int compareTo(Task task) {
        return Double.compare(this.getArriveTime(), task.getArriveTime());
    }
}