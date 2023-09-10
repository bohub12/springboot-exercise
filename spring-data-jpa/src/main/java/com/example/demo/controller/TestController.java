package com.example.demo.controller;

import com.example.demo.controller.dto.joined.GetJoinedAlbumDto;
import com.example.demo.controller.dto.singletable.GetSingleTableAlbumDto;
import com.example.demo.controller.dto.tableperclass.GetTablePerClassAlbumDto;
import com.example.demo.domain.joined.JoinedAlbum;
import com.example.demo.domain.singletable.SingleTableAlbum;
import com.example.demo.domain.singletable.SingleTableItem;
import com.example.demo.domain.tableperclass.TablePerClassAlbum;
import com.example.demo.repository.joined.JoinedAlbumRepository;
import com.example.demo.repository.singletable.SingleTableAlbumRepository;
import com.example.demo.repository.tableperclass.TablePerClassAlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final SingleTableAlbumRepository singleTableAlbumRepository;
    private final TablePerClassAlbumRepository tablePerClassAlbumRepository;
    private final JoinedAlbumRepository joinedAlbumRepository;

    @PostMapping("/single/album")
    public long createSingleTableAlbum(@RequestBody CreateAlbumDto body) {
        SingleTableAlbum entity = singleTableAlbumRepository.save(body.toSingleTableEntity());
        return entity.getId();
    }

    @PostMapping("/table-per-class/album")
    public long createTablePerClassAlbum(@RequestBody CreateAlbumDto body) {
        TablePerClassAlbum entity = tablePerClassAlbumRepository.save(body.toTablePerClassEntity());
        return entity.getId();
    }

    @PostMapping("/joined/album")
    public long createJoinedAlbum(@RequestBody CreateAlbumDto body) {
        JoinedAlbum entity = joinedAlbumRepository.save(body.toJoinedEntity());
        return entity.getId();
    }

    @GetMapping("/single/album")
    public GetSingleTableAlbumDto getSingleTableAlbum(@RequestParam long id) {
        return new GetSingleTableAlbumDto(singleTableAlbumRepository.findById(id).orElseThrow());
    }

    @GetMapping("/table-per-class/album")
    public GetTablePerClassAlbumDto getTablePerClassAlbum(@RequestParam long id) {
        return new GetTablePerClassAlbumDto(tablePerClassAlbumRepository.findById(id).orElseThrow());
    }

    @GetMapping("/joined/album")
    public GetJoinedAlbumDto getJoinedAlbum(@RequestParam long id) {
        return new GetJoinedAlbumDto(joinedAlbumRepository.findById(id).orElseThrow());
    }
}
