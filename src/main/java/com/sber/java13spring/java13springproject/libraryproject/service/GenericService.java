package com.sber.java13spring.java13springproject.libraryproject.service;

import com.sber.java13spring.java13springproject.libraryproject.dto.GenericDTO;
import com.sber.java13spring.java13springproject.libraryproject.mapper.GenericMapper;
import com.sber.java13spring.java13springproject.libraryproject.model.GenericModel;
import com.sber.java13spring.java13springproject.libraryproject.repository.GenericRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Абстрактный сервис который хранит в себе реализацию CRUD операций по умолчанию
 * Если реализация отличная от того что представлено в этом классе,
 * то она переопределяется в сервисе для конкретной сущности
 *
 * @param <T> - Сущность с которой мы работаем
 * @param <N> - DTO, которую мы будем отдавать/принимать дальше
 */
@Service
public abstract class GenericService<T extends GenericModel, N extends GenericDTO> {
    
    //Инжектим абстрактный репозиторий для работы с базой данных
    protected final GenericRepository<T> repository;
    protected final GenericMapper<T, N> mapper;
    
    protected GenericService(GenericRepository<T> repository, GenericMapper<T, N> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    public List<N> listAll() {
        return mapper.toDTOs(repository.findAll());
    }
    
    public N getOne(Long id) {
        return mapper.toDTO(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Данные по заданному id: " + id + " не найдены")));
    }
    
    public N create(N object) {
        //переделаем, когда будет спринг сисуриту
        object.setCreatedBy("Admin");
        object.setCreatedWhen(LocalDateTime.now());
        return mapper.toDTO(repository.save(mapper.toEntity(object)));
    }
    
    public N update(N object) {
        return mapper.toDTO(repository.save(mapper.toEntity(object)));
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
