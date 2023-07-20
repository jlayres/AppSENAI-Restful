package br.com.senai.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.commons.ExampleValues;
import br.com.senai.dto.EstudanteDTO;
import br.com.senai.entity.Estudante;
import br.com.senai.service.EstudanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

@RestController
@RequestMapping("estudantes") //caminho a ser passado na url
public class EstudanteResource {
	
	@Autowired
	private EstudanteService estudanteService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	@Operation(description = "Retorna todos os estudantes")
	public ResponseEntity<List<EstudanteDTO>> buscarTodosEstudantes() {
		List<Estudante> listaEstudantes = estudanteService.buscarTodosEstudantes();
		//adicionar o modelmapper manualmente no pom.xml (pesquisar modelmapper maven)
		List<EstudanteDTO> listaEstudanteDTO = listaEstudantes.stream().map(estudante -> 
		mapper.map(estudante, EstudanteDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaEstudanteDTO);
	}
	
	@GetMapping("/{id}")
	@Operation(description = "Retorna o registro do estudante pelo id")
	public ResponseEntity<EstudanteDTO> buscarEstudantePeloId
	(@PathVariable("id") @Schema(example = ExampleValues.ID_ESTUDANTE) Integer id) {
		Estudante estudante = estudanteService.buscarEstudantePorId(id);
		EstudanteDTO estudanteDTO = mapper.map(estudante, EstudanteDTO.class);
		return ResponseEntity.ok().body(estudanteDTO);
	}
	
	@PostMapping
	@Operation(description = "Cadastra um estudante")
	public ResponseEntity<EstudanteDTO> cadastrarEstudante(@Valid
			@io.swagger.v3.oas.annotations.parameters.RequestBody
			(content = @Content
			(examples = {
		    @ExampleObject(name = "Exemplo de Estudante", value = ExampleValues.exemploEstudante)
			}))
			@RequestBody EstudanteDTO estudanteDTO) {
		Estudante estudante = mapper.map(estudanteDTO, Estudante.class);
		estudante = estudanteService.salvar(estudante);
		EstudanteDTO novoEstudante = mapper.map(estudante, EstudanteDTO.class);
		return ResponseEntity.ok().body(novoEstudante);
	}
	
	//PUT - atualiza todo o registro
	//Patch - atualiza somente um campo
	@PutMapping("/{id}")
	@Operation(description = "Altera o registro do estudante pelo id")
	public ResponseEntity<EstudanteDTO> atualizarEstudante(@PathVariable("id") Integer id, 
			@Schema(example = ExampleValues.ID_ESTUDANTE)
			@io.swagger.v3.oas.annotations.parameters.RequestBody
			(content = @Content
			(examples = {
		    @ExampleObject(name = "Exemplo de Estudante", value = ExampleValues.exemploEstudante)
			}))
			@RequestBody EstudanteDTO estudanteDTO) {
		Estudante estudante = mapper.map(estudanteDTO, Estudante.class);
		estudante = estudanteService.updateEstudante(id, estudante);
		EstudanteDTO novoEstudante = mapper.map(estudante, EstudanteDTO.class);
		return ResponseEntity.ok().body(novoEstudante);
	}
	
	@DeleteMapping("/{id}")
	@Operation(description = "Remove um estudante pelo id")
	public ResponseEntity<Boolean> excluirEstudante(@Schema(example = ExampleValues.ID_ESTUDANTE) 
	@PathVariable Integer id) {
		Boolean flag =  estudanteService.deleteEstudante(id);
		return ResponseEntity.ok().body(flag);
	}
	
	//localhost:8080/estudantes/paginacao?pagina=0&itensPorPagina=2&ordenacao=nome&tipoOrdenacao=ASC
	@GetMapping("paginacao")
	public Page<Estudante> buscarEstudantePorPaginacao
	(@RequestParam @Schema(example = ExampleValues.PAGINA) Integer pagina,
			@RequestParam @Schema(example = ExampleValues.ITENS_POR_PAGINA) Integer itensPorPagina,
			@RequestParam @Schema(example = ExampleValues.ORDENACAO) String ordenacao,
			@RequestParam @Schema(example = ExampleValues.TIPO_ORDENACAO) String tipoOrdenacao) {
				
		PageRequest page = PageRequest.of(pagina, itensPorPagina, 
				(tipoOrdenacao.equals("ASC")? Sort.by(ordenacao).ascending(): Sort.by(ordenacao).descending()));
		return estudanteService.buscarEstudantePorPaginacao(page);
	}

}
