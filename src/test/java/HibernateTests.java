import models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.spi.PersistenceProvider;
import java.util.List;

public class HibernateTests {

    private static SessionFactory sessionFactory;

    @BeforeAll
    static void setUp() {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
        Metadata metadata = metadataSources.getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    @Test
    void selectTest() {
        final Session session = sessionFactory.openSession();
        final Query<Product> productQuery = session.createQuery("from Product", Product.class);
        final List<Product> resultList = productQuery.getResultList();
        Assertions.assertEquals(77, resultList.size());
    }

    @Test
    void nativeQueryTest() {
        final Session session = sessionFactory.openSession();
        final Query<Product> productQuery = session.createNativeQuery("SELECT TOP(10) * FROM Products", Product.class);
        final List<Product> resultList = productQuery.getResultList();
        Assertions.assertEquals(10, resultList.size());
        resultList.forEach(System.out::println);
    }

    @Test
    void criteriaQueryTest() {
        final Session session = sessionFactory.openSession();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        final Root<Product> root = criteriaQuery.from(Product.class);
        final CriteriaQuery<Product> productsWhereNameLike = criteriaQuery.select(root).where(criteriaBuilder.like(root.get("name"), "Cha%"));
        final Query<Product> query = session.createQuery(productsWhereNameLike);
        final List<Product> productList = query.getResultList();
        Assertions.assertEquals(3, productList.size());
        productList.forEach(product -> Assertions.assertTrue(product.getName().startsWith("Cha")));
        productList.forEach(System.out::println);
    }

    @Test
    void entityManagerTest() {
        // uses persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        final EntityManager entityManager = emf.createEntityManager();
        final Product product = entityManager.find(Product.class, 1L);
        Assertions.assertEquals(1L, product.getId());
    }
}
