package com.mercadolibre.finalchallengedemo.util;

import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidPartFilterException;

public class ValidatorUtil {
    public static void validatePartFilter(PartFilterDTO filter) {
        if( (filter.getQueryType().equals('P') || filter.getQueryType().equals('V'))  && filter.getDate() == null)
            throw new InvalidPartFilterException("Date can not be null.");
        if(filter.getOrder() != null && filter.getOrder() > 3 && filter.getOrder() < 1 )
            throw new InvalidPartFilterException("Order must be 1,2 or 3.");
    }
}
