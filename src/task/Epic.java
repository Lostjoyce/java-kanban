package task;
import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtasksIds = new ArrayList<>();

    public Epic(String title, String description) {
        super(title, description);
    }

    public ArrayList<Integer> getSubtasksIds() {
        return subtasksIds;
    }

    public void setSubtasksIds(ArrayList<Integer> subtasksIds) {
        this.subtasksIds = subtasksIds;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                ", subtasksIds=" + subtasksIds +
                '}';
    }


}
