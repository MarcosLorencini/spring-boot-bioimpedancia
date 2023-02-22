package com.lorencini.bio.resources;

import com.lorencini.bio.domain.Client;
import com.lorencini.bio.dto.ClientDTO;
import com.lorencini.bio.dto.ClientResponseDTO;
import com.lorencini.bio.services.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api")
public class ClientResource {

    @Autowired
    private ModelMapper modelMapper;

    private ClientService service;

    public ClientResource(ClientService clientService) {
        super();
        this.service = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<ClientResponseDTO> insert(@Valid  @RequestBody ClientDTO dto) {
        Client client = service.insert(dto.transformaParaObjeto());
        ClientResponseDTO clientResponseDTO = ClientResponseDTO.transformaEmDTO(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientResponseDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Integer id) {
        Client client = service.find(id);
        ClientResponseDTO alunoDTO = ClientResponseDTO.transformaEmDTO(client);
        return ResponseEntity.ok().body(alunoDTO);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientResponseDTO>> findAll() {
        List<Client> list = service.findAll();
        List<ClientResponseDTO> listDto = list.stream().map(client -> new ClientResponseDTO(client))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Integer id, @RequestBody ClientDTO clientDto) {
        Client clientRequest = clientDto.transformaParaObjeto();
        clientRequest.setId(id);
        service.update(clientRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<ClientResponseDTO> deleteById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping(value = "/page")
    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<ClientDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                    @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
        Page<Client> list = service.findPage(page, linesPerPage, direction, orderBy);
        // convert entity to DTO
        Page<ClientDTO> listDto = list.map(aluno ->  modelMapper.map(aluno, ClientDTO.class));
        return ResponseEntity.ok().body(listDto);
    }


}
