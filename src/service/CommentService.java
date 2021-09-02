package service;

import pojo.Comment;

import java.util.List;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-09-02-11
 */
public interface CommentService {
    public List<Comment> loadComments(String username);
}
