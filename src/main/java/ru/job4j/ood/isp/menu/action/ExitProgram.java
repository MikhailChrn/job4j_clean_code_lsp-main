package ru.job4j.ood.isp.menu.action;

import ru.job4j.ood.isp.menu.Input;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.Output;
import ru.job4j.ood.isp.menu.UserAction;

public class ExitProgram implements UserAction {
    private Output out;

    public ExitProgram(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Exit Program";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        out.println("Bye!");
        return false;
    }
}
