package com.allanfelipe.agendadortarefas.business.mapper;

import com.allanfelipe.agendadortarefas.business.dto.TarefasDTO;
import com.allanfelipe.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefasEntity(TarefasDTO dto);
    TarefasDTO paraTarefasDTO(TarefasEntity entity);
}
