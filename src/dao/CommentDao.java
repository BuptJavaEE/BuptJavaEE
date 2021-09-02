package dao;

import pojo.Comment;

import java.util.List;

/**
 * 类<code>CommentDao</code>用于:定义评论类所需要的一系列操作函数
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-09-02-10
 */
public interface CommentDao {
    /**
     * 根据用户名查找相关评论
     * @param username
     * @return List<Comment>
     */
    public List<Comment> queryCommentsByUsername(String username);

    /**
     * 保存对应用户的评论
     * @param comment
     */
    public void saveComment(Comment comment);
}
