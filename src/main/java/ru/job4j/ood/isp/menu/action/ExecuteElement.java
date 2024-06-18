package ru.job4j.ood.isp.menu.action;

import ru.job4j.ood.isp.menu.Input;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.Output;
import ru.job4j.ood.isp.menu.UserAction;

public class ExecuteElement implements UserAction {
    private Output out;

    public ExecuteElement(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Execute element";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        out.println("=== Execute element ===");
        String name = input.askStr("Enter element name: ");
        menu.select(name).get().getActionDelegate().delegate();
        return true;
    }
}
