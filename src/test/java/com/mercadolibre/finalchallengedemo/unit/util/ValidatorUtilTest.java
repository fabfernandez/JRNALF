package com.mercadolibre.finalchallengedemo.unit.util;

import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidPartFilterException;
import com.mercadolibre.finalchallengedemo.util.ValidatorUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorUtilTest {

    @Test
    @DisplayName("Given filter with query type but non date, then throw invalid filter exception")
    public void givenFilterWithQueryTypeButNonDate_thenThrowInvalidFilterException() {
        PartFilterDTO filter = new PartFilterDTO();
        filter.setQueryType('V');
        assertThrows(InvalidPartFilterException.class, () -> ValidatorUtil.validatePartFilter(filter));
    }

    @Test
    @DisplayName("Given filter with date but non query type, then throw invalid filter exception")
    public void givenFilterWithDateButNonQueryType_thenThrowInvalidFilterException() {
        PartFilterDTO filter = new PartFilterDTO();
        filter.setDate(Date.from(Instant.now()));
        assertThrows(InvalidPartFilterException.class, () -> ValidatorUtil.validatePartFilter(filter));
    }


}
