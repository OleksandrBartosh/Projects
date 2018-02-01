package ua.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Comments;
import ua.entity.Owner;
import ua.entity.Role;
import ua.entity.Transporter;
import ua.model.view.CommentsView;

public interface CommentsRepository extends JpaRepository<Comments, Integer>{

	@Query("SELECT o FROM User u JOIN u.owner o WHERE u.email=?1")
	Owner getOwner(String email);
	
	@Query("SELECT o FROM Owner o WHERE o.id=?1")
	Owner getOwnerFPage(Integer id);
	
	@Query("SELECT t FROM Transporter t WHERE t.id=?1")
	Transporter getTansporterFPage(Integer id);

	@Query("SELECT t FROM User u JOIN u.transporter t WHERE u.email=?1")
	Transporter getTansporter(String email);
	
	@Query("SELECT u.role FROM User u WHERE u.email=?1")
	Role determineRole(String email);
	
	@Query(value="SELECT new ua.model.view.CommentsView(c.id, c.comment, c.date, t.name , t.surname, c.rate) FROM Comments c JOIN c.transporter t JOIN c.owner o WHERE c.leftBy=ua.entity.Role.ROLE_TRANSPORTER AND o.id=?1 ORDER BY c.date DESC",
			countQuery= "SELECT (c.id) FROM Comments c JOIN c.transporter t JOIN c.owner o WHERE c.leftBy=ua.entity.Role.ROLE_TRANSPORTER AND o.id=?1")
	Page<CommentsView> commentsPageOwner(Integer id, Pageable pageable);

	@Query(value="SELECT new ua.model.view.CommentsView(c.id, c.comment, c.date, o.name , o.surname, c.rate) FROM Comments c JOIN c.transporter t JOIN c.owner o WHERE c.leftBy=ua.entity.Role.ROLE_OWNER AND t.id=?1 ORDER BY c.date DESC",
			countQuery="SELECT (c.id) FROM Comments c JOIN c.transporter t JOIN c.owner o WHERE c.leftBy=ua.entity.Role.ROLE_OWNER AND t.id=?1")
	Page<CommentsView> commentsPageTransporter(Integer id, Pageable pageable);
	
	@Query("SELECT new ua.model.view.CommentsView(c.id, c.comment, c.date, o.name , o.surname, c.rate) FROM Comments c JOIN c.transporter t JOIN t.user u JOIN c.owner o WHERE c.leftBy=ua.entity.Role.ROLE_OWNER AND u.email=?1 ORDER BY c.date DESC")
	List<CommentsView> commentsPageTransporterByEmail(String email);

	@Query("SELECT new ua.model.view.CommentsView(c.id, c.comment, c.date, o.name , o.surname, c.rate) FROM Comments c JOIN c.owner o JOIN o.user u JOIN c.transporter t WHERE c.leftBy=ua.entity.Role.ROLE_TRANSPORTER AND u.email=?1 ORDER BY c.date DESC")
	List<CommentsView> commentsPageOwnerByEmail(String email);
	
	@Query("SELECT AVG(c.rate) FROM Comments c JOIN c.transporter t WHERE t.id=?1 AND c.leftBy=ua.entity.Role.ROLE_OWNER ")
	BigDecimal avgRateTransorter(Integer id);
	
	@Query("SELECT AVG(c.rate) FROM Comments c JOIN c.owner o WHERE o.id=?1 AND c.leftBy=ua.entity.Role.ROLE_TRANSPORTER ")
	BigDecimal avgRateOwner(Integer id);
}