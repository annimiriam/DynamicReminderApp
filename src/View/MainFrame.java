package View;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

import Controller.*;
import Model.TimeUnit;

/**
 * @author Cornelia Sköld & Hanna My Jansson
 * @version 1.0
 */
public class MainFrame extends JFrame {
    private int width = 350;
    private int height = 700;
    private MainPanel panel;
    private Controller controller;

    public MainFrame(Controller controller) {
        this.controller = controller;

        final int offsetX = width;
        final int offsetY = height / 10;
        setLocation(offsetX, offsetY);

        setSize(new Dimension(width, height));
        setTitle("Dynamic Reminder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new MainPanel(controller, width, height);
        setContentPane(panel);
        pack();
        setVisible(true);
    }
    public void setCard(String cardNbr){
        panel.setCard(cardNbr);
    }


    public String getTaskTitle(){
        return panel.getTaskDetailsPanel().getTaskTitle();
    }

    public void setTaskTitle(String title){
        panel.getTaskDetailsPanel().setTaskTitle(title);
    }

    public int getIntervalAmount(){
        return panel.getTaskDetailsPanel().getIntervalAmount();
    }


    public TimeUnit getIntervalUnit(){
        return panel.getTaskDetailsPanel().getIntervalUnit();
    }

    public void setTaskInterval(int amount, TimeUnit unit){
        panel.getTaskDetailsPanel().setTaskInterval(amount, unit);
    }

    public LocalTime[] getPossibleHours(){
        return panel.getTaskDetailsPanel().getPossibleHours();
    }

    public void setPossibleHours(LocalTime from, LocalTime to){
        panel.getTaskDetailsPanel().setPossibleHours(from, to);
    }

    public boolean[] getPossibleDates(){
        return panel.getTaskDetailsPanel().getPossibleDates();
    }

    public void setPossibleDates(boolean[] dates){
        panel.getTaskDetailsPanel().setPossibleDates(dates);
    }

    public boolean[] getPossibleWeekdays(){
        return panel.getTaskDetailsPanel().getPossibleWeekdays();
    }

    public void setPossibleWeekdays(boolean[] possibleWeekdays){
        panel.getTaskDetailsPanel().setPossibleWeekdays(possibleWeekdays);
    }

    public String getNotes(){
        return panel.getTaskDetailsPanel().getNotes();
    }

    public void setNotes(String notes){
        panel.getTaskDetailsPanel().setNotes(notes);
    }

    public void addTask(String title, int timeRemaining, TimeUnit timeUnit, int taskId){
        panel.getTaskOverviewPanel().addTask(title,timeRemaining, timeUnit, taskId);
    }

    //TODO uppdate metod för alla singlepanels i overveiw

}
