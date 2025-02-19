Table Class

Represents a restaurant table with:

size: The maximum number of seats.

occupiedSeats: The number of currently occupied seats.

isAvailable(int groupSize): Checks if there are enough seats for a given group.

seatGroup(int groupSize): Assigns a group to the table.

leaveGroup(int groupSize): Removes a group from the table.

isEmpty(): Checks if the table is empty.

getSize(): Returns the table size.

getOccupiedSeats(): Returns the current number of occupied seats.



2. Group Class

Represents a group of customers with:

size: The number of people in the group.

getSize(): Returns the group size.



3. RestManager Class

Manages restaurant operations with:

tables: A sorted list of tables (smallest to largest size).

queue: A queue of waiting groups.

arrive(int groupSize): Tries to seat a new group. If no table is available, the group is added to the queue.

leave(int groupSize): Removes a group from a table and attempts to seat waiting groups.

seatQueuedGroups(): Iterates through the queue to find and seat groups if space is available.



Seating Rules

Groups are seated at the smallest available empty table that fits them.

If no empty table is available, they are placed at a partially occupied table.

If no suitable table is found, they wait in the queue.

When a group leaves, queued groups are checked and seated if possible.



Implementation

Define a list of available table sizes.

Initialize the RestManager with these table sizes.

Use arrive(int groupSize) to add groups.

Use leave(int groupSize) when a group leaves.

The system automatically manages the queue and seating assignments.



How to Run

Ensure you have Java installed on your system.

Copy the implementation files (Table.java, Group.java, RestManager.java).

Create a main class (Main.java) and include:



import java.util.Arrays;

    public static void main(String[] args) {
        RestManager manager = new RestManager(Arrays.asList(2, 4, 6));
        manager.arrive(3);
        manager.arrive(2);
        manager.leave(3);
    }
}


Compile the program using:
javac Main.java Table.java Group.java RestManager.java

Run the program:
java Main