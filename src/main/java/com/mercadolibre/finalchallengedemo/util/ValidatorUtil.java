package com.mercadolibre.finalchallengedemo.util;

import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidPartFilterException;

public class ValidatorUtil {
    public static void validatePartFilter(PartFilterDTO filter) {
        if (filter.getQueryType() != null) {
            switch (filter.getQueryType()) {
                case 'P':
                case 'V':
                    if (filter.getDate() == null)
                        throw new InvalidPartFilterException("Date can not be null.");
                    break;
                case 'C':
                    if (filter.getDate() != null)
                        throw new InvalidPartFilterException("Date does not apply to queryType C.");
                    break;
                default:
                    throw new InvalidPartFilterException("Query type must be C, P or V.");
            }
        }
    }
}
