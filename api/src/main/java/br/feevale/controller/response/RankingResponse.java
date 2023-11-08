package br.feevale.controller.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RankingResponse {

    private String name;
    private String image;
    private int points;
}
