package com.Yurym;

public class Classroom {

    int capacity;
    int number;

    static int nextNumber = 101;
    static Classroom biggestClass;


    Classroom (int students) {
        capacity = students;
        if ((biggestClass == null) || (this.capacity > biggestClass.capacity))
            biggestClass = this;

        this.number = Classroom.nextNumber;
        Classroom.nextNumber++;
    }

    public String toString(){
        return "Class N" + this.number + " has " + this.capacity + " students";
    }

    public static int getNumberOfClassromm(){
        return Classroom.nextNumber - 101;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public int getClassroomNumber(){
        return this.number;
    }

    public static Classroom getMaxCapacityClassroom(){
        return Classroom.biggestClass;
    }

    public static void main(String [] args){
        Classroom c1 = new Classroom(15);
        Classroom c2 = new Classroom(33);
        Classroom c3 = new Classroom(32);

        System.out.println(c1.getCapacity());
        System.out.println(c2.getClassroomNumber());

        System.out.println(Classroom.getNumberOfClassromm());
        System.out.println(Classroom.getMaxCapacityClassroom());

    }

}