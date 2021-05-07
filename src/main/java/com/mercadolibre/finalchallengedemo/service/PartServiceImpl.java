package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.PartResponseDTO;
import com.mercadolibre.finalchallengedemo.entities.PartEntity;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidPartFilterException;
import com.mercadolibre.finalchallengedemo.exceptions.PartsNotFoundException;
import com.mercadolibre.finalchallengedemo.repository.IPartRepository;
import com.mercadolibre.finalchallengedemo.repository.IStockRepository;
import com.mercadolibre.finalchallengedemo.security.DecodeToken;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Repository
public class PartServiceImpl implements IPartService {

    private final IPartRepository partRepository;
    private final IStockRepository stockRepository;

    private ModelMapper modelMapper;

    public PartServiceImpl(IPartRepository partRepository, ModelMapper modelMapper, IStockRepository stockRepository) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.stockRepository = stockRepository;
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
        PartResponseDTO response = new PartResponseDTO();
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
        partRepository.save(modelMapper.map(part, PartEntity.class));
    }

    @Override
    @Transactional()
    public PartDTO findPart(Integer id) {
        Optional<PartEntity> part = partRepository.findById(id);
        if(!part.isPresent())
            throw new PartsNotFoundException("The part with id " + id + " was not founded.");
        return modelMapper.map(part.get(), PartDTO.class);
    }

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

    private PartResponseDTO mapResponse(List<PartEntity> parts ) {
        PartResponseDTO response = new PartResponseDTO();
        List<PartDTO> responseParts = parts.stream().map(p -> modelMapper.map(p, PartDTO.class)).collect(Collectors.toList());
        responseParts.forEach(p->p.setQuantity(getQuantityFromPart(p)));
        response.setParts(responseParts);
        return response;
    }

    private Integer getQuantityFromPart(PartDTO p) {
        return this.stockRepository.findStockByPartCodeAndSubsidiary(p.getPartCode(),DecodeToken.location).getQuantity();
    }


}