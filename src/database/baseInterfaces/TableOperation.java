package database.baseInterfaces;

public interface TableOperation {
    void createTable();

    void dropTable();

    boolean insert(Object o);

    Object select(String PK);

    boolean update(Object o);

    boolean delete(String PK);

}
