package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.AttributedCharacterIterator;
import Model.TimeUnit;

/**
 * @author Cornelia Sk�ld
 * @version 1.0
 */
public class SingleTaskPanel extends JPanel{
    private JLabel lblTask;
    private JButton btnDone;
    private JButton btnOpen;
    private String title;
    private TimeUnit timeUnit;
    private int taskId;
    private ButtonActionListener btnListener;

    public SingleTaskPanel(String title, int timeRemaining, TimeUnit timeUnit) {
        this.title = title;
        this.timeUnit = timeUnit;

        setPreferredSize(new Dimension(380, 60));
        String text = generateText(title, timeRemaining);
        lblTask = new JLabel(text);
        lblTask.setPreferredSize(new Dimension(320, 25));
        lblTask.setFont(new Font("Arial", Font.BOLD, 15));
        btnDone = new JButton("Mark as done");
        btnOpen = new JButton("Open Task");
        btnListener = new ButtonActionListener();
        btnDone.addActionListener(btnListener);
        btnOpen.addActionListener(btnListener);
        add(lblTask);
        add(btnDone);
        add(btnOpen);

    }

    private String generateText(String title, int timeRemaining) {

        if (title == null)
            title = this.title;
        String text;

        if (timeRemaining > 1)
            text = timeRemaining + " "+timeUnit.toString()+"s remaining to: " + title;
        else if(timeRemaining == 1)
            text = timeRemaining + " "+timeUnit.toString()+ " remaining to: " + title;
        else if (timeRemaining == 0)
            text = "Now its time to: " + title;
        else if(timeRemaining == -1)
            text = Math.abs(timeRemaining) + " "+timeUnit.toString()+" LATE to: " + title;
        else
            text = Math.abs(timeRemaining) + " "+timeUnit.toString()+"s LATE to: " + title;
        return text;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void updateDaysRemaining(int timeRemaining) {
        String text = generateText(null, timeRemaining);
        lblTask.setText(text);
    }

    private class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnDone) {

            }
            else if (e.getSource() == btnOpen)
            {

            }
        }
    }

}
