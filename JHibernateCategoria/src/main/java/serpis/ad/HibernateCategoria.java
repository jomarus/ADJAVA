package serpis.ad;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class HibernateCategoria {

	private static EntityManagerFactory entityManagerFactory;
	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.jpa.mysql");
		
		showCategorias();
		
		//System.out.println("Añado categorias");
		//persistNuevasCategorias();
		
		//System.out.println("Elimino categoria");
		//deleteCategorias();
		
		//System.out.println("Actualizo categoria");
		//updateCategorias();
		
		showCategorias();
		
		entityManagerFactory.close();
	}

	public static void showCategorias(){
		
		//EntityManager es una version mas moderna que sesionFactory 
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Categoria> categorias = entityManager.createQuery("from Categoria", Categoria.class).getResultList();
		for(Categoria categoria : categorias){
			System.out.printf("id=%d nombre=%s\n",categoria.getId(),categoria.getNombre());
		}
		entityManager.close();
	}
	
	public static void persistNuevasCategorias(){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Categoria categoria = new Categoria();
		categoria.setNombre("Hibernate "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		entityManager.persist(categoria);
		entityManager.getTransaction().commit();
		
		entityManager.close();
	}
	
	public static void deleteCategorias(){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

			Categoria categoria = entityManager.find(Categoria.class,(long)19);
			entityManager.remove(categoria);
			entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void updateCategorias(){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		Categoria categoria = entityManager.find(Categoria.class,(long)17);
		categoria.setNombre("Fecha hibernate");
		entityManager.merge(categoria);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
	}
}
