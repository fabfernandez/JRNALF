package com.mercadolibre.finalchallengedemo.unit.contoller;

import com.mercadolibre.finalchallengedemo.controller.PartController;
import com.mercadolibre.finalchallengedemo.service.PartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PartControllerTest {
    @Mock
    private PartServiceImpl partService;
    private PartController partController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.partController = new PartController(partService);
    }

    @Test
    @DisplayName("When get parts without filter, then return Ok response")
    public void whenGetPartsWithoutFilter_thenReturnOkResponse() {

    }

}
