package View;

import javax.swing.*;
import java.awt.*;
import Controller.*;
import Model.TimeUnit;

/**
 * @author Cornelia Sköld & Hanna My Jansson
 * @version 1.0
 */
public class TaskOverviewPanel extends JPanel{
    private int width, height;
    private Controller controller;
    private final static int MAX_TASKS= 30 ;

    private SingleTaskPanel[] singleTaskPanels;
    private int index;



    public TaskOverviewPanel(Controller controller, int width, int height) {
        this.controller = controller;
        this.width = width;
        this.height = height;
        singleTaskPanels = new SingleTaskPanel[MAX_TASKS];
        index = 0;
        setPreferredSize(new Dimension(width,height+1000));

    }

    public void addTask(String title, int timeRemaining, TimeUnit timeUnit, int taskId) {

        singleTaskPanels[index] = new SingleTaskPanel(title, timeRemaining, timeUnit, controller);
        singleTaskPanels[index].setTaskId(taskId);
        add(singleTaskPanels[index]);
        index++;
    }

    public void clearTaskList(){
        singleTaskPanels = new SingleTaskPanel[MAX_TASKS];
        removeAll();
    }


    public void updateAllTasks(){

            for (int i = 0; i < singleTaskPanels.length; i++)
                if(singleTaskPanels[i] != null) {
                    singleTaskPanels[i].updateTask();
                }
    }
}
