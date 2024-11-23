import java.util.ArrayList;
import java.util.HashMap;
import task.*;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int nextId = 1;

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    public void deleteAllSubtasks() {
        for (Epic epic : epics.values()) {
            updateEpicStatus(epic);
        }
        subtasks.clear();
    }

    public Task getTheTask(int id) {
        return tasks.get(id);
    }

    public Epic getTheEpic(int id) {
        return epics.get(id);
    }

    public Subtask getTheSubtask(int id) {
        return subtasks.get(id);
    }

    public void addNewTask(Task task) {
        task.setId(nextId);
        tasks.put(task.getId(), task);
        nextId++;
    }

    public void addNewEpic(Epic epic) {
        epic.setId(nextId);
        epics.put(epic.getId(), epic);
        nextId++;
        updateEpicStatus(epic);
    }

    public void addNewSubtask(Subtask subtask) {
        subtask.setId(nextId);
        subtasks.put(subtask.getId(), subtask);
        nextId++;
        Epic epic = getTheEpic(subtask.getEpicId());
        epic.getSubtasksIds().add(subtask.getId());
        updateEpicStatus(epic);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        Epic oldEpic = getTheEpic(epic.getId());
        epic.setSubtasksIds(oldEpic.getSubtasksIds());
        epics.put(epic.getId(), epic);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Epic epic = getTheEpic(subtask.getEpicId());
        updateEpicStatus(epic);
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteEpic(int id) {
        Epic epic = getTheEpic(id);
        for (Subtask epicSubtask : getEpicSubtasks(epic)) {
            subtasks.remove(epicSubtask.getId());
        }
        epics.remove(id);
    }

    public void deleteSubtask(Integer id) {
        Subtask subtask = getTheSubtask(id);
        Epic subtaskEpic = getTheEpic(subtask.getEpicId());
        subtaskEpic.getSubtasksIds().remove(id);
        subtasks.remove(id);
    }

    public ArrayList<Subtask> getEpicSubtasks(Epic epic) {
        ArrayList<Subtask> epicSubtasks = new ArrayList<>();

        for (Integer id : epic.getSubtasksIds()) {
            epicSubtasks.add(subtasks.get(id));
        }
        return epicSubtasks;
    }

    public void updateEpicStatus(Epic epic) {
        ArrayList<Subtask> epicSubtasks = getEpicSubtasks(epic);

        if (epicSubtasks.isEmpty() || areAllEpicSubtasksNew(epicSubtasks)) {
            epic.setStatus(TaskStatus.NEW);
        } else if (areAllEpicSubtasksDone(epicSubtasks)) {
            epic.setStatus(TaskStatus.DONE);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    public boolean areAllEpicSubtasksNew(ArrayList<Subtask> epicSubtasks) {
        for (Subtask subtask : epicSubtasks) {
            if (subtask.getStatus() != TaskStatus.NEW) return false;
        }
        return true;
    }

    public boolean areAllEpicSubtasksDone(ArrayList<Subtask> epicSubtasks) {
        for (Subtask subtask : epicSubtasks) {
            if (subtask.getStatus() != TaskStatus.DONE) return false;
        }
        return true;
    }

}
