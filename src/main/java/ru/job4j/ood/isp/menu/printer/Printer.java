package ru.job4j.ood.isp.menu.printer;

import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.MenuPrinter;
import ru.job4j.ood.isp.menu.Output;

public class Printer implements MenuPrinter {

    static final String INDENTATION = "----";

    private final Output out;

    public Printer(Output out) {
        this.out = out;
    }

    @Override
    public void print(Menu menu) {
        menu.forEach(menuItemInfo ->
                out.println(getIndentation(menuItemInfo)
                            + menuItemInfo.getNumber()
                            + menuItemInfo.getName()));
    }

    private String getIndentation(Menu.MenuItemInfo menuItemInfo) {
        StringBuilder sb = new StringBuilder();
        int numb = (int) menuItemInfo.getNumber().chars()
                .filter(ch -> ch == '.')
                .count();
        sb.append(INDENTATION.repeat(Math.max(0, numb - 1)));
        return sb.toString();
    }
}
