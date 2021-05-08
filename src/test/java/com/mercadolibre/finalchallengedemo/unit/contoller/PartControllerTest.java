package com.mercadolibre.finalchallengedemo.unit.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.finalchallengedemo.controller.PartController;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.PartResponseDTO;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidPartFilterException;
import com.mercadolibre.finalchallengedemo.exceptions.PartsNotFoundException;
import com.mercadolibre.finalchallengedemo.service.PartServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PartControllerTest {
    @Mock
    private PartServiceImpl partService;
    private PartController partController;

    private Date pastDate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.partController = new PartController(partService);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 02);
        c.set(Calendar.DATE, 26);
        c.set(Calendar.YEAR, 2021);
        pastDate = c.getTime();
    }

    @Test
    @DisplayName("When get parts without filter, then return Ok response")
    public void whenGetPartsWithoutFilter_thenReturnOkResponse() {
        List<PartDTO> partsList = getList("classpath:allParts.json",PartDTO.class);
        PartResponseDTO responseDTO = new PartResponseDTO(partsList);
        when(this.partService.getAll()).thenReturn(responseDTO);

        final ResponseEntity<PartResponseDTO> response = partController.getParts(new PartFilterDTO());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(partsList,response.getBody().getParts());
        verify(partService,times(1)).getAll();
    }

    @Test
    @DisplayName("When get parts with valid filter, then return ok Response")
    public void whenGetPartsWithValidFilter_thenReturnOkResponse() {
        List<PartDTO> partsList = getList("classpath:allParts.json",PartDTO.class);
        PartResponseDTO responseDTO = new PartResponseDTO(partsList);
        when(this.partService.getPartsByFilter(any())).thenReturn(responseDTO);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('V');
        validFilter.setDate(pastDate);

        final ResponseEntity<PartResponseDTO> response = partController.getParts(validFilter);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(partsList,response.getBody().getParts());
        verify(partService,times(1)).getPartsByFilter(any());
    }

    @Test
    @DisplayName("When get parts with invalid filter, then throws InvalidPartFilterException")
    public void whenGetPartsWithInvalidFilter_thenThrowsInvalidPartFilterException() {
        PartFilterDTO invalidFilter = new PartFilterDTO();
        invalidFilter.setDate(pastDate);
        invalidFilter.setQueryType('C');
        assertThrows(InvalidPartFilterException.class, () -> partController.getParts(invalidFilter));
        verify(partService,times(0)).getPartsByFilter(any());
    }

    @Test
    @DisplayName("When get parts with invalid filter because of query type, then throws InvalidPartFilterException")
    public void whenGetPartsWithInvalidFilterBecauseOfQueryType_thenThrowsInvalidPartFilterException() {
        PartFilterDTO invalidFilter = new PartFilterDTO();
        invalidFilter.setDate(pastDate);
        invalidFilter.setQueryType('Z');
        assertThrows(InvalidPartFilterException.class, () -> partController.getParts(invalidFilter));
        verify(partService,times(0)).getPartsByFilter(any());
    }

    @Test
    @DisplayName("When find part by id, then return ok Response")
    public void whenFindPartById_thenReturnOkResponse() {
        PartDTO part = new PartDTO();
        when(this.partService.findPart(any())).thenReturn(part);

        final ResponseEntity<PartDTO> response = partController.findPart(2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody(),part);
        verify(partService,times(1)).findPart(any());
    }

    @Test
    @DisplayName("When find part by id which not exists, then throw Not Found")
    public void whenFindPartByIdWhichNotExists_thenThrowNotFound() {
        when(this.partService.findPart(any())).thenThrow(PartsNotFoundException.class);
        assertThrows(PartsNotFoundException.class,() -> partController.findPart(23423));
        verify(partService,times(1)).findPart(any());
    }

    @Test
    @DisplayName("When save part, then return ok Response")
    public void whenSavePart_thenReturnOkResponse() {
        PartDTO part = new PartDTO();
        part.setPartCode(1);
        part.setDescription("test");
        part.setLastModification(pastDate);
        part.setLongDimension(11);
        part.setNetWeight(11);
        part.setNormalPrice(11D);
        part.setTallDimension(11);
        part.setWidthDimension(11);

        final ResponseEntity response = partController.savePart(part);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(partService,times(1)).savePart(any());
    }

    @Test
    @DisplayName("When update part, then return ok Response")
    public void whenUpdatePart_thenReturnOkResponse() {
        PartDTO part = new PartDTO();
        part.setPartCode(1);
        part.setDescription("test");
        part.setLastModification(pastDate);
        part.setLongDimension(11);
        part.setNetWeight(11);
        part.setNormalPrice(11D);
        part.setTallDimension(11);
        part.setWidthDimension(11);

        final ResponseEntity response = partController.update(part);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(partService,times(1)).savePart(any());
    }

    @Test
    @DisplayName("When delete part, then return ok Response")
    public void whenDeletePart_thenReturnOkResponse() {
        assertEquals(HttpStatus.OK, partController.deletePart(2).getStatusCode());
        verify(partService,times(1)).deletePart(any());
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
