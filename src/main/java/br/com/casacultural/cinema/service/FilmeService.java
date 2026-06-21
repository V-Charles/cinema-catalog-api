package br.com.casacultural.cinema.service;

import br.com.casacultural.cinema.model.Analise;
import br.com.casacultural.cinema.model.Filme;
import br.com.casacultural.cinema.repository.AnaliseRepository;
import br.com.casacultural.cinema.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private AnaliseRepository analiseRepository;

    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    public Filme cadastrar(Filme filme) {
        return filmeRepository.save(filme);
    }

    public Filme buscarPorId(Integer id) {
        return filmeRepository.findByIdWithAnalises(id)
                .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado"));
    }

    public void excluir(Integer id) {
        filmeRepository.deleteById(id);
    }

    public Analise adicionarAnalise(Integer idFilme, Analise analise) {
        Filme filme = buscarPorId(idFilme);
        if (filme != null) {
            analise.setFilme(filme);
            return analiseRepository.save(analise);
        }
        return null;
    }
}