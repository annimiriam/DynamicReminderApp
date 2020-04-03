package Controller;

import Model.*;
import View.ButtonType;
import View.MainFrame;

import javax.swing.*;
import java.time.LocalTime;
import java.util.Date;

// Hej från oss i Malmö!

/**
 * @author Hanna My Jansson
 * @version 1.0
 */

public class Controller {
    private MainFrame frame;
    private TaskRegister taskRegister;
    private FileHandler fileHandler;

    public Controller() {
        frame = new MainFrame(this);
        taskRegister = new TaskRegister();
        UpdateThread updateThread = new UpdateThread(this);
        updateThread.start();
        fileHandler = new FileHandler(taskRegister);

        loadTasksToGUI();
    }


    public void buttonPressed(ButtonType button) {
        switch (button) {
            case TASKS:
                frame.setCard("1");
                break;
            case ADD:

                frame.setDefaultFields();
                frame.setSelectedTaskId(0);
                frame.setCard("2");
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
                int intervalAmount = 0;
                try {
                    intervalAmount = Integer.parseInt(frame.getIntervalAmount());
                }catch (Exception e){
                    JOptionPane.showMessageDialog(frame, "You need to add a preferred interval as a number");
                    break;
                }

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
                fileHandler.saveToFile(); //todo flytta! Just nu sparar den bara 1? tror jag
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

    public void setLastPerformed(int taskId, Date date){
        Task task = taskRegister.getTaskWithId(taskId);
        task.setLastPerformed(date);
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

    public void loadTasksToGUI(){
        int size = taskRegister.getBiggestID();
        for(int i =1; i<size;i++){
           Task task =  taskRegister.getTaskWithId(i);
           if(task!=null){
                frame.addTask(task.getTitle(), task.getTimeUntil(), task.getTimeUnit(), task.getId());
            }

        }

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
