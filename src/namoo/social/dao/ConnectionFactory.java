package namoo.social.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import namoo.social.util.exception.NamooSocialException;

public class ConnectionFactory {

	private static ConnectionFactory instance = new ConnectionFactory();
	
	private String driver;
	private String url;
	private String user;
	private String password;
	
	/**
	 * <PRE>
	 * 기본생성자
	 * DB접속을 위한 설정정보를 로딩한다.
	 * 외부에서는 호출안됨
	 * </PRE>
	 */
	private ConnectionFactory() {
		//
		loadProperties();
		
		try {
			Class.forName(driver);
		}
		catch(ClassNotFoundException e) {
			throw new NamooSocialException("드라이버를 로딩하기 위한 클래스를 찾을 수 없습니다. : " + driver);
		}
	}
	
	/**
	 * 데이터베이스 접속정보가 저장된 프로퍼티 파일을 로딩
	 */
	private void loadProperties() {
		//
		Properties prop = new Properties();
		InputStream is = this.getClass().getResourceAsStream("jdbc.properties");
		
		try {
			prop.load(is);
			this.driver = prop.getProperty("database.driver");
			this.url = prop.getProperty("database.url");
			this.user = prop.getProperty("database.user");
			this.password = prop.getProperty("database.password");
		}
		catch(IOException e) {
			throw new NamooSocialException("데이터베이스 정보 로딩중 오류가 발생했습니다.");
		}
	}
	
	/**
	 * 컨넥션 팩토리 인스턴스 반환
	 * @return
	 */
	public static ConnectionFactory getInstance() {
		//
		return instance;
	}
	
	/**
	 * 데이터베이스 컨넥션 생성
	 * @return
	 */
	public Connection createConnection() {
		//
		try {
			return DriverManager.getConnection(url, user, password);
		}
		catch(SQLException e) {
			throw new NamooSocialException("데이터베이스 접속중 오류가 발생했습니다.");
		}
	}
}
