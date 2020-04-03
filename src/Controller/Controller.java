package Controller;

import java.time.LocalTime;

import View.MainFrame;
import View.ButtonType;
import Model.*;

// Hej från oss i Malmö!

/**
 * @author Hanna My Jansson
 * @version 1.0
 */

public class Controller {
    private MainFrame frame;
    private TaskRegister taskRegister;

    public Controller() {
        frame = new MainFrame(this);
        taskRegister = new TaskRegister();
        UpdateThread updateThread = new UpdateThread(this);
        updateThread.start();
    }


    public void buttonPressed(ButtonType button) {
        switch (button) {
            case TASKS:
                frame.setCard("1");
                break;
            case ADD:
                frame.setCard("2");
                frame.setDefaultFields();

                break;
            case DELETE:
                //frame.setCard("3");
                break;
            case CALENDAR:
                //frame.setCard("4");
                break;
            case STATISTICS:
                //frame.setCard("5");


                break;

            case MARKASDONE:


                break;
            case SAVE:
                String title = frame.getTaskTitle();
                String info = frame.getNotes();

                //preferredIntervall
                int intervalAmount = frame.getIntervalAmount();
                TimeUnit timeUnit = frame.getIntervalUnit();
                System.out.println("Controller - intervall: " + intervalAmount + " " + timeUnit);
                TimeSpan preferredInterval = new TimeSpan(intervalAmount, timeUnit);


                Task newTask = new Task(title, info, preferredInterval);

                PossibleTime possibleTime = new PossibleTime();
                possibleTime.setPossibleWeekDays(frame.getPossibleWeekdays());
                possibleTime.setPossibleDates(frame.getPossibleDates());

                LocalTime[] localTimes = frame.getPossibleHours();
                System.out.println("Controller - save: possiblehour: " + localTimes[0] + " - " + localTimes[1]);
                possibleTime.setPossibleHours(localTimes[0], localTimes[1]);

                newTask.setPossibleTimeForExecution(possibleTime);

                taskRegister.addTask(newTask);
                System.out.println("Task added, Title: " + title + "possible hours: " + newTask.getPossibleTimeForExecution().getPossibleHours().toString());
                frame.addTask(newTask.getTitle(), newTask.getTimeUntil(), newTask.getTimeUnit(), newTask.getId());
                frame.setCard("1");
                break;


        }


    }

    public void markTaskAsDone(int taskId) {
        Task task = taskRegister.getTaskWithId(taskId);
        System.out.println("Task id: " + taskId);
        task.markAsDoneNow();
        System.out.println("Last performed: " + task.getLastPerformed().toString());
        updateGUI();
    }

    public void openTask(int taskId) {
        Task task = taskRegister.getTaskWithId(taskId);

        frame.setTaskInterval(task.getPreferredInterval().getTime(), task.getTimeUnit());
        frame.setTaskTitle(task.getTitle());
        frame.setNotes(task.getInfo());
        frame.setPossibleDates(task.getPossibleDates());
        frame.setPossibleWeekdays(task.getPossibleWeekdays());
        TimeInterval timeInterval = task.getPossibleTimeInterval();
        if (timeInterval != null)
            frame.setPossibleHours(timeInterval.getFrom(), timeInterval.getTo());
        frame.setCard("2");
    }

    public void updateGUI() {
        frame.updateAllTasks();

    }


    public String getTaskTitle(int id){
        return taskRegister.getTaskWithId(id).getTitle();
    }

    public int getTaskTimeRemaining(int id){
        return taskRegister.getTaskWithId(id).getTimeUntil();
    }
    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
