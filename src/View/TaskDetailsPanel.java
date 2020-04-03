package View;

import Controller.Controller;
import Model.TimeUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Cornelia Sköld & Hanna My Jansson
 * @version 1.0
 */

public class TaskDetailsPanel extends JPanel {
    private int taskId;
    private int width, height;
    private Controller controller;
    private JScrollPane scrollPane;

    //task title
    private JPanel titlePanel;
    private JLabel lblTitle;
    private JTextField tfTitle;

    //task interval
    private JPanel intervalPanel;
    private JLabel lblIntervalChoice;
    private JTextField tfInterval;
    private JComboBox<TimeUnit> comboBoxTimeUnits;

    //possible time
    private JLabel lblPossibleTime;
    private JPanel possibleHoursPanel;
    private JLabel lblHoursFrom;
    private JLabel lblHoursTo;
    private JComboBox<String> comboBoxHoursFrom;
    private JComboBox<String> comboBoxHoursTo;
    private JButton btnAddHourInterval;

    private JPanel datesPanel;
    private JLabel lblDatesFrom;
    private JLabel lblDatesTo;
    private JComboBox<String> comboBoxDatesFrom;
    private JComboBox<String> comboBoxDatesTo;
    private JButton btnAddDateInterval;

    private JPanel weekdaysPanel;
    private JLabel lblWeekdayPanel;
    private JCheckBox cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday, cbSunday;


    //notes
    private JLabel lblNotes;
    private JTextArea taNotes;

    //save
    private JPanel savePanel;
    private JButton btnSave;

    //last performed
    private JPanel lastPerformedPanel;
    private JLabel lblLastPerformed;
    private JButton btnSetLastPerformed;
    private JButton btnMarkAsDoneNow;
    private JComboBox<String> comboBoxLastPerformedHour;
    //private JComboBox<String> comboBoxLastPerformedMinute;
    private JComboBox<String> comboBoxLastPerformedDay;
    private JComboBox<String> comboBoxLastPerformedMonth;
    //private JComboBox<String> comboBoxLastPerformedYear;

    private JPanel panel;
    private JLabel emptyLabel = new JLabel(" ");

