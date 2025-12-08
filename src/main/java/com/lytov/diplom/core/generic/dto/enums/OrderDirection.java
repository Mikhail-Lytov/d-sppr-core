package com.lytov.diplom.core.generic.dto.enums;


import com.lytov.diplom.core.generic.dto.SortRequestDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

/**
 * This enum represents sort direction of filtered entity.
 */
public enum OrderDirection {

    /**
     * Order by key ascending.
     */
    ASC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequestDTO request) {
            return cb.asc(this.getPath(root, request));
        }
    },

    /**
     * Order by key descending.
     */
    DESC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequestDTO request) {
            return cb.desc(this.getPath(root, request));
        }
    };

    public abstract <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequestDTO request);


    public <T, V> Path<V> getPath(Root<T> root, SortRequestDTO request) {
        String[] keys = request.getKey().split("\\.");
        Path<V> path = root.get(keys[0]);
        for (int i = 1; i < keys.length; i++) {
            path = path.get(keys[i]);
        }
        return path;
    }

}

