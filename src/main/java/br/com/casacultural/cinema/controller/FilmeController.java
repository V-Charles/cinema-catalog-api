package br.com.casacultural.cinema.controller;

import br.com.casacultural.cinema.model.Analise;
import br.com.casacultural.cinema.model.Filme;
import br.com.casacultural.cinema.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeService.listarTodos());
        return "listagem";
    }

    @GetMapping("/cadastrar")
    public String formCadastro(Model model) {
        model.addAttribute("filme", new Filme());
        return "cadastro";
    }

    @PostMapping("/cadastrar")
    public String salvarFilme(@ModelAttribute Filme filme) {
        filmeService.cadastrar(filme);
        return "redirect:/filmes";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesFilme(@PathVariable int id, Model model) {
        Filme filme = filmeService.buscarPorId(id);
        model.addAttribute("filme", filme);
        return "detalhes";
    }

    @PostMapping("/detalhes/{id}/analise")
    public String adicionarAnalise(@PathVariable int id,
                                   @RequestParam String analise,
                                   @RequestParam double nota) {
        Analise novaAnalise = new Analise(analise, nota);
        filmeService.adicionarAnalise(id, novaAnalise);
        return "redirect:/filmes/detalhes/" + id;
    }

    @GetMapping("/editar/{id}")
    public String formEdicao(@PathVariable int id, Model model) {
        Filme filme = filmeService.buscarPorId(id);
        model.addAttribute("filme", filme);
        return "edicao";
    }
}