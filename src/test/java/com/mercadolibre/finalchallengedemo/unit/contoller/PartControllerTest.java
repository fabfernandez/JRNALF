package com.mercadolibre.finalchallengedemo.unit.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.finalchallengedemo.controller.PartController;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidPartFilterException;
import com.mercadolibre.finalchallengedemo.service.PartServiceImpl;
import org.hibernate.annotations.FilterDef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
        List<PartDTO> partsList = getList("classpath:allParts.json",PartDTO.class);
        when(this.partService.getAll()).thenReturn(partsList);

        final ResponseEntity<List<PartDTO>> response = partController.getParts(new PartFilterDTO());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(partsList,response.getBody());
        verify(partService,times(1)).getAll();
    }

    @Test
    @DisplayName("When get parts with valid filter, then return ok Response")
    public void whenGetPartsWithValidFilter_thenReturnOkResponse() {
        List<PartDTO> partsList = getList("classpath:allParts.json",PartDTO.class);
        when(this.partService.getPartsByFilter(any())).thenReturn(partsList);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('V');
        validFilter.setDate(LocalDate.of(2021,02,01));

        final ResponseEntity<List<PartDTO>> response = partController.getParts(validFilter);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(partsList,response.getBody());
        verify(partService,times(1)).getPartsByFilter(any());
    }

    @Test
    @DisplayName("When get parts with invalid filter, then throws InvalidPartFilterException")
    public void whenGetPartsWithInvalidFilter_thenThrowsInvalidPartFilterException() {
        PartFilterDTO invalidFilter = new PartFilterDTO();
        invalidFilter.setDate(LocalDate.of(2021,04,10));
        invalidFilter.setQueryType('C');
        assertThrows(InvalidPartFilterException.class, () -> partController.getParts(invalidFilter));
        verify(partService,times(0)).getPartsByFilter(any());
    }

    @Test
    @DisplayName("When get parts with invalid filter because of query type, then throws InvalidPartFilterException")
    public void whenGetPartsWithInvalidFilterBecauseOfQueryType_thenThrowsInvalidPartFilterException() {
        PartFilterDTO invalidFilter = new PartFilterDTO();
        invalidFilter.setDate(LocalDate.of(2021,04,10));
        invalidFilter.setQueryType('Z');
        assertThrows(InvalidPartFilterException.class, () -> partController.getParts(invalidFilter));
        verify(partService,times(0)).getPartsByFilter(any());
    }




    private static <T> T getList(String filePath, Class<?> target) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(ResourceUtils.getFile(filePath), objectMapper .getTypeFactory().constructCollectionType(List.class, Class.forName(target.getName())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <T> T getObject(String filePath, Class<?> target) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(ResourceUtils.getFile(filePath), objectMapper .getTypeFactory().constructType(target));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
