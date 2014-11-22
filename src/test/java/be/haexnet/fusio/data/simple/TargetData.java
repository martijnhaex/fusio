package be.haexnet.fusio.data.simple;

import java.math.BigDecimal;

public class TargetData {

    private String name;
    private BigDecimal salary;
    private int age;
    private String occupation;

    private TargetData() {
    }

    private TargetData(final String name, final BigDecimal salary, final int age, final String occupation) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.occupation = occupation;
    }

    public static TargetData empty() {
        return new TargetData();
    }

    public static TargetData of(final String name, final BigDecimal salary, final int age, final String occupation) {
         return new TargetData(name, salary, age, occupation);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }

}
