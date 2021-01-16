package vn.techmaster.demojpa;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;

import vn.techmaster.demojpa.model.mapping.Product;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;



@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class ProductTests {
  @Autowired
  private EntityManager em;

  @Autowired
  private ApplicationContext appContext;

  @Test
  public void insertAndFindProductByNaturalId() {
    Product product = new Product();
    product.setTitle("High-Performance Java persistence");
    String slug = "high-performance-java-persistence";
    product.setSlug(slug);
    em.persist(product);
    Session session = em.unwrap(Session.class);

    Product product1 = session.bySimpleNaturalId(Product.class).load(slug);
    assertThat(product1).isEqualTo(product);
  }

  @Test
  public void accessToApplicationContext() {
    String[] beanNames = appContext.getBeanDefinitionNames();
    for (String beanName : beanNames) {
			System.out.println(beanName);
		}
    assertThat(beanNames).contains("testEntityManager", 
    "jpaContext", 
    "jpaMappingContext", 
    "entityManagerFactoryBuilder",
    "entityManagerFactory"
    );
  }

}
