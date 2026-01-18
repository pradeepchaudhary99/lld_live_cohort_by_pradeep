package week_1;

import java.util.*;

// =========================
// ASSOCIATION
// =========================
// Student and Course are independent.
// They just know about each other.

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Course {
    String title;

    Course(String title) {
        this.title = title;
    }
}

// =========================
// AGGREGATION
// =========================
// Department HAS Professors.
// Professors can exist without Department.

class Professor {
    String name;

    Professor(String name) {
        this.name = name;
    }
}

class Department {
    String deptName;
    List<Professor> professors;

    Department(String deptName, List<Professor> professors) {
        this.deptName = deptName;
        this.professors = professors; // weak ownership
    }
}

// =========================
// COMPOSITION
// =========================
// House OWNS Rooms.
// Rooms cannot exist without House.

class House {
    String houseName;
    List<Room> rooms = new ArrayList<>();

    House(String houseName) {
        this.houseName = houseName;
        rooms.add(new Room("Bedroom"));
        rooms.add(new Room("Kitchen"));
    }

    // Inner class emphasizes strong ownership
    class Room {
        String roomName;

        Room(String roomName) {
            this.roomName = roomName;
        }
    }
}

// =========================
// MAIN CLASS
// =========================

public class RelationshipsDemo {

    public static void main(String[] args) {

        // -------- ASSOCIATION --------
        Student student = new Student("Amit");
        Course course = new Course("Data Structures");

        System.out.println("Association:");
        System.out.println(student.name + " enrolled in " + course.title);

        // -------- AGGREGATION --------
        Professor p1 = new Professor("Dr. Sharma");
        Professor p2 = new Professor("Dr. Mehta");

        List<Professor> profs = new ArrayList<>();
        profs.add(p1);
        profs.add(p2);

        Department dept = new Department("Computer Science", profs);

        System.out.println("\nAggregation:");
        System.out.println("Department: " + dept.deptName);
        for (Professor p : dept.professors) {
            System.out.println("Professor: " + p.name);
        }

        // -------- COMPOSITION --------
        House house = new House("Dream Home");

        System.out.println("\nComposition:");
        System.out.println("House: " + house.houseName);
        for (House.Room r : house.rooms) {
            System.out.println("Room: " + r.roomName);
        }
    }
}
