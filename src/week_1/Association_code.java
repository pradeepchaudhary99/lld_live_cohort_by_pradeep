//package week_1;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class Student {
//
//    private String name;
//    private List<Course> courses = new ArrayList<>();
//
//    public Student(String name) {
//        this.name = name;
//    }
//
//    public void enroll(Course course) {
//        courses.add(course);
//        course.addStudent(this);   //  bidirectional link
//    }
//
//    public String getName() {
//        return name;
//    }
//}
//
//
//class Course {
//    private String title;
//    private List<Student> students = new ArrayList<>();
//
//    public Course(String title) {
//        this.title = title;
//    }
//
//    void addStudent(Student student) {
//        students.add(student);
//    }
//
//    public String getTitle() {
//        return title;
//    }
//}
//
//public class Association_code {
//    public static void main(String[] args) {
//        Student s1 = new Student("Amit");
//        Course c1 = new Course("Data Structures");
//        s1.enroll(c1);
//    }
//}
