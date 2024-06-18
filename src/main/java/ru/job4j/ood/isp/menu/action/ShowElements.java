package ru.job4j.ood.isp.menu.action;

import ru.job4j.ood.isp.menu.*;
import ru.job4j.ood.isp.menu.printer.Printer;

public class ShowElements implements UserAction {
    private Output out;

    public ShowElements(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show elements";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        out.println("=== Show current actions ===");
        Printer printer = new Printer(out);
        printer.print(menu);
        return true;
    }
}
