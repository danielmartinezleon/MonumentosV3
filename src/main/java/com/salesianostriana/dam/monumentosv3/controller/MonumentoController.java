package com.salesianostriana.dam.monumentosv3.controller;

import com.salesianostriana.dam.monumentosv3.model.Monumento;
import com.salesianostriana.dam.monumentosv3.service.MonumentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monumento/")
@RequiredArgsConstructor
@Tag(name = "Monumentos", description = "Gestión de monumentos")
public class MonumentoController {

    private final MonumentoService monumentoService;

    @Operation(summary = "Crear un nuevo monumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el monumento correctamente",
                    content = {@Content(mediaType = "application/json",
                            examples = {@ExampleObject(
                                    value = """
                            {
                                \"id\": 4,
                                \"codigoPais\": \"US\",
                                \"nombrePais\": \"Estados Unidos\",
                                \"nombreCiudad\": \"Nueva York\",
                                \"latitud\": 40.68925,
                                \"longitud\": -74.0445,
                                \"nombre\": \"Estatua de la Libertad\",
                                \"descripcion\": \"La Estatua de la Libertad es un símbolo universal de libertad y democracia. Fue un regalo de Francia a los Estados Unidos en 1886, diseñada por Frédéric Auguste Bartholdi.\",
                                \"imagen\": \"https://upload.wikimedia.org/wikipedia/commons/a/a1/Statue_of_Liberty_7.jpg\"
                            }
                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Monumento> create(@RequestBody Monumento monumento) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(monumentoService.addMonumento(monumento));
    }

    @Operation(summary = "Obtener todos los monumentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado monumentos",
                    content = { @Content(mediaType = "application/json",
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                     \"id\": 1,
                                                     \"codigoPais\": \"ES\",
                                                     \"nombrePais\": \"España\",
                                                     \"nombreCiudad\": \"Úbeda\",
                                                     \"latitud\": 38.01194,
                                                     \"longitud\": -3.38034,
                                                     \"nombre\": \"Hospital de Santiago Úbeda\",
                                                     \"descripcion\": \"El hospital de Santiago es un monumento nacional situado en la ciudad de Úbeda, Jaén. Proyectado por Andrés de Vandelvira en 1562, las obras finalizaron en 1575, como consta en el testero de la escalera.\",
                                                     \"imagen\": \"https://cloud.inspain.org/imgwbp/sitios/1/1/8/ay3pje36bku5eh4bm7a43f7cgm_2000.webp\"
                                                 },
                                                 {
                                                     \"id\": 2,
                                                     \"codigoPais\": \"ES\",
                                                     \"nombrePais\": \"España\",
                                                     \"nombreCiudad\": \"Granada\",
                                                     \"latitud\": 37.17607,
                                                     \"longitud\": -3.58814,
                                                     \"nombre\": \"Alhambra de Granada\",
                                                     \"descripcion\": \"La Alhambra es una ciudad palatina andalusí situada en Granada. Es uno de los monumentos más visitados de España y una obra maestra del arte islámico.\",
                                                     \"imagen\": \"https://upload.wikimedia.org/wikipedia/commons/9/9b/Alhambra_de_Granada.jpg\"
                                                 }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún monumento",
                    content = @Content),
    })
    @GetMapping
    public ResponseEntity<List<Monumento>> getAll() {
        List<Monumento> result = monumentoService.getAllMonumentos();

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtener un monumento por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el monumento",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Monumento.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se encontró el monumento con el ID especificado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Monumento> getById(@PathVariable Long id) {
        return ResponseEntity.of(
                monumentoService.getMonumentoById(id)
        );
    }

    @Operation(summary = "Editar un monumento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el monumento correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Monumento.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se encontró el monumento con el ID especificado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Monumento> edit(@PathVariable("id") Long id, @RequestBody Monumento monumento) {
        return ResponseEntity.of(
                monumentoService.editMonumento(id, monumento)
        );
    }

    @Operation(summary = "Eliminar un monumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se eliminó el monumento correctamente",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se encontró el monumento con el ID especificado para eliminar",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        monumentoService.deleteMonumento(id);
        return ResponseEntity.noContent().build();
    }
}
