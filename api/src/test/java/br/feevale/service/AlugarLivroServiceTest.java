package br.feevale.service;

import br.feevale.controller.response.DetalheLivroResponse;
import br.feevale.factories.LivroFactory;
import br.feevale.factories.UsuarioFactory;
import br.com.cwi.crescer.biblioteca.security.domain.Usuario;
import br.com.cwi.crescer.biblioteca.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.biblioteca.service.core.BuscarLivroService;
import br.com.cwi.crescer.biblioteca.service.core.NowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlugarLivroServiceTest {

    @InjectMocks
    private AlugarLivroService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private BuscarLivroService buscarLivroService;

    @Mock
    private NowService nowService;

    @Mock
    private LivroRepository livroRepository;

    @BeforeEach
    public void beforeEach() {
        ReflectionTestUtils.setField(tested, "prazoDevolucaoPadrao", 5L, Long.class);
    }

    @Test
    @DisplayName("Deve definir usuário e data de devolução e retornar detalhes do livro alugado")
    void deveDevolverLivroAlugado() {

        Livro livro = LivroFactory.getDisponivel();
        Usuario usuario = UsuarioFactory.get();
        Long livroId = livro.getId();
        Long responsavelId = usuario.getId();
        LocalDate dataDevolucao = LocalDate.of(2022, 4, 6);
        LocalDate hoje = LocalDate.of(2022, 4, 1);
        when(buscarLivroService.porId(livroId)).thenReturn(livro);
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(nowService.getDate()).thenReturn(hoje);

        DetalheLivroResponse response = tested.alugar(livroId);

        verify(buscarLivroService).porId(livroId);
        verify(usuarioAutenticadoService).get();
        verify(livroRepository).save(livro);
        assertEquals(responsavelId, response.getResponsavelId());
        assertEquals(dataDevolucao, response.getDataDevolucao());
    }

    @Test
    @DisplayName("Não deve alugar livro que está disponível")
    void naoDeveDevolverLivroDisponivel() {
        Livro livro = LivroFactory.getAlugado();
        Long livroId = livro.getId();
        when(buscarLivroService.porId(livroId)).thenReturn(livro);

        assertThrows(ResponseStatusException.class, () -> {
            tested.alugar(livroId);
        });

        verify(livroRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("Não deve alugar livro que não existe")
    void naoDeveDevolverLivroQueNaoEncontrado() {
        Livro livro = LivroFactory.getAlugado();
        Long livroId = livro.getId();
        doThrow(ResponseStatusException.class).when(buscarLivroService).porId(livroId);

        assertThrows(ResponseStatusException.class, () -> {
            tested.alugar(livroId);
        });

        verify(usuarioAutenticadoService, Mockito.never()).get();
        verify(livroRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("Deve definir data de devolução para segunda se alugado na segunda")
    void deveDevolverQuandoAlugadoSegunda() {

        Livro livro = LivroFactory.getDisponivel();
        Usuario usuario = UsuarioFactory.get();

        Long livroId = livro.getId();
        LocalDate hoje = LocalDate.of(2022, 4, 11);
        LocalDate dataDevolucao = LocalDate.of(2022, 4, 18);

        when(buscarLivroService.porId(livroId)).thenReturn(livro);
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(nowService.getDate()).thenReturn(hoje);

        DetalheLivroResponse response = tested.alugar(livroId);

        assertEquals(dataDevolucao, response.getDataDevolucao());
    }

    @Test
    @DisplayName("Deve definir data de devolução para segunda se alugado na terça")
    void deveDevolverQuandoAlugadoTerca() {

        Livro livro = LivroFactory.getDisponivel();
        Usuario usuario = UsuarioFactory.get();

        Long livroId = livro.getId();
        LocalDate hoje = LocalDate.of(2022, 4, 12);
        LocalDate dataDevolucao = LocalDate.of(2022, 4, 18);

        when(buscarLivroService.porId(livroId)).thenReturn(livro);
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(nowService.getDate()).thenReturn(hoje);

        DetalheLivroResponse response = tested.alugar(livroId);

        assertEquals(dataDevolucao, response.getDataDevolucao());
    }

    @Test
    @DisplayName("Deve definir data de devolução para mais 5 dias se alugado na quarta")
    void deveDevolverQuandoAlugadoQuarta() {

        Livro livro = LivroFactory.getDisponivel();
        Usuario usuario = UsuarioFactory.get();

        Long livroId = livro.getId();
        LocalDate hoje = LocalDate.of(2022, 4, 13);
        LocalDate dataDevolucao = LocalDate.of(2022, 4, 18);

        when(buscarLivroService.porId(livroId)).thenReturn(livro);
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(nowService.getDate()).thenReturn(hoje);

        DetalheLivroResponse response = tested.alugar(livroId);

        assertEquals(dataDevolucao, response.getDataDevolucao());
    }

    @Test
    @DisplayName("Deve definir data de devolução para mais 5 dias se alugado na quinta")
    void deveDevolverQuandoAlugadoQuinta() {

        Livro livro = LivroFactory.getDisponivel();
        Usuario usuario = UsuarioFactory.get();

        Long livroId = livro.getId();
        LocalDate hoje = LocalDate.of(2022, 4, 14);
        LocalDate dataDevolucao = LocalDate.of(2022, 4, 19);

        when(buscarLivroService.porId(livroId)).thenReturn(livro);
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(nowService.getDate()).thenReturn(hoje);

        DetalheLivroResponse response = tested.alugar(livroId);

        assertEquals(dataDevolucao, response.getDataDevolucao());
    }

    @Test
    @DisplayName("Deve definir data de devolução para mais 5 dias se alugado na sexta")
    void deveDevolverQuandoAlugadoSexta() {

        Livro livro = LivroFactory.getDisponivel();
        Usuario usuario = UsuarioFactory.get();

        Long livroId = livro.getId();
        LocalDate hoje = LocalDate.of(2022, 4, 15);
        LocalDate dataDevolucao = LocalDate.of(2022, 4, 20);

        when(buscarLivroService.porId(livroId)).thenReturn(livro);
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(nowService.getDate()).thenReturn(hoje);

        DetalheLivroResponse response = tested.alugar(livroId);

        assertEquals(dataDevolucao, response.getDataDevolucao());
    }
}
