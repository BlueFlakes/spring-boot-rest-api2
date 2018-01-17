package com.stockexchange.controller;

import com.stockexchange.dao.CommonRepository;
import com.stockexchange.exception.AlreadyOccupiedIdException;
import com.stockexchange.exception.InvalidMethodNamesException;
import com.stockexchange.exception.UnavailableElementException;
import com.stockexchange.model.PossessArchivedStatus;
import com.stockexchange.model.PossessId;
import com.stockexchange.service.restService.DefaultRestServiceImpl;
import org.springframework.web.bind.annotation.*;

public abstract class WebController<T extends PossessId & PossessArchivedStatus,
                                    P extends CommonRepository<T, Integer>,
                                    U extends DefaultRestServiceImpl<T, P>> {
    private U restService;

    public WebController(U restService) {
        this.restService = restService;
    }

    @GetMapping(path = "/{id}")
    private T doGet(@PathVariable Integer id) throws UnavailableElementException {
        return this.restService.get(id);
    }

    @GetMapping(path = "")
    private Iterable<T> doGet() {
        return this.restService.getAll();
    }

    @PostMapping(path = "")
    private T doPost(@RequestBody T obj) throws AlreadyOccupiedIdException {
        return this.restService.post(obj);
    }

    @DeleteMapping(path = "/{id}")
    private void doDelete(@PathVariable Integer id) throws UnavailableElementException {
        this.restService.deleteById(id);
    }

    @PutMapping(path = "")
    private void doPut(@RequestBody T obj) throws UnavailableElementException {
        this.restService.put(obj);
    }

    @PatchMapping(path = "")
    private void doPatch(@RequestBody T obj) throws UnavailableElementException, InvalidMethodNamesException {
        this.restService.patch(obj);
    }
}
