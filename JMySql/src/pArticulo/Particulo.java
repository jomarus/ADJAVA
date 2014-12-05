package pArticulo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Particulo {

	private static Scanner scanner = new Scanner(System.in);
	private static Connection connection;

	public static void main(String[] args) throws SQLException{
		int opcion, id;
		String nombre;
		long categoria;
		BigDecimal precio;
		do{
			System.out.println("Menu");
			System.out.println("Indique opcion a realizar: ");
			System.out.println("0. Salir ");
			System.out.println("1. Nuevo ");
			System.out.println("2. Editar");
			System.out.println("3. Eliminar ");
			System.out.println("4. Visualizar ");
			opcion= scanner.nextInt();
			scanner.nextLine();
			
			 switch(opcion) {
			 case 0: 
					System.out.println("Hasta luego!");
					System.exit(0);
			     break;
			 case 1: 
				 	//Nuevo
				 	System.out.println("Indique nuevo nombre: ");	
					nombre=scanner.nextLine();
					System.out.println("Indique nuevo categoria: ");	
					categoria=scanner.nextLong();
					System.out.println("Indique nuevo precio: ");	
					precio=scanner.nextBigDecimal();
					nuevo(nombre,categoria,precio);
			     break;
			 case 2: 
				 	//Editar
				 	System.out.println("Indique id a editar: ");
					id=scanner.nextInt();
					scanner.nextLine();
					System.out.println("Indique nuevo nombre: ");	
					nombre=scanner.nextLine();
					System.out.println("Indique nuevo categoria: ");	
					categoria=scanner.nextLong();
					System.out.println("Indique nuevo precio: ");	
					precio=scanner.nextBigDecimal();
					
					editar(nombre, categoria, precio, id);
			     break;
			 case 3: 
				 	//Eliminar
					System.out.println("Indique id a eliminar: ");
					id=scanner.nextInt();
					eliminar(id);
			     break;	        
			 case 4: 
				 	//Visualizar
					visualizar();
			     break;	
			 }
		}while(opcion!=0);
	}
	
	public static void nuevo(String nombre,long categoria, BigDecimal precio) {
		try{
			connection=conexion();
	
			PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO articulo(nombre,categoria,precio) VALUES (?,?,?)"
			);
			preparedStatement.setString(1, nombre);
			preparedStatement.setLong(2, categoria);
			preparedStatement.setBigDecimal(3, precio);
			preparedStatement.executeUpdate();
	
			preparedStatement.close();
			connection.close();		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void visualizar(){
		try{
			connection=conexion();
	
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
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void editar(String nombre,long categoria, BigDecimal precio, int id){
		try{
			connection=conexion();
	
			PreparedStatement preparedStatement = connection.prepareStatement(
				"UPDATE articulo SET nombre=?, categoria=?, precio=? WHERE id=?"
			);
			preparedStatement.setString(1, nombre);
			preparedStatement.setLong(2, categoria);
			preparedStatement.setBigDecimal(3, precio);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
	
			preparedStatement.close();
			connection.close();	
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void eliminar(int id) throws SQLException{
		try{
			connection=conexion();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
				"DELETE FROM articulo where id=? "
			);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
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
