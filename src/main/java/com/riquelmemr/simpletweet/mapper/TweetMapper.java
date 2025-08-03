package com.riquelmemr.simpletweet.mapper;

import com.riquelmemr.simpletweet.dto.request.CreateTweetRequest;
import com.riquelmemr.simpletweet.entities.Tweet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TweetMapper {
    Tweet toModel(CreateTweetRequest createTweetRequest);
}
