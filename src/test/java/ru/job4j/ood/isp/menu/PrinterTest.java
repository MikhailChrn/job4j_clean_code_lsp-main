package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.isp.menu.output.StubOutput;
import ru.job4j.ood.isp.menu.printer.Printer;

import static org.assertj.core.api.Assertions.*;

class PrinterTest {

    static final ActionDelegate STUB_ACTION = System.out::println;

    static final String LN = System.lineSeparator();

    private final Output out = new StubOutput();

    private final Menu menu = new SimpleMenu();

    {
        this.menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        this.menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        this.menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        this.menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        this.menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
    }

    @Test
    public void whenPrintedCurrentMenu() {
        MenuPrinter printer = new Printer(out);
        printer.print(menu);
        assertThat(out.toString()).isEqualTo(
                "1.Сходить в магазин" + LN
                        + "----1.1.Купить продукты" + LN
                        + "--------1.1.1.Купить хлеб" + LN
                        + "--------1.1.2.Купить молоко" + LN
                        + "2.Покормить собаку" + LN
        );
    }
}