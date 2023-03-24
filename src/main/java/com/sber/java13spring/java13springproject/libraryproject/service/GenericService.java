package com.sber.java13spring.java13springproject.libraryproject.service;

import com.sber.java13spring.java13springproject.libraryproject.dto.GenericDTO;
import com.sber.java13spring.java13springproject.libraryproject.exception.MyDeleteException;
import com.sber.java13spring.java13springproject.libraryproject.mapper.GenericMapper;
import com.sber.java13spring.java13springproject.libraryproject.model.GenericModel;
import com.sber.java13spring.java13springproject.libraryproject.repository.GenericRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Абстрактный сервис, который хранит в себе реализацию CRUD операций по-умолчанию.
 * Если реализация отличная от того, что представлено в этом классе,
 * то она переопределяется в реализации конкретного сервиса.
 *
 * @param <T> - Сущность, с которой мы работаем.
 * @param <N> - DTO, которую мы будем отдавать/принимать дальше.
 */
@Service
public abstract class GenericService<T extends GenericModel, N extends GenericDTO> {
    
    //Инжектим абстрактный репозиторий для работы с базой данных
    protected final GenericRepository<T> repository;
    //Инжектим абстрактный маппер для преобразований из DTO -> Entity, и обратно.
    protected final GenericMapper<T, N> mapper;
    
    protected GenericService(GenericRepository<T> repository,
                             GenericMapper<T, N> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    /**
     * Метод, возвращающий полный список всех сущностей.
     *
     * @return Список сконвертированных сущностей в DTO
     */
    public List<N> listAll() {
        return mapper.toDTOs(repository.findAll());
    }
    
    /***
     * Получить информацию о конкретном объекте/сущности по ID.
     *
     * @param id - идентификатор сущности для поиска.
     * @return - конкретная сущность в формате DTO
     */
    public N getOne(Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(() -> new NotFoundException("Данных по заданному id: " + id + " не найдены")));
    }
    
    /***
     * Создание сущности в БД.
     *
     * @param object - информация о сущности/объекте.
     * @return - сохраненная в БД сущность в формате DTO.
     */
    public N create(N object) {
        object.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        object.setCreatedWhen(LocalDateTime.now());
        return mapper.toDTO(repository.save(mapper.toEntity(object)));
    }
    
    /***
     * Обновление сущности в БД.
     *
     * @param object - информация о сущности/объекте.
     * @return - обновленная в БД сущность в формате DTO.
     */
    public N update(N object) {
        return mapper.toDTO(repository.save(mapper.toEntity(object)));
    }
    
    /***
     * Удаление сущности из БД.
     *
     * @param id - идентификатор сущности, которая должна быть удалена.
     */
    public void delete(Long id) throws MyDeleteException {
        repository.deleteById(id);
    }
}
