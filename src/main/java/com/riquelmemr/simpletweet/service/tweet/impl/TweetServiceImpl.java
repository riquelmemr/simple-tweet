package com.riquelmemr.simpletweet.service.tweet.impl;

import com.riquelmemr.simpletweet.dto.request.UpdateTweetRequest;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;
import com.riquelmemr.simpletweet.enums.RoleEnum;
import com.riquelmemr.simpletweet.exceptions.EntityNotFoundException;
import com.riquelmemr.simpletweet.exceptions.ResourceNotAllowedException;
import com.riquelmemr.simpletweet.repository.TweetRepository;
import com.riquelmemr.simpletweet.service.tweet.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TweetServiceImpl implements TweetService {
    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public void create(Tweet tweet, User user) {
        tweet.setAuthor(user);
        tweetRepository.save(tweet);
    }

    @Override
    public Tweet update(String id, UpdateTweetRequest request, User user) {
        Tweet tweet = findById(id);
        validatePermission(user, tweet, "update");
        updateTweetData(request, tweet);
        tweetRepository.save(tweet);
        return tweet;
    }

    @Override
    public void deleteById(String id, User user) {
        Tweet tweet = findById(id);
        validatePermission(user, tweet, "delete");
        tweetRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Tweet findById(String id) {
        return tweetRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Tweet not found with id [" + id + "]"));
    }

    @Override
    public List<Tweet> findByUserId(String userId) {
        return tweetRepository.findByAuthorPk(UUID.fromString(userId));
    }

    @Override
    public Page<Tweet> findAll(int page, int pageSize) {
        return tweetRepository.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC, "creationTime"));
    }

    private void validatePermission(User user, Tweet tweet, String action) {
        boolean isAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals(RoleEnum.ADMIN.name()));

        boolean isOwner = tweet.getAuthor().getPk().equals(user.getPk());

        if (!isAdmin && !isOwner) {
            throw new ResourceNotAllowedException("You do not have permission to " + action + " this tweet.");
        }
    }

    private void updateTweetData(UpdateTweetRequest request, Tweet tweet) {
        tweet.setContent(request.content());
    }
}
