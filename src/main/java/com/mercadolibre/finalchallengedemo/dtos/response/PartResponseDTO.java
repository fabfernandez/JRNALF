package com.mercadolibre.finalchallengedemo.dtos.response;

import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PartResponseDTO {
    List<PartDTO> parts;
}
