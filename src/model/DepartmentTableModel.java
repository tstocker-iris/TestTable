package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DepartmentTableModel extends AbstractTableModel {
    private String[] columnNames =
            {
                    "ID Département",
                    "Nom",
            };

    private List<DepartmentModel> data;

    public DepartmentTableModel()
    {
        data = new ArrayList<DepartmentModel>();
    }

    public DepartmentTableModel(List<DepartmentModel> departments)
    {
        this.data = departments;
    }

    public List<DepartmentModel> getData() { return data; }
    public void setData(List<DepartmentModel> pData) { data = pData; }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        DepartmentModel person = getDepartment(row);

        switch (column)
        {
            case 0: return person.getIdDepartment();
            case 1: return person.getName();
            default: return null;
        }
    }

    public DepartmentModel getDepartment(int row)
    {
        return data.get( row );
    }
}
