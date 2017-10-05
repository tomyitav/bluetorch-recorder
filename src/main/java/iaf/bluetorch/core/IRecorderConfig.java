package iaf.bluetorch.core;

public interface IRecorderConfig {
	public String dbHost();
	public int dbPort();
	public String dbName();
	public int dbSocketTimeout();
	public int dbConnectTimeout();
	public int dbMaxConnectionIdleTime();
}
