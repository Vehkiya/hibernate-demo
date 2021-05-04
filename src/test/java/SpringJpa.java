import models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repository.ProductRepository;
import spring.Config;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Config.class)
public class SpringJpa {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void selectTest() {
        final List<Product> productList = productRepository.findAll();
        Assertions.assertEquals(77, productList.size());
    }

    @Test
    void customTest() {
        final String name = "Chai";
        final Product chai = productRepository.findByName(name);
        Assertions.assertNotNull(chai);
        Assertions.assertEquals(name, chai.getName());
    }

    @Test
    void findLike() {
        final List<Product> products = productRepository.findLike("Cha%");
        Assertions.assertEquals(3, products.size());
        products.forEach(product -> Assertions.assertTrue(product.getName().startsWith("Cha")));
    }
}
