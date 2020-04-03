package Controller;

public class UpdateThread extends Thread {
private Controller controller;

    public UpdateThread(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while(true){
           controller.updateGUI();
            System.out.println("Thread: Task Updated");
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
