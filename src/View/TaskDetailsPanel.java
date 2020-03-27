package View;

import Controller.Controller;
import Model.TimeUnit;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Cornelia Sköld & Hanna My Jansson
 * @version 1.0
 */

public class TaskDetailsPanel extends JPanel {
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
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24","25","26","27","28","29","30","31"};
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
        panel.add(emptyLabel);
        panel.add(savePanel);

        scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(width, height));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }

    public void setTaskTitle(String title){
        tfTitle.setText(title);
    }

    public String getTaskTitle(){
        return tfTitle.getText();
    }

    public void setTaskInterval(int amount, TimeUnit timeUnit ){
          tfInterval.setText(String.valueOf(amount));
          comboBoxTimeUnits.setSelectedItem(timeUnit);
    }

    public int getIntervalAmount() {
        int interval = 0;
        interval = Integer.parseInt(tfInterval.getText());
        return interval;
    }

    public TimeUnit getIntervalUnit() {
        //TODO funkar detta? eller är det en string som ska göras om till en timeunit först?
        return (TimeUnit) comboBoxTimeUnits.getSelectedItem();
    }

    //TODO: ingen aning om detta funkar...
    public void setPossibleHours(LocalTime from, LocalTime to){
        comboBoxHoursFrom.setSelectedItem(from);
        comboBoxHoursTo.setSelectedItem(to);
    }

    public LocalTime[] getPossibleHours(){
        LocalTime[] hours = new LocalTime[2];
        hours[0] = (LocalTime) comboBoxHoursFrom.getSelectedItem();
        hours[1] = (LocalTime) comboBoxHoursTo.getSelectedItem();
        return hours;
    }

    public void setPossibleWeekdays(boolean[] possibleWeekdays){
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

    //TODO: hur ska detta visas om det är fler än ett datum??
    public void setPossibleDates(boolean[] dates){
        int from = comboBoxDatesFrom.getSelectedIndex() -1;
        int to = comboBoxDatesTo.getSelectedIndex() -1;
        for (int i = 0; i < dates.length; i++){
            if (i < from && i > to){
                dates[i] = false;
            } else {
                dates[i] = true;
            }
        }
    }

    public boolean[] getPossibleDates(){
        boolean[] dates = new boolean[31];
        int from;
        int to;

        for (int i = 0; i < dates.length; i++){
            if (dates[i]){
                from = i;
            } else {
                to = i;
                break;
            }
        }
        return dates;
    }

    public void setNotes(String notes){
        taNotes.setText(notes);
    }


    public String getNotes() {
        return taNotes.getText();
    }


    private class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnSave) {
                controller.buttonPressed(ButtonType.SAVE);
            }
        }
    }
}
