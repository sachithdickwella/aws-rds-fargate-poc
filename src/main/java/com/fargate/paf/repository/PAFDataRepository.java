package com.fargate.paf.repository;

import com.fargate.paf.pojo.PAF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Sachith Dickwella
 */
public interface PAFDataRepository extends PagingAndSortingRepository<PAF, String> {

    Page<PAF> findAll(Pageable pageable);
}
