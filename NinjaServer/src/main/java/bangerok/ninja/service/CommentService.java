package bangerok.ninja.service;

import bangerok.ninja.domain.Comment;
import bangerok.ninja.domain.User;
import bangerok.ninja.domain.Views;
import bangerok.ninja.dto.EventType;
import bangerok.ninja.dto.ObjectType;
import bangerok.ninja.repo.CommentRepo;
import bangerok.ninja.util.WsSender;
import java.util.function.BiConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

		private final CommentRepo commentRepo;
		private final BiConsumer<EventType, Comment> wsSender;

		@Autowired
		public CommentService(CommentRepo commentRepo, WsSender wsSender) {
				this.commentRepo = commentRepo;
				this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
		}

		public Comment create(Comment comment, User user) {
				comment.setAuthor(user);
				Comment commentFromDb = commentRepo.save(comment);

				wsSender.accept(EventType.CREATE, commentFromDb);

				return commentFromDb;
		}
}
