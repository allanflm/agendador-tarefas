package com.allanfelipe.agendadortarefas.business;

import com.allanfelipe.agendadortarefas.business.dto.TarefasDTO;
import com.allanfelipe.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.allanfelipe.agendadortarefas.business.mapper.TarefasConverter;
import com.allanfelipe.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.allanfelipe.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.allanfelipe.agendadortarefas.infrastructure.respository.TarefasRepository;
import com.allanfelipe.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefasService {
    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

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

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return tarefasConverter.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);

        return tarefasConverter.paraListaTarefasDTO(listaTarefas);
    }

    public void deletaTarefaPorId(String id){
        try{
            tarefasRepository.deleteById(id);
        }catch (ResourceAccessException e){
            throw new RuntimeException("Erro ao deletar a tarefa por ID: " + e.getCause() + " -> "  + id);
        }

    }

    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id){
        try {
            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));

            entity.setStatusNotificacaoEnum(status);
            TarefasEntity updatedEntity = tarefasRepository.save(entity);

            return tarefasConverter.paraTarefasDTO(updatedEntity);
        }catch (ResourceAccessException e){
            throw new RuntimeException("Erro ao alterar o status da tarefa por ID: " + e.getCause() + " -> "  + id);
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id){
        try {
            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));
            tarefaUpdateConverter.updateTarefas(dto, entity);
            return  tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));

        }catch (ResourceAccessException e){
            throw new RuntimeException("Erro ao alterar o status da tarefa por ID: " + e.getCause() + " -> "  + id);
        }
    }

}
