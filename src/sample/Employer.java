package sample;

public class Employer {
    private int id_employer;
    private String name_employer;
    private int department_employer;
    private int experience_employer;
    private int salary_employer;

    public int getId_employer() {
        return id_employer;
    }

    public void setId_employer(int id_employer) {
        this.id_employer = id_employer;
    }

    public String getName_employer() {
        return name_employer;
    }

    public void setName_employer(String name_employer) {
        this.name_employer = name_employer;
    }

    public int getDepartment_employer() {
        return department_employer;
    }

    public void setDepartment_employer(int department_employer) {
        this.department_employer = department_employer;
    }

    public int getExperience_employer() {
        return experience_employer;
    }

    public void setExperience_employer(int experince_employer) {
        this.experience_employer = experince_employer;
    }

    public int getSalary_employer() {
        return salary_employer;
    }

    public void setSalary_employer(int salary_employer) {
        this.salary_employer = salary_employer;
    }

    public Employer(int id_employer, String name_employer, int department_employer, int experince_employer, int salary_employer) {
        this.id_employer = id_employer;
        this.name_employer = name_employer;
        this.department_employer = department_employer;
        this.experience_employer = experince_employer;
        this.salary_employer = salary_employer;
    }
}
