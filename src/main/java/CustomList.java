import java.util.ArrayList;

public class CustomList {

    // ArrayList of String to keep track of tasks
    private ArrayList<String> customList = new ArrayList<String>();

    public CustomList() {
    }

    // add task to the ArrayList
    public void addToList(String task) {
        this.customList.add(task);
    }

    public void showList() {
        if (this.customList.size() == 0) { // if ArrayList is empty, let the user know
            System.out.println("List is empty!");
        } else { // else show everything in the list
            for(int i = 0; i < this.customList.size(); i++) {
                System.out.println((i+1) + ". " + (String)this.customList.get(i));
            }
        }

    }
}