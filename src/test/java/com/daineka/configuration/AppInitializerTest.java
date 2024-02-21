package com.daineka.configuration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
class AppInitializerTest {

    private static AppInitializer appInitializer;

    @BeforeAll
    static void beforeAll() {
        appInitializer = new AppInitializer();
    }

    @BeforeEach
    void setUp() {
        appInitializer = new AppInitializer();
    }

    @Test
    void testGetRootConfigClasses() {
        Class<?>[] rootConfigClasses = appInitializer.getRootConfigClasses();

        assertNull(rootConfigClasses);
    }

    @Test
    void testGetServletConfigClasses() {
        Class<?>[] servletConfigClasses = appInitializer.getServletConfigClasses();

        assert servletConfigClasses != null;
        assertEquals(1, servletConfigClasses.length);
        assertEquals(AppConfig.class, servletConfigClasses[0]);
    }

    @Test
    void testGetServletMappings() {
        String[] servletMappings = appInitializer.getServletMappings();

        assertEquals(1, servletMappings.length);
        assertEquals("/", servletMappings[0]);
    }
}