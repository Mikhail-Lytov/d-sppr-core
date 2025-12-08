package com.lytov.diplom.core.generic.dto.enums;

import com.lytov.diplom.core.generic.dto.FilterRequestDTO;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This enum represents operator of searching entity e.g. table
 * By default LIKE operator can be used to filter tables
 */
@Slf4j
public enum Operator {

    /**
     * Filter when given value is exactly the same.
     */
    EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDTO request, Predicate predicate) {
            Expression<?> key;
            Object value;

            if (request.getFieldType() == FieldType.ENUM) {
                key = root.get(request.getKey()).as(String.class);
                value = request.getValue();
            } else {
                key = this.getPath(root, request);
                value = request.getFieldType().parse(request.getValue().toString());
            }
            return cb.and(cb.equal(key, value), predicate);
        }
    },
    /**
     * Filter when given value is not the same.
     */
    NOT_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDTO request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Expression<?> key = this.getPath(root, request);
            return cb.and(cb.notEqual(key, value), predicate);
        }
    },

    /**
     * Filter when given value is in the text.
     */
    LIKE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDTO request, Predicate predicate) {
            Expression<String> key;

            if (request.getFieldType() == FieldType.UUID_ID) {
                key = cb.upper(this.getPath(root, request).as(String.class));
            } else {
                key = cb.upper(this.getPath(root, request));
            }

            return cb.and(cb.like(key, "%" + request.getValue().toString().toUpperCase() + "%"), predicate);
        }
    },


    /**
     * Filter when given value is in array of values.
     */
    IN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDTO request, Predicate predicate) {
            List<Object> values = request.getValues();
            CriteriaBuilder.In<Object> inClause = cb.in(this.getPath(root, request));
            for (Object value : values) {
                if (request.getFieldType() == FieldType.ENUM) {
                    inClause.value(value);
                } else {
                    inClause.value(request.getFieldType().parse(value.toString()));
                }
            }
            return cb.and(inClause, predicate);
        }
    },

    /**
     * Filter when given value is in between of values.
     */
    BETWEEN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDTO request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Object valueTo = request.getFieldType().parse(request.getValueTo().toString());
            if (request.getFieldType() == FieldType.DATE) {
                LocalDateTime startDate = (LocalDateTime) value;
                LocalDateTime endDate = (LocalDateTime) valueTo;
                Expression<LocalDateTime> key = this.getPath(root, request);
                return cb
                        .and(cb
                                .and(cb.greaterThanOrEqualTo(key, startDate),
                                        cb.lessThanOrEqualTo(key, endDate)),
                                predicate);
            }

            if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
                Number start = (Number) value;
                Number end = (Number) valueTo;
                Expression<Number> key = this.getPath(root, request);
                return cb.and(cb.and(cb.ge(key, start), cb.le(key, end)), predicate);
            }

            log.info("Can not use between for {} field type.", request.getFieldType());
            return predicate;
        }
    };

    public abstract <T> Predicate build(Root<T> root,
                                        CriteriaBuilder cb,
                                        FilterRequestDTO request,
                                        Predicate predicate);


    public <T, V> Path<V> getPath(Root<T> root, FilterRequestDTO request) {
        String[] keys = request.getKey().split("\\.");
        Path<V> path = root.get(keys[0]);
        for (int i = 1; i < keys.length; i++) {
            path = path.get(keys[i]);
        }
        return path;
    }
}
