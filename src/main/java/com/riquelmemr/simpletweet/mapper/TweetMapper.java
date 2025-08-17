package com.riquelmemr.simpletweet.mapper;

import com.riquelmemr.simpletweet.dto.request.CreateTweetRequest;
import com.riquelmemr.simpletweet.dto.response.FeedItemResponse;
import com.riquelmemr.simpletweet.dto.response.FeedResponse;
import com.riquelmemr.simpletweet.dto.response.TweetDetailResponse;
import com.riquelmemr.simpletweet.model.Like;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TweetMapper {
    public Tweet toModel(CreateTweetRequest createTweetRequest) {
        if (createTweetRequest == null) {
            return null;
        }

        Tweet tweet = new Tweet();
        tweet.setContent(createTweetRequest.content());
        return tweet;
    }

    public Tweet toModel(CreateTweetRequest createTweetRequest, User user) {
        if (createTweetRequest == null) {
            return null;
        }

        Tweet tweet = new Tweet();
        tweet.setContent(createTweetRequest.content());
        tweet.setAuthor(user);
        return tweet;
    }

    public FeedItemResponse toFeedItemResponseDto(Tweet tweet) {
        if (tweet == null) {
            return null;
        }

        return new FeedItemResponse(
                tweet.getPk(),
                tweet.getContent(),
                tweetAuthorUsername(tweet),
                tweetAuthorName(tweet),
                tweet.getLikes().size(),
                tweet.getComments().size(),
                tweet.getCreationTime()
        );
    }

    public TweetDetailResponse toTweetDetailResponseDto(Tweet tweet) {
        if (tweet == null) {
            return null;
        }

        Long id = tweet.getPk();
        String content = tweet.getContent();
        String username = tweetAuthorUsername(tweet);
        String name = tweetAuthorName(tweet);
        List<Like> likes = tweet.getLikes() != null ? new ArrayList<>(tweet.getLikes()) : null;
        Date creationTime = tweet.getCreationTime();

        return new TweetDetailResponse(id, content, username, name, likes, creationTime);
    }

    public FeedResponse toFeedResponseDto(Page<Tweet> tweetPage) {
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

    public FeedResponse toFeedResponseDto(List<Tweet> tweets) {
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

    private String tweetAuthorUsername(Tweet tweet) {
        if (tweet == null || tweet.getAuthor() == null) {
            return null;
        }
        return tweet.getAuthor().getUsername();
    }

    private String tweetAuthorName(Tweet tweet) {
        if (tweet == null || tweet.getAuthor() == null) {
            return null;
        }
        return tweet.getAuthor().getName();
    }
}
