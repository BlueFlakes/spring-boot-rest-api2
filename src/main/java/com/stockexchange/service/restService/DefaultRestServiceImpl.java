package com.stockexchange.service.restService;

import com.stockexchange.dao.CommonRepository;
import com.stockexchange.exception.*;
import com.stockexchange.model.Customer;
import com.stockexchange.model.PossessArchivedStatus;
import com.stockexchange.model.PossessId;
import com.stockexchange.service.ObjectFieldValueSwapper;

import java.util.function.Consumer;

public abstract class DefaultRestServiceImpl<T extends PossessId & PossessArchivedStatus,
                                             U extends CommonRepository<T, Integer>>
                                             implements RestService<T> {
    private U objectDao;
    private ObjectFieldValueSwapper fieldValueSwapper;

    public DefaultRestServiceImpl() {}

    public DefaultRestServiceImpl(U objectDao, ObjectFieldValueSwapper fieldValueSwapper) {
        this.objectDao = objectDao;
        this.fieldValueSwapper = fieldValueSwapper;
    }


    @Override
    public Iterable<T> getAll( ) {
        return this.objectDao.findAllByArchivedIsFalse();
    }

    @Override
    public T get(Integer id) throws UnavailableElementException {
        if (existsAndArchivedIsFalse(id)) {
            return this.objectDao.findOne(id);
        }

        throw new UnavailableElementException();
    }

    @Override
    public void deleteById(Integer id) throws UnavailableElementException {
        if (existsAndArchivedIsFalse(id)) {
            archive(id);
            return;
        }

        throw new UnavailableElementException();
    }

    private void archive(Integer id) throws UnavailableElementException {
        T foundObject = get(id);
        foundObject.setArchived(true);
        this.objectDao.save(foundObject);
    }

    @Override
    public T post(T obj) throws AlreadyOccupiedIdException {
        Integer id = obj.getId();

        if (id == null | (id != null && !this.objectDao.exists(id))) {
            return this.objectDao.save(obj);
        }

        throw new AlreadyOccupiedIdException();
    }

    @Override
    public void put(T obj) throws UnavailableElementException {
        Integer id = obj.getId();

        if (id != null && existsAndArchivedIsFalse(id)) {
            this.objectDao.save(obj);
            return;
        }

        throw new UnavailableElementException();
    }

    @Override
    public void patch(T obj) throws UnavailableElementException, InvalidMethodNamesException {
        Integer id = obj.getId();

        if (id != null && existsAndArchivedIsFalse(id)) {
            T foundObjectFromDbById = this.get(id);
            this.fieldValueSwapper.antiNullSwap(obj, foundObjectFromDbById);

            this.objectDao.save(foundObjectFromDbById);
            return;
        }

        throw new UnavailableElementException();
    }

    private boolean existsAndArchivedIsFalse(Integer id) {
        T loadedObjFromDatabase = this.objectDao.findByArchivedIsFalseAndIdEquals(id);

        return loadedObjFromDatabase != null;
    }
}
