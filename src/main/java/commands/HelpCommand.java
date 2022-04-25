package commands;

public class HelpCommand implements Command {

    public HelpCommand() {
    }

    @Override
    public void execute(Object... args) {
        System.out.println("info: Выводит информацию о коллекции");
        System.out.println("show: Выводит все элементы коллекции");
        System.out.println("add: Добавляет элемент в коллекцию");
        System.out.println("update: Обновляет значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id: Удаляет элемент из коллекции по его id");
        System.out.println("clear: Очищает коллекцию");
        System.out.println("save: Сохраняет коллекцию в файл");
        System.out.println("execute_script: Считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь");
        System.out.println("add_if_max: Добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        System.out.println("remove_greater: Удаляет из коллекции все элементы, превышающие заданный");
        System.out.println("remove_lower: Удаляет из коллекции все элементы, меньшие, чем заданный");
        System.out.println("remove_all_by_weight: Удаляет из коллекции все элементы, значение поля weight которых эквивалентно заданному");
        System.out.println("count_greater_than_killer: Выводит количество элементов, значение поля killer которых больше заданного");
        System.out.println("filter_greater_than_age age: Выводит элементы, значение поля age которых больше заданного");
        System.out.println("exit: Завершает выполнение программы без сохранения в файл");
    }

    @Override
    public boolean withArgument() {
        return false;
    }
}
