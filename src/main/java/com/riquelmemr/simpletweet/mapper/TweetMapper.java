package com.riquelmemr.simpletweet.mapper;

import com.riquelmemr.simpletweet.dto.request.CreateTweetRequest;
import com.riquelmemr.simpletweet.dto.response.FeedItemResponse;
import com.riquelmemr.simpletweet.dto.response.FeedResponse;
import com.riquelmemr.simpletweet.dto.response.TweetDetailResponse;
import com.riquelmemr.simpletweet.model.Tweet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TweetMapper {
    Tweet toModel(CreateTweetRequest createTweetRequest);

    @Mapping(source = "author.username", target = "username")
    @Mapping(source = "author.name", target = "name")
    @Mapping(source = "pk", target = "id")
    FeedItemResponse toFeedItemResponseDto(Tweet tweet);

    @Mapping(source = "author.username", target = "username")
    @Mapping(source = "author.name", target = "name")
    @Mapping(source = "pk", target = "id")
    TweetDetailResponse toTweetDetailResponseDto(Tweet tweet);

    default FeedResponse toFeedResponseDto(Page<Tweet> tweetPage) {
        List<FeedItemResponse> items = tweetPage.getContent().stream()
                .map(this::toFeedItemResponseDto)
                .toList();

        return new FeedResponse(
                items,
                tweetPage.getNumber(),
                tweetPage.getSize(),
                tweetPage.getTotalPages(),
                tweetPage.getTotalElements()
        );
    }

    default FeedResponse toFeedResponseDto(List<Tweet> tweets) {
        List<FeedItemResponse> items = tweets.stream()
                .map(this::toFeedItemResponseDto)
                .toList();

        return new FeedResponse(
                items,
                0,
                tweets.size(),
                1,
                tweets.size()
        );
    }
}
