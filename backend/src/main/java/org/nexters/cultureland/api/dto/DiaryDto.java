package org.nexters.cultureland.api.dto;

import lombok.*;
import org.nexters.cultureland.api.model.Culture;
import org.nexters.cultureland.api.model.Diary;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiaryDto {
    private long id;
    private String title;
    private LocalDateTime sometime;
    private String place;
    private String withWho;
    private String content;
    private Culture culture;

    public DiaryDto(Diary diary){
        this.id = diary.getId();
        this.title = diary.getTitle();
        this.sometime = diary.getSometime();
        this.content = diary.getContent();
        this.place = diary.getPlace();
        this.withWho = diary.getWithWho();
        this.culture = diary.getCulture();
    }
}
