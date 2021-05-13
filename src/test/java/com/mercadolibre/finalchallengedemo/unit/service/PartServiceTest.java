package com.mercadolibre.finalchallengedemo.unit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.PartResponseDTO;
import com.mercadolibre.finalchallengedemo.entities.PartEntity;
import com.mercadolibre.finalchallengedemo.entities.StockSubsidiaryEntity;
import com.mercadolibre.finalchallengedemo.entities.SubsidiaryEntity;
import com.mercadolibre.finalchallengedemo.exceptions.PartsNotFoundException;
import com.mercadolibre.finalchallengedemo.repository.IPartRepository;
import com.mercadolibre.finalchallengedemo.repository.IStockRepository;
import com.mercadolibre.finalchallengedemo.repository.ISubsidiaryRepository;
import com.mercadolibre.finalchallengedemo.security.DecodeToken;
import com.mercadolibre.finalchallengedemo.service.PartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.Part;
import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
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
    @Mock
    private ISubsidiaryRepository subsidiaryRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private PartServiceImpl partService;

    private Date pastDate;

    @BeforeEach
    public void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        MockitoAnnotations.openMocks(this);
        this.partService = new PartServiceImpl(partRepository, stockRepository,subsidiaryRepository,modelMapper);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 02);
        c.set(Calendar.DATE, 26);
        c.set(Calendar.YEAR, 2021);
        pastDate = c.getTime();
    }

    @Test
    @DisplayName("R1: Given parts, then return all parts")
    public void givenPartsWithoutFilter_thenReturnAllParts() {
        List<PartEntity> partsEntityList = getList("classpath:allPartsEntities.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:allParts.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();
        stock.setQuantity(9);

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findAll()).thenReturn(partsEntityList);

        final PartResponseDTO response = partService.getAll();

        assertIterableEquals(response.getParts(), partsDtoList);
        verify(partRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("R1: Given parts, then return filtered parts")
    public void givenParts_thenReturnFilteredParts() {
        List<PartEntity> partsEntityList = getList("classpath:filteredPartsEntities.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:filteredParts.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();
        stock.setQuantity(6);

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
    @DisplayName("R1: Given parts, then return filtered parts and sorted by description asc")
    public void givenParts_thenReturnFilteredPartsAndSortedByDescriptionAsc() {
        List<PartEntity> partsEntityList = getList("classpath:filteredPartsEntitiesAndSortedByDescriptionAsc.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:filteredPartsAndSortedByDescriptionAsc.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();
        stock.setQuantity(9);

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findPartsModifiedSinceDateSortedByDescriptionAsc(any(),any())).thenReturn(partsEntityList);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('P');
        validFilter.setDate(pastDate);
        validFilter.setOrder(1);

        final PartResponseDTO response = partService.getPartsByFilter(validFilter);

        assertIterableEquals(partsDtoList,response.getParts());
        verify(partRepository,times(1)).findPartsModifiedSinceDateSortedByDescriptionAsc(any(),any());
    }

    @Test
    @DisplayName("R1: Given parts, then return filtered parts and sorted by description desc")
    public void givenParts_thenReturnFilteredPartsAndSortedByDescriptionDesc() {
        List<PartEntity> partsEntityList = getList("classpath:filteredPartsEntitiesAndSortedByDescriptionDesc.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:filteredPartsAndSortedByDescriptionDesc.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();
        stock.setQuantity(9);

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findPartsModifiedSinceDateSortedByDescriptionDesc(any(),any())).thenReturn(partsEntityList);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('P');
        validFilter.setDate(pastDate);
        validFilter.setOrder(2);

        final PartResponseDTO response = partService.getPartsByFilter(validFilter);

        assertIterableEquals(partsDtoList,response.getParts());
        verify(partRepository,times(1)).findPartsModifiedSinceDateSortedByDescriptionDesc(any(),any());
    }


    @Test
    @DisplayName("R1: No parts found, then throw NoPartsFoundException")
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
    @DisplayName("R1: Given part, when find, then return part")
    public void givenPart_whenFind_thenReturnPart() {
        PartDTO partDTO = new PartDTO(1,"test","maker",1,"A01",1,1,1,1,1.0, 1.0, "2021-02-26");
        PartEntity partEntity = new PartEntity();
        partEntity.setPartCode(1);
        partEntity.setDescription("test");
        partEntity.setMaker("maker");
        partEntity.setDiscountType("A01");
        partEntity.setWidthDimension(1);
        partEntity.setTallDimension(1);
        partEntity.setLongDimension(1);
        partEntity.setNetWeight(1);
        partEntity.setNormalPrice(1);
        partEntity.setUrgentPrice(1);
        partEntity.setLastModification(pastDate);
        partEntity.setLastPriceModification(pastDate);
        Optional<PartEntity> part = Optional.of(partEntity);

        when(this.partRepository.findById(any())).thenReturn(part);

        assertEquals(partDTO,this.partService.findPart(1));

        verify(partRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("R1: When find by non existent id, then throw not found exception")
    public void whenFindByNonExistentId_thenThrowNotFoundException() {
        when(this.partRepository.findById(any())).thenReturn(Optional.ofNullable(null));

        assertThrows(PartsNotFoundException.class,() -> this.partService.findPart(113));

        verify(partRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("R4: Try to add a new part with correct parameters. ")
    public void whenAddInexistentPart_returnOk(){
        PartDTO newPart = new PartDTO(30,"This is a mock part", "juan", 10, "A00", 10,10,10,15, 100D, 150D,"2021-05-11");

        SubsidiaryEntity casaMatriz = getObject("classpath:subsidiaryCasaMatriz.json",SubsidiaryEntity.class);


        when(subsidiaryRepository.findById(1)).thenReturn(Optional.ofNullable(casaMatriz));
        when(partRepository.findById(any())).thenReturn(Optional.ofNullable(null));


        PartEntity partEntity = getObject("classpath:newPartEntity.json", PartEntity.class);
        when(partRepository.save(any())).thenReturn(partEntity);

        StockSubsidiaryEntity stockSubsidiaryEntity = new StockSubsidiaryEntity();
        stockSubsidiaryEntity.setSubsidiary(casaMatriz);
        stockSubsidiaryEntity.setPart(partEntity);
        stockSubsidiaryEntity.setQuantity(newPart.getQuantity());

        assertEquals(this.partService.savePart(newPart),partEntity);
    }

    @Test
    @DisplayName("R4: Try to add a part that already exists with correct parameters. ")
    public void whenAddExistentPart_returnOkWithChangedLastPriceModification(){
        PartDTO newPart = new PartDTO(123,"This is a mock part", "juan", 10, "A00", 10,10,10,15, 1000D, 1500D,"2021-05-11");

        SubsidiaryEntity casaMatriz = getObject("classpath:subsidiaryCasaMatriz.json",SubsidiaryEntity.class);

        PartEntity existingPartEntity = getObject("classpath:modifiedPartEntity.json", PartEntity.class);

        when(subsidiaryRepository.findById(1)).thenReturn(Optional.ofNullable(casaMatriz));
        when(partRepository.findById(any())).thenReturn(Optional.ofNullable(existingPartEntity));


        PartEntity partEntity = getObject("classpath:newPartEntityPriceModification.json", PartEntity.class);
        when(partRepository.save(any())).thenReturn(partEntity);

        StockSubsidiaryEntity stockSubsidiaryEntity = new StockSubsidiaryEntity();
        stockSubsidiaryEntity.setSubsidiary(casaMatriz);
        stockSubsidiaryEntity.setPart(partEntity);
        stockSubsidiaryEntity.setQuantity(newPart.getQuantity());

        assertEquals(this.partService.savePart(newPart).getLastPriceModification(),existingPartEntity.getLastPriceModification());
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


