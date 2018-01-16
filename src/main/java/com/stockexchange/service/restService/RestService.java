package com.stockexchange.service.restService;

import com.stockexchange.exception.AlreadyOccupiedIdException;
import com.stockexchange.exception.InvalidMethodNamesException;
import com.stockexchange.exception.UnavailableElementException;
import com.stockexchange.model.PossessId;

public interface RestService<T extends PossessId> {
    Iterable<T> getAll() throws UnavailableElementException;
    T get(Integer id) throws UnavailableElementException;
    T post(T obj) throws AlreadyOccupiedIdException;
    void deleteById(Integer id) throws UnavailableElementException;
    void put(T obj) throws UnavailableElementException;
    void patch(T obj) throws UnavailableElementException, InvalidMethodNamesException;
    boolean exists(Integer id);
}
