package ru.job4j.ood.isp.menu;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
          if (findItem(childName).isPresent()) {
            return false;
        }
        if (Objects.equals(Menu.ROOT, parentName)) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        } else {
            findItem(parentName).get().menuItem
                    .getChildren()
                    .add(new SimpleMenuItem(childName, actionDelegate));
        }
        return true;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> menuItemInfo = Optional.empty();
        Optional<ItemInfo> tmp = findItem(itemName);
        if (tmp.isPresent()) {
            menuItemInfo = Optional.of(new MenuItemInfo(tmp.get().menuItem.getName(),
                    tmp.get().menuItem.getChildren().stream()
                            .map(MenuItem::getName)
                            .collect(Collectors.toList()),
                    tmp.get().menuItem.getActionDelegate(),
                    tmp.get().number));
        }
        return menuItemInfo;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<>() {
            private DFSIterator dfsIterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return select(dfsIterator.next().menuItem.getName()).get();
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> itemInfo = Optional.empty();
        Iterator<ItemInfo> iterator = new DFSIterator();
        ItemInfo tempItemInfo = null;
        while (iterator.hasNext()) {
            tempItemInfo = iterator.next();
            if (name.equals(tempItemInfo.menuItem.getName())) {
                itemInfo = Optional.of(tempItemInfo);
                break;
            }
        }
        return itemInfo;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        private Deque<MenuItem> stack = new LinkedList<>();

        private Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious(); ) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        private MenuItem menuItem;
        private String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}