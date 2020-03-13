package ro.uaic.student.gboghez;

public class Hospital implements Comparable<Hospital> {
    private String name;
    private int capacity;

    public Hospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int compareTo(Hospital otherHospital) {
        int nameDiff = name.compareToIgnoreCase(otherHospital.getName());
        if (nameDiff != 0) {
            return nameDiff;
        }
        return capacity - otherHospital.getCapacity();
    }

    @Override
    public String toString() {
        return name;
    }
}
