package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class MySql {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException{
		int opcion;
		String nombre;
		do{
			System.out.println("Menu");
			System.out.println("Indique opcion a realizar: ");
			System.out.println("0. Salir ");
			System.out.println("1. Nuevo ");
			System.out.println("2. Editar");
			System.out.println("3. Eliminar ");
			System.out.println("4. Visualizar ");
			opcion= scanner.nextInt();
			
			
			 switch(opcion) {
			 case 0: 
					System.out.println("Saliendo");
			     break;
			 case 1: 
					System.out.println("Nuevo");
			     break;
			 case 2: 
					System.out.println("Editar: ");
					System.out.println("Indique nombre a editar: ");
					nombre=scanner.nextLine();
					editar(nombre);
			     break;
			 case 3: 
					System.out.println("Eliminar");
					System.out.println("Indique no: ");

			     break;	        
			 case 4: 
					System.out.println("Visualizar");
					listar();
			     break;	
			 }
		}while(opcion!=0);
	}
	public static void listar() throws SQLException{
		Connection connection=conexion();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from articulo");

		while(resultSet.next()){	
			System.out.printf("id=%4s nombre=%s categoria=%s precio=%s \n",
					resultSet.getObject("id"),
					resultSet.getObject("nombre"),
					resultSet.getObject("categoria"),
					resultSet.getObject("precio"));
		}
		resultSet.close();
		statement.close();
		connection.close();
	}
	
	public static void editar(String nombre) throws SQLException{
		Connection connection=conexion();
		
		PreparedStatement preparedStatement = connection.prepareStatement(
			"select * from categoria where nombre like ? "
		);
		preparedStatement.setObject(1, nombre);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		
		while(resultSet.next()){	
			System.out.printf("id=%4s nombre=%s\n",
					resultSet.getObject("id"),
					resultSet.getObject("nombre"));
		}
		resultSet.close();
		preparedStatement.close();
		connection.close();
	}
	
	
	public static Connection conexion(){
		try{
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba?"
				+ "user=root"
				+ "&password=sistemas"
				);
		return connection;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;	
	}
}
