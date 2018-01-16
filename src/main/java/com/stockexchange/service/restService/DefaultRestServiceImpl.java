package com.stockexchange.service.restService;

import com.stockexchange.exception.AlreadyOccupiedIdException;
import com.stockexchange.exception.InvalidMethodNamesException;
import com.stockexchange.exception.UnavailableElementException;
import com.stockexchange.model.PossessId;
import com.stockexchange.service.ObjectFieldValueSwapper;
import org.springframework.data.repository.CrudRepository;

import java.util.function.Consumer;


public abstract class DefaultRestServiceImpl<T extends PossessId,
                                             U extends CrudRepository<T, Integer>>
                                             implements RestService<T> {
    private U objectDao;
    private ObjectFieldValueSwapper<T> fieldValueSwapper;

    public DefaultRestServiceImpl(U objectDao, ObjectFieldValueSwapper<T> fieldValueSwapper) {
        this.objectDao = objectDao;
        this.fieldValueSwapper = fieldValueSwapper;
    }

    @Override
    public Iterable<T> getAll() {
        return this.objectDao.findAll();
    }

    @Override
    public T get(Integer id) throws UnavailableElementException {
        if (exists(id)) {
            return this.objectDao.findOne(id);
        }

        throw new UnavailableElementException("Item with given Id does not exist.");
    }

    @Override
    public T post(T obj) throws AlreadyOccupiedIdException {
        Integer id = obj.getId();

        if (id == null | (id != null && !exists(id))) {
            return saveToDB(obj);
        }

        throw new AlreadyOccupiedIdException("Given id is already used.");
    }

    private T saveToDB(T obj) {
        return this.objectDao.save(obj);
    }

    @Override
    public void deleteById(Integer id) throws UnavailableElementException {
        doIfExist(id, n -> this.objectDao.delete(n));
    }

    private void doIfExist(Integer id, Consumer<Integer> consumer) throws UnavailableElementException {
        if (!exists(id)) {
            throw new UnavailableElementException("");
        }

        consumer.accept(id);
    }

    private void doIfExist(Integer id, T object, Consumer<T> consumer) throws UnavailableElementException {
        if (!exists(id)) {
            throw new UnavailableElementException("");
        }

        consumer.accept(object);
    }

    @Override
    public void put(T obj) throws UnavailableElementException {
        doIfExist(obj.getId(), obj, o -> this.objectDao.save(o));
    }

    @Override
    public void patch(T deliveredObject) throws UnavailableElementException, InvalidMethodNamesException {
        T loadedObjectFromDb = get(deliveredObject.getId());

        fieldValueSwapper.antiNullSwap(deliveredObject, loadedObjectFromDb);
        this.saveToDB(loadedObjectFromDb);
    }

    @Override
    public boolean exists(Integer id) {
        return this.objectDao.exists(id);
    }
}
