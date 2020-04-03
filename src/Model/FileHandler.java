package Model;

import java.io.*;


public class FileHandler {
    private TaskRegister taskRegister;

    public FileHandler(TaskRegister taskRegister){ //todo vet inte hur jag annars ska göra det
        this.taskRegister = taskRegister;
    }

    public void saveToFile(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("files/taskregister")))){
            oos.writeObject(taskRegister);
            System.out.println("taskregister written: " + taskRegister.toString());
        }catch(FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(){
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("files/taskregister")))){
            ois.readObject();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
