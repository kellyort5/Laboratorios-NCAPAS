package com.example.labevaluado.common;

import com.example.labevaluado.domain.entity.Material;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MaterialList {

    private final List<Material> materials;

    public MaterialList() {
        this.materials = new ArrayList<>();

        this.materials.add(Material.builder()
                .nombre("Ámbar Rojo")
                .categoria("Mineral")
                .efecto("Corazones")
                .precio(30)
                .ubicacion("Cordillera de Hebra")
                .rareza("Poco Común")
                .build());

        this.materials.add(Material.builder()
                .nombre("Ala de Keese")
                .categoria("Parte de Monstruo")
                .efecto("Sigilo")
                .precio(15)
                .ubicacion("Volcán de Eldin")
                .rareza("Común")
                .build());

        this.materials.add(Material.builder()
                .nombre("Pimienta Ardiente")
                .categoria("Planta")
                .efecto("Ataque")
                .precio(10)
                .ubicacion("Cordillera de Hebra")
                .rareza("Común")
                .build());

        this.materials.add(Material.builder()
                .nombre("Trufa Energizante")
                .categoria("Comida")
                .efecto("Estamina")
                .precio(40)
                .ubicacion("Bosque de Farone")
                .rareza("Raro")
                .build());

        this.materials.add(Material.builder()
                .nombre("Diamante")
                .categoria("Mineral")
                .efecto("Defensa")
                .precio(500)
                .ubicacion("Montaña de la Muerte")
                .rareza("Legendario")
                .build());

        this.materials.add(Material.builder()
                .nombre("Cola de Lizalfos")
                .categoria("Parte de Monstruo")
                .efecto("Ataque")
                .precio(25)
                .ubicacion("Desierto Gerudo")
                .rareza("Poco Común")
                .build());

        this.materials.add(Material.builder()
                .nombre("Flor sigilosa")
                .categoria("Planta")
                .efecto("Sigilo")
                .precio(20)
                .ubicacion("Bosque Kolog")
                .rareza("Raro")
                .build());

        this.materials.add(Material.builder()
                .nombre("Escarabajo Rugiente")
                .categoria("Comida")
                .efecto("Estamina")
                .precio(50)
                .ubicacion("Cordillera de Hebra")
                .rareza("Legendario")
                .build());
    }

    public List<Material> getMaterials() {
        return materials;
    }
}