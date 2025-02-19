import java.util.*;

class Table {
    private int size;
    private int occupiedSeats;

    public Table(int size) {
        this.size = size;
        this.occupiedSeats = 0;
    }

    public boolean isAvailable(int groupSize) {
        return (size - occupiedSeats) >= groupSize;
    }

    public void seatGroup(int groupSize) {
        occupiedSeats += groupSize;
    }

    public void leaveGroup(int groupSize) {
        if (occupiedSeats >= groupSize) {
            occupiedSeats -= groupSize;
        }
    }

    public boolean isEmpty() {
        return occupiedSeats == 0;
    }

    public int getSize() {
        return size;
    }

    public int getOccupiedSeats() {
        return occupiedSeats;
    }
}

class Group {
    private int size;

    public Group(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}

class RestManager {
    private List<Table> tables;
    private Queue<Group> queue;

    public RestManager(List<Integer> tableSizes) {
        tables = new ArrayList<>();
        for (int size : tableSizes) {
            tables.add(new Table(size));
        }
        tables.sort(Comparator.comparingInt(Table::getSize));
        queue = new LinkedList<>();
    }

    public void arrive(int groupSize) {
        Group newGroup = new Group(groupSize);

        for (Table table : tables) {
            if (table.isEmpty() && table.getSize() >= groupSize) {
                table.seatGroup(groupSize);
                return;
            }
        }

        for (Table table : tables) {
            if (table.isAvailable(groupSize)) {
                table.seatGroup(groupSize);
                return;
            }
        }

        queue.add(newGroup);
    }

    public void leave(int groupSize) {
        for (Table table : tables) {
            if (table.getOccupiedSeats() >= groupSize) {
                table.leaveGroup(groupSize);
                seatQueuedGroups();
                return;
            }
        }
    }

    private void seatQueuedGroups() {
        Queue<Group> remainingQueue = new LinkedList<>();
        while (!queue.isEmpty()) {
            Group group = queue.poll();
            boolean seated = false;

            for (Table table : tables) {
                if (table.isEmpty() && table.getSize() >= group.getSize()) {
                    table.seatGroup(group.getSize());
                    seated = true;
                    break;
                }
            }

            if (!seated) {
                for (Table table : tables) {
                    if (table.isAvailable(group.getSize())) {
                        table.seatGroup(group.getSize());
                        seated = true;
                        break;
                    }
                }
            }

            if (!seated) {
                remainingQueue.add(group);
            }
        }
        queue = remainingQueue;
    }
}
