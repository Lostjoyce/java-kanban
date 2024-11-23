import task.*;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("День матери", "Выбрать и купить телефонный чехол",
                TaskStatus.NEW);
        taskManager.addNewTask(task1);

        Task task2 = new Task("Отдых", "Залипнуть в стену на полчаса", TaskStatus.IN_PROGRESS);
        taskManager.addNewTask(task2);

        Epic epic1 = new Epic("Поездка в автобусе", "Воспроизвести мем");
        taskManager.addNewEpic(epic1);
        Subtask epic1Subtask1 = new Subtask("СТАРТУЕМ!", "Требовать отправления автобуса",
                TaskStatus.DONE, epic1.getId());
        taskManager.addNewSubtask(epic1Subtask1);
        Subtask epic1Subtask2 = new Subtask("Я СЕЙЧАС НАЧНУ СТРЕЛЯТЬ!!", "Угрожать серьезными увечьями",
                TaskStatus.IN_PROGRESS, epic1.getId());
        taskManager.addNewSubtask(epic1Subtask2);
        Subtask epic1Subtask3 = new Subtask("И КСТАТИ, ТЫ УВОЛЕН!!!", "Обидевшись, выйти из автобуса",
                TaskStatus.NEW, epic1.getId());
        taskManager.addNewSubtask(epic1Subtask3);

        Epic epic2 = new Epic("Еще более важное дело", "Что-нибудь покушать");
        taskManager.addNewEpic(epic2);
        Subtask epic2Subtask1 = new Subtask("Поесть", "Клянчить на вокзале", TaskStatus.NEW,
                epic2.getId());
        taskManager.addNewSubtask(epic2Subtask1);

        System.out.println("Создали 2 таска, 1 эпик с тремя сабками, 1 эпик с одной сабкой:");
        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());
        System.out.println();

        Task updatedTask1 = new Task("День матери", "Выбрать и купить телефонный чехол",
                TaskStatus.IN_PROGRESS);
        updatedTask1.setId(task1.getId());
        taskManager.updateTask(updatedTask1);

        Task updatedTask2 = new Task("Отдых", "Залипнуть в стену на полчаса", TaskStatus.DONE);
        updatedTask2.setId(task2.getId());
        taskManager.updateTask(updatedTask2);

        Epic updatedEpic1 = new Epic("Поездка в автобусе", "Кошмарить мирных людей");
        updatedEpic1.setId(epic1.getId());
        taskManager.updateEpic(updatedEpic1);
        Subtask updatedEpic1Subtask2 = new Subtask("Я СЕЙЧАС НАЧНУ СТРЕЛЯТЬ!!",
                "Угрожать серьезными увечьями", TaskStatus.DONE, updatedEpic1.getId());
        updatedEpic1Subtask2.setId(epic1Subtask2.getId());
        taskManager.updateSubtask(updatedEpic1Subtask2);
        Subtask updatedEpic1Subtask3 = new Subtask("И КСТАТИ, ТЫ УВОЛЕН!!!",
                "Обидевшись, выйти из автобуса", TaskStatus.DONE, updatedEpic1.getId());
        updatedEpic1Subtask3.setId(epic1Subtask3.getId());
        taskManager.updateSubtask(updatedEpic1Subtask3);

        Subtask updatedEpic2Subtask1 = new Subtask("Поесть", "Клянчить на вокзале",
                TaskStatus.IN_PROGRESS, epic2.getId());
        updatedEpic2Subtask1.setId(epic2Subtask1.getId());
        taskManager.updateSubtask(updatedEpic2Subtask1);

        System.out.println("Обновили статусы всех тасков, статусы всех сабок (кроме 1-ой сабки 1-го эпика)," +
                "обновили описание 1-го эпика: ");
        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());
        System.out.println();

        taskManager.deleteTask(updatedTask1.getId());
        taskManager.deleteEpic(epic2.getId());
        taskManager.deleteSubtask(updatedEpic1Subtask2.getId());

        System.out.println("Удалили 1-ый таск, 2-ой эпик и 2-ую сабку 1-го эпика:");
        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());
        System.out.println();

        taskManager.deleteAllTasks();
        taskManager.deleteAllEpics();

        System.out.println("Очистили всю память программы:");
        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());

    }
}
