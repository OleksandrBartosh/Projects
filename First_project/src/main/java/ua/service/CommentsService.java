package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Role;
import ua.model.request.CommentRequest;
import ua.model.view.CommentsView;

public interface CommentsService {

	void saveCommentByOwner(CommentRequest request, String email, Integer id);
	
	void saveCommentByTransporter(CommentRequest request, String email, Integer id);
	
	Role determineRole(String email);
	
	Page<CommentsView> commentsPageOwner(Integer id, Pageable pageable);
	
	Page<CommentsView> commentsPageTransporter(Integer id, Pageable pageable);
	
	List<CommentsView> commentsPageTransporterByEmail(String email);
	
	List<CommentsView> commentsPageOwnerByEmail(String email);
	
}
