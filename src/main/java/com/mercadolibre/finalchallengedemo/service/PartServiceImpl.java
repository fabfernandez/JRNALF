package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.entities.PartEntity;
import com.mercadolibre.finalchallengedemo.exceptions.PartsNotFoundedException;
import com.mercadolibre.finalchallengedemo.repository.IPartRepository;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements IPartService {

    private final IPartRepository partRepository;
    @Autowired
    private ModelMapper modelMapper;

    public PartServiceImpl(IPartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<PartDTO> getAll() {
        return this.partRepository.findAll().stream().map(p -> modelMapper.map(p,PartDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional()
    public List<PartDTO> getPartsByFilter(PartFilterDTO filter) {
        switch (filter.getQueryType()) {
            case 'P':
                return null;
                //return this.partRepository.findPartsModifiedSinceDate(filter.getDate());
            case 'V':
                return null;
                //return this.partRepository.findPartsPriceModifiedSinceDate(filter.getDate());
            case 'C':
            default:
                return getAll();
        }
    }

    @Override
    @Transactional
    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void savePart(PartDTO part) {
        partRepository.save(modelMapper.map(part,PartEntity.class));
    }

    @Override
    @Transactional()
    public PartDTO findPart(Long id) {
        Optional<PartEntity> part = partRepository.findById(id);
        if(!part.isPresent())
            throw new PartsNotFoundedException("The part with id " + id + " was not founded.");
        return modelMapper.map(part, PartDTO.class);
    }


}
