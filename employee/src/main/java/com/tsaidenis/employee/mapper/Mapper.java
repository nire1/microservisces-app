package com.tsaidenis.employee.mapper;

import java.util.List;

public interface Mapper<D,S> {
    D map (S source);

}
