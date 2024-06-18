package ru.job4j.ood.isp.menu.action;

import ru.job4j.ood.isp.menu.*;

public class AddRootElement implements UserAction {
    private Output out;

    public AddRootElement(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add root element";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        out.println("=== Add root element ===");
        String name = input.askStr("Enter element name: ");
        menu.add(Menu.ROOT, name, SimpleMenu.STUB_ACTION);
        return true;
    }
}
