package com.mercadolibre.finalchallengedemo.unit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.PartResponseDTO;
import com.mercadolibre.finalchallengedemo.entities.PartsResponseEntity;
import com.mercadolibre.finalchallengedemo.repository.IPartRepository;
import com.mercadolibre.finalchallengedemo.service.PartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.util.ResourceUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PartServiceTest {
    @Mock
    private IPartRepository partRepository;
    private PartServiceImpl partService;

    private Date pastDate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.partService = new PartServiceImpl(partRepository,new ModelMapper());
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 02);
        c.set(Calendar.DATE, 26);
        c.set(Calendar.YEAR, 2021);
        pastDate = c.getTime();
    }

    @Test
    @DisplayName("Given parts, then return all parts")
    public void givenPartsWithoutFilter_thenReturnAllParts() {
        List<PartsResponseEntity> partsEntityList = getList("classpath:allPartsEntities.json",PartsResponseEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:allParts.json",PartDTO.class);

        when(this.partRepository.findAll()).thenReturn(partsEntityList);

        final PartResponseDTO response = partService.getAll();

        assertEquals(partsDtoList.size(), response.getParts().size());
        verify(partRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Given parts, then return filtered parts")
    public void givenParts_thenReturnFilteredParts() {
        List<PartsResponseEntity> partsEntityList = getList("classpath:allPartsEntities.json",PartsResponseEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:allParts.json",PartDTO.class);

        when(this.partRepository.findPartsModifiedSinceDate(any(),any(),any())).thenReturn(partsEntityList);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('P');
        validFilter.setDate(pastDate);

        final PartResponseDTO response = partService.getPartsByFilter(validFilter);

        assertEquals(partsDtoList.size(), response.getParts().size());
        verify(partRepository,times(1)).findPartsModifiedSinceDate(any(),any(),any());
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


