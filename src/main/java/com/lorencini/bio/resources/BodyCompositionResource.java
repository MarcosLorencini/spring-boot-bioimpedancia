package com.lorencini.bio.resources;

import com.lorencini.bio.domain.BodyComposition;
import com.lorencini.bio.dto.BodyCompositionDTO;
import com.lorencini.bio.dto.BodyCompositonResponseDTO;
import com.lorencini.bio.dto.ClientResponseDTO;
import com.lorencini.bio.services.BodyCompositionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api")
public class BodyCompositionResource {

    @Autowired
    private BodyCompositionService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/clients/{clientId}/bodycompositions")
    public ResponseEntity<BodyCompositonResponseDTO> insert(@Valid @PathVariable(value = "clientId") Integer clientId,
                                                            @RequestBody BodyCompositionDTO dto) {
        BodyComposition bodyComposition = service.insert(clientId, dto.transformaParaObjeto(dto));
        BodyCompositonResponseDTO responseDTO = BodyCompositonResponseDTO.transformaEmDTO(bodyComposition);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/compositions/{id}")
    public ResponseEntity<BodyCompositonResponseDTO> findById(@PathVariable(value = "id") Integer id) {
        BodyComposition bodyComposition = service.findById(id);
        return ResponseEntity.ok().body(BodyCompositonResponseDTO.transformaEmDTO(bodyComposition));
    }

    @GetMapping("/clientes/{clienteId}/compositions")
    public ResponseEntity<List<BodyCompositonResponseDTO>> findAll(@PathVariable(value = "clienteId") Integer clienteId) {
        List<BodyComposition> list = service.findAll(clienteId);
        List<BodyCompositonResponseDTO> listDto = list.stream().map(composition -> BodyCompositonResponseDTO.transformaEmDTO(composition))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PutMapping("/compositions/{id}")
    public ResponseEntity<BodyCompositonResponseDTO> update(@Valid @PathVariable(value = "id") Integer id,
                                                            @RequestBody BodyCompositionDTO bodyCompositionDTO) {
        BodyComposition bodyCompositionRequest = BodyCompositionDTO.transformaParaObjeto(bodyCompositionDTO);
        service.update(id, BodyCompositionDTO.transformaParaObjeto(bodyCompositionDTO));
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<BodyCompositonResponseDTO> deleteById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }




}
