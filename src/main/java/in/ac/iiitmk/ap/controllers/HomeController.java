package in.ac.iiitmk.ap.controllers;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import in.ac.iiitmk.ap.controllers.handlers.*;
import in.ac.iiitmk.ap.models.web.*;
import in.ac.iiitmk.ap.services.api.*;
import in.ac.iiitmk.ap.utils.*;

@Controller
public class HomeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	private static final String NEWS_HOLDER = "newsHolder";
	
	private static final int MAX_NEWS_TO_DISPLAY = 4;
	
	private NewsService mNewsService;
	
	private ISummarizeLongText mLongTextSummarizer;
	
	@Autowired
	public HomeController (NewsService newsService, ISummarizeLongText longTestSummarizer) {
		this.mNewsService = newsService;
		this.mLongTextSummarizer = longTestSummarizer;
	}
	
	@RequestMapping (value = {"/", "/home"}, method = RequestMethod.GET)
	public Page showHomePage (ModelMap modelMap) {
		List<ShortNews> shortNewsList = new LinkedList<>();
		this.mNewsService.getTopNNewsSortedByCreationDate(MAX_NEWS_TO_DISPLAY).ifPresent(listOfNews -> {
			listOfNews.stream().forEach(news -> {
				ShortNews shortNews = new ShortNews(news.getId(), news.getTitle(), this.mLongTextSummarizer.summarize(news.getNews()), news.getCreatedOn());
				LOGGER.info("ShortNews: {}", shortNews);
				shortNewsList.add(shortNews);
			});
		});
		modelMap.addAttribute(NEWS_HOLDER, shortNewsList);
		LOGGER.debug(modelMap.get(NEWS_HOLDER).toString());
		return Page.PAGE_PUBLIC_HOME;
	}
}
