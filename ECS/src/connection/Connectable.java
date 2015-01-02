package connection;

public interface Connectable {
	public java.sql.Connection getConnection() throws Exception;
}
