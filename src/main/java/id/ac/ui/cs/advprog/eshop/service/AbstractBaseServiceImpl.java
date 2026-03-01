package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.repository.BaseRepository;
import id.ac.ui.cs.advprog.eshop.util.IteratorToList;


import java.util.List;

public abstract class AbstractBaseServiceImpl<T> implements BaseService<T> {

    protected abstract BaseRepository<T> getRepository();

    @Override
    public T create(final T item){
        return getRepository().create(item);
    }

    @Override
    public List<T> findAll(){
        return IteratorToList.convertIteratorToList(getRepository().findAll());
    }

    @Override
    public T findById(final String id){
        return getRepository().findById(id);
    }

    @Override
    public T update(final String id,final T item){
        return getRepository().update(id, item);
    }

    @Override
    public void deleteById(final String id){
        getRepository().deleteById(id);
    }
}
