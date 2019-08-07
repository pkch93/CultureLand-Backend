package org.nexters.cultureland.api.controller;

import org.modelmapper.ModelMapper;
import org.nexters.cultureland.api.dto.CultureDetailDto;
import org.nexters.cultureland.api.service.CultureServiceImpl;
import org.nexters.cultureland.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/cultureInfos")
public class CultureController {

    private final int PAGE_SIZE = 5;

    @Autowired
    private CultureServiceImpl cultureService;

    @Autowired
    private ModelMapper modelMapper;

    ///cultureInfos?category={category}&sort={sort}&page={page} 문화생활 전체 목록 조회(최신순(new), 인기순(popular), 기본값: 최신순(new)), 카테고리에 맞는 문화생활 조회
    @GetMapping
    public ResponseMessage readCultures(@RequestParam(value = "category", required = false, defaultValue = "") String category,
                                        @RequestParam(value = "sort", required = false, defaultValue = "new") String sort,
                                        @RequestParam(value = "page", required = false, defaultValue = "0") int page){

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();

        Pageable pageable = sort.equals("new") ? PageRequest.of(page, PAGE_SIZE,new Sort(Sort.Direction.DESC,"startDate"))
                :  PageRequest.of(page, PAGE_SIZE,new Sort(Sort.Direction.ASC,"id"));

        //id,imgUrl 전체 목록 조회
        if(category.equals(""))
            responseMessage.setMessage(cultureService.getAll(pageable));

        //카테고리에 맞는 문화생활 조회
        else
            responseMessage.setMessage(cultureService.getByCategoryPage(category,pageable));

        return responseMessage;
    }

    //검색어`title`에 맞는 문화생활 조회
    @GetMapping("/{title}")
    public ResponseMessage readListByTitle(@PathVariable("title") String title) {
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        List<Object> cultureRawDatas = cultureService.getBySearch(title);
        responseMessage.setMessage(cultureRawDatas);
        return responseMessage;
    }


    //검색어`query`에 맞는 제목 조회
    @GetMapping("/search")
    public ResponseMessage readBySearch(@RequestParam(value = "query", required = false, defaultValue = "") String query){

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        List<Object> cultureRawDatas = cultureService.getTitleBySearch(query);
        responseMessage.setMessage(cultureRawDatas);
        return responseMessage;
    }

    //문화생활 상세조회
    @GetMapping("/{cultureInfoId}")
    public ResponseMessage readDetailById(@PathVariable("cultureInfoId") Long id) {

        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        CultureDetailDto culture= cultureService.getByCultureId(id);
        responseMessage.setMessage(culture);

        return responseMessage;
    }

    //페이지로 반환 전체 리스트
    @GetMapping("/test")
    public ResponseMessage testall() {
        //@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 5)
        ResponseMessage responseMessage = ResponseMessage.getOkResponseMessage();
        Pageable p = PageRequest.of(1, PAGE_SIZE);
        cultureService.getByCategoryPage("play", p);
        return responseMessage;
    }

}
