package com.example.labevaluado;

import com.example.labevaluado.domain.entity.Material;
import com.example.labevaluado.services.MaterialService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LabEvaluadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabEvaluadoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(MaterialService materialService) {
        return args -> {

            System.out.println("=== CATÁLOGO COMPLETO ORDENADO POR PRECIO ===");
            materialService.obtenerTodosOrdenadosPorPrecioDesc()
                    .forEach(m -> System.out.println(
                            "[HYRULE-DB] Nombre: " + m.getNombre()
                                    + " | Categoría: " + m.getCategoria()
                                    + " | Precio: " + m.getPrecio() + " Rupias"
                    ));

            System.out.println("\n=== MATERIAL MÁS CARO ===");
            Material masCaro = materialService.obtenerMaterialMasCaro();
            if (masCaro != null) {
                System.out.println(
                        "[HYRULE-DB] Nombre: " + masCaro.getNombre()
                                + " | Categoría: " + masCaro.getCategoria()
                                + " | Precio: " + masCaro.getPrecio() + " Rupias"
                );
            }

            System.out.println("\n=== MATERIALES LEGENDARIOS ===");
            materialService.obtenerLegendarios()
                    .forEach(m -> System.out.println(
                            "[HYRULE-DB] Nombre: " + m.getNombre()
                                    + " | Categoría: " + m.getCategoria()
                                    + " | Precio: " + m.getPrecio() + " Rupias"
                    ));

            System.out.println("\n=== UBICACIONES SIN REPETIR ===");
            materialService.obtenerUbicacionesSinRepetir()
                    .forEach(u -> System.out.println("[HYRULE-DB] Ubicación: " + u));
        };
    }
}
