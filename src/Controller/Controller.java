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
    private int taskId;

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
                frame.setSelectedTaskId(0);
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
                Task task = null;
                int selectedTaskId = frame.getSelectedTaskId();

                String title = frame.getTaskTitle();
                String info = frame.getNotes();

                //preferredIntervall
                int intervalAmount = frame.getIntervalAmount();
                TimeUnit timeUnit = frame.getIntervalUnit();
                System.out.println("Controller - intervall: " + intervalAmount + " " + timeUnit);
                TimeSpan preferredInterval = new TimeSpan(intervalAmount, timeUnit);

                PossibleTime possibleTime = new PossibleTime();
                possibleTime.setPossibleWeekDays(frame.getPossibleWeekdays());
                possibleTime.setPossibleDates(frame.getPossibleDates());
                LocalTime[] localTimes = frame.getPossibleHours();
                possibleTime.setPossibleHours(localTimes[0], localTimes[1]);

                if (selectedTaskId <= 0) {
                    task = new Task(title, info, preferredInterval);
                    task.setPossibleTimeForExecution(possibleTime);
                    taskRegister.addTask(task);
                    frame.addTask(task.getTitle(), task.getTimeUntil(), task.getTimeUnit(), task.getId());

                } else {
                    task = taskRegister.getTaskWithId(selectedTaskId);
                    task.setInfo(info);
                    task.setTitle(title);
                    task.setPreferredInterval(preferredInterval);
                    task.setPossibleTimeForExecution(possibleTime);

                }
                updateGUI();
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
        frame.setSelectedTaskId(taskId);
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


    public String getTaskTitle(int id) {
        return taskRegister.getTaskWithId(id).getTitle();
    }

    public int getTaskTimeRemaining(int id) {
        return taskRegister.getTaskWithId(id).getTimeUntil();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
