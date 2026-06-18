package br.com.casacultural.cinema.service;

import br.com.casacultural.cinema.model.Analise;
import br.com.casacultural.cinema.model.Filme;
import br.com.casacultural.cinema.repository.AnaliseRepository;
import br.com.casacultural.cinema.repository.FilmeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmeServiceTest {

    @Mock
    private FilmeRepository filmeRepository;

    @Mock
    private AnaliseRepository analiseRepository;

    @InjectMocks
    private FilmeService filmeService;

    @Test
    public void testeListarTodos(){
        Filme filme1 = new Filme();
        filme1.setTitulo("Matrix");
        Filme filme2 = new Filme();
        filme2.setTitulo("Inception");

        when(filmeRepository.findAll()).thenReturn(Arrays.asList(filme1, filme2));

        List<Filme> resultado = filmeService.listarTodos();

        assertEquals(2, resultado.size());
        assertEquals("Matrix", resultado.get(0).getTitulo());
        verify(filmeRepository, times(1)).findAll();
    }

    @Test
    public void testeCadastrar(){
        Filme filme = new Filme();
        filme.setTitulo("Interstellar");

        when(filmeRepository.save(any(Filme.class))).thenReturn(filme);

        Filme resultado = filmeService.cadastrar(new Filme());

        assertNotNull(resultado);
        assertEquals("Interstellar", resultado.getTitulo());
        verify(filmeRepository, times(1)).save(any(Filme.class));
    }

    @Test
    public void testeBuscarPorIdEncontrado(){
        Filme filme = new Filme();
        filme.setId(1);
        filme.setTitulo("O Senhor dos Anéis");

        when(filmeRepository.findById(1)).thenReturn(Optional.of(filme));

        Filme resultado = filmeService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("O Senhor dos Anéis", resultado.getTitulo());
    }

    @Test
    public void testeBuscarPorIdNaoEncontrado(){
        when(filmeRepository.findById(99)).thenReturn(Optional.empty());

        Filme resultado = filmeService.buscarPorId(99);

        assertNull(resultado);
    }

    @Test
    public void testeAdicionarAnaliseComSucesso(){
        Filme filme = new Filme();
        filme.setId(1);

        Analise analise = new Analise("Muito bom", 9.5);

        when(filmeRepository.findById(1)).thenReturn(Optional.of(filme));
        when(analiseRepository.save(any(Analise.class))).thenReturn(analise);

        Analise resultado = filmeService.adicionarAnalise(1, analise);

        assertNotNull(resultado);
        assertEquals(9.5, resultado.getNota());
        verify(analiseRepository, times(1)).save(analise);
    }
}
