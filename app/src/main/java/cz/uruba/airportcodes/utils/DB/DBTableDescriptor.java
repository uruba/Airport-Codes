package cz.uruba.airportcodes.utils.DB;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Vaclav on 27.12.2014.
 */
public class DBTableDescriptor {
    private String tableName;
    private HashMap<String, Column> tableColumns;

    public DBTableDescriptor(String tableName) {
        this.tableName = tableName;
        this.tableColumns = new HashMap<>();
    }

    public void addColumn(String columnAlias, String columnName, Column.columnDataType columnDataType) {
        tableColumns.put(columnAlias, new Column(columnName, columnDataType));
    }

    public String getTableName() {
        return tableName;
    }

    public Column getColumn(String columnAlias) {
        return tableColumns.get(columnAlias);
    }

    public ArrayList<Column> getAllColumns() {
        ArrayList<Column> columns = new ArrayList<>();

        for (String key : tableColumns.keySet()) {
            columns.add(getColumn(key));
        }

        return columns;
    }

    public String getColumnName(String columnAlias) {
        return getColumn(columnAlias).getName();
    }


    public static String[] getProjection(Column[] columns) {
        int columnsArrLength = columns.length;
        String[] projection = new String[columnsArrLength];

        for (int i = 0; i < columnsArrLength; i++) {
            projection[i] = columns[i].getName();
        }

        return projection;
    }

    public static class Column {
        public static enum columnDataType {
            INT,
            TEXT
        }

        private String columnName;
        private columnDataType columnType;

        public Column(String columnName, columnDataType columnDataType) {
            this.columnName = columnName;
            this.columnType = columnDataType;
        }

        public String getName() {
            return columnName;
        }

        public columnDataType getColumnDataType() {
            return columnType;
        }
    }
}
