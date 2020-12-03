package model;

public class DepartmentModel {
    private int idDepartment;
    private String name;

    public DepartmentModel(int pIdDepartment, String pName)
    {
        idDepartment = pIdDepartment;
        name = pName;
    }

    public int getIdDepartment(){
        return this.idDepartment;
    }

    public void setIdDepartment(int pIdDepartment) {
        idDepartment = pIdDepartment;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public String toString()
    {
        // Exemple : 1 - Administration
        return idDepartment + " - " + name;
    }
}
