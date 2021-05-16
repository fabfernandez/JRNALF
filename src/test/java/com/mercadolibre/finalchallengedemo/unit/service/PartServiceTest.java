package com.mercadolibre.finalchallengedemo.unit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.PartResponseDTO;
import com.mercadolibre.finalchallengedemo.model.PartEntity;
import com.mercadolibre.finalchallengedemo.model.StockSubsidiaryEntity;
import com.mercadolibre.finalchallengedemo.model.SubsidiaryEntity;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidPartFilterException;
import com.mercadolibre.finalchallengedemo.exceptions.PartsNotFoundException;
import com.mercadolibre.finalchallengedemo.repository.IPartRepository;
import com.mercadolibre.finalchallengedemo.repository.IStockRepository;
import com.mercadolibre.finalchallengedemo.repository.ISubsidiaryRepository;
import com.mercadolibre.finalchallengedemo.service.PartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.util.ResourceUtils;
import java.time.Instant;
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
    @DisplayName("R1 findAll(): Ask for parts. Return all parts.")
    public void givenPartsWithoutFilter_thenReturnAllParts() {
        List<PartEntity> partsEntityList = getList("classpath:allPartsEntities.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:allParts.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();
        stock.setQuantity(2);

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findAll()).thenReturn(partsEntityList);

        final PartResponseDTO response = partService.getAll();

        assertIterableEquals(response.getParts(), partsDtoList);
        verify(partRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("R1 findAll(): Ask for parts. Throw PartsNotFoundException.")
    public void sentEmptyParts_thenThrowPartsNotFoundException() {
        List<PartEntity> partsEntityList = new ArrayList<>();

        when(this.partRepository.findAll()).thenReturn(partsEntityList);

        assertThrows(PartsNotFoundException.class, () -> partService.getAll());
        verify(partRepository,times(1)).findAll();
    }


    @Test
    @DisplayName("R1 getPartsByFilter(): Case 'C'.")
    public void givenParts_thenReturnFilteredPartsCaseC() {
        List<PartEntity> partsEntityList = getList("classpath:filteredPartsEntitiesCaseC.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:filteredPartsCaseC.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();
        stock.setQuantity(6);

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findAll()).thenReturn(partsEntityList);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('C');

        final PartResponseDTO response = partService.getAll();

        assertEquals(partsDtoList, response.getParts());
        verify(partRepository,times(1)).findAll();
    }


    @Test
    @DisplayName("R1 getPartsByFilter(): Case 'P' Order 'null'. Return parts modified since date.")
    public void givenParts_thenReturnFilteredPartsCaseP() {
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
    @DisplayName("R1 getPartsByFilter(): Case 'P' Order '1'. Return parts modified since date ordered by description asc.")
    public void givenParts_thenReturnFilteredPartsCasePAndSortedByDescriptionAsc() {
        List<PartEntity> partsEntityList = getList("classpath:filteredPartsEntitiesCasePAndSortedByDescriptionAsc.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:filteredPartsCasePAndSortedByDescriptionAsc.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();
        stock.setQuantity(6);

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findPartsModifiedSinceDateSortedByDescriptionAsc(any(),any())).thenReturn(partsEntityList);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('P');
        validFilter.setDate(pastDate);
        validFilter.setOrder(1);

        final PartResponseDTO response = partService.getPartsByFilter(validFilter);

        assertEquals(partsDtoList, response.getParts());
        verify(partRepository,times(1)).findPartsModifiedSinceDateSortedByDescriptionAsc(any(),any());
    }

    @Test
    @DisplayName("R1 getPartsByFilter(): Case 'V' Order '1'. Return parts with price modified since date ordered by description asc.")
    public void givenParts_thenReturnFilteredPartsCaseVAndSortedByDescriptionAsc() {
        List<PartEntity> partsEntityList = getList("classpath:filteredPartsEntitiesCaseVAndSortedByDescriptionAsc.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:filteredPartsCaseVAndSortedByDescriptionAsc.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();
        stock.setQuantity(6);

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findPartsWithPriceModifiedSinceDateSortedByDescriptionAsc(any(),any())).thenReturn(partsEntityList);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('V');
        validFilter.setDate(pastDate);
        validFilter.setOrder(1);

        final PartResponseDTO response = partService.getPartsByFilter(validFilter);

        assertEquals(partsDtoList, response.getParts());
        verify(partRepository,times(1)).findPartsWithPriceModifiedSinceDateSortedByDescriptionAsc(any(),any());
    }

    @Test
    @DisplayName("R1 getPartsByFilter(): Case 'P' Order '2'. Return parts modified since date ordered by description desc.")
    public void givenParts_thenReturnFilteredPartsCasePAndSortedByDescriptionDesc() {
        List<PartEntity> partsEntityList = getList("classpath:filteredPartsEntitiesCasePAndSortedByDescriptionDesc.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:filteredPartsCasePAndSortedByDescriptionDesc.json",PartDTO.class);

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
    @DisplayName("R1 getPartsByFilter(): Case 'V' Order '2'. Return parts with price modified since date ordered by description desc.")
    public void givenParts_thenReturnFilteredPartsCaseVAndSortedByDescriptionDesc() {
        List<PartEntity> partsEntityList = getList("classpath:filteredPartsEntitiesCaseVAndSortedByDescriptionDesc.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:filteredPartsCaseVAndSortedByDescriptionDesc.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();
        stock.setQuantity(9);

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findPartsWithPriceModifiedSinceDateSortedByDescriptionDesc(any(),any())).thenReturn(partsEntityList);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('V');
        validFilter.setDate(pastDate);
        validFilter.setOrder(2);

        final PartResponseDTO response = partService.getPartsByFilter(validFilter);

        assertIterableEquals(partsDtoList,response.getParts());
        verify(partRepository,times(1)).findPartsWithPriceModifiedSinceDateSortedByDescriptionDesc(any(),any());
    }

    @Test
    @DisplayName("R1 getPartsByFilter(): Case 'P' Order '3'. Return parts modified since date ordered by last modified desc.")
    public void givenParts_thenReturnFilteredPartsCasePAndSortedByByLastModified() {
        List<PartEntity> partsEntityList = getList("classpath:filteredPartsEntitiesCasePAndSortedByLastModified.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:filteredPartsCasePAndSortedByLastModified.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();
        stock.setQuantity(6);

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findPartsModifiedSinceDateSortedByLastModified(any(),any())).thenReturn(partsEntityList);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('P');
        validFilter.setDate(pastDate);
        validFilter.setOrder(3);

        final PartResponseDTO response = partService.getPartsByFilter(validFilter);

        assertIterableEquals(partsDtoList,response.getParts());
        verify(partRepository,times(1)).findPartsModifiedSinceDateSortedByLastModified(any(),any());
    }

    @Test
    @DisplayName("R1 getPartsByFilter(): Case 'V' Order '3'. Return parts modified since date ordered by last modified desc.")
    public void givenParts_thenReturnFilteredPartsCaseVAndSortedByByLastModified() {
        List<PartEntity> partsEntityList = getList("classpath:filteredPartsEntitiesCaseVAndSortedByLastModified.json",PartEntity.class);
        List<PartDTO> partsDtoList = getList("classpath:filteredPartsCaseVAndSortedByLastModified.json",PartDTO.class);

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();
        stock.setQuantity(6);

        when(this.stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stock);
        when(this.partRepository.findPartsWithPriceModifiedSinceDateSortedByLastModified(any(),any())).thenReturn(partsEntityList);

        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('V');
        validFilter.setDate(pastDate);
        validFilter.setOrder(3);

        final PartResponseDTO response = partService.getPartsByFilter(validFilter);

        assertIterableEquals(partsDtoList,response.getParts());
        verify(partRepository,times(1)).findPartsWithPriceModifiedSinceDateSortedByLastModified(any(),any());
    }

    @Test
    @DisplayName("R1 getPartsByFilter(): Case 'P' Wrong order. Throw InvalidPartFilterException.")
    public void wrongOrderCaseP_thenThrowInvalidPartFilterException() {
        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('P');
        validFilter.setDate(pastDate);
        validFilter.setOrder(4);

        assertThrows(InvalidPartFilterException.class, () -> partService.getPartsByFilter(validFilter));
    }

    @Test
    @DisplayName("R1 getPartsByFilter(): Case 'V' Wrong order. Throw InvalidPartFilterException.")
    public void wrongOrderCaseV_thenThrowInvalidPartFilterException() {
        PartFilterDTO validFilter = new PartFilterDTO();
        validFilter.setQueryType('V');
        validFilter.setDate(pastDate);
        validFilter.setOrder(4);

        assertThrows(InvalidPartFilterException.class, () -> partService.getPartsByFilter(validFilter));
    }

    @Test
    @DisplayName("R1 getPartsByFilter(): No parts found (response has parts empty), then throw NoPartsFoundException")
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
    @DisplayName("R1 findPart(Integer id): Recieve id. Return part.")
    public void givenPart_whenFind_thenReturnPart() {
        PartDTO partDTO = getObject("classpath:partDTO.json",PartDTO.class);
        PartEntity partEntity = getObject("classpath:newPartEntity.json",PartEntity.class);
        Optional<PartEntity> part = Optional.of(partEntity);

        when(this.partRepository.findById(any())).thenReturn(part);

        assertEquals(partDTO,this.partService.findPart(1));

        verify(partRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("R1 findPart(Integer id): Recieve not existing id. Throw PartsNotFoundException.")
    public void whenFindByNonExistentId_thenThrowNotFoundException() {
        when(this.partRepository.findById(any())).thenReturn(Optional.ofNullable(null));

        assertThrows(PartsNotFoundException.class,() -> this.partService.findPart(113));

        verify(partRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("R4 savePart(PartDTO part): Recieve PartDTO. Return 'Saved'.")
    public void whenAddInexistentPart_returnOk(){
        PartDTO newPart = getObject("classpath:partDTO.json",PartDTO.class);
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
    @DisplayName("R4 savePart(PartDTO part): Recieve PartDTO. Return 'Modified'.")
    public void whenAddExistentPart_returnOkWithChangedLastPriceModification(){
        PartDTO newPart = getObject("classpath:partDTO.json",PartDTO.class);

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


