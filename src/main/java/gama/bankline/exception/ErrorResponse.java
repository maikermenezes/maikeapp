package gama.bankline.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class ErrorResponse {
	@ApiModelProperty(value = "A data da ocorrência do erro (ANO-MES-DIA HORA:MINUTO:SEGUNDO)", required = true, example = "2019-01-01 16:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    @ApiModelProperty(value = "Código de status HTTP", required = true, example = "400")
    private int status;

    @ApiModelProperty(value = "Código interno para identificar o erro", required = true, example = "400.001")
    private String codigo;

    @ApiModelProperty(value = "A mensagem de erro", required = true, example = "Não foi possível processar a operação solicitada..")
    private String error;
    
    private Object response;
    
    public Object getResponse() {
		return response;
	}
    public void setResponse(Object response) {
		this.response = response;
	}

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

