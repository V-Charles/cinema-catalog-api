package br.com.casacultural.cinema.controller;

import br.com.casacultural.cinema.model.Analise;
import br.com.casacultural.cinema.model.Filme;
import br.com.casacultural.cinema.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeRestController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<Filme> listarTodos() {
        return filmeService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable int id) {
        Filme filme = filmeService.buscarPorId(id);
        if (filme != null) {
            return ResponseEntity.ok(filme);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Filme cadastrarFilme(@RequestBody Filme filme) {
        return filmeService.cadastrar(filme);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable int id, @RequestBody Filme filmeAtualizado) {
        Filme filmeExistente = filmeService.buscarPorId(id);
        if (filmeExistente != null) {
            filmeExistente.setTitulo(filmeAtualizado.getTitulo());
            filmeExistente.setSinopse(filmeAtualizado.getSinopse());
            filmeExistente.setGenero(filmeAtualizado.getGenero());
            filmeExistente.setAnoLancamento(filmeAtualizado.getAnoLancamento());
            filmeService.cadastrar(filmeExistente);
            return ResponseEntity.ok(filmeExistente);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFilme(@PathVariable int id) {
        Filme filme = filmeService.buscarPorId(id);
        if (filme != null) {
            filmeService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/analise")
    public ResponseEntity<Analise> adicionarAnalise(@PathVariable int id, @RequestBody Analise analise) {
        Analise novaAnalise = filmeService.adicionarAnalise(id, analise);
        if (novaAnalise != null) {
            return ResponseEntity.ok(novaAnalise);
        }
        return ResponseEntity.notFound().build();
    }
}
