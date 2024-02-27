package com.tsaidenis.department.mapper;

public interface Mapper <D, S> {
    D map(S source);
}
