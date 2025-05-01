package hei.school.championship.dao.operations;

import java.util.List;

public interface CrudOperationsAlt<E> {
    List<E> getAll(int page, int size);

    E findById(int id);

    // Both create (if does not exist) or update (if exist) entities
    List<E> saveAll(List<E> entities);
}
