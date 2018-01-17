package com.stockexchange.service.restService;

import com.stockexchange.exception.AlreadyOccupiedIdException;
import com.stockexchange.exception.AppCustomException;
import com.stockexchange.exception.InvalidMethodNamesException;
import com.stockexchange.exception.UnavailableElementException;
import com.stockexchange.model.PossessId;

public interface RestService<T extends PossessId> {
    Iterable<T> getAll();
    T get(Integer id) throws AppCustomException;
    T post(T obj) throws AppCustomException;
    void deleteById(Integer id) throws AppCustomException;
    void put(T obj) throws AppCustomException;
    void patch(T obj) throws AppCustomException;
}
