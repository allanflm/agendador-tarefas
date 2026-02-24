package com.allanfelipe.agendadortarefas.business.dto;

import com.allanfelipe.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mongodb.annotations.NotThreadSafe;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasDTO {

    private String id;
    private String nomeDaTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;

    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
