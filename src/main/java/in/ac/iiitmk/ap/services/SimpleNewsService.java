package in.ac.iiitmk.ap.services;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import javax.transaction.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.*;

import in.ac.iiitmk.ap.models.*;
import in.ac.iiitmk.ap.repositories.*;
import in.ac.iiitmk.ap.services.api.*;

/**
 * Default implementation of {@link NewsService}
 * @author Prashant Chaturvedi
 *
 */
@Service
@Transactional
public class SimpleNewsService implements NewsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleNewsService.class);
	
	private static final String DEFAULT_SORT_FIELD = "createdOn";
	
	private final NewsRepository mNewsRepository;
	
	@Autowired
	public SimpleNewsService (NewsRepository newsRepository) {
		this.mNewsRepository = newsRepository;
	}

	@Override
	public Optional<News> getNewsById(Long id) {
		News news = this.mNewsRepository.findOne(id);
		if (news == null) {
			LOGGER.info("No news with id: {} was found", id);
		} else {
			LOGGER.info("A news article with id: {} was found. This news is: {}", id, news);
		}
		return Optional.ofNullable(news);
	}

	@Override
	public Optional<List<News>> getAllNews() {
		List<News> allNews = this.mNewsRepository.findAll();
		LOGGER.info("Total number of news items found in the table: {}.", allNews == null? 0 : allNews.size());
		return Optional.ofNullable(allNews);
	}

	@Override
	public Optional<List<News>> getAllNewsSortedByCreationDate() {
		List<News> allNews = this.mNewsRepository.findAll(new Sort(DEFAULT_SORT_FIELD)).stream().collect(Collectors.toList());
		LOGGER.info("Total number of entries found: {}", allNews == null? 0 : allNews.size());
		if (LOGGER.isDebugEnabled()) {
			allNews.parallelStream().forEachOrdered(element -> LOGGER.debug("News: {0}", element));
		}
		return Optional.ofNullable(allNews);
	}

	@Override
	public Optional<List<News>> getTopNNewsSortedByCreationDate(int count) {
		Page<News> topNNewsPage = this.mNewsRepository.findAll(new PageRequest(0, count, Direction.DESC, DEFAULT_SORT_FIELD));
		List<News> topNNews = topNNewsPage.getContent().stream().collect(Collectors.toList());
		LOGGER.info("Total number of entries found: {}", topNNews == null ? 0 : topNNews.size());
		if (LOGGER.isDebugEnabled()) {
			topNNews.parallelStream().forEachOrdered(element -> LOGGER.debug("News: {0}", element));
		}
		return Optional.ofNullable(topNNews);
	}
	
	@Override
	public News create (News news) {
		news.setCreatedOn(LocalDateTime.now());
		LOGGER.info("Creating a new news item: {}", news);
		return this.mNewsRepository.save(news);
	}
}
