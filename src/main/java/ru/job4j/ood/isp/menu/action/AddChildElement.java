package ru.job4j.ood.isp.menu.action;

import ru.job4j.ood.isp.menu.*;

public class AddChildElement implements UserAction {
    private Output out;

    public AddChildElement(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add child elements";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        out.println("=== Add child element ===");
        String parentName = input.askStr("Enter parent element name: ");
        String name = input.askStr("Enter element name: ");
        menu.add(parentName, name, SimpleMenu.STUB_ACTION);
        return true;
    }
}
