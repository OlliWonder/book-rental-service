package com.sber.java13spring.java13springproject.libraryproject.repository;

import com.sber.java13spring.java13springproject.libraryproject.model.GenericModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Абстрактный репозиторий
 * Необходим для работы абстрактного сервиса,
 * т.к. в абстрактном сервисе мы не можем использовать конкретный репозиторий,
 * а должны указывать параметризованный (GenericRepository)
 * @param <T> - Сущность с которой работает репозиторий
 */
@Component
public interface GenericRepository<T extends GenericModel> extends JpaRepository<T, Long> {
}
