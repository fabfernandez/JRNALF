package com.mercadolibre.finalchallengedemo.unit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.PartResponseDTO;
import com.mercadolibre.finalchallengedemo.entities.PartEntity;
import com.mercadolibre.finalchallengedemo.entities.StockSubsidiaryEntity;
import com.mercadolibre.finalchallengedemo.exceptions.PartsNotFoundException;
import com.mercadolibre.finalchallengedemo.repository.IPartRepository;
import com.mercadolibre.finalchallengedemo.repository.IStockRepository;
import com.mercadolibre.finalchallengedemo.security.DecodeToken;
import com.mercadolibre.finalchallengedemo.service.PartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.Part;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PartServiceTest {
    @Mock
    private IPartRepository partRepository;
    @Mock
    private IStockRepository stockRepository;

    private PartServiceImpl partService;

    private Date pastDate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.partService = new PartServiceImpl(partRepository, stockRepository,new ModelMapper());
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 02);
        c.set(Calendar.DATE, 26);
        c.set(Calendar.YEAR, 2021);
        pastDate = c.getTime();
    }

    @Test
    @DisplayName("Given parts, then return all parts")
    public void givenPartsWithoutFilter_thenReturnAllParts() {
        List<PartEntity> partsEntityList = getList("classpath:allPartsEntities.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:allParts.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findAll()).thenReturn(partsEntityList);

        final PartResponseDTO response = partService.getAll();

        assertIterableEquals(response.getParts(), partsDtoList);
        verify(partRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Given parts, then return filtered parts")
    public void givenParts_thenReturnFilteredParts() {
        List<PartEntity> partsEntityList = getList("classpath:filteredPartsEntities.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:filteredParts.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findPartsModifiedSinceDate(any(),any())).thenReturn(partsEntityList);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('P');
        validFilter.setDate(pastDate);

        final PartResponseDTO response = partService.getPartsByFilter(validFilter);

        assertIterableEquals(response.getParts(), partsDtoList);
        verify(partRepository,times(1)).findPartsModifiedSinceDate(any(),any());
    }


    @Test
    @DisplayName("Given parts, then return filtered parts and sorted by description asc")
    public void givenParts_thenReturnFilteredPartsAndSortedByDescriptionAsc() {
        List<PartEntity> partsEntityList = getList("classpath:filteredPartsEntitiesAndSortedByDescriptionAsc.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:filteredPartsAndSortedByDescriptionAsc.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findPartsModifiedSinceDateSortedByDescriptionAsc(any(),any())).thenReturn(partsEntityList);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('P');
        validFilter.setDate(pastDate);
        validFilter.setOrder(1);

        final PartResponseDTO response = partService.getPartsByFilter(validFilter);

        assertIterableEquals(response.getParts(), partsDtoList);
        verify(partRepository,times(1)).findPartsModifiedSinceDateSortedByDescriptionAsc(any(),any());
    }

    @Test
    @DisplayName("Given parts, then return filtered parts and sorted by description desc")
    public void givenParts_thenReturnFilteredPartsAndSortedByDescriptionDesc() {
        List<PartEntity> partsEntityList = getList("classpath:filteredPartsEntitiesAndSortedByDescriptionDesc.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:filteredPartsAndSortedByDescriptionDesc.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findPartsModifiedSinceDateSortedByDescriptionDesc(any(),any())).thenReturn(partsEntityList);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('P');
        validFilter.setDate(pastDate);
        validFilter.setOrder(2);

        final PartResponseDTO response = partService.getPartsByFilter(validFilter);

        assertIterableEquals(response.getParts(), partsDtoList);
        verify(partRepository,times(1)).findPartsModifiedSinceDateSortedByDescriptionDesc(any(),any());
    }


    @Test
    @DisplayName("No parts found, then throw NoPartsFoundException")
    public void noPartsFound_thenThrowNoPartsFoundException() {

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findPartsWithPriceModifiedSinceDate(any(),any())).thenReturn(new ArrayList<PartEntity>());

        PartFilterDTO filter = new PartFilterDTO();
        filter.setQueryType('V');
        filter.setDate(Date.from(Instant.now()));

        assertThrows(PartsNotFoundException.class, () -> partService.getPartsByFilter(filter));
        verify(partRepository,times(1)).findPartsWithPriceModifiedSinceDate(any(),any());
    }

    @Test
    @DisplayName("Given part, when find, then return part")
    public void givenPart_whenFind_thenReturnPart() {
        PartDTO partDTO = new PartDTO(1,"test","maker",1,1,1,1,1.00,1.00,pastDate, LocalDate.of(2021, 3, 1), 1);
        PartEntity partEntity = new PartEntity(1,"test",1,1,1,1,1,1,pastDate,pastDate,"maker","A",null, null);
        Optional<PartEntity> part = Optional.of(partEntity);

        when(this.partRepository.findById(any())).thenReturn(part);

        assertEquals(partDTO,this.partService.findPart(1));

        verify(partRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("When find by non existent id, then throw not found exception")
    public void whenFindByNonExistentId_thenThrowNotFoundException() {
        when(this.partRepository.findById(any())).thenReturn(Optional.ofNullable(null));

        assertThrows(PartsNotFoundException.class,() -> this.partService.findPart(113));

        verify(partRepository,times(1)).findById(any());
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


