package serpis.ad;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Categoria {

    private Long id;
    private String nombre;
    
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	//@ManyToOne(cascade={CascadeType.ALL})

    public Long getId() {
		return id;
    }

    private void setId(Long id) {
		this.id = id;
    }
    
    public String getNombre() {
		return nombre;
    }

    public void setNombre(String nombre) {
		this.nombre = nombre;
    }
}
