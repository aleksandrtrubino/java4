package ru.trubino;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        final String SEPARATOR = ";";
        final String FILE_PATH = "src/main/resources/foreign_names.csv";

        String line = "";
        List<Person> personList = new ArrayList<>();
        Set<Department> departmentSet = new HashSet<>();
        Map<String, Department> departmentMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(SEPARATOR);
                String id = data[0];
                String name = data[1];
                String gender = data[2];
                String birthDate = data[3];

                String departmentName = data[4];
                Department department = departmentMap.get(departmentName);
                if (department == null) {
                    int departmentId = departmentName.hashCode();
                    department = new Department(departmentId, departmentName);
                    departmentMap.put(departmentName, department);
                    departmentSet.add(department);
                }

                double salary = Double.parseDouble(data[5]);

                personList.add(new Person(id, name, gender, birthDate, departmentName, salary, department));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (Person person : personList) {
            System.out.println(person);
        }
    }
}