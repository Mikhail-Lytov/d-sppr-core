package com.lytov.diplom.core.repository;

import com.lytov.diplom.core.generic.dto.FilterRequestDTO;
import com.lytov.diplom.core.generic.dto.SearchRequestDTO;
import com.lytov.diplom.core.generic.dto.SortRequestDTO;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {

    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_ITEMS_SIZE = 100;
    private final SearchRequestDTO request;

    @Override
    public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);

        for (FilterRequestDTO filter : this.request.getFilters()) {
            log.info("Filter: {} {} {}", filter.getKey(), filter.getOperator().toString(), filter.getValue());
            predicate = filter.getOperator().build(root, cb, filter, predicate);
        }

        List<Order> orders = new ArrayList<>();
        for (SortRequestDTO sort : this.request.getSorts()) {
            orders.add(sort.getDirection().build(root, cb, sort));
        }

        query.orderBy(orders);
        return predicate;
    }

    /**
     * Gets PageRequest of given page and size or defaults.
     *
     * @param page - data page number
     * @param size - data size
     * @return Pageable
     */
    public static Pageable getPageable(Integer page, Integer size) {
        return PageRequest.of(Objects.requireNonNullElse(page, DEFAULT_PAGE_NUMBER),
                Objects.requireNonNullElse(size, DEFAULT_ITEMS_SIZE));
    }

}
