package com.riquelmemr.simpletweet.service.commentlike;

import com.riquelmemr.simpletweet.model.Comment;
import com.riquelmemr.simpletweet.model.User;

public interface CommentLikeService {
    void createOrDelete(Comment comment, User user);
}
