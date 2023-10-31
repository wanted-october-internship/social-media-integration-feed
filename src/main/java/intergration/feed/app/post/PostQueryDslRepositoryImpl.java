package intergration.feed.app.post;


import static intergration.feed.app.post.domain.QPost.post;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import intergration.feed.app.post.domain.Post;
import intergration.feed.app.post.domain.QHashTag;
import intergration.feed.app.post.domain.type.SnsType;
import intergration.feed.app.post.dto.PostRequestDto.Filter;
import intergration.feed.app.post.dto.type.SortType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostQueryDslRepositoryImpl implements PostQueryDslRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> findAllPost(Filter filter) {
        return jpaQueryFactory.select(post).from(post)
            .where(eqHashTag(filter.getHashTag()),
                eqSnsType(filter.getSnsType()),
                containsSearch(filter.getSearchBy(), filter.getSearch()))
            .orderBy(orderSpecifier(filter.getSortType(), filter.getOrderBy())).fetch();
    }

    private BooleanExpression eqHashTag(String hashTag) {
        return hashTag != null ? QHashTag.hashTag.title.eq(hashTag) : null;
    }

    private BooleanExpression eqSnsType(SnsType snsType) {
        return snsType !=null ? post.snsType.eq(snsType) : null;
    }
    private BooleanExpression containsTitle(String search) {
        return search != null ? post.title.contains(search) : null;
    }
    private BooleanExpression containsContent(String search) {
        return search != null ? post.content.contains(search) : null;
    }

    private BooleanExpression containsSearch(String searchBy, String search) {
        if(searchBy.equals("title")) {
            return containsTitle(search);
        }else if(searchBy.equals("content")) {
            return containsContent(search);
        }
        return containsTitle(search).or(containsContent(search));
    }

    private OrderSpecifier orderSpecifier(SortType sortType, String orderBy) {
        if(orderBy != null) {
            if(orderBy.equals("createdAt")) {
                if(sortType == SortType.ASC) {
                    return new OrderSpecifier(Order.ASC, post.createdAt);
                }
                return new OrderSpecifier(Order.DESC, post.createdAt);
            }
            if(orderBy.equals("updatedAt")) {
                if(sortType == SortType.ASC) {
                    return new OrderSpecifier(Order.ASC, post.updatedAt);
                }
                return new OrderSpecifier(Order.DESC, post.updatedAt);
            }
            if(orderBy.equals("likeCount")) {
                if(sortType == SortType.ASC) {
                    return new OrderSpecifier(Order.ASC, post.likeCount);
                }
                return new OrderSpecifier(Order.DESC, post.likeCount);
            }
            if(orderBy.equals("shareCount")) {
                if(sortType == SortType.ASC) {
                    return new OrderSpecifier(Order.ASC, post.shareCount);
                }
                return new OrderSpecifier(Order.DESC, post.shareCount);
            }
            if(orderBy.equals("viewCount")) {
                if(sortType == SortType.ASC) {
                    return new OrderSpecifier(Order.ASC, post.viewCount);
                }
                return new OrderSpecifier(Order.DESC, post.viewCount);
            }
        }
        return null;
    }
}
