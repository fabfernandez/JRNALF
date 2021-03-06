package com.mercadolibre.finalchallengedemo.util;

import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.PartOrderQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.exceptions.CanNotUpdateException;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidOrderFilterException;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidPartFilterException;

public class ValidatorUtil {

    // Validating filter parameters for Req 1.
    public static void validatePartFilter(PartFilterDTO filter) {
        validatePartFilterQueryType(filter);
        validatePartFilterOrder(filter);
        validatePartFilterDate(filter);
    }

    private static void validatePartFilterOrder(PartFilterDTO filter) {
        if (filter.getOrder() != null && (filter.getQueryType() == null || filter.getDate() == null))
            throw new InvalidPartFilterException("Enter queryType and date.");
    }

    private static void validatePartFilterDate(PartFilterDTO filter) {
        if (filter.getDate() != null && filter.getQueryType() == null)
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
                    if (filter.getDate() != null || filter.getOrder() != null)
                        throw new InvalidPartFilterException("Date and Order do not apply to queryType C.");
                    break;
                default:
                    throw new InvalidPartFilterException("Query type must be C, P or V.");
            }
        }
    }

    //Validations for all update scenarios
    public static void validateOrderUpdate(Character actualStatus, Character newStatus) {
        if (actualStatus.equals(newStatus))
            throw new CanNotUpdateException("The order you are trying to update already has " + actualStatus + " status.");
        if (actualStatus.equals('F') || actualStatus.equals('C'))
            throw new CanNotUpdateException("Can not update the status from the requested order.");
    }
}
