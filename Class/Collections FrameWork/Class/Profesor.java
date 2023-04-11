public class Profesor {

    private int profesorNo;
    private String profesorName;
    private String department;

    public Profesor (int profesorNo, String profesorName) {
        this.profesorNo = profesorNo;
        this.profesorName = profesorName;
    }

    public void setDepartment (String department) {
        this.department = department;
    }
}
