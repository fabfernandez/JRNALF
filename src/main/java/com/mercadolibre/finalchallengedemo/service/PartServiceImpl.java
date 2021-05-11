package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.PartResponseDTO;
import com.mercadolibre.finalchallengedemo.entities.PartEntity;
import com.mercadolibre.finalchallengedemo.entities.StockSubsidiaryEntity;
import com.mercadolibre.finalchallengedemo.entities.SubsidiaryEntity;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidPartFilterException;
import com.mercadolibre.finalchallengedemo.exceptions.PartsNotFoundException;
import com.mercadolibre.finalchallengedemo.repository.IPartRepository;
import com.mercadolibre.finalchallengedemo.repository.IStockRepository;
import com.mercadolibre.finalchallengedemo.repository.ISubsidiaryRepository;
import com.mercadolibre.finalchallengedemo.security.DecodeToken;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements IPartService {

    private final IPartRepository partRepository;
    private final IStockRepository stockRepository;
    private final ISubsidiaryRepository subsidiaryRepository;
    private ModelMapper modelMapper;

    public PartServiceImpl(IPartRepository partRepository, IStockRepository stockRepository, ISubsidiaryRepository subsidiaryRepository, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.stockRepository = stockRepository;
        this.subsidiaryRepository = subsidiaryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PartResponseDTO getAll() {
        PartResponseDTO response = mapResponse(this.partRepository.findAll());
        if(response.getParts().isEmpty())
            throw new PartsNotFoundException("No parts found with the requested filter.");
        return response;
    }

    @Override
    @Transactional()
    public PartResponseDTO getPartsByFilter(PartFilterDTO filter) {
        Integer order = filter.getOrder();
        Date filterDate = filter.getDate();
        Date currentDate = Date.from(Instant.now());
        PartResponseDTO response;
        switch (filter.getQueryType()) {
            case 'V':
                if(order != null)
                    response = getSortedPartsWithPriceVariationResponse(order,filterDate,currentDate);
                else
                    response = mapResponse(partRepository.findPartsWithPriceModifiedSinceDate(filterDate, currentDate));
                break;
            case 'P':
                if(order != null)
                    response = getSortedPartsPartialResponse(order,filterDate,currentDate);
                else
                    response = mapResponse(partRepository.findPartsModifiedSinceDate(filterDate, currentDate));
                break;
            case 'C':
            default:
                response = getAll();

        }
        if(response.getParts().isEmpty())
            throw new PartsNotFoundException("No parts founded with the requested filter.");
        return response;
    }

    @Override
    @Transactional
    public void deletePart(Integer id) {
        partRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void savePart(PartDTO part) {
        //REQ 4 Add or update a part.
        Optional<PartEntity> partFromDB = partRepository.findById(part.getPartCode());
        if (partFromDB.isPresent() && !part.getNormalPrice().equals(partFromDB.get().getNormalPrice())){
            part.setLastPriceModification(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        PartEntity partEntity = partRepository.save(modelMapper.map(part, PartEntity.class));
        System.out.println(partEntity.getStockSubsidiaryEntities());

        StockSubsidiaryEntity stock = new StockSubsidiaryEntity();
        stock.setPart(partEntity);
        stock.setQuantity(part.getQuantity());
        //Find by id en subsidiary

        SubsidiaryEntity subsidiaryEntity = subsidiaryRepository.findById(1).get();
        stock.setSubsidiary(subsidiaryEntity);

        stockRepository.save(stock);

    }

    @Override
    @Transactional()
    public PartDTO findPart(Integer id) {
        Optional<PartEntity> part = partRepository.findById(id);
        if(!part.isPresent())
            throw new PartsNotFoundException("The part with id " + id + " was not founded.");
        return modelMapper.map(part.get(), PartDTO.class);
    }

    //Returns all the parts for query type P, from repository, sorted by requested order.
    private PartResponseDTO getSortedPartsPartialResponse(Integer order, Date filterDate, Date currentDate) {
        switch (order) {
            case 1:
                return mapResponse(this.partRepository.findPartsModifiedSinceDateSortedByDescriptionAsc(filterDate,currentDate));
            case 2:
                return mapResponse(this.partRepository.findPartsModifiedSinceDateSortedByDescriptionDesc(filterDate,currentDate));
            case 3:
                return mapResponse(this.partRepository.findPartsModifiedSinceDateSortedByLastModified(filterDate,currentDate));
            default:
                throw new InvalidPartFilterException("Order must be 1,2 or 3.");
        }
    }

    //Returns all the parts for query type V, from repository, sorted by requested order.
    private PartResponseDTO getSortedPartsWithPriceVariationResponse(Integer order, Date filterDate, Date currentDate) {
        switch (order) {
            case 1:
                return mapResponse(this.partRepository.findPartsWithPriceModifiedSinceDateSortedByDescriptionAsc(filterDate,currentDate));
            case 2:
                return mapResponse(this.partRepository.findPartsWithPriceModifiedSinceDateSortedByDescriptionDesc(filterDate,currentDate));
            case 3:
                return mapResponse(this.partRepository.findPartsWithPriceModifiedSinceDateSortedByLastModified(filterDate,currentDate));
            default:
                throw new InvalidPartFilterException("Order must be 1,2 or 3.");
        }
    }

    //Prepare response mapping the parts from entities to dtos and assigns the stock for all of them depending of the user subsidiary id.
    private PartResponseDTO mapResponse(List<PartEntity> parts ) {
        PartResponseDTO response = new PartResponseDTO();
        List<PartDTO> responseParts = parts.stream().map(p -> modelMapper.map(p, PartDTO.class)).collect(Collectors.toList());
        responseParts.forEach(p->p.setQuantity(getQuantityFromPart(p)));
        response.setParts(responseParts);
        return response;
    }

    //Finds the stock corresponding to the part associated with the subsidiary.
    private Integer getQuantityFromPart(PartDTO p) {
        return this.stockRepository.findStockByPartCodeAndSubsidiary(p.getPartCode(),DecodeToken.location).getQuantity();
    }

}