package com.riquelmemr.simpletweet.mapper;

import com.riquelmemr.simpletweet.dto.response.LikeDetailResponse;
import com.riquelmemr.simpletweet.model.Like;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LikeMapper {
    public LikeDetailResponse toLikeDetailResponseDto(Like like) {
        return new LikeDetailResponse(like.getOwner().getUsername(), like.getOwner().getName());
    }

    public List<LikeDetailResponse> toLikeDetailResponseListDto(Page<Like> likes) {
        return likes.getContent().stream().map(this::toLikeDetailResponseDto).toList();
    }

    public List<LikeDetailResponse> toLikeDetailResponseListDto(List<Like> likes) {
        return likes.stream().map(this::toLikeDetailResponseDto).toList();
    }
}
