package observers;

import news.Journal;
import news.News;
import research.ResearchProject;

public interface Observer {
	 void update(Journal journal, ResearchProject researchProject);
	 void update(News news);
}
