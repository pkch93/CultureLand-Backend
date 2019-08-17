package org.nexters.cultureland.api.service;

import org.nexters.cultureland.api.dto.Diaries;
import org.nexters.cultureland.api.dto.DiaryCreateDto;
import org.nexters.cultureland.api.dto.DiaryDto;
import org.nexters.cultureland.api.dto.DiatyUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DiaryService {
    private final RepositoryService repositoryService;

    public DiaryService(final RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public Diaries fetchDiaries() {
        return repositoryService.readAllDiaries();
    }

    public Diaries fetchUserDiaries(long userId) {
        Diaries diaries = repositoryService.readUserDiaries(userId);
        return diaries;
    }

    public DiaryDto create(long userId, final DiaryCreateDto diaryDto) {
        DiaryDto diary = repositoryService.createDiary(diaryDto, userId);
        return diary;
    }

    public DiaryDto getDiaryOf(long userId, final Long diaryId) {
        DiaryDto diary = repositoryService.readDiary(userId, diaryId);
        return diary;
    }

    public DiaryDto updateDiaryOf(long userId, final Long diaryId, DiatyUpdateDto diaryDto) {
        DiaryDto diary = repositoryService.updateDiary(userId, diaryId, diaryDto);
        return diary;
    }

    public void deleteDiaryOf(long userId, final Long diaryId) {
        repositoryService.deleteDiary(userId, diaryId);
    }

    public DiaryDto like(final long userId, final Long diaryId) {
        return repositoryService.like(userId, diaryId);
    }
}