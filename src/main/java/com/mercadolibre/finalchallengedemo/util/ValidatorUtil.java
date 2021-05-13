package com.mercadolibre.finalchallengedemo.util;

import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.PartOrderQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.exceptions.CanNotUpdateException;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidOrderFilterException;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidPartFilterException;

public class ValidatorUtil {

    //Validations for the filter from req 1
    //Params will be valid if:
    //order is 1,2 or 3.
    //date is past or present
    //queryType is C, P or V
    //All the valid filters are:
    //queryType 'C' with none order and date.
    //queryType 'P' or 'V', with a date.
    //queryType 'P' or 'V', with a date and a order.
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

    // Validations for the input parameters of requirement 2
    public static void validateQueryParamFilter(PartOrderQueryParamsDTO filter) {
        validateQueryParamDealer(filter);
    }


    private static void validateQueryParamDealer(PartOrderQueryParamsDTO filter) {
        if (filter.getDealerNumber() == null || filter.getDeliveryStatus() != null) {
            throw new InvalidOrderFilterException("The Dealer Number cannot be null");
        }
    }

    //Validations for all update scenarios
    public static void validateOrderUpdate(Character actualStatus, Character newStatus) {
        if (actualStatus.equals(newStatus))
            throw new CanNotUpdateException("The order to update al ready has " + actualStatus + " status.");
        if (actualStatus.equals('F') || actualStatus.equals('C'))
            throw new CanNotUpdateException("Can not update the status from the requested order.");
    }
}