    public TaskDetailsPanel(Controller controller, int width, int height) {
        this.controller = controller;
        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width, height));
        //Scroll pane panels size:
        Dimension dim = new Dimension((width - 30), 25);

        //Task title panel
        lblTitle = new JLabel("  I would like to be reminded to...");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        titlePanel = new JPanel();
        tfTitle = new JTextField();
        tfTitle.setPreferredSize(new Dimension((width - 25), 25));
        titlePanel.add(tfTitle);

        // Task interval panel
        intervalPanel = new JPanel();
        lblIntervalChoice = new JLabel("I would like to do this every");
        lblIntervalChoice.setFont(new Font("Arial", Font.BOLD, 14));
        lblIntervalChoice.setPreferredSize(new Dimension(200, 25));
        tfInterval = new JTextField();
        tfInterval.setPreferredSize(new Dimension(40, 25));
        comboBoxTimeUnits = new JComboBox<>(TimeUnit.values());
        intervalPanel.add(lblIntervalChoice);
        intervalPanel.add(tfInterval);
        intervalPanel.add(comboBoxTimeUnits);

        //Possible time for execution
        lblPossibleTime = new JLabel("  I am available for this task:");
        lblPossibleTime.setFont(new Font("Arial", Font.BOLD, 14));

        //Hours
        possibleHoursPanel = new JPanel();
        lblHoursFrom = new JLabel("Hours from:");
        lblHoursTo = new JLabel("To:");
        String hours[] = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00",
                "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
        comboBoxHoursFrom = new JComboBox<>(hours);
        comboBoxHoursTo = new JComboBox<>(hours);
        btnAddHourInterval = new JButton("Add");
        possibleHoursPanel.add(lblHoursFrom);
        possibleHoursPanel.add(comboBoxHoursFrom);
        possibleHoursPanel.add(lblHoursTo);
        possibleHoursPanel.add(comboBoxHoursTo);
        possibleHoursPanel.add(btnAddHourInterval);

        //Dates
        datesPanel = new JPanel();
        lblDatesFrom = new JLabel("Date from:");
        lblDatesTo = new JLabel("To:");
        String[] dates = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        comboBoxDatesFrom = new JComboBox<>(dates);
        comboBoxDatesTo = new JComboBox<>(dates);
        btnAddDateInterval = new JButton("Add");
        datesPanel.add(lblDatesFrom);
        datesPanel.add(comboBoxDatesFrom);
        datesPanel.add(lblDatesTo);
        datesPanel.add(comboBoxDatesTo);
        datesPanel.add(btnAddDateInterval);

        lblWeekdayPanel = new JLabel("  Weekdays:");
        lblWeekdayPanel.setFont(new Font("Arial", Font.BOLD, 14));

        cbMonday = new JCheckBox("Monday");
        cbTuesday = new JCheckBox("Tuesday");
        cbWednesday = new JCheckBox("Wednesday");
        cbThursday = new JCheckBox("Thursday");
        cbFriday = new JCheckBox("Friday");
        cbSaturday = new JCheckBox("Saturday");
        cbSunday = new JCheckBox("Sunday");
        weekdaysPanel = new JPanel(new GridLayout(2, 4));
        weekdaysPanel.add(cbMonday);
        weekdaysPanel.add(cbTuesday);
        weekdaysPanel.add(cbWednesday);
        weekdaysPanel.add(cbThursday);
        weekdaysPanel.add(cbFriday);
        weekdaysPanel.add(cbSaturday);
        weekdaysPanel.add(cbSunday);

        //Notes
        lblNotes = new JLabel("  Notes:");
        lblNotes.setFont(new Font("Arial", Font.BOLD, 14));
        taNotes = new JTextArea();

        //last performed
        lblLastPerformed = new JLabel("  Last time I did this was: ");
        lblLastPerformed.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel time = new JLabel("Or set time: ");
        JLabel date = new JLabel("date: ");
        comboBoxLastPerformedHour = new JComboBox<>(hours);
        comboBoxLastPerformedDay = new JComboBox<>(dates);
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        comboBoxLastPerformedMonth = new JComboBox<>(months);
        btnSetLastPerformed = new JButton("Set");
        btnMarkAsDoneNow = new JButton("Just now");
        lastPerformedPanel = new JPanel();
        lastPerformedPanel.add(time);
        lastPerformedPanel.add(comboBoxLastPerformedHour);
        lastPerformedPanel.add(date);
        lastPerformedPanel.add(comboBoxLastPerformedDay);
        lastPerformedPanel.add(comboBoxLastPerformedMonth);
        lastPerformedPanel.add(btnSetLastPerformed);
        initializeLastPerformed();

        savePanel = new JPanel();
        btnSave = new JButton("Save task!");
        btnSave.addActionListener(new ButtonActionListener());
        savePanel.add(btnSave);

        panel = new JPanel(new GridLayout(18, 1));
        panel.setPreferredSize(new Dimension(width - 30, height));
        panel.add(lblTitle);
        panel.add(titlePanel);
        panel.add(emptyLabel);
        panel.add(intervalPanel);
        panel.add(emptyLabel);
        panel.add(lblPossibleTime);
        panel.add(possibleHoursPanel);
        panel.add(emptyLabel);
        panel.add(datesPanel);
        panel.add(lblWeekdayPanel);
        panel.add(weekdaysPanel);
        panel.add(emptyLabel);
        panel.add(lblNotes);
        panel.add(taNotes);
        panel.add(lblLastPerformed);
        panel.add(btnMarkAsDoneNow);
        panel.add(lastPerformedPanel);
        panel.add(emptyLabel);
        panel.add(savePanel);

        scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(width, height));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }

    public void initializeLastPerformed(){
        Calendar cal = Calendar.getInstance();
        comboBoxLastPerformedHour.setSelectedIndex(cal.get(Calendar.HOUR_OF_DAY));
        comboBoxLastPerformedDay.setSelectedIndex(cal.get(Calendar.DAY_OF_MONTH) -1);
        comboBoxLastPerformedMonth.setSelectedIndex(cal.get(Calendar.MONTH));
    }

    public void setTaskTitle(String title) {
        tfTitle.setText(title);
    }

    public String getTaskTitle() {
        return tfTitle.getText();
    }


    public String getIntervalAmount() {
        return tfInterval.getText();
    }

    public TimeUnit getIntervalUnit() {

        TimeUnit timeUnit = null;
        switch (comboBoxTimeUnits.getSelectedItem().toString()) {
            case "hour":
                timeUnit = TimeUnit.hour;
                break;
            case "day":
                timeUnit = TimeUnit.day;
                break;
            case "week":
                timeUnit = TimeUnit.week;
                break;
            case "month":
                timeUnit = TimeUnit.month;
                break;
            case "year":
                timeUnit = TimeUnit.year;
                break;

        }
        System.out.println("Timeunit:" + timeUnit.toString());
        return timeUnit;

    }

    public void setTaskInterval(int amount, TimeUnit timeUnit) {
        tfInterval.setText(amount + "");
        comboBoxTimeUnits.setSelectedItem(timeUnit);
    }


    public void setPossibleHours(LocalTime from, LocalTime to) {
        System.out.println("TaskDetailsPanel - set possibleHours: " + from.getHour() + " - " + to.getHour());
        int fromInt = from.getHour();
        int toInt = to.getHour();

        comboBoxHoursFrom.setSelectedIndex(fromInt);
        comboBoxHoursTo.setSelectedIndex(toInt);

    }

    public LocalTime[] getPossibleHours() {
        LocalTime[] hours = new LocalTime[2];

        int hourFrom = comboBoxHoursFrom.getSelectedIndex();
        int hourTo = comboBoxHoursTo.getSelectedIndex();

        hours[0] = LocalTime.of(hourFrom, 0);
        hours[1] = LocalTime.of(hourTo, 0);

        return hours;
    }

    public void setPossibleDates(boolean[] dates) {
        int from = -1;
        int to = -1;


        for (int i = 0; i < dates.length; i++) {
            if (dates[i] == true && from == -1) {
                from = i;
            }
            if (dates[i] == false && from != -1) {
                to = i;
                break;
            }

        }
        comboBoxDatesFrom.setSelectedIndex(from);
        comboBoxDatesTo.setSelectedIndex(to);

    }

    public boolean[] getPossibleDates() {
        int from = comboBoxDatesFrom.getSelectedIndex();
        int to = comboBoxDatesTo.getSelectedIndex();


        boolean[] dates = new boolean[31];


        for (int i = 0; i < dates.length; i++) {
            if (i >= from && i < to) {
                dates[i] = true;
            }
        }
        return dates;
    }

    public void setPossibleWeekdays(boolean[] possibleWeekdays) {
        cbMonday.setSelected(possibleWeekdays[0]);
        cbTuesday.setSelected(possibleWeekdays[1]);
        cbWednesday.setSelected(possibleWeekdays[2]);
        cbThursday.setSelected(possibleWeekdays[3]);
        cbFriday.setSelected(possibleWeekdays[4]);
        cbSaturday.setSelected(possibleWeekdays[5]);
        cbSunday.setSelected(possibleWeekdays[6]);
    }

    public boolean[] getPossibleWeekdays() {
        boolean[] possibleWeekdays = new boolean[7];
        possibleWeekdays[0] = cbMonday.isSelected();
        possibleWeekdays[1] = cbTuesday.isSelected();
        possibleWeekdays[2] = cbWednesday.isSelected();
        possibleWeekdays[3] = cbThursday.isSelected();
        possibleWeekdays[4] = cbFriday.isSelected();
        possibleWeekdays[5] = cbSaturday.isSelected();
        possibleWeekdays[6] = cbSaturday.isSelected();

        return possibleWeekdays;
    }

    public void setNotes(String notes) {
        taNotes.setText(notes);
    }

    public String getNotes() {
        return taNotes.getText();
    }

    public void setLastPerformed(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        comboBoxLastPerformedHour.setSelectedIndex(cal.get(Calendar.HOUR_OF_DAY));
        comboBoxLastPerformedDay.setSelectedIndex(cal.get(Calendar.DAY_OF_MONTH) -1);
        comboBoxLastPerformedMonth.setSelectedIndex(cal.get(Calendar.MONTH));
    }

    public void setDefaultFields() {
        tfTitle.setText("");
        tfInterval.setText("");
        comboBoxTimeUnits.setSelectedIndex(0);
        comboBoxHoursFrom.setSelectedIndex(0);
        comboBoxHoursTo.setSelectedIndex(comboBoxHoursTo.getItemCount() - 1);
        comboBoxDatesFrom.setSelectedIndex(0);
        comboBoxDatesTo.setSelectedIndex(comboBoxDatesTo.getItemCount() - 1);
        if (!cbMonday.isSelected()) {
            cbMonday.doClick();
        }
        if (!cbTuesday.isSelected()) {
            cbTuesday.doClick();
        }
        if (!cbWednesday.isSelected()) {
            cbWednesday.doClick();
        }
        if (!cbThursday.isSelected()) {
            cbThursday.doClick();
        }
        if (!cbFriday.isSelected()) {
            cbFriday.doClick();
        }
        if (!cbSaturday.isSelected()) {
            cbSaturday.doClick();
        }
        if (!cbSunday.isSelected()) {
            cbSunday.doClick();
        }
        taNotes.setText("");

    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    private class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnSave) {
                controller.buttonPressed(ButtonType.SAVE);
            }
            if (e.getSource() == btnMarkAsDoneNow){
                controller.markTaskAsDone(taskId);
            }
            if (e.getSource() == btnSetLastPerformed){
                int hour = comboBoxLastPerformedHour.getSelectedIndex();
                int day = comboBoxLastPerformedDay.getSelectedIndex() + 1;
                int month = comboBoxLastPerformedMonth.getSelectedIndex();
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, hour);
                cal.set(Calendar.DAY_OF_MONTH, day);
                cal.set(Calendar.MONTH, month);
                Date date = cal.getTime();
                controller.setLastPerformed(taskId, date);
            }
        }
    }
}
