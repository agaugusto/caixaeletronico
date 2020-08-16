package br.com.adriano.caixaeletronico.error;

import lombok.Getter;

@Getter
public class ValorIndisponivelDetails {
    private String title;
    private int status;
    private String detail;
    private Long timeStamp;
    private String developorMessage;

    private ValorIndisponivelDetails(){
    }


    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private Long timeStamp;
        private String developorMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timeStamp(Long timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder developorMessage(String developorMessage) {
            this.developorMessage = developorMessage;
            return this;
        }

        public ValorIndisponivelDetails build() {
            ValorIndisponivelDetails valorIndisponivelDetails = new ValorIndisponivelDetails();
            valorIndisponivelDetails.status = this.status;
            valorIndisponivelDetails.title = this.title;
            valorIndisponivelDetails.detail = this.detail;
            valorIndisponivelDetails.timeStamp = this.timeStamp;
            valorIndisponivelDetails.developorMessage = this.developorMessage;
            return valorIndisponivelDetails;
        }
    }
}
