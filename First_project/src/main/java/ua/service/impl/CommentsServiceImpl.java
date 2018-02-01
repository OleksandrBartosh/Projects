package ua.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Comments;
import ua.entity.Owner;
import ua.entity.Role;
import ua.entity.Transporter;
import ua.model.request.CommentRequest;
import ua.model.view.CommentsView;
import ua.repository.CommentsRepository;
import ua.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService{

	private final CommentsRepository repository;

	public CommentsServiceImpl(CommentsRepository repository) {
		this.repository = repository;
	}

	@Override
	public void saveCommentByOwner(CommentRequest request, String email, Integer id) {
		Comments comment = new Comments();
		comment.setComment(request.getComment());
		comment.setDate(LocalDateTime.now());
		comment.setRate(Integer.valueOf(request.getRate()));
		comment.setLeftBy(Role.ROLE_OWNER);
		comment.setOwner(repository.getOwner(email));
		comment.setTransporter(repository.getTansporterFPage(id));
			repository.save(comment);
		BigDecimal avg = repository.avgRateTransorter(id);
		Transporter transporter = repository.getTansporterFPage(id);
		transporter.setRate(avg);
		comment.setTransporter(transporter);
			repository.save(comment);
	}
	
	@Override
	public void saveCommentByTransporter(CommentRequest request, String email, Integer id) {
		Comments comment = new Comments();
		comment.setComment(request.getComment());
		comment.setDate(LocalDateTime.now());
		comment.setRate(Integer.valueOf(request.getRate()));
		comment.setLeftBy(Role.ROLE_TRANSPORTER);
		comment.setTransporter(repository.getTansporter(email));
		comment.setOwner(repository.getOwnerFPage(id));
			repository.save(comment);
		Owner owner = repository.getOwnerFPage(id);
		BigDecimal avg = repository.avgRateOwner(id);
		owner.setRate(avg);
		comment.setOwner(owner);
			repository.save(comment);
	}
	
	@Override
	public Role determineRole(String email) {
		return repository.determineRole(email);
	}

	@Override
	public List<CommentsView> commentsPageTransporterByEmail(String email) {
		return repository.commentsPageTransporterByEmail(email);
	}

	@Override
	public List<CommentsView> commentsPageOwnerByEmail(String email) {
		return repository.commentsPageOwnerByEmail(email);
	}

	@Override
	public Page<CommentsView> commentsPageOwner(Integer id, Pageable pageable) {
		return repository.commentsPageOwner(id, pageable);
	}

	@Override
	public Page<CommentsView> commentsPageTransporter(Integer id, Pageable pageable) {
		return repository.commentsPageTransporter(id, pageable);
	}

}