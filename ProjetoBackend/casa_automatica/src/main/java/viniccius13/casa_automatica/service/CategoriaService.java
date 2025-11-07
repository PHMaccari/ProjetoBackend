package viniccius13.casa_automatica.service;

import viniccius13.casa_automatica.model.Categoria;
import viniccius13.casa_automatica.repository.CategoriaRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class CategoriaService {


    private final CategoriaRepository categoriaRepository;


    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }


    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }


    public Categoria salvar(Categoria categoria) {
        categoriaRepository.findByTipo(categoria.getTipo()).ifPresent(c -> {
            throw new RuntimeException("Categoria já existe");
        });
        return categoriaRepository.save(categoria);
    }


    public Optional<Categoria> atualizar(Long id, Categoria dadosAtualizados) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setTipo(dadosAtualizados.getTipo());
            categoria.setDescricao(dadosAtualizados.getDescricao());
            return categoriaRepository.save(categoria);
        });
    }


    public boolean deletar(Long id) {
        if(categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
