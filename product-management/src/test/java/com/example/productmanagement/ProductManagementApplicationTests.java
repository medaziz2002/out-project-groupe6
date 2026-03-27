package com.example.productmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductManagementApplicationTest {

    /**
     * Vérifie que le contexte Spring démarre correctement
     */
    @Test
    void contextLoads() {
        // Si le contexte ne démarre pas, le test échoue automatiquement
    }

    /**
     * Vérifie que la méthode main s’exécute sans erreur
     */
    @Test
    void testMainMethod() {
        ProductManagementApplication.main(new String[]{});
    }

    /**
     * Vérifie le démarrage de l'application SANS serveur web
     * (évite les conflits avec @SpringBootTest)
     */
    @Test
    void testApplicationStartsWithoutWebEnvironment() {

        SpringApplication app = new SpringApplication(ProductManagementApplication.class);

        // 🔥 IMPORTANT : désactive le serveur web (Tomcat)
        app.setWebApplicationType(WebApplicationType.NONE);

        try (ConfigurableApplicationContext context = app.run()) {

            assertNotNull(context);
            assertTrue(context.isActive());

        }
    }
}