package com.PineGap.Project;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    final private List<DataSet> dataSets = DataSet.getDataSets();

    public Page<DataSet> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<DataSet> list;

        if (dataSets.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, dataSets.size());
            list = dataSets.subList(startItem, toIndex);
        }

        Page<DataSet> dataSetPage
          = new PageImpl<DataSet>(list, PageRequest.of(currentPage, pageSize), dataSets.size());

        return dataSetPage;
    }
}