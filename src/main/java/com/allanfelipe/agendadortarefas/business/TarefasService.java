package com.allanfelipe.agendadortarefas.business;

import com.allanfelipe.agendadortarefas.business.dto.TarefasDTO;
import com.allanfelipe.agendadortarefas.business.mapper.TarefasConverter;
import com.allanfelipe.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.allanfelipe.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.allanfelipe.agendadortarefas.infrastructure.respository.TarefasRepository;
import com.allanfelipe.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class TarefasService {
    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token,
                                   TarefasDTO dto) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));

        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);

        TarefasEntity entity = tarefasConverter.paraTarefasEntity(dto);

        return tarefasConverter.paraTarefasDTO(
                tarefasRepository.save(entity));
    }

}
