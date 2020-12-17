import model.DepartmentTableModel;

public class Terminal {

    public static void main(String[] args) {
        DepartmentTableModel model = new DepartmentTableModel();
        // model.setData(data); // 14 lignes;

        for (int i = 0; i < model.getColumnCount(); i++) {
            System.out.print(model.getColumnName(i) + "\t");
        }

        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                System.out.print(model.getValueAt(i, j) + "\t");
            }
        }


    }

}
