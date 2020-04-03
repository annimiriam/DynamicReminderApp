package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Hanna My Jansson
 * @version 1.0
 */
public class TaskRegister implements Serializable {
    private HashMap<Integer,Task> taskList;
    private static final long serialVersionUID = 655296850; //gör så att man kan läsa från filen

    private int lastId;

    public TaskRegister() {
        taskList = new HashMap();
        lastId = 0;
    }


    public void addTask(Task task){
        if(task!=null) {
            int id = generateId();
            task.setID(id);
            taskList.put(id,task);
        }

    }

    public void removeWithId(int taskId){
        Task task = new Task(taskId);
        taskList.remove(task);
    }

    public void removeTask(Task task){
        taskList.remove(task);
    }

    public void removeWithIndex(int index){
        taskList.remove(index);
    }

    public Task getTaskWithIndex(int index) {
        return taskList.get(index);

    }



    public Task getTaskWithId(int id) {
     Task task =  taskList.get(id);
     return task;
    }

    private int generateId(){
        lastId++;
        return lastId;
    }

    public String toString(){
        //TODO Hur gör jag för att få ut mer än bara 1 task?
        String str = taskList.get(1).toString();
        return str;
    }


}
