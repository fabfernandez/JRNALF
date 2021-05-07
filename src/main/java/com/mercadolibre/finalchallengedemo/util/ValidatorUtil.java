package com.mercadolibre.finalchallengedemo.util;

import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.partsorders.PartOrderQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidOrderFilterException;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidPartFilterException;

public class ValidatorUtil {
    public static void validatePartFilter(PartFilterDTO filter) {
        validatePartFilterQueryType(filter);
        validatePartFilterOrder(filter);
        validatePartFilterDate(filter);
    }

    private static void validatePartFilterOrder(PartFilterDTO filter) {
        if(filter.getOrder() != null && (filter.getQueryType() == null || filter.getDate() == null))
            throw new InvalidPartFilterException("Enter queryType and date.");
    }

    private static void validatePartFilterDate(PartFilterDTO filter) {
        if(filter.getDate() != null && filter.getQueryType() == null)
            throw new InvalidPartFilterException("Enter queryType.");
    }

    private static void validatePartFilterQueryType(PartFilterDTO filter) {
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

    // Validations for the input parameters of requirement 2
    public static void validateQueryParamFilter(PartOrderQueryParamsDTO filter) {
        validateQueryParamDealer(filter);
    }


    private static void validateQueryParamDealer(PartOrderQueryParamsDTO filter) {
        if (filter.getDealerNumber() == null || filter.getDeliveryStatus() != null){
            throw new InvalidOrderFilterException("The Dealer Number cannot be null");
        }
    }

}
