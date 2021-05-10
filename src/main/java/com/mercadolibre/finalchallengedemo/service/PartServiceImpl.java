package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.partstock.StockSubsidiaryDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.PartResponseDTO;
import com.mercadolibre.finalchallengedemo.entities.PartEntity;
import com.mercadolibre.finalchallengedemo.entities.StockSubsidiaryEntity;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidPartFilterException;
import com.mercadolibre.finalchallengedemo.exceptions.PartsNotFoundException;
import com.mercadolibre.finalchallengedemo.repository.IPartRepository;
import com.mercadolibre.finalchallengedemo.repository.IStockRepository;
import com.mercadolibre.finalchallengedemo.security.DecodeToken;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements IPartService {

    private final IPartRepository partRepository;
    private final IStockRepository stockRepository;
    private ModelMapper modelMapper;

    private Integer idSubsidiary = 1;

    public PartServiceImpl(IPartRepository partRepository, IStockRepository stockRepository, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.stockRepository = stockRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PartResponseDTO getAll() {
        PartResponseDTO response = new PartResponseDTO(this.partRepository.findAll().stream().map(p -> modelMapper.map(p, PartDTO.class)).collect(Collectors.toList()));
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
                    response.setParts(getSortedPartsWithPriceVariationResponse(order,filterDate,currentDate));
                else
                    response.setParts(mapListPartsResponse(partRepository.findPartsWithPriceModifiedSinceDate(filterDate, currentDate)));
                break;
            case 'P':
                if(order != null)
                    response.setParts(getSortedPartsPartialResponse(order,filterDate,currentDate));
                else
                    response.setParts(mapListPartsResponse(partRepository.findPartsModifiedSinceDate(filterDate, currentDate, DecodeToken.location)));
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
        StockSubsidiaryDTO stock = new StockSubsidiaryDTO();
        stock.setPart(part.getPartCode());
        stock.setQuantity(part.getQuantity());
        stock.setSubsidiary(DecodeToken.location);

        partRepository.save(modelMapper.map(part, PartEntity.class));
        stockRepository.save(modelMapper.map(stock, StockSubsidiaryEntity.class));

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
                return mapListPartsResponse(this.partRepository.findPartsModifiedSinceDateSortedByDescriptionAsc(filterDate,currentDate));
            case 2:
                return mapListPartsResponse(this.partRepository.findPartsModifiedSinceDateSortedByDescriptionDesc(filterDate,currentDate));
            case 3:
                return mapListPartsResponse(this.partRepository.findPartsModifiedSinceDateSortedByLastModified(filterDate,currentDate));
            default:
                throw new InvalidPartFilterException("Order must be 1,2 or 3.");
        }
    }

    //Returns all the parts for query type V, from repository, sorted by requested order.
    private PartResponseDTO getSortedPartsWithPriceVariationResponse(Integer order, Date filterDate, Date currentDate) {
        switch (order) {
            case 1:
                return mapListPartsResponse(this.partRepository.findPartsWithPriceModifiedSinceDateSortedByDescriptionAsc(filterDate,currentDate));
            case 2:
                return mapListPartsResponse(this.partRepository.findPartsWithPriceModifiedSinceDateSortedByDescriptionDesc(filterDate,currentDate));
            case 3:
                return mapListPartsResponse(this.partRepository.findPartsWithPriceModifiedSinceDateSortedByLastModified(filterDate,currentDate));
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