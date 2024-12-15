package research;

public class BibtexFormatter implements CitationFormatter {
    @Override
    public String format(ResearchPaper paper) {
        return String.format("@article{%d,\n  title = {%s},\n  author = {%s},\n  year = {%d},\n  pages = {%d},\n  citationNumber = {%d},\n}",
                paper.getPaperId(), paper.getTitle(), paper.getAuthor().getResearcherName(), paper.getPublicationYear(), paper.getNumberOfPages(), paper.getCitationNumber());
    }
}
