package ru.job4j.ood.isp.menu;

import ru.job4j.ood.isp.menu.action.*;
import ru.job4j.ood.isp.menu.output.ConsoleOutput;
import ru.job4j.ood.isp.menu.input.ConsoleInput;
import ru.job4j.ood.isp.menu.input.ValidateInput;

import java.util.Arrays;
import java.util.List;

public class TodoApp {
    private Output output;

    public TodoApp(Output out) {
        this.output = out;
    }

    private void showMenu(List<UserAction> actions) {
        output.println("**********");
        for (int index = 0; index < actions.size(); index++) {
            output.println(index + ". " + actions.get(index).name());
        }
    }

    public void init(Input input, Menu menu, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                output.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, menu);
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        Menu newMenu = new SimpleMenu();
        UserAction[] actionsArray = {
                new AddRootElement(output),
                new AddChildElement(output),
                new ExecuteElement(output),
                new ShowElements(output),
                new ExitProgram(output)};
        List<UserAction> actions = Arrays.asList(actionsArray);
        new TodoApp(output).init(input, newMenu, actions);
    }
}
