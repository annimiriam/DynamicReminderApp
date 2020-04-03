package View;

import javax.swing.*;
import java.awt.*;
import Controller.*;
import Model.TimeUnit;

/**
 * @author Cornelia Sk�ld & Hanna My Jansson
 * @version 1.0
 */
public class TaskOverviewPanel extends JPanel{
    private int width, height;
    private Controller controller;

    private SingleTaskPanel[] singleTaskPanels;
    private int index;



    public TaskOverviewPanel(Controller controller, int width, int height) {
        this.controller = controller;
        this.width = width;
        this.height = height;
        singleTaskPanels = new SingleTaskPanel[30];
        index = 0;
        setPreferredSize(new Dimension(width,height+1000));

    }

    public void addTask(String title, int timeRemaining, TimeUnit timeUnit, int taskId) {

        singleTaskPanels[index] = new SingleTaskPanel(title, timeRemaining, timeUnit, controller);
        singleTaskPanels[index].setTaskId(taskId);
        add(singleTaskPanels[index]);
        index++;
    }
}
